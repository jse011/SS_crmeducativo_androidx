package com.consultoraestrategia.ss_crmeducativo.tabsSesiones;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.actividades.ui.ActividadesFragment;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.ui.FragmentAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comentarios.ui.ComentariosFragment;
import com.consultoraestrategia.ss_crmeducativo.grouplist.ui.ListaGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.RubricaSesionFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.situacion.ui.SituacionFragment;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.domain.UseCase.ChangeDataBaseDocenteMentor;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.domain.UseCase.GetDatosEsenciales;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.DatosEnsencialesUI;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.RubroEvaluacionResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.ui.FragmentTareasSesiones;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 13/12/2017.
 */

public class TabsSesionPresenterImpl implements TabsSesionPresenter {

    private static final String TAG = TabsSesionPresenterImpl.class.getSimpleName();
    private TabsSesionView view;
    private int sesionAprendizajeId;
    private int cargaAcademicaId;
    private int personaId;
    private int backgroudColor;
    private int CargaCursoId;
    private int CursoId;
    private int entidadId;
    private int georeferenciaId;
    private SesionAprendizajeUi sesionAprendizajeUi;
    private UseCaseHandler handler;
    private GetDatosEsenciales getDatosEsenciales;
    private RubroEvaluacionResultadoUi rubroEvaluacionResultadoUi;
    private int programaEducativoId;
    private GetFechaCreacionSesionAprendizaje getFechaCreacionSesionAp;
    private long f_CreacionServidor;
    private long f_CreacionLocal;
    private int parametroDisenioId;
    private boolean calendarioActivo;
    private ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor;
    ArrayList<CompetenciaCheck> tipoCompetencia = new ArrayList<>();
    private boolean initFragment = true;
    private boolean changeDataBase = false;

    public TabsSesionPresenterImpl(UseCaseHandler handler, GetDatosEsenciales getDatosEsenciales, GetFechaCreacionSesionAprendizaje getFechaCreacionSesionAp, ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor) {
        this.handler = handler;
        this.getDatosEsenciales = getDatosEsenciales;
        this.getFechaCreacionSesionAp = getFechaCreacionSesionAp;
        this.changeDataBaseDocenteMentor = changeDataBaseDocenteMentor;
    }

