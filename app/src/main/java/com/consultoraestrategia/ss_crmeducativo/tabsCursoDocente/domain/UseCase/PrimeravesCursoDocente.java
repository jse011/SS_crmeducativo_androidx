package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoRepository;

public class PrimeravesCursoDocente {
    private TabsCursoRepository tabsCursoRepository;

    public PrimeravesCursoDocente(TabsCursoRepository tabsCursoRepository) {
        this.tabsCursoRepository = tabsCursoRepository;
    }

    public boolean execute(int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaEduId, int usuarioId, int empleadoId, int cargaAcademicaId, int georeferenciaId, int entidadId, int cursoId){
        return this.tabsCursoRepository.isfirstTimeHere(cargaCursoId, calendarioPeriodoId, silaboEventoId, programaEduId, usuarioId, empleadoId, cargaAcademicaId, georeferenciaId, entidadId, cursoId);
    }
}
