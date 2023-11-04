package com.example.coursework.dao;


// /dao/PersonDao.java
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursework.models.Hike;

import java.util.List;

@Dao
public interface HikeDao {
    @Insert
    long insertHike(Hike hike);

    @Query("SELECT * FROM hikes ORDER BY hike_id")
    List<Hike> getAllHikes();
}


