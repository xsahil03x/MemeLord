package com.magarex.memelord.services;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.SimpleJobService;
import com.magarex.memelord.data.MemeTemplateRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by sahil on 20/11/18.
 **/
public class FetchMemeTemplatesJob extends SimpleJobService {

    @Inject
    MemeTemplateRepository repository;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public int onRunJob(JobParameters job) {
        repository.getMemeTemplates();
        return RESULT_SUCCESS;
    }
}