    @Override
    public void attachView(TabsSesionView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        GetDatosEsenciales();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        //GetFechaCreacionSesionAprendizaje();
        Log.d(TAG, "onResume");
        if(!initFragment){

            Log.d(TAG, "initFragmentonResumenFramentManger");
            boolean changeDataBase = false;
            if(view!=null)changeDataBase = view.ischangeDataBase();

            if(changeDataBase){
                this.changeDataBase = changeDataBase;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(view!=null)view.successchangeDataBase();
                        if(view!=null)view.saveNotifyChangeTabCurso();
                        onResumenFramentManger();
                    }
                },500);
            }else {
                Log.d(TAG, "comprobarVigenciaCalendarioPeriodo");
                comprobarVigenciaCalendarioPeriodo();
            }
        }

        initFragment = false;

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void setExtra(Bundle extra) {
        sesionAprendizajeId = extra.getInt(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJEID);
        personaId = extra.getInt(TabsSesionesActivityV2.INT_PERSONAID);
        backgroudColor = extra.getInt(TabsSesionesActivityV2.INT_BACKGROUND);
        CargaCursoId = extra.getInt(TabsSesionesActivityV2.INT_CARGACURSO_ID);
        CursoId = extra.getInt(TabsSesionesActivityV2.INT_CURSO_ID);
        parametroDisenioId = extra.getInt(TabsSesionesActivityV2.PARAMETRO_DISENIO_ID);
        cargaAcademicaId = extra.getInt(TabsSesionesActivityV2.INT_CARGA_ACADEMICA_ID);
        sesionAprendizajeUi = (SesionAprendizajeUi) extra.getSerializable(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJE_UI);
        CRMBundle crmBundle= new CRMBundle(extra);
        entidadId= crmBundle.getEntidadId();
        georeferenciaId = crmBundle.getGeoreferenciaId();
        calendarioPeriodoId= crmBundle.getCalendarioPeriodoId();
        Log.d(TAG, "Calendario perido "+ crmBundle.getCalendarioPeriodoId()+ " activo "+ crmBundle.isCalendarioActivo());
    }

    @Override
    public void onClickAceptarDialog() {
        if (view == null) return;
        view.updateFragmentoEvaluacion();
    }

    @Override
    public void hideCrearRubro() {
        if (view == null) return;
        view.hideListaIndicadores();
    }

    @Override
    public void onSelectIndicadorCampotematicovoid(int indicadorId, ArrayList<Integer> campotematicoIds) {
        if (view == null) return;
        view.showCrearRubro(sesionAprendizajeId, indicadorId, campotematicoIds);
    }

    @Override
    public void onSalirListaIndicadores() {
        if (view == null) return;
        view.hideListaIndicadores();
    }

    @Override
    public void onSelectTab(int posicion) {

    }

    @Override
    public void onSussesRubroEvaluacionCompetenciasLista(ArrayList<Integer> competenciasId) {
        if (view == null) return;
        view.updateFragmentosRubro(competenciasId);
    }

    @Override
    public void onChangeRubroEvaluacion(int ruborEvaluacionId) {
        if (view == null) return;
        view.updateFragmentosRubro(ruborEvaluacionId);
    }

    @Override
    public void onAddRubroEvaluacion(int addruborEvaluacionId) {
        if (view == null) return;
        view.addFragmentosRubro(addruborEvaluacionId);
    }

    private int calendarioPeriodoId;
    private int silaboEventoId;
    private int rubroEvalResultadoId;

    private void GetDatosEsenciales() {
        final DatosEnsencialesUI response = getDatosEsenciales.executeUseCase(new GetDatosEsenciales.RequestValues(sesionAprendizajeId, CursoId));
        //  calendarioPeriodoId = response.getCalendarioPeriodoId();
        silaboEventoId = response.getSilavoEventoId();
        rubroEvalResultadoId = response.getRubroEvalaucionResultadoId();
        programaEducativoId = response.getProgramaEducativoId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) view.setupAdapter(3, calendarioPeriodoId, response.getSilavoEventoId(), response.getRubroEvalaucionResultadoId(), programaEducativoId, sesionAprendizajeId, parametroDisenioId, cargaAcademicaId, CursoId, CargaCursoId, sesionAprendizajeUi, personaId, backgroudColor, entidadId, georeferenciaId);
            }
        }, 500);
        if (view != null) view.changeColorToolbar();
    }


    private Class<? extends Fragment> selectFragmentClass;

    @Override
    public void onPageChanged(Class<? extends Fragment> fragmentClass) {

        selectFragmentClass = fragmentClass;

        if (selectFragmentClass == null) {
            selectFragmentClass = FragmentAprendizaje.class;
            hideFrameLayoutCometarios();
            showViewPager();
            hideFab();
            showToolbar();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }


        if (selectFragmentClass.equals(FragmentAprendizaje.class)) {
            selectFragmentClass = FragmentAprendizaje.class;
            hideFrameLayoutCometarios();
            showViewPager();
            hideFab();
            showToolbar();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (selectFragmentClass.equals(ActividadesFragment.class)) {
            selectFragmentClass = ActividadesFragment.class;
            hideFrameLayoutCometarios();
            showViewPager();
            hideFab();
            showToolbar();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (fragmentClass.equals(RubricaSesionFragment.class)) {
            selectFragmentClass = RubricaSesionFragment.class;
            hideFrameLayoutCometarios();
            showViewPager();
            showFab(R.drawable.ic_add_white_24dp);
            changeColorButtom();
            showToolbar();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (fragmentClass.equals(FragmentRubroEvalProcesoLista.class)) {
            selectFragmentClass = FragmentRubroEvalProcesoLista.class;
            hideFrameLayoutCometarios();
            showViewPager();
            showFab(R.drawable.ic_filter_list_white_24dp);
            changeColorButtom();
            showToolbar();
            changeColorToolbar();
            //hideFab();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (fragmentClass.equals(FragmentTareasSesiones.class)) {
            selectFragmentClass = FragmentTareasSesiones.class;
            hideFrameLayoutCometarios();
            showViewPager();
            showFab(R.drawable.ic_add_white_24dp);
            changeColorButtom();
            showToolbar();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (fragmentClass.equals(SituacionFragment.class)) {
            selectFragmentClass = SituacionFragment.class;
            hideFrameLayoutCometarios();
            showViewPager();
            hideFab();
            showToolbar();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (fragmentClass.equals(NewFragment.class)) {
            selectFragmentClass = NewFragment.class;
            expandirToolbar();
            hideFab();
            showToolbar();
            initComentarios();
            changeColorToolbar();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

    }

    private void changeColorToolbar() {
        if (view != null) view.changeColorToolbar();
    }

    private void changeColorButtom() {
        if (view != null) view.changeColorFloatButton();
    }

    private void hideToolbar() {
        if (view != null) view.hideToolbar();
    }

    private void showToolbar() {
        if (view != null) view.showToolbar();
    }

    private void showViewPager() {
        if (view != null) view.showViewPager();
    }

    private void hideFrameLayoutCometarios() {
        if (view != null) view.hideFrameLayoutComentarios();
    }

    private void expandirToolbar() {
        if (view != null) view.expandirToolbar();
    }

    private void initComentarios() {
        //if (view != null) view.hideViewPager();
        if (view != null)
            view.initComentarios(sesionAprendizajeUi, personaId, backgroudColor, CargaCursoId, CursoId, cargaAcademicaId, parametroDisenioId);
    }

    @Override
    public void onClickFab() {
        if (selectFragmentClass == null) return;

        if (selectFragmentClass.equals(RubricaSesionFragment.class)) {
            startCreateRubBid();
            return;
        }
        if (selectFragmentClass.equals(FragmentRubroEvalProcesoLista.class)) {
            showFiltroCamposTematicos();
            return;
        }
        if (selectFragmentClass.equals(FragmentTareasSesiones.class)) {
            startCreateTarea();
            return;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        Bundle bundle = data.getExtras();
        if (bundle == null) return;
        int tipo = bundle.getInt(RubricasAbstractFragment.TIPO_RUBRICA);
        //    Log.d(TAG, "tipo :" + tipo);
        switch (tipo) {
            case RubricasAbstractFragment.AGREGAR_RUBRICAS_BIDIMENSIONAL:
                // view.onActualizarRubricaFragment(data);
                if (view != null) view.onActualizarItemRubrica();
                break;
            default:
                Log.d(TAG, "default");
                break;
        }
    }

    @Override
    public void onClickActualizar() {
        if (view != null) view.showActualizarSesionAprendizaje(sesionAprendizajeId);
    }

    @Override
    public void onClickRefrescar() {
        boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }
        if (view != null) view.callSynckService(programaEducativoId);
    }

    @Override
    public void onClickUpdate() {
        BEVariables beVariables = new BEVariables();
        beVariables.setCalendarioPeriodoId(calendarioPeriodoId);
        if (selectFragmentClass == null) {
            return;
        }

        if (selectFragmentClass.equals(RubricaSesionFragment.class)) {
            view.showActualizarRubrica(beVariables);
            return;
        }

        if (selectFragmentClass.equals(ListaGrupoFragment.class)) {
            view.showActualizarGrupos(beVariables);
            return;
        }

        if (selectFragmentClass.equals(FragmentRubroEvalProcesoLista.class)) {
            view.showActualizarRubros(beVariables);
            return;
        }
    }

    @Override
    public void onChangeRubroUpdate() {
        if (view != null) view.onActualizarItemRubrica();
    }

    @Override
    public void onBtnSuccesItemFilterRubro(ArrayList<CompetenciaCheck> tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
        if (view != null) {
            view.onSesionCompetenciaFragmentFabClicked(tipoCompetencia);
        }
    }


    @Override
    public void onChange(TipoImportacion tipoImportacion, boolean success) {
        switch (tipoImportacion) {
            case RUBROEVALUACION:
                onChangeRubro(success);
                break;
            case RUBROEVALUACION_CALENDARIO:
                onChangeRubro(success);
                break;
            case ASISTENCIA:
                break;
            case SESIONES:
                onChangeSesiones(success);
                break;
            case UNIDAD:
                onChangeSesiones(success);
                break;
            case TIPONOTA:
                break;
            case CALENDARIO_PERIODO:
                onChangeCalendarioPeriodo(success);
                break;
            case GRUPO:
                break;
            case TAREA:
                onChangeTarea(success);
                break;
            case GRUPOS:
                break;
            case MENSAJE:
                break;
            case CONTACTOS:
                break;
            case TAREA_UNIDAD:
                break;
            case STRATEGY_LOGIN:
                break;
            case DEFAULT:
                break;
        }
    }

    @Override
    public void onChangeFull(boolean success) {
        onResumenFramentManger();

//        onChangeAprendizaje(success);
//        onChangeRubro(success);
//        onChangeSesiones(success);
//        onChangeCalendarioPeriodo(success);
//        onChangeTarea(success);
//        onChangeComentarios(success);
    }


    @Override
    public void onDeleteRubrica() {
        if (view != null) view.onActualizarItemRubrica();
        if (view != null) view.onResumenFragmentRegistro();
        if (view != null) view.onResumenTarea();
    }

    @Override
    public void onDeleteRubro() {
        if (view != null) view.onResumenTarea();
    }

    @Override
    public void salirFrameLayoutComentarios() {
       //showToolbar();
       //showViewPager();
        hideFrameLayoutCometarios();
    }

    @Override
    public void onCreateOptionsMenu() {
        changeDataBaseDocenteMentor();
    }

    @Override
    public void onFinishSynck() {
        changeDataBaseDocenteMentor();
    }

    private void changeDataBaseDocenteMentor(){
        List<String> tableChange = changeDataBaseDocenteMentor.execute();
        boolean isUpdateTable = tableChange.isEmpty();
        if(isUpdateTable){
            if(view!=null)view.progressUpdateSuccess();
        }else {
            if(view!=null)view.progressUpdateError();
        }
    }

    private void onChangeAprendizaje(boolean success) {
        if (view != null) view.onResumenFragmentAprendizaje();
    }

    private void onChangeComentarios(boolean success) {
        if (view != null) view.onResumenFragmentComentarios();
    }


    private void onChangeTarea(boolean success) {
        if (view != null) view.onResumenTarea();
    }

    private void onChangeCalendarioPeriodo(boolean success) {
        onChangeRubro(success);
        onChangeTarea(success);
    }

    private void onChangeSesiones(boolean success) {
        if (view != null) view.onResumenFragmentAprendizaje();
        if (view != null) view.onResumenFragmentActividades();
        if (view != null) view.onResumenFragmentSituacion();
        if (view != null) view.onResumenTarea();
    }

    private void onChangeRubro(boolean success) {
        if (view != null) view.onResumenFragmentRubrica();
        if (view != null) view.onResumenFragmentRegistro();
    }


    private void showFab(@DrawableRes int drawable) {
        if (view != null) view.changeFabBg(drawable);
    }


    private void hideFab() {
        if (view != null) view.hideFab();
    }

    private void showFiltroCamposTematicos() {
        if (view != null) view.showFiltroCamposTematicos(tipoCompetencia);
    }

    private void startCreateRubBid() {
        if (view != null)
            view.startCreateRubBid(calendarioPeriodoId, silaboEventoId, 0, programaEducativoId);
    }

    private void startCreateTarea() {
        if (view != null)
            view.startTarea();
    }


    private void onResumenFramentManger() {

        if (selectFragmentClass == null) {
            if (view != null) view.onResumenFragmentAprendizaje();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (selectFragmentClass.equals(FragmentAprendizaje.class)) {
            if (view != null) view.onResumenFragmentAprendizaje();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (selectFragmentClass.equals(ActividadesFragment.class)) {
            if (view != null) view.onResumenFragmentActividades();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (selectFragmentClass.equals(RubricaSesionFragment.class)) {
            if (view != null) view.onResumenFragmentRubrica();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (selectFragmentClass.equals(FragmentRubroEvalProcesoLista.class)) {
            if (view != null) view.onResumenFragmentRegistro();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (selectFragmentClass.equals(FragmentTareasSesiones.class)) {
            if (view != null) view.onResumenFragmentRubrica();
            if (view != null) view.onResumenFragmentRegistro();
            if (view != null) view.onResumenTarea();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

        if (selectFragmentClass.equals(SituacionFragment.class)) {
            if (view != null)
                comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (selectFragmentClass.equals(ComentariosFragment.class)) {
            if (view != null) view.onResumenFragmentComentarios();
            comprobarVigenciaCalendarioPeriodo();
            return;
        }
        if (selectFragmentClass.equals(NewFragment.class)) {
            comprobarVigenciaCalendarioPeriodo();
            return;
        }

    }

    private void GetFechaCreacionSesionAprendizaje() {
        handler.execute(getFechaCreacionSesionAp,
                new GetFechaCreacionSesionAprendizaje.RequestValues(sesionAprendizajeId),
                new UseCase.UseCaseCallback<GetFechaCreacionSesionAprendizaje.ResponseValue>() {
                    @Override
                    public void onSuccess(GetFechaCreacionSesionAprendizaje.ResponseValue response) {
                        f_CreacionServidor = response.getF_Servidor();
                        f_CreacionLocal = response.getF_Local();
                        comprobarFechaRubroEvaluacion();
                    }

                    @Override
                    public void onError() {
                        ocultarMsgActualizacion();
                    }
                });
    }

    private void comprobarFechaRubroEvaluacion() {
        Log.d(TAG, "comprobarFechaRubroEvaluacion: " + f_CreacionServidor + " > " + f_CreacionLocal);
        if (f_CreacionServidor > f_CreacionLocal) {
            visibleMsgActualizacion();
        } else {
            ocultarMsgActualizacion();
        }
    }

    private void comprobarVigenciaCalendarioPeriodo() {
        if (sesionAprendizajeUi == null) return;
        Log.d(TAG, "sesionAprendizajeUi.isVigente()" + sesionAprendizajeUi.isVigente());
        if (selectFragmentClass == null) hideFab();

        if (selectFragmentClass != null){
            if(sesionAprendizajeUi.isEditar()){
                if (selectFragmentClass.equals(RubricaSesionFragment.class)) showFab(R.drawable.ic_add_white_24dp);
            }else {
                if (selectFragmentClass.equals(RubricaSesionFragment.class)) hideFab();
            }

            if(sesionAprendizajeUi.isVigente()){
                if(view!=null)view.hideMsgCalendarioPeriodo();
            }else {
                if(view!=null)view.showMsgCalendarioPeriodo();
            }

            if (selectFragmentClass.equals(FragmentRubroEvalProcesoLista.class))comprobarCPRubro(sesionAprendizajeUi.isEditar(), sesionAprendizajeUi.isVigente());
            else if (selectFragmentClass.equals(FragmentTareasSesiones.class)) comprobarCPTareas( sesionAprendizajeUi.isVigente());
        }

    }

    private void comprobarCPTareas(boolean vigente) {
        view.onComprobarCPTareas(vigente);
    }

    private void comprobarCPRubro(boolean editar, boolean anclar) {
        view.onComprobarCPRubro(editar, anclar);
    }

    private void visibleMsgActualizacion() {
        if (view != null) view.showMsgActualizacion();
    }

    private void ocultarMsgActualizacion() {
        if (view != null) view.hideMsgActualizacion();
    }

}
