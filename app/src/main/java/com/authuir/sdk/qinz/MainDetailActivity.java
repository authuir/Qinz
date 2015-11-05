package com.authuir.sdk.qinz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDialog;
import android.os.Bundle;
import android.text.Layout;
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


public class MainDetailActivity extends ActionBarActivity {

    private TextView buybtn = null;
    private String BackActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        Intent intent = getIntent();
        BackActivity = intent.getStringExtra("from");
        if (BackActivity!=null)
            Log.e("TAG",BackActivity);

        //设置滚动效果
        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        DemoPagerAdapter defaultPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
        defaultViewpager.setAdapter(defaultPagerAdapter);
        defaultIndicator.setViewPager(defaultViewpager);

        buybtn = (TextView) findViewById(R.id.main_detail_buy);
        buybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
                Log.e("TAG", "PPP");
                intent.setClass(MainDetailActivity.this, MainVerifyActivity.class);
                MainDetailActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    public void onBackPressed() {
        Log.e("TAG", "BACK");
        setResult(1);
        super.onBackPressed();
        finish();
        //System.out.println("按下了back键   onBackPressed()");
        /*Intent intent = new Intent();
        if (BackActivity!=null) {
            Log.e("TAG",BackActivity);
            intent.setClassName(MainDetailActivity.this, BackActivity);
        }
        else
            intent.setClass(MainDetailActivity.this, MainActivity.class);
        MainDetailActivity.this.startActivity(intent);*/

        //MainDetailActivity.this.finish();
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

    public void onClick_Talk(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainDetailActivity.this, NewsTalkActivity.class);
        MainDetailActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Cart(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainDetailActivity.this, MainCartActivity.class);
        MainDetailActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Buy(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        Log.e("TAG","PPP");
        intent.setClass(MainDetailActivity.this, MainVerifyActivity.class);
        MainDetailActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Back(View v)
    {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("TAG", "Catch");
    }

}
