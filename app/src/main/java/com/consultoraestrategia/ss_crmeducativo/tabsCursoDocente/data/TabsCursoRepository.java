package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.local.TabsCursoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.List;

/**
 * Created by @stevecampos on 20/02/2018.
 */

public class TabsCursoRepository implements TabsCursoDataSource {

    public static final String TAG = TabsCursoRepository.class.getSimpleName();
    private TabsCursoLocalDataSource localDataSource;

    public TabsCursoRepository(TabsCursoLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }


    @Override
    public List<PeriodoUi> getPeriodoList(int cargaCursoId, int cursoId, int parametroDisenioId, int anioAcademicoId) {
        return localDataSource.getPeriodoList(cargaCursoId, cursoId, parametroDisenioId, anioAcademicoId);
    }

    @Override
    public boolean isTutor(int cargaAcademica) {
        return localDataSource.isTutor(cargaAcademica);
    }

    @Override
    public boolean isAprendizajeColegio(int entidadId, int georeferenciaId) {
        return localDataSource.isAprendizajeColegio(entidadId, georeferenciaId);
    }

    @Override
    public ParametroDisenioUi getParametroDisenio(int parametroDisenioId) {
        return localDataSource.getParametroDisenio(parametroDisenioId);
    }

    @Override
    public boolean isfirstTimeHere(int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaEduId, int usuarioId, int empleadoId, int cargaAcademicaId, int georeferenciaId, int entidadId, int cursoId) {
        return localDataSource.isfirstTimeHere(cargaCursoId, calendarioPeriodoId, silaboEventoId, programaEduId, usuarioId, empleadoId, cargaAcademicaId, georeferenciaId, entidadId, cursoId);
    }

}
