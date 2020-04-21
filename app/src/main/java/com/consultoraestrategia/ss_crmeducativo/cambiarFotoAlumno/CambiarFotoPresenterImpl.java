package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.GetUploadImagen;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.SavePersona;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.ui.CambiarFotoAlumnoView;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

import java.util.List;

public class CambiarFotoPresenterImpl extends BasePresenterImpl<CambiarFotoAlumnoView> implements CambiarFotoAlumnoPresenter {

    private GetPersona getPersona;
    private SavePersona savePersona;
    private int cargacursoId;
    private PersonaUi personaChange;
    private GetUploadImagen getUploadImagen;
    private List<PersonaUi> personaUiList;

    public CambiarFotoPresenterImpl(UseCaseHandler handler, Resources res, GetPersona getPersona,SavePersona savePersona, GetUploadImagen getUploadImagen) {
        super(handler, res);
        this.getPersona = getPersona;
        this.savePersona = savePersona;
        this.getUploadImagen = getUploadImagen;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        cargacursoId = crmBundle.getCargaCursoId();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hideProgress();
        personaUiList = getPersona.execute(cargacursoId);
        if(view!=null)view.showListPersonas(personaUiList);
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void btnAddFoto(PersonaUi personaUi) {
        this.personaChange = personaUi;
        if(view!=null)view.startCropImageActivity(personaUi.getPath());
    }

    @Override
    public void onCropImageActivityResult(String uri) {
        if(personaChange!=null){
            personaChange.setPath(uri);
            savePersona.execute(personaChange);
        }
        if(personaUiList!=null) if(view!=null)view.showListPersonas(personaUiList);
        if(personaChange!=null)subirFoto(personaChange);


    }

    @Override
    public void subirFoto(PersonaUi personaUi) {

        boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessage("No tiene acceso a internet");
            return;
        }

        if(TextUtils.isEmpty(personaUi.getPath())){
            showMessage("Seleccione una imagen");
            return;
        }

        personaUi.setWorking(true);
        if(view!=null)view.updatePersona(personaUi);
        getUploadImagen.execute(personaUi, new UseCaseSincrono.Callback<PersonaUi>() {
            @Override
            public void onResponse(boolean success, PersonaUi value) {
                if(success){
                    savePersona.execute(value);
                    value.setWorking(false);
                }else {
                    value.setWorking(false);
                    showMessage(res.getString(R.string.unknown_error));
                }
                if(view!=null)view.updatePersona(value);
            }
        });

    }
}
