package com.postpc.imri.ex2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Imri on 11-May-18.
 */

public class ThreadsActivity extends AppCompatActivity {

    private static final String CREATED = "Thread handler created";
    private static final String FINISHED = "Thread handler finished";
    private static final String CANCELED = "Thread handler canceled";
    private static final String NO_THREAD = "No thread found! please first create one";

    Button create;
    Button start;
    Button cancel;
    Handler handler;
    Thread thread;

    // Create Inner Thread Class
    class MyThread implements Runnable {
        @Override
        public void run() {
            Message message;
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    try {
                        if (Thread.currentThread().isInterrupted()) {
                            // checking if thread is being interrupted (cancel button)
                            message = Message.obtain();
                            message.what = -3;
                            handler.sendMessage(message);
                            return;
                        }
                        message = Message.obtain();
                        message.what = i;
                        handler.sendMessage(message);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(e);
                    }
                }
            }
            // finished running successfully. printing message to screen
            message = Message.obtain();
            message.what = -1;
            handler.sendMessage(message);
        }
    }

    // class for handler to change textView object
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            TextView textview = findViewById(R.id.threads_textview);
            switch (msg.what) {
                case -4:
                    textview.setText(NO_THREAD);
                    break;
                case -3:
                    textview.setText(CANCELED);
                    break;
                case -2:
                    textview.setText(CREATED);
                    break;
                case -1:
                    textview.setText(FINISHED);
                    break;
                default:
                    textview.setText(String.format("%s", msg.what));
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threads_activity);
        create = findViewById(R.id.threads_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                thread = new Thread(new MyThread());
                handler = new MyHandler();
                message.what = -2;
                // finished executing thread. sending -2 message for CREATED
                handler.sendMessage(message);
            }
        });

        start = findViewById(R.id.threads_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread == null || thread.getState() == Thread.State.TERMINATED){
                    // we dont have initialized thread yet.
                    return;
                } else if (thread.getState() == Thread.State.NEW) {
                    // starting the thread
                    thread.start();
                }
            }
        });

        cancel = findViewById(R.id.threads_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread != null) {
                    thread.interrupt();
                }
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        //TODO: implement save instance
//        ArrayList<String> messages = new ArrayList<>(mAdapter.data.size());
//        for (MessagePojo msg : mAdapter.data) {
//            messages.add(msg.toJsonObject().toString());
//        }
//        outState.putStringArrayList(MESSAGES, messages);

        super.onSaveInstanceState(outState);
    }


}


