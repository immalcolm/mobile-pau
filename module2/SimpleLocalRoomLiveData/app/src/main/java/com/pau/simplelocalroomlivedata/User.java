package com.pau.simplelocalroomlivedata;

import androidx.room.PrimaryKey;
import androidx.room.Entity;
import androidx.room.ColumnInfo;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    private String name;
    // Other fields and columns

    //paramterized constructor that just takes in a name
    public User(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){this.name = name;}
}