package com.authuir.sdk.qinz;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;


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
     * @函数名: onClick_Upload
     * @描述: 上传
     * @参数: none
     */
    public void onClick_Upload(View v)
    {

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
            Log.e("uri", this.getRealFilePath(this, uri));

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
    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
