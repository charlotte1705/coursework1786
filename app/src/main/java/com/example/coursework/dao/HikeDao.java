package com.example.coursework.dao;


// /dao/PersonDao.java
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursework.models.Hike;

import java.util.List;

@Dao
public interface HikeDao {
    @Insert
    long insertHike(Hike hike);

    @Query("SELECT * FROM hikes ORDER BY hike_id")
    List<Hike> getAllHikes();
    @Query("SELECT * FROM hikes WHERE hike_id = :id")
    Hike getHikeById(long id);

    @Delete
    void deleteHike(Hike hike);
    void deleteAllHikes(Hike hike);


    @Update
    void updateHike(Hike hike);




}


