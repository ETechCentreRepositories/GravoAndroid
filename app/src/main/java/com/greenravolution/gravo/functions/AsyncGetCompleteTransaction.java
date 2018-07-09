package com.greenravolution.gravo.functions;

import android.os.AsyncTask;
import android.util.Log;

import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncGetCompleteTransaction extends AsyncTask<String, Void , String> {
    OnAsyncResult onAsyncResult;


    public void setOnResultListener(OnAsyncResult onAsyncResult) {
        if (onAsyncResult != null) {
            this.onAsyncResult = onAsyncResult;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpReq req = new HttpReq();
        String result = "";
        API links = new API();

        String getTransaction = req.GetRequest(strings[0]);
        try{
            JSONObject resultObject = new JSONObject();

            JSONObject getTransactionObject = new JSONObject(getTransaction);
            JSONArray getTransactionResultArray = getTransactionObject.getJSONArray("result");

            resultObject.put("status",200);
            resultObject.put("message","OK");
            //resultObject.put("transactionArray",getTransactionResultArray);

            for(int i = 0; i<getTransactionResultArray.length(); i++){
                JSONObject transaction = getTransactionResultArray.getJSONObject(i);
                String transaction_id = transaction.getString("id");

                String getTransactionDetailsURL = links.getTransactionDetails()+"?transactionid="+transaction_id;
                String getTransactionDetailsResult = req.GetRequest(getTransactionDetailsURL);
                JSONObject getTransactionDetailsObject = new JSONObject(getTransactionDetailsResult);
                JSONArray getTransactionDetailsArray = getTransactionDetailsObject.getJSONArray("result");

                JSONArray transactionDetailArray = new JSONArray();


                for(int detail = 0; detail<getTransactionDetailsArray.length(); detail++){
                    JSONObject transactionDetail = new JSONObject();
                    JSONObject getTransactionDetail = getTransactionDetailsArray.getJSONObject(detail);
                    String cat_id = getTransactionDetail.getString("category_id");

                    String getCategoryTypeURL = links.getCategories()+"?type=withid&category="+cat_id;
                    String getCategoryTypeResult = req.GetRequest(getCategoryTypeURL);

                    JSONObject getCategoryTypeObject = new JSONObject(getCategoryTypeResult);
                    JSONArray categoryTypeResultArray = getCategoryTypeObject.getJSONArray("result");
                    JSONObject category = categoryTypeResultArray.getJSONObject(0);

                    String type = category.getString("type");
                    transactionDetail.put("category_type",type);
                    transactionDetailArray.put(transactionDetail);
                    transaction.put("details",transactionDetailArray);
                }

            }

            result = getTransactionObject.toString();
        }catch(JSONException e){
            e.printStackTrace();
        }

        if(!result.isEmpty()){
            Log.e("resultObject",result);
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
