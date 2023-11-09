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

    public Hike(long hike_id, String hike_name, String hike_location,
                String hike_date, String hike_parking, String hike_length, String hike_level, String hike_description) {
        this.hike_id = hike_id;
        this.hike_name = hike_name;
        this.hike_location = hike_location;
        this.hike_date = hike_date;
        this.hike_parking = hike_parking;
        this.hike_length = hike_length;
        this.hike_level = hike_level;
        this.hike_description = hike_description;
    }

    public Hike(){}
//    public String getHikeName() {
//        return hike_name;
//    }
//
//    public void setHikeName(String hikeName) {
//        this.hike_name = hikeName;
//    }
//
//    public String getHikeLocation() {
//        return hike_location;
//    }
//
//    public void setHikeLocation(String hikeLocation) {
//        this.hike_location = hikeLocation;
//    }
//    public String getHikeDate() {
//        return hike_date;
//    }
//
//    public void setHikeDate(String hikeDate) {
//        this.hike_date = hikeDate;
//    }
//
//    public String getHikeParking() {
//        return hike_parking;
//    }
//
//    public void setHikeParking(String hikeParking) {
//        this.hike_parking = hikeParking;
//    }
//
//    public String getHikeLength() {
//        return hike_length;
//    }
//
//    public void setHikeLength(String hikeLength) {
//        this.hike_length = hikeLength;
//    }
//
//    public String getHikeLevel() {
//        return hike_level;
//    }
//
//    public void setHikeLevel(String hikeLevel) {
//        this.hike_level = hikeLevel;
//    }
//
//    public String getHikeDescription() {
//        return hike_description;
//    }
//
//    public void setHikeDescription(String hikeDescription) {
//        this.hike_description = hikeDescription;
//    }

}

