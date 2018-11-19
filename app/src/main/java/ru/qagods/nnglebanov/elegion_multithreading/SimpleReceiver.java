package ru.qagods.nnglebanov.elegion_multithreading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class SimpleReceiver extends BroadcastReceiver {

    public static final String SIMPLE_ACTION="ru.qagods.nnglebanov.elegion_multithreading.SIMPLE_ACTION";

    private WeakReference<TextView> tv;

    public SimpleReceiver(TextView tv){
        this.tv=new WeakReference<>(tv);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        long time=intent.getLongExtra("Time",-1);
        Toast.makeText(context,time+"",Toast.LENGTH_SHORT).show();
        Intent intentToTempActivity=new Intent(context,TempActivity.class);
        intentToTempActivity.putExtra("Time",time);
        //context.startActivity(intentToTempActivity);
        tv.get().setText(String.valueOf(time));
    }
}
