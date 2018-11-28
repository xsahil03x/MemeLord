package com.magarex.memelord.ui.main;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.databinding.FragmentHomeBinding;
import com.magarex.memelord.ui.base.BaseFragment;
import com.magarex.memelord.utils.FeedSpacingItemDecoration;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.magarex.memelord.utils.AppUtils.dpToPx;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> implements FeedAdapter.FeedClickListener {

    private FeedAdapter feedAdapter;
    private static final String TAG = "HomeFragment";

    @Override
    protected int provideLayout() {
        return R.layout.fragment_home;
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected Class<HomeViewModel> provideViewModelClass() {
        return HomeViewModel.class;
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
        feedAdapter = new FeedAdapter(this, getActivity());
        RecyclerView rvPosts = getDataBinding().rvPosts;
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvPosts.addItemDecoration(new FeedSpacingItemDecoration(
                1, dpToPx(16), true));
        rvPosts.setItemAnimator(new DefaultItemAnimator());
        rvPosts.setAdapter(feedAdapter);
        getViewModel().getPostList().observe(this, postList -> {
            feedAdapter.addPostsToList(postList);
            getDataBinding().pbPosts.setVisibility(View.GONE);
        });
    }

    @Override
    public void onLitButtonPress(Post post) throws InterruptedException {
        getViewModel().incrementUpvoteCount(post.getPostId()).observe(this, status -> {
            if (status.isComplete()) {
                Toast.makeText(getContext(), "Upvoted", Toast.LENGTH_SHORT).show();
            } else if (status.isErroneous()) {
                Toast.makeText(getContext(), status.getExtra(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPostDoubleTap(ImageView litButton, ImageView bigLitButton, Post post) {
        Log.d(TAG, "onPostDoubleTap: " + post.getPostId());
    }


    @Override
    public void onProfilePicTap(String userId) {
        Log.i(TAG, "onProfilePicTap: " + userId);
    }
}
