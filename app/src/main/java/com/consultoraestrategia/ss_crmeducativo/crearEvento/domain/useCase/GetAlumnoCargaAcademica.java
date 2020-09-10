package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;

import java.util.List;

public class GetAlumnoCargaAcademica {
    private CrearEventoRepository repository;

    public GetAlumnoCargaAcademica(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public List<AlumnoUi> execute(int cargaAcademicaId, int empleadoId, String enventoId){
        return repository.getAlumnosCargaAcademica(cargaAcademicaId, empleadoId, enventoId);
    }
}
