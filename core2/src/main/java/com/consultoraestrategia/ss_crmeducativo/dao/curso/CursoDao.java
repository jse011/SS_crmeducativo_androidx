package com.consultoraestrategia.ss_crmeducativo.dao.curso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;

import java.util.List;

public interface CursoDao extends BaseIntegerDao<Cursos> {
    List<CursoCustom> obtenerPorProgramaEducativo(int programaEducativoId, int anioAcademicoId);
    List<CursoCustom> obtenerPorCargaCurso(List<Integer> cargacursoIdList, int programaEducativoId, int anioAcademicoId);
    List<CursoCustom> obtenerPorCargaCursos(List<Integer> integerList);
    List<CursoCustom> obtenerPorCargaAcademicaDocente( int idProgramaEducativo,List<Integer> integerList, int anioAcademicoId);
}
