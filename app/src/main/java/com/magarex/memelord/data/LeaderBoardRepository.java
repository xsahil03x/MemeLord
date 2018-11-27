package com.magarex.memelord.data;

import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.models.User;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 24/11/18.
 **/
public interface LeaderBoardRepository {

    LiveData<List<Post>> getTopPostsList();

}
