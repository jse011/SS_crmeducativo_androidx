package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.DeleteFotoAlumno;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.FotoPocisionExist;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetAlumno;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetFotoFirebaseStorage;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetFotoPreferents;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveFirebaseStorage;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveFotoPocision;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveFotoPreferents;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.InicialView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.SendView;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment.SentimientoView;

import java.util.ArrayList;
import java.util.List;

public class PreviewPresenterImpl extends BasePresenterImpl<PreviewView> implements PreviewPresenter {

    private int cargaCursoId;
    private int entidadId;
    private int personaId;
    private int georeferenciaId;
    private int position;
    private GetAlumno getPersona;
    private PersonaUi personaUi;
    private ArrayList<SentimientoUi> sentimientoList = new ArrayList<>();
    private ArrayList<SentimientoUi> sentimientoListFirebase = new ArrayList<>();
    private SentimientoView sentimientoView;
    private SentimientoUi sentimientoUiSelected;
    private int flash = -1;
    private SaveFotoPocision saveFotoPocision;
    private FotoPocisionExist fotoPocisionExist;
    private DeleteFotoAlumno deleteFotoAlumno;
    private SaveFirebaseStorage saveFirebaseStorage;
    private GetFotoFirebaseStorage getFotoFirebaseStorage;
    private SendView sendView;
    private SaveFotoPreferents saveFotoPreferents;
    private InicialView inicialView;
    private GetFotoPreferents getFotoPreferents;

    public PreviewPresenterImpl(UseCaseHandler handler, Resources res, GetAlumno getPersona, SaveFotoPocision saveFotoPocision, FotoPocisionExist fotoPocisionExist, DeleteFotoAlumno deleteFotoAlumno,
                                SaveFirebaseStorage saveFirebaseStorage, GetFotoFirebaseStorage getFotoFirebaseStorage, SaveFotoPreferents saveFotoPreferents, GetFotoPreferents getFotoPreferents) {
        super(handler, res);
        this.getPersona = getPersona;
        this.saveFotoPocision = saveFotoPocision;
        this.fotoPocisionExist = fotoPocisionExist;
        this.deleteFotoAlumno = deleteFotoAlumno;
        this.saveFirebaseStorage = saveFirebaseStorage;
        this.getFotoFirebaseStorage = getFotoFirebaseStorage;
        this.saveFotoPreferents = saveFotoPreferents;
        this.getFotoPreferents = getFotoPreferents;
    }

    @Override
    protected String getTag() {
        return "PreviewPresenterImplTAG";
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        cargaCursoId = crmBundle.getCargaCursoId();
        entidadId = crmBundle.getEntidadId();
        personaId = crmBundle.getPersonaId();
        georeferenciaId = crmBundle.getGeoreferenciaId();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sentimientoList.clear();
        personaUi = getPersona.execute(personaId);
    }


    @Override
    public void onResumedFragment(SentimientoView sentimientoView, int position) {
        this.position = position;
        this.sentimientoView = sentimientoView;
        SentimientoUi sentimientoUi = new SentimientoUi();
        sentimientoUi.setPosition(position);
        int i = sentimientoList.indexOf(sentimientoUi);
        if(i!=-1){
            sentimientoUi = sentimientoList.get(i);
        }else {
            sentimientoList.add(sentimientoUi);
        }

        switch (position){
            case 1:
                sentimientoUi.setPosition(position);
                sentimientoUi.setNombre("Paso 1/5");
                break;
            case 2:
                sentimientoUi.setPosition(position);
                sentimientoUi.setNombre("Paso 2/5");
                break;
            case 3:
                sentimientoUi.setPosition(position);
                sentimientoUi.setNombre("Paso 3/5");
                break;
            case 4:
                sentimientoUi.setPosition(position);
                sentimientoUi.setNombre("Paso 4/5");
                break;
            default:
                sentimientoUi.setPosition(position);
                sentimientoUi.setNombre("Paso 5 /5");
                break;
        }

        sentimientoUi.setFoto(fotoPocisionExist.execute(position, personaId));
        sentimientoUiSelected = sentimientoUi;
        sentimientoView.setContente(sentimientoUi);
        if(sentimientoUi.getFoto()!=null){
            sentimientoView.showPreview(sentimientoUi, personaUi);
            sentimientoView.hideCamera();
        }else {
            sentimientoView.hidePreview();
            sentimientoView.showCamera();
        }

        sentimientoView.flash(flash);
    }


