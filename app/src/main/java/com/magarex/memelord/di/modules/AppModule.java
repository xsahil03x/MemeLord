package com.magarex.memelord.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class AppModule {

//    @Provides
//    @Singleton
//    static SharedPreferences provideSharedPreferences(Application application) {
//        return PreferenceManager.getDefaultSharedPreferences(application);
//    }
//
//    @Provides
//    @Singleton
//    static Gson provideGson() {
//        return new GsonBuilder().create();
//    }
//
//    @Provides
//    @Singleton
//    static PreferenceRepository providePreferenceRepository(SharedPreferences sharedPreferences, Gson gson) {
//        return new PreferenceRepository(sharedPreferences, gson);
//    }
//
//    @Provides
//    @Singleton
//    static GsonConverterFactory provideGsonConverterFactory(Gson gson) {
//        return GsonConverterFactory.create(gson);
//    }
//
//    @Provides
//    @Singleton
//    static Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory) {
//        return new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl("http://go.udacity.com/")
//                .addConverterFactory(gsonConverterFactory)
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    static RecipeService provideRecipeApi(Retrofit retrofit) {
//        return retrofit.create(RecipeService.class);
//    }
//
//    @Provides
//    @Singleton
//    static RecipeRepository provideRecipeRepository(RecipeService recipeService) {
//        return new RecipeRepository(recipeService);
//    }
//
//    @Provides
//    @Singleton
//    static RecipeViewModel provideRecipeViewModel(RecipeRepository recipeRepository) {
//        return new RecipeViewModel(recipeRepository);
//    }

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
    static StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(StethoInterceptor stethoInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(stethoInterceptor)
                .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient okHttpClient, MoshiConverterFactory moshiCon, RxJava2CallAdapterFactory rxCallAdapter) {
        return new Retrofit.Builder()
                .baseUrl("")
                .client(okHttpClient)
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

//    @Provides
//    @Singleton
//    public static TMDbService provideTMDbService(Retrofit retrofit) {
//        return retrofit.create(TMDbService.class);
//    }

//    @Provides
//    @Singleton
//    public static AppDatabase provideAppDatabase(Application app) {
//        return Room.databaseBuilder(app, AppDatabase.class, AppDatabase.NAME).build();
//    }

}