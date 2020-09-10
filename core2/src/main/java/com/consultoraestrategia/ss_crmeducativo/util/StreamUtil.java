package com.consultoraestrategia.ss_crmeducativo.util;

import com.google.android.gms.common.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

    public static final String PREFIX = "stream2file";

    public static File stream2file (InputStream inputStream, String tempFileSuffix) throws IOException {
        FileOutputStream outputStream = null;
        File file = null;
        try {
            file = File.createTempFile(PREFIX, tempFileSuffix);
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while (true) {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (Exception e){
          e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

        return file;
    }
}
