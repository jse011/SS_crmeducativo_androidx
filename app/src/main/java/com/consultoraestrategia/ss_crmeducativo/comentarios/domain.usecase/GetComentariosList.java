package com.consultoraestrategia.ss_crmeducativo.comentarios.domain.usecase;


import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.ComentariosDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.ComentariosRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;
import java.util.List;
import java.util.Objects;

public class GetComentariosList extends UseCase<GetComentariosList.RequestValues,GetComentariosList.ResponseValue> {

    private ComentariosRepository repository;

    public GetComentariosList(ComentariosRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getComentariosList(requestValues.getSesionAprendizajeId(), new ComentariosDataSource.CallbackComentarios() {
            @Override
            public void onListeActividades(List<Object> comentariosUiList, int status) {
                getUseCaseCallback().onSuccess(new ResponseValue(comentariosUiList));
            }
        });

    }

    public static class RequestValues implements UseCase.RequestValues{
        private int sesionAprendizajeId;

        public RequestValues(int sesionAprendizajeId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<Object> comentarioUiList;


        public ResponseValue(List<Object> comentarioUiList) {
            this.comentarioUiList = comentarioUiList;
        }

        public List<Object> getComentarioUiList() {
            return comentarioUiList;
        }

        public void setComentarioUiList(List<Object> comentarioUiList) {
            this.comentarioUiList = comentarioUiList;
        }


    }
}
