package com.magarex.memelord.data.models;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.magarex.memelord.utils.GlideApp;
import com.squareup.moshi.Json;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "memes")
public class Meme {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "memeid")
    private int memeId;
    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "url")
    private String url;
    @Json(name = "width")
    private Integer width;
    @Json(name = "height")
    private Integer height;

    public Meme(int memeId, String id, String name, String url, Integer width, Integer height) {
        this.memeId = memeId;
        this.id = id;
        this.name = name;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    @Ignore
    public Meme(String id, String name, String url, Integer width, Integer height) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getMemeId() {
        return memeId;
    }

    public void setMemeId(int memeId) {
        this.memeId = memeId;
    }
}
