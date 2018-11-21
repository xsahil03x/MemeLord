package com.magarex.memelord.data.local;


import com.magarex.memelord.data.models.Meme;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by sahil on 14/11/18.
 **/
@Dao
public interface MemeDao {

    @Query("SELECT * FROM memes")
    Flowable<List<Meme>> getAllMemes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTemplatesToDb(List<Meme> memeList);

    @Query("DELETE FROM memes")
    void deleteAllMemes();

//    @Query("DELETE FROM movies WHERE criteria = :criteria")
//    void deleteMoviesByCriteria(String criteria);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertMoviesToDb(List<Movie> movieList);
//
//    @Query("SELECT * FROM favourites")
//    LiveData<List<FavouriteMovie>> getFavouriteMovies();
//
//    @Query("SELECT COUNT(movie_id) FROM favourites WHERE movie_id = :movieId")
//    LiveData<Integer> isFavourite(int movieId);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertFavouriteMovie(FavouriteMovie favouriteMovie);
//
//    @Query("DELETE FROM favourites WHERE movie_id = :movieId")
//    void deleteFavouriteMovie(int movieId);

}
