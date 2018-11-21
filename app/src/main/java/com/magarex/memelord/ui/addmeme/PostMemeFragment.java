package com.magarex.memelord.ui.addmeme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.ablanco.zoomy.Zoomy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.models.User;
import com.magarex.memelord.data.remote.OperationStatus;
import com.magarex.memelord.databinding.FragmentPostMemeBinding;
import com.magarex.memelord.di.modules.GlideApp;
import com.magarex.memelord.ui.base.BaseFragment;
import com.magarex.memelord.utils.AppUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public class PostMemeFragment extends BaseFragment<AddMemeViewModel, FragmentPostMemeBinding> {

    private static final String TAG = "PostMemeFragment";
    private static final String SELECTED_IMAGE = "SelectedImage";
    private InteractionListener interactionListener;
    private User currentUser;
    private Uri imageUri;
    private String selectedImagePath;

    @Override
    protected int provideLayout() {
        return R.layout.fragment_post_meme;
    }

    @Override
    protected Class<AddMemeViewModel> provideViewModelClass() {
        return AddMemeViewModel.class;
    }

    static PostMemeFragment newInstance(String imagePath) {
        PostMemeFragment fragment = new PostMemeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SELECTED_IMAGE, imagePath);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddMemeActivity) {
            interactionListener = (InteractionListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(SELECTED_IMAGE))
            selectedImagePath = getArguments().getString(SELECTED_IMAGE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareViews();
        addZoomy();
    }

    private void prepareViews() {
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getViewModel().loadUserData(id);
        getViewModel().getUserData().observe(this, user -> {
            currentUser = user;
            getDataBinding().setUser(currentUser);
        });
        showFinalMeme();
    }

    private void showFinalMeme() {
        imageUri = Uri.fromFile(AppUtils.compressFile(new File(selectedImagePath)));
        if (imageUri != null) {
            GlideApp.with(getContext())
                    .load(imageUri)
                    .dontAnimate()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                    .into(getDataBinding().ivFinalMeme);
        }
    }

    private void addZoomy() {
        new Zoomy.Builder(getActivity())
                .target(getDataBinding().ivFinalMeme)
                .interpolator(new OvershootInterpolator())
                .tapListener(v -> Log.i(TAG, "prepareViews: TAP"))
                .longPressListener(v -> Log.i(TAG, "prepareViews: LongPress"))
                .doubleTapListener(v -> Log.i(TAG, "prepareViews: Doubletap"))
                .register();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_post_meme_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_post:
                uploadMeme();
                break;
        }
        return true;
    }

    private void uploadMeme() {
        if (!TextUtils.isEmpty(getDataBinding().edMemeCaption.getText().toString().trim())) {
            Post post = new Post();
            post.setUploaderPic(currentUser.getProfilePicURL());
            post.setUploader(currentUser.getDisplayName());
            post.setCaption(getDataBinding().edMemeCaption.getText().toString().trim());
            post.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            getViewModel().uploadPostToFirebase(imageUri, post)
                    .observe(this, status -> interactionListener.postMeme(status));
        } else
            AppUtils.showBasicSnackBar(getView(), "Oops Empty caption...");
    }
}
