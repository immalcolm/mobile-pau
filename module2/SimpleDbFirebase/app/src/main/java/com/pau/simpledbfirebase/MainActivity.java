package com.pau.simpledbfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

//@TODO ensure that the google-services.json is the latest
//must have a URL link
//download from project settings
//check for errors
//@TODO consider running executor service

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private String TAG = "Pau";
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //auto initialize
        //FirebaseApp.initializeApp(this);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();

        //get the root reference
        myRef = database.getReference();
        //get a node reference
        //DatabaseReference myRef = database.getReference();

        //myRef.setValue("Hello, test!");

        //setup some listeners
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        //child heirachy
        myRef.child("pau").child("message").setValue("Hello, Pau!");

        //check for errors
        myRef.child("pauv2").child("message").setValue("Hello, Pau!", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    System.out.println("Data could not be saved. " + error.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });

        //insert as json
        // Creating a new user object
        createNewUser(new User("Pau","pau@pau.com"));


        readUsers();
    }

    public void createNewUser(User user){
        // Converting object to a Map
        Map<String, Object> userValues = user.toMap();

        // Generating a unique key(push) for the user
        String userId = myRef.child("users").push().getKey();

        // Inserting the user
        myRef.child("users").child(userId).setValue(userValues);

    }

    //The addOnCompleteListener
    //add a listener once the task operation is completed
    //used together with the Task obj
    //register the callback
    //other listeners: addOnFailureListener | addOnSuccessListener
    public void readUsers(){
        myRef.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());

                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    //DataSnapshot is a generic representation of the data
                    DataSnapshot dataSnapshot = task.getResult();

                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        //.class here is to indicate which class type Firebase should deserialize to
                        //convert to a usable Java object and map the values to the class
                        //now 'user' is a User object populated with data from Firebase

                        User user = userSnapshot.getValue(User.class);
                        Log.d("user", user.getEmail() + user.getName() + userSnapshot.getKey());

                        //@TODO update UI or us recyclers
                    }
                }
            }
        });
    }

    public void deleteUser(String userId){
        //@TODO find by name and remove
        myRef.child("users").child(userId).removeValue();
    }


}