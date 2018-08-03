package com.kdab.training;

import android.widget.Toast;
import android.content.Intent;
import android.content.Context;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import org.qtproject.qt5.android.bindings.QtService;

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

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            String channelId = "channel-01";
            String channelName = "Channel Name";

            NotificationChannel сhannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(сhannel);
        }

        NotificationCompat.Builder builder =new NotificationCompat.Builder(this, "channel-01")
                                                .setSmallIcon(R.mipmap.ic_launcher)
                                                .setContentTitle("Title")
                                                .setContentText("Text");

        startForeground (456, builder.build());

        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
    }

   public int onStartCommand(Intent intent, int flags, int startId) {
       return START_NOT_STICKY;
       //return super.onStartCommand(intent, flags, startId);
   }
}
