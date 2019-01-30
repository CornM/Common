/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.zswl.common.widget;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class FileUtil {


    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else {
            return false;
        }
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return !isSDCardEnable() ? "sdcard unable!" : Environment.getExternalStorageDirectory().getPath() + File.separator;
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static File getFileByPath(String filePath) {

        return isNullString(filePath) ? null : new File(filePath);
    }


    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        } else if (file.exists()) {
            return file.isFile();
        } else if (!createOrExistsDir(file.getParentFile())) {
            return false;
        } else {
            try {
                return file.createNewFile();
            } catch (IOException var2) {
                var2.printStackTrace();
                return false;
            }
        }
    }

    public static boolean createOrExistsDir(File file) {
        boolean var10000;
        label25:
        {
            if (file != null) {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        break label25;
                    }
                } else if (file.mkdirs()) {
                    break label25;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }


    private static boolean isNullString(String str) {
        return str == null || str.length() == 0 || "null".equals(str);
    }

    public static String getUploadImgPath() {
        return getSDCardPath() + "Decorate" + File.separator + "upload" + File.separator;
    }
}
