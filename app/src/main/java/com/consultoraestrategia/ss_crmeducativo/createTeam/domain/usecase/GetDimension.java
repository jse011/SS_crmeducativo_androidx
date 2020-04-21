package com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.ValidarUsuario;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;

import java.util.List;

public class GetDimension {

    CreateTeamRepository createTeamRepository;

    public GetDimension(CreateTeamRepository createTeamRepository) {
        this.createTeamRepository = createTeamRepository;
    }

    public Response execute(){
        return new Response(createTeamRepository.getDimensiones());
    }


    public class Response{
     private List<DimensionUi>dimensionUiList;

        public Response(List<DimensionUi> dimensionUiList) {
            this.dimensionUiList = dimensionUiList;
        }

        public List<DimensionUi> getDimensionUiList() {
            return dimensionUiList;
        }
    }
}
