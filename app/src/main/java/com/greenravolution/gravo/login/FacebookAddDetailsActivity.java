package com.greenravolution.gravo.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookAddDetailsActivity extends AppCompatActivity {
    EditText getFN, getLN, getContact;
    TextView tvaddress;
    TextView getEmail;
    Button completeProfile;
    SharedPreferences sharedPreferences;
    public static final String SESSION = "login_status";
    LinearLayout progress,getaddresslayout;
    String getUnits;
    private PlaceAutocompleteFragment placeAutocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_add_details);
        sharedPreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getFN = findViewById(R.id.first_name);
        getFN.setText(sharedPreferences.getString("user_first_name", ""));
        getLN = findViewById(R.id.last_name);
        getLN.setText(sharedPreferences.getString("user_last_name", ""));
        getContact = findViewById(R.id.getPhone);

        getUnits = sharedPreferences.getString("user_address_unit", "");

        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        getaddresslayout = findViewById(R.id.getaddresslayout);
        getaddresslayout.setVisibility(View.GONE);

        tvaddress = findViewById(R.id.tvaddress);
        tvaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getaddresslayout.getVisibility()==View.GONE){
                    getaddresslayout.setVisibility(View.VISIBLE);
                }else{
                    getaddresslayout.setVisibility(View.GONE);
                }
            }
        });

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setCountry("SG").build();
        placeAutocompleteFragment.setFilter(autocompleteFilter);
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                tvaddress.setText(place.getAddress().toString());
                placeAutocompleteFragment.setText("");
                getaddresslayout.setVisibility(View.GONE);

            }

            @Override
            public void onError(Status status) {
            }
        });

        progress = findViewById(R.id.progressbar);
        getContact.setText(sharedPreferences.getString("user_contact", ""));
        getEmail = findViewById(R.id.newEmail);
        getEmail.setText(sharedPreferences.getString("user_email", ""));
        completeProfile = findViewById(R.id.complete);
        completeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateItems()) {
                    UpdateDetails updateDetails = new UpdateDetails();
                    updateDetails.execute();
                } else {
                    Toast.makeText(FacebookAddDetailsActivity.this, "Please fill in all your details!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean validateItems() {
        return !getFN.getText().toString().equalsIgnoreCase("")
                && !getLN.getText().toString().equalsIgnoreCase("")
                && !getContact.getText().toString().equalsIgnoreCase("")
                && !getEmail.getText().toString().equalsIgnoreCase("");
    }

    public class UpdateDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpReq req = new HttpReq();
            sharedPreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            return req.PostRequest("http://ehostingcentre.com/gravo/updateuserdetails.php"
                    , "userid="
                            + sharedPreferences.getInt("user_id", -1)
                            + "&firstname=" + getFN.getText().toString()
                            + "&lastname=" + getLN.getText().toString()
                            + "&email=" + getEmail.getText().toString()
                            + "&contactnumber=" + getContact.getText().toString()
                            + "&address=" + tvaddress.getText().toString());
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
                    editor.putString("user_address_block", user.getString("block"));
                    editor.putString("user_address_unit", user.getString("unit"));
                    editor.putString("user_address_street", user.getString("street"));
                    editor.putString("user_address_postal", user.getString("postal"));
                    editor.apply();
                    Toast.makeText(FacebookAddDetailsActivity.this, "Profile updated!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FacebookAddDetailsActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else {
                    Toast.makeText(FacebookAddDetailsActivity.this, "Unable to update details!", Toast.LENGTH_LONG).show();
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
