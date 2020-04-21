package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesDataSource;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesRepository;
import java.util.List;

public class GetUnidadesList extends UseCaseSincrono<GetUnidadesList.RequestValues, GetUnidadesList.ResponseValue> {

 UnidadesRepository UnidadesRepository;

    public GetUnidadesList(UnidadesRepository unidadesRepository) {
        UnidadesRepository = unidadesRepository;
    }


    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        UnidadesRepository.getUnidadesList(requestValues.idCargaCurso, requestValues.idCalendarioPeriodo, requestValues.getIdAnioAcademico(), new UnidadesDataSource.CallbackUnidades() {
            @Override
            public void onListeUnidades(List<UnidadAprendizajeUi> UnidadesList, int status) {
                callback.onResponse(true, new ResponseValue(UnidadesList));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int idCargaCurso;
        private int idCalendarioPeriodo;
        private int idAnioAcademico;

        public int getIdCargaCurso() {
            return idCargaCurso;
        }

        public int getIdCalendarioPeriodo() {
            return idCalendarioPeriodo;
        }

        public RequestValues(int idCargaCurso, int idCalendarioPeriodo, int idAnioAcademico) {
            this.idCargaCurso = idCargaCurso;
            this.idCalendarioPeriodo = idCalendarioPeriodo;
            this.idAnioAcademico = idAnioAcademico;
        }

        public int getIdAnioAcademico() {
            return idAnioAcademico;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<UnidadAprendizajeUi> unidadAprendizajesList;

        public List<UnidadAprendizajeUi> getUnidadAprendizajesList() {
            return unidadAprendizajesList;
        }

        public ResponseValue(List<UnidadAprendizajeUi> unidadAprendizajesList) {
            this.unidadAprendizajesList = unidadAprendizajesList;
        }
    }
}
