package com.samsung.mainwithstrahgedesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.db.MyConstants;
import com.samsung.mainwithstrahgedesign.db.MyDbManager;
import com.samsung.mainwithstrahgedesign.db.Obrabotchik;

import java.util.ArrayList;

public class RightFragment extends Fragment {
    private ImageFilterButton left_main,right_main,left;
    private SharedPreferences pref;
    private SharedPreferences prfs;
    private MyDbManager myDbManager;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null, false);
        left = view.findViewById(R.id.rulerbtn);
        right_main= view.findViewById(R.id.energybtn);
        left_main= view.findViewById(R.id.runnerbtn);
        TextView right_tv=view.findViewById(R.id.right_tv);
        String formattedDouble = String.format("%.1f", prfs.getInt(MyConstants.JUMP_NUMBER,71)*0.01*75*10);

        right_tv.setText(" Затрачено: "+"\n "+formattedDouble+" Дж");


        BarChart barChart = view.findViewById(R.id.barCharRight);
        ArrayList<BarEntry> visitors = new ArrayList<>();
        if(prfs.getInt(MyConstants.JUMP_NUMBER,71)!=71) {

            for (int i = 0; i < Obrabotchik.razdelJump(myDbManager.getFromDb(pref.getInt(MyConstants.Training_NUMBER, 3) + 1)).size(); i++) {

                visitors.add(new BarEntry(i + 1, (float) (Obrabotchik.razdelJump(myDbManager.getFromDb(pref.getInt(MyConstants.Training_NUMBER, 3) + 1)).get(i) * 0.01 * 75 * 10)));
            }
        }else{
            visitors.add(new BarEntry(1, (float) 126.8));
        }
        BarDataSet barDataSet = new BarDataSet(visitors, "Jumps");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(16f);
        barDataSet.setValueTextColor(Color.BLACK);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(false);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(1300);
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        myDbManager = new MyDbManager(getContext());
        pref = getContext().getSharedPreferences(BtConsts.NUMBER_OF_TRAINING, Context.MODE_PRIVATE);
        prfs = getContext().getSharedPreferences(BtConsts.JUMP_KEY, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
    }
}