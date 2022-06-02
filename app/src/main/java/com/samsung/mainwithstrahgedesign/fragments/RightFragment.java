package com.samsung.mainwithstrahgedesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.db.MyConstants;

public class RightFragment extends Fragment {
    private ImageFilterButton left_main,right_main,left;
    private SharedPreferences pref;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null, false);
        left = view.findViewById(R.id.rulerbtn);
        right_main= view.findViewById(R.id.energybtn);
        left_main= view.findViewById(R.id.runnerbtn);
        pref = getContext().getSharedPreferences(BtConsts.JUMP_KEY, Context.MODE_PRIVATE);
        TextView right_tv=view.findViewById(R.id.right_tv);
        String formattedDouble = String.format("%.1f", pref.getInt(MyConstants.JUMP_NUMBER,71)*0.01*75*10);

        right_tv.setText(" Затрачено: "+"\n "+formattedDouble+" Дж");

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftFragment()).commit();
            }
        });
        right_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new RightMainFragment()).commit();
            }
        });
        left_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftMainFragment()).commit();
            }
        });
        return view;


    }
}