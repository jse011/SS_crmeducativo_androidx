package com.consultoraestrategia.ss_crmeducativo.comentarios.data.source;

import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.RecursosUi;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;

import java.util.List;

public interface ComentariosDataSource {

    interface CallbackComentarios {
        void onListeActividades(List<Object> comentariosUiList, int status);
    }

    interface Callback<T> {
        void onLoad(boolean success, T item);
    }

    void getComentariosList(int sesionAprendizajeId, CallbackComentarios callbackComentarios);

    void addComentario(int sesionAprendizajeId, String contenido, CallbackComentarios callbackComentarios);

}
