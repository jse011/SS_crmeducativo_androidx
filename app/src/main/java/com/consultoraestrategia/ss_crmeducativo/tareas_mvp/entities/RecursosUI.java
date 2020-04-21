package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import org.parceler.Parcel;

/**
 * Created by irvinmarin on 21/11/2017.
 */
@Parcel
public class RecursosUI extends RepositorioFileUi {

    public TareasUI tarea;

    public void setTarea(TareasUI tarea) {
        this.tarea = tarea;
    }

    public TareasUI getTarea() {
        return tarea;
    }
}
