package com.consultoraestrategia.ss_crmeducativo.dao.dia;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;

import java.util.List;

public interface DiaDao extends BaseIntegerDao<Dia> {

    List<Dia> obtenerPorHorarioPrograma(int programaHorarioId);

    List<Dia> obtenerPorHorario(int horarioId);

    List<Dia> obtenerPorCargaCursoId(int cargaCursoId, int anioACademicoId);
    List<Dia> obtenerPorCargaCursoId(List<Integer> CargaCursoListaId, int anioACademicoId);
}
