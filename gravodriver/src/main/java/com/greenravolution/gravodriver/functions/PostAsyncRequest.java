package com.greenravolution.gravodriver.functions;

import android.os.AsyncTask;

public class PostAsyncRequest extends AsyncTask<String, Void, String> {
    PostAsyncRequest.OnAsyncResult onAsyncResult;


    public void setOnResultListener(PostAsyncRequest.OnAsyncResult onAsyncResult) {
        if (onAsyncResult != null) {
            this.onAsyncResult = onAsyncResult;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpReq req = new HttpReq();
        String result = req.PostRequest(strings[0],strings[1]);
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        onAsyncResult.result(0, result);
    }

    public interface OnAsyncResult {
        void result(int resultCode, String message);
    }
}
