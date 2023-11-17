package com.pau.simplerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button button_recycler_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Use this setting to improve performance
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        //encapsulate the textview with linear layout
        // 3layouts to play with
        //Linear Layout, Grid Layout, Staggered Layout
        //layoutManager = new LinearLayoutManager(this);

        //3 rows and vertical orientation
        //layoutManager = new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL);
        layoutManager = new GridLayoutManager(this,3 );
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        String[] myDataset = {"Pau Pau", "Grab", "Deliveroo","Pau Pau",
                "Grab", "Chicken nugget fried rice","Pau Pau", "Grab", "Deliveroo",
                "Grab", "Deliveroo","Pau Pau", "Fried fish nuggets and chips", "Deliveroo",
                "Grab", "Laksa with lots of hum","Pau Pau", "Grab", "Deliveroo"

        };
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        button_recycler_model = (Button) findViewById(R.id.button_recycler_model);
        button_recycler_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerModelActivity.class);
                startActivity(intent);
            }
        });

    }
}