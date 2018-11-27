package com.magarex.memelord.data.remote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.magarex.memelord.data.LeaderBoardRepository;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.models.User;
import com.magarex.memelord.utils.FirebasePaths;
import com.magarex.memelord.utils.FirebaseQueryLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

/**
 * Created by sahil on 24/11/18.
 **/
public class LeaderBoardRepositoryImpl implements LeaderBoardRepository {

    private DatabaseReference postsRef;

    @Inject
    public LeaderBoardRepositoryImpl(DatabaseReference baseDatabaseRef) {
        this.postsRef = baseDatabaseRef.child(FirebasePaths.POSTS_DB_PATH);
    }

    @Override
    public LiveData<List<Post>> getTopPostsList() {
        Query postsByUpvotes = postsRef.orderByChild("upvoteCount");
        FirebaseQueryLiveData postsData = new FirebaseQueryLiveData(postsByUpvotes);
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


}



