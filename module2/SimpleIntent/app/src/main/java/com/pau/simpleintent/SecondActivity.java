package com.pau.simpleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView textview_message;
    public static final String RECEIVE_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textview_message = (TextView)findViewById(R.id.textview_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(RECEIVE_MESSAGE);

        textview_message.setText(message);


    }
}