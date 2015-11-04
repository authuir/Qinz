package com.authuir.sdk.qinz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class NewsTalkActivity extends ActionBarActivity {

    private class TalkList{
        public String SpeakerName;
        public String TalkDetail;
        public TalkList()
        {
            SpeakerName = "立正";
            TalkDetail = "约约约";
        }
        public TalkList(String title,String detail)
        {
            SpeakerName = title;
            TalkDetail = detail;
        }
    }

    private ListView mListView ;
    private List<TalkList> mAppList ;
    private AppAdapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_talk);
/*
        mListView = (ListView) findViewById(R.id.Talklistview);
        mAppList = new ArrayList<TalkList>();
        mAdapter= new AppAdapter(this);

        TalkList data1 = new TalkList(),data2 = new TalkList();

        mAppList.add(data1);
        mAppList.add(data2);

        mListView.setAdapter(mAdapter);*/
    }

    public void onBackPressed() {
        super.onBackPressed();
        //System.out.println("按下了back键   onBackPressed()");
        Intent intent = new Intent();
        intent.setClass(NewsTalkActivity.this, MainActivity.class);
        NewsTalkActivity.this.startActivity(intent);
        NewsTalkActivity.this.finish();
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

    public void onClick_SendMSG(View v)
    {
        EditText MsgBox = (EditText) findViewById(R.id.msg);
        String msg = MsgBox.getText().toString();
        Log.d("TAG",msg);
    }

    public class AppAdapter extends BaseAdapter {

        public AppAdapter(Context context) {
            super();
        }

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public TalkList getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),R.layout.item_list_cart, null);
            }
            TalkList item = getItem(position);
            return convertView;
        }
    }
}
