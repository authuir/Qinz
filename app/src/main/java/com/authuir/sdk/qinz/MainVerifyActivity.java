package com.authuir.sdk.qinz;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.relex.circleindicator.CircleIndicator;


public class MainVerifyActivity extends ActionBarActivity {

    TextView btnVerify = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verify);
        btnVerify = (TextView) findViewById(R.id.main_detail_buy);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainVerifyActivity.this, MainCartActivity.class);
                MainVerifyActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    public void onBackPressed() {
        setResult(1);
        Log.e("TAG", "PPP");
        super.onBackPressed();
        finish();
    }

    public void onClick_Cart(View v) {
        Intent intent = new Intent();
        intent.setClass(MainVerifyActivity.this, MainCartActivity.class);
        MainVerifyActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Detail(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainVerifyActivity.this, MainDetailActivity.class);
        MainVerifyActivity.this.startActivityForResult(intent, 0);
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

    public void onClick_Talk(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainVerifyActivity.this, NewsTalkActivity.class);
        MainVerifyActivity.this.startActivityForResult(intent, 0);
    }
}
