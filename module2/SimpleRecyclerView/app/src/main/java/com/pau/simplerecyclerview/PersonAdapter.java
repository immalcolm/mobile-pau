package com.pau.simplerecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private List<Person> personList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView detailTextView;

        public MyViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.nameTextView);
            detailTextView = v.findViewById(R.id.detailTextView);
        }
    }

    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new MyViewHolder(v);    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.nameTextView.setText(person.getName());
        holder.detailTextView.setText(person.getDetail());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}