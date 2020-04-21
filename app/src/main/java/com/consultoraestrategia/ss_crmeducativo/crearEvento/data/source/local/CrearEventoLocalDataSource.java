package com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.local;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class CrearEventoLocalDataSource implements CrearEventoDataSource {

    @Override
    public EventoUi getEventoDocente(String eventoId) {
        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(eventoId))
                .querySingle();
        EventoUi eventoUi =  new EventoUi();
        if(evento!=null){
            eventoUi.setId(evento.getEventoId());
            eventoUi.setCalendarioId(evento.getCalendarioId());
            eventoUi.setTipoEventoId(evento.getTipoEventoId());
            eventoUi.setNombre(evento.getTitulo());
            eventoUi.setDescripcion(evento.getDescripcion());
            eventoUi.setFecha(evento.getFechaEvento());
            eventoUi.setHora(evento.getHoraEvento());
            eventoUi.setPath(evento.getPathImagen());

        }
        return eventoUi;
    }

    @Override
    public List<TipoCalendarioUi> getListTipoCalendario(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {



        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                .and(CargaCursos_Table.complejo.withTable().eq(0))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        List<CargaCursos> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);

        List<TipoCalendarioUi> tipoCalendarioUiList = new ArrayList<>();

        List<Calendario> calendarioList = SQLite.select()
                .from(Calendario.class)
                .where(Calendario_Table.georeferenciaId.eq(georeferenciaId))
                .and(Calendario_Table.usuarioId.eq(usuarioId))
                .queryList();

        for (Calendario calendario: calendarioList){
            TipoCalendarioUi tipoCalendarioUi = new TipoCalendarioUi();
            tipoCalendarioUi.setId(calendario.getCalendarioId());
            tipoCalendarioUi.setNombre(calendario.getNombre());
            tipoCalendarioUi.setSelecionado(false);
            tipoCalendarioUiList.add(tipoCalendarioUi);
        }

        return tipoCalendarioUiList;
    }

    @Override
    public List<TipoEventoUi> getTipoEventos() {
        List<TipoEventoUi> tiposEventosUiList = new ArrayList<>();
        List<Tipos> tiposList = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.objeto.eq("T_CE_MOV_EVENTOS"))
                .and(Tipos_Table.concepto.eq("TipoEvento"))
                .queryList();

        for (Tipos tipos: tiposList) {
            TipoEventoUi tiposEventosUi = new TipoEventoUi();
            tiposEventosUi.setId(tipos.getTipoId());
            tiposEventosUi.setNombre(tipos.getNombre());
            tiposEventosUi.setSelecionado(false);
            tiposEventosUiList.add(tiposEventosUi);
        }

        return tiposEventosUiList;
    }

    @Override
    public RetrofitCancel saveEvento(EventoUi eventoUi, Callback callback) {

        return null;
    }
}
