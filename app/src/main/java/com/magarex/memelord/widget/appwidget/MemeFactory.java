package com.magarex.memelord.widget.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.magarex.memelord.R;
import com.magarex.memelord.data.StorageRepository;
import com.magarex.memelord.data.models.Post;

import java.util.List;

/**
 * Created by sahil on 28/11/18.
 **/
public class MemeFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Post> postList;
    private static final String TAG = "MemeFactory";
    private StorageRepository storageRepository;

    MemeFactory(Context mContext, StorageRepository storageRepository) {
        this.mContext = mContext;
        this.storageRepository = storageRepository;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (postList != null)
            return;
        try {
            postList = storageRepository.getPostsForWidget(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return (postList == null ? 0 : postList.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Post post = postList.get(position);
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout
                .widget_item);
        try {
            Bitmap bitmap1 = Glide.with(mContext)
                    .asBitmap()
                    .load(post.getDownloadURL())
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            Bitmap bitmap2 = Glide.with(mContext)
                    .asBitmap()
                    .load(post.getUploaderPic())
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();

            rv.setImageViewBitmap(R.id.ivWidgetMeme, bitmap1);
            rv.setImageViewBitmap(R.id.ivWidgetMemeUploader, bitmap2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
