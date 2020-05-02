package com.example.cosmeticsapp;

import android.app.Application;

import androidx.room.Room;

public class DatabaseBuilder extends Application {
    public static DatabaseBuilder instance;
    private FavouriteDB favouriteDB;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        favouriteDB= Room.databaseBuilder(this,FavouriteDB.class,"favouritesDb").fallbackToDestructiveMigration().allowMainThreadQueries().build();


    }

    public static DatabaseBuilder getInstance(){
        return instance;
    }
    public FavouriteDB getFavouriteDB(){
        return favouriteDB;
    }
}
