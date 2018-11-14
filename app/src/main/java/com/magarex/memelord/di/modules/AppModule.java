package com.magarex.memelord.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.magarex.memelord.data.local.MemeDatabase;
import com.magarex.memelord.data.remote.MemeApi;
import com.magarex.memelord.data.remote.UserRepositoryImpl;
import com.magarex.memelord.ui.login.LoginViewModel;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.room.Room;
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
                .baseUrl("https://api.imgflip.com/")
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
    static UserRepositoryImpl provideUserRepository(DatabaseReference baseDatabaseReference) {
        return new UserRepositoryImpl(baseDatabaseReference);
    }

    @Provides
    @Singleton
    static DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

}