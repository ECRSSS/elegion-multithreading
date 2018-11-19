package ru.qagods.nnglebanov.elegion_multithreading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SimpleReceiver extends BroadcastReceiver {

    public static final String SIMPLE_ACTION="ru.qagods.nnglebanov.elegion_multithreading.SIMPLE_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getAction(),Toast.LENGTH_SHORT).show();
    }
}
