package com.magarex.memelord.ui.main;

import com.magarex.memelord.data.StorageRepository;
import com.magarex.memelord.data.UserRepository;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.models.User;
import com.magarex.memelord.data.remote.OperationStatus;
import com.magarex.memelord.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 16/11/18.
 **/
public class ProfileViewModel extends BaseViewModel {

    private StorageRepository storageRepository;
    private UserRepository userRepository;

    @Inject
    ProfileViewModel(StorageRepository storageRepository, UserRepository userRepository) {
        this.storageRepository = storageRepository;
        this.userRepository = userRepository;
    }

    LiveData<User> loadUserData(String userId) {
        return userRepository.getUserInfo(userId);
    }

    LiveData<OperationStatus> incrementUpvoteCount(String postId) {
        return storageRepository.incrementUpvoteCount(postId);
    }

    LiveData<List<Post>> getAllPostsOfUser(String userId) {
        return storageRepository.getAllPostsOfUser(userId);
    }

}
