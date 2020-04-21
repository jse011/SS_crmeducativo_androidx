package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data;



import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.List;

/**
 * Created by @stevecampos on 20/02/2018.
 */

public interface TabsCursoDataSource {

    List<PeriodoUi>  getPeriodoList(int cargaCursoId, int cursoId, int parametroDisenioId,int anioAcademicoId);

    boolean isTutor(int cargaAcademica);

    boolean isAprendizajeColegio(int entidadId, int georeferenciaId);

    ParametroDisenioUi getParametroDisenio(int parametroDisenioId);

    boolean isfirstTimeHere(int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaEduId, int usuarioId, int empleadoId, int cargaAcademicaId, int georeferenciaId, int entidadId, int cursoId);
}
