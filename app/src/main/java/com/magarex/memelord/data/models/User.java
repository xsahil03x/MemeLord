package com.magarex.memelord.data.models;

import com.magarex.memelord.utils.FirebasePaths;

/**
 * Created by sahil on 14/11/18.
 **/
public class User {

    private String userId;
    private String profilePicURL;
    private String signInService; // google or twitter
    private String signInServiceIdentifier;
    private String displayName;
    private String fcmToken;

    public User() {
    }

    public User(String userId, String profilePicURL, String signInService, String signInServiceIdentifier, String displayName, String fcmToken) {
        this.userId = userId;
        this.profilePicURL = profilePicURL;
        this.signInService = signInService;
        this.signInServiceIdentifier = signInServiceIdentifier;
        this.displayName = displayName;
        this.fcmToken = fcmToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public String getSignInService() {
        return signInService;
    }

    public void setSignInService(String signInService) {
        this.signInService = signInService;
    }

    public String getSignInServiceIdentifier() {
        return signInServiceIdentifier;
    }

    public void setSignInServiceIdentifier(String signInServiceIdentifier) {
        this.signInServiceIdentifier = signInServiceIdentifier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
