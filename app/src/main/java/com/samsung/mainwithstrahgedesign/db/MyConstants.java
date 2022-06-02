package com.samsung.mainwithstrahgedesign.db;

public class MyConstants {
    public static final String TABLE_NAME ="High_jump_table";
    public static String _ID ="_id";
    public static final String TITLE ="title";
    public static final String DB_NAME="High_db.db";
    public static final int DB_VERSION=1;
    public static final String JUMP_NUMBER ="NUMBER_JUMP";
    public static final String COUNT_NUMBER ="NUMBER_COUNT";
    public static final String TABLE_STRUCTURE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                    _ID + " INTEGER PRIMARY KEY , " +
                    TITLE + " TEXT )";
    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
