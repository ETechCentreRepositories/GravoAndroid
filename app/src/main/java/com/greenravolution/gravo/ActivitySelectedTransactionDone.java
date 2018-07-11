package com.greenravolution.gravo;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.greenravolution.gravo.contents.ActivityHelp;

public class ActivitySelectedTransactionDone extends AppCompatActivity {
    Toolbar toolbar;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_transaction_done);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        title = findViewById(R.id.transaction_title);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

            switch (itemId) {
                case R.id.help:
                    Log.i("MainActivity", "clicked on help");
                    startActivity(new Intent(this, ActivityHelp.class));
                    return true;
        }
        return false;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.needhelp_menu, menu);//Menu Resource, Menu
        return true;
    }
}
