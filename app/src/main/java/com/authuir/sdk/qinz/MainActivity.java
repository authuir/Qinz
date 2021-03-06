package com.authuir.sdk.qinz;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ImageView mIcon1 = null,mIcon2 = null,mIcon3 = null;
    private ImageView mIconmenu = null;
    private ImageView mBookIcon1 = null;
    private ImageView mBookIcon2 = null;

    private com.authuir.sdk.fabbtn.FloatingActionButton mReque,mSell;

    private ScrollView mainView = null;

    private ResideLayout residelout = null;
    private LinearLayout persLayout = null;
    private LinearLayout newsLayout = null;
    private LinearLayout fridLayout = null;

    private TabHost mTabHost = null;
    private TabWidget mTabWidget = null;

    private ImageView mImgleft = null,mImgright = null;
    private TextView mTextleft = null,mTextright = null;

    private QinzMenuList qml = null;

    private SwipeMenuListView mListView ;
    private List<CartList> mAppList ;
    private newsAdapter mAdapter ;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    private class CartList{
        public String GoodsName;
        public Drawable GoodsImg;
        public String GoodsDetail;
        private String GoodsID;
        private Resources Res;
        //private java.lang.class
        public CartList()
        {
            Res = Resources.getSystem();
            GoodsName = "新学期书单推荐";
            GoodsDetail = "新学期视觉传达专业教材教参推荐";
            GoodsImg = MainActivity.this.getResources().getDrawable(R.drawable.main_head1);
        }
        public CartList(String title,String detail,int icon_id)
        {
            Res = Resources.getSystem();
            GoodsName = title;
            GoodsDetail = detail;
            GoodsImg = MainActivity.this.getResources().getDrawable(icon_id);
        }
    }

    private void InitVal(){

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabstrip);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

        persLayout = (LinearLayout) findViewById(R.id.pers);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        persLayout.setLayoutParams(params);

        newsLayout = (LinearLayout) findViewById(R.id.news);
        newsLayout.setLayoutParams(params);

        fridLayout = (LinearLayout) findViewById(R.id.frid);
        fridLayout.setLayoutParams(params);

        mIcon1 = (ImageView) findViewById(R.id.main_icon1);
        mIcon2 = (ImageView) findViewById(R.id.main_icon2);
        mIcon3 = (ImageView) findViewById(R.id.main_icon3);
        mIconmenu = (ImageView) findViewById(R.id.main_menuicon);
        mBookIcon1 = (ImageView) findViewById(R.id.main_book1);
        residelout = (ResideLayout) findViewById(R.id.reside_layout);

        mAppList = new ArrayList<CartList>();

        mReque = (com.authuir.sdk.fabbtn.FloatingActionButton) findViewById(R.id.action_a);
        mSell = (com.authuir.sdk.fabbtn.FloatingActionButton) findViewById(R.id.action_b);

        mReque.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainRequestActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });

        mSell.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent intent = new Intent();
                 intent.setClass(MainActivity.this, MainUploadActivity.class);
                 //intent.setClass(MainActivity.this, MainUploadActivity.class);
                 MainActivity.this.startActivityForResult(intent, 0);
             }
        });

        CartList f1 = new CartList("新活动推荐","新学期电子信息系教材推荐",R.drawable.main_hot_head2);
        CartList f2 = new CartList("好消息","50G硬盘免费领取",R.drawable.main_hot_head1);
        CartList f3 = new CartList();

        mListView = (SwipeMenuListView) findViewById(R.id.news_goodslistview);
        mAppList.add(f1);
        mAppList.add(f2);
        mAppList.add(f3);
        mAdapter= new newsAdapter(this);

        mListView.setAdapter(mAdapter);

        qml = new QinzMenuList();

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
            deleteItem.setWidth(dp2px(90));
            deleteItem.setTitle("删除");
            deleteItem.setTitleSize(20);
            deleteItem.setTitleColor(Color.WHITE);
            menu.addMenuItem(deleteItem);
            }
        };

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                CartList item = mAppList.get(position);
                switch (index) {
                    case 0:
                        //mAppList.remove(position);
                        //mAppList
                        Log.d("TAG", "Init OK."+position);
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        Log.d("TAG", "Init 2."+position);
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewsBooklistActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });

        // set creator
        mListView.setMenuCreator(creator);


        mIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
                //MainActivity.this.finish();
            }
        });
        mIcon2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivityForResult(intent,0);
                //MainActivity.this.finish();
            }
        });
        mIcon3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
                intent.setClass(MainActivity.this, MainHotActivity.class);
                MainActivity.this.startActivityForResult(intent,0);
                //MainActivity.this.finish();
            }
        });
        mIconmenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("TAG","book click");
                residelout.smoothSlideTo(1.f);
            }
        });
        mBookIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainDetailActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
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
        Intent intent = getIntent();
        String name="FROM:"+intent.getStringExtra("from");
        String BACK = intent.getStringExtra("from");
        Log.e("TAG", name);
        if (BACK!=null)
            Log.e("TAG",BACK);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

        //使能pers
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        persLayout.setLayoutParams(params);

        //禁用news,frid
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        newsLayout.setLayoutParams(params);
        fridLayout.setLayoutParams(params);

        qml.setView(3);
        Log.d("TAG","Pers");
    }

    public void onClick_ML_newsview(View v)
    {
        mainView.setVisibility(View.INVISIBLE);

        //使能news
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        newsLayout.setLayoutParams(params);

        //禁用pers,frid
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        persLayout.setLayoutParams(params);
        fridLayout.setLayoutParams(params);

        qml.setView(2);
        Log.d("TAG", "News");
    }

    public void onClick_ML_fridview(View v)
    {
        mainView.setVisibility(View.INVISIBLE);

        //使能news
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fridLayout.setLayoutParams(params);

        //禁用pers,frid
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        persLayout.setLayoutParams(params);
        newsLayout.setLayoutParams(params);

        qml.setView(1);
        Log.d("TAG", "Frid");
    }

    public void onClick_ML_mainview(View v)
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        persLayout.setLayoutParams(params);
        newsLayout.setLayoutParams(params);
        fridLayout.setLayoutParams(params);

        mainView.setVisibility(View.VISIBLE);
        qml.setView(0);
        Log.d("TAG", "Main");
    }
    public void onClick_news_frid(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, NewsTalkActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }
    public void onClick_act1(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, PersAct1Activity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }
    public void onClick_Talk(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, NewsTalkActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }
    public void onClick_act2(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, PersAct2Activity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }
    public void onClick_act3(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, PersAct3Activity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }
    public void onClick_BookDetail(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, MainDetailActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }
    public void onClick_Search(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, MainSearchActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Setting(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SettingActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Frid_main(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, FridMainActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_cat2(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainCat2.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Hot(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, MainHotActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_sell(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, MainUploadActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Menu(View v)
    {
        Log.d("TAG", "book click");
        residelout.smoothSlideTo(1.f);
    }

    public void onClick_Order(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, PersOrderActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public void onClick_Cart(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("from", "com.authuir.sdk.qinz.MainActivity");
        intent.setClass(MainActivity.this, MainCartActivity.class);
        MainActivity.this.startActivityForResult(intent, 0);
    }

    public class newsAdapter extends BaseAdapter {
        private Context context;//用于接收传递过来的Context对象

        public newsAdapter(Context context) {
            super();
            this.context = context;
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
                convertView = View.inflate(getApplicationContext(),R.layout.item_list_news, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            CartList item = getItem(position);
            holder.tv_title.setText(mAppList.get(position).GoodsName);
            holder.tv_detail.setText(mAppList.get(position).GoodsDetail);
            holder.tv_icon.setImageDrawable(mAppList.get(position).GoodsImg);
            return convertView;
        }

        class ViewHolder {
            ImageView tv_icon;
            TextView tv_title;
            TextView tv_detail;

            public ViewHolder(View view) {
                tv_icon = (ImageView) view.findViewById(R.id.news_goodsicon);
                tv_title = (TextView) view.findViewById(R.id.news_goodsname);
                tv_detail = (TextView) view.findViewById(R.id.news_goodsdetail);
                view.setTag(this);
            }
        }
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

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "     我的物品     ", "        心愿单        ", "     任务活动     " };
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
            return SuperAwesomeCardFragment.newInstance(position);
        }
    }
}
