package com.magarex.memelord.data;

import com.magarex.memelord.data.models.Meme;
import com.magarex.memelord.data.models.MemeResponse;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 18/11/18.
 **/
public interface MemeTemplateRepository {

    void getMemeTemplates();

    LiveData<List<Meme>> getTemplatesFromDb();

}
