package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.DeleteArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.DeleteComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.GetArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.GetComentarioPred;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.SaveArchivoRubro;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoComentarioDialog;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoRubroView;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;


/**
 * Created by Jse on 15/09/2018.
 */

public class InfoRubroPresenterImpl extends BaseFragmentPresenterImpl<InfoRubroView> implements InfoRubroPresenter {

    private String evaluacionProcesoId;
    private int cargaCursoId;
    private String TAG =InfoRubroPresenterImpl.class.getSimpleName();
    private GetArchivoComentario getArchivoComentario;
    private GetComentarioPred getComentarioPred;
    private SaveComentario saveComentario;
    private DeleteComentario deleteComentario;
    private SaveArchivoRubro saveArchivoRubro;
    private DeleteArchivoComentario deleteArchivoComentario;


    public InfoRubroPresenterImpl(UseCaseHandler handler, Resources res,GetComentarioPred getComentarioPred, GetArchivoComentario getArchivoComentario,SaveComentario saveComentario, DeleteComentario deleteComentario,SaveArchivoRubro saveArchivoRubro, DeleteArchivoComentario deleteArchivoComentario) {
        super(handler, res);
        this.getComentarioPred = getComentarioPred;
        this.getArchivoComentario = getArchivoComentario;
        this.saveComentario = saveComentario;
        this.deleteComentario = deleteComentario;
        this.saveArchivoRubro = saveArchivoRubro;
        this.deleteArchivoComentario = deleteArchivoComentario;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void onActivityCreated() {
        super.onActivityCreated();
        changeRubroArchivo();
        changeComentario();
    }

    private void changeComentario(){
        GetComentarioPred.Response response=getComentarioPred.execute(new GetComentarioPred.Requests(evaluacionProcesoId));
        if(view!=null)view.showListComentarios(response.getMensajeUiList());
    }

    private void changeRubroArchivo() {
            if(view!=null)view.showListComentariosArchivos(getArchivoComentario.execute(new GetArchivoComentario.Response(evaluacionProcesoId)));
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.evaluacionProcesoId= extras.getString(InfoComentarioDialog.ID_EVALUACION_PROCESO);
    }

    @Override
    public void closeDialog() {
       if(view!=null)view.cerrarDialog(evaluacionProcesoId);
    }

    @Override
    public void onTextChangedEditComentario(String toString) {
        if(toString.length()>0){
            if(view!=null)view.showBtnSendComentario();
        }else {
            if(view!=null)view.hideBtnSendComentario();
        }
    }

    @Override
    public void saveComentario(String comentario) {
        Log.d(TAG,"Cometario :" + comentario);
        MensajeUi mensajeUi= new MensajeUi();
        mensajeUi.setId(IdGenerator.generateId());
        mensajeUi.setDescripcion(comentario);
        mensajeUi.setEvaluacionId(evaluacionProcesoId);
        if(view!=null)view.clearTextInpuComentario();
        if(view!=null)view.hideBtnSendComentario();
        SaveComentario.Response response= saveComentario.execute(new SaveComentario.Requests(mensajeUi));
        if(response.isSuccess()) changeComentario();
    }

    @Override
    public void onClickComentarioNormal(MensajeUi mensajeUi) {
        DeleteComentario.Response response = deleteComentario.execute(new DeleteComentario.Requests(mensajeUi));
        if(response.isSuccess()) changeComentario();
    }
    @Override
    public void saveComentarioArchivo(ArchivoComentarioUi archivoComentarioUi) {
        archivoComentarioUi.setEvaluacionId(evaluacionProcesoId);
        saveArchivoRubro.execute(new SaveArchivoRubro.Requests(archivoComentarioUi));

    }
    @Override
    public void removerComentarioArchivo(ArchivoComentarioUi archivoComentarioUi) {
        deleteArchivoComentario.execute(new DeleteArchivoComentario.Requests(archivoComentarioUi));
    }
}
