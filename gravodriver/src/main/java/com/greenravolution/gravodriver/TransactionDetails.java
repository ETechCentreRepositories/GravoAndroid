package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class TransactionDetails extends AppCompatActivity {
    public static final String SESSION = "login_status";
    Toolbar toolbar;
    TextView taddress, ttiming;
    LinearLayout items, llGetRecipient;
    Button btnAddRec, btnCfmNPay;
    EditText etGetName, etGetNumber;
    LinearLayout llProgress;
    ImageView progressbar;
    TextView totalPrice, totalWeight;

    SharedPreferences sessionManager;

    Rates getRates = new Rates();
    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        StopLoading();
        try {
            SharedPreferences sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            JSONObject object = new JSONObject(message);
            Log.e("MESSAGE", message);
            int status = object.getInt("status");
            if (status == 200) {

                JSONArray results = object.getJSONArray("result");
                JSONObject transaction = results.getJSONObject(0);
                String getTotalWeight = transaction.getString("total_weight");
                String getTotalPrice = String.valueOf(transaction.getDouble("total_price"));
                totalPrice.setText(String.format("$%s", getTotalPrice));
                totalWeight.setText(getTotalWeight);
                JSONArray details = object.getJSONArray("details");
                for (int i = 0; i < details.length(); i++) {
                    JSONObject detail = details.getJSONObject(i);
                    String detail_id = String.valueOf(detail.getInt("id"));
                    String detail_item = detail.getString("item");
                    String detail_price = String.valueOf(detail.getDouble("price"));
                    String detail_weight = String.valueOf(detail.getDouble("weight"));
                    String detail_rate = detail.getString("rate");
                    String[] itemArray = {detail_item, detail_price, detail_weight, detail_rate, detail_id};
                    items.addView(initView(itemArray));
                }
                llProgress.setVisibility(View.GONE);
                AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
                progressDrawable.stop();

            } else {
                Toast.makeText(this, "No Details", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        llProgress = findViewById(R.id.llProgress);
        progressbar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);
        taddress = findViewById(R.id.address);
        ttiming = findViewById(R.id.arrivalTime);
        items = findViewById(R.id.listview);
        llGetRecipient = findViewById(R.id.llgetRecepient);
        llGetRecipient.setVisibility(View.GONE);
        btnAddRec = findViewById(R.id.addRecipient);
        btnCfmNPay = findViewById(R.id.cfmPayment);
        etGetName = findViewById(R.id.getRecName);
        etGetNumber = findViewById(R.id.getRecContact);
        totalPrice = findViewById(R.id.totalPrice);
        totalWeight = findViewById(R.id.totalWeight);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {

            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        btnAddRec.setOnClickListener(v -> {
            llGetRecipient.setVisibility(View.VISIBLE);
            btnAddRec.setVisibility(View.GONE);
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("transaction_id");
        String address = intent.getStringExtra("address");
        toolbar.setTitle(title);
        taddress.setText(address);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        ttiming.setText(String.format("Arrival Time: %s", date));

        int trans_id = intent.getIntExtra("id", -1);

        getTransacionDetails(trans_id);

//        getTransacionDetails(trans_id);

        btnCfmNPay.setOnClickListener(v -> {
            if (Objects.equals(etGetName.getText().toString(), "") || Objects.equals(etGetName.getText().toString(), "")) {
                Intent ib = new Intent();
                ib.putExtra("type", "1");
                ib.putExtra("transaction_id", String.valueOf(trans_id));
                setResult(1, ib);
                UpdateTransactionStatus updateTransactionStatus = new UpdateTransactionStatus();
                Intent intent1 = getIntent();
                int transactionid = intent1.getIntExtra("id", -1);

                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                String collectorid = sessionManager.getString("id","");

                updateTransactionStatus.execute(String.valueOf(transactionid),collectorid);

                //update collection user.
            } else {
                String name = etGetName.getText().toString();
                String contact = etGetNumber.getText().toString();
                UpdateTransactionDetails updateTransactionDetails = new UpdateTransactionDetails();
                Intent intent1 = getIntent();
                int transactionid = intent1.getIntExtra("id", -1);
                updateTransactionDetails.execute(String.valueOf(transactionid), name, contact);
                //update collection
                Intent ib = new Intent();
                ib.putExtra("type", "1");
                ib.putExtra("transaction_id", trans_id);
                setResult(1, ib);
                finish();
            }
        });
        //temp
        llProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ib = new Intent();
        ib.putExtra("type", "0");
        setResult(1, ib);
        finish();

    }

    public void FinishAfterAsyncTask()
    {
        this.finish();
    }

    public void getTransacionDetails(int id) {
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.start();
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withid&transactionid=" + id);
    }

    public View initView(String[] itemArray) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.item_details, null);
        TextView getWeight = view.findViewById(R.id.getWeight);
        TextView getPrice = view.findViewById(R.id.getPrice);
        TextView getRate = view.findViewById(R.id.getRate);
        TextView getTitle = view.findViewById(R.id.getTitle);
        ImageView itemImg = view.findViewById(R.id.getImage);
        Button plus = view.findViewById(R.id.btnPlus);
        Button minus = view.findViewById(R.id.btnMinus);
        Button delete = view.findViewById(R.id.btnDelete);
        Double price = Double.parseDouble(itemArray[1]);
        Double weight = Double.parseDouble(itemArray[2]);
        String rate = itemArray[3];
        String category = itemArray[0];
        itemImg.setBackgroundColor(getRates.getImageColour(category));
        itemImg.setImageDrawable(getDrawable(getRates.getImage(category)));
        getTitle.setText(category);
        getRate.setText(rate);
        getPrice.setText(String.format("$%s", df2.format(price)));
        getWeight.setText(String.valueOf(weight));
        Double doubleRate = Double.parseDouble(rate.split("/")[0]);
        delete.setOnClickListener((View v) -> {
            String detail_id = itemArray[4];
            String getTotalPrice = totalPrice.getText().toString().substring(1);
            String getTotalWeight = totalWeight.getText().toString();
            double getOnlyWeight = Double.parseDouble(getTotalWeight.split("KG")[0]);
            double getOnlyPiece = Double.parseDouble(getTotalWeight.split(" ")[1]);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(false);
            dialog.setTitle("Delete Item");
            dialog.setMessage("Delete this " + itemArray[0].split(" ")[0] + " item?");
            dialog.setPositiveButton("yes", (dialogInterface, i) -> {
                if (itemArray[0].split(" ")[0].equalsIgnoreCase("e-waste")) {
                    double newTotalPiece = getOnlyPiece - Double.parseDouble(getWeight.getText().toString());
                    double itemTotalPrice = Double.parseDouble(getTotalPrice);
                    double newTotalPrice = itemTotalPrice - Double.parseDouble(getPrice.getText().toString().substring(1));
                    String newStringPieces = getOnlyWeight + "KG, " + newTotalPiece + " Piece(s)";
                    totalWeight.setText(newStringPieces);
                    totalPrice.setText(String.format("$%s", String.valueOf(newTotalPrice)));
                    Log.e("newStringPieces", newStringPieces);

                } else {
                    double newTotalWeight = getOnlyWeight - Double.parseDouble(getWeight.getText().toString());
                    double itemTotalPrice = Double.parseDouble(getTotalPrice);
                    double newTotalPrice = itemTotalPrice - Double.parseDouble(getPrice.getText().toString().substring(1));
                    String newStringPieces = newTotalWeight + "KG, " + getOnlyPiece + " Piece(s)";
                    totalWeight.setText(newStringPieces);
                    totalPrice.setText(String.format("$%s", String.valueOf(newTotalPrice)));
                    Log.e("newStringPieces", newStringPieces);
                    DeleteDetails deleteDetails = new DeleteDetails();
                    deleteDetails.execute(detail_id);
                }


                view.setVisibility(View.GONE);
                Toast.makeText(TransactionDetails.this, "Deleted", Toast.LENGTH_SHORT).show();
            });
            dialog.setNegativeButton("No", (dialogInterface, i) -> {
                Toast.makeText(getApplicationContext(), "Not deleted", Toast.LENGTH_LONG).show();
            });
            AlertDialog dialogue = dialog.create();
            dialogue.show();


        });
        minus.setOnClickListener((View v) -> {
            String detail_id = itemArray[4];
            String getTotalPrice = totalPrice.getText().toString().substring(1);
            String getTotalWeight = totalWeight.getText().toString();
            double getOnlyWeight = Double.parseDouble(getTotalWeight.split("KG")[0]);
            double getOnlyPiece = Double.parseDouble(getTotalWeight.split(" ")[1]);
            if (getWeight.getText().toString().equalsIgnoreCase("1.0") || getWeight.getText().toString().equalsIgnoreCase("1")) {
                if (itemArray[0].split(" ")[0].equalsIgnoreCase("e-waste")) {
                    Toast.makeText(TransactionDetails.this, "Cannot go below 1 Piece", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TransactionDetails.this, "Cannot go below 1 KG", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (itemArray[0].split(" ")[0].equalsIgnoreCase("e-waste")) {
                    DecimalFormat precision = new DecimalFormat("0.00");
                    double newTotalPiece = getOnlyPiece - 1.0;
                    double itemTotalPrice = Double.parseDouble(getTotalPrice);
                    double newTotalPrice = itemTotalPrice - doubleRate;
                    String newStringPieces = getOnlyWeight + "KG, " + newTotalPiece + " Piece(s)";
                    totalWeight.setText(newStringPieces);
                    totalPrice.setText(String.format("$%s", String.valueOf(precision.format(newTotalPrice))));
                    Log.e("newStringPieces", newStringPieces);

                } else {
                    DecimalFormat precision = new DecimalFormat("0.00");
                    double newTotalWeight = getOnlyWeight - 1.0;
                    double itemTotalPrice = Double.parseDouble(getTotalPrice);
                    double newTotalPrice = itemTotalPrice - doubleRate;
                    String newStringPieces = newTotalWeight + "KG, " + getOnlyPiece + " Piece(s)";
                    totalWeight.setText(newStringPieces);
                    totalPrice.setText(String.format("$%s", String.valueOf(precision.format(newTotalPrice))));
                    Log.e("newStringPieces", newStringPieces);
                }
                DecimalFormat precision = new DecimalFormat("0.00");
                getWeight.setText(String.valueOf(Double.parseDouble(getWeight.getText().toString()) - 1.0));
                getPrice.setText(String.format("$%s", String.valueOf(precision.format(Double.parseDouble(getPrice.getText().toString().substring(1)) - doubleRate))));

                UpdateDetails updateDetails = new UpdateDetails();
                updateDetails.execute(detail_id, getWeight.getText().toString(), getPrice.getText().toString().substring(1));
            }

        });
        plus.setOnClickListener((View v) -> {
            String detail_id = itemArray[4];
            String getTotalPrice = totalPrice.getText().toString().substring(1);
            String getTotalWeight = totalWeight.getText().toString();
            double getOnlyWeight = Double.parseDouble(getTotalWeight.split("KG")[0]);
            double getOnlyPiece = Double.parseDouble(getTotalWeight.split(" ")[1]);

            if (itemArray[0].split(" ")[0].equalsIgnoreCase("e-waste")) {
                double newTotalPiece = getOnlyPiece + 1.0;
                double itemTotalPrice = Double.parseDouble(getTotalPrice);
                double newTotalPrice = itemTotalPrice + doubleRate;
                String newStringPieces = getOnlyWeight + "KG, " + newTotalPiece + " Piece(s)";
                totalWeight.setText(newStringPieces);
                totalPrice.setText(String.format("$%s", String.valueOf(newTotalPrice)));
                Log.e("newStringPieces", newStringPieces);

            } else {
                double newTotalWeight = getOnlyWeight + 1.0;
                double itemTotalPrice = Double.parseDouble(getTotalPrice);
                double newTotalPrice = itemTotalPrice + doubleRate;
                String newStringPieces = newTotalWeight + "KG, " + getOnlyPiece + " Piece(s)";
                totalWeight.setText(newStringPieces);
                totalPrice.setText(String.format("$%s", String.valueOf(newTotalPrice)));
                Log.e("newStringPieces", newStringPieces);
            }

            getWeight.setText(String.valueOf(Double.parseDouble(getWeight.getText().toString()) + 1.0));
            getPrice.setText(String.format("$%s", String.valueOf(Double.parseDouble(getPrice.getText().toString().substring(1)) + doubleRate)));
            UpdateDetails updateDetails = new UpdateDetails();
            updateDetails.execute(detail_id, getWeight.getText().toString(), getPrice.getText().toString().substring(1));

        });
        return view;
    }

    public class DeleteDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://ehostingcentre.com/gravo/deletetransactiondetails.php", "id=" + strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE DETAILS RESULT:", s);
            try {
                JSONObject object = new JSONObject(s);
                int status = object.getInt("status");
                if (status == 200) {
                    Intent intent = getIntent();
                    int trans_id = intent.getIntExtra("id", -1);
                    UpdateTransaction updateTransaction = new UpdateTransaction();
                    updateTransaction.execute(String.valueOf(trans_id), totalWeight.getText().toString(), totalPrice.getText().toString().substring(1));
                } else {
                    Toast.makeText(TransactionDetails.this, "An unexpected error has occurred", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class UpdateDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://ehostingcentre.com/gravo/updatetransactiondetails.php", "transactiondetailid=" + Integer.parseInt(strings[0]) + "&weight=" + strings[1] + "&price=" + strings[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE DETAILS RESULT:", s);
            try {
                JSONObject object = new JSONObject(s);
                int status = object.getInt("status");
                if (status == 200) {
                    Intent intent = getIntent();
                    int trans_id = intent.getIntExtra("id", -1);
                    UpdateTransaction updateTransaction = new UpdateTransaction();
                    updateTransaction.execute(String.valueOf(trans_id), totalWeight.getText().toString(), totalPrice.getText().toString().substring(1));
                } else {
                    Toast.makeText(TransactionDetails.this, "An unexpected error has occurred", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public class UpdateTransactionDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Log.e("id", strings[0]);
            return req.PostRequest("http://ehostingcentre.com/gravo/updatetransactionuserandcontact.php", "transactionid=" + strings[0] + "&user=" + strings[1] + "&number=" + strings[2] + "&status=4");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE TRANSACTIONS:", s + "");
        }
    }

    public class UpdateTransactionStatus extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Log.e("id", strings[0]);
            return req.PostRequest("http://ehostingcentre.com/gravo/updatetransactionstatuscomplete.php"
                    , "transactionid=" + strings[0] + "&status=4" + "&collectorid=" + strings[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE TRANSACTIONS:", s + "");
            try {
                JSONObject object = new JSONObject(s);
                int status = object.getInt("status");
                if (status == 200) {
                    int transactionid = object.getJSONArray("data").getJSONObject(0).getInt("id");
                    UpdateStatusMessages updateStatusMessages = new UpdateStatusMessages();
                    updateStatusMessages.execute(String.valueOf(transactionid));
                    Log.i("called",object.toString());

                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.putString("alltransactionsObject",s);
                    editor.commit();
                    FinishAfterAsyncTask();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public class UpdateTransaction extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://ehostingcentre.com/gravo/updatetransactionpriceweight.php", "transactionid=" + Integer.parseInt(strings[0]) + "&weight=" + strings[1] + "&price=" + strings[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE TRANS RESULT:", s);
            try {
                JSONObject object = new JSONObject(s);
                int status = object.getInt("status");
                if (status == 200) {
                    UpdateStatusMessages updateStatusMessages = new UpdateStatusMessages();

                } else {
                    Toast.makeText(TransactionDetails.this, "An unexpected error has occurred", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void StartLoading() {
        llProgress.setVisibility(View.VISIBLE);
    }

    public void StopLoading() {
        llProgress.setVisibility(View.GONE);
    }

    public static class UpdateStatusMessages extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            int transactionID = Integer.parseInt(strings[0]);
            Date d = new Date();
            HttpReq req = new HttpReq();
            CharSequence date = android.text.format.DateFormat.format("MMMM d, yyyy", d.getTime());
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = mdformat.format(calendar.getTime());
            String addmessage = req.PostRequest("http://ehostingcentre.com/gravo/addtransactionhistory.php", "transactionid=" + transactionID + "&message=Driver has picked up the items on " + date + " at " + strDate);
            Log.e("HISTORY", addmessage);
            try {
                JSONObject results = new JSONObject(addmessage);
                int statusid = results.getInt("status");
                if (statusid != 200) {
                    Log.e("ERROR", "ERROR OCCURRED");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "true";

        }
    }
}
