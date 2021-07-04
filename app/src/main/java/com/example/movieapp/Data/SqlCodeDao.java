package com.example.movieapp.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SqlCodeDao {

    @Insert
    void InsertTv(tvshow t);

    @Delete
    void DeleteItme(tvshow t);

    @Query("SELECT * FROM TVshow")
    List<tvshow> getAll();




}
