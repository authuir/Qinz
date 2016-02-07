package com.authuir.sdk.qinz;

import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

/**
 * ÍøÂç²Ù×÷
 * @author Authuir
 * @Time 2016-02-06 14:49:01
 *
 **/

public class Networks {
    RequestQueue mQueue ;
    public ContentResolver Resolver = null;
    CookieManager manager;

    public Networks(android.content.Context env)
    {
        Resolver = env.getContentResolver();
        mQueue = Volley.newRequestQueue(env);

        // Optionally, you can just use the default CookieManager
        manager = new CookieManager();
        CookieHandler.setDefault(manager);
    }


    public void doPost( String URLS , Response.Listener<String> listener , final Map<String, String> param)
    {
        StringRequest mRequest = new StringRequest(
                Request.Method.POST,URLS,
                listener,
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                return param;
            }
        };
        mQueue.add(mRequest);
    }
}
