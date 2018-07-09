package com.greenravolution.gravo.functions;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class AsyncDeleteCartDetails extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String ...paramsArray) {
        HttpReq postReq = new HttpReq();
        return postReq.PostRequest(paramsArray[0],"userid=" + paramsArray[1] + "&cartitemid=" + paramsArray[2]);
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
