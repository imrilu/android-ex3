package com.postpc.imri.ex2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Imri on 11-May-18.
 */

public class MyAsyncTask extends AsyncTask<Void, String, String> {

    protected final static String FINISH = "FINISH";
    protected final static String CREATED_MSG = "AsyncTask thread created";
    protected final static String CANCELED_MSG = "AsyncTask thread canceled";
    protected final static String FINISHED_MSG = "AsyncTask thread finished";
    protected int counter;
    protected Context context;
    protected TextView textview;

    public MyAsyncTask(Context context, TextView textview) {
        this.context = context;
        this.textview = textview;
        textview.setText(CREATED_MSG);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        counter = 0;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        textview.setText(values[0]);
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (counter >= 0 || counter <= 10) {
            // counter is valid
            for (int i = 0; i < 10; i++) {
                try {
                    this.publishProgress(Integer.toString(i));
                    Thread.sleep(500);
//                    return Integer.toString(i);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
        return FINISH;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        textview.setText(CANCELED_MSG);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textview.setText(FINISHED_MSG);
    }



}
