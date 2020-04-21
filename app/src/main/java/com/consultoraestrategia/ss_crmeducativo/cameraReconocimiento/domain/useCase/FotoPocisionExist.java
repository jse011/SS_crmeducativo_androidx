package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import android.os.Environment;

import java.io.File;

public class FotoPocisionExist {
    private String NameOfFolder = "/DetectorRostro";
    private String NameOfFile = "persona";

    public String execute(int position, int personaId){
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder+"/"+NameOfFile + "_"+personaId+"_"+position + ".jpg";

        File dir = new File(file_path);
        if (dir.exists()) {
            return dir.getPath();
        }
        return null;

    }
}
