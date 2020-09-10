package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;

import java.util.List;

public class GetAlumnoCargaCurso {
    private CrearEventoRepository repository;

    public GetAlumnoCargaCurso(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public List<AlumnoUi> execute(int cargaCursoId, int empleadoId, String enventoId){
        return repository.getAlumnosCargaCurso(cargaCursoId, empleadoId, enventoId);
    }
}
