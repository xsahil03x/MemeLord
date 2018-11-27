package com.magarex.memelord.ui.leaderboard;

import com.magarex.memelord.data.LeaderBoardRepository;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

/**
 * Created by sahil on 24/11/18.
 **/
public class LeaderBoardViewModel extends BaseViewModel {

    private LiveData<List<Post>> topPosts;

    @Inject
    LeaderBoardViewModel(LeaderBoardRepository leaderBoardRepository) {
        if (topPosts != null)
            return;
        topPosts = leaderBoardRepository.getTopPostsList();
    }

    LiveData<List<Post>> getTopPosts() {
        return topPosts;
    }
}
