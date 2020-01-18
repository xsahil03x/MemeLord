package com.magarex.memelord.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.magarex.memelord.data.models.Meme;

/**
 * Created by sahil on 14/11/18.
 **/
@Database(entities = {Meme.class}, version = 1, exportSchema = false)
public abstract class MemeDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "MemeDatabase";

    public abstract MemeDao memeDao();
}