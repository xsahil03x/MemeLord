package com.magarex.memelord.data;

import com.magarex.memelord.data.models.MemeResponse;
import com.magarex.memelord.data.models.User;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 14/11/18.
 **/
public interface MainRepository {

    LiveData<MemeResponse> getMemes();

}
