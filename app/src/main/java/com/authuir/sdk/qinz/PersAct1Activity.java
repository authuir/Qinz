package com.authuir.sdk.qinz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PersAct1Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pers_act1);
    }

    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick_Back(View v)
    {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    public void onClick_Buy(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(PersAct1Activity.this, MainUploadActivity.class);
        PersAct1Activity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Sell(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(PersAct1Activity.this, MainRequestActivity.class);
        PersAct1Activity.this.startActivityForResult(intent, 0);
    }

}
