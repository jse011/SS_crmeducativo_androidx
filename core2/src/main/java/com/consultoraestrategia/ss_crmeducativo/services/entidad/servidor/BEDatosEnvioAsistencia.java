package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.ArchivoAsistencia;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.JustificacionC;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosEnvioAsistencia extends BEDatosServidor {
    private List<AsistenciaSesionAlumnoC> asistenciaAlumnos;
    //public List<BEJustifacion> obtenerjustificacion{ get; set; }
    private List<JustificacionC> justificacion;//Ya existia la entidad
    private List<ArchivoAsistencia> archivoAsistencia;


    public BEDatosEnvioAsistencia() {
    }

    public List<AsistenciaSesionAlumnoC> getAsistenciaAlumnos() {
        return asistenciaAlumnos;
    }

    public void setAsistenciaAlumnos(List<AsistenciaSesionAlumnoC> asistenciaAlumnos) {
        this.asistenciaAlumnos = asistenciaAlumnos;
    }

    public List<JustificacionC> getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(List<JustificacionC> justificacion) {
        this.justificacion = justificacion;
    }

    public List<ArchivoAsistencia> getArchivoAsistencia() {
        return archivoAsistencia;
    }

    public void setArchivoAsistencia(List<ArchivoAsistencia> archivoAsistencia) {
        this.archivoAsistencia = archivoAsistencia;
    }

}
