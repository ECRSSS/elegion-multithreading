package ru.qagods.nnglebanov.elegion_multithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        long time=getIntent().getLongExtra("Time",-1);
        text = findViewById(R.id.text1);
        text.setText(String.valueOf(time));
    }
}
