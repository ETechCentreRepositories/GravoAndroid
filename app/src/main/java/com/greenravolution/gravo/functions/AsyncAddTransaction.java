package com.greenravolution.gravo.functions;

import android.os.AsyncTask;
import android.util.Log;

import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AsyncAddTransaction extends AsyncTask<String, Void, String> {

    OnAsyncResult onAsyncResult;


    public void setOnResultListener(OnAsyncResult onAsyncResult) {
        if (onAsyncResult != null) {
            this.onAsyncResult = onAsyncResult;
        }
    }

    @Override
    protected String doInBackground(String ...paramsArray) {
        HttpReq httpReq = new HttpReq();
        API links = new API();
        String userID = paramsArray[9];
        String transactionID = "";
        String getCartUrl = links.getCart()+"userid="+userID;

        int addDetailsStatus = 0;
        int deleteDetailsStatus = 0;

        String postTransactionResult = httpReq.PostRequest(paramsArray[0],"collectiondate=" + paramsArray[1] + "&collectionaddress=" + paramsArray[2] + "&collectionuser=" + paramsArray[3] + "&collectionnumber=" + paramsArray[4]
            + "&total_price=" + paramsArray[5] + "&total_weight=" + paramsArray[6] + "&remarks=" + paramsArray[7] + "&transaction_id=" + paramsArray[8] + "&userid=" + paramsArray[9]
            + "&status=" + paramsArray[10]);

        String getCartDetailsResult = httpReq.GetRequest(getCartUrl);
        ArrayList<String> arrayOfId = new ArrayList<>();

        try {
            JSONObject postTransactionResultObject = new JSONObject(postTransactionResult);
            JSONArray postTransactionArray = postTransactionResultObject.getJSONArray("result");
            JSONObject postTransactionObject = postTransactionArray.getJSONObject(0);
            transactionID = postTransactionObject.getString("id");

            Log.i("transacdionID",transactionID);

            JSONObject cartDetailsResultObject = new JSONObject(getCartDetailsResult);
            JSONArray cartDetailsArray = cartDetailsResultObject.getJSONArray("result");
            for(int detail = 0; detail<cartDetailsArray.length(); detail++){
                JSONObject cartDetailObject = cartDetailsArray.getJSONObject(detail);
                Log.i("cardDetailObject",cartDetailObject.toString());

                //get IDs for deletion
                arrayOfId.add(cartDetailObject.getString("id"));

                //category_id,transaction_id,user_id,weight,price
                String categoryID = cartDetailObject.getString("category_id");
                String weight = cartDetailObject.getString("weight");
                String price = cartDetailObject.getString("price");
                String postTransactionDetailsURL = links.addTransactionDetails();

                String postTransactionDetailsResult = httpReq.PostRequest(postTransactionDetailsURL,"userid=" + userID + "&transactionid=" + transactionID + "&weight=" + weight + "&price=" + price + "&category=" + categoryID);
                JSONObject postTransactionDetailsObject = new JSONObject(postTransactionDetailsResult);
                addDetailsStatus = postTransactionDetailsObject.getInt("status");

            }
            if(addDetailsStatus == 200){
                String deleteCartDetailsURL = links.deleteCartDetails();
                for(int id = 0; id<arrayOfId.size(); id++){
                    String deleteID = arrayOfId.get(id);

                    String deleteCartDetailsResult = httpReq.PostRequest(deleteCartDetailsURL,"userid=" + userID + "&cartitemid=" + deleteID);
                    JSONObject deleteCartDetailsObject = new JSONObject(deleteCartDetailsResult);
                    deleteDetailsStatus = deleteCartDetailsObject.getInt("status");
                }
            } else {
                return "Fail";
            }
        }catch (JSONException e){
            e.printStackTrace();
        }





        if(addDetailsStatus == 200 && deleteDetailsStatus == 200){
            return "Success";
        } else {
            return "Fail";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onAsyncResult.result(0, s);
    }

    public interface OnAsyncResult {
        void result(int resultCode, String message);
    }
}
