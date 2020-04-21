package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities;

import java.util.Arrays;

public enum EnumFile {


    DOC_ESCRITOS(new String[]{".doc", ".docx", ".txt"}),
    DOC_PRESENTACIONES(new String[]{".ppt", ".pptx"}),
    PDF(new String[]{".pdf"}),
    DOC_TABLAS(new String[]{".xls", ".xlsx",".ods"}),
    MUSICA(new String[]{".mp3", ".ogg",".wav"}),
    VIDEOS(new String[]{".mpg",".3gp",".mpg4",".wmv",".mov",".ogv"}),
    IMAGENES(new String[]{".gif",".jpeg",".jpg",".png"}),
    COMPRESION(new String[]{".gz",".gzip",".rar",".zip"});
    String[] extenciones;

    EnumFile(String[] extenciones) {
        this.extenciones = extenciones;
    }

    public String[] getExtenciones() {
        return extenciones;
    }

    public static EnumFile getFile(String extencion){
        for (EnumFile file : EnumFile.values()){
           if(Arrays.asList(file.getExtenciones()).contains(extencion)){
               return file;
           }
        }

        return null;
    }


}
