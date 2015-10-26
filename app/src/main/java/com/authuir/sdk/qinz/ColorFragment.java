package com.authuir.sdk.qinz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorFragment extends Fragment {

    private static final String ARG_COLOR = "color";
    private static final String ARG_ID = "img_detailid";

    private int mColor;
    private int local_ID;

    public static ColorFragment newInstance(int param1) {
        ColorFragment fragment = new ColorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static ColorFragment newInstance(int param1,int param2) {
        ColorFragment fragment = new ColorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, param1);
        args.putInt(ARG_ID, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ColorFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getInt(ARG_COLOR);
            local_ID = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_fragment, container, false);

        //v.setBackgroundColor(mColor);
        switch (local_ID)
        {
            case 0:v.setBackgroundResource(R.drawable.booklist1);break;
            case 1:v.setBackgroundResource(R.drawable.booklist2);break;
            case 2:v.setBackgroundResource(R.drawable.booklist3);break;
            case 3:v.setBackgroundResource(R.drawable.booklist4);break;
            default:v.setBackgroundResource(R.drawable.booklist1);break;
        }


        return v;
    }
}
