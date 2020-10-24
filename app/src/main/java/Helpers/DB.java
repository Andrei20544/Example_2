package Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import Model.News;
import Model.Service;
import Model.User;

public class DB {
    private static final String DATABASE_NAME = "wsr7.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SERVICE = "TableService";
    private static final String TABLE_NEWS = "TableNews";
    private static final String TABLE_PATIENT = "TablePatient";

    private static final String COLUMN_ID_PAT = "id";
    private static final String COLUMN_FIRST_NAME_PAT = "FirstName";
    private static final String COLUMN_LAST_NAME_PAT = "LastName";
    private static final String COLUMN_PASS_DATA = "PassData";
    private static final String COLUMN_PHONE = "Phone";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_DATEBIRTH = "DateBirth";
    private static final String COLUMN_POLICENUM = "PoliceNum";
    private static final String COLUMN_LOGIN = "Login";
    private static final String COLUMN_PASSWORD = "Password";

    private static final int NUM_COLUMN_ID_PAT = 0;
    private static final int NUM_COLUMN_FIRST_NAME_PAT = 1;
    private static final int NUM_COLUMN_LAST_NAME_PAT  = 2;
    private static final int NUM_COLUMN_PASS_DATA  = 3;
    private static final int NUM_COLUMN_PHONE = 4;
    private static final int NUM_COLUMN_EMAIL = 5;
    private static final int NUM_COLUMN_DATEBIRTH   = 6;
    private static final int NUM_COLUMN_POLICENUM = 7;
    private static final int NUM_COLUMN_LOGIN = 8;
    private static final int NUM_COLUMN_PASSWORD = 9;


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
    private static final int NUM_COLUMN_DATENEWS = 1;
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

    public User getUser(String login, String password)
    {
        Cursor mCursor = DataBase.query(TABLE_PATIENT, null, "login = ? and password = ?", new String[] {login, password}, null, null, null);
        mCursor.moveToFirst();
        User user=new User(mCursor.getLong(NUM_COLUMN_ID_PAT), mCursor.getString(NUM_COLUMN_FIRST_NAME_PAT), mCursor.getString(NUM_COLUMN_LAST_NAME_PAT),
                mCursor.getString(NUM_COLUMN_PASS_DATA), mCursor.getString(NUM_COLUMN_PHONE), mCursor.getString(NUM_COLUMN_EMAIL), mCursor.getString(NUM_COLUMN_DATEBIRTH),
                mCursor.getString(NUM_COLUMN_POLICENUM), mCursor.getString(NUM_COLUMN_LOGIN), mCursor.getString(NUM_COLUMN_PASSWORD));
        return user;
    }
    public ArrayList<Service> selectAllServices() {
        Cursor mCursor = DataBase.query(TABLE_SERVICE, null, null, null, null, null,
                null);
        ArrayList<Service> arr = new ArrayList<Service>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {//
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID_SERVICE);
                String NameServices = mCursor.getString(NUM_COLUMN_NAME);
                double CostServices = mCursor.getDouble(NUM_COLUMN_COST);
                arr.add(new Service( id,NameServices, CostServices));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<News> selectAllNews() {
        Cursor mCursor = DataBase.query(TABLE_NEWS, null, null, null, null, null,
                null);
        ArrayList<News> arr = new ArrayList<News>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {//
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID_NEWS);
                String Title = mCursor.getString(NUM_COLUMN_TITLE);
                String Description = mCursor.getString(NUM_COLUMN_DESCRIPTION);
                String DateNews = mCursor.getString(NUM_COLUMN_DATENEWS);
                arr.add(new News( id, DateNews, Title, Description));
            } while (mCursor.moveToNext());
        }
        return arr;
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

            String queryUser = "CREATE TABLE " + TABLE_PATIENT+
                    " (" + COLUMN_ID_PAT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + COLUMN_FIRST_NAME_PAT+ " TEXT," +
                    "" + COLUMN_PASS_DATA+ " TEXT," +
                    "" + COLUMN_PHONE+ " TEXT," +
                    "" + COLUMN_EMAIL+ " TEXT," +
                    "" + COLUMN_DATEBIRTH+ " TEXT," +
                    "" + COLUMN_POLICENUM+ " TEXT," +
                    "" + COLUMN_LOGIN+ " TEXT," +
                    "" + COLUMN_PASSWORD+ " TEXT," +
                    "" + COLUMN_LAST_NAME_PAT + " TEXT"+")";
            sqLiteDatabase.execSQL(queryUser);

            String queryNews = "CREATE TABLE " + TABLE_NEWS+
                    " (" + COLUMN_ID_NEWS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + COLUMN_DATENEWS+ " TEXT," +
                     COLUMN_DESCRIPTION+ " TEXT," +
                    " " + COLUMN_TITLE + " TEXT"+")";

            sqLiteDatabase.execSQL(queryNews);

            //загрузка json
            List<Service> services=new ArrayList<Service>();
            Gson gson=new Gson();
            String json=gson.toJson(services);
            services=JSONHelper.importFromJSON(context);
            for (Service s:services) {
                ContentValues cv=new ContentValues();
                cv.put(COLUMN_ID, s.getID());
                cv.put(COLUMN_NAME, s.getName());
                cv.put(COLUMN_COST, s.getCost());
                sqLiteDatabase.insert(TABLE_SERVICE,null,cv);
            }

            //Загрузка CSV
            List<News> newsList = new ArrayList<News>();
            newsList = JSONHelper.importFromCSV(context);
            for(News n:newsList){
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_ID_NEWS, n.getID());
                cv.put(COLUMN_TITLE, n.getTitle());
                cv.put(COLUMN_DESCRIPTION, n.getDescription());
                cv.put(COLUMN_DATENEWS, n.getDateNews());
                sqLiteDatabase.insert(TABLE_NEWS, null, cv);
            }

            User user = new User(1,"AHMAD", "ADKIN", "2456 889914", "87561297546", "a.adkin@dayrep.net", "04.10.1990", "894557411563", "login123", "password123");
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_ID_PAT, user.getID());
            cv.put(COLUMN_FIRST_NAME_PAT, user.getFirstName());
            cv.put(COLUMN_LAST_NAME_PAT, user.getLastName());
            cv.put(COLUMN_PASS_DATA, user.getPassData());
            cv.put(COLUMN_PHONE, user.getPhone());
            cv.put(COLUMN_EMAIL, user.getEmail());
            cv.put(COLUMN_DATEBIRTH, user.getDateBirth());
            cv.put(COLUMN_POLICENUM, user.getPoliceNum());
            cv.put(COLUMN_LOGIN, user.getLogin());
            cv.put(COLUMN_PASSWORD, user.getPassword());
            sqLiteDatabase.insert(TABLE_PATIENT, null, cv);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//            DataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
//            DataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
            onCreate(DataBase);
        }
    }
}
