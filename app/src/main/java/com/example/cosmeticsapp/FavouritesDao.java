package com.example.cosmeticsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouritesDao {
    @Query("SELECT * FROM favourites")
    List<Favourites> getFavouriteProducts();
    @Insert
    void insert(Favourites favourites);
    @Delete
    void delete(Favourites favourites);
}
