package com.authuir.sdk.qinz;

import com.authuir.sdk.fabbtn.FloatingActionButton;
import com.authuir.sdk.fabbtn.FloatingActionsMenu;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends ActionBarActivity {

    enum qmld {
        lmain, lfrid, lnews, lpers
    }

    private ImageView mIcon1 = null,mIcon2 = null,mIcon3 = null;
    private ImageView mIconmenu = null;
    private ImageView mBookIcon1 = null;
    private ImageView mBookIcon2 = null;

    private ScrollView mainView = null;

    private ResideLayout residelout = null;

    private TabHost mTabHost = null;
    private TabWidget mTabWidget = null;

    private ImageView mImgleft = null,mImgright = null;
    private TextView mTextleft = null,mTextright = null;

    private QinzMenuList qml = null;


    private void InitVal()
    {
        mIcon1 = (ImageView) findViewById(R.id.main_icon1);
        mIcon2 = (ImageView) findViewById(R.id.main_icon2);
        mIcon3 = (ImageView) findViewById(R.id.main_icon3);
        mIconmenu = (ImageView) findViewById(R.id.main_menuicon);
        mBookIcon1 = (ImageView) findViewById(R.id.main_book1);
        residelout = (ResideLayout) findViewById(R.id.reside_layout);

        qml = new QinzMenuList();



        mIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
        mIcon2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainFloatingButtonActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
        mIcon3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewsActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });
        mIconmenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("TAG","book click");
                /*Intent intent = new Intent();
                intent.putExtra("name", "LeiPei");
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();*/
                residelout.smoothSlideTo(1.f);
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



        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mImgleft = (ImageView) super.findViewById(R.id.main_hot_left);
        mImgright = (ImageView) super.findViewById(R.id.main_hot_right);
        mTextleft = (TextView) super.findViewById(R.id.main_hottext_left);
        mTextright = (TextView) super.findViewById(R.id.main_hottext_right);

        mImgleft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mImgleft.setImageResource(R.drawable.main_hot_leftfocus);
                mImgright.setImageResource(R.drawable.main_hot_right);
                mTextleft.setTextColor(Color.parseColor("#727272"));
                mTextright.setTextColor(Color.parseColor("#01CA97"));
                mTabHost.setCurrentTab(0);
            }
        });

        mImgright.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mImgleft.setImageResource(R.drawable.main_hot_left);
                mImgright.setImageResource(R.drawable.main_hot_rightfocus);
                mTextright.setTextColor(Color.parseColor("#727272"));
                mTextleft.setTextColor(Color.parseColor("#01CA97"));
                mTabHost.setCurrentTab(1);
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

        mainView = (ScrollView) findViewById(R.id.mainview);

        Log.d("TAG", "Init OK.");
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

    public void onClick_ML_persview(View v)
    {
        mainView.setVisibility(View.INVISIBLE);
        qml.setView(3);
    }

    public void onClick_ML_newsview(View v)
    {
        mainView.setVisibility(View.INVISIBLE);
        qml.setView(2);
    }

    public void onClick_ML_fridview(View v)
    {
        mainView.setVisibility(View.INVISIBLE);
        qml.setView(1);
    }

    public void onClick_ML_mainview(View v)
    {
        mainView.setVisibility(View.VISIBLE);
        qml.setView(0);
    }

    private class QinzMenuList{

        private ImageView Mainview_icon;
        private TextView Mainview_text;
        private LinearLayout Mainview_lout;

        private ImageView Newsview_icon;
        private TextView Newsview_text;
        private LinearLayout Newsview_lout;

        private ImageView Persview_icon;
        private TextView Persview_text;
        private LinearLayout Persview_lout;

        private ImageView Fridview_icon;
        private TextView Fridview_text;
        private LinearLayout Fridview_lout;



        Resources res;

        public QinzMenuList()
        {

            Mainview_icon = (ImageView) findViewById(R.id.mainview_icon);
            Mainview_text = (TextView) findViewById(R.id.mainview_text);
            Mainview_lout = (LinearLayout) findViewById(R.id.mainview_lout);

            Newsview_icon = (ImageView) findViewById(R.id.newsview_icon);
            Newsview_text = (TextView) findViewById(R.id.newsview_text);
            Newsview_lout = (LinearLayout) findViewById(R.id.newsview_lout);

            Persview_icon = (ImageView) findViewById(R.id.persview_icon);
            Persview_text = (TextView) findViewById(R.id.persview_text);
            Persview_lout = (LinearLayout) findViewById(R.id.persview_lout);

            Fridview_icon = (ImageView) findViewById(R.id.fridview_icon);
            Fridview_text = (TextView) findViewById(R.id.fridview_text);
            Fridview_lout = (LinearLayout) findViewById(R.id.fridview_lout);

            res = getResources();
        }

        public void setView(int MenuID)
        {
            switch (MenuID)
            {
                case 0:setMainview();unsetNewsview();unsetFridview();unsetPersview();break;
                case 2:setNewsview();unsetMainview();unsetFridview();unsetPersview();break;
                case 1:setFridview();unsetMainview();unsetNewsview();unsetPersview();break;
                case 3:setPersview();unsetMainview();unsetFridview();unsetNewsview();break;
            }
            residelout.smoothSlideTo(0.f);
        }

        private void setNewsview()
        {
            setMenuview(Newsview_icon,Newsview_text,Newsview_lout);
            Newsview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon3_rev));
        }

        private void unsetNewsview()
        {
            unsetMenuview(Newsview_icon,Newsview_text,Newsview_lout);
            Newsview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon3));
        }

        private void setPersview()
        {
            setMenuview(Persview_icon,Persview_text,Persview_lout);
            Persview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon4_rev));
        }

        private void unsetPersview()
        {
            unsetMenuview(Persview_icon,Persview_text,Persview_lout);
            Persview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon4));
        }

        private void setMainview()
        {
            setMenuview(Mainview_icon,Mainview_text,Mainview_lout);
            Mainview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon1_rev));
        }

        private void unsetMainview()
        {
            unsetMenuview(Mainview_icon, Mainview_text, Mainview_lout);
            Mainview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon1));
        }

        private void setFridview()
        {
            setMenuview(Fridview_icon,Fridview_text,Fridview_lout);
            Fridview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon2_rev));
        }

        private void unsetFridview()
        {
            unsetMenuview(Fridview_icon, Fridview_text, Fridview_lout);
            Fridview_icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon2));
        }


        private void setMenuview(ImageView icon,TextView text,LinearLayout lout)
        {

            //icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon1));
            text.setTextColor(res.getColor(R.color.pwhite));
            lout.setBackgroundColor(res.getColor(R.color.qinz));
        }

        private void unsetMenuview(ImageView icon,TextView text,LinearLayout lout)
        {
            //icon.setImageDrawable(res.getDrawable(R.drawable.dirt_icon1));
            text.setTextColor(res.getColor(R.color.qinz));
            lout.setBackgroundColor(res.getColor(R.color.dirtbg));
        }

    }
}
