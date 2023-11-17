package com.pau.simplerecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);

            //decide what content that needs to be updated
            textView = v.findViewById(R.id.textViewItem);
        }
    }

    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    //inflating the UI with the layout file
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    //UI  binding mechaism when the data is done
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

/*
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            //textView = v;
            textView = v.findViewById(R.id.textViewItem);

        }
    }

    // Provide a suitable constructor
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

 */
