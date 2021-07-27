package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.CreateComportamientoView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetParametroDisenio;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.SaveComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public class CreateComportamientoPresenterImpl extends BaseFragmentPresenterImpl<CreateComportamientoView>  implements CreateComportamientoPresenter{

    SaveComportamiento saveComportamiento;
    GetParametroDisenio getParametroDisenio;
    String TAG= CreateComportamientoPresenterImpl.class.getSimpleName();
    private int idCargaCurso;
    private int programaEducativoId;


    public CreateComportamientoPresenterImpl(UseCaseHandler handler, Resources res,  SaveComportamiento saveComportamiento, GetParametroDisenio getParametroDisenio) {
        super(handler, res);
        this.saveComportamiento=saveComportamiento;
        this.getParametroDisenio=getParametroDisenio;
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void saveComportamiento(ComportamientoUi comportamientoUi, DestinoUi destinatarios, List<RepositorioFileUi> repositorioFileUiList) {
        if(comportamientoUi.getAlumnoUi()!=null){
            if(comportamientoUi.getTipoComportamientoUi()==null) {if(view!=null)view.showMessage(res.getString(R.string.msg_empty_tipo_comportamiento));}
            else {


                SaveComportamiento.Response response= saveComportamiento.execute(new SaveComportamiento.Request(comportamientoUi, destinatarios,repositorioFileUiList));
                Log.d(TAG, "success " +response.isSuccess() );
                if(response.isSuccess()){
                    if(comportamientoUi.getId()==null){
                        if(view!=null)view.onResumenSave();
                    }
                    else{
                        if(view!=null)view.onResumenEdit();
                    }

                    if(view!=null) view.sincronizar(programaEducativoId);
                }
            }
        }
        else  {if(view!=null)view.showMessage(res.getString(R.string.msg_empty_alumno_comportamiento));}

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle= new CRMBundle(extras);
        this.idCargaCurso=crmBundle.getCargaCursoId();
        this.programaEducativoId = crmBundle.getProgramaEducativoId();
        getParametroDisenio();
    }

    private void getParametroDisenio() {
        GetParametroDisenio.Response response= getParametroDisenio.execute(new GetParametroDisenio.Requests(idCargaCurso));
        if(view!=null)view.setParametroDisenio(response.getParametroDisenioUi());
    }
}
