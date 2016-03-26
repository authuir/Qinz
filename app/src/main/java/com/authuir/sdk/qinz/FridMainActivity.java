package com.authuir.sdk.qinz;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

import org.w3c.dom.Text;

import me.relex.circleindicator.CircleIndicator;

/*
*
* 备注：首页的朋友页面
*
*/

public class FridMainActivity extends ActionBarActivity {

    private final Handler handler = new Handler();

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    private Drawable oldBackground = null;
    private int currentColor = 0xFF666666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frid_main);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabstrip);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "     他的物品     ", "        心愿单        ", "    你们的联系   " };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return FridMainFragement.newInstance(position);
        }
    }

    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    public void onClick_Back(View v)
    {
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
}
