package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.bundle;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.ui.CrearTareasActivity;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import org.parceler.Parcels;

public class CrearTareaBundle {

    private static final String mSesionAprendizajeId = "mSesionAprendizajeId";
    private static final String headerTareasAprendizaje = "headerTareasAprendizajeUI";
    private static final String mIdSilaboEvento = "mIdSilaboEvento";
    private static final String mIdUsuario = "mIdUsuario";
    private static final String mIdCurso = "mIdCurso";
    public static final String tareas = "tareasUI";
    public static final String mColorCurso = "mCOlorCurso";
    public static final String mProgramaEducativoId = "mProgramaEducativoId";

    private HeaderTareasAprendizajeUI headerTareasAprendizajeUI;
    private int sesionAprendizajeId;
    private int silavoId;
    private int cursoId;
    private int usuarioId;
    private String colorCurso;
    private TareasUI tareasUI;
    private int programaEducativoId;

    public CrearTareaBundle(Bundle bundle) {
        if(bundle==null)return;
        sesionAprendizajeId = bundle.getInt(mSesionAprendizajeId);
        headerTareasAprendizajeUI = Parcels.unwrap(bundle.getParcelable(headerTareasAprendizaje));
        tareasUI = Parcels.unwrap(bundle.getParcelable(tareas));
        silavoId = bundle.getInt(mIdSilaboEvento);
        usuarioId = bundle.getInt(mIdUsuario);
        cursoId = bundle.getInt(mIdCurso);
        colorCurso = bundle.getString(mColorCurso);
        programaEducativoId = bundle.getInt(mProgramaEducativoId);
    }


    public TareasUI getTareasUI() {
        return tareasUI;
    }

    public void setTareasUI(TareasUI tareasUI) {
        this.tareasUI = tareasUI;
    }

    public int getSilavoId() {
        return silavoId;
    }

    public void setSilavoId(int silavoId) {
        this.silavoId = silavoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getColorCurso() {
        return colorCurso;
    }

    public void setColorCurso(String colorCurso) {
        this.colorCurso = colorCurso;
    }

    public HeaderTareasAprendizajeUI getHeaderTareasAprendizajeUI() {
        return headerTareasAprendizajeUI;
    }

    public void setHeaderTareasAprendizajeUI(HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        this.headerTareasAprendizajeUI = headerTareasAprendizajeUI;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(headerTareasAprendizaje, Parcels.wrap(headerTareasAprendizajeUI));
        bundle.putParcelable(tareas, Parcels.wrap(tareasUI));
        bundle.putInt(mIdSilaboEvento,silavoId);
        bundle.putInt(mIdUsuario, usuarioId);
        bundle.putInt(mIdCurso, cursoId);
        bundle.putInt(mSesionAprendizajeId, sesionAprendizajeId);
        bundle.putString(mColorCurso, colorCurso);
        bundle.putInt(mProgramaEducativoId, programaEducativoId);
        return  bundle;
    }
}
