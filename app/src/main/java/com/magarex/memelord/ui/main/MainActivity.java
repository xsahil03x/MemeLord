package com.magarex.memelord.ui.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.magarex.memelord.R;
import com.magarex.memelord.databinding.ActivityMainBinding;
import com.magarex.memelord.services.MemeTemplateJobUtilities;
import com.magarex.memelord.ui.addmeme.AddMemeActivity;
import com.magarex.memelord.utils.AppUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, EasyPermissions.PermissionCallbacks {

    private ActivityMainBinding mainBinding;
    private static final String STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String TAG = "MainActivity";
    private static final int RC_STORAGE_PERM = 333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mainBinding.mainToolbar);
        prepareBottomNavBar();
        if (savedInstanceState == null) {
            MemeTemplateJobUtilities.scheduleTemplateFetch(this);
            mainBinding.mainBottomNav.setSelectedItemId(R.id.navigation_home);
        }

        mainBinding.fabAddMeme.setOnClickListener(v -> startAddMemeActivity());
    }

    @AfterPermissionGranted(RC_STORAGE_PERM)
    private void startAddMemeActivity() {
        if (EasyPermissions.hasPermissions(this, STORAGE_PERMISSION)) {
            Intent intent = new Intent(this, AddMemeActivity.class);
            startActivity(intent);
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "You need to give storage permission in order to add Upload Meme",
                    RC_STORAGE_PERM,
                    STORAGE_PERMISSION
            );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            AppUtils.showMaterialSnackBar(findViewById(R.id.mainLayout), "Go On...");
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.i(TAG, "onPermissionsGranted: " + perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


}
