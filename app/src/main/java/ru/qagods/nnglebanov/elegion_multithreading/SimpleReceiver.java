package ru.qagods.nnglebanov.elegion_multithreading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SimpleReceiver extends BroadcastReceiver {

    public static final String SIMPLE_ACTION="ru.qagods.nnglebanov.elegion_multithreading.SIMPLE_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        long time=intent.getLongExtra("Time",-1);
        Toast.makeText(context,time+"",Toast.LENGTH_SHORT).show();
        Intent intentToTempActivity=new Intent(context,TempActivity.class);
        intentToTempActivity.putExtra("Time",time);
        context.startActivity(intentToTempActivity);
    }
}
