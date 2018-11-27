package com.magarex.memelord.data.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.magarex.memelord.data.UserRepository;
import com.magarex.memelord.data.models.User;
import com.magarex.memelord.utils.FirebasePaths;
import com.magarex.memelord.utils.FirebaseQueryLiveData;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

/**
 * Created by sahil on 14/11/18.
 **/
@Singleton
public class UserRepositoryImpl implements UserRepository {

    private DatabaseReference usersRef;

    @Inject
    public UserRepositoryImpl(DatabaseReference baseDatabaseReference) {
        this.usersRef = baseDatabaseReference.child(FirebasePaths.USERS_DB_PATH);
    }

    @Override
    public LiveData<User> getUserInfo(@NonNull String userId) {
        FirebaseQueryLiveData userData = new FirebaseQueryLiveData(usersRef.child(userId));
        return Transformations.map(userData, input -> input.getValue(User.class));
    }

    @Override
    public LiveData<OperationStatus> updateMyInfo(String userId, String provider, String profilePic, String providerIdentifier, String displayName, String fcmToken) {
        final MutableLiveData<OperationStatus> status = new MutableLiveData<>();

        Map<String, Object> newValues = new HashMap<>();
        newValues.put(FirebasePaths.USER_PROVIDER_PATH, provider);
        newValues.put(FirebasePaths.USER_PROVIDER_IDENTIFIER_PATH, providerIdentifier);
        newValues.put(FirebasePaths.USER_ID_PATH, userId);
        newValues.put(FirebasePaths.USER_PIC_PATH, profilePic);
        newValues.put(FirebasePaths.USER_NAME_PATH, displayName);
        newValues.put(FirebasePaths.USER_FCM_TOKEN_PATH, fcmToken);

        usersRef.child(userId).updateChildren(newValues)
                .addOnSuccessListener(a -> status.postValue(OperationStatus
                        .getCompletedStatus()))
                .addOnFailureListener(ex -> {
                    status.postValue(OperationStatus.getErrorStatus(ex.getMessage()));
                    ex.printStackTrace();
                });

        return status;
    }

    @Override
    public LiveData<OperationStatus> updateMyFCMToken(@NonNull String fcmToken) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final MutableLiveData<OperationStatus> status = new MutableLiveData<>();
        if (currentUser != null) {
            usersRef.child(currentUser.getUid())
                    .child(FirebasePaths.USER_FCM_TOKEN_PATH)
                    .setValue(fcmToken)
                    .addOnSuccessListener(aVoid -> status.postValue(OperationStatus
                            .getCompletedStatus()))
                    .addOnFailureListener(e -> {
                        status.postValue(OperationStatus.getErrorStatus(e.getMessage()));
                        e.printStackTrace();
                    });
        }
        return status;
    }
}