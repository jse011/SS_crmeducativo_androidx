package com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class GenerateTeamList extends UseCase<GenerateTeamList.RequestValues, GenerateTeamList.ResponseValue> {

    private TeamListRepository repository;
    private String TAG = GenerateTeamList.class.getSimpleName();

    public GenerateTeamList(TeamListRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        final List<Person> personGeneralList = new ArrayList<>();

        repository.getTeamList(requestValues.getCargaCUrsoId(), requestValues.getCantidad(), requestValues.getGroup(),requestValues.getTipo(), requestValues.getEntidadId(), requestValues.getGeoregerenciaId(), new TeamListDataSource.Callback<List<Team>>() {
            @Override
            public void onLoad(boolean estado, List<Team> item) {
                if(estado){
                  getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });

    }

    public static class RequestValues implements UseCase.RequestValues{
        private int cantidad;
        private int cargaCUrsoId;
        private Group group;
        private int tipo;
        private int entidadId;
        private int georegerenciaId;

        public RequestValues(int cantidad, int cargaCUrsoId, Group group, int tipo, int entidadId, int georegerenciaId) {
            this.cantidad = cantidad;
            this.cargaCUrsoId = cargaCUrsoId;
            this.group = group;
            this.tipo = tipo;
            this.entidadId = entidadId;
            this.georegerenciaId = georegerenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public int getGeoregerenciaId() {
            return georegerenciaId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public int getCargaCUrsoId() {
            return cargaCUrsoId;
        }

        public Group getGroup() {
            return group;
        }

        public int getTipo() {
            return tipo;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<Team> teamList;

        public ResponseValue(List<Team> teamList) {
            this.teamList = teamList;
        }

        public List<Team> getTeamList() {
            return teamList;
        }
    }
}
