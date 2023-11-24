package com.pau.simplelocalroomlivedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//@TODO
//The Room database is stored in the internal storage of the app,
//specifically in the /data/data/<your-package-name>/databases directory.

//runOnUiThread: This method is used to perform a task on the main UI thread from any other thread.
// It's typically used to update the UI from a background thread,
// because only the main thread can update the UI.
// If the current thread is the UI thread, then the action is executed immediately.
// If the current thread is not the UI thread, the action is posted to the event queue of the UI thread.

//Executor: This is an interface used to manage and control thread execution in concurrent Java applications.
// It provides a way of decoupling task submission from the mechanics of how each task will be run,
// including details of thread use, scheduling, etc. An Executor is normally used to run tasks in a background thread.
//In summary, runOnUiThread is used to run tasks on the main UI thread, while Executor is used to effectively manage background tasks.
public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private UserDao userDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get some UI
        EditText edittext_adduser = (EditText) findViewById(R.id.edittext_user);
        Button button_adduser = (Button)findViewById(R.id.button_adduser);
        TextView textview_empty_message = (TextView)findViewById(R.id.textview_empty_message);

        //initialize the AppDatabase
        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "paulive").build();
        //get an instance of DAO and define our db name
        userDao = db.userDao();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new UserAdapter(userDao, recyclerView, textview_empty_message);
        recyclerView.setAdapter(adapter);
        //determine our layout style
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        userDao.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> notes) {
                // Update the cached copy of the notes in the adapter.
                adapter.setUsers(notes); //notifyDataSetChanged();
            }
        });
        button_adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(() -> {
                    long lastInsertId = userDao.insert(new User(String.valueOf(edittext_adduser.getText())));
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "User created with id: " + lastInsertId,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
        });

        // For insert
        /*
        executor.execute(() -> {
            userDao.insert(new User("Pau"));
            userDao.insert(new User("Grab"));
            userDao.insert(new User("Boo!"));
            userDao.insert(new User("Zzzz"));
        });
         */
    }
}
