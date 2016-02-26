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
     * @方法名: onBackPressed
     * @描述: 返回上一页
     * @参数: none
     */
    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    /**
     * @方法名: onClick_Back
     * @描述: 返回上一页
     * @参数: none
     */
    public void onClick_Back(View v)
    {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    private void pic_pick(int i)
    {
        Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
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

    @Override
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
                /* 将Bitmap设定到ImageView*/
                imageView.setImageBitmap(scaled);
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
