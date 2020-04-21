package com.consultoraestrategia.ss_crmeducativo.comentarios.data.source;

import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.local.ComentariosLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.remote.ComentariosRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;

import java.util.List;

public class ComentariosRepository implements  ComentariosDataSource {

    private ComentariosLocalDataSource comentariosLocalDataSource;
    private ComentariosRemoteDataSource comentariosRemoteDataSource;

    public ComentariosRepository(ComentariosLocalDataSource comentariosLocalDataSource, ComentariosRemoteDataSource comentariosRemoteDataSource) {
        this.comentariosLocalDataSource = comentariosLocalDataSource;
        this.comentariosRemoteDataSource = comentariosRemoteDataSource;
    }

    @Override
    public void getComentariosList(int sesionAprendizajeId,  CallbackComentarios callbackComentarios) {
        comentariosLocalDataSource.getComentariosList(sesionAprendizajeId, callbackComentarios);

    }

    @Override
    public void addComentario(int sesionAprendizajeId, String contenido, CallbackComentarios callbackComentarios) {
    comentariosLocalDataSource.addComentario(sesionAprendizajeId,contenido,  callbackComentarios);
    }
}
