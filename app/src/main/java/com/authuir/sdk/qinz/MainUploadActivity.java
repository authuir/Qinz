package com.authuir.sdk.qinz;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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

import java.io.FileNotFoundException;

import me.relex.circleindicator.CircleIndicator;


public class MainUploadActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }

    /**
     * @函数名: onBackPressed
     * @描述: 返回
     * @参数: none
     */
    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    /**
     * @函数名: onClick_Back
     * @描述: 返回
     * @参数: none
     */
    public void onClick_Back(View v)
    {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    /**
     * @函数名: pic_pick
     * @描述: 返回
     * @参数: int i:第i张被选择的图片
     */
    private void pic_pick(int i)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, i);
    }
    public void onClick_select_pic1(View v)
    {
        pic_pick(1);
    }

    public void onClick_select_pic2(View v)
    {
        pic_pick(2);
    }

    public void onClick_select_pic3(View v)
    {
        pic_pick(3);
    }

    /**
     * @函数名: onActivityResult
     * @描述: 选择图片之后处理函数
     * @参数: int requestCode:第x张图片
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 || requestCode == 2 ||requestCode == 3) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            ImageView imageView = (ImageView) findViewById(R.id.goods_pic1);
            switch (requestCode)
            {
                case 1:imageView = (ImageView) findViewById(R.id.goods_pic1);
                    break;
                case 2:imageView = (ImageView) findViewById(R.id.goods_pic2);
                    break;
                case 3:imageView = (ImageView) findViewById(R.id.goods_pic3);
                    break;
            }
            try {
                Bitmap d = BitmapFactory.decodeStream(cr.openInputStream(uri));
                int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
                /* 缩放Bitmap 防止内存不够溢出*/
                imageView.setImageBitmap(scaled);
                /* 释放Bitmap 防止内存不够溢出*/
                if(d != null && !d.isRecycled()) {
                    d.recycle();
                }
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        } else {
            Log.e("uri", "test");
        }
    }
}
