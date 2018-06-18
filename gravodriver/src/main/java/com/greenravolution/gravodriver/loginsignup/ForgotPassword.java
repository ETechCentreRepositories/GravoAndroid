package com.greenravolution.gravodriver.loginsignup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.functions.HttpReq;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPassword extends AppCompatActivity {
Button submit;
EditText getEmail;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        submit = findViewById(R.id.submit);
        getEmail = findViewById(R.id.getEmail);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v->finish());
        submit.setOnClickListener(v->{
            if(getEmail.getText().toString().equals("")){
                Toast.makeText(ForgotPassword.this, "You have not entered an email yet!",Toast.LENGTH_SHORT).show();
            }else{
                GetForgotPassword getForgotPassword = new GetForgotPassword();
                getForgotPassword.execute();
            }

        });

    }
    public class GetForgotPassword extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("https://www.greenravolution.com/API/forgetpassword.php","role=collector&email="+getEmail.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("FORGET PASSWORD RESULT:",s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if(status == 200){
                    Toast.makeText(ForgotPassword.this, "An Email has been sent to you!",Toast.LENGTH_SHORT).show();
                }else if(status == 404){
                    Toast.makeText(ForgotPassword.this, "You have not registered as a driver yet!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgotPassword.this, "An unexpected error has occured!",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
