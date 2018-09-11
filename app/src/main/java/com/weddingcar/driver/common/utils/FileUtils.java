package com.weddingcar.driver.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

public class FileUtils {

    /**
     * Determine whether the SD card is mounted
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Create a folder
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        return !(!file.exists() || !file.isDirectory()) || file.mkdirs();
    }

    /**
     * Modify file permissions
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * Delete the specified file
     * @param path
     * @return result
     */
    public static boolean DeleteFile(String path) {
        File f = new File(path);
        boolean ret = true;

        if (!f.exists()) {
            ret = false;
        } else if (f.exists()) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (!DeleteFile(files[i].getPath())) {
                        ret = false;
                    }
                }
            } else if (!f.delete()) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * 根据Uri获取文件路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
