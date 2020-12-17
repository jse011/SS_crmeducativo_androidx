package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data.CentroProcesamientoRepositorio;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;

import java.util.List;

public class GetCalendarioPeriodo {
    private CentroProcesamientoRepositorio repositorio;

    public GetCalendarioPeriodo(CentroProcesamientoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<PeriodoUi> execute(int programaId, int anioAcademicoId){
        return repositorio.getCalendarioPeriodo(programaId, anioAcademicoId);
    }

}
