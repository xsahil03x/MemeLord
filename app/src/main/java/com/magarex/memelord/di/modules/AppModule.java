package com.magarex.memelord.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.magarex.memelord.data.LeaderBoardRepository;
import com.magarex.memelord.data.MemeTemplateRepository;
import com.magarex.memelord.data.StorageRepository;
import com.magarex.memelord.data.UserRepository;
import com.magarex.memelord.data.local.MemeDao;
import com.magarex.memelord.data.local.MemeDatabase;
import com.magarex.memelord.data.remote.LeaderBoardRepositoryImpl;
import com.magarex.memelord.data.remote.MemeApi;
import com.magarex.memelord.data.remote.MemeTemplateRepositoryImpl;
import com.magarex.memelord.data.remote.StorageRepositoryImpl;
import com.magarex.memelord.data.remote.UserRepositoryImpl;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    static Resources provideResources(Application app) {
        return app.getResources();
    }

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(MoshiConverterFactory moshiCon, RxJava2CallAdapterFactory rxCallAdapter) {
        return new Retrofit.Builder()
                .baseUrl("https://api.imgflip.com/")
                .addConverterFactory(moshiCon)
                .addCallAdapterFactory(rxCallAdapter)
                .build();
    }

    @Provides
    @Singleton
    static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    static MoshiConverterFactory provideMoshiConverterFactory() {
        return MoshiConverterFactory.create();
    }

    @Provides
    @Singleton
    static MemeApi provideMemeApi(Retrofit retrofit) {
        return retrofit.create(MemeApi.class);
    }

    @Provides
    @Singleton
    static MemeDatabase provideMemeDatabase(Application app) {
        return Room.databaseBuilder(app, MemeDatabase.class, MemeDatabase.DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    static MemeDao provideMemeDao(MemeDatabase memeDatabase) {
        return memeDatabase.memeDao();
    }

    @Provides
    @Singleton
    static UserRepository provideUserRepository(DatabaseReference baseDatabaseReference) {
        return new UserRepositoryImpl(baseDatabaseReference);
    }

    @Provides
    @Singleton
    static MemeTemplateRepository provideMemeTemplateRepository(MemeDao memeDao, MemeApi memeApi) {
        return new MemeTemplateRepositoryImpl(memeDao, memeApi);
    }

    @Provides
    @Singleton
    static StorageRepository provideStorageRepository(DatabaseReference databaseReference, StorageReference storageReference) {
        return new StorageRepositoryImpl(databaseReference, storageReference);
    }

    @Provides
    @Singleton
    static LeaderBoardRepository provideLeaderBoardRepository(DatabaseReference databaseReference) {
        return new LeaderBoardRepositoryImpl(databaseReference);
    }

    @Provides
    @Singleton
    static DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    static StorageReference provideStorageReference() {
        return FirebaseStorage.getInstance().getReference();
    }

}