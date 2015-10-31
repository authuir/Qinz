package com.authuir.sdk.qinz;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDialog;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class MainCartActivity extends ActionBarActivity {

    private class CartList{
        public String GoodsName;
        public Drawable GoodsImg;
        public String GoodsDetail;
        private String GoodsID;
        private Resources Res;
        public CartList()
        {
            Res = Resources.getSystem();
            GoodsName = "新学期书单推荐";
            GoodsDetail = "新学期视觉传达专业教材教参推荐";
            GoodsImg = MainCartActivity.this.getResources().getDrawable(R.drawable.main_head1);
        }
        public CartList(String title,String detail,int icon_id)
        {
            Res = Resources.getSystem();
            GoodsName = title;
            GoodsDetail = detail;
            GoodsImg = MainCartActivity.this.getResources().getDrawable(icon_id);
        }
    }

    private SwipeMenuListView mListView ;
    private List<CartList> mAppList ;
    private AppAdapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cart);

        mListView = (SwipeMenuListView) findViewById(R.id.cartlistview);
        mAppList = new ArrayList<CartList>();
        mAdapter= new AppAdapter(this);

        CartList data1 = new CartList(),data2 = new CartList();

        mAppList.add(data1);
        mAppList.add(data2);

        mListView.setAdapter(mAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set item title
                deleteItem.setTitle("删除");
                // set item title fontsize
                deleteItem.setTitleSize(20);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                //deleteItem.setIcon(R.drawable.main_icon1);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };



        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                CartList item = mAppList.get(position);
                switch (index) {
                    case 0:
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        // delete
                        mAppList.remove(position);
                        break;
                }
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // set MenuStateChangeListener
        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });

        // test item long click
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        // set creator
        mListView.setMenuCreator(creator);

    }

    public void onBackPressed() {
        super.onBackPressed();
        //System.out.println("按下了back键   onBackPressed()");
        Intent intent = new Intent();
        intent.setClass(MainCartActivity.this, MainActivity.class);
        MainCartActivity.this.startActivity(intent);
        MainCartActivity.this.finish();
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

    public class AppAdapter extends BaseAdapter {

        public AppAdapter(Context context) {
            super();
        }

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public CartList getItem(int position) {
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
            CartList item = getItem(position);
            return convertView;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
