package com.magarex.memelord.ui.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.databinding.ProfileItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sahil on 24/11/18.
 **/
public class UserPostsAdapter extends RecyclerView.Adapter<UserPostsAdapter.UserPostsViewHolder> {

    private static final String TAG = "FeedAdapter";
    private List<Post> postList;
    private PostClickListener postClickListener;

    UserPostsAdapter(PostClickListener postClickListener) {
        this.postClickListener = postClickListener;
    }

    void addPostsToList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProfileItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.profile_item, parent, false);
        return new UserPostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserPostsViewHolder holder, int position) {
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

    class UserPostsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ProfileItemBinding mBinding;

        UserPostsViewHolder(ProfileItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mBinding = itemBinding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            postClickListener.onPostClick(postList.get(getAdapterPosition()));
        }
    }

    interface PostClickListener {
        void onPostClick(Post post);
    }
}
