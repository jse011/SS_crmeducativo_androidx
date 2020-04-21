package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listaAlumnos;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetFotoPreferents;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetPersonas;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

import java.util.ArrayList;
import java.util.List;

public class ListaAlumnosPresenterImpl extends BasePresenterImpl<ListaAlumnosView> implements ListaAlumnosPresenter {
    private int cargacursoId;
    private GetPersonas getPersonas;
    private List<PersonaUi> personaUiList;
    private int entidadId;
    private int georeferenciaId;
    private GetFotoPreferents getFotoPreferents;

    public ListaAlumnosPresenterImpl(UseCaseHandler handler, Resources res, GetPersonas getPersonas,
                                     GetFotoPreferents getFotoPreferents) {
        super(handler, res);
        this.getPersonas = getPersonas;
        this.getFotoPreferents = getFotoPreferents;
    }

    @Override
    protected String getTag() {
        return "ListaAlumnosPresenterImplTAG";
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onClikSubirFoto(PersonaUi personaUi) {
        if(view!=null)view.showActivityPreview(personaUi.getPersonaId(), cargacursoId, entidadId, georeferenciaId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        personaUiList = getPersonas.execute(cargacursoId);
        for (PersonaUi personaUi : personaUiList){
            List<SentimientoUi> sentimientoUiList = getFotoPreferents.execute(personaUi.getPersonaId());
            List<SentimientoUi> newList = new ArrayList<>();
            newList.add(getSentimiento(1, sentimientoUiList));
            newList.add(getSentimiento(2, sentimientoUiList));
            newList.add(getSentimiento(3, sentimientoUiList));
            newList.add(getSentimiento(4, sentimientoUiList));
            newList.add(getSentimiento(5, sentimientoUiList));
            personaUi.setFotos(newList);
        }

        if(view!=null)view.showListPersonas(personaUiList);
    }


    private SentimientoUi getSentimiento(int position, List<SentimientoUi> sentimientoUiList){
        SentimientoUi sentimientoUi = new SentimientoUi();
        sentimientoUi.setPosition(position);
        int i = sentimientoUiList.indexOf(sentimientoUi);
        if(i!=-1){
            sentimientoUi=sentimientoUiList.get(i);
        }

        return sentimientoUi;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        cargacursoId = crmBundle.getCargaCursoId();
        entidadId = crmBundle.getEntidadId();
        georeferenciaId = crmBundle.getGeoreferenciaId();
    }

    @Override
    public void onResume() {
        super.onResume();
        for (PersonaUi personaUi : personaUiList){
            List<SentimientoUi> sentimientoUiList = getFotoPreferents.execute(personaUi.getPersonaId());
            List<SentimientoUi> newList = new ArrayList<>();
            newList.add(getSentimiento(1, sentimientoUiList));
            newList.add(getSentimiento(2, sentimientoUiList));
            newList.add(getSentimiento(3, sentimientoUiList));
            newList.add(getSentimiento(4, sentimientoUiList));
            newList.add(getSentimiento(5, sentimientoUiList));
            personaUi.setFotos(newList);
        }

        if(view!=null)view.showListPersonas(personaUiList);
    }
}
