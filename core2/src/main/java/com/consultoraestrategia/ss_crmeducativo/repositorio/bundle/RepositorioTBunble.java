package com.consultoraestrategia.ss_crmeducativo.repositorio.bundle;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;

import java.io.Serializable;

public class RepositorioTBunble implements Serializable {
    private static final String BUNDLE = "JustificacionRepositorioFragment.BUNDLE";
    private RepositorioUi repositorio = RepositorioUi.ARCHIVO;
    private FragmentoTipo fragmentoTipo = FragmentoTipo.SUBIDA_DESCARGA_IMAGEN;
    private String colorCurso;

    public RepositorioTBunble() {
    }



    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE, this);
        return bundle;
    }

    public static RepositorioTBunble clone(Bundle bundle) {
        RepositorioTBunble tBunble = (RepositorioTBunble) bundle.getSerializable(BUNDLE);
        Log.d(RepositorioTBunble.class.getSimpleName(), tBunble.toString());
        return tBunble;
    }

    public String getColorCurso() {
        return colorCurso;
    }

    public void setColorCurso(String colorCurso) {
        this.colorCurso = colorCurso;
    }

    public RepositorioUi getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepositorioUi repositorio) {
        this.repositorio = repositorio;
    }

    public FragmentoTipo getFragmentoTipo() {
        return fragmentoTipo;
    }

    public void setFragmentoTipo(FragmentoTipo fragmentoTipo) {
        this.fragmentoTipo = fragmentoTipo;
    }
}
