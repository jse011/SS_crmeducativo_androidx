package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import android.os.Environment;

import java.io.File;

public class DeleteFotoAlumno {
    private String NameOfFolder = "/DetectorRostro";
    private String NameOfFile = "persona";

    public boolean execute(int position, int personaId){
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder+"/"+NameOfFile + "_"+personaId+"_"+position + ".jpg";
        File file = new File(file_path);
        if (file.exists()) {
            return new File(file.getAbsolutePath()).delete();
        }

        return false;
    }
}
