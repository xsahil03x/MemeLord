package com.magarex.memelord.data.remote;

import com.magarex.memelord.data.MainRepository;
import com.magarex.memelord.data.models.MemeResponse;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 15/11/18.
 **/
public class MainRepositoryImpl implements MainRepository {
    @Override
    public LiveData<MemeResponse> getMemes() {
        return null;
    }
}
