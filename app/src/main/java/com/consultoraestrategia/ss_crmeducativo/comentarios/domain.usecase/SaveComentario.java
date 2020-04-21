package com.consultoraestrategia.ss_crmeducativo.comentarios.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.ComentariosDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.ComentariosRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;

import java.util.List;

public class SaveComentario  extends UseCase<SaveComentario.RequestValues,SaveComentario.ResponseValue> {

    private ComentariosRepository repository;

    public SaveComentario(ComentariosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.addComentario(requestValues.getSesionAprendizajeId(),requestValues.getContenido(), new ComentariosDataSource.CallbackComentarios() {
            @Override
            public void onListeActividades(List<Object> comentariosUiList, int status) {
                getUseCaseCallback().onSuccess(new ResponseValue(comentariosUiList));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int sesionAprendizajeId;
        private String contenido;

        public RequestValues(int sesionAprendizajeId, String contenido) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.contenido = contenido;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public String getContenido() {
            return contenido;
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
