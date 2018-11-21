package com.magarex.memelord.ui.addmeme;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magarex.memelord.R;
import com.magarex.memelord.data.models.Meme;
import com.magarex.memelord.databinding.MemeTemplateItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sahil on 18/11/18.
 **/
public class MemeTemplateAdapter extends RecyclerView.Adapter<MemeTemplateAdapter.MemeTemplateViewHolder> {

    private ConstraintSet constraintSet;
    private List<Meme> memeList;
    private MemeTemplateClickListener memeTemplateClickListener;
    private static final String TAG = "MemeTemplateAdapter";

    MemeTemplateAdapter(MemeTemplateClickListener memeTemplateClickListener) {
        this.memeTemplateClickListener = memeTemplateClickListener;
        this.constraintSet = new ConstraintSet();
    }

    void addMemeTemplateToList(List<Meme> memeList) {
        this.memeList = memeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemeTemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MemeTemplateItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.meme_template_item, parent, false);
        return new MemeTemplateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemeTemplateViewHolder holder, int position) {
        Meme meme = memeList.get(position);
        holder.mBinding.setMemeTemplate(meme);
        String ratio = String.format("%d:%d", meme.getWidth(), meme.getHeight());
        constraintSet.clone(holder.mBinding.layoutMemeTemplateItem);
        constraintSet.setDimensionRatio(holder.mBinding.ivMemeTemplate.getId(), ratio);
        Log.i(TAG, "onBindViewHolder: " + ratio);
        constraintSet.applyTo(holder.mBinding.layoutMemeTemplateItem);
    }

    @Override
    public int getItemCount() {
        if (memeList == null) {
            return 0;
        } else {
            return memeList.size();
        }
    }

    class MemeTemplateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final MemeTemplateItemBinding mBinding;

        MemeTemplateViewHolder(MemeTemplateItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mBinding = itemBinding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            memeTemplateClickListener.onClick(memeList.get(getAdapterPosition()));
        }
    }

    interface MemeTemplateClickListener {
        void onClick(Meme meme);
    }
}
