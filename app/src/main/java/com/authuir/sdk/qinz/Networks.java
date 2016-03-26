package com.authuir.sdk.qinz;

import android.content.ContentResolver;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Map;

/**
 * 网络操作类
 * @author Authuir
 * @Time 2016-02-06 14:49:01
 *
 **/

public class Networks {
    RequestQueue mQueue ;
    public ContentResolver Resolver = null;
    CookieManager manager;

    /**
     * @方法: Networks
     * @功能: 构造方法
     * @参数: android.content.Context env：要加载网络功能的Context
     */
    public Networks(android.content.Context env)
    {
        Resolver = env.getContentResolver();
        mQueue = Volley.newRequestQueue(env);

        // Optionally, you can just use the default CookieManager
        manager = new CookieManager();
        CookieHandler.setDefault(manager);
    }

    /**
     * @方法: doPost
     * @功能: 发送Post请求
     * @参数: String URLS：POST请求URL
     *         Response.Listener<String> listener：请求完毕的回调方法
     *         final Map<String, String> param：POST请求参数
     */
    public void doPost( String URLS , Response.Listener<String> listener , final Map<String, String> param)
    {
        StringRequest mRequest = new StringRequest(
                Request.Method.POST,URLS,
                listener,
                /*new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                }*/
                null

        )
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                return param;
            }
        };
        mQueue.add(mRequest);
    }
}
