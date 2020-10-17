package Helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import android.view.Display;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.AccessMode;
import java.util.List;

import Model.Service;

import static java.nio.file.AccessMode.*;

public class JSONHelper
{
    private static final String FILE_NAME ="text.json";

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
