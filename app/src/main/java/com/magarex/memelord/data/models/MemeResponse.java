package com.magarex.memelord.data.models;

import com.squareup.moshi.Json;

import java.util.List;

public class MemeResponse {

    @Json(name = "success")
    private Boolean success;
    @Json(name = "data")
    private MemeData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public MemeData getData() {
        return data;
    }

    public void setData(MemeData data) {
        this.data = data;
    }
}
