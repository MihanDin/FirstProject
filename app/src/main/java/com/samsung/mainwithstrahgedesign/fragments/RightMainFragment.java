package com.samsung.mainwithstrahgedesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.db.MyConstants;


public class RightMainFragment extends Fragment {
    private ImageFilterButton left_main,right,left;
    private SharedPreferences pref;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_main, null, false);
        left = view.findViewById(R.id.rulerbtn);
        left_main= view.findViewById(R.id.runnerbtn);
        right= view.findViewById(R.id.weightbtn);
        pref = getContext().getSharedPreferences(BtConsts.JUMP_KEY, Context.MODE_PRIVATE);
        TextView right_main_tv=view.findViewById(R.id.right_main_tv);
        String formattedDouble = String.format("%.2f", pref.getInt(MyConstants.JUMP_NUMBER,71)*0.01*75*10/4.2);
        right_main_tv.setText(" Сожжено: "+"\n "+formattedDouble+" кaлорий");
        Log.d("TEXTdeb",String.valueOf(pref.getInt(MyConstants.JUMP_NUMBER,71)));

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftFragment()).commit();
            }
        });
        left_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftMainFragment()).commit();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new RightFragment()).commit();
            }
        });
        return view;


    }
}