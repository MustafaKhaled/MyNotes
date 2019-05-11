package com.mynotes.project.util;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class SharedPreferenceManager {
    private static final String TAG = "SharedPreferenceManager";
    private SharedPreferences mySharedPref;

    @Inject
    public SharedPreferenceManager(SharedPreferences sharedPreferences) {
        this.mySharedPref = sharedPreferences;
    }

    public String getStringData(String key){
        if(mySharedPref!=null){
            return mySharedPref.getString(key,null);
        }
        return "";
    }

    public void saveStringData(String key,String value){
        if(mySharedPref!=null){
            SharedPreferences.Editor editor = mySharedPref.edit();
            editor.remove(key);
            editor.putString(key,value);
            editor.commit();
        }
    }

    public void removeData(String key){
        mySharedPref.edit().remove(key).commit();
    }


}
