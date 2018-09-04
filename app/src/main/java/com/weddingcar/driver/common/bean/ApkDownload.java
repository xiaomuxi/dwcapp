package com.weddingcar.driver.common.bean;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.weddingcar.driver.common.config.GlobalConfig;
import com.weddingcar.driver.common.manager.SPController;

import java.io.File;

/**
 * Apk download
 */
public class ApkDownload {

    private static final String DOWNLOAD_FILE_NAME = "XMApp.apk";
    private final String APK_DOWNLOAD_ID = "APK_DOWNLOAD_ID";
    private final String TAG = "ApkDownload";

    private final DownloadManager downloadManager;
    private final DownloadReceiver downloadReceiver;

    private Context context;
    private String title;
    private String notificationMsg;
    private String url;

    public ApkDownload(Context context, String url, String title, String notificationMsg) {
        context = context;
        this.title = title;
        this.notificationMsg = notificationMsg;
        this.url = url;

        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadReceiver = new DownloadReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void execute() {

        // Clear download task
        long downloadId = SPController.getInstance().getLong(APK_DOWNLOAD_ID, -1);
        if(downloadId != -1) {
            downloadManager.remove(downloadId);
            SPController.getInstance().remove(APK_DOWNLOAD_ID);
        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // Set the title of the notification
        request.setTitle(title);
        // Set the message of the notification bar
        request.setDescription(notificationMsg);
        request.allowScanningByMediaScanner();
        // During the download process, the notification bar is displayed. After the download is completed, the notification bar does not disappear. After the download is completed, click to install.
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // Set the available network types
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        // Setting does not display the download interface
        request.setVisibleInDownloadsUi(false);
        // Set the storage location of download files
        File folder =  new File(GlobalConfig.getInstance().sdCachePath);
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }
        request.setDestinationUri(Uri.withAppendedPath(Uri.fromFile(folder), DOWNLOAD_FILE_NAME));
        //Set file type
        request.setMimeType("application/vnd.android.package-archive");

        // Start the download task and save the task id
        SPController.getInstance().putLong(APK_DOWNLOAD_ID, downloadManager.enqueue(request));
    }

    // Download monitoring
    class DownloadReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
             //get the id of download which have download success, if the id is my id and it's status is successful,
             //then install it
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            long downloadId = SPController.getInstance().getLong(APK_DOWNLOAD_ID, -1);

            if (completeDownloadId == downloadId) {
                // if download successful
                if (getDownloadStatus(downloadManager, downloadId) == DownloadManager.STATUS_SUCCESSFUL) {

                    //clear downloadId
                    SPController.getInstance().remove(APK_DOWNLOAD_ID);

                    //unregisterReceiver
                    context.unregisterReceiver(downloadReceiver);
                    //prepare the apk file path
                    String apkFilePath = new StringBuilder(GlobalConfig.getInstance().sdCachePath)
                            .append(File.separator)
                            .append(DOWNLOAD_FILE_NAME).toString();

                    //install apk
                    apkInstall(context, apkFilePath);
                }
            }
        }
    }

    /**
     * Query download status
     *
     * @param manager download manager instance
     * @param id      downloadId
     * @return result
     */
    private int getDownloadStatus(DownloadManager manager, long id) {
        int result = -1;
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(id);
        Cursor cursor = null;
        try {
            cursor = manager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    /**
     * install apk
     * @param cxt context
     * @param path apk local path
     * @return result
     */
    private boolean apkInstall(Context cxt, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri apkUri = FileProvider.getUriForFile(cxt, "com.jsure.xmapp.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cxt.startActivity(intent);
            return true;
        }
        return false;
    }
}
