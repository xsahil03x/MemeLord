package com.magarex.memelord.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.magarex.memelord.R;
import com.magarex.memelord.ui.login.LoginActivity;
import com.magarex.memelord.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Check if the user has already logged in
                if (FirebaseAuth.getInstance().getCurrentUser() != null)
                    navigateToHome();
                else
                    navigateToLogin();
            }
        }, 3000);
    }

    private void navigateToHome() {
        Intent homeScreenIntent = new Intent(this, MainActivity.class);
        startActivity(homeScreenIntent);
        finish();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}