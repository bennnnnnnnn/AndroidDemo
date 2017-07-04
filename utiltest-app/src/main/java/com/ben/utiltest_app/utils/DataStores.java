package com.ben.utiltest_app.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created on 17/7/4.
 *
 * @author Ben
 *
 * refrence from Drakeet
 */


public class DataStores {

    private static final String TAG = DataStores.class.getSimpleName();
    public static final String ALIAS = "dataStore";


    public static DataStore newDataStore(Context context) {
        return new DataStore(context, ALIAS);
    }


    public static boolean createKeys(DataStore box) {
        try {
            box.createKeys();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "[createKeys]", e);
        }
        return false;
    }


    public static String encrypt(DataStore box, String secret) {
        try {
            return box.encrypt(secret);
        } catch (Exception e) {
            Log.e(TAG, "[encrypt]", e);
        }
        return null;
    }


    public static String encrypt(Context context, String secret) {
        DataStore box = DataStores.newDataStore(context);
        DataStores.createKeys(box);
        return encrypt(box, secret);
    }


    public static String decrypt(DataStore box, String encrypted) {
        try {
            return box.decrypt(encrypted);
        } catch (Exception e) {
            Log.e(TAG, "[decrypt]", e);
        }
        return null;
    }


    public static String decrypt(Context context, String encrypted) {
        return decrypt(DataStores.newDataStore(context), encrypted);
    }
}
