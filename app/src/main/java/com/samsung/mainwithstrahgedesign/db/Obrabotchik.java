package com.samsung.mainwithstrahgedesign.db;

import com.samsung.mainwithstrahgedesign.adapterForLibrary.Jump;

import java.util.ArrayList;
import java.util.List;

public class Obrabotchik {
    private String str;

    public Obrabotchik(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static List<Integer> razdelJump(String str) {
        List<Integer> result = new ArrayList<>();

        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (int) str.length(); i++) {
            if (i < str.length() - 1 && str.charAt(i + 1) == ' ') {
                builder.append(str.charAt(i));
                result.add(Integer.parseInt(String.valueOf(builder)));
                count++;
                builder.delete(0, builder.length());
            } else if (i + 1 == str.length()) {
                builder.append(str.charAt(i));
                result.add(Integer.parseInt(String.valueOf(builder)));
            } else if (str.charAt(i) != ' ') {
                builder.append(str.charAt(i));

            }
        }


        return result;
    }
    public static List<Integer> perevodIzString(String str){
        List<Integer> list= new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            int k= str.charAt(i);
            list.add(k-48);
        }

        return list;
    }
    public static String perevodVString(List<Integer> list){
        String str ="";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size()-1; i++) {
            if(i==0){
                str+=list.get(i);
            }else{
                str+=" "+list.get(i);
            }

        }
        return str;
    }
}
