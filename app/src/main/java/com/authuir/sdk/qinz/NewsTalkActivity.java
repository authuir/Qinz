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
import android.widget.ArrayAdapter;
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

    private class MsgBox{
        public String Name;
        public String Detail;
        public MsgBox()
        {
            Name = "";
            Detail = "est";
        }
        public MsgBox(String msg)
        {
            Name = "";
            Detail = msg;
        }
    }

    private TalkAdapter mAdapter ;

    private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };

    private ListView lv;
    private List<MsgBox> mAppList = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_talk);

        mAdapter= new TalkAdapter(this);
        mAppList = new ArrayList<MsgBox>();

        MsgBox data1 = new MsgBox(),data2 = new MsgBox("测试测试测试");

        mAppList.add(data1);
        mAppList.add(data2);

        lv = (ListView) findViewById(R.id.lv);
        lv.setDividerHeight(0);
        lv.setAdapter(mAdapter);
    }

    public void onClick_FridMain(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(NewsTalkActivity.this, FridMainActivity.class);
        NewsTalkActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_PersMain(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(NewsTalkActivity.this, PersActivity.class);
        NewsTalkActivity.this.startActivityForResult(intent, 0);
    }

    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    public void onClickBack(View v) {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick_SendMSG(View v)
    {
        EditText MsgBox = (EditText) findViewById(R.id.msg);
        String msg = MsgBox.getText().toString();
        MsgBox.setText("");
        MsgBox data = new MsgBox(msg);
        mAppList.add(data);
        mAdapter.notifyDataSetChanged();
        Log.d("TAG",msg);
    }

    public class TalkAdapter extends BaseAdapter {

        public TalkAdapter(Context context) {
            super();
        }

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public MsgBox getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),R.layout.item_pers_talk, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            MsgBox item = getItem(position);
            holder.tv_title.setText(mAppList.get(position).Detail);
            return convertView;
        }

        class ViewHolder {
            TextView tv_title;

            public ViewHolder(View view) {
                tv_title = (TextView) view.findViewById(R.id.msgdetail);
                view.setTag(this);
            }

        }
    }
}
