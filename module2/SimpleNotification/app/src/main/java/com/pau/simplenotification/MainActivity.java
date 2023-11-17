package com.pau.simplenotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

//1. add permissions to manifest
//2. implement notification channel
//3. create notification and notify

//[Make Toast message]
//1. Creating Toast requires context, message, duration

//Reading Materials
//https://developer.android.com/reference/android/app/PendingIntent

//Generating unique notification id
//int notificationId = (int) System.currentTimeMillis();

public class MainActivity extends AppCompatActivity {

    //channel_id should be unique and particular to the case
    private static final String CHANNEL_ID = "pau_pau";
    private static final int MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 1;

    private static final int PROGRESS_MAX = 100;
    private static final int PROGRESS_CURRENT = 0;

    Button btn_create_notification;
    Button btn_create_notification_action;
    Button btn_create_toast;
    Button btn_create_notification_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create_notification = (Button) findViewById(R.id.btn_create_notification);
        btn_create_notification_action = (Button) findViewById(R.id.btn_create_notification_action);
        btn_create_toast = (Button) findViewById(R.id.btn_create_toast);
        btn_create_notification_progress = (Button) findViewById(R.id.btn_create_notification_progress);

        //@TODO 2. implement notification channels
        createNotificationChannel();


        btn_create_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Notification", "onclick");
                createSimpleNotification();
            }
        });

        btn_create_notification_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Notification", "Creating notification with action");
                createNotificationAction();
            }
        });

        btn_create_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Notification", "Creating toast message");
                createToastNotification();
            }
        });

        btn_create_notification_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createNotificationProgress();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //setup our notification channel first
    //stick to using constants when possible or strings.xml
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    //Tap on NotificationCompat.Builder to setup the notification
    //3 key parts (icon, title, text)
    private void createSimpleNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Example Notification")
                .setContentText("short notification...message")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //if multiple lines of text
        //.setStyle(new NotificationCompat.BigTextStyle().bigText("A big text A big textA big text A big text A big text A big text A big text A big text A big text A big text A big text"));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("notification", "permission issue");

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);

        } else {
            Log.d("Notification", "Creating n notify");
            notificationManager.notify(1, builder.build());
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS: {
                // Check if the request was granted or denied and act accordingly
                //ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);

                break;
            }
            // Handle other permission requests
        }
    }

    //creating a notification with actionable button that opens up another activity
    //take note of the PendingIntent FLAG_IMMUTABLE
    public void createNotificationAction() {
        Intent actionIntent = new Intent(this, SecondActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this, 0, actionIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("My Notification")
                .setContentText("This is an actionable notification")
                .addAction(R.drawable.baseline_celebration_24, "Go to Second Activity", actionPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);

        }
        notificationManager.notify(2, builder.build());
    }

    public void createToastNotification() {
        int duration = Toast.LENGTH_SHORT;

        //Create the toast object
        Toast toast = Toast.makeText(this, "Message sent!", duration);
        //display the toast
        toast.show();
    }

    public void createNotificationProgress() throws InterruptedException {
        //setup our builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Download in progress")
                .setContentText("Downloading.... ")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setOngoing(true) //Make the notification ongoing so it would not be dimissed
                .setOnlyAlertOnce(true) //alert only once for this notification
                .setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);//set the initial progress

        //create the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);

        }
        notificationManager.notify(3, builder.build());

        //create a notification thread using mythread
        MyThread thread = new MyThread(this, builder, notificationManager);

        thread.start();

    }

    class MyThread extends Thread {
        //for thread safety we infer a weak reference
        private final WeakReference<Activity> activityWeakReference;
        NotificationCompat.Builder builder;
        NotificationManagerCompat notificationManager;

        MyThread(Activity activity, NotificationCompat.Builder builder,
                 NotificationManagerCompat notificationManager) {
            this.activityWeakReference = new WeakReference<>(activity);
            this.notificationManager = notificationManager;
            this.builder = builder;
        }

        @Override
        public void run() {
            // Do some background work...

            Activity activity = activityWeakReference.get();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = activityWeakReference.get();

                        // Assuming this is inside a loop or a background task
                        int progress = 0;
                        for (; progress <= PROGRESS_MAX; progress += 10) {
                            builder.setProgress(PROGRESS_MAX, progress, false);
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                                        MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);

                            }
                            notificationManager.notify(3, builder.build());
                            // Sleep or wait for a while
                            try {
                                Thread.sleep(1000);  // Just for demonstration; replace with real progress logic
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (progress >= PROGRESS_MAX) {
                            builder.setContentText("Download complete")
                                    .setProgress(0, 0, false)  // Remove the progress bar
                                    .setOngoing(false);  // Allow the notification to be dismissible
                            notificationManager.notify(3, builder.build());
                        }
                    }
                });

            }
        }
    }

}

