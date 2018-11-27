package com.magarex.memelord.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sahil on 23/11/18.
 **/
public class FeedSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int spanCount;
    private final int spacing;
    private final boolean includeEdge;

    public FeedSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position

        if (includeEdge) {
            if (position < spanCount && position != 0) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else if (position >= spanCount) {
            outRect.top = spacing; // item top
        }

    }
}
