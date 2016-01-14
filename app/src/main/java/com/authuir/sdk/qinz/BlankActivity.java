package com.authuir.sdk.qinz;

import android.content.Intent;  
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;



public class BlankActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
    }

    public void onBackPressed() {
        super.onBackPressed();
        //System.out.println("按下了back键   onBackPressed()");
        Intent intent = new Intent();
        intent.setClass(BlankActivity.this, MainActivity.class);
        BlankActivity.this.startActivity(intent);
        BlankActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
