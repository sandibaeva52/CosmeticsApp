package com.example.cosmeticsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourites")

public class Favourites  {

    @PrimaryKey
    @NonNull
    public String id;

    public Favourites(){
}

    public Favourites(String id) {
    this.id=id;
    }
}
