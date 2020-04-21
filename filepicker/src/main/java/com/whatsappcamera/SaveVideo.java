package com.whatsappcamera;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SaveVideo {
    private Context TheThis;
    private String NameOfFolder = "/CamaraDm3.0";
    private String NameOfFile = "videos";
    public String SaveVideo(Context context, String filePath) {
        TheThis = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, NameOfFile + CurrentDateAndTime + ".mp4");
        try {
            File currentFile = new File(filePath);

            InputStream in = new FileInputStream(currentFile);
            OutputStream out = new FileOutputStream(file);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();

            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
            return file.getPath();
        }
        catch(FileNotFoundException e) {
            UnableToSave();
        }
        catch(IOException e) {
            UnableToSave();
        }

        return null;
    }
    private void MakeSureFileWasCreatedThenMakeAvabile(File file){
        MediaScannerConnection.scanFile(TheThis,
                new String[] { file.toString() } , null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }
    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    private void UnableToSave() {
        Toast.makeText(TheThis, "¡No se ha podido guardar el video!", Toast.LENGTH_SHORT).show();
    }
    private void AbleToSave() {
        Toast.makeText(TheThis, "Video guardado en la galería.", Toast.LENGTH_SHORT).show();
    }
}
