package com.magarex.memelord.data;

import android.net.Uri;

import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.remote.OperationStatus;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 19/11/18.
 **/
public interface StorageRepository {

    LiveData<OperationStatus> uploadPost(Uri imageUri, Post post);

    LiveData<List<Post>> getAllPosts();

}
