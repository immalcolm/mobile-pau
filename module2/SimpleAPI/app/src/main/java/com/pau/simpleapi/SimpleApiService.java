package com.pau.simpleapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SimpleApiService {

    //you would define a base URL in start
    //https://yourAPIurl.com/posts
    @GET("posts")
    Call<List<Post>> getPosts();

    //@POST("users/new")
    //Call<User> createUser(@Body User user);
}