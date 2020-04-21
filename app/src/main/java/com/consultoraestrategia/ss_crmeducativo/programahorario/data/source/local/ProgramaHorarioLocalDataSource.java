package com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.cargaCursoDao.CargaCursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dia.DiaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.horarioPrograma.HorarioProgramaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioDatSource;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraDiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProgramaHorarioLocalDataSource implements ProgramaHorarioDatSource
{

    private HorarioProgramaDao horarioProgramaDao;
    private DiaDao diaDao;
    private CursoDao cursoDao;
    private CargaCursoDao cargaCursoDao;
    private ParametrosDisenioDao parametrosDisenioDao;
    private String TAG = ProgramaHorarioLocalDataSource.class.getSimpleName();

    public ProgramaHorarioLocalDataSource(HorarioProgramaDao horarioProgramaDao, DiaDao diaDao, CursoDao cursoDao, CargaCursoDao cargaCursoDao, ParametrosDisenioDao parametrosDisenioDao) {
        this.horarioProgramaDao = horarioProgramaDao;
        this.diaDao = diaDao;
        this.cursoDao = cursoDao;
        this.cargaCursoDao = cargaCursoDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
    }

    private List<ProgramaHorarioUi> listarProgramasHorario(List<HorarioPrograma> horarioProgramaList) {
        List<ProgramaHorarioUi> programaHorarioUiList = new ArrayList<>();
            for (HorarioPrograma horarioPrograma : horarioProgramaList){
                ProgramaHorarioUi programaHorarioUi = new ProgramaHorarioUi();
                programaHorarioUi.setId(horarioPrograma.getIdHorarioPrograma());
                StringBuilder dias = new StringBuilder();
                int count = 0;
                List<Dia> diaList = diaDao.obtenerPorHorarioPrograma(horarioPrograma.getIdHorarioPrograma());
                for (Dia dia : diaList){
                    count++;
                    if(diaList.size() == count && count != 1)dias.append(" y ");
                    else if(dias.length()!=0)dias.append(", ");
                    dias.append(dia.getNombre());

                }

                Horario horario = SQLite.select()
                        .from(Horario.class)
                        .where(Horario_Table.idHorario
                                .eq(horarioPrograma.getIdHorario()))
                        .querySingle();

                if(horario!=null){
                    programaHorarioUi.setNombre(horario.getNombre());
                }else {
                    programaHorarioUi.setNombre(dias.toString());
                }
                programaHorarioUiList.add(programaHorarioUi);
            }

            return programaHorarioUiList;
    }


    @Override
    public void listarProgramasHorarioCargaCurso(int cargaCursoId, int anioAcademicoId, Callback<List<ProgramaHorarioUi>> callback) {
        try {
            callback.load(true, listarProgramasHorario(horarioProgramaDao.obtenerPorCargacurso(cargaCursoId, anioAcademicoId)));
        }catch (Exception e){
            callback.load(false, null);
        }
    }

    @Override
    public void listarProgramasHorarioProgramaEducativo(int programaEducativo, int anioAcademicoId, Callback<List<ProgramaHorarioUi>> callback) {
        try {
            callback.load(true, listarProgramasHorario(horarioProgramaDao.obtenerPorProgramaEducativo(programaEducativo, anioAcademicoId)));
        }catch (Exception e){
            callback.load(false, null);
        }
    }

    @Override
    public void listarCursosCargaCursoProgramaEducativo(int programaEducativoId, int empleadoId, int anioAcademicoId, Callback<List<CursoUi>> callback) {

        Log.d("empleadiUS", "AS" + empleadoId);
        List<CargaCursos> cargaCursosList = cargaCursoDao.obtenerPorEmpleado(empleadoId);
        List<Integer> cargaCursoIdList = new ArrayList<>();
        for (CargaCursos cargaCursos: cargaCursosList)cargaCursoIdList.add(cargaCursos.getCargaCursoId());
        List<CursoCustom> cursosList = cursoDao.obtenerPorCargaCurso(cargaCursoIdList, programaEducativoId, anioAcademicoId);
        List<CursoUi> cursoUiList = new ArrayList<>();
        int posicion = 0;
        for (CursoCustom cursos: cursosList){
            posicion++;
          CursoUi cursoUi = new CursoUi();
          cursoUi.setId(cursos.getCursoId());
          cursoUi.setCargaCursoId(cursos.getCargaCursoId());
          cursoUi.setNombre(cursos.getNombre());
          cursoUi.setDetalle(String.valueOf(cursos.getPeriodo() + " - " + cursos.getSeccion()));
          cursoUi.setPosicion(posicion);

          ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cursos.getCargaCursoId());
          if(parametrosDisenio!=null){
              cursoUi.setColor1(parametrosDisenio.getColor1());
              cursoUi.setColor2(parametrosDisenio.getColor2());
              cursoUi.setColor3(parametrosDisenio.getColor3());
          }

          cursoUiList.add(cursoUi);
        }
        callback.load(true, cursoUiList);
    }

    @Override
    public void obtenerCursosPorCargaCurso(int cargaCursoId, int programaEducativoId, int anioAcademicoId, Callback<List<CursoUi>> callback) {


        List<Integer> cargaCursoIdList = new ArrayList<>();
        cargaCursoIdList.add(cargaCursoId);

        List<CursoCustom> cursosList = cursoDao.obtenerPorCargaCurso(cargaCursoIdList, programaEducativoId, anioAcademicoId);
        List<CursoUi> cursoUiList = new ArrayList<>();
        int posicion = 0;
        for (CursoCustom cursos: cursosList){
            posicion++;
            CursoUi cursoUi = new CursoUi();
            cursoUi.setId(cursos.getCursoId());
            cursoUi.setCargaCursoId(cursos.getCargaCursoId());
            cursoUi.setNombre(cursos.getNombre());
            cursoUi.setDetalle(String.valueOf(cursos.getPeriodo() + " - " + cursos.getSeccion()));
            cursoUi.setPosicion(posicion);

            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cursos.getCargaCursoId());
            if(parametrosDisenio!=null){
                cursoUi.setColor1(parametrosDisenio.getColor1());
                cursoUi.setColor2(parametrosDisenio.getColor2());
                cursoUi.setColor3(parametrosDisenio.getColor3());
            }

            cursoUiList.add(cursoUi);
        }
        callback.load(true, cursoUiList);
    }

    @Override
    public void listarDias(List<Integer> horarioProgramaIdList, Callback<List<DiaUi>> callback) {
        List<DiaUi> diaUiList = new ArrayList<>();
        //region Dia
        for (Integer itemHorarioProgramaId : horarioProgramaIdList){
            List<Dia> diaList = diaDao.obtenerPorHorarioPrograma(itemHorarioProgramaId);
            for (Dia dia: diaList){
                DiaUi diaUi = new DiaUi();
                diaUi.setId(dia.getDiaId());
                diaUi.setNombre(dia.getNombre());
                diaUi.setAlias(dia.getAlias());
                switch (dia.getDiaId()){
                    case 1:
                        diaUi.setColor(DiaUi.COLOR.AZUL);
                        break;
                    case 2:
                        diaUi.setColor(DiaUi.COLOR.AMARILLO);
                        break;
                    case 3:
                        diaUi.setColor(DiaUi.COLOR.VERDE);
                        break;
                    case 4:
                        diaUi.setColor(DiaUi.COLOR.ROSADO);
                        break;
                    case 5:
                        diaUi.setColor(DiaUi.COLOR.ANARANJADO);
                        break;
                    case 6:
                        diaUi.setColor(DiaUi.COLOR.GUINDA);
                        break;
                    case 7:
                        diaUi.setColor(DiaUi.COLOR.PLOMO);
                        break;
                }
                diaUiList.add(diaUi);
            }
        }


        Collections.sort(diaUiList, new Comparator<DiaUi>() {
            @Override
            public int compare(DiaUi o1, DiaUi o2) {
                int result = 0;
                if (o1.getId() < o2.getId()) {
                        result = -1;
                }
                if (o1.getId() > o2.getId()) {
                        result = 1;
                }
                return result;
            }
        });

        //endregion

        callback.load(true, diaUiList);

    }

    @Override
    public void listarHoras(int horarioProgramaId, Callback<List<HoraUi>> callback) {

        List<HoraUi> horaUis = new ArrayList<>();
        List<DetalleHorario> horarioDiaList =  SQLite.select(Utils.f_allcolumnTable(DetalleHorario_Table.ALL_COLUMN_PROPERTIES))
                .from(DetalleHorario.class)
                .innerJoin(HorarioDia.class)
                .on(DetalleHorario_Table.idHorarioDia.withTable()
                        .eq(HorarioDia_Table.idHorarioDia.withTable()))
                .innerJoin(Horario.class)
                .on(HorarioDia_Table.idHorario.withTable()
                        .eq(Horario_Table.idHorario.withTable()))
                .innerJoin(HorarioPrograma.class)
                .on(Horario_Table.idHorario.withTable()
                        .eq(HorarioPrograma_Table.idHorario.withTable()))
                .where(HorarioPrograma_Table.idHorarioPrograma.eq(horarioProgramaId))
                .and(DetalleHorario_Table.idTipoTurno.is(DetalleHorario.TIPO_TURNO_MAÑANA))
                .and(HorarioPrograma_Table.activo.is(1))
                .orderBy(DetalleHorario_Table.horaInicio.asc())
                .groupBy(Utils.f_allcolumnTable(DetalleHorario_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        for (DetalleHorario detalleHorario : horarioDiaList) {
            HoraUi horaUi;
            if(DetalleHorario.TIPO_HORA_ACADEMICO == detalleHorario.getIdTipoHora()){
                horaUi = new HoraUi();
            }else {
                horaUi = new BanerUi();
            }
            horaUi.setDesde(detalleHorario.getHoraInicio());
            horaUi.setHasta(detalleHorario.getHoraFin());
            int posicion = horaUis.indexOf(horaUi);
            if(posicion==-1){
                horaUis.add(horaUi);
            }
        }
        callback.load(true, horaUis);
    }

    @Override
    public void listarHorasporCursos(int cargaCursoId, int programaHorarioId, Callback<List<HoraDiaUi>> callback) {

        Log.d(TAG, "cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "programaHorarioId: " + programaHorarioId);
        List<HorarioDia> horarioDiaList =  SQLite.select(Utils.f_allcolumnTable(HorarioDia_Table.ALL_COLUMN_PROPERTIES))
                .from(HorarioDia.class)
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
                .where(CursosDetHorario_Table.idCargaCurso.withTable()
                        .eq(cargaCursoId))
                .and(HorarioPrograma_Table.idHorarioPrograma.eq(programaHorarioId))
                .groupBy(Utils.f_allcolumnTable(HorarioDia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();


        List<HoraDiaUi> horaDiaUiList = new ArrayList<>();
        for (HorarioDia horarioDia : horarioDiaList){
            HoraDiaUi horaDiaUi = new HoraDiaUi();
            horaDiaUi.setId(horarioDia.getIdHorarioDia());
            horaDiaUi.setDiaId(horarioDia.getIdDia());

            List<DetalleHorario> detalleHorarioList = SQLite.select(Utils.f_allcolumnTable(DetalleHorario_Table.ALL_COLUMN_PROPERTIES))
                    .from(DetalleHorario.class)
                    .innerJoin(HorarioDia.class)
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
                    .where(CursosDetHorario_Table.idCargaCurso.withTable()
                            .eq(cargaCursoId))
                    .and(HorarioPrograma_Table.idHorarioPrograma.eq(programaHorarioId))
                    .and(HorarioDia_Table.idHorarioDia.withTable().eq(horarioDia.getIdHorarioDia()))
                    .queryList();

            List<HoraUi> horaUiList = new ArrayList<>();
            for (DetalleHorario detalleHorario : detalleHorarioList){
                HoraUi horaUi = new HoraUi();
                horaUi.setDesde(detalleHorario.getHoraInicio());
                horaUi.setHasta(detalleHorario.getHoraFin());
                horaUiList.add(horaUi);
            }
            horaDiaUi.setHoraUiList(horaUiList);
            horaDiaUiList.add(horaDiaUi);
        }

        callback.load(true, horaDiaUiList);

    }

}
