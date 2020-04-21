package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.camera;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.SaveImgen;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

public class CameraReconocimientoImpl extends BasePresenterImpl<CameraReconocimientoView> implements CameraReconocimientoPresenter  {

    private GetPersona getPersona;
    private int usuarioId;
    private PersonaUi personaUi;
    private SaveImgen saveImage;
    private boolean succesProgress;

    public CameraReconocimientoImpl(UseCaseHandler handler, Resources res, GetPersona getPersona, SaveImgen saveImage) {
        super(handler, res);
        this.getPersona = getPersona;
        this.saveImage = saveImage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.personaUi = getPersona.execute(usuarioId);
        if(view!=null)view.setCamaraPersona(personaUi.getNombre());
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        this.usuarioId = crmBundle.getUsuarioId();
    }

    @Override
    protected String getTag() {
        return CameraReconocimientoImpl.class.getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onPictureTaken(byte[] jpeg, int mOrientation) {
        saveImage.execute(personaUi, jpeg, mOrientation, new SaveImgen.CallBack() {
            @Override
            public void onProgress(int progress) {
                if(view!=null)view.showBarProgress(progress);
                succesProgress = progress==100;
            }

            @Override
            public void onSuccess() {
                if(view!=null)view.successProgress();
            }

            @Override
            public void onError() {
                if(view!=null)view.errorProgress();
            }
        });
    }

    @Override
    public void onClickReintentarEnvio() {
        saveImage.multiplEnvio(new SaveImgen.CallBack() {
            @Override
            public void onProgress(int progress) {
                if(view!=null)view.showBarProgress(progress);
                succesProgress = progress==100;
            }

            @Override
            public void onSuccess() {
                if(view!=null)view.successProgress();
            }

            @Override
            public void onError() {
                if(view!=null)view.errorProgress();
            }
        });
    }
}
