package com.hongsec.projectframe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hongsec.projectframe.db.base.BaseDB;

/**
 * Created by Hongsec on 2016-04-15.
 */
public class DB_APP extends BaseDB {

    private static final String TABLE_APP="table_app";


    private static class Column_APP{

        private static final String ID = "id";
        private static final String ERROR_STR = "err";

        private static String column_create() {
            return TABLE_APP + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ERROR_STR + " TEXT DEFAULT ''" +
                    ");";
        }

    }




    public DB_APP(Context mContext) {
        super(mContext);
    }

    @Override
    public int get_DbVersion() {
        return 1;
    }

    @Override
    public String get_DbName() {
        return "DB_APP";
    }

    @Override
    public void onDBUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public String[] onDBCreate() {
        String[] strings = new String[1];
        strings[0] = Column_APP.column_create() ;

        return strings;
    }



}
