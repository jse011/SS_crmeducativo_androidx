package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;

import java.util.List;

public class GetTipoCalendario {
    private CrearEventoRepository repository;

    public GetTipoCalendario(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public List<TipoCalendarioUi> excute(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId){
        return repository.getListTipoCalendario(usuarioId, georeferenciaId, empleadoId, anioAcademicoId);
    }
}
