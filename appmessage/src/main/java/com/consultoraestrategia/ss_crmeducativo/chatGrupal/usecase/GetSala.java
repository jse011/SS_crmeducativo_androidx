package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

public class GetSala {
    private ChatRepository repository;

    public GetSala(ChatRepository repository) {
        this.repository = repository;
    }

    public SalaUi execute(int cargaAcademicaId, int cargaCursoId, String grupoEquipoId, TipoSalaEnum tipo) {
        return repository.getSala(cargaAcademicaId, cargaCursoId, grupoEquipoId, tipo);
    }
}
