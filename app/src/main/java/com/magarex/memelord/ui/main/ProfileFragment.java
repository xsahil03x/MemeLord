package com.magarex.memelord.ui.main;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.models.User;
import com.magarex.memelord.databinding.FragmentProfileBinding;
import com.magarex.memelord.ui.base.BaseFragment;
import com.magarex.memelord.utils.GridSpacingItemDecoration;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.magarex.memelord.utils.AppUtils.dpToPx;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> implements UserPostsAdapter.PostClickListener {

    private User currentUser;

    @Override
    protected int provideLayout() {
        return R.layout.fragment_profile;
    }

    public static ProfileFragment getInstance() {
        return new ProfileFragment();
    }

    @Override
    protected Class<ProfileViewModel> provideViewModelClass() {
        return ProfileViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareViews();

    }

    private void prepareViews() {
        UserPostsAdapter postsAdapter = new UserPostsAdapter(this);
        RecyclerView rvPosts = getDataBinding().rvPosts;
        rvPosts.setLayoutManager(new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false));
        rvPosts.addItemDecoration(new GridSpacingItemDecoration(
                3, dpToPx(1), true));
        rvPosts.setItemAnimator(new DefaultItemAnimator());
        rvPosts.setAdapter(postsAdapter);
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getViewModel().loadUserData(id).observe(this, getDataBinding()::setUser);
        getViewModel().getAllPostsOfUser(id).observe(this, postList -> {
            if (!postList.isEmpty()) {
                getDataBinding().groupRvPosts.setVisibility(View.VISIBLE);
                postsAdapter.addPostsToList(postList);
            } else getDataBinding().tvError.setVisibility(View.VISIBLE);
        });
    }


    @Override
    public void onPostClick(Post post) {

    }
}
