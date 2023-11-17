package com.pau.simpletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleTimer extends AppCompatActivity {

    TextView textview_counter;
    //for playing with main layout
    ViewGroup rootView;
    private int count = 0;
    private String TAG = "Timer";
    private CountDownTimer countDownTimer;
    private TextView textview_timer;
    private EditText edittext_countdown_limit; //set to number type
    private Button button_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //when we have no idea of the root view ID
        rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        textview_counter = (TextView) findViewById(R.id.textview_counter);
        runTimer();


        //let's make our countdown

        button_start = (Button) findViewById(R.id.button_start);
        textview_timer = (TextView) findViewById(R.id.textview_countdown_timer);
        edittext_countdown_limit = (EditText) findViewById(R.id.edittext_countdown_limit);
        //add button listener for countdown timer
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                button_start.setText("Counting Down ↓");
                button_start.setEnabled(false);
            }
        });
    }

    private void runTimer() {
        //create new Timer object
        Timer timer = new Timer();

        //schedule a specified task for reported fixed-delay execution
        //beginning after the specified delay
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //task to be done at fixed rate
                count++;
                Log.d(TAG, "count::" + count);
                if (count >= 10) {
                    this.cancel();
                }
                //if you update UI here ..errors may occur. try it out
                //update UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //update UI elements here
                        textview_counter.setText(String.valueOf(count));
                    }
                });
            }
        }, 1000, 1000);
    }

    private void startTimer() {
        long timeLeftInMillis = Long.parseLong(String.valueOf(edittext_countdown_limit.getText())) * 1000;//60 secs
        long countDownInterval = 1000; //1 second interval

        //runs on main thread
        countDownTimer = new CountDownTimer(timeLeftInMillis, countDownInterval) {
            //Note: does not guarantee exact timing for each interval
            //the interval can vary based on accuracy of system timer and CPU scheduling
            @Override
            public void onTick(long millisUntilFinished) {
                //called upon every interval
                //update the UI with the countdown
                textview_timer.setText("" + millisUntilFinished / 1000);
                rootView.setBackgroundColor(getRandomColor());
            }

            @Override
            public void onFinish() {
                //called when the timer finishes
                //Update the UI to show that the timer has finished
                textview_timer.setText("Tada!!! ");
                button_start.setText("Start");
                button_start.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Tada done!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private int getRandomColor() {
        Random rnd = new Random();
        int maxBound = 256;
        return Color.argb(100, rnd.nextInt(maxBound), rnd.nextInt(maxBound), rnd.nextInt(maxBound));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            //when to stop
            countDownTimer.cancel();
        }
    }

}