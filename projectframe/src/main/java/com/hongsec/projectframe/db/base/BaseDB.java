package com.hongsec.projectframe.db.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hongsec on 2016-04-15.
 */
public abstract class BaseDB {

    protected Context mContext;
    protected DataBaseHelper mDBHelper;
    protected SQLiteDatabase mWritableDatabase;


    public BaseDB(Context mContext) {
        this.mContext = mContext;
    }

    public void openDB() {
        try {
            mDBHelper = new DataBaseHelper(mContext);
            mWritableDatabase = mDBHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeDB() {
        try {
            if (mWritableDatabase != null) {
                mWritableDatabase.close();
                SQLiteDatabase.releaseMemory();
            }
            if (mDBHelper != null)
                mDBHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Cursor getAllValues(String tableName) {
        if (mWritableDatabase != null)
            return mWritableDatabase.query(tableName, null, null, null, null, null, null);
        return null;
    }


    public boolean isExistRows(String TABLE_NAME) {
        boolean result = false;
        Cursor fetchAllValues = getAllValues(TABLE_NAME);
        if (fetchAllValues != null) {
            if (fetchAllValues.getCount() > 0) {
                result = true;
            }

            fetchAllValues.close();
        }
        return result;
    }


    public boolean deleteAll(String tablename) {

        int id = -1;
        if (mWritableDatabase != null) {
            id = mWritableDatabase.delete(tablename, null, null);
        }

        if (id > 0) {
            return true;
        }

        return false;
    }


    public boolean update(String tableName, ContentValues contentValues) {

        long id = -1;

        if (mWritableDatabase != null) {
            if (isExistRows(tableName)) {
                id = mWritableDatabase.update(tableName, contentValues, null, null);
            } else {
                id = mWritableDatabase.insert(tableName, null, contentValues);
            }
        }

        if (id < 1) {
            return false;
        } else {
            return true;
        }

    }

    public void insert(String tableName, ContentValues contentValues) {
        if (mWritableDatabase != null) {
            mWritableDatabase.insert(tableName, null, contentValues);
        }

    }


    public abstract int get_DbVersion();

    public abstract String get_DbName();

    public abstract void onDBUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public abstract String[] onDBCreate();

    protected class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, get_DbName(), null, get_DbVersion());
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            for (String column_create : onDBCreate()) {
                db.execSQL("CREATE TABLE IF NOT EXISTS " + column_create);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion >= newVersion) return;
            onDBUpgrade(db, oldVersion, newVersion);
        }

    }


}
