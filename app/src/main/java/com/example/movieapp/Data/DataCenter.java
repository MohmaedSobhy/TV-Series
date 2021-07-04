package com.example.movieapp.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {tvshow.class},version = 1)
public abstract class DataCenter extends RoomDatabase {

    public abstract SqlCodeDao getFunction();

    public static  DataCenter instance;

    public static synchronized DataCenter getInstance(Context context)
    {
        if(instance==null)
        {
           instance= Room.databaseBuilder(context,DataCenter.class,"db_center")
                   .fallbackToDestructiveMigration()
                   .allowMainThreadQueries()
                   .build();
        }
        return instance;
    }


}
