package com.samsung.mainwithstrahgedesign.adapterForLibrary;

import static com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap.si;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;

import java.util.List;

public class LibraryAdapter  extends RecyclerView.Adapter<LibraryAdapter.MyHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private final List<Library> listLib;
    private SharedPreferences pref;

//    public Map<Integer,Boolean>  mapa = new HashMap<Integer,Boolean>();


    public LibraryAdapter(Context context, List<Library> listLib) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listLib = listLib;

    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private CheckBox chBtSelected;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tv_id=itemView.findViewById(R.id.tvBtName);
            chBtSelected=itemView.findViewById(R.id.checkBox2);

        }
    }
    private void savePref(int pos) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(BtConsts.ID_KEY, String.valueOf(listLib.get(pos).getNumbOfTrain()));
        editor.apply();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= inflater.inflate(R.layout.library_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        Library library= listLib.get(position);
        ((MyHolder)holder).tv_id.setText(library.getNumbOfTrain()+1+"-я Тренировка");
        ((MyHolder)holder).chBtSelected.setChecked(Boolean.getBoolean(String.valueOf(SaveToPrefMap.si[position])));

       holder.chBtSelected.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(SaveToPrefMap.si[position]==false){
                   SaveToPrefMap.si[position]=true;
               }else{
                   SaveToPrefMap.si[position]=false;
               } }
       });
    }

    //                Log.d("Siii",String.valueOf(Boolean.getBoolean(String.valueOf(mapa.get(position)))));
//                mapa.put(position,!Boolean.getBoolean(String.valueOf(mapa.get(position))));
    @Override
    public int getItemCount() {
        return listLib.size();
    }
}
