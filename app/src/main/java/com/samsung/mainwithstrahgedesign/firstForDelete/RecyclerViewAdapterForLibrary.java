package com.samsung.mainwithstrahgedesign.firstForDelete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.Library;

import java.util.List;

public class RecyclerViewAdapterForLibrary extends RecyclerView.Adapter
        <RecyclerViewAdapterForLibrary.MyViewHolder> {
    Context mContext;
    List<Library> mData;

    public RecyclerViewAdapterForLibrary(Context mContext, List<Library> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.jumps_adapter_item,parent,false);
        MyViewHolder vHolder= new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_Lib_id.setText(mData.get(position).getNumbOfTrain());
        holder.tv_Lib.setText("Тренировка");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_Lib;
        private TextView tv_Lib_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Lib_id=itemView.findViewById(R.id.jumpsTrainName);
        }
    }


}
