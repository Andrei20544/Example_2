package Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Service;

public class DB {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SERVICE = "TableService";
    private static final String TABLE_NEWS = "TableNews";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_COST = "Cost";
    private static final int NUM_COLUMN_ID_SERVICE = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_COST = 2;


    private static final String COLUMN_ID_NEWS = "id";
    private static final String COLUMN_DATENEWS = "DateNews";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final int NUM_COLUMN_ID_NEWS = 0;
    private static final int NUM_COLUMN_DATENEW = 1;
    private static final int NUM_COLUMN_TITLE = 2;
    private static final int NUM_COLUMN_DESCRIPTION = 3;

    private SQLiteDatabase DataBase;
    private Context context;
    public DB(Context cont)
    {
        context=cont;
        OpenHelper mOpenHelper = new OpenHelper(context);
        DataBase= mOpenHelper.getWritableDatabase();
    }

    private class OpenHelper extends SQLiteOpenHelper
    {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String queryService = "CREATE TABLE " + TABLE_SERVICE+
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + COLUMN_NAME+ " TEXT," +
                    " " + COLUMN_COST + " TEXT"+")";
            sqLiteDatabase.execSQL(queryService);

            String queryNews = "CREATE TABLE " + TABLE_NEWS+
                    " (" + COLUMN_ID_NEWS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + COLUMN_DATENEWS+ " TEXT," +
                     COLUMN_DESCRIPTION+ " TEXT," +
                    " " + COLUMN_TITLE + " TEXT"+")";
            sqLiteDatabase.execSQL(queryNews);
            List<Service> services=new ArrayList<Service>();
            services=JSONHelper.importFromJSON(context);
            for (Service s:services) {
                ContentValues cv=new ContentValues();
                cv.put(COLUMN_ID, s.getID());
                cv.put(COLUMN_NAME, s.getName());
                cv.put(COLUMN_COST, s.getCost());
                sqLiteDatabase.insert(TABLE_SERVICE,null,cv);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            DataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
            DataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
            onCreate(DataBase);
        }
    }
}
