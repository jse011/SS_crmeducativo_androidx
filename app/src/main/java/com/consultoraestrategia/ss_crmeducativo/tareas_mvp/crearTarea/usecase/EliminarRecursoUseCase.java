package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.CreateTareaRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.EliminarRecursoCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class EliminarRecursoUseCase extends UseCase<EliminarRecursoUseCase.RequestValues, EliminarRecursoUseCase.ResponseValue> {

    private CreateTareaRepository repository;

    public EliminarRecursoUseCase(CreateTareaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.eliminarRecurso(requestValues, new EliminarRecursoCallback() {
            @Override
            public void onRecursoDeleted(String msjCorrecto) {
                getUseCaseCallback().onSuccess(new ResponseValue(true));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final TareasUI tareasUI;
        private final RecursosUI recursosUI;

        public RequestValues(TareasUI tareasUI, RecursosUI recursosUI) {
            this.tareasUI = tareasUI;
            this.recursosUI = recursosUI;
        }

        public TareasUI getTareasUI() {
            return tareasUI;
        }

        public RecursosUI getRecursosUI() {
            return recursosUI;
        }

        @Override
        public String toString() {
            return "RequestValues{" +
                    "tareasUI=" + tareasUI +
                    ", recursosUI=" + recursosUI +
                    '}';
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final boolean sucess;

        public ResponseValue(boolean sucess) {
            this.sucess = sucess;
        }

        public boolean isSucess() {
            return sucess;
        }
    }
}
