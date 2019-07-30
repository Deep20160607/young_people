package com.young.library.base.utils;

import android.content.Context;
import android.util.Base64;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SharedPreferencesManager {
    private static Context sContext = null;
    private static SharedPreferencesManager sInstance = null;

    public static void createInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesManager(context);
            LogUtils.d("createInstance()");
        }

    }

    private SharedPreferencesManager(Context context) {
        sContext = context;
    }

    public static <T extends Serializable> T getSerializable(String key, T defValue) {
        String valueString = getString(key, key, defValue.toString());
        if (valueString != null && valueString.length() != 0) {
            try {
                Object resultObject = fromString(valueString);
                return (T) resultObject;
            } catch (IOException var4) {
                LogUtils.d("getSerializable: IOException " + var4.getMessage());
                var4.printStackTrace();
            } catch (ClassNotFoundException var5) {
                LogUtils.d("getSerializable: ClassNotFoundException " + var5.getMessage());
                var5.printStackTrace();
            } catch (Exception var6) {
                LogUtils.d("getSerializable: Exception " + var6.getMessage());
                var6.printStackTrace();
            }

            return defValue;
        } else {
            saveSerializable(key, defValue);
            return defValue;
        }
    }

    public static <T extends Serializable> boolean saveSerializable(String key, T value) {
        if (value == null) {
            return false;
        } else {
            try {
                String valueString = toString(value);
                putString(key, key, valueString);
                return true;
            } catch (IOException var3) {
                LogUtils.d("saveSerializable: IOException " + var3.getMessage());
                var3.printStackTrace();
                return false;
            }
        }
    }

    public static boolean hasKey(String name, String key) {
        return sContext.getSharedPreferences(name, 0).contains(key);
    }

    public static boolean getBoolean(String name, String key, boolean defValue) {
        LogUtils.d("getBoolean() : name = " + name + " : key = " + key + " : defValue = " + defValue);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).getBoolean(key, defValue) : defValue;
    }

    public static String getString(String name, String key, String defValue) {
        LogUtils.d("getString() : name = " + name + " : key = " + key + " : defValue = " + defValue);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).getString(key, defValue) : defValue;
    }

    public static int getInt(String name, String key, int defValue) {
        LogUtils.d("getInt() : name = " + name + " : key = " + key + " : defValue = " + defValue);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).getInt(key, defValue) : defValue;
    }

    public static boolean putInt(String name, String key, int value) {
        LogUtils.d("putInt() : name = " + name + " : key = " + key + " : value = " + value);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).edit().putInt(key, value).commit() : false;
    }

    public static long getLong(String name, String key, long defValue) {
        LogUtils.d("getLong() : name = " + name + " : key = " + key + " : defValue = " + defValue);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).getLong(key, defValue) : defValue;
    }

    public static boolean putLong(String name, String key, long value) {
        LogUtils.d("putLong() : name = " + name + " : key = " + key + " : value = " + value);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).edit().putLong(key, value).commit() : false;
    }

    public static boolean putBoolean(String name, String key, boolean value) {
        LogUtils.d("putBoolean() : name = " + name + " : key = " + key + " : defValue = " + value);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).edit().putBoolean(key, value).commit() : false;
    }

    public static boolean putString(String name, String key, String value) {
        LogUtils.d("putString() : name = " + name + " : key = " + key + " : defValue = " + value);
        return sInstance != null && sContext != null ? sContext.getSharedPreferences(name, 0).edit().putString(key, value).commit() : false;
    }

    private static Object fromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.decode(s, 0);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return new String(Base64.encode(baos.toByteArray(), 0));
    }

    public static void clear(String name) {
        if (sInstance != null && sContext != null) {
            sContext.getSharedPreferences(name, 0).edit().clear().commit();
        }

    }

    public static void remove(String name, String key) {
        if (sInstance != null && sContext != null) {
            sContext.getSharedPreferences(name, 0).edit().remove(key).commit();
        }

    }
}
