package com.authuir.sdk.qinz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


public class MainHotActivity extends ActionBarActivity {
    private TabHost mTabHost = null;
    private TabWidget mTabWidget = null;
    private ImageView mReqHead = null;

    private ImageView mImgleft = null,mImgright = null;
    private TextView mTextleft = null,mTextright = null;

    private String BackActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hotbook);

        Intent intent = getIntent();
        BackActivity = intent.getStringExtra("from");
        if (BackActivity!=null)
            Log.e("TAG",BackActivity);

        mReqHead = (ImageView) findViewById(R.id.request1);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mImgleft = (ImageView) super.findViewById(R.id.main_hot_left);
        mImgright = (ImageView) super.findViewById(R.id.main_hot_right);
        mTextleft = (TextView) super.findViewById(R.id.main_hottext_left);
        mTextright = (TextView) super.findViewById(R.id.main_hottext_right);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mImgleft = (ImageView) super.findViewById(R.id.main_hot_left);
        mImgright = (ImageView) super.findViewById(R.id.main_hot_right);
        mTextleft = (TextView) super.findViewById(R.id.main_hottext_left);
        mTextright = (TextView) super.findViewById(R.id.main_hottext_right);

        mImgleft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mImgleft.setImageResource(R.drawable.main_hot_leftfocus);
                mImgright.setImageResource(R.drawable.main_hot_rightunfocus);
                mTextright.setTextColor(Color.parseColor("#727272"));
                mTextleft.setTextColor(Color.parseColor("#01CA97"));
                mTabHost.setCurrentTab(0);
            }
        });

        mImgright.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mImgleft.setImageResource(R.drawable.main_hot_leftunfocus);
                mImgright.setImageResource(R.drawable.main_hot_rightfocus);
                mTextleft.setTextColor(Color.parseColor("#727272"));
                mTextright.setTextColor(Color.parseColor("#01CA97"));
                mTabHost.setCurrentTab(1);
            }
        });

        mReqHead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainHotActivity.this, MainReqDetailActivity.class);
                MainHotActivity.this.startActivity(intent);
                MainHotActivity.this.finish();
            }
        });

        mTabHost.setup();
        mTabWidget = mTabHost.getTabWidget();
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setContent(
                R.id.tabcnt1).setIndicator("Tab1"));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setContent(
                R.id.tabcnt2).setIndicator("Tab2"));
        mTabWidget.setVisibility(View.INVISIBLE);
        mTabHost.setCurrentTab(0);
    }


    public void onBackPressed() {
        Log.e("TAG", "BACK");
        setResult(1);
        super.onBackPressed();
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("TAG", "Catch");
    }


    public void onClick_Detail(View v)
    {
        Log.e("TAG", "CLICK");
        Intent intent = new Intent();
        intent.setClass(MainHotActivity.this, MainDetailActivity.class);
        MainHotActivity.this.startActivityForResult(intent, 0);
        //MainHotActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick_Back(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainHotActivity.this,MainActivity.class);
        MainHotActivity.this.startActivity(intent);
        MainHotActivity.this.finish();
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
}
