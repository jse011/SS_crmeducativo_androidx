package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;

import java.util.List;

public class BEDatosTarea {
    private List<TareasC> tarea;

    private long fecha_servidor;

    private List<TareasRecursosC> tareasRecursos;

    private List<RecursoArchivo> recursoArchivo;

    private List<RecursoDidacticoEventoC> recursoDidactico;

    private List<Archivo> archivo;

    //private List<TareaRubroEvaluacionProceso> tareaRubroEvaluacion;

    public List<TareasC> getTarea() {
        return tarea;
    }

    public void setTarea(List<TareasC> tarea) {
        this.tarea = tarea;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    public List<TareasRecursosC> getTareasRecursos() {
        return tareasRecursos;
    }

    public void setTareasRecursos(List<TareasRecursosC> tareasRecursos) {
        this.tareasRecursos = tareasRecursos;
    }

    public List<RecursoArchivo> getRecursoArchivo() {
        return recursoArchivo;
    }

    public void setRecursoArchivo(List<RecursoArchivo> recursoArchivo) {
        this.recursoArchivo = recursoArchivo;
    }

    public List<RecursoDidacticoEventoC> getRecursoDidactico() {
        return recursoDidactico;
    }

    public void setRecursoDidactico(List<RecursoDidacticoEventoC> recursoDidactico) {
        this.recursoDidactico = recursoDidactico;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

}
