package com.pau.simpleapi;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//@TODO READ: https://square.github.io/retrofit/
//This demo details the usage of Retrofit for various API use cases

public class MainActivity extends AppCompatActivity {

    final String TAG = "Retrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview_posts = findViewById(R.id.textview_posts);

        TextView textview_ip_details = findViewById(R.id.textview_ip_details);
        textview_ip_details.setMovementMethod(new ScrollingMovementMethod());
        textview_ip_details.setScrollbarFadingEnabled(false);
        textview_ip_details.setVerticalScrollBarEnabled(true);

        Button button_get_ip = findViewById(R.id.button_ip_details);
        button_get_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIpAddress(textview_ip_details);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SimpleApiService service = retrofit.create(SimpleApiService.class);

        //pre-build a command -> to retrieve all posts in the API (getPosts)
        Call<List<Post>> listCall = service.getPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d("API","Code " + response.code());
                    return;
                }

                //automatically convert all json data into Post object
                //dump the post object into a list
                List<Post> posts = response.body();

                assert posts != null;
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserID() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Body: " + post.getText() + "\n\n";

                    Log.d("API",content);
                    textview_posts.append("\n" + content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("API",t.getMessage());
            }
        });
    }

    //Using a Retrofit with raw json
    //function converts the json value to an object mapping using Gson
    //IpAddress attributes must map to the JSON data exactly
    private void getIpAddress(TextView tv){
        String ipAddress = "";
        //Let's work with rawJson and see what happens
        Retrofit retrofitIp = new Retrofit.Builder()
                .baseUrl("https://ipapi.co/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SimpleIpService ipService = retrofitIp.create(SimpleIpService.class);

        //make a call to the API
        ipService.getIpAddress("https://api64.ipify.org?format=json").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        String rawJson = response.body().string();
                        //convert json
                        IpAddress ip = gson.fromJson(rawJson, IpAddress.class);
                        Log.d(TAG, "ip details:\n" + ip.toString());
                        getIpDetails(tv, ip.getIpAddress());
                        //call another api to get full details

                    } catch (IOException e) {
                        e.printStackTrace(); // Handle the exception
                    }
                } else {
                    String rawJson = String.valueOf(response.body());

                    Log.d(TAG, "raw" + rawJson);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"Error" + t.getMessage().toString());
            }

        });
    }

    private void getIpDetails(TextView v, String ipAddress){
        //build a retrofit with GSON(a JSON convertor lib)
        Retrofit retrofitIp = new Retrofit.Builder()
                .baseUrl("http://ip-api.com/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Tap onto the service to get all the possible API calls
        SimpleIpService ipService = retrofitIp.create(SimpleIpService.class);

        //make a call to the API
        ipService.getRawJson("http://ip-api.com/json/" + ipAddress).enqueue(new Callback<IpDetails>() {
            @Override
            public void onResponse(Call<IpDetails> call, Response<IpDetails> response) {
                if (response.isSuccessful()) {
                    //Method 1
                    // Process the raw JSON as needed
                    // if we use the raw JSON way with GSON
                    // Map to the IpDetails Class
                        /*
                        String rawJson = response.body().string();
                        Gson gson = new Gson();
                        //convert json
                        IpDetails ip = gson.fromJson(rawJson, IpDetails.class);
                        Log.d(TAG, ip.toString());

                         */
                    //@TODO upgrade to UIThread
                    //Method 2: Tapping on the GSON converterFactory
                    IpDetails ipDetails = response.body();
                    Log.d(TAG, "ip " + ipDetails.toString());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // Do stuff…
                            v.setText(ipDetails.toString());
                        }
                    });


                } else {
                    String rawJson = String.valueOf(response.body());
                    Log.d(TAG, "raw" + rawJson);
                }
            }

            @Override
            public void onFailure(Call<IpDetails> call, Throwable t) {
                Log.d(TAG,"Error" + t.getMessage().toString());
            }

        });
    }

    public void createNewUser(){
        /*
        User newUser = new User("JohnDoe", "john@example.com");
        Call<User> call = service.createUser(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // User created successfully
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle failure
            }
        });*/

    }
}