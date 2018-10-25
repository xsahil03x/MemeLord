package com.magarex.memelord.data.models;

import com.squareup.moshi.Json;

import java.util.List;

public class MemeResponse {

    @Json(name = "memes")
    private List<Meme> memes = null;

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }

}
