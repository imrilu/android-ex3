package com.postpc.imri.ex2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Imri on 11-May-18.
 */

public class AsyncActivity extends AppCompatActivity {

    private static final String NO_THREAD = "No thread found! please first create one";
    Button create;
    Button start;
    Button cancel;
    TextView textview;
    MyAsyncTask asyncThread;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_activity);
        // adding this thread to the thread list
        textview = findViewById(R.id.async_textview);
        create = findViewById(R.id.async_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncThread = new MyAsyncTask(AsyncActivity.this, textview);
            }
        });

        start = findViewById(R.id.async_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asyncThread == null || asyncThread.getStatus() == AsyncTask.Status.FINISHED ) {
                    return;
                }
                if (asyncThread.getStatus() == AsyncTask.Status.PENDING) {
                    asyncThread.execute();
                }
            }
        });

        cancel = findViewById(R.id.async_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncThread.cancel(true);
                textview.setText(MyAsyncTask.CANCELED_MSG);
            }
        });


    }
}


