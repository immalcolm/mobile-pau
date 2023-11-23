package com.pau.simpleapi;

//retrofit imports
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SimpleIpService {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("https://pokeapi.co/api/v2/type/4/")//endpoint depends on us. "." means the url is the same as the base url
    Call<IpInfo> getIpInfo();

    @GET
    Call<IpDetails> getRawJson(@Url String url);

    @GET
    Call<ResponseBody> getIpAddress(@Url String url);
}
