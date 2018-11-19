package ru.qagods.nnglebanov.elegion_multithreading;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountService extends Service {
    public static final String TAG = CountService.class.getSimpleName();
    private ScheduledExecutorService mScheduledExecutorService;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private String mychannel_id;

    public CountService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "OnCreate:");
        mScheduledExecutorService = Executors.newScheduledThreadPool(1);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder=getNotificationBuilder();
        mBuilder.setContentTitle("Count Time Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            return new NotificationCompat.Builder(this);
        }else{
            mychannel_id = "mychannel_id";
            if (mNotificationManager.getNotificationChannel(mychannel_id) == null) {
                NotificationChannel notificationChannel =
                        new NotificationChannel(mychannel_id, "Text for user", NotificationManager.IMPORTANCE_LOW);
                mNotificationManager.createNotificationChannel(notificationChannel);
            }
            return new NotificationCompat.Builder(this, mychannel_id);
        }
    }

    private Notification getNotification(String text){
        return mBuilder.setContentText(text).build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "OnStartCommand:");
        startForeground(123,mBuilder.setContentText("start notification").build());
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Log.d(TAG, "run: " + System.currentTimeMillis());
                mNotificationManager.notify(123,getNotification("time="+time));
                Intent intentToSendBroadcast = new Intent(SimpleReceiver.SIMPLE_ACTION);
                intentToSendBroadcast.putExtra("Time",time);
                sendBroadcast(intentToSendBroadcast);
            }
        }, 1000, 4000, TimeUnit.MILLISECONDS);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mScheduledExecutorService.shutdownNow();
        Log.d(TAG, "OnDestroy:");
    }
}
