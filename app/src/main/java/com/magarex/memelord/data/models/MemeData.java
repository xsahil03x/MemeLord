package com.magarex.memelord.data.models;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by sahil on 15/11/18.
 **/
public class MemeData {
    @Json(name = "memes")
    private List<Meme> memes = null;

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }
}

