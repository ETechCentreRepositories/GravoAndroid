package com.greenravolution.gravoapp.functions;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncEditCartDetails extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String ...paramsArray) {
        HttpReq postReq = new HttpReq();

        return postReq.PostRequest(paramsArray[0],"userid=" + paramsArray[1] + "&weight=" + paramsArray[2] + "&price=" + paramsArray[3] + "&cartitemid=" + paramsArray[4]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            JSONObject result = new JSONObject(s);
            int status = result.getInt("status");
            String message = result.getString("message");

            Log.i("status",status+"");
            Log.i("message",message+"");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
