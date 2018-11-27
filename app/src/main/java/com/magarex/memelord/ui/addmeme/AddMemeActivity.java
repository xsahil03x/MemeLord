package com.magarex.memelord.ui.addmeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.magarex.memelord.R;
import com.magarex.memelord.data.remote.OperationStatus;
import com.magarex.memelord.databinding.ActivityAddMemeBinding;
import com.magarex.memelord.ui.base.BaseActivity;
import com.magarex.memelord.ui.main.MainActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AddMemeActivity extends BaseActivity<AddMemeViewModel, ActivityAddMemeBinding> implements InteractionListener {

    private static final String SELECT_AND_EDIT_FRAGMENT = "SelectAndEditFragment";
    private static final String POST_MEME_FRAGMENT = "PostMemeFragment";

    @Override
    protected int provideLayout() {
        return R.layout.activity_add_meme;
    }

    @Override
    protected Class<AddMemeViewModel> provideViewModelClass() {
        return AddMemeViewModel.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().addMemeToolbar);
        if (savedInstanceState == null)
            loadFragment(SelectAndEditFragment.newInstance(), SELECT_AND_EDIT_FRAGMENT);
    }

    @Override
    public void saveMeme(String memePath) {
        loadFragment(PostMemeFragment.newInstance(memePath), POST_MEME_FRAGMENT);
    }

    @Override
    public void postMeme(OperationStatus status) {
        if (status != null) {
            if (status.isInProgress())
                showUploadProgress(status);
            if (status.isErroneous())
                Toast.makeText(this, status.getExtra(), Toast.LENGTH_SHORT).show();
            else if (status.isComplete()) {
                getBinding().addMemeProgressBar.setVisibility(View.GONE);
                navigateToHome();
            }
        }
    }

    private void showUploadProgress(OperationStatus status) {
        getBinding().addMemeProgressBar.setVisibility(View.VISIBLE);
        getBinding().addMemeProgressBar.setProgress(status.getCompletionPercentage());
    }

    private void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getBinding().frameAddMeme.getId(), fragment, tag);
        if (tag.equals(POST_MEME_FRAGMENT))
            transaction.addToBackStack(tag);
        transaction.commit();
    }

    private void navigateToHome() {
        finish();
    }
}


