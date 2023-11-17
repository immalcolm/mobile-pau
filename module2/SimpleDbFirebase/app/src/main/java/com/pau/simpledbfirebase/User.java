package com.pau.simpledbfirebase;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

//@TODO Read
//Think about what model you want
//what attributes
//what functions to use to define your model better

//Why User.class
//Type Safety >> make automatically to the Java Object
//Convenience >> abstract the manual parsing of DataSnapshot
//OO Approach >> work with complex data in a more structure way

//we rely on User.class to map DataSnapshot objects to the User class
//User user = userSnapshot.getValue(User.class);

//https://firebase.google.com/docs/reference/kotlin/com/google/firebase/database/IgnoreExtraProperties

//Properties that don't map to class fields are ignored when serializing
// to a class annotated with this annotation.
@IgnoreExtraProperties
public class User {

    //@TODO change attributes to public for serialization
    //@TODO or define getters and settiers for each field
    private String name;
    private String email;

    public User(){

    }
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    //easy and quick Mapping method
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        //define our mapping structure
        result.put("name", name);
        result.put("email", email);

        return result;
    }

    //getters and setters
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}
}