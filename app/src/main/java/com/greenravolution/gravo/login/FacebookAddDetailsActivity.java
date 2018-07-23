package com.greenravolution.gravo.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.FaceDetector;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookBroadcastReceiver;
import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivityEditUser;
import com.greenravolution.gravo.functions.HttpReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookAddDetailsActivity extends AppCompatActivity {
    EditText getFN, getLN, getBlk, getUnit, getStreet, getPostal, getContact;
    TextView getEmail;
    Button completeProfile;
    SharedPreferences sharedPreferences;
    public static final String SESSION = "login_status";
    LinearLayout progress;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_add_details);
        sharedPreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getFN = findViewById(R.id.first_name);
        getFN.setText(sharedPreferences.getString("user_first_name",""));
        getLN = findViewById(R.id.last_name);
        getLN.setText(sharedPreferences.getString("user_last_name",""));
        getBlk = findViewById(R.id.address_blk);
        getUnit = findViewById(R.id.address_unit);
        getStreet = findViewById(R.id.address_street);
        getPostal = findViewById(R.id.address_postal);
        getContact = findViewById(R.id.getPhone);
        String getAddress = sharedPreferences.getString("user_address", "");
        if(getAddress.equals("")){
            getBlk.setText("");
            getUnit.setText("");
            getStreet.setText("");
            getPostal.setText("");
        }else{
            String[] addressarray = getAddress.split("_");
            getBlk.setText(addressarray[0].split(" ")[1]);
            getUnit.setText(addressarray[1].substring(1));
            getStreet.setText(addressarray[2]);
            getPostal.setText(addressarray[3].split(" ")[1]);
        }
        progress = findViewById(R.id.progressbar);
        getContact.setText(sharedPreferences.getString("user_contact",""));
        getEmail = findViewById(R.id.newEmail);
        getEmail.setText(sharedPreferences.getString("user_email",""));
        completeProfile = findViewById(R.id.complete);
        completeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateItems()){
                    UpdateDetails updateDetails = new UpdateDetails();
                    updateDetails.execute();
                }else{
                    Toast.makeText(FacebookAddDetailsActivity.this, "Please fill in all your details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean validateItems(){
        if(getFN.getText().toString().equalsIgnoreCase("")
                ||getLN.getText().toString().equalsIgnoreCase("")
                ||getBlk.getText().toString().equalsIgnoreCase("")
                ||getUnit.getText().toString().equalsIgnoreCase("")
                ||getStreet.getText().toString().equalsIgnoreCase("")
                ||getPostal.getText().toString().equalsIgnoreCase("")
                ||getContact.getText().toString().equalsIgnoreCase("")
                ||getEmail.getText().toString().equalsIgnoreCase("")){
            return false;
        }else{
            return true;
        }
    }
    public class UpdateDetails extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            address = "Blk "+getBlk.getText().toString()+"_#"+getUnit.getText().toString()+"_"+getStreet.getText().toString()+"_Singapore "+getPostal.getText().toString();
            HttpReq req = new HttpReq();
            sharedPreferences = getSharedPreferences(SESSION,Context.MODE_PRIVATE);
            return req.PostRequest("http://ehostingcentre.com/gravo/updateuserdetails.php"
                    ,"userid="
                    +sharedPreferences.getInt("user_id",-1)
                    +"&firstname="+getFN.getText().toString()
                    +"&lastname="+getLN.getText().toString()
                    +"&email="+getEmail.getText().toString()
                    +"&contactnumber="+getContact.getText().toString()
                    +"&address="+address
                    +"&block="+getBlk.getText().toString()
                    +"&unit="+getUnit.getText().toString()
                    +"&street="+getStreet.getText().toString()
                    +"&postal="+getPostal.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if (status == 200) {
                    JSONArray profiles = result.getJSONArray("result");
                    JSONObject user = profiles.getJSONObject(0);
                    sharedPreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_image", user.getString("photo"));
                    editor.putString("user_first_name", user.getString("first_name"));
                    editor.putString("user_last_name", user.getString("last_name"));
                    editor.putString("user_name", user.getString("first_name") + " " + user.getString("last_name"));
                    editor.putString("user_full_name", user.getString("full_name"));
                    editor.putString("user_email", user.getString("email"));
                    editor.putString("user_contact", user.getString("contact_number"));
                    editor.putString("user_address", user.getString("address"));
                    editor.apply();
                    Toast.makeText(FacebookAddDetailsActivity.this, "Profile updated!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FacebookAddDetailsActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else {
                    Toast.makeText(FacebookAddDetailsActivity.this, "Unable to update details!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void HideProgress() {
        progress.setVisibility(View.GONE);
    }

    public void ShowProgress() {
        progress.setVisibility(View.VISIBLE);
    }

}
