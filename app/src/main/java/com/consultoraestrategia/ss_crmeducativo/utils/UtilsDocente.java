package com.consultoraestrategia.ss_crmeducativo.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilsDocente {
    public static float getFingerSpacing(MotionEvent event) {
        try {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        } catch (Exception e) {
            Log.e("exc", "->" + e.getMessage());
            return 0;
        }
    }
    public static Bitmap rotate(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        return Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
    }

    public static File writeImage(Bitmap bitmap) {
        File dir = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera");
        if (!dir.exists())
            dir.mkdir();
        File photo = new File(dir, "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmSS", Locale.ENGLISH).format(new Date()) + ".jpg");
        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos = new FileOutputStream(photo.getPath());

            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, fos);
            // fos.write(jpeg);
            fos.close();
        } catch (Exception e) {
            Log.e("PictureDemo", "Exception in photoCallback", e);
        }
        return photo;
    }
}
