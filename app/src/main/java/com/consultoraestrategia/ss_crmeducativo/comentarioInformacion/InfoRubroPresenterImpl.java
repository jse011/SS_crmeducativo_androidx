package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion;

import android.content.res.Resources;
import android.net.Uri;
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
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.UploadArchivo;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoComentarioDialog;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoRubroView;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;

import java.util.Map;


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
    private UploadArchivo uploadArchivo;

    public InfoRubroPresenterImpl(UseCaseHandler handler, Resources res,GetComentarioPred getComentarioPred, GetArchivoComentario getArchivoComentario,SaveComentario saveComentario, DeleteComentario deleteComentario,SaveArchivoRubro saveArchivoRubro, DeleteArchivoComentario deleteArchivoComentario, UploadArchivo uploadArchivo) {
        super(handler, res);
        this.getComentarioPred = getComentarioPred;
        this.getArchivoComentario = getArchivoComentario;
        this.saveComentario = saveComentario;
        this.deleteComentario = deleteComentario;
        this.saveArchivoRubro = saveArchivoRubro;
        this.deleteArchivoComentario = deleteArchivoComentario;
        this.uploadArchivo = uploadArchivo;
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
    public void onClickCamera() {
        if(view!=null)view.showCamera();
    }

    @Override
    public void onClickGalery() {
        if(view!=null)view.showGalery();
    }


    @Override
    public void removerComentarioArchivo(ArchivoUi archivoComentarioUi) {
        deleteArchivoComentario.execute(new DeleteArchivoComentario.Requests(archivoComentarioUi));
        if(view!=null)view.removeTareaArchivo(archivoComentarioUi);
    }

    @Override
    public void onUpdload(Map<Uri, String> photoPaths) {
        for (Map.Entry<Uri, String> entry : photoPaths.entrySet()) {
            String fileName = entry.getValue();
            ArchivoUi archivoUi = new ArchivoUi();
            archivoUi.setId(IdGenerator.generateId());
            archivoUi.setEvaluacionProcesoId(evaluacionProcesoId);
            archivoUi.setNombre(fileName);
            archivoUi.setUri(entry.getKey());

            uploadArchivo.execute(archivoUi, new UploadArchivo.CallbackProgress<ArchivoUi>() {
                @Override
                public void onProgress(int count, ArchivoUi item) {
                    item.setProgress(count);
                    if(view!=null)view.updateTareaArchivo(item);
                }

                @Override
                public void onLoad(boolean success, ArchivoUi item) {
                    if(success){
                        if(view!=null)view.updateTareaArchivo(item);
                    }else {
                        if(view!=null)view.removeTareaArchivo(item);
                    }
                }

                @Override
                public void onPreLoad(ArchivoUi item) {
                    if(view!=null)view.addTareaArchivo(item);
                }
            });
        }
    }
}
