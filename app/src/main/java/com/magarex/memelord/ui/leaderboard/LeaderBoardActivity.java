package com.magarex.memelord.ui.leaderboard;

import android.os.Bundle;

import com.magarex.memelord.R;
import com.magarex.memelord.databinding.ActivityLeaderBoardBinding;
import com.magarex.memelord.ui.base.BaseActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderBoardActivity extends BaseActivity<LeaderBoardViewModel, ActivityLeaderBoardBinding> implements LeaderBoardAdapter.LeaderBoardClickListener {

    private LeaderBoardAdapter leaderBoardAdapter;

    public LeaderBoardActivity() {
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_leader_board;
    }

    @Override
    protected Class<LeaderBoardViewModel> provideViewModelClass() {
        return LeaderBoardViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        getViewModel().getTopPosts().observe(this, postList -> {
            getBinding().setPost(postList.get(0));
            leaderBoardAdapter.addPostsToList(postList.subList(1, postList.size()));
        });
    }

    private void initViews() {
        leaderBoardAdapter = new LeaderBoardAdapter(this);
        RecyclerView rvTopUsers = getBinding().rvTopUsers;
        rvTopUsers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvTopUsers.setItemAnimator(new DefaultItemAnimator());
        rvTopUsers.setAdapter(leaderBoardAdapter);
    }

    @Override
    public void onProfilePicTap(String userId) {
        // TODO : Open Post
    }
}
