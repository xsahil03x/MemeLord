package com.magarex.memelord.ui.main;

import com.magarex.memelord.data.StorageRepository;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.remote.OperationStatus;
import com.magarex.memelord.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by sahil on 16/11/18.
 **/
public class HomeViewModel extends BaseViewModel {

    private StorageRepository storageRepository;
    private LiveData<List<Post>> postList;

    @Inject
    HomeViewModel(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
        if (postList != null) {
            return;
        }
        postList = getAllPosts();
    }

    LiveData<OperationStatus> incrementUpvoteCount(String postId) throws InterruptedException {
        return storageRepository.incrementUpvoteCount(postId);
    }

    private LiveData<List<Post>> getAllPosts() {
        return storageRepository.getAllPosts();
    }

    LiveData<List<Post>> getPostList() {
        return postList;
    }
}
