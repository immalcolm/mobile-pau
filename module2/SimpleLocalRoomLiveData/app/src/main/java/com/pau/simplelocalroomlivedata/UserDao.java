package com.pau.simplelocalroomlivedata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//@TODO READ
//this is a DAO(Data access object) interface
//you can write customised queries or use the defaults
//when using LiveData we can only use List
@Dao
public interface UserDao {

    //@TODO READ
    //When using LiveData we can only use List
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Delete
    void deleteUser(User user);

    //@TODO
    //any other sequel commands
}
