package com.consultoraestrategia.ss_crmeducativo.dao.dia;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

public class DiaDaoImpl extends BaseIntegerDaoImpl<Dia, Dia_Table> implements DiaDao{

    private static DiaDaoImpl mInstance;

    private DiaDaoImpl() {
    }

    public static DiaDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new DiaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Dia_Table.diaId;
    }

    @Override
    protected Class<Dia> getEntityClass() {
        return Dia.class;
    }

    @Override
    protected Class<Dia_Table> getTableclass() {
        return Dia_Table.class;
    }

    @Override
    public List<Dia> obtenerPorHorarioPrograma(int programaHorarioId) {
        return SQLite.select(Utils.f_allcolumnTable(Dia_Table.ALL_COLUMN_PROPERTIES))
                .from(Dia.class)
                .innerJoin(HorarioDia.class)
                .on(Dia_Table.diaId.withTable()
                        .eq(HorarioDia_Table.idDia.withTable()))
                .innerJoin(Horario.class)
                .on(HorarioDia_Table.idHorario.withTable()
                        .eq(Horario_Table.idHorario.withTable()))
                .innerJoin(HorarioPrograma.class)
                .on(Horario_Table.idHorario.withTable()
                        .eq(HorarioPrograma_Table.idHorario.withTable()))
                .where(HorarioPrograma_Table.idHorarioPrograma.withTable()
                        .eq(programaHorarioId))
                .queryList();
    }

    @Override
    public List<Dia> obtenerPorHorario(int horarioId) {
        return SQLite.select(Utils.f_allcolumnTable(Dia_Table.ALL_COLUMN_PROPERTIES))
                .from(Dia.class)
                .innerJoin(HorarioDia.class)
                .on(Dia_Table.diaId.withTable()
                        .eq(HorarioDia_Table.idDia.withTable()))
                .where(HorarioDia_Table.idHorario.withTable()
                        .eq(horarioId))
                .queryList();
    }

    @Override
    public List<Dia> obtenerPorCargaCursoId(int cargaCursoId, int anioACademicoId) {
        //Le fatal filtral por anio acadmicoId
        Log.d(getClass().getSimpleName(),"cargaCursoId : " + cargaCursoId + "anioACademicoId: " + anioACademicoId);

        return  SQLite.select(Utils.f_allcolumnTable(Dia_Table.ALL_COLUMN_PROPERTIES))
                .from(Dia.class)
                .innerJoin(HorarioDia.class)
                .on(Dia_Table.diaId.withTable().eq(HorarioDia_Table.idDia.withTable()))
                .innerJoin(DetalleHorario.class)
                .on(HorarioDia_Table.idHorarioDia.withTable()
                        .eq(DetalleHorario_Table.idHorarioDia.withTable()))
                .innerJoin(CursosDetHorario.class)
                .on(DetalleHorario_Table.idDetalleHorario.withTable()
                        .eq(CursosDetHorario_Table.idDetHorario.withTable()))
                .innerJoin(Horario.class)
                .on(HorarioDia_Table.idHorario.withTable()
                        .eq(Horario_Table.idHorario.withTable()))
                .innerJoin(HorarioPrograma.class)
                .on(Horario_Table.idHorario.withTable()
                        .eq(HorarioPrograma_Table.idHorario.withTable()))
                .innerJoin(CargaCursos.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CursosDetHorario_Table.idCargaCurso.withTable()))
                .where(CursosDetHorario_Table.idCargaCurso.withTable().eq(cargaCursoId))
                .and(HorarioPrograma_Table.idAnioAcademico.withTable().eq(anioACademicoId))
                .groupBy(Utils.f_allcolumnTable(Dia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

    }


    @Override
    public List<Dia> obtenerPorCargaCursoId(List<Integer> CargaCursoListaId, int anioACademicoId) {

        Log.d(getClass().getSimpleName(),"cargaCursoId : " + CargaCursoListaId.size() + "anioACademicoId: " + anioACademicoId);

        return SQLite.select(Utils.f_allcolumnTable(Dia_Table.ALL_COLUMN_PROPERTIES))
                .from(Dia.class)
                .innerJoin(HorarioDia.class)
                .on(Dia_Table.diaId.withTable().eq(HorarioDia_Table.idDia.withTable()))
                .innerJoin(DetalleHorario.class)
                .on(HorarioDia_Table.idHorarioDia.withTable()
                        .eq(DetalleHorario_Table.idHorarioDia.withTable()))
                .innerJoin(CursosDetHorario.class)
                .on(DetalleHorario_Table.idDetalleHorario.withTable()
                        .eq(CursosDetHorario_Table.idDetHorario.withTable()))
                .innerJoin(Horario.class)
                .on(HorarioDia_Table.idHorario.withTable()
                        .eq(Horario_Table.idHorario.withTable()))
                .innerJoin(HorarioPrograma.class)
                .on(Horario_Table.idHorario.withTable()
                        .eq(HorarioPrograma_Table.idHorario.withTable()))
                .innerJoin(CargaCursos.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CursosDetHorario_Table.idCargaCurso.withTable()))
                .where(CursosDetHorario_Table.idCargaCurso.withTable()
                        .in(CargaCursoListaId))
                .and(HorarioPrograma_Table.idAnioAcademico.withTable().eq(anioACademicoId))
                .groupBy(Utils.f_allcolumnTable(Dia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

    }





}
