package com.magarex.memelord.data.remote;

import android.util.Log;

import com.magarex.memelord.data.MemeTemplateRepository;
import com.magarex.memelord.data.local.MemeDao;
import com.magarex.memelord.data.models.Meme;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sahil on 18/11/18.
 **/
public class MemeTemplateRepositoryImpl implements MemeTemplateRepository {

    private MemeDao memeDao;
    private MemeApi memeApi;
    private CompositeDisposable disposables;
    private static final String TAG = "MemeTemplateRepositoryI";

    @Inject
    public MemeTemplateRepositoryImpl(MemeDao memeDao, MemeApi memeApi) {
        this.memeDao = memeDao;
        this.memeApi = memeApi;
        this.disposables = new CompositeDisposable();
    }

    @Override
    public void getMemeTemplates() {
        Disposable d = memeApi.getMemeTemplates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memeResponse -> {
                    Completable.fromAction(() -> memeDao.deleteAllMemes())
                            .subscribeOn(Schedulers.io());
                    memeDao.insertTemplatesToDb(memeResponse.getData().getMemes())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    Log.i(TAG, "onComplete: Data saved successfully into database");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i(TAG, "onError: " + e.getCause());
                                }
                            });
                });
        disposables.add(d);
    }

    @Override
    public LiveData<List<Meme>> getTemplatesFromDb() {
        final MutableLiveData<List<Meme>> memeTemplates = new MutableLiveData<>();
        Disposable d = memeDao.getAllMemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memeList -> {
                    if (memeList.isEmpty())
                        getMemeTemplates();
                    else memeTemplates.postValue(memeList);
                });
        disposables.add(d);
        return memeTemplates;
    }
}
