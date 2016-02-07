package com.authuir.sdk.qinz;

import android.content.ContentResolver;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class LoginActivity extends ActionBarActivity {

    public ContentResolver Resolver = null;
    private Networks mNetworkHandle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mNetworkHandle = new Networks(this);
    }

    public void onBackPressed() {
        setResult(1);
        super.onBackPressed();
        finish();
    }

    public void onClick_Main(View v)
    {
        final Intent intent=new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);

        Response.Listener ll =  new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject jsonArray = new JSONObject(String.valueOf(response));
                    if (jsonArray.getString("status").equals("1"))                          //验证成功
                    {
                        Log.d("TAG", "logged in");
                        Toast.makeText(getApplicationContext(), "登录成功",Toast.LENGTH_SHORT).show();
                        //this.startActivity(intent);
                        LoginActivity.this.startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "密码错误",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();                                                    //抛出json解析异常
                }
            }
        };
        String serialnum = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class );
            serialnum = (String)(   get.invoke(c, "ro.serialno", "unknown" )  );
        }
        catch (Exception ignored)
        {
        }
        Map<String, String> map = new HashMap<String, String>();
        EditText textusername =(EditText)findViewById(R.id.username);
        EditText textpassword =(EditText)findViewById(R.id.password);
        /*map.put("username", "201211010231");
        map.put("password", "xxx");*/

        map.put("username", textusername.getText().toString());
        map.put("password", textpassword.getText().toString());
        map.put("ssid", serialnum);

        Log.d("AUT",map.toString());

        mNetworkHandle.doPost("http://qinz.qnxg.net/ci/index.php/Login/log", ll, map);

        //mQueue.add(AuthRequest);
        //this.startActivity(intent);
        //this.finish();
    }
}
