package com.consultoraestrategia.ss_crmeducativo.main;


import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.main.changePerfil.ChangePerfilView;
import com.consultoraestrategia.ss_crmeducativo.main.dialogProgress.ProgressDialogView;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.ChangeDataBaseDocenteMentor;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetAccesosUIList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetAlarma;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetAnioAcademicoList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetCursosUIList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetDatosServidorLocal;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetGradosList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetHijosUIList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetPeriodosList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetProgramasEdcativosList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetUploadImagen;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.SaveAlarma;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.SavePersona;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.SuccesData;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.UpadateListAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.UpdatePersonaServidor;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ConfiguracionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.NuevaVersionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioRolGeoReferenciaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio.SeleccionarAnioAcademicoView;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities.TabsCursoDocenteActivity;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kelvi on 20/02/2017.
 */

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private MainView view;
    private UseCaseHandler handler;
    private GetProgramasEdcativosList getProgramasEdcativosUIList;
    private SaveAlarma saveAlarma;
    private GetAlarma getAlarma;
    GetDatosServidorLocal getDatosServidorLocal;
    private GetAccesosUIList getAccesosUIList;
    private GetCursosUIList getCursosUIList;
    private GetUsuarioUI getUsuarioUI;
    private GetHijosUIList getHijosUIList;
    private GetAnioAcademicoList getAnioAcademicoList;
    private ProgramaEduactivosUI programaEducativoUi;
    private PeriodoUi periodoUiSeleted;
    private GetPeriodosList getPeriodosList;
    private List<PeriodoUi>periodoUiList = new ArrayList<>();
    private GetGradosList getGradosList;
    private List<Object>objectListGrados;
    private int horaAlarma;
    private int minutoAlarma;
    private boolean efectoLista = false;
    private GradoUi gradoSelected;
    private UpadateListAnioAcademico upadateListAnioAcademico;

    public static final int ACCESO_TIPO_NOTA = 136;
    private List<ProgramaEduactivosUI> programaEduactivosUIList = new ArrayList<>();
    private List<AnioAcademicoUi> anioAcademicoUiList = new ArrayList<>();
    private PersonaUi personaUi;
    private int empleadoId;
    private int georeferenciaId;
    private int entidadId;
    CRMBundle crmBundle;
    private UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi;
    private ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor;
    private List<UsuarioRolGeoReferenciaUi> usuarioRolGeoReferenciaUiList;
    private boolean initMain;
    private List<CursosUI> cursosList;
    private ProgressDialogView progressDialogView;
    private boolean successData;
    private SuccesData succesData;
    private boolean changeDataBaseFull = false;
    private SeleccionarAnioAcademicoView seleccionarAnioAcademicoView;
    private RetrofitCancel cancelUpadateListAnioAcademico;
    private RetrofitCancel retrofitCancelGetDatosServidor;
    private ChangePerfilView changePerfilView;
    private GetUploadImagen getUploadImagen;
    private SavePersona savePersona;
    private UpdatePersonaServidor updatePersonaServidor;
    private RetrofitCancel updatePersonaRetrofitCancel;
    private ArrayList<UsuarioAccesoUI> usuarioAccesoUIList = new ArrayList<>();
    private ArrayList<ConfiguracionUi> configuracionUiList = new ArrayList<>();
    private int menuSelected = 0;
    private int tutorCargaAdemicaId;
    private boolean viewPause;
    private NuevaVersionUi nuevaVersionUi;

    public MainPresenterImpl(UseCaseHandler handler, Resources res, GetProgramasEdcativosList getProgramasEdcativosUIList, GetAccesosUIList getAccesosUIList, GetCursosUIList getCursosUIList, GetUsuarioUI getUsuarioUI, GetHijosUIList getHijosUIList, GetPeriodosList getPeriodosList, GetGradosList getGradosList, SaveAlarma saveAlarma, GetAlarma getAlarma,
                             ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor,GetAnioAcademicoList getAnioAcademicoList, GetDatosServidorLocal getDatosServidorLocal, SuccesData succesData,UpadateListAnioAcademico upadateListAnioAcademico, GetUploadImagen getUploadImagen, SavePersona savePersona, UpdatePersonaServidor updatePersonaServidor) {
        super(handler, res);
        this.handler = handler;
        this.getProgramasEdcativosUIList = getProgramasEdcativosUIList;
        this.getAccesosUIList = getAccesosUIList;
        this.getCursosUIList = getCursosUIList;
        this.getUsuarioUI = getUsuarioUI;
        this.getHijosUIList = getHijosUIList;
        this.getPeriodosList=getPeriodosList;
        this.getGradosList=getGradosList;
        this.saveAlarma=saveAlarma;
        this.getAlarma=getAlarma;
        this.changeDataBaseDocenteMentor = changeDataBaseDocenteMentor;
        this.getAnioAcademicoList = getAnioAcademicoList;
        this.getDatosServidorLocal = getDatosServidorLocal;
        this.succesData= succesData;
        this.upadateListAnioAcademico = upadateListAnioAcademico;
        this.getUploadImagen = getUploadImagen;
        this.savePersona = savePersona;
        this.updatePersonaServidor = updatePersonaServidor;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void attachView(MainView view) {
        this.view = view;

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        menuSelected=0;
        getUsuario();
    }




    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        viewPause = false;
        if(nuevaVersionUi!=null){
            if(view!=null)view.showDialogNuevaVersion(nuevaVersionUi);
            nuevaVersionUi  = null;
        }
    }

    @Override
    public void onResume()
    {
        Log.d(TAG, "onResume");
        if(initMain){
            getCursosUIListCallback();
        }else
        initMain =true;

        changeDataBaseDocenteMentor();

        if(personaUi!=null)if(changePerfilView!=null)this.changePerfilView.setPersona(personaUi);
        if(personaUi!=null)if(changePerfilView!=null)this.changePerfilView.showFaceDectecion(georeferenciaId, personaUi);

    }

    @Override
    public void onPause() {
        viewPause = true;
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        view = null;
        if(retrofitCancelGetDatosServidor!=null)retrofitCancelGetDatosServidor.cancel();
        if(updatePersonaRetrofitCancel!=null)updatePersonaRetrofitCancel.cancel();
        if(cancelUpadateListAnioAcademico!=null)cancelUpadateListAnioAcademico.cancel();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        if (view != null) {
            view.close();
        }
    }

    private int idUsuario;

    @Override
    public void setExtras(Bundle extras) {

    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        if (singleItem instanceof AnioAcademicoUi) {
            AnioAcademicoUi academico = (AnioAcademicoUi) singleItem;
            anioAcademicoIdFinal = academico.getAnioAcademicoId();
            if (view != null)view.setAnioSelectedText(academico.getNombre());
            getDatosServidorLocal();
            changeToogleAnioAcademico(anioAcademicoIdFinal);
        }
    }

    private void changeToogleAnioAcademico(int anioAcademicoIdFinal) {
        SQLite.update(AnioAcademico.class)
                .set(AnioAcademico_Table.toogle.eq(false))
                .execute();

        SQLite.update(ProgramasEducativo.class)
                .set(ProgramasEducativo_Table.toogle.eq(false))
                .execute();

        SQLite.update(AnioAcademico.class)
                .set(AnioAcademico_Table.toogle.eq(true))
                .where(AnioAcademico_Table.idAnioAcademico.eq(anioAcademicoIdFinal))
                .execute();
        Log.d(TAG, "anioAcademico: " + anioAcademicoIdFinal);

    }

    private void getDatosServidorLocal() {
        if(view!=null&&view.isInternetAvailable()){
            if(view!=null)view.showProgressInit();
            if(retrofitCancelGetDatosServidor!=null)retrofitCancelGetDatosServidor.cancel();
             retrofitCancelGetDatosServidor = getDatosServidorLocal.execute(new GetDatosServidorLocal.Request(empleadoId, anioAcademicoIdFinal), new UseCaseLoginSincrono.Callback<Boolean>() {
                @Override
                public void onResponse(boolean success, Boolean value) {
                    if (success) {
                        if (view != null) view.hideProgressInit();
                        efectoLista = true;
                        initMain();
                    } else {
                        if (view != null) view.showMessage("connection timeout");
                        if (view != null) view.hideProgressInit();
                        efectoLista = false;
                        getProgramaEducativosUIListCallback();
                        getCursosUIListCallback();
                    }
                }
            });
        }else {
            efectoLista = false;
            getProgramaEducativosUIListCallback();
            getCursosUIListCallback();
        }

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    private void getUsuario() {
        getUsuarioUI.execute(null, new UseCaseSincrono.Callback<GetUsuarioUI.ResponseValue>() {
            @Override
            public void onResponse(boolean success, GetUsuarioUI.ResponseValue response) {
                if(success){
                    UsuarioUi usuarioUi = response.getUsuarioUi();

                    personaUi = usuarioUi.getPersonaUi();
                    usuarioRolGeoReferenciaUiList = usuarioUi.getEntidadUiList();

                    idUsuario = usuarioUi.getUsuarioid();
                    successData = usuarioUi.isSuccessData();
                    if(personaUi==null){
                        if(view!=null)view.starActivityLogin(null);
                        if(view!=null)view.close();
                    }else if(usuarioRolGeoReferenciaUiList==null||usuarioRolGeoReferenciaUiList.isEmpty()){
                        if(view!=null)view.starActivityLogin("No se encontro los datos de la persona");
                        if(view!=null)view.close();
                    }else {
                        efectoLista = false;
                        initMain();
                    }
                }else {
                    Log.d(TAG, "Error no hay Usuario");
                    if (view != null) {
                        view.starActivityLogin(null);
                        view.close();
                    }
                }
            }
        });
        if(view!=null)view.callSynckServiceFB();
        if(programaEducativoUi!=null)if(view!=null)view.callSynckService(programaEducativoUi.getIdPrograma());
    }

    private void initMain() {
        getAniosAcademicos();
        getProgramaEducativosUIListCallback();

        showDatosPersonalesUser(personaUi);
        empleadoId = personaUi.getEmpleadoId();
        if(view!=null)view.subscribeToTopic(personaUi.getPersonaId());
        getUsuarioRolGeoReferencia();

        showLetraPerfil(personaUi.getApellidos(),personaUi.getNombre());
        showEntidad(usuarioRolGeoReferenciaUi.getNombreEntidad(), usuarioRolGeoReferenciaUi.getNombreGeoreferencia());
        //getHijosCallback();
        //getAccesoUIListCallback(idUsuario, false);
       /* if(usuarioRolGeoReferenciaUiList.size()==1){
            if(view!=null)view.hideBtnEntidadSelect();
        }else {
            if(view!=null)view.showBtnEntidadSelect();
        }*/

        if(view!=null)view.hideBtnEntidadSelect();
        if(!successData){
            successData = succesData.execute();
            //if(view!=null)view.showDialogFastData(anioAcademicoIdFinal, idUsuario);
            showProgress();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getCursosUIListCallback();
                }
            },2000);
        }else {
            getCursosUIListCallback();
        }
    }

    private void getUsuarioRolGeoReferencia() {
        if(usuarioRolGeoReferenciaUiList!=null&&!usuarioRolGeoReferenciaUiList.isEmpty()){

            if(anioAcademicoUiList!=null){
                AnioAcademicoUi anioAcademicoSelected = null;
                for (AnioAcademicoUi anioAcademicoUi : anioAcademicoUiList){
                    if(anioAcademicoUi.getAnioAcademicoId()==anioAcademicoIdFinal){
                        anioAcademicoSelected = anioAcademicoUi;
                    }
                }

                if(anioAcademicoSelected!=null){
                    for (UsuarioRolGeoReferenciaUi usuarioRolUi : usuarioRolGeoReferenciaUiList){
                        if(anioAcademicoSelected.getGeoreferencia() == usuarioRolUi.getGeoreferenciaId()){
                            usuarioRolGeoReferenciaUi = usuarioRolUi;
                            georeferenciaId = usuarioRolGeoReferenciaUi.getGeoreferenciaId();
                            entidadId= usuarioRolGeoReferenciaUi.getEntidadId();
                        }
                    }
                }
            }



            if(usuarioRolGeoReferenciaUi==null){
                usuarioRolGeoReferenciaUi = usuarioRolGeoReferenciaUiList.get(0);
                georeferenciaId = usuarioRolGeoReferenciaUi.getGeoreferenciaId();
                entidadId= usuarioRolGeoReferenciaUi.getEntidadId();
            }

        }
    }

    private void showEntidad(String nombreEntidad, String nombreGeoreferencia) {
        if(view!=null)view.setNombreEntidad(nombreEntidad,nombreGeoreferencia);
    }

    private void showLetraPerfil(String fullName, String nombre) {
        String letra = "";
        try {
            if(!TextUtils.isEmpty(nombre)){
                letra = String.valueOf(nombre.charAt(0));
            }else if(!TextUtils.isEmpty(fullName)) {
                letra = String.valueOf(fullName.charAt(0));
            }
        }catch (Exception e){
            letra = "?";
        }
        if(view!=null)view.showLetraPerfil(letra);

    }

    @Override
    public void onCursoUIClicked(List<CursosUI> cursosUIList) {
        /*objectList.addAll(cursosUIList);
        view.setCursosList(objectList);*/
    }

    @Override
    public void onProgramaEducativoUIClicked(ProgramaEduactivosUI programaEducativo) {
        if (programaEducativo != null) {
            if( this.programaEducativoUi != null){
                this.programaEducativoUi.setSeleccionado(false);
            }
            programaEducativo.setSeleccionado(true);
            this.programaEducativoUi = programaEducativo;
            changeToogleProgramaEducativo(this.programaEducativoUi.getIdPrograma());
            if (view != null) {
                view.showActualProgramaEdu(programaEducativo);
                view.changeProgramaEducativoList();
            }
            efectoLista = false;
            getCursosUIListCallback();
        }
    }

    private void changeToogleProgramaEducativo(int programaEducativoId){
        SQLite.update(ProgramasEducativo.class)
                .set(ProgramasEducativo_Table.toogle.eq(false))
                .execute();

        ProgramasEducativo programaEducativo = SQLite.select()
                .from(ProgramasEducativo.class)
                .where(ProgramasEducativo_Table.programaEduId.eq(programaEducativoId))
                .querySingle();

        if(programaEducativo!=null){
            programaEducativo.setToogle(true);
            programaEducativo.save();
        }

    }



    private void getAniosAcademicos() {
        anioAcademicoIdFinal = 0;
        anioAcademicoUiList.clear();
        anioAcademicoUiList.addAll(getAnioAcademicoList.execute(idUsuario));

        String nombreAnio = "";
        for (AnioAcademicoUi anioAcademicoUi : anioAcademicoUiList) {
            if (anioAcademicoUi.isToogle()) {
                anioAcademicoIdFinal = anioAcademicoUi.getAnioAcademicoId();
                nombreAnio = anioAcademicoUi.getNombre();
            }
        }

        if(anioAcademicoIdFinal==0)
        for (AnioAcademicoUi anioAcademicoUi : anioAcademicoUiList) {
            if (anioAcademicoUi.isVigente()) {
                anioAcademicoIdFinal = anioAcademicoUi.getAnioAcademicoId();
                nombreAnio = anioAcademicoUi.getNombre();

                SQLite.update(AnioAcademico.class)
                        .set(AnioAcademico_Table.toogle.eq(true))
                        .where(AnioAcademico_Table.idAnioAcademico.eq(anioAcademicoIdFinal))
                        .execute();
            }
        }

        if(anioAcademicoIdFinal==0 && !anioAcademicoUiList.isEmpty()){
            anioAcademicoIdFinal = anioAcademicoUiList.get(0).getAnioAcademicoId();
            nombreAnio = anioAcademicoUiList.get(0).getNombre();
        }

        if (view != null)view.setAnioSelectedText(nombreAnio);

    }

    @Override
    public void onIbtnProgramaClicked() {
        menuSelected=0;
        showMenuList();
    }

    @Override
    public void onCerrarSesionClicked() {
       mostrarDialogoCerrarSesion();
    }


    @Override
    public void onCursoSelected(CursosUI cursosUI) {
        //if(view!=null)view.showMessage("Aquí mostrar tips de los botones");
       validarRol(cursosUI);
    }

    private void validarRol(CursosUI cursosUI) {
        if(programaEducativoUi==null)return;
        switch (cursosUI.getEstado()){
            case CREADO:
                if(view!=null)view.showMessage("El curso "+cursosUI.getNombreCurso()+" no esta autorizado");
                break;
            case AUTORIZADO:
                if(view!=null)view.starActivityTabs(TabsCursoDocenteActivity.class, cursosUI,this.programaEducativoUi.getIdPrograma(),georeferenciaId, empleadoId,entidadId, anioAcademicoIdFinal, idUsuario, cursosUI.getComplejo());
                break;
            case SINSILABO:
                if(view!=null)view.showMessage("El curso "+cursosUI.getNombreCurso()+" no tiene programa anual");
                break;
        }
    }

    private List<Persona> hijoUIList;

    @Override
    public void onImgHijoClicked() {

        if (hijoUIList != null) {
            if(view!=null)view.showDialogListHijos(hijoUIList);
        } else {
            if(view!=null)view.hideImgHijo();
        }
    }


    @Override
    public void getCuentaHijo(int idHijoSelected) {
       // idHijo = idHijoSelected;
        //getAccesoUIListCallback(idHijoSelected, true);
        //getProgramaEducativosUIListCallback(idHijo);
    }

    @Override
    public void onAccesoSelected(UsuarioAccesoUI usuarioAccesoUI) {
        Log.d(TAG, "onAccesoSelected : " + usuarioAccesoUI.getIdAcceso());
        switch (usuarioAccesoUI.getIdAcceso()) {
            case ACCESO_TIPO_NOTA:
                if(view!=null)view.startActivityTipoNota(usuarioAccesoUI);
                break;
            case 1:
                if(view!=null)view.showPerfil();
                break;
            case 2:
                if(programaEducativoUi!=null)if(view!=null)view.showActivityService2(idUsuario, empleadoId,anioAcademicoIdFinal, programaEducativoUi.getIdPrograma(), georeferenciaId, entidadId);
                break;
        }
    }

    private int anioAcademicoIdFinal;


    @Override
    public void onBtnSelectorAnioCLicked() {
        if(view!=null)view.showSeleccionAnioAcademico();
        /*List<Object> items = new ArrayList<>();
        items.addAll(anioAcademicoUiList);

        int posicion = 0;

        for (AnioAcademicoUi anioAcademicoUi: anioAcademicoUiList){
            if(anioAcademicoUi.getAnioAcademicoId()==anioAcademicoIdFinal){
                break;
            }
            posicion++;
        }

        if (view != null) {
            view.showListSingleChooser("Año Académico", items, posicion);
        }*/
    }

    @Override
    public void onClickActionHorario() {
        if(programaEducativoUi==null)return;
        if(view!=null)view.showHorarioAnioAcademico(this.programaEducativoUi.getIdPrograma(), empleadoId, anioAcademicoIdFinal);
    }

    @Override
    public void onSelectHorarioCurso(CursosUI cursosUI, int cargaCurso) {
        Log.d(TAG,"onSelectHorarioCurso");
        if(programaEducativoUi==null)return;
        if(view!=null)view.showHorarioCargaCurso(programaEducativoUi.getIdPrograma(), empleadoId, cargaCurso, anioAcademicoIdFinal);
    }

    @Override
    public void onClickBtnAcceso() {
        this.usuarioAccesoUIList.clear();
        UsuarioAccesoUI usuarioAccesoUI = new UsuarioAccesoUI();
        usuarioAccesoUI.setIdAcceso(1);
        usuarioAccesoUI.setNombreAcceso("Perfil");
        usuarioAccesoUIList.add(usuarioAccesoUI);
        usuarioAccesoUI = new UsuarioAccesoUI();
        usuarioAccesoUI.setIdAcceso(2);
        usuarioAccesoUI.setNombreAcceso("Servicio general");
        usuarioAccesoUIList.add(usuarioAccesoUI);
        menuSelected=1;
        showMenuList();
    }

    @Override
    public void onClickBtnConfiguracion() {
        this.configuracionUiList = new ArrayList<>();
        configuracionUiList.add(ConfiguracionUi.INFORMACION);
        //configuracionUiList.add(ConfiguracionUi.BORRAR_CACHE);
        //configuracionUiList.add(ConfiguracionUi.CONTACTOS);
        configuracionUiList.add(ConfiguracionUi.ALARMA);
        configuracionUiList.add(ConfiguracionUi.CERRAR_SESION);
        menuSelected=2;
        showMenuList();
    }



    @Override
    public void onClickConfiguracion(ConfiguracionUi configuracionUi) {
       switch (configuracionUi){
           case INFORMACION:
               getInformacionApp() ;
               break;
           case CONTACTOS:
               BEVariables beVariables = new BEVariables();
               if(view!=null) view.viewActivityImportLogin(beVariables);
               break;
           case CERRAR_SESION:
               mostrarDialogoCerrarSesion();
               break;
           case BORRAR_CACHE:
               if(view!=null) view.mostrarDialogoBorrarMemoriaCahe();
               break;
           case ALARMA:
               if(view!=null) view.mostrarDialogoConfigAlarma();
               break;


       }
    }

    private void mostrarDialogoCerrarSesion() {
        List<String> tableChange = changeDataBaseDocenteMentor.execute();
        boolean isUpdateTable = tableChange.isEmpty();
        if(view!=null) view.mostrarDialogoCerrarSesion(tableChange,isUpdateTable);
    }

    private void getInformacionApp() {
        String empresa= "System Estrategy";
        String app= "Docente Mentor " ;
        String desarrolladores= new String("Jose Arias \nEstrella Barrientos\nSol Mamani ");
        if(view!=null)view.showDialogInformacion(empresa, app, desarrolladores);
    }

    @Override
    public void onClickDialogoCerrarSesion() {
        if(view!=null)view.cerrarSesion();
    }

    @Override
    public void onClickDialogoBorrarCahe() {
        if(view!=null)view.borrarCache();
    }

    @Override
    public void updateCalendarioPeriodo() {
        if(programaEducativoUi==null)return;
        BEVariables beVariables = new BEVariables();
        beVariables.setAnioAcademicoId(anioAcademicoIdFinal);
        beVariables.setProgramaEducativoId(programaEducativoUi.getIdPrograma());
        if(view!=null)view.showImportarCalendario(beVariables);
    }

    @Override
    public void onClickRefrescar() {
        /*boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }*/

        //if(programaEducativoUi!=null)if(view!=null)view.callSynckService(programaEducativoUi.getIdPrograma());

        if(programaEducativoUi!=null)if(view!=null)view.showActivityService2(idUsuario, empleadoId,anioAcademicoIdFinal, programaEducativoUi.getIdPrograma(), georeferenciaId, entidadId);
    }

    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        Log.d(TAG, "onPeriodoCargaCursoSelected : " + periodoUi.getIdPeriodo());
        if(periodoUiSeleted!=null)if (periodoUiSeleted.equals(periodoUi))return;
        changePeriodo(periodoUi);
        crmBundle.setCalendarioPeriodoId(periodoUiSeleted.getIdCalendarioPeriodo());
        if(view!=null)view.updateCalendar(crmBundle);
    }


    private void changePeriodo(PeriodoUi periodoUi) {
        Log.d(TAG, "changePeriodo : " + periodoUi.getIdPeriodo());
        if(periodoUiSeleted!=null)periodoUiSeleted.setStatus(false); // LLegaba con true y cambiamos false
        periodoUi.setStatus(true);
        if (view != null)view.changePeriodo(periodoUiSeleted, periodoUi);
        this.periodoUiSeleted = periodoUi;
    }



    private void getProgramaEducativosUIListCallback() {
        programaEduactivosUIList.clear();
        programaEduactivosUIList.addAll(getProgramasEdcativosUIList.execute(idUsuario, anioAcademicoIdFinal));
        programaEducativoUi = null;

        try {
            for (ProgramaEduactivosUI programaEduactivosUI: programaEduactivosUIList){
                if(programaEduactivosUI.isSeleccionado())this.programaEducativoUi = programaEduactivosUI;
            }

            if(this.programaEducativoUi==null){
                this.programaEducativoUi = programaEduactivosUIList.get(0);
                this.programaEducativoUi.setSeleccionado(true);
            }

            if(view!=null)view.showActualProgramaEdu(this.programaEducativoUi);

        }catch (Exception e){
            e.printStackTrace();
        }

        showMenuList();

    }

    private void showMenuList() {
        switch (menuSelected){
            case 0://Programa Educativo
                if(view!=null)view.showMenuList(new ArrayList<Object>(programaEduactivosUIList));
                break;
            case 1:///usuario Accesos
                if(view!=null)view.showMenuList(new ArrayList<Object>(usuarioAccesoUIList));
                break;
            default://configuracion
                if(view!=null)view.showMenuList(new ArrayList<Object>(configuracionUiList));
                break;
        }

    }

    private void showDatosPersonalesUser(PersonaUi personaUi) {
        String persona =  Utils.getFirstWord(personaUi.getNombre()) + " " + personaUi.getApellidos();
        if(view!=null)view.showNombreUsuario(persona);
        if(view!=null)view.subscribe(personaUi.getCelular());
        if (TextUtils.isEmpty(personaUi.getCorreo())) {
            if(view!=null) view.showEmailUsuario("Email no registrado");
        } else {
            if(view!=null)view.showEmailUsuario(personaUi.getCorreo());
        }
        if(view!=null)view.showImgProfileUser(personaUi.getFoto());

//        view.showImgProfileBackgroundUser("http://3.bp.blogspot.com/-ftcLyoUXwsc/UVRjuPWuUFI/AAAAAAAAAA0/BKPJPpDvFPI/s1600/8310376-world-map-technology-style-against-fiber-optic-background.jpg");
    }


    private void getCursosUIListCallback() {


        int programaEducativoId = 0;
        if(programaEducativoUi!=null){
            programaEducativoId = programaEducativoUi.getIdPrograma();
        }
        showProgress();
        handler.execute(getCursosUIList, new GetCursosUIList.RequestValues(idUsuario, programaEducativoId, anioAcademicoIdFinal), new UseCase.UseCaseCallback<GetCursosUIList.ResponseValue>() {
            @Override
            public void onSuccess(GetCursosUIList.ResponseValue response) {

                cursosList = response.getCursosUIList();
                //Is Tutor
                tutorCargaAdemicaId = 0;
                for (CursosUI cursosUI : cursosList){
                    if(cursosUI.isTutor()){
                        tutorCargaAdemicaId = cursosUI.getCargaAcademicaId();
                        break;
                    }
                }
                //Is Tutor
                if(tutorCargaAdemicaId>0){
                    if(view!=null)view.showBtnAgenda();
                }else {
                    if(view!=null)view.hideBtnAgenda();
                }


                if(view!=null)view.hideProgress();
                showCursosUIList(cursosList, efectoLista);
                if(response.getCursosUIList().isEmpty()){
                    if(view!=null)view.showMensaje();
                }else {
                    if(view!=null)view.hideMensaje();
                }
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error no hay cursos");
                hideProgress();
                if(view!=null)view.hideBtnAgenda();
            }
        });

    }

    private void getPeriodos() {

        periodoUiList.clear();
        handler.execute(getPeriodosList, new GetPeriodosList.RequestValues(anioAcademicoIdFinal,programaEducativoUi.getIdPrograma()), new UseCase.UseCaseCallback<GetPeriodosList.ResponseValue>() {
            @Override
            public void onSuccess(GetPeriodosList.ResponseValue response) {
                Log.d(TAG, "onSuccess periodods "+ response.getPeriodoUiList().size());
                periodoUiList=response.getPeriodoUiList();
                for(PeriodoUi periodo:periodoUiList )if(periodo.isStatus())periodoUiSeleted=periodo;
                if(view!=null)view.showPeriodoList(periodoUiList);
                showGradosUIList(objectListGrados);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onClickAsistenciaGradoSeccion() {
            if(view!=null)view.showContenAsistencia();
            if(view!=null)view.hideContenCurso();
            if(objectListGrados!=null)showGradosUIList(objectListGrados);
            else getGrados();

    }

    private void showCursosUIList(List<CursosUI> objectUIList, boolean efectoLista) {
        if (view != null){
            view.setCursosList(objectUIList, efectoLista);
        }
    }
    private void showGradosUIList(List<Object> objectUIList) {
        crmBundle= new CRMBundle();
        if(programaEducativoUi!=null)crmBundle.setProgramaEducativoId(programaEducativoUi.getIdPrograma());
        if(gradoSelected!=null)crmBundle.setCargaAcademicaId(gradoSelected.getCargaAcademicaId());
        if(periodoUiSeleted!=null)crmBundle.setCalendarioPeriodoId(periodoUiSeleted.getIdCalendarioPeriodo());
        if(gradoSelected!=null)crmBundle.setEmpleadoId(gradoSelected.getDocenteId());
        crmBundle.setGeoreferenciaId(georeferenciaId);

        if (view != null){
            view.setGradosList(objectUIList, crmBundle);
        }
    }
    private void getGrados() {
      objectListGrados= new ArrayList<>();
      objectListGrados.clear();
      handler.execute(getGradosList, new GetGradosList.RequestValues(programaEducativoUi.getIdPrograma(), idUsuario, anioAcademicoIdFinal), new UseCase.UseCaseCallback<GetGradosList.ResponseValue>() {
         @Override
         public void onSuccess(GetGradosList.ResponseValue response) {
             Log.d(TAG, "onSuccess "+ response.getGradoUiList().size());
             int count=0;
             for(GradoUi gradoUi:response.getGradoUiList() ){
                 count=count+1;
                 if(gradoUi.isSeleted())gradoSelected=gradoUi;
                 gradoUi.setContador(count);
                 objectListGrados.add(gradoUi);
             }
             getPeriodos();
         }

         @Override
         public void onError() {
         }
     });
    }
    @Override
    public void onSelectedGrado(GradoUi gradoUi) {
      this.gradoSelected=gradoUi;
      Log.d(TAG, "cargaacademica "+ gradoSelected.getCargaAcademicaId());
        crmBundle.setCargaAcademicaId(gradoSelected.getCargaAcademicaId());
        if(view!=null)view.updateCalendar(crmBundle);
    }

    @Override
    public void onClickListCursos() {
        if(view!=null)view.hideContenAsistencia();
        if(view!=null)view.showContenCurso();
        efectoLista=false;
        if(cursosList!=null)showCursosUIList(cursosList, efectoLista);
        else getCursosUIListCallback();
    }

    @Override
    public void selectedHoraMinuteAlarma(int hour, int minute) {
      this.horaAlarma= hour;
      this.minutoAlarma= minute;
    }

    @Override
    public void aceptarHora() {

      SaveAlarma.Response response= saveAlarma.execute(new SaveAlarma.Requests(horaAlarma, minutoAlarma));
        Log.d(TAG, "aceptarHora "+  response.isSuccess());
      if(response.isSuccess()) starAlarma();

    }

    @Override
    public AlarmaUi getAlarma() {
        GetAlarma.Response response= getAlarma.execute();
        if(response.getAlarmaUi()!=null)Log.d(TAG, "getAlarma "+ response.getAlarmaUi().getHora()+ " : "+response.getAlarmaUi().getMinute());
        return response.getAlarmaUi();
    }

    @Override
    public void onChangeFull(boolean b) {
        //getUsuario();
    }

    @Override
    public void onClickedMenuEntidad() {
        if(usuarioRolGeoReferenciaUiList!=null)if(view!=null)view.showPopListEntidad(usuarioRolGeoReferenciaUiList);
    }

    @Override
    public void onSelectedEntidad(UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi) {
        this.usuarioRolGeoReferenciaUi = usuarioRolGeoReferenciaUi;
        initMain();
    }

    @Override
    public void onCreateOptionsMenu() {
        changeDataBaseDocenteMentor();
        if(tutorCargaAdemicaId>0){//Volver a llamar por si el caso de uso se jecuto antes del metodo on Create Option menu
            if(view!=null)view.showBtnAgenda();
        }else {
            if(view!=null)view.hideBtnAgenda();
        }
    }

    @Override
    public void onFinishSynck() {
        changeDataBaseDocenteMentor();
    }

    @Override
    public void onClickTutoriaCursoSelected(CursosUI cursosUI, int cargaCursoId) {
        if(view!=null)view.showCambiarFotoAlumnoActivity(cargaCursoId, georeferenciaId, entidadId);
    }

    @Override
    public void onProgressDialogViewDestroyed() {
        progressDialogView = null;
    }

    @Override
    public void attachView(ProgressDialogView progressDialogView) {
        this.progressDialogView = progressDialogView;
    }

    @Override
    public void onChangeDatabseDesdeService2() {
        changeDataBaseFull = true;
    }

    @Override
    public void onClickAnioSelected(AnioAcademicoUi anioAcademicoUi) {

        for(AnioAcademicoUi anioAcademico : anioAcademicoUiList){
            if(anioAcademico.getAnioAcademicoId() == anioAcademicoUi.getAnioAcademicoId()){
                anioAcademicoIdFinal = anioAcademico.getAnioAcademicoId();
                if (view != null)view.setAnioSelectedText(anioAcademico.getNombre());
                changeToogleAnioAcademico(anioAcademicoIdFinal);
                anioAcademico.setToogle(true);
            }else {
                anioAcademico.setToogle(false);
            }
        }
        getUsuarioRolGeoReferencia();
        showEntidad(usuarioRolGeoReferenciaUi.getNombreEntidad(), usuarioRolGeoReferenciaUi.getNombreGeoreferencia());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDatosServidorLocal();
                if(seleccionarAnioAcademicoView!=null)seleccionarAnioAcademicoView.close();
            }
        },300);

    }

    @Override
    public void attachView(SeleccionarAnioAcademicoView f) {
        this.seleccionarAnioAcademicoView = f;

        seleccionarAnioAcademicoView.showProgress();
        cancelUpadateListAnioAcademico = upadateListAnioAcademico.execute(new UpadateListAnioAcademico.Response(idUsuario), new UseCaseLoginSincrono.Callback<UpadateListAnioAcademico.Request>() {
            @Override
            public void onResponse(boolean success, UpadateListAnioAcademico.Request value) {
                if(seleccionarAnioAcademicoView!=null)seleccionarAnioAcademicoView.hideProgress();
                if (!success) {
                    if(seleccionarAnioAcademicoView!=null){
                        if (view != null) view.showMessage("connection timeout");
                    }
                } else {
                    anioAcademicoUiList.clear();
                    anioAcademicoUiList.addAll(getAnioAcademicoList.execute(idUsuario));
                }

                getUsuarioRolGeoReferencia();
                Set<Object> anioColegioList = new LinkedHashSet<>();
                for (AnioAcademicoUi anioAcademicoUi : anioAcademicoUiList) {

                    UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi = new UsuarioRolGeoReferenciaUi();
                    usuarioRolGeoReferenciaUi.setNombreEntidad("Desconocido");
                    usuarioRolGeoReferenciaUi.setNombreGeoreferencia("Desconocido");
                    if(usuarioRolGeoReferenciaUiList!=null){
                        for (UsuarioRolGeoReferenciaUi usuarioRolUi : usuarioRolGeoReferenciaUiList){
                            if(anioAcademicoUi.getGeoreferencia() == usuarioRolUi.getGeoreferenciaId()){
                                usuarioRolGeoReferenciaUi = usuarioRolUi;
                            }
                        }
                    }

                    anioColegioList.add(usuarioRolGeoReferenciaUi);

                    if (anioAcademicoUi.getAnioAcademicoId() == anioAcademicoIdFinal) {
                        anioAcademicoUi.setToogle(true);
                    }else {
                        anioAcademicoUi.setToogle(false);
                    }

                    anioColegioList.add(anioAcademicoUi);
                }
                if(seleccionarAnioAcademicoView!=null)seleccionarAnioAcademicoView.setListAnioAcademico(new ArrayList<>(anioColegioList));
            }
        });
    }

    @Override
    public void onSeleccionarAnioAcademicoViewDestroyed() {
        this.seleccionarAnioAcademicoView = null;
        if(cancelUpadateListAnioAcademico!=null)cancelUpadateListAnioAcademico.cancel();
    }

    @Override
    public void onChangePerfilViewDestroyed() {
        changePerfilView = null;
    }

    @Override
    public void attachView(ChangePerfilView changePerfilView) {
        this.changePerfilView = changePerfilView;
        if(personaUi!=null)this.changePerfilView.setPersona(personaUi);
        if(personaUi!=null)this.changePerfilView.showFaceDectecion(georeferenciaId, personaUi);

    }

    @Override
    public void onClickReconocimiento() {
        if(view!=null)view.showCamaraReconocimientos(idUsuario);
    }

    @Override
    public void onClickActionAgenta() {
       if(view!=null)view.showActivityAgenda(idUsuario, georeferenciaId, empleadoId, anioAcademicoIdFinal, entidadId, tutorCargaAdemicaId);
    }

    @Override
    public void onClickReconocimientoCursoSelected(CursosUI cursosUI, int cargaCursoId) {
        if(view!=null)view.showReconocimientoActivity(cargaCursoId, georeferenciaId, entidadId);

    }

    @Override
    public void onClickGuardarPerfil(String telefono, String email) {
        String telefonoAnterio = telefono;
        String emailAnterior = email;
        personaUi.setCorreo(email);
        personaUi.setCelular(telefono);
        if(view!=null)changePerfilView.disabledButtons();
        if(view!=null)changePerfilView.showProgress();
        updatePersonaRetrofitCancel = updatePersonaServidor.execute(personaUi, new UpdatePersonaServidor.Callback() {
            @Override
            public void onSuccess() {
                if (changePerfilView != null) changePerfilView.enabledButtons();
                if (changePerfilView != null) changePerfilView.hideProgress();
                if (changePerfilView != null) changePerfilView.close();
            }

            @Override
            public void onError() {
                if (changePerfilView != null) changePerfilView.enabledButtons();
                if (changePerfilView != null) changePerfilView.hideProgress();

                personaUi.setCorreo(emailAnterior);
                personaUi.setCelular(telefonoAnterio);
            }
        });
    }

    @Override
    public void changeFile() {
        if(view!=null)view.startCropImageActivity(personaUi.getPath());
    }

    @Override
    public void onCropImageActivityResult(String filePath) {
        if(personaUi!=null){
            personaUi.setPath(filePath);
            savePersona.execute(personaUi);
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
            if(changePerfilView!=null)changePerfilView.updatePersona(personaUi);
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
                    getUsuario();
                    if(changePerfilView!=null)changePerfilView.updatePersona(value);
                }
            });


        }
    }

    @Override
    public void onClickResultadoCursoSelected(CursosUI cursosUiRecurso) {
       if(view!=null)view.showCentroProcesoActivty(cursosUiRecurso, (programaEducativoUi!=null?programaEducativoUi.getIdPrograma():0), anioAcademicoIdFinal);
    }

    @Override
    public void onClickEvaCursoSelected(CursosUI cursosUiRecurso) {
        if(view!=null)view.showMessage("Abrir pagina del eva");
    }

    @Override
    public void onClickMovilCursoSelected(CursosUI cursosUiRecurso) {
        validarRol(cursosUiRecurso);
    }

    @Override
    public void onClickAgendaCursoSelected(CursosUI cursosUiRecurso) {

    }

    @Override
    public void onVersionChecker(NuevaVersionUi nuevaVersionUi) {
        if(!viewPause){
            if(view!=null)view.showDialogNuevaVersion(nuevaVersionUi);
            this.nuevaVersionUi  = null;
        }else {
            this.nuevaVersionUi  = nuevaVersionUi;
        }


    }


    private void changeDataBaseDocenteMentor(){
        Log.d(TAG, "changeDataBaseDocenteMentor");
        List<String> tableChange = changeDataBaseDocenteMentor.execute();
        boolean isUpdateTable = tableChange.isEmpty();
        if(isUpdateTable){
            if(view!=null)view.progressUpdateSuccess();
        }else {
            if(view!=null)view.progressUpdateError();
        }
    }

    private void starAlarma() {
        Calendar currCalendar = Calendar.getInstance();
        currCalendar.set(Calendar.HOUR_OF_DAY, horaAlarma);
        currCalendar.set(Calendar.MINUTE, minutoAlarma);
        currCalendar.set(Calendar.SECOND, 0);
        if(view!=null)view.startAlarm(currCalendar, programaEducativoUi.getIdPrograma());
    }

}
