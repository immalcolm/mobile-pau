package com.pau.simplelocalroomlivedata;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//take note of how we reference the name
@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

