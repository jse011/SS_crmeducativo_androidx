package com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.local.CrearEventoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.remoto.CrearEventoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AgendaUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class CrearEventoRepository implements CrearEventoDataSource {
    private CrearEventoLocalDataSource localDataSource;
    private CrearEventoRemoteDataSource remoteDataSource;

    public CrearEventoRepository(CrearEventoLocalDataSource localDataSource, CrearEventoRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public List<AgendaUi> getListAgenda(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {
        return localDataSource.getListAgenda(usuarioId, georeferenciaId, empleadoId, anioAcademicoId);
    }

    @Override
    public List<AlumnoUi> getAlumnosCargaAcademica(int cargaAcademicaId, int empleadoId, String enventoId) {
        return localDataSource.getAlumnosCargaAcademica(cargaAcademicaId, empleadoId, enventoId);
    }

    @Override
    public String getNombreCargaAcademica(int cargaAcademicaId) {
        return localDataSource.getNombreCargaAcademica(cargaAcademicaId);
    }

    @Override
    public String getNombreCargaCurso(int cargaCursoId) {
        return localDataSource.getNombreCargaCurso(cargaCursoId);
    }

    @Override
    public List<AlumnoUi> getAlumnosCargaCurso(int cargaCursoId, int empleadoId, String enventoId) {
        return localDataSource.getAlumnosCargaCurso(cargaCursoId, empleadoId, enventoId);
    }

    @Override
    public EventoUi getEventoDocente(String eventoId) {
        return localDataSource.getEventoDocente(eventoId);
    }

    @Override
    public List<TipoCalendarioUi> getListTipoCalendario(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {
        return localDataSource.getListTipoCalendario(usuarioId, georeferenciaId, empleadoId, anioAcademicoId);
    }

    @Override
    public List<TipoEventoUi> getTipoEventos() {
        return localDataSource.getTipoEventos();
    }

    @Override
    public RetrofitCancel saveEvento(EventoUi eventoUi, boolean publicar, Callback callback) {
        return remoteDataSource.saveEvento(eventoUi, publicar, callback);
    }
}