    @Override
    public void onBackPrincipalClicked() {
        boolean success = deleteFotoAlumno.execute(position, personaId);
        Log.d(getTag(), "Sucess "+ success);

        if(sentimientoUiSelected!=null)sentimientoUiSelected.setFoto(null);
        if(sentimientoView!=null)sentimientoView.hidePreview();
        if(sentimientoView!=null)sentimientoView.showCamera();
    }

    @Override
    public void toggleFacing() {
        if(view!=null)view.toggleFacing();
    }

    @Override
    public void onViewFlash() {
        if(view!=null)view.onViewFlash();
    }

    @Override
    public void capturePicture() {
        if(view!=null)view.capturePicture();
    }

    @Override
    public void flashOn() {
        flash=1;
        if(sentimientoView!=null)sentimientoView.flash(flash);
    }

    @Override
    public void flashOff() {
        flash=-1;
        if(sentimientoView!=null)sentimientoView.flash(flash);
    }

    @Override
    public void flashAuto() {
        flash=0;
        if(sentimientoView!=null)sentimientoView.flash(flash);
    }

    @Override
    public void onResumedSendView(SendView sendView) {
        sendView.setImgaPreview(personaUi,sentimientoList);
        this.sendView = sendView;
    }

    @Override
    public void onClickBtnSave() {
        int count = 0;
        for (SentimientoUi sentimientoUi : sentimientoList){
            if(!TextUtils.isEmpty(sentimientoUi.getFoto()))count++;
        }

        if(count<5){
            if(view!=null)view.showMessage("Serie de fotos incompleta");
            return;
        }
        if(sendView!=null)sendView.showContenDownload();
        if(sendView!=null)sendView.hideBtnSend();
        cancelSendStorage= false;
        sendStorage(0);

    }

    @Override
    public void onClickCancelar() {
        saveFirebaseStorage.cancel();
        if(sendView!=null)sendView.hideContenDownload();
        if(sendView!=null)sendView.showBtnSend();
    }

    @Override
    public void onResumedInicialView(InicialView inicialView) {

    }

    private int countFoto = 0;
    @Override
    public void setAtachInicialView(final InicialView ini) {
        this.inicialView = ini;
        countFoto = 0;
        getFotoFirebaseStorage.execute(georeferenciaId, personaId, 0, new GetFotoFirebaseStorage.Callback() {
            @Override
            public void onSucess(String url) {
                SentimientoUi sentimientoUi = new SentimientoUi();
                sentimientoUi.setPosition(1);
                sentimientoUi.setFoto(url);
                updateSentimientoPreferents(personaId,sentimientoUi);
                if(inicialView!=null)inicialView.addList(sentimientoUi);
                countFoto();
            }

            @Override
            public void onError() {
                countFoto();
            }
        });

        getFotoFirebaseStorage.execute(georeferenciaId, personaId, 1, new GetFotoFirebaseStorage.Callback() {
            @Override
            public void onSucess(String url) {
                SentimientoUi sentimientoUi = new SentimientoUi();
                sentimientoUi.setPosition(2);
                sentimientoUi.setFoto(url);
                updateSentimientoPreferents(personaId,sentimientoUi);
                if(inicialView!=null)inicialView.addList(sentimientoUi);
                 countFoto();
            }

            @Override
            public void onError() {
                 countFoto();
            }
        });

        getFotoFirebaseStorage.execute(georeferenciaId, personaId, 2, new GetFotoFirebaseStorage.Callback() {
            @Override
            public void onSucess(String url) {
                SentimientoUi sentimientoUi = new SentimientoUi();
                sentimientoUi.setPosition(3);
                sentimientoUi.setFoto(url);
                updateSentimientoPreferents(personaId,sentimientoUi);
                if(inicialView!=null)inicialView.addList(sentimientoUi);
                 countFoto();
            }

            @Override
            public void onError() {
                 countFoto();
            }
        });

        getFotoFirebaseStorage.execute(georeferenciaId, personaId, 3, new GetFotoFirebaseStorage.Callback() {
            @Override
            public void onSucess(String url) {
                SentimientoUi sentimientoUi = new SentimientoUi();
                sentimientoUi.setPosition(4);
                sentimientoUi.setFoto(url);
                updateSentimientoPreferents(personaId,sentimientoUi);
                if(inicialView!=null)inicialView.addList(sentimientoUi);
                 countFoto();
            }

            @Override
            public void onError() {
                 countFoto();
            }
        });

        getFotoFirebaseStorage.execute(georeferenciaId, personaId, 4, new GetFotoFirebaseStorage.Callback() {
            @Override
            public void onSucess(String url) {
                SentimientoUi sentimientoUi = new SentimientoUi();
                sentimientoUi.setPosition(5);
                sentimientoUi.setFoto(url);
                updateSentimientoPreferents(personaId,sentimientoUi);
                if(inicialView!=null)inicialView.addList(sentimientoUi);
                countFoto();
            }

            @Override
            public void onError() {
                 countFoto();
            }
        });

    }

