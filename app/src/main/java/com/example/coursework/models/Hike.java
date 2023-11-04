package com.example.coursework.models;
// /models/Hike.java
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "hikes")
public class Hike {
    @PrimaryKey(autoGenerate = true)
    public long hike_id;
    public String hike_name;
    public String hike_location;
    public String hike_date;
    public String hike_parking;
    public String hike_length;
    public String hike_level;
    public  String hike_description;

}