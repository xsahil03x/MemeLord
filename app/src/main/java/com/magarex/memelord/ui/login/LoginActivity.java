package com.magarex.memelord.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.magarex.memelord.R;
import com.magarex.memelord.databinding.ActivityLoginBinding;
import com.magarex.memelord.ui.base.BaseActivity;
import com.magarex.memelord.ui.main.MainActivity;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding> {

    private static final int RC_SIGN_IN = 3333;
    private String fcmToken;

    @Override
    protected int provideLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected Class<LoginViewModel> provideViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO perform internet connectivity check here and take appropriate action
        launchLogin();
        getFCMToken();

    }

    private void launchLogin() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());
        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme_MemeLord_FullScreen)
                        .setLogo(R.drawable.com_facebook_button_login_logo)
                        .build(),
                RC_SIGN_IN);
    }

    private void getFCMToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    if (task.getResult() == null) {
                        return;
                    }
                    fcmToken = task.getResult().getToken();
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (resultCode == RESULT_OK && user != null) {

                String provider = response == null ? "Firebase" : response.getProviderType();
                String providerId = user.getEmail();
                String userId = user.getUid();
                String displayName = user.getDisplayName();
                String pic = user.getPhotoUrl() == null ? null : user.getPhotoUrl().toString();
                String profilePic;
                switch (provider) {
                    case "twitter.com":
                        profilePic = pic != null ? pic.replace("normal", "400x400") : null;
                        break;
                    case "facebook.com":
                        profilePic = pic != null ? pic.concat("?height=300") : null;
                        break;
                    case "google.com":
                        profilePic = pic != null ? pic.replace("s96-c", "s300-c") : null;
                        break;
                    default:
                        profilePic = pic;
                }

                getViewModel().updateCurrentUserInfo(userId, provider, profilePic, providerId,
                        displayName, fcmToken)
                        .observe(this, status -> {
                            if (status != null) {
                                if (status.isErroneous()) {
                                    Toast.makeText(this, status.getExtra(), Toast.LENGTH_SHORT)
                                            .show();
                                } else if (status.isComplete()) {
                                    navigateToHome();
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void navigateToHome() {
        Intent homeScreenIntent = new Intent(this, MainActivity.class);
        startActivity(homeScreenIntent);
        finish();
    }
}
