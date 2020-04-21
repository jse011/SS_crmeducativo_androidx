package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetTareasUIList extends UseCase<GetTareasUIList.RequestValues, GetTareasUIList.ResponseValue> {

    private TareasMvpRepository repository;

    public GetTareasUIList(TareasMvpRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getTareasUIList(requestValues.getIdUsuario(), requestValues.getIdCargaCurso(), requestValues.getTipoTarea(), requestValues.getmSesionAprendizajeId(), requestValues.getCalendarioPeriodoId(),requestValues.getAnioAcademicoId(),
                new GetTareasListCallback() {
                    @Override
                    public void onTareasListLoaded(List<HeaderTareasAprendizajeUI> headerTareasAprendizajeUIList) {
                        getUseCaseCallback().onSuccess(new ResponseValue(headerTareasAprendizajeUIList));
                    }

                    @Override
                    public void onError(String error) {

                    }
                });


    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int idUsuario;
        private final int idCargaCurso;
        private final int tipoTarea;
        private final int mSesionAprendizajeId;
        private int calendarioPeriodoId;
        private int anioAcademicoId;

        public RequestValues(int idUsuario, int idCargaCurso, int tipoTarea, int mSesionAprendizajeId, int calendarioPeriodoId, int anioAcademicoId) {
            this.idUsuario = idUsuario;
            this.idCargaCurso = idCargaCurso;
            this.tipoTarea = tipoTarea;
            this.mSesionAprendizajeId = mSesionAprendizajeId;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getmSesionAprendizajeId() {
            return mSesionAprendizajeId;
        }

        public int getIdCargaCurso() {
            return idCargaCurso;
        }

        public int getTipoTarea() {
            return tipoTarea;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }

        public void setAnioAcademicoId(int anioAcademicoId) {
            this.anioAcademicoId = anioAcademicoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<HeaderTareasAprendizajeUI> headerTareasAprendizajeUIList;

        public List<HeaderTareasAprendizajeUI> getHeaderTareasAprendizajeUIList() {
            return headerTareasAprendizajeUIList;
        }

        public ResponseValue(List<HeaderTareasAprendizajeUI> headerTareasAprendizajeUIList) {
            this.headerTareasAprendizajeUIList = headerTareasAprendizajeUIList;
        }
    }
}
