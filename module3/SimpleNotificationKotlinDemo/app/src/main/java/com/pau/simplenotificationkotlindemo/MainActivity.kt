package com.pau.simplenotificationkotlindemo

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pau.simplenotificationkotlindemo.ui.theme.SimpleNotificationKotlinDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            REQUEST_CODE // An app-defined int constant used in the callback to identify the request
        )
        setContent {
            SimpleNotificationKotlinDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    DisplayNotificationPage()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission was granted. You can post notifications.
                } else {
                    // Permission denied. You can't post notifications.
                }
            }
        }
    }
}

/*
Initiate a NotificationChannel instance by specifying a unique channel ID, a name, a description,
and an importance level that you set. Then, enroll the created channel with the system.
 */
// Channel ID as a Global Variable
private const val CHANNEL_ID = "FoodPanda"

private fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is not in the Support Library.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "FoodPanda"
        val descriptionText = "Channel on FoodPanda"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Composable
fun createNotificationBuilder(
    context: Context,
    channelId: String,
    @DrawableRes smallIconResId: Int,
    textTitle: String,
    textContent: String
): NotificationCompat.Builder {
    // Create a NotificationCompat.Builder instance for building the notification
    return NotificationCompat.Builder(context, channelId)
        // Set the small icon for the notification
        .setSmallIcon(smallIconResId)
        // Set the title of the notification
        .setContentTitle(textTitle)
        // Set the content text of the notification
        .setContentText(textContent)
        // Set the priority of the notification (default priority is used here)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
}

// Notification ID as a Global Variable
private const val NOTIFICATION_ID = 1
private const val REQUEST_CODE = 99

@Composable
fun DisplayNotificationPage() {
    // Obtain the current context using LocalContext
    val context = LocalContext.current

    // Create the notification builder
    //we can actually use this to create notifications
    val notificationBuilder = createNotificationBuilder(
        context = context,
        channelId = CHANNEL_ID,
        smallIconResId = android.R.drawable.ic_dialog_alert,//R.drawable.icon,
        textTitle = "Notification Title",
        textContent = "Notification Content"
    )
// Display the notification using another Composable function or trigger it in an event
    // (Note: Actual notification display typically involves using NotificationManagerCompat)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Button to trigger the notification
        Button(
            onClick = {
                // Show the notification (this is just an example, you may need to use NotificationManagerCompat)
                with(NotificationManagerCompat.from(context)) {
                    // notificationId is a unique int for each notification that you must define.
                    if (ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.d("Notification", "Permission issue")

                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                          //                                        int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                       // return ""
                    }
                    notify(NOTIFICATION_ID, notificationBuilder.build())
                    
                }
            }
        ) {
            // Text inside the button
            Text("Show Notification")
        }
    }
}

