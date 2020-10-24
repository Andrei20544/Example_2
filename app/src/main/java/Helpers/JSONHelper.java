package Helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import android.view.Display;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.AccessMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Model.News;
import Model.Service;

import static java.nio.file.AccessMode.*;

public class JSONHelper {
    private static final String FILE_NAME = "text.json";

    static boolean exportToJSON(Context context, List<Service> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setPhones(dataList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            }
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    static List<News> importFromCSV(Context context) {
        List<News> news=null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("news.csv")));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            news = new ArrayList<News>();
            while ((line = reader.readLine()) != null) {
                News newNews = new News();
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0) newNews.setID(Long.parseLong(data));
                    else if (index == 1) newNews.setDateNews(data);
                    else if (index == 2) newNews.setTitle(data);
                    else if (index == 3) newNews.setDescription(data);
                    index++;
                }
                index = 0;
                news.add(newNews);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }


    static List<Service> importFromJSON(Context context) {

        InputStream is=null;
        DataItems dataItems=null;
        try{
            byte[] buffer = null;
            is = context.getAssets().open("text.json");
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            String str_data = new String(buffer);
            Gson gson = new Gson();
            dataItems = gson.fromJson(str_data, DataItems.class);
            return  dataItems.getServices();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
//        finally {
//            if (is != null) {
//                try {
//                    Log.d("gfdg",dataItems.getServices().toString());
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//           }
        return null;
    }

    private static class DataItems {
        private List<Service> services;

        List<Service> getServices() {
            return services;
        }
        void setPhones(List<Service> serviceList) {
            this.services = serviceList;
        }
    }
}
