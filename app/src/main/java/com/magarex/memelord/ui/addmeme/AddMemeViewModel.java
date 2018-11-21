package com.magarex.memelord.ui.addmeme;

import android.graphics.Bitmap;
import android.net.Uri;

import com.magarex.memelord.data.MemeTemplateRepository;
import com.magarex.memelord.data.StorageRepository;
import com.magarex.memelord.data.UserRepository;
import com.magarex.memelord.data.models.Meme;
import com.magarex.memelord.data.models.MemeResponse;
import com.magarex.memelord.data.models.Post;
import com.magarex.memelord.data.models.User;
import com.magarex.memelord.data.remote.OperationStatus;
import com.magarex.memelord.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by sahil on 18/11/18.
 **/
public class AddMemeViewModel extends BaseViewModel {

    private StorageRepository storageRepository;
    private UserRepository userRepository;
    private LiveData<List<Meme>> memeResponseLiveData;
    private LiveData<User> userData;
    private final MutableLiveData<Meme> selectedMemeTemplate;

    @Inject
    AddMemeViewModel(UserRepository userRepository, MemeTemplateRepository memeTemplateRepository, StorageRepository storageRepository) {
        this.userRepository = userRepository;
        this.storageRepository = storageRepository;
        this.selectedMemeTemplate = new MutableLiveData<>();
        if (memeResponseLiveData != null) {
            return;
        }
        memeResponseLiveData = memeTemplateRepository.getTemplatesFromDb();
    }

    LiveData<OperationStatus> uploadPostToFirebase(Uri imageUri, Post post) {
        return storageRepository.uploadPost(imageUri, post);
    }

    void loadUserData(String userId) {
        userData = userRepository.getUserInfo(userId);
    }

    LiveData<User> getUserData() {
        return userData;
    }

    LiveData<List<Meme>> getMemeResponseLiveData() {
        return memeResponseLiveData;
    }

    void setSelectedMemeTemplate(Meme meme) {
        this.selectedMemeTemplate.postValue(meme);
    }

    MutableLiveData<Meme> getSelectedMemeTemplate() {
        return selectedMemeTemplate;
    }

}
