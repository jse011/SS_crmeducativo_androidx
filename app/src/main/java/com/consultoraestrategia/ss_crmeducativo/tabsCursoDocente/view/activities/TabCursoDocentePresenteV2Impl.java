package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui.AsistenciaCursoFragment;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.ui.TabsBandejaFragment;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.ui.ComportamientoFragment;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.view.EvaluacionCompetenciasFragment;
import com.consultoraestrategia.ss_crmeducativo.grouplist.ui.ListaGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.RubricaBidFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.silabo.RubroResultadoSilaboFragment;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.fragments.FragmentUnidades;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.ChangeDataBaseDocenteMentor;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetChangeCentroProcesamiento;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetIsAprendizajeColegio;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetIsTutor;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetParametroDisenio;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetPeriodoList;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.PrimeravesCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.ui.FragmentTareas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TabCursoDocentePresenteV2Impl extends BasePresenterImpl<TabCursoDocenteView> implements TabCursoDocentePresenter  {
    ArrayList<CompetenciaCheck> tipoCompetenciaArrayList= new ArrayList<>();
    private GetPeriodoList getPeriodoList;
    private GetIsAprendizajeColegio getIsAprendizajeColegio;
    private ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor;
    private GetParametroDisenio getParametroDisenio;
    private PrimeravesCursoDocente primeravesCursoDocente;
    private GetIsTutor getIsTutor;
    private int anioAcademicoId;
    private int perdiodoAcademicoId;
    private int grupoAcademicoId;
    private int silaboId;
    private int idEntidad;
    private int idGeoreferenciaId;
    private String bgColor;
    private int idCargaAcademica;
    private int parametrodisenioid;
    private int idProgramaEducativo;
    private int idCurso;
    private int idCargaCurso;
    private String urlBackgroudResource;
    private String[] csoData;
    private List<PeriodoUi> periodoUiList = new ArrayList<>();
    private int idCalendarioPeriodo;
    private String color3 = "";
    private String color2 = "";
    private String color1 = "";
    private Class<? extends Fragment> fragmentClassVisible;
    private boolean calendarioPeriodoVigente  =false;
    private boolean calendarioPeriodoEditar  = false;
    private boolean isAprendizajeColegio;
    private boolean filtro;
    private boolean isTutor;
    private boolean initFragment = true;//varibale para controlar que se llame en la segunda vez al onresumefragmenManager
    private boolean reinicioxcerrarcurso;
    private boolean changeDataBase = false;
    private boolean isPrimeravesCursoDocente;
    private boolean isChangeCentroAprendizaje;
    private int usuarioId;
    private int empleadoId;
    private boolean cursoComplejo;
    private boolean changeDataBaseFull = false;
    private GetChangeCentroProcesamiento getChangeCentroProcesamiento;

    public TabCursoDocentePresenteV2Impl(UseCaseHandler handler, Resources res, GetPeriodoList getPeriodoList,GetIsAprendizajeColegio getIsAprendizajeColegio, ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor,GetIsTutor getIsTutor,GetParametroDisenio getParametroDisenio, PrimeravesCursoDocente primeravesCursoDocente, GetChangeCentroProcesamiento getChangeCentroProcesamiento) {
        super(handler, res);
        this.getPeriodoList = getPeriodoList;
        this.getIsAprendizajeColegio = getIsAprendizajeColegio;
        this.changeDataBaseDocenteMentor = changeDataBaseDocenteMentor;
        this.getIsTutor = getIsTutor;
        this.getParametroDisenio = getParametroDisenio;
        this.primeravesCursoDocente = primeravesCursoDocente;
        this.getChangeCentroProcesamiento = getChangeCentroProcesamiento;
    }

    @Override
    protected String getTag() {
        return TabCursoDocentePresenteV2Impl.class.getSimpleName();
    }

    private void showFragments() {
        Log.d(getTag(), "showFragments 2 idCalendarioPeriodo: " + idCalendarioPeriodo );
        if (view != null) view.showFragments(idCargaCurso,  idCurso,idProgramaEducativo,parametrodisenioid, idCalendarioPeriodo, idCargaAcademica,calendarioPeriodoVigente, calendarioPeriodoEditar);
    }

    private void showTitle(String title) {
        if (view != null && !TextUtils.isEmpty(title)) view.showTitle(title);
    }

    private void showSubtitle(String subtitle) {
        if (view != null && !TextUtils.isEmpty(subtitle)) view.showSubtitle(subtitle);
    }

    private void intiPeriodo() {
        PeriodoUi periodoSelected = null;

        List<PeriodoUi> periodoUiList = getPeriodoList.execute(new GetPeriodoList.RequestValues(idCargaCurso, idCurso, parametrodisenioid, anioAcademicoId));

        this.periodoUiList.clear();
        this.periodoUiList.addAll(periodoUiList);
        //#region Buscar calendario periodo con el estado vigente
        for (PeriodoUi periodo : this.periodoUiList) {
            // idCalendarioPeriodo = periodo.getIdCalendarioPeriodo();
            if (periodo.getEstado()== PeriodoUi.Estado.Vigente) {
                periodoSelected = periodo;
                break;
            }
        }
        //#endregion

        //#region encontrar un calendario seleccionado
        for (PeriodoUi periodo : this.periodoUiList) {
            if(periodo.getIdCalendarioPeriodo() == idCalendarioPeriodo){
                periodoSelected = periodo;
            }
            periodo.setStatus(false);
        }
        //#endregion






        int size = this.periodoUiList.size();

        if (size > 0 && periodoSelected == null)
        {

            List<PeriodoUi> calendarioPeriodoList = new ArrayList<>(this.periodoUiList);
            Collections.sort(calendarioPeriodoList, new Comparator<PeriodoUi>() {
                public int compare(PeriodoUi o1, PeriodoUi o2) {
                    return new Date(o2.getFechaFin()).compareTo(new Date(o1.getFechaFin()));
                }
            });

            //#region Buscar el calendario en el estado creado proximo a estar vigente
            int count = 0;
            periodoSelected =  calendarioPeriodoList.get(0);
            for (PeriodoUi item_CalendarioPeriodo :  calendarioPeriodoList) {
                if (item_CalendarioPeriodo.getEstado() == PeriodoUi.Estado.Creado)
                {
                    periodoSelected =  item_CalendarioPeriodo;
                    if (count != 0 &&
                            calendarioPeriodoList.get(count - 1).getEstado() == PeriodoUi.Estado.Cerrado)
                    {
                        periodoSelected = calendarioPeriodoList.get(count - 1);
                        break;
                    }
                }

                count++;
            }
            //#endregion
        }

        if(periodoSelected==null){
            calendarioPeriodoEditar = false;
            calendarioPeriodoVigente = false;
        }

        if(periodoSelected!=null){
            periodoSelected.setStatus(true);
            idCalendarioPeriodo = periodoSelected.getIdCalendarioPeriodo();
            calendarioPeriodoEditar = periodoSelected.isEditar();
            calendarioPeriodoVigente = periodoSelected.isVigente();
        }





        validarCalendarioPeriodo();
        habilitarMenuUpdateAlumno();

        if(view!=null)view.showPeriodoList(this.periodoUiList,color3);
        if(view!=null)view.changeColorToolbar(color1);
        if(view!=null)view.changeColorFloatButon(color2);

    }


    private void validarCalendarioPeriodo(){
        if(calendarioPeriodoVigente){
            if(view!=null)view.hideMsgCalendarioPeriodo();
        }else {
            if(view!=null)view.showMsgCalendarioPeriodo();
        }

        if(fragmentClassVisible==EvaluacionCompetenciasFragment.class){
            if(view!=null)view.hideMsgCalendarioPeriodo();
        }

        if(calendarioPeriodoEditar){
            if(fragmentClassVisible==RubricaBidFragment.class)showFab(R.drawable.ic_add_white_24dp);
        }else {
            if(fragmentClassVisible==RubricaBidFragment.class)hideFab();
        }

        if(idCalendarioPeriodo==0){
            if(view!=null)view.hideMsgCalendarioPeriodo();
            if(view!=null)view.hideItemMenuUpdateAlumno();
            hideFab();
        }
    }

    private void onResumenAllFragment() {
        if (view != null)view.onResumenFragmentRubrica(idCalendarioPeriodo, false);
        if (view != null)view.onResumenFragmentRegistro(idCalendarioPeriodo,  false);
        if (view != null)view.onResumenFragmentGrupos();
        if (view != null)view.onResumenFragmentResultado(idCalendarioPeriodo, false);
        if (view != null)view.onResumenFragmentUnidades(idCalendarioPeriodo, false);
        if (view != null)view.onResumenFragmentTareas(idCalendarioPeriodo, false);
        if (view != null)view.onResumenAsistenciaCursoFragment(idCalendarioPeriodo, false);
        if (view != null)view.onResumenFragmentComportamiento(idCalendarioPeriodo, false);
    }

    private void onResumenFramentManger() {
        Log.d(getTag(),"onResumenFramentManger");
        if (fragmentClassVisible == null) {
            if(view!=null)view.onResumenFragmentRubrica(idCalendarioPeriodo, false);
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo,false);
            return;
        }

        if (fragmentClassVisible.equals(RubricaBidFragment.class)) {
            if(view!=null)view.onResumenFragmentRubrica(idCalendarioPeriodo, false);
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo, false);
            return;
        }

        if (fragmentClassVisible.equals(EvaluacionCompetenciasFragment.class)) {
            if(view!=null)view.onResumenFragmentResultado(idCalendarioPeriodo, false);
            return;
        }

        if (fragmentClassVisible.equals(ListaGrupoFragment.class)) {
            if(view!=null)view.onResumenFragmentGrupos();
            return;
        }

        if (fragmentClassVisible.equals(FragmentUnidades.class)) {
            if(view!=null) view.onResumenFragmentUnidades(idCalendarioPeriodo, false);
            if(view!=null) view.onResumenFragmentRubrica(idCalendarioPeriodo, false);
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo, false);
            if(view!=null)view.onResumenFragmentTareas(idCalendarioPeriodo, false);
            return;
        }

        if (fragmentClassVisible.equals(FragmentTareas.class)) {
            if(view!=null) view.onResumenFragmentTareas(idCalendarioPeriodo, false);
            if(view!=null)  view.onResumenFragmentRubrica(idCalendarioPeriodo, false);
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo, false);
            return;
        }

        if (fragmentClassVisible.equals(RubroResultadoSilaboFragment.class)) {
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo, false);
            return;
        }


        if (fragmentClassVisible.equals(AsistenciaCursoFragment.class)) {
            if(view!=null)view.onResumenAsistenciaCursoFragment(idCalendarioPeriodo, false);
            return;
        }

        if (fragmentClassVisible.equals(ComportamientoFragment.class)) {
            if(view!=null)view.onResumenFragmentComportamiento(idCalendarioPeriodo, false);
            return;
        }
    }

    private void hidePeriodoFinal() {
        /*PeriodoUi periodoUi = new PeriodoUi(0, "Final", "", false);
        periodoUiList.remove(periodoUi);
        if (view != null)view.showPeriodoList(periodoUiList, color3);*/
    }

    private void hideListPeriodos() {if (view != null)view.hidePeriodosLIst();}
    private void showPeriodosList() {if (view != null)view.showPeriodosLIst(); }

    private void showFabGrupos(){
        if (view != null) view.showFabGrupos();
    }
    private void hideFabGrupos(){
        if (view != null) view.hideFabGrupos();
    }

    private void showPeriodoFinal() {
        /*PeriodoUi periodoUi = new PeriodoUi(0, "Final", "", false );
        periodoUiList.remove(periodoUi);
        periodoUiList.add(periodoUi);
        if (view != null)  view.showPeriodoList(periodoUiList, color3);*/

    }

    private void onChangeTarea(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentTareas(idCalendarioPeriodo, false);
        }
    }

    private void onChangeCalendarioPeriodo(boolean success) {
        onChangeSesiones(success);
        onChangeRubro(success);
        onChangeTarea(success);


    }

    private void onChangeSesiones(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentUnidades(idCalendarioPeriodo, false);
        }
    }

    private void onChangeRubro(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentRubrica(idCalendarioPeriodo, false);
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo, false);
        }
    }

    private void onRubroResultadoSilaboFabClicked() {
        if(view!=null)view.showRubroResultadoSilaboDialogFilter(tipoCompetenciaArrayList);

    }

    private void onEvaluacionFragmentFabClicked() {
        if (view != null) view.showEvaluacionCompetenciaDialogFilter();
    }

    private void onRubricaBidFragmentFabClicked() {
        if (view != null) view.onRubricaBidFragmentFabClicked();
    }

    private void onTareaFragmentFabClicked() {
        if (view != null) view.onTareaFragmentFabClicked();
    }



    private void onGroupFragmentFabClicked() {
        if (view != null) view.onGrupoFragmentFabClicked(isAprendizajeColegio);
    }

    private void onComportamientoFabClicked() {
        if (view != null)view.onComportamientoFabClicked();
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

    private void habilitarContenidoTutor() {
        isTutor = getIsTutor.execute(idCargaAcademica);
        if(isTutor){
            if(view!=null)view.showItemMenuUpdateFotoAlumno();
        }else {
            if(view!=null)view.hideItemMenuUpdateFotoAlumno();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initFragment = true;
        fragmentClassVisible = RubricaBidFragment.class;
        this.isAprendizajeColegio = getIsAprendizajeColegio.execute(idEntidad, idGeoreferenciaId);
        if(csoData!=null){
            int length = csoData.length;
            if (length >= 1) {
                String title = csoData[0];
                String subtitle = csoData[1];
                showTitle(title);
                showSubtitle(subtitle);
            }
        }
        getParametroDisenio();
    }

    private void getParametroDisenio() {
        ParametroDisenioUi parametroDisenioUi = getParametroDisenio.execute(parametrodisenioid);
        if(parametroDisenioUi!=null){
            color1 = parametroDisenioUi.getColor1();
            color2 = parametroDisenioUi.getColor2();
            color3 = parametroDisenioUi.getColor3();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        intiPeriodo();
        if(initFragment){
            if(view!=null)view.showAutoHideProgress();
            showFragments();
            isPrimeravesCursoDocente = idCalendarioPeriodo != 0 && primeravesCursoDocente.execute(idCargaCurso, idCalendarioPeriodo,silaboId, idProgramaEducativo, usuarioId, empleadoId, idCargaAcademica, idGeoreferenciaId,idEntidad,idCurso);
            isChangeCentroAprendizaje = idCalendarioPeriodo != 0 && getChangeCentroProcesamiento.execute(idCargaCurso, idCalendarioPeriodo);
            primeravesCursoDocente();
            changeCentroAprendizaje();
        }else {
            changeDataBaseDocenteMentor();

            if(!changeDataBase){
                //validar que si existe mensaje de notificacion de base datos del tabsesionActivity
                if(view!=null)changeDataBase = view.ischangeDataBase();
            }

            if(changeDataBaseFull){

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeDataBase = false;
                        changeDataBaseFull = false;
                        if(view!=null)view.successchangeDataBase();
                        onResumenAllFragment();
                    }
                },500);


            }else if(changeDataBase){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeDataBase = false;
                        if(view!=null)view.successchangeDataBase();
                        onResumenFramentManger();
                    }
                },500);
            }

        }
        initFragment = false;


    }

    private void changeCentroAprendizaje() {
        if(!isPrimeravesCursoDocente){
            if(isChangeCentroAprendizaje){
                if(view!=null)view.startChangeCentroProcesamiento(idCalendarioPeriodo, silaboId, idCargaCurso);
            }
        }
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras != null){
            csoData = extras.getStringArray("csoData");
            urlBackgroudResource = extras.getString("backgroudResource");
            idCargaCurso = extras.getInt("idCargaCurso", 0);
            idCurso = extras.getInt("cursoId", 0);
            idProgramaEducativo = extras.getInt("idProgramaEducativo", 0);
            parametrodisenioid = extras.getInt("parametrodisenioid", 0);
            CRMBundle crmBundle = new CRMBundle(extras);
            idCargaAcademica= crmBundle.getCargaAcademicaId();
            bgColor = extras.getString("fondo");
            idGeoreferenciaId = crmBundle.getGeoreferenciaId();
            idEntidad = crmBundle.getEntidadId();
            silaboId = crmBundle.getSilaboEventoId();
            grupoAcademicoId = crmBundle.getGrupoAcademicoId();
            perdiodoAcademicoId = crmBundle.getPeriodAcademicoId();
            anioAcademicoId = crmBundle.getAnioAcademico();
            reinicioxcerrarcurso= extras.getBoolean(TabsCursoDocenteActivity.RELOAD_X_CERRAR_CURSO);
            idCalendarioPeriodo = crmBundle.getCalendarioPeriodoId();
            usuarioId = crmBundle.getUsuarioId();
            empleadoId = crmBundle.getEmpleadoId();
            cursoComplejo = crmBundle.isComplejo();
            isPrimeravesCursoDocente = idCalendarioPeriodo != 0 && primeravesCursoDocente.execute(idCargaCurso, idCalendarioPeriodo,silaboId, idProgramaEducativo, usuarioId, empleadoId, idCargaAcademica, idGeoreferenciaId,idEntidad,idCurso);
            isChangeCentroAprendizaje = idCalendarioPeriodo != 0 && getChangeCentroProcesamiento.execute(idCargaCurso, idCalendarioPeriodo);
        }

    }

    private void primeravesCursoDocente(){
        if(isPrimeravesCursoDocente){
            if(view!=null)view.showProgressService2(usuarioId, empleadoId, idProgramaEducativo, idCargaCurso, idCalendarioPeriodo, idGeoreferenciaId, idEntidad, silaboId, idCurso, idCargaAcademica,cursoComplejo, anioAcademicoId);
        }
    }

    private void showFab(@DrawableRes int drawable) {
        if (view != null) view.changeFabBg(drawable);
    }
    private void hideFab() {
        if (view != null) view.hideFab();
    }

    @Override
    public void onFabClicked(int pagePosition) {
        Log.d(getTag(), "pagePosition: " + pagePosition);

        if (fragmentClassVisible.equals(RubricaBidFragment.class)) {
            onRubricaBidFragmentFabClicked();

            return;
        }

        if (fragmentClassVisible.equals(EvaluacionCompetenciasFragment.class)) {
            onEvaluacionFragmentFabClicked();
            return;
        }
        if (fragmentClassVisible.equals(ListaGrupoFragment.class)) {
            onGroupFragmentFabClicked();
            return;
        }


        if (fragmentClassVisible.equals(FragmentTareas.class)) {
            onTareaFragmentFabClicked();
            return;
        }

        if (fragmentClassVisible.equals(RubroResultadoSilaboFragment.class)) {
            onRubroResultadoSilaboFabClicked();
            return;
        }

        if (fragmentClassVisible.equals(ComportamientoFragment.class)) {
            onComportamientoFabClicked();
            return;
        }
    }

    @Override
    public void onFabClickedGrupos() {
        if(filtro)filtro=false;
        else {filtro=true;}
        if(view!=null) view.onFiltroGrupos(filtro);
    }

    @Override
    public void onPageChanged(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == null) return;
        fragmentClassVisible = fragmentClass;

        if(calendarioPeriodoVigente||idCalendarioPeriodo==0){
            if(view!=null)view.hideMsgCalendarioPeriodo();
        }else {
            if(view!=null)view.showMsgCalendarioPeriodo();
        }

        if (fragmentClass.equals(RubricaBidFragment.class)) {
            if(calendarioPeriodoEditar){
                if(idCalendarioPeriodo!=0)showFab(R.drawable.ic_add_white_24dp);
            }else {
                hideFab();
            }
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            return;
        }

        if (fragmentClass.equals(EvaluacionCompetenciasFragment.class)) {
            if(idCalendarioPeriodo!=0)showFab(R.drawable.ic_filter_list_white_24dp);
            showPeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            if(view!=null)view.hideMsgCalendarioPeriodo();
            return;
        }


        if (fragmentClass.equals(RubroResultadoSilaboFragment.class)) {
            if(idCalendarioPeriodo!=0)showFab(R.drawable.ic_filter_list_white_24dp);
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            return;
        }


        if (fragmentClass.equals(FragmentUnidades.class)) {
            hideFab();
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            return;
        }

        if (fragmentClass.equals(ListaGrupoFragment.class)) {
            if(idCalendarioPeriodo!=0)showFab(R.drawable.ic_add_white_24dp);
            showFabGrupos();
            hideListPeriodos();
            return;
        }


        if (fragmentClass.equals(FragmentTareas.class)) {
            hideFab();
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            return;
        }


        if (fragmentClass.equals(TabsBandejaFragment.class)) {
            hideFab();
            hideFabGrupos();
            return;
        }

        if(fragmentClass.equals(AsistenciaCursoFragment.class)){
            hideFab();
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            if(view!=null)view.contraerToolbar();
            return;
        }
        if (fragmentClass.equals(ComportamientoFragment.class)) {
            if(idCalendarioPeriodo!=0)showFab(R.drawable.ic_add_white_24dp);
            hideFabGrupos();
            hidePeriodoFinal();
            showPeriodosList();
            return;
        }
    }

    @Override
    public void onBtnSuccesItemFilterRubro(ArrayList<CompetenciaCheck> tipoCompetencia) {
        this.tipoCompetenciaArrayList=tipoCompetencia;
        if (view != null) {
            view.onEvaluacionRubroSilaboFragmentFabClicked(tipoCompetencia);
        }
    }

    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {

        Log.d(getTag(), "onPeriodoCargaCursoSelected : " + periodoUi.getIdPeriodo());
        if (idCalendarioPeriodo == periodoUi.getIdCalendarioPeriodo()) {
            return;
        }
        for (PeriodoUi itemPeriodoUi: periodoUiList)itemPeriodoUi.setStatus(false);

        periodoUi.setStatus(true);
        if (view != null) view.changePeriodo();
        if(view!=null)view.changeBundleCalendarioId(idCalendarioPeriodo);
        idCalendarioPeriodo = periodoUi.getIdCalendarioPeriodo();
        calendarioPeriodoVigente = periodoUi.isVigente();
        calendarioPeriodoEditar = periodoUi.isEditar();
        validarCalendarioPeriodo();
        habilitarMenuUpdateAlumno();
        isPrimeravesCursoDocente = primeravesCursoDocente.execute(idCargaCurso, idCalendarioPeriodo,silaboId, idProgramaEducativo, usuarioId, empleadoId, idCargaAcademica, idGeoreferenciaId,idEntidad,idCurso);
        isChangeCentroAprendizaje = getChangeCentroProcesamiento.execute(idCargaCurso, idCalendarioPeriodo);
        primeravesCursoDocente();
        changeCentroAprendizaje();

        //continue PostDelaychangePeriodo
    }

    @Override
    public void actualizarRubroTipoAnclaFragmentRegistro() {
        if (view != null) view.onActualizarEvaluacionFrament();
    }
    /*Este metodo llega desde RubricaFragment,Cuando se agrega*/
    @Override
    public void onActualizarItemRubrica() {
        if (view != null) view.onActualizarRegistroFrament();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onClickUpdate() {
        //eliminar
    }

    @Override
    public void onChangeRubroUpdate() {
        if (view != null) view.onActualizarRegistroFrament();
    }

    @Override
    public void onClickRefrescar() {
       /* boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }
        if(view!=null)view.onCallSinckService(idProgramaEducativo);*/
        if(idCalendarioPeriodo!=0){
            if(view!=null)view.showActivityService2(usuarioId, empleadoId, idProgramaEducativo, idCargaCurso, idCalendarioPeriodo, idGeoreferenciaId, idEntidad, silaboId, idCurso, idCargaAcademica, anioAcademicoId,cursoComplejo);
        }
        else{
            if(view!=null)view.showMessage("Seleccioné un calendario periodo");
        }
    }

    @Override
    public void onChange(TipoImportacion tipoImportacion, boolean success) {
        switch (tipoImportacion){
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
    public void onAceptarBottomDialog(Object o) {

    }

    @Override
    public void onChangeFull(boolean success) {
        if(idCalendarioPeriodo>0){
            List<PeriodoUi> periodoUiList = getPeriodoList.execute(new GetPeriodoList.RequestValues(idCargaCurso, idCurso, parametrodisenioid, anioAcademicoId));
            PeriodoUi selectedPeriodoUi = null;
            for (PeriodoUi periodoUi : this.periodoUiList){
                for (PeriodoUi newPeriodoUi: periodoUiList){
                    if(periodoUi.getIdCalendarioPeriodo()==newPeriodoUi.getIdCalendarioPeriodo()){

                        periodoUi.setVigente(newPeriodoUi.isVigente());
                        periodoUi.setEditar(newPeriodoUi.isEditar());
                        periodoUi.setEstado(newPeriodoUi.getEstado());

                        if(periodoUi.getIdCalendarioPeriodo()==idCalendarioPeriodo){
                            selectedPeriodoUi = periodoUi;
                        }
                    }
                }
            }

            if(selectedPeriodoUi!=null){
                calendarioPeriodoVigente = selectedPeriodoUi.isVigente();
                calendarioPeriodoEditar = selectedPeriodoUi.isEditar();
            }else {
                calendarioPeriodoVigente = false;
                calendarioPeriodoEditar = false;
            }

            validarCalendarioPeriodo();
            habilitarMenuUpdateAlumno();
        }

       onResumenAllFragment();
    }

    @Override
    public void delteRubrica() {
        Log.d(getTag(), "delteRubrica");
        if(view!=null) view.onActualizarRegistroFrament();
        if(view!=null) view.onResumenFragmentRegistro(idCalendarioPeriodo, false);
        if(view!=null) view.onResumenFragmentTareas(idCalendarioPeriodo,false);
        if(view!=null) view.onResumenFragmentUnidades(idCalendarioPeriodo,false);
    }

    @Override
    public void onDeleteRubro() {
        if(view!=null) view.onResumenFragmentTareas(idCalendarioPeriodo, false);
    }

    @Override
    public void onFinishSynck() {
        changeDataBaseDocenteMentor();
        if(view!=null)view.comprobarSiActulizaronCorrectementeRubros();
    }

    @Override
    public void onCreateOptionsMenu() {
        changeDataBaseDocenteMentor();
        habilitarContenidoTutor();
        habilitarMenuUpdateAlumno();

    }

    private void habilitarMenuUpdateAlumno() {
        if(calendarioPeriodoVigente && idCalendarioPeriodo!=0){
            if(view!=null)view.showItemMenuUpdateAlumno();
        }else {
            if(view!=null)view.hideItemMenuUpdateAlumno();
        }
    }

    @Override
    public void onClickUpadteAlumnos() {
        if(view!=null)view.showProgressDialogUpdateAlumnos();
        if(view!=null)view.showSincContratoAlumnos(silaboId, grupoAcademicoId, perdiodoAcademicoId, idCalendarioPeriodo, idProgramaEducativo);
    }

    @Override
    public void onClickUpadteFotoAlumnos() {
        if(view!=null)view.showCambiarFotoAlumnoActivity(idCargaCurso);
    }

    @Override
    public void PostDelaychangePeriodo() {
        onResumenAllFragment();
    }

    @Override
    public void onConfigurationChanged() {
        if(view!=null)view.changeOrientationUnidad();
    }

    @Override
    public void onChangeDatabseDesdeTabSesion() {
        changeDataBase = true;
    }

    @Override
    public void onChangeDatabseDesdeService2() {
        changeDataBaseFull = true;
    }

    @Override
    public void onClickAceptarRevizarErroresActualizar() {
        if(idCalendarioPeriodo!=0){
            if(view!=null)view.showActivityService2(usuarioId, empleadoId, idProgramaEducativo, idCargaCurso, idCalendarioPeriodo, idGeoreferenciaId, idEntidad, silaboId, idCurso, idCargaAcademica, anioAcademicoId, cursoComplejo);
        }
        else{
            if(view!=null)view.showMessage("Seleccioné un calendario periodo");
        }
    }

    @Override
    public void onClickCalendario() {
        SessionUser sessionUser = SessionUser.getCurrentUser();
        int usuarioId = sessionUser!=null?sessionUser.getUserId(): 0;
        if(view!=null)view.showActivityAgenda(usuarioId, idGeoreferenciaId, empleadoId, anioAcademicoId, idEntidad, idCargaCurso);
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
