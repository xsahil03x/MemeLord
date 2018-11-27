package com.magarex.memelord.ui.leaderboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.databinding.LeaderboardItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sahil on 28/11/18.
 **/
public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardViewHolder> {

    private List<Post> postList;
    private LeaderBoardClickListener clickListener;

    LeaderBoardAdapter(LeaderBoardClickListener clickListener) {
        this.clickListener = clickListener;
    }

    void addPostsToList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LeaderboardItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.leaderboard_item, parent, false);
        return new LeaderBoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardViewHolder holder, int position) {
        holder.mBinding.setPosition(String.valueOf(position + 2));
        holder.mBinding.setPost(postList.get(position));
    }

    @Override
    public int getItemCount() {
        if (postList == null) {
            return 0;
        } else {
            return postList.size();
        }
    }

    class LeaderBoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final LeaderboardItemBinding mBinding;

        LeaderBoardViewHolder(LeaderboardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mBinding = itemBinding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onProfilePicTap(postList.get(getAdapterPosition()).getUploaderId());
        }
    }

    interface LeaderBoardClickListener {
        void onProfilePicTap(String userId);
    }
}
