package com.magarex.memelord.data;

import com.magarex.memelord.data.models.User;
import com.magarex.memelord.data.remote.OperationStatus;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 14/11/18.
 **/
public interface UserRepository {

    LiveData<User> getUserInfo(String userId);

    LiveData<OperationStatus> updateMyInfo(String userId, String provider, String
            profilePic, String providerIdentifier, String displayName, String fcmToken);

    LiveData<OperationStatus> updateMyFCMToken(@NonNull String fcmToken);

}
