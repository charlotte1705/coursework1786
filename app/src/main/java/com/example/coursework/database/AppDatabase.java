package com.example.coursework.database;

// /database/AppDatabase.java
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.coursework.dao.HikeDao;
import com.example.coursework.models.Hike;

@Database(entities = {Hike.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HikeDao hikeDao();
}