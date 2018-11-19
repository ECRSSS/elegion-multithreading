package ru.qagods.nnglebanov.elegion_multithreading;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mStartServiceButton;
    private Button mStopServiceButton;
    private SimpleReceiver mSimpleReceiver;
    private IntentFilter mIntentFilter;
    private Button mSendCustomAction;
    private TextView mTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStartServiceButton = findViewById(R.id.btnStartService);
        mStopServiceButton = findViewById(R.id.btnStopService);
        mSendCustomAction = findViewById(R.id.btnSendCustomAction);
        mTimeText = findViewById(R.id.tvTime);

        mSendCustomAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(SimpleReceiver.SIMPLE_ACTION));
            }
        });

        mStartServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CountService.class);
                startService(intent);
            }
        });

        mStopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CountService.class);
                stopService(intent);

            }
        });

        mSimpleReceiver = new SimpleReceiver(mTimeText);
        mIntentFilter = new IntentFilter(SimpleReceiver.SIMPLE_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mSimpleReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mSimpleReceiver);
    }
}
