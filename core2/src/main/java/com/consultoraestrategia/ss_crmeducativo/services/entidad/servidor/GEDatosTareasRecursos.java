package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 23/05/2018.
 */

public class GEDatosTareasRecursos extends BEDatosServidor {
    private List<TareasRecursosC> tareasRecursos;
    private List<RecursoDidacticoEventoC> recursoDidactico;
    private List<TareasC> tareas;
    private List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProceso;
    private List<RecursoArchivo> recursoArchivo;
    private List<Archivo> archivo;

    public GEDatosTareasRecursos() {
    }

    public List<TareasRecursosC> getTareasRecursos() {
        return tareasRecursos;
    }

    public List<RecursoDidacticoEventoC> getRecursoDidactico() {
        return recursoDidactico;
    }

    public List<TareasC> getTareas() {
        return tareas;
    }

    public void setTareasRecursos(List<TareasRecursosC> tareasRecursos) {
        this.tareasRecursos = tareasRecursos;
    }

    public void setRecursoDidactico(List<RecursoDidacticoEventoC> recursoDidactico) {
        this.recursoDidactico = recursoDidactico;
    }

    public void setTareas(List<TareasC> tareas) {
        this.tareas = tareas;
    }

    public List<TareaRubroEvaluacionProceso> getTareaRubroEvaluacionProceso() {
        return tareaRubroEvaluacionProceso;
    }

    public void setTareaRubroEvaluacionProceso(List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProceso) {
        this.tareaRubroEvaluacionProceso = tareaRubroEvaluacionProceso;
    }

    public List<RecursoArchivo> getRecursoArchivo() {
        return recursoArchivo;
    }

    public void setRecursoArchivo(List<RecursoArchivo> recursoArchivo) {
        this.recursoArchivo = recursoArchivo;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }
}
