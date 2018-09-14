package com.greenravolution.gravoapp.contents;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.greenravolution.gravoapp.R;

import java.util.Objects;

public class ActivitySuccessfullTransaction extends AppCompatActivity {

    Toolbar toolbar;
    TextView date;
    LottieAnimationView tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfull_transaction);
        toolbar = findViewById(R.id.toolbar);
        tick = findViewById(R.id.tick);
        tick.setAnimation(R.raw.tick);
        tick.playAnimation();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        date = findViewById(R.id.date);
        Intent intent = getIntent();
        date.setText(String.format("See you on %s", intent.getStringExtra("date")));

        toolbar.setNavigationOnClickListener(v -> finish());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent putintent = new Intent(ActivitySuccessfullTransaction.this, ActivitySelectedTransaction.class);
                int chosenID = Integer.parseInt(intent.getStringExtra("transactionid"));
                Log.i("getTag", chosenID + "");
                putintent.putExtra("intChosenID", chosenID);
                startActivity(putintent);
                finish();
            }
        }, 3000);
    }
}
