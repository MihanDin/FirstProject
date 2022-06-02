package com.samsung.mainwithstrahgedesign.fragments;

import static com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap.si;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.Library;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.LibraryAdapter;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap;
import com.samsung.mainwithstrahgedesign.db.MyConstants;
import com.samsung.mainwithstrahgedesign.db.MyDbManager;
import com.samsung.mainwithstrahgedesign.db.Obrabotchik;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class LibraryFragment extends Fragment {
    private RecyclerView myRecycle;
    private LibraryAdapter libraryAdapter;
    private MyDbManager myDbManager;
    private SQLiteDatabase database;
    private SharedPreferences pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getContext().getSharedPreferences(BtConsts.COUNT, Context.MODE_PRIVATE);
        setHasOptionsMenu(true);
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        List<Library> lstLib = new ArrayList<>();
        myDbManager= new MyDbManager(getContext());
        for (int i = 0; i < pref.getInt(MyConstants.COUNT_NUMBER,1)-71; i++) {
            lstLib.add(new Library(i));
        }
        myRecycle = view.findViewById(R.id.recycleViewTrains);
        libraryAdapter = new LibraryAdapter(getContext(), lstLib);
        myRecycle.setAdapter(libraryAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.findItem(R.id.id_okey).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.id_okey) {

            if (SaveToPrefMap.countSi() == 0) {
                Toast.makeText(getContext(), "Choose one training", Toast.LENGTH_SHORT).show();
            } else if (SaveToPrefMap.countSi() == 1) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new FragmentTrainings()).commit();
                int find = 0;
                for (int i = 0; i < SaveToPrefMap.si.length; i++) {
                    if (si[i] == true) {
                        find = i;
                        break;
                    }
                }
                for (int i = 0; i < SaveToPrefMap.si.length;i++) {
                    si[i]=false;
                }
                SaveToPrefMap.ID=find;

            } else {
                Toast.makeText(getContext(), "Choose ONLY one training ", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}