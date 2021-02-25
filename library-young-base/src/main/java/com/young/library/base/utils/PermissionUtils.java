package com.young.library.base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PermissionUtils {
    public PermissionUtils() {
    }

    public static boolean checkAndApplyfPermissionActivity(Activity activity, String[] permissions, int requestCode) {
        if (VERSION.SDK_INT >= 23) {
            permissions = checkPermissions(activity, permissions);
            if (permissions != null && permissions.length > 0) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkAndApplyfPermissionFragment(Fragment mFragment, String[] permissions, int requestCode) {
        if (VERSION.SDK_INT >= 23) {
            permissions = checkPermissions(mFragment.getActivity(), permissions);
            if (permissions != null && permissions.length > 0) {
                if (mFragment.getActivity() != null) {
                    mFragment.requestPermissions(permissions, requestCode);
                }

                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private static String[] checkPermissions(Context context, String[] permissions) {
        if (permissions != null && permissions.length != 0) {
            ArrayList<String> permissionLists = new ArrayList();
            permissionLists.addAll(Arrays.asList(permissions));

            for(int i = permissionLists.size() - 1; i >= 0; --i) {
                if (ContextCompat.checkSelfPermission(context, (String)permissionLists.get(i)) == 0) {
                    permissionLists.remove(i);
                }
            }

            String[] temps = new String[permissionLists.size()];

            for(int i = 0; i < permissionLists.size(); ++i) {
                temps[i] = (String)permissionLists.get(i);
            }

            return temps;
        } else {
            return new String[0];
        }
    }

    public static boolean checkPermission(int[] grantResults) {
        if (grantResults != null && grantResults.length != 0) {
            int temp = 0;
            int[] var2 = grantResults;
            int var3 = grantResults.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                int i = var2[var4];
                if (i == 0) {
                    ++temp;
                }
            }

            return temp == grantResults.length;
        } else {
            return true;
        }
    }

    public static void showPermissionsToast(Activity activity, @NonNull String[] permissions) {
        if (permissions.length > 0) {
            String[] var2 = permissions;
            int var3 = permissions.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String permission = var2[var4];
                showPermissionToast(activity, permission);
            }
        }

    }

    @SuppressLint("WrongConstant")
    private static void showPermissionToast(Activity activity, String permission) {
        String info = "";
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            if (Utils.isStringEquals(permission, "android.permission.CAMERA")) {
                info = "相机权限已被禁止，请在应用管理中打开";
            } else if (Utils.isStringEquals(permission, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                info = "文件权限已被禁止，请在应用管理中打开";
            } else if (Utils.isStringEquals(permission, "android.permission.RECORD_AUDIO")) {
                info = "录制音频权限已被禁止，请在应用管理中打开";
            } else if (Utils.isStringEquals(permission, "android.permission.ACCESS_FINE_LOCATION")) {
                info = "位置权限已被禁止，请在应用管理中打开";
            } else if (Utils.isStringEquals(permission, "android.permission.READ_PHONE_STATE")) {
                info = "读取手机状态权限已被禁止，请在应用管理中打开";
            } else if (Utils.isStringEquals(permission, "android.permission.MOUNT_UNMOUNT_FILESYSTEMS")) {
                info = "文件权限已被禁止，请在应用管理中打开";
            } else {
                info = "应用权限已被禁止，请在应用管理中打开权限";
            }
        } else if (Utils.isStringEquals(permission, "android.permission.CAMERA")) {
            info = "没有相机权限";
        } else if (Utils.isStringEquals(permission, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            info = "没有文件读取权限";
        } else if (Utils.isStringEquals(permission, "android.permission.RECORD_AUDIO")) {
            info = "没有录制音频权限";
        } else if (Utils.isStringEquals(permission, "android.permission.ACCESS_FINE_LOCATION")) {
            info = "没有位置权限";
        } else if (Utils.isStringEquals(permission, "android.permission.READ_PHONE_STATE")) {
            info = "没有获取手机状态权限";
        } else if (Utils.isStringEquals(permission, "android.permission.MOUNT_UNMOUNT_FILESYSTEMS")) {
            info = "没有文件读取权限";
        } else {
            info = "没有获取手机权限";
        }

        if (!Utils.isStringEmpty(info)) {
            Toast.makeText(activity, info, 0).show();
        }

    }

    @SuppressLint("WrongConstant")
    public static void gotoPermissionManager(Context context) {
        try {
            String var3 = Build.MANUFACTURER;
            byte var4 = -1;
            switch(var3.hashCode()) {
                case -2122609145:
                    if (var3.equals("Huawei")) {
                        var4 = 0;
                    }
                    break;
                case -1675632421:
                    if (var3.equals("Xiaomi")) {
                        var4 = 2;
                    }
                    break;
                case 2427:
                    if (var3.equals("LG")) {
                        var4 = 5;
                    }
                    break;
                case 2364891:
                    if (var3.equals("Letv")) {
                        var4 = 6;
                    }
                    break;
                case 2432928:
                    if (var3.equals("OPPO")) {
                        var4 = 4;
                    }
                    break;
                case 2582855:
                    if (var3.equals("Sony")) {
                        var4 = 3;
                    }
                    break;
                case 74224812:
                    if (var3.equals("Meizu")) {
                        var4 = 1;
                    }
            }

            Intent intent;
            ComponentName comp;
            switch(var4) {
                case 0:
                    intent = new Intent();
                    intent.setFlags(268435456);
                    intent.putExtra("packageName", "com.yiqizuoye.framework");
                    comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
                    intent.setComponent(comp);
                    context.startActivity(intent);
                    break;
                case 1:
                    intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.putExtra("packageName", "com.yiqizuoye.framework");
                    context.startActivity(intent);
                    break;
                case 2:
                    String rom = getSystemProperty("ro.miui.ui.version.name");
                    if ("v5".equals(rom)) {
                        Uri packageURI = Uri.parse("package:" + context.getApplicationInfo().packageName);
                        intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", packageURI);
                    } else {
                        intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                        intent.putExtra("extra_pkgname", context.getPackageName());
                    }

                    context.startActivity(intent);
                    break;
                case 3:
                    intent = new Intent();
                    intent.setFlags(268435456);
                    intent.putExtra("packageName", "com.yiqizuoye.framework");
                    comp = new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity");
                    intent.setComponent(comp);
                    context.startActivity(intent);
                    break;
                case 4:
                    intent = new Intent();
                    intent.setFlags(268435456);
                    intent.putExtra("packageName", "com.yiqizuoye.framework");
                    comp = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
                    intent.setComponent(comp);
                    context.startActivity(intent);
                    break;
                case 5:
                    intent = new Intent("android.intent.action.MAIN");
                    intent.setFlags(268435456);
                    intent.putExtra("packageName", "com.yiqizuoye.framework");
                    comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
                    intent.setComponent(comp);
                    context.startActivity(intent);
                    break;
                case 6:
                    intent = new Intent();
                    intent.setFlags(268435456);
                    intent.putExtra("packageName", "com.yiqizuoye.framework");
                    comp = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
                    intent.setComponent(comp);
                    context.startActivity(intent);
                    break;
                default:
                    getAppDetailSettingIntent(context);
            }
        } catch (Exception var7) {
            getAppDetailSettingIntent(context);
        }

    }

    public static String getSystemProperty(String propName) {
        BufferedReader input = null;

        Object var4;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            String line = input.readLine();
            input.close();
            return line;
        } catch (IOException var14) {
            var4 = null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException var13) {
                    ;
                }
            }

        }

        return (String)var4;
    }

    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(268435456);
        if (VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), (String)null));
        } else if (VERSION.SDK_INT <= 8) {
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }

        launchApp(context, localIntent);
    }

    public static boolean launchApp(Context ctx, Intent intent) {
        if (ctx == null) {
            throw new NullPointerException("ctx is null");
        } else {
            try {
                ctx.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException var3) {
                return false;
            }
        }
    }
}
