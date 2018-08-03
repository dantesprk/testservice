// java file android/src/com/kdab/training/MyService.java
package com.kdab.training;

import android.content.Context;
import android.content.Intent;
import org.qtproject.qt5.android.bindings.QtService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.os.Handler;
import java.util.concurrent.TimeUnit;
import android.os.Looper;

public class MyService extends QtService
{
    public static void startMyService(Context ctx)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            ctx.startForegroundService(new Intent(ctx, MyService.class));
        else
            ctx.startService(new Intent(ctx, MyService.class));
    }

    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "channel-01")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text");


        startForeground (456, builder.build());
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
       return START_NOT_STICKY;
       //return super.onStartCommand(intent, flags, startId);
   }
}
