package com.young.library.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dupeng
 * @version 2.6.0, 2019/2/9  10:05 PM
 * @since android 17MiddleTeacher
 */
public class Utils {

    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static String MD5File(String filename) {
        byte[] buffer = new byte[1024];
        boolean var3 = false;

        try {
            InputStream fis = new FileInputStream(filename);
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int numRead;
            while((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }

            fis.close();
            return toHexString(md5.digest());
        } catch (Exception var6) {
            System.out.println("error");
            return null;
        }
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);

        for(int i = 0; i < b.length; ++i) {
            sb.append(HEX_DIGITS[(b[i] & 240) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 15]);
        }

        return sb.toString();
    }


    public static boolean isStringEquals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        } else if (str1 != null && str2 == null) {
            return str1.equals(str2);
        } else {
            return str2 != null && str1 == null ? str2.equals(str1) : str1.equals(str2);
        }
    }

    public static boolean isStringEmpty(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static String getMetaData(Context context, String dataName) {
        String sChannel = "";
        if (isStringEmpty(sChannel) && !isStringEmpty(dataName) && context != null) {
            if ("UMENG_CHANNEL".equals(dataName)) {
                sChannel = "100106";
                String start_flag = "META-INF/channel_";
                ApplicationInfo appinfo = context.getApplicationInfo();
                String sourceDir = appinfo.sourceDir;
                ZipFile zipfile = null;

                try {
                    zipfile = new ZipFile(sourceDir);
                    Enumeration entries = zipfile.entries();

                    while(entries.hasMoreElements()) {
                        ZipEntry entry = (ZipEntry)entries.nextElement();
                        String entryName = entry.getName();
                        if (entryName.contains("META-INF/channel_")) {
                            sChannel = entryName.replace("META-INF/channel_", "");
                            break;
                        }
                    }
                } catch (IOException var19) {
                    var19.printStackTrace();
                } finally {
                    if (zipfile != null) {
                        try {
                            zipfile.close();
                        } catch (IOException var17) {
                            var17.printStackTrace();
                        }
                    }

                }
            } else {
                try {
                    @SuppressLint("WrongConstant") ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                    String data = "";
                    if (appInfo.metaData != null) {
                        data = appInfo.metaData.getString(dataName);
                        sChannel = data == null ? appInfo.metaData.getInt(dataName) + "" : data;
                    }
                } catch (PackageManager.NameNotFoundException var18) {
                    var18.printStackTrace();
                }
            }
        }

        return sChannel;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
