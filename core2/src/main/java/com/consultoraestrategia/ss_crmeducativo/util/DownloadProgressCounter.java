package com.consultoraestrategia.ss_crmeducativo.util;

import android.app.DownloadManager;
import android.database.Cursor;

public class DownloadProgressCounter extends Thread {
    private long downloadId;
    private final DownloadManager downloadManager;
    private final DownloadManager.Query query;
    private int lastBytesDownloadedSoFar;
    private int totalBytes;
    private final Listener listener;

    public DownloadProgressCounter(DownloadManager downloadManager, long downloadId, Listener listener) {
        this.downloadId = downloadId;
        this.downloadManager = downloadManager;
        this.query = new DownloadManager.Query();
        this.listener = listener;
        query.setFilterById(this.downloadId);
    }

    @Override
    public void run() {
        int progress = 0;
        while (downloadId > 0) {
            try {
                Thread.sleep(300);

                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {

                    //get total bytes of the file
                    if (totalBytes <= 0) {
                        totalBytes = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    }

                    final int bytesDownloadedSoFar = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));

                    if (bytesDownloadedSoFar == totalBytes && totalBytes > 0||
                            cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        this.interrupt();

                    }else if( cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_FAILED) {
                        this.interrupt();
                    }else {
                        progress += (bytesDownloadedSoFar - lastBytesDownloadedSoFar);
                        listener.onProgress(progress, downloadId);
                        lastBytesDownloadedSoFar = bytesDownloadedSoFar;
                    }

                }
                cursor.close();
            } catch (Exception e) {
                return;
            }
        }
    }

    public interface Listener{
        void onProgress(double progress, long downloadId);
    }
}
