package com.magarex.memelord.utils;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.magarex.memelord.di.modules.GlideApp;

import androidx.databinding.BindingAdapter;

/**
 * Created by sahil on 16/11/18.
 **/
public class BindingAdapters {

    @BindingAdapter({"android:networkImage"})
    public static void loadNetworkImage(ImageView view, String imageUrl) {
        GlideApp.with(view.getContext())
                .load(imageUrl)
                .dontAnimate()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(12)))
                .into(view);
    }

}