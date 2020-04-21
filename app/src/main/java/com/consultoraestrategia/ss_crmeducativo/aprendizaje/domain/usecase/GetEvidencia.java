package com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;

import java.util.List;

public class GetEvidencia  {

    private AprendizajeRepository aprendizajeRepository;

    public GetEvidencia(AprendizajeRepository aprendizajeRepository) {
        this.aprendizajeRepository = aprendizajeRepository;
    }

    public List<EvidenciaUi> excute(int sesionAprenId){
        return aprendizajeRepository.getEvidencias(sesionAprenId);
    }
}
