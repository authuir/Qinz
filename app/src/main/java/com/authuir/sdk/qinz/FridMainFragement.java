package com.authuir.sdk.qinz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FridMainFragement extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static FridMainFragement newInstance(int position) {
        FridMainFragement f = new FridMainFragement();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if (position == 1 )
            view = inflater.inflate(R.layout.item_frame_pers2, container, false);
        else if (position == 2)
            view = inflater.inflate(R.layout.item_frid_main3, container, false);
        else
            view = inflater.inflate(R.layout.item_frid_main1, container, false);

        return view;
    }

}