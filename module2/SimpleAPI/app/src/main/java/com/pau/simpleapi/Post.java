package com.pau.simpleapi;

import com.google.gson.annotations.SerializedName;

public class Post {

    /*
    JSON is based on key:value pairs
    Keys will always be a string
    {
    "userID" : 1,
    "id": 4930,
    "title: "some title"
    },
    {
    "userID" : 2,
    "id": 4930,
    "title: "some title"
    },

     */
    private int userID;

    private int id;
    private String title;

    @SerializedName("body")
    private String text;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}