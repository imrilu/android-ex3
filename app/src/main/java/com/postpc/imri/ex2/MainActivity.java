package com.postpc.imri.ex2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String MESSAGES = "messages";
    Button asyncButton;
    Button threadsButton;
    EditText mInput;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncButton = findViewById(R.id.async_main_button);
        asyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AsyncActivity.class);
                intent.putExtra("callingActivity", "MainActivity");
                final int result = 1;
                startActivity(intent);


            }
        });
        threadsButton = findViewById(R.id.threads_main_button);
        threadsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThreadsActivity.class);
                intent.putExtra("callingActivity", "MainActivity");
                final int result = 1;
                startActivity(intent);
            }
        });






    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
//        ArrayList<String> messages = new ArrayList<>(mAdapter.data.size());
//        for (MessagePojo msg : mAdapter.data) {
//            messages.add(msg.toJsonObject().toString());
//        }
//        outState.putStringArrayList(MESSAGES, messages);

        super.onSaveInstanceState(outState);
    }
}

