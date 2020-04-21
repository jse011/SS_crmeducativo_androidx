package com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetCompetenciasCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class GetCompetencias extends UseCaseSincrono<GetCompetencias.RequestValues, GetCompetencias.ResponseValues> {
    private AprendizajeRepository repository;


    public GetCompetencias(AprendizajeRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValues> callback) {
        repository.getCompencias(requestValues.getUsuarioId(), requestValues.getSesionAprendizajeId(), new GetCompetenciasCallback() {
            @Override
            public void onRecursoLoad(List<Object> competenciaUis, List<CampotematicoUi> campotematicoUiList) {
                callback.onResponse(true , new ResponseValues(competenciaUis, campotematicoUiList));
            }

            @Override
            public void onError(String errot) {
                callback.onResponse(false, null);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int usuarioId;
        private int sesionAprendizajeId;

        public RequestValues(int usuarioId, int sesionAprendizajeId) {
            this.usuarioId = usuarioId;
            this.sesionAprendizajeId = sesionAprendizajeId;
        }

        public int getUsuarioId() {
            return usuarioId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValue {
        private final List<CampotematicoUi> campotematicoUiList;
        private List<Object> competenciaUis;

        public ResponseValues(List<Object> competenciaUis, List<CampotematicoUi> campotematicoUiList) {
            this.competenciaUis = competenciaUis;
            this.campotematicoUiList = campotematicoUiList;
        }

        public List<Object> getCompetenciaUis() {
            return competenciaUis;
        }

        public List<CampotematicoUi> getCampotematicoUiList() {
            return campotematicoUiList;
        }
    }
}
