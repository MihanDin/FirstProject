package com.samsung.mainwithstrahgedesign.adapterForLibrary;

import android.util.Log;

public class SaveToPrefMap {
    public static boolean[] si= new boolean[100];
    public static boolean[] jumpi= new boolean[100];
    public static int colVo=0;
    public static int ID=0;
    public static boolean connect;


    public boolean getSi(int pos) {
        return si[pos];
    }
    public boolean getJumpi(int pos) {
        return jumpi[pos];
    }

    public static void setSi(int pos,Boolean znach) {
        si[pos]=znach;
    }
    public static void setJumpi(int pos,Boolean znach) {
        jumpi[pos]=znach;
    }
    public static int countSi(){
        int count=0;
        for (int i = 0; i < SaveToPrefMap.si.length; i++) {
            if(si[i]==true){
                Log.d("Countery",String.valueOf(Boolean.getBoolean(String.valueOf(SaveToPrefMap.si[i]))));
                count+=1;
            }
        }

        return count;
    }
    public static int countJumpi(){
        int count=0;
        for (int i = 0; i < SaveToPrefMap.jumpi.length; i++) {
            if(jumpi[i]==true){
                Log.d("Countery",String.valueOf(Boolean.getBoolean(String.valueOf(SaveToPrefMap.si[i]))));
                count+=1;
            }
        }

        return count;
    }

}
