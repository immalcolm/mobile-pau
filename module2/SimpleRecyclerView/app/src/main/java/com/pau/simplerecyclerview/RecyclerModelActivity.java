package com.pau.simplerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerModelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set to layout file
        setContentView(R.layout.activity_recycler_model);

        //set to recycler view inside layout file
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        //define layout mechanism
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Create a list of persons
        // let the adapter settle the list and how they are displayed
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John Doe", "Details about John"));
        personList.add(new Person("Jane Smith", "Details about Jane"));
        personList.add(new Person("Jona Smith", "Details about Jane"));
        personList.add(new Person("Pau Smith", "Details about Jane"));
        personList.add(new Person("Roo Smith", "Details about Jane"));
        personList.add(new Person("Stan Smith", "Details about Jane"));
        personList.add(new Person("Zoe Smith", "Details about Jane"));
        // ... Add more items

        mAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(mAdapter);
    }



}