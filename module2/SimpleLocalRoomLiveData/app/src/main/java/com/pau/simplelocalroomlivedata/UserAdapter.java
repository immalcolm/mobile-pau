package com.pau.simplelocalroomlivedata;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users = new ArrayList<>();
    private UserDao userDao;

    private View recyclerView;
    private View emptyView;

    //constructor that takes in the UserDao meaning we have access to the database
    public UserAdapter(UserDao noteDao, View recyclerView, View emptyView) {

        this.userDao = noteDao;
        this.recyclerView = recyclerView;
        this.emptyView = emptyView;
        showEmptyView();
    }

    private void showEmptyView(){
        if(users.size() == 0){
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

    }

    //
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User currentUser = users.get(position);

        //our own UI to properties binding
        holder.bind(currentUser);

        //bind other data to the view
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the note
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.deleteUser(currentUser);
                    }
                }).start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        showEmptyView();
        notifyDataSetChanged();//@TODO important to have this
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        // UI elements from user_item layout
        TextView userNameTextView;
        Button buttonDelete;//@TODO
        //any other views

        UserViewHolder(View itemView) {
            super(itemView);
            // initialize UI elements
            userNameTextView = itemView.findViewById(R.id.text_view_username);
            //@TODO
            buttonDelete = itemView.findViewById(R.id.button_delete);
        }

        void bind(User user) {
            // Bind user data to UI elements
            userNameTextView.setText(user.getName() + "(" + user.id+ ")");

        }
    }
}
