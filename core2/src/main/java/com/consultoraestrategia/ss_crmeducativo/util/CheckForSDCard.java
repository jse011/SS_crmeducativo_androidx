package com.consultoraestrategia.ss_crmeducativo.util;

import android.os.Environment;

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public static boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
