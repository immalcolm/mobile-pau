package com.pau.simplesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

//    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    private SharedPreferences sharedPref;

    private TextView textview_sharedpref;
    protected static String key = "TestPref";
    private ConstraintLayout layout;//activity layout
    private Button button_changeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (ConstraintLayout) findViewById(R.id.layout);

        //Context context = getApplicationContext();
        //this will save a new MyPrefs.xml file in data
        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        writeToPrefString("test","testing shared preference");

        textview_sharedpref = (TextView) findViewById(R.id.textview_sharedpref);

        //<key>, <not found or null>
        String value = sharedPref.getString("test", "key not found");
        //String value = sharedPref.getString("x", "key not found");
        Log.d("Shared", "value .. " + value);
        textview_sharedpref.setText(value);

        button_changeColor = (Button) findViewById(R.id.button_changeColor);
        button_changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColorScheme();
            }
        });
    }

    private void writeToPrefString(String key, String value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value); // Put your value with a key
        //editor.putBoolean(key, true);
        editor.apply(); // Or editor.commit();
    }

    private void writeToPrefInt(String key, int value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value); // Put your value with a key
        //editor.putBoolean(key, true);
        editor.apply(); // Or editor.commit();
    }

    private void changeColorScheme(){
        //retrieve the stored color, default set to white
        int currentColor = sharedPref.getInt("backgroundColor", Color.WHITE);

        //2 color schemes
        int bgColor1 = Color.parseColor("#FFEED9");
        int bgColor2 = Color.parseColor("#D70F64");

        //toggle colors if one is used
        if(currentColor == bgColor1){
            currentColor = bgColor2;
        }else{
            currentColor = bgColor1;
        }

        writeToPrefInt("backgroundColor", currentColor);
        //apply the new color
        layout.setBackgroundColor(currentColor);
    }
}