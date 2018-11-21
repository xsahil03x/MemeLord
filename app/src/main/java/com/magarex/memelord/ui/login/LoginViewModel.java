package com.magarex.memelord.ui.login;

import com.magarex.memelord.data.UserRepository;
import com.magarex.memelord.data.remote.OperationStatus;
import com.magarex.memelord.data.remote.UserRepositoryImpl;
import com.magarex.memelord.ui.base.BaseViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 14/11/18.
 **/
public class LoginViewModel extends BaseViewModel {

    private UserRepository userRepository;

    @Inject
    LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    LiveData<OperationStatus> updateCurrentUserInfo(
            @NonNull String userId, String provider, String profilePic, String
            providerIdentifier, String displayName, String fcmToken) {
        return userRepository.updateMyInfo(userId, provider, profilePic, providerIdentifier,
                displayName, fcmToken);
    }
}