package com.example.travelapp;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, String,
        String> {
    Activity activity;
    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        return HttpManager.getData(params[0]);
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<Destination> destinations =
                DestinationJasonParser.getObjectFromJason(s);
    }
}