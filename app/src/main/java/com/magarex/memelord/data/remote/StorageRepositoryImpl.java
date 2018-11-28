package com.magarex.memelord.data.remote;

import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.magarex.memelord.data.StorageRepository;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.utils.FirebasePaths;
import com.magarex.memelord.utils.FirebaseQueryLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

/**
 * Created by sahil on 19/11/18.
 **/
public class StorageRepositoryImpl implements StorageRepository {

    private DatabaseReference postsRef;
    private StorageReference storageRef;

    @Inject
    public StorageRepositoryImpl(DatabaseReference baseDatabaseReference, StorageReference baseStorageReference) {
        this.postsRef = baseDatabaseReference.child(FirebasePaths.POSTS_DB_PATH);
        this.storageRef = baseStorageReference.child(FirebasePaths.POSTS_STORAGE_PATH);
    }

    @Override
    public LiveData<OperationStatus> uploadPost(@NonNull Uri imageUri, @NonNull Post post) {
        final MutableLiveData<OperationStatus> status = new MutableLiveData<>();
        storageRef.child(imageUri.getLastPathSegment()).putFile(imageUri)
                .addOnProgressListener(snapshot -> {
                    int progressPct = (int) ((100 * snapshot.getBytesTransferred()) / snapshot
                            .getTotalByteCount());
                    status.postValue(OperationStatus.getInProgresStatus(progressPct));
                })
                .continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        throw Objects.requireNonNull(task.getException());
                    }
                    return storageRef.child(imageUri.getLastPathSegment()).getDownloadUrl();
                })
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        postsRef = postsRef.push();
                        post.setDownloadURL(task.getResult().toString());
                        post.setPostId(postsRef.getKey());
                        postsRef.setValue(post)
                                .addOnSuccessListener(a -> status.postValue(OperationStatus
                                        .getCompletedStatus(task.getResult().toString())))
                                .addOnFailureListener(ex -> {
                                    status.postValue(OperationStatus.getErrorStatus(ex.getMessage()));
                                    ex.printStackTrace();
                                });
                    } else {
                        status.postValue(
                                OperationStatus.getErrorStatus(task.getException() == null
                                        ? "ERROR"
                                        : task.getException().toString()));
                    }
                })
                .addOnFailureListener(e -> {
                    status.postValue(OperationStatus.getErrorStatus(e.getMessage()));
                    e.printStackTrace();
                });

        return status;
    }

    @Override
    public LiveData<List<Post>> getAllPosts() {
        FirebaseQueryLiveData postsData = new FirebaseQueryLiveData(postsRef);
        return Transformations.map(postsData, input -> {
            List<Post> posts = new ArrayList<>();
            for (DataSnapshot snapshot : input.getChildren()) {
                Post singlePost = new Post();
                try {
                    singlePost = snapshot.getValue(Post.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (singlePost != null) {
                    singlePost.setPostId(snapshot.getKey());
                    posts.add(singlePost);
                }
            }
            Collections.reverse(posts);
            return posts;
        });
    }

    @Override
    public List<Post> getPostsForWidget(int limit) throws InterruptedException {
        Query photoQuery = postsRef;
        List<Post> posts = new ArrayList<>();

        DataSnapshot dataSnapshot = (new FirebaseQueryLiveData(photoQuery)).singleFetch(limit);

        if (dataSnapshot != null) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Post singlePost = snapshot.getValue(Post.class);
                if (singlePost != null) {
                    singlePost.setPostId(snapshot.getKey());
                    posts.add(singlePost);
                }
            }
            Collections.reverse(posts);
        }

        return posts;
    }

    @Override
    public LiveData<List<Post>> getAllPostsOfUser(@NonNull String userId) {
        Query postsByIdRef = postsRef.orderByChild("uploaderId").equalTo(userId);
        FirebaseQueryLiveData postsData = new FirebaseQueryLiveData(postsByIdRef);
        return Transformations.map(postsData, input -> {
            List<Post> posts = new ArrayList<>();
            for (DataSnapshot snapshot : input.getChildren()) {
                Post singlePost = snapshot.getValue(Post.class);
                if (singlePost != null) {
                    singlePost.setPostId(snapshot.getKey());
                    posts.add(singlePost);
                }
            }
            Collections.reverse(posts);
            return posts;
        });
    }

    @Override
    public LiveData<OperationStatus> incrementUpvoteCount(@NonNull String photoId) {
        DatabaseReference reference = postsRef.child(photoId).child(FirebasePaths
                .PHOTOS_UPVOTE_COUNT_PATH);
        final MutableLiveData<OperationStatus> status = new MutableLiveData<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer upvoteCount = dataSnapshot.getValue(Integer.class);
                if (upvoteCount == null) {
                    upvoteCount = 0;
                }
                upvoteCount++;
                reference.setValue(upvoteCount).addOnCompleteListener(command -> {
                    if (command.isSuccessful()) {
                        status.postValue(OperationStatus.getCompletedStatus());
                    } else {
                        status.postValue(OperationStatus.getErrorStatus(
                                command.getException() == null ? "Error" : command
                                        .getException().getMessage()));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                status.postValue(OperationStatus.getErrorStatus(databaseError.getMessage()));
            }
        });
        return status;
    }
}

