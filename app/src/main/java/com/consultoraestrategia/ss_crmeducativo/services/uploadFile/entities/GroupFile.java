package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupFile {
    private String nombre;
    private List<String> extenciones = new ArrayList<>();
    private int drawableIcon;


    public GroupFile(String nombre, int drawableIcon, EnumFile...files) {
        this.nombre = nombre;
        addtFiles(files);
        this.drawableIcon = drawableIcon;
    }

    public GroupFile(String nombre, EnumFile...files) {
        this.nombre = nombre;
        addtFiles(files);
    }

    public String getNombre() {
        return nombre;
    }


    public String[] getExtenciones() {
        String[] stockArr = new String[extenciones.size()];
        return extenciones.toArray(stockArr);
    }

    private void addtFiles(EnumFile...files) {
        if(files==null)return;
        for (EnumFile file: files){
            this.extenciones.addAll(Arrays.asList(file.getExtenciones()));
        }
    }


    public int getDrawableIcon() {
        return drawableIcon;
    }



}
