package com.greenravolution.gravo.functions;

import android.os.AsyncTask;
import android.util.Log;

import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class asyncGetSelectedTransaction extends AsyncTask<String, Void , String> {
    OnAsyncResult onAsyncResult;


    public void setOnResultListener(OnAsyncResult onAsyncResult) {
        if (onAsyncResult != null) {
            this.onAsyncResult = onAsyncResult;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpReq req = new HttpReq();
        String chosenID = strings[0];
        String result = "";

        API links = new API();
        String getTransactionDetailsURL = links.getTransactionDetails()+"?transactionid="+chosenID;
        String getTransactionDetailsResult = req.GetRequest(getTransactionDetailsURL);

        String getTransactionURL = links.getTransaction()+"?type=withid&transactionid="+chosenID;
        String getTransactionResult = req.GetRequest(getTransactionURL);


        try{
            JSONObject transactionDetailObject = new JSONObject(getTransactionDetailsResult);
            JSONArray transactionDetailResultArray = transactionDetailObject.getJSONArray("result");

            for(int detail=0; detail<transactionDetailResultArray.length(); detail++){
                JSONObject transactionDetail = transactionDetailResultArray.getJSONObject(detail);
                String category_id = transactionDetail.getString("category_id");

                String getCategoryTypeURL = links.getCategories()+"?type=withid&category="+category_id;
                String getCategoryTypeResult = req.GetRequest(getCategoryTypeURL);

                JSONObject getCategoryTypeObject = new JSONObject(getCategoryTypeResult);
                JSONArray categoryTypeResultArray = getCategoryTypeObject.getJSONArray("result");
                JSONObject category = categoryTypeResultArray.getJSONObject(0);

                String type = category.getString("item");
                //String formattedType = type.substring(0,type.indexOf(" "));

                String rate = category.getString("rate");

                transactionDetail.put("category_type",type);
                transactionDetail.put("category_rate",rate);


            }



            JSONObject resultObject = new JSONObject();
            resultObject.put("status",200);
            resultObject.put("message","OK");
            resultObject.put("detailsArray",transactionDetailResultArray);


            JSONObject transactionObject = new JSONObject(getTransactionResult);
            JSONArray transactionResultArray = transactionObject.getJSONArray("result");

            resultObject.put("transactionArray",transactionResultArray);


            result = resultObject.toString();


        } catch (JSONException e){
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