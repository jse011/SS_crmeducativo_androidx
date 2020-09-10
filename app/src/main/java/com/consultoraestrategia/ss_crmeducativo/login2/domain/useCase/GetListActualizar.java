package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;

import java.util.ArrayList;
import java.util.List;

public class GetListActualizar {
    private LoginDataRepository repositorio;

    public GetListActualizar(LoginDataRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<ActualizarUi> getServicioEnvio(int usuarioId, int empleadoId, int programaEducativoId, int calendarioPeriodoId, int cargaCursoId, int georeferenciaId, int entidadId, int silaboEventoId, int cursoId, int cargaAcademicaId, boolean cursoComplejo){



        ActualizarUi unidades = new ActualizarUi();
        unidades.setNombre("Unidades");
        unidades.setTipo(ActualizarTipoUi.Unidades);
        unidades.setCargacursoId(cargaCursoId);
        unidades.setCalendarioPeriodoId(calendarioPeriodoId);
        unidades.setSilaboEventoId(silaboEventoId);
        unidades.generarId();
        unidades.setFecha(repositorio.getTimeSesionData(unidades));
        unidades.setEncoloa(false);


        ActualizarUi tipoNota = new ActualizarUi();
        tipoNota.setNombre("Nivel de logro");
        tipoNota.setTipo(ActualizarTipoUi.TipoNota);
        tipoNota.setProgramaEducativoId(programaEducativoId);
        tipoNota.setUsuarioId(usuarioId);
        tipoNota.generarId();
        tipoNota.setFecha(repositorio.getTimeSesionData(tipoNota));
        tipoNota.setEncoloa(false);


        ActualizarUi estudiantes = new ActualizarUi();
        estudiantes.setNombre("Estudiantes y sus familias");
        estudiantes.setTipo(ActualizarTipoUi.Estudiantes);
        estudiantes.setCargacursoId(cargaCursoId);
        estudiantes.setDocenteId(empleadoId);
        estudiantes.setCargaAcademicaId(cargaAcademicaId);
        estudiantes.generarId();
        estudiantes.setFecha(repositorio.getTimeSesionData(estudiantes));
        estudiantes.setEncoloa(false);



        ActualizarUi rubros = new ActualizarUi();
        rubros.setNombre("Rubro evaluación");
        rubros.setTipo(ActualizarTipoUi.Rubros);
        rubros.setCargacursoId(cargaCursoId);
        rubros.setCalendarioPeriodoId(calendarioPeriodoId);
        rubros.setSilaboEventoId(silaboEventoId);
        rubros.generarId();
        rubros.setFecha(repositorio.getTimeSesionData(rubros));
        rubros.setEncoloa(false);



        ActualizarUi resultado = new ActualizarUi();
        resultado.setNombre("Resultado evaluación");
        resultado.setTipo(ActualizarTipoUi.Resultado);
        resultado.setCargacursoId(cargaCursoId);
        resultado.setCalendarioPeriodoId(calendarioPeriodoId);
        resultado.setSilaboEventoId(silaboEventoId);
        resultado.generarId();
        resultado.setFecha(repositorio.getTimeSesionData(resultado));
        resultado.setEncoloa(false);

        ActualizarUi grupos = new ActualizarUi();
        grupos.setNombre("Grupos");
        grupos.setTipo(ActualizarTipoUi.Grupos);
        grupos.setCargacursoId(cargaCursoId);
        grupos.generarId();
        grupos.setFecha(repositorio.getTimeSesionData(grupos));
        grupos.setEncoloa(false);


        ActualizarUi tareas = new ActualizarUi();
        tareas.setNombre("Tarea");
        tareas.setTipo(ActualizarTipoUi.Tareas);
        tareas.setCargacursoId(cargaCursoId);
        tareas.setSilaboEventoId(silaboEventoId);
        tareas.setCalendarioPeriodoId(calendarioPeriodoId);
        tareas.generarId();
        tareas.setFecha(repositorio.getTimeSesionData(tareas));
        tareas.setEncoloa(false);


        ActualizarUi casos = new ActualizarUi();
        casos.setNombre("Casos");
        casos.setTipo(ActualizarTipoUi.Casos);
        casos.setCargacursoId(cargaCursoId);
        casos.setCalendarioPeriodoId(calendarioPeriodoId);
        casos.setGeoreferenciaId(georeferenciaId);
        casos.setEntidadId(entidadId);
        casos.generarId();
        casos.setFecha(repositorio.getTimeSesionData(casos));
        casos.setEncoloa(false);


        ActualizarUi asistencia = new ActualizarUi();
        asistencia.setNombre("Asistencia");
        asistencia.setTipo(ActualizarTipoUi.Asistencias);
        asistencia.setCargacursoId(cargaCursoId);
        asistencia.setCalendarioPeriodoId(calendarioPeriodoId);
        asistencia.generarId();
        asistencia.setFecha(repositorio.getTimeSesionData(asistencia));
        asistencia.setEncoloa(false);


        ActualizarUi docente = new ActualizarUi();
        docente.setNombre("Docente");
        docente.setTipo(ActualizarTipoUi.Docente);
        docente.setGeoreferenciaId(georeferenciaId);
        docente.generarId();
        docente.setFecha(repositorio.getTimeSesionData(docente));
        docente.setEncoloa(false);


        ActualizarUi dimencionDesarrollo = new ActualizarUi();
        dimencionDesarrollo.setNombre("Dimensión desarrollo");
        dimencionDesarrollo.setTipo(ActualizarTipoUi.Dimencion_Desarrollo);
        dimencionDesarrollo.setCursoId(cursoId);
        dimencionDesarrollo.setCargacursoId(cargaCursoId);
        dimencionDesarrollo.setEntidadId(entidadId);
        dimencionDesarrollo.setGeoreferenciaId(georeferenciaId);
        dimencionDesarrollo.setDocenteId(empleadoId);
        dimencionDesarrollo.generarId();
        dimencionDesarrollo.setFecha(repositorio.getTimeSesionData(dimencionDesarrollo));
        dimencionDesarrollo.setEncoloa(false);

        /*if(rubrica.getFecha()==0||rubros.getFecha()==0||formula.getFecha()==0||resultado.getFecha()==0){
            tipoNota.setEncoloa(true);
            contratos.setEncoloa(true);
        }

        if(rubrica.getFecha()==0||rubros.getFecha()==0){
            grupos.setEncoloa(true);
        }*/

        List<ActualizarUi> actualizarUiList = new ArrayList<>();
        actualizarUiList.add(unidades);

        actualizarUiList.add(estudiantes);

        actualizarUiList.add(tipoNota);

        //actualizarUiList.add(rubrica);
        //actualizarUiList.add(formula);
        actualizarUiList.add(grupos);
        actualizarUiList.add(tareas);
        actualizarUiList.add(casos);
        actualizarUiList.add(dimencionDesarrollo);
        actualizarUiList.add(rubros);
        actualizarUiList.add(resultado);
        //actualizarUiList.add(docente);
        //actualizarUiList.add(asistencia);
        return actualizarUiList;
    }
}
