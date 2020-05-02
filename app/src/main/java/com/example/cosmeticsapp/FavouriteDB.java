package com.example.cosmeticsapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Favourites.class}, version = 1, exportSchema = false)
public abstract class FavouriteDB extends RoomDatabase {
    public abstract FavouritesDao favouritesDao();
}
