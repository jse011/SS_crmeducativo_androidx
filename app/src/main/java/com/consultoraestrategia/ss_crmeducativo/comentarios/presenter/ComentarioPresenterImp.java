package com.consultoraestrategia.ss_crmeducativo.comentarios.presenter;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.comentarios.domain.usecase.GetComentariosList;
import com.consultoraestrategia.ss_crmeducativo.comentarios.domain.usecase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;

import java.util.ArrayList;
import java.util.List;

public class ComentarioPresenterImp implements ComentarioPresenter {

    private static String TAG = ComentarioPresenterImp.class.getSimpleName();
    private int sesionAprendizajeId;
    private int cargaCursoId;
    private UseCaseHandler handler;
    private GetComentariosList getComentariosList;
    private SaveComentario saveComentario;
    private ComentarioView comentarioView;
    private SesionAprendizajeUi sesionAprendizajeUis;


    public ComentarioPresenterImp(UseCaseHandler handler, GetComentariosList getComentariosList, SaveComentario saveComentario) {
        this.handler = handler;
        this.getComentariosList = getComentariosList;
        this.saveComentario = saveComentario;
    }


    @Override
    public void onAttach() {
    }

    @Override
    public void onCreateView() {
        Log.d(TAG, "onViewCreated");
    }

    private void initViewComentarios() {
        if (comentarioView != null) {
            comentarioView.clearComentarios();
            getComentarioList();
        }
    }

    private void getComentarioList() {
        Log.d(TAG, "getComentariosList");
        handler.execute(getComentariosList,
                new GetComentariosList.RequestValues(sesionAprendizajeId),
                new UseCase.UseCaseCallback<GetComentariosList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetComentariosList.ResponseValue response) {
                        Log.d(TAG, "onSuccess");
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getComentarioUiList());
                        Log.d("AGTAMANIO", "as" + response.getComentarioUiList().size());
                        if (comentarioView != null) comentarioView.showListComentarios(objects);
                        if (comentarioView != null) comentarioView.hideComentario();

                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public void onViewCreated() {
        initViewComentarios();
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void attachView(ComentarioView view) {
        this.comentarioView = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void setExtras(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    @Override
    public void onResumeFragment() {
        initViewComentarios();
    }

    @Override
    public void onClickSaveComentario(String contenido) {
        Log.d(TAG, "saveComentarios");
        handler.execute(saveComentario,
                new SaveComentario.RequestValues(sesionAprendizajeId, contenido),
                new UseCase.UseCaseCallback<SaveComentario.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveComentario.ResponseValue response) {
                        Log.d(TAG, "onSuccess");
                        List<Object> objects = new ArrayList<>();
                        objects.addAll(response.getComentarioUiList());
                        if (comentarioView != null) comentarioView.showListComentarios(objects);
                        if (comentarioView != null) comentarioView.onSaveSucceFull();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}
