package com.magarex.memelord.data.remote;

import com.magarex.memelord.data.models.MemeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sahil on 14/11/18.
 **/
public interface MemeApi {
    @GET("get_memes")
    Call<MemeResponse> getMemeTemplates();
}
