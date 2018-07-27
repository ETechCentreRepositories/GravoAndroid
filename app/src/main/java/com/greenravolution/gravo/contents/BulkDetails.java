package com.greenravolution.gravo.contents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.CategoryFragments.Bulk;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;

import org.json.JSONException;
import org.json.JSONObject;

public class BulkDetails extends AppCompatActivity {
    ImageView bulkimage;
    TextView bulkaddress, bulkname, bulkdescription, bulkstatus,bulktransactionid,bulkquote;
    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    LinearLayout statusone,statustwo,statusthree;
    RelativeLayout btmlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        bulkimage = findViewById(R.id.bulkimage);
        bulkaddress = findViewById(R.id.bulkaddress);
        bulkname = findViewById(R.id.bulkname);
        bulkdescription = findViewById(R.id.bulkdescription);
        bulkstatus = findViewById(R.id.bulkstatus);
        bulktransactionid = findViewById(R.id.bulktransactionid);
        refreshLayout = findViewById(R.id.swipelayout);
        bulkquote = findViewById(R.id.bulkquote);

        btmlayout = findViewById(R.id.btmlayout);
        statustwo = findViewById(R.id.statustwo);
        btmlayout.setVisibility(View.GONE);
        statustwo.setVisibility(View.GONE);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdetails();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getdetails();
    }

    private class GetBulkItem extends AsyncTask<String, Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            Intent intent = getIntent();
            int id = intent.getIntExtra("bulkid",-1);
            HttpReq req = new HttpReq();
            return req.GetRequest("http://ehostingcentre.com/gravo/getbulkitems.php?type=single_item&id="+String.valueOf(id));
        }

        @Override
        protected void onPostExecute(String s) {
            refreshLayout.setRefreshing(false);
            super.onPostExecute(s);
            if(s.equals("")||s==null){

            }else{
                try {
                    JSONObject result = new JSONObject(s);
                    int status = result.getInt("status");
                    if(status==200){
                        JSONObject item = result.getJSONArray("result").getJSONObject(0);
                        Glide.with(BulkDetails.this).load(item.getString("image")).into(bulkimage);
                        bulkname.setText(item.getString("full_name"));
                        bulkquote.setText(item.getString("price_quote"));
                        bulkstatus.setText(item.getString("status"));
                        String[] address = item.getString("address").split("_");
                        bulkaddress.setText(address[0]+" "+address[1]+"\n"+address[2]+"\n"+address[3]);
                        bulkdescription.setText(item.getString("description"));
                        bulktransactionid.setText(item.getString("transaction_id_key"));
                        int status_id = item.getInt("bulk_transaction_status_id");
                        if(status_id==2){
                            btmlayout.setVisibility(View.VISIBLE);
                            statustwo.setVisibility(View.VISIBLE);
                        }else{
                            btmlayout.setVisibility(View.GONE);
                            statustwo.setVisibility(View.GONE);
                        }

                    }else if(status==404){
                        refreshLayout.setRefreshing(false);
                    }else{
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(BulkDetails.this, "An unexpected error has occurred, please contact the administrator", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };

        }
    }
    public void getdetails(){
        GetBulkItem getBulkItem = new GetBulkItem();
        getBulkItem.execute();
    }
}
