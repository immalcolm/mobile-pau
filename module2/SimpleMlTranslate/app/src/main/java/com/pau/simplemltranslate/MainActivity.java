package com.pau.simplemltranslate;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.languageid.LanguageIdentifier;
import com.google.mlkit.nl.languageid.LanguageIdentification;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
//@TODO: READ
//This project revolves around simple ML Translation Tapping onto Google ML Kit
//v1 translate between given and available models
//Supported Languages >> https://developers.google.com/ml-kit/language/translation/translation-language-support

public class MainActivity extends AppCompatActivity {

    static String TAG = "Translation";
    LanguageIdentifier languageIdentifier = LanguageIdentification.getClient();
    TextView sourceTextTitle;
    EditText targetTextView;
    String languageSrc = "en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = "hi there";
        translateText(text, TranslateLanguage.ENGLISH, TranslateLanguage.GERMAN);

        Button detectLang = findViewById(R.id.button_detect_lang);

        sourceTextTitle = findViewById(R.id.textview_source);
        EditText myTextView = findViewById(R.id.edittext_source_lang);
        targetTextView = findViewById(R.id.edittext_target_lang);

        detectLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectLang(myTextView);
                translateText(myTextView.getText().toString(), languageSrc, "zh");
            }
        });

    }

    //Setup our translation method
    private void translateText(String message, String sourceLang, String targetLang){
        //Step 1. Create a translation object
        // Create an English-German translator:
        TranslatorOptions options =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(sourceLang)//"en"/"zh"
                        .setTargetLanguage(targetLang)
                        .build();
        final Translator translator =
                Translation.getClient(options);



        //Step 2. Setup Translation: Ensure language models exist on device
        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Model downloaded successfully, now translate
                        translator.translate(message)
                                .addOnSuccessListener(new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(String translatedText) {
                                        // Translation successful
                                        // Use the translated text
                                        targetTextView.setText(translatedText);
                                        Log.d(TAG,"Translated text" + translatedText);
                                        translator.close();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Error during translation
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error in downloading the model
                    }
                });
    }

    private void detectLang(TextView textView){
        String text = textView.getText().toString();

        languageIdentifier.identifyLanguage(text)
                .addOnSuccessListener(languageCode -> {
                    if (!languageCode.equals("und")) {
                        // Successfully identified the language
                        // Do something with the identified language code (e.g., "en", "fr")
                        sourceTextTitle.setText("Lang code detected: " + languageCode);
                        languageSrc = languageCode;
                        Log.d(TAG,"Lang code: " + languageCode);
                    } else {
                        // Language not identified
                        sourceTextTitle.setText("Unable to identify language, switching to English");
                        Log.d(TAG,"Unable to identify language, switching to English");
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }
}