package com.pau.simpleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button_sendmessage;
    Button button_sendemail;
    EditText edittext_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. initialize UI and assign based on the ids created in activity_main.xml
        button_sendmessage = (Button)findViewById(R.id.button_sendmessage);
        edittext_message = (EditText) findViewById(R.id.edittext_message);
        button_sendemail = (Button) findViewById(R.id.button_sendemail);

        //2. Create button handler
        //annoymous function
        button_sendmessage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //handle the button click
                sendMessage(view);
            }
        });

        button_sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(v);
            }
        });
    }


    //3. Make intent function
    public void sendMessage(View view) {
        //3. Make explicit intent
        Intent intent = new Intent(this, SecondActivity.class);
        
        //we can upgrade with a static identifier or a string identifier from our resource
        intent.putExtra(SecondActivity.RECEIVE_MESSAGE, edittext_message.getText().toString());
        //4. Add additional intent data

        startActivity(intent);
    }

    //5. Action based intent
    public void sendEmail(View view){
        Log.d("Activit","email sending");
        Intent intent = new Intent(Intent.ACTION_SEND);

        //share as email intent
        intent.setType("message/cfr893");

        //share text intent
        //intent.setType("text/plain");

        //make out intent message
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@test.com"});
        intent.putExtra(Intent.EXTRA_TEXT, edittext_message.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT, "This is a subj");

        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(Intent.createChooser(intent,"Choose an email client:"));
        }
    }
}