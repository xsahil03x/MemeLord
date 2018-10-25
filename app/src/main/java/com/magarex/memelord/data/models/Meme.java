package com.magarex.memelord.data.models;

import com.squareup.moshi.Json;

public class Meme {

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
}