    private void countFoto() {
        countFoto++;
        if(countFoto<5){
            if(inicialView!=null)inicialView.showProgress();
        }else {
            if(inicialView!=null)inicialView.hideProgress();
        }
    }

    @Override
    public void btnSiguiente() {
        if(view!=null)view.changePage(position+1);
    }

    @Override
    public void btnAtras() {
        if(view!=null)view.changePage(position-1);
    }

    @Override
    public void btnIniciar() {
        if(view!=null)view.changePage(1);
    }

    @Override
    public void onPictureTaken(String path_img, int rotation) {
        if(sentimientoUiSelected!=null)sentimientoUiSelected.setFoto(saveFotoPocision.execute(path_img, position, personaId, rotation));
        if(sentimientoView!=null)sentimientoView.showPreview(sentimientoUiSelected, personaUi);
        if(sentimientoView!=null)sentimientoView.hideCamera();
    }

    private void updateSentimientoPreferents(int personaId, SentimientoUi sentimientoUi){
        List<SentimientoUi> sentimientoUiList = getFotoPreferents.execute(personaId);
        int position = sentimientoUiList.indexOf(sentimientoUi);
        if(position!=-1)sentimientoUiList.set(position,sentimientoUi);
        else sentimientoUiList.add(sentimientoUi);
        saveFotoPreferents.execute(sentimientoUiList, personaId);
    }

    private boolean cancelSendStorage = false;
    public void sendStorage(final int position){
        saveFirebaseStorage.execute(sentimientoList.get(position).getFoto(), georeferenciaId, personaId, position,new SaveFirebaseStorage.Callback() {
            @Override
            public void onSuccess() {
                int nexPosition = position+1;
                if(cancelSendStorage){
                    if(view!=null)view.showMessage("Cancelado!");
                }else if(sentimientoList.size()>nexPosition){
                    sendStorage(nexPosition);
                }else {
                    saveFotoPreferents();
                    if(sendView!=null)sendView.hideContenDownload();
                    if(sendView!=null)sendView.hideBtnSend();
                    if(sendView!=null)sendView.onSuccesSendStorege();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(view!=null)view.exitActivity();
                        }
                    },2000);
                }
            }

            @Override
            public void onError() {
                if(sendView!=null)sendView.hideContenDownload();
                if(sendView!=null)sendView.showBtnSend();
                if(sendView!=null)sendView.onErrorSendStorege();
            }

            @Override
            public void onProgress(int progress) {
                if(sendView!=null)sendView.progress(sentimientoList.get(position).getNombre(), progress);
            }

            @Override
            public void onCancel() {
                cancelSendStorage = true;
            }
        });
    }

    private void saveFotoPreferents() {
        saveFotoPreferents.execute(sentimientoList, personaId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sentimientoView=null;
        sendView = null;
        inicialView = null;
    }
}
