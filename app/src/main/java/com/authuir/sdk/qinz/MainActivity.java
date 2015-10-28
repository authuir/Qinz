package com.authuir.sdk.qinz;

import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends ActionBarActivity {
    private ImageView mIcon1 = null,mIcon2 = null;
    private ImageView mIconmenu = null;
    private ImageView mBookIcon1 = null;
    private ImageView mBookIcon2 = null;

    private void InitVal()
    {
        mIcon1 = (ImageView) findViewById(R.id.main_icon1);
        mIconmenu = (ImageView) findViewById(R.id.main_menuicon);
        mBookIcon1 = (ImageView) findViewById(R.id.main_book1);
        mIcon2 = (ImageView) findViewById(R.id.main_icon2);

        mIcon2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DirtActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

        mIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

        mIconmenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("TAG","book click");
                Intent intent = new Intent();
                intent.putExtra("name", "LeiPei");
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
        mBookIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainDetailActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitVal();
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
}
