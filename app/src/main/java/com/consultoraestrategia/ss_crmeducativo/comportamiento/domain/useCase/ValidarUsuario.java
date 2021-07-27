package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;

import java.util.List;

public class ValidarUsuario {
    ComportamientoRepository comportamientoRepository;

    public ValidarUsuario(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

}
