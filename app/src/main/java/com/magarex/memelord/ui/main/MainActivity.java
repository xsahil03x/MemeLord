package com.magarex.memelord.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.magarex.memelord.R;
import com.magarex.memelord.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mainBinding.mainToolbar);
        prepareBottomNavBar();
        if (savedInstanceState == null)
            mainBinding.mainBottomNav.setSelectedItemId(R.id.navigation_home);

        mainBinding.fabAddMeme.setOnClickListener(v -> {
            // TODO : Open Add Meme Screen
        });
    }

    private void prepareBottomNavBar() {
        mainBinding.mainBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(HomeFragment.getInstance());
                    break;
                case R.id.navigation_profile:
                    loadFragment(ProfileFragment.getInstance());
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(mainBinding.mainFrame.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_info:
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_leaderboard:
                Toast.makeText(this, "Leaderboard", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
