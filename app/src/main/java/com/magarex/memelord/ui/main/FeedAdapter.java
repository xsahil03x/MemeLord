package com.magarex.memelord.ui.main;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.ablanco.zoomy.Zoomy;
import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.databinding.HomeItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sahil on 22/11/18.
 **/
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private ConstraintSet constraintSet;
    private static final String TAG = "FeedAdapter";
    private List<Post> postList;
    private FeedClickListener feedClickListener;
    private Activity mActivity;

    FeedAdapter(FeedClickListener feedClickListener, Activity mActivity) {
        this.feedClickListener = feedClickListener;
        this.mActivity = mActivity;
        this.constraintSet = new ConstraintSet();
    }

    void addPostsToList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.home_item, parent, false);
        return new FeedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedViewHolder holder, int position) {
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

    class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final HomeItemBinding mBinding;

        FeedViewHolder(HomeItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mBinding = itemBinding;
            mBinding.getRoot().setOnClickListener(this);
            new Zoomy.Builder(mActivity)
                    .target(mBinding.ivMeme)
                    .interpolator(new OvershootInterpolator())
                    .tapListener(v -> Log.i(TAG, "prepareViews: TAP"))
                    .longPressListener(v -> Log.i(TAG, "prepareViews: LongPress"))
                    .doubleTapListener(v -> {
                        try {
                            feedClickListener.onPostDoubleTap(mBinding.ivLit, mBinding.ivBigLit, postList.get(getAdapterPosition()));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    })
                    .register();
        }

        @Override
        public void onClick(View v) {
            feedClickListener.onProfilePicTap(postList.get(getAdapterPosition()).getUploader());
        }
    }

    interface FeedClickListener {

        void onPostDoubleTap(ImageView litButton, ImageView bigLitButton, Post post) throws InterruptedException;

        void onProfilePicTap(String userId);
    }
}
