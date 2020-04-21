package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetIndicadorList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubroList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.PublicarEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetAlumnoGrupoList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetNotaEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveGrupoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 5/10/2017.
 */

public class RegistroPresenterImpl implements RegistroPresenter {
    private String TAG = "RegistroPresenterImpl";
    private RegistroView view;
    private List<RubroEvaluacionUi> rubroEvaluacionUis;
    private UseCaseHandler useCaseHandler;
    private GetRubroList getRubroList;
    private GetAlumnoGrupoList getAlumnoList;
    private GetIndicadorList getIndicadorList;
    private GetNotaEvaluacionList getNotaEvaluacionList;
    private SaveGrupoEvaluacion saveGrupoEvaluacion;
    private int eventoIdFloatButton;
    private float posicionElementoX;
    private float posicionElementoY;
    private RubroEvaluacionUi rubroEvaluacionUi;
    private int sesionAprendizajeId;
    private int idCargaCurso;
    private int cursoId;
    private int idCargaAcademica;
    private int idEntidad;
    private int idGeoreferencia;
    private List<IndicadorUi> indicadorUis;
    private List<GrupoEvaluacionUi> gruposEvaluacion;
    private List<Object> itemsEvaluacion;
    private GetRubro getRubro;
    private String rubroEvaluacionProcesoId;
    private final int INITRUBRO = 1, TABSRUBRO = 0;
    private boolean disabledEval;
    private FiltroTableUi filtroTableUi;
    private Object itemEvaluacionUiSelect;
    private PublicarEvaluacion publicarEvaluacion;
    private boolean btnEye;
    private boolean btnFooter;

    public RegistroPresenterImpl(UseCaseHandler useCaseHandler, GetRubroList getRubroList, GetAlumnoGrupoList getAlumnoList, GetIndicadorList getIndicadorList, SaveGrupoEvaluacion saveGrupoEvaluacion, GetNotaEvaluacionList getNotaEvaluacionList, GetRubro getRubro, PublicarEvaluacion publicarEvaluacion) {
        this.useCaseHandler = useCaseHandler;
        this.getRubroList = getRubroList;
        this.getAlumnoList = getAlumnoList;
        this.getIndicadorList = getIndicadorList;
        this.rubroEvaluacionUi = new RubroEvaluacionUi();
        this.indicadorUis = new ArrayList<>();
        this.rubroEvaluacionUis = new ArrayList<>();
        this.itemsEvaluacion = new ArrayList<>();
        this.gruposEvaluacion = new ArrayList<>();
        this.saveGrupoEvaluacion = saveGrupoEvaluacion;
        this.getNotaEvaluacionList = getNotaEvaluacionList;
        this.getRubro = getRubro;
        this.publicarEvaluacion = publicarEvaluacion;
    }

    @Override
    public void attachView(RegistroView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        /*if(view != null){
            int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
            if(posicion == -1){
                getRubroList();
            }
            getIndicadorList();
        }*/

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    //region Float Button
    @Override
    public void actionDowButtonFloat(int eventoIdFloatButton, float posicionEventoX, float posicionEventoY, float posicionElementoX, float posicionElementoY) {
        this.posicionElementoX = posicionElementoX - posicionEventoX;
        this.posicionElementoY = posicionElementoY - posicionEventoY;
        this.eventoIdFloatButton = eventoIdFloatButton;
    }

    @Override
    public void actionMoveButtonFloat(int eventoIdFloatButton, float posicionEventoX, float posicionEventoY) {
        if (view != null) {
            view.moverButtonfloat(posicionEventoX + posicionElementoX, posicionEventoY + posicionElementoY);
            this.eventoIdFloatButton = eventoIdFloatButton;
        }
    }

    @Override
    public void actionUpButtonFloat() {
        if (view != null) {
            // evento MotionEvent.ACTION_DOWN = 0
            if (eventoIdFloatButton == 0) {
                view.showDialogInidicador(indicadorUis);
            }
        }
    }
    //endregion Float Button

    //region Evaluacion
    @Override
    public void onClickEvaluacion(NotaUi evaluacion) {

    }

    @Override
    public void onClickLongEvaluacion(NotaUi evaluacion) {

    }
    //endregion Evaluacion

    @Override
    public void onItemSelectRubroEval(RubroEvaluacionUi rubroEvaluacionUi) {
        if (view != null) {
            view.showProgress();
            toogleRubroEvaluacionProceso(rubroEvaluacionUi);
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            getNotaAlumnoList();
        }

    }

    @Override
    public void onClickItemIndicador(IndicadorUi indicadorUi) {
        if (view != null) {
            view.showDialogAgregarRubro(sesionAprendizajeId, indicadorUi.getId());
            view.hideDialogInidicador();
        }
    }

    @Override
    public void setExtras(Bundle extras) {
        sesionAprendizajeId = extras.getInt(FragmentRubroEvalProcesoLista.TAG_SESIONAPRENDIZAJEID);
        idCargaCurso = extras.getInt(FragmentRubroEvalProcesoLista.TAG_CARGACURSO_ID);
        cursoId = extras.getInt(FragmentRubroEvalProcesoLista.TAG_CARGACURSO_ID);
        rubroEvaluacionProcesoId = extras.getString(EvaluacionContainerActivity.RUBROEVALUACIONID);
        disabledEval = extras.getBoolean(EvaluacionContainerActivity.DESAVILITAR_EVALUACION, false);
        idCargaAcademica = extras.getInt(FragmentRubroEvalProcesoLista.TAG_CARGA_ACADEMICA_ID);
        CRMBundle crmBundle = new CRMBundle(extras);
        idEntidad = crmBundle.getEntidadId();
        idGeoreferencia = crmBundle.getGeoreferenciaId();
        Log.d(TAG, "extras : " + extras);
    }

    @Override
    public void onChangeCrearRubro() {
        getRubroList();
    }

    @Override
    public void onChangeRubro() {
        getRubro();
    }

    @Override
    public void onSelectAlumnoEvaluacion(NotaUi notaUi) {


        if (notaUi.getResaltar()) {
            notaUi.setResaltar(false);
        } else {
            notaUi.setResaltar(true);
        }

        AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
        GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();

        if ( alumnosEvaluacionUiSelected != null) {

            if(!alumnosEvaluacionUiSelected.isVigente())return;

            try {
                GrupoEvaluacionUi grupoEvaluacionUi = alumnosEvaluacionUiSelected.getParent();
                NotaUi notaSelecionpadre = null;
                for (NotaUi itemNotaUi : grupoEvaluacionUi.getNotaUis()) {
                    if (itemNotaUi.getId().equals(notaUi.getId())) {
                        notaSelecionpadre = itemNotaUi;
                        break;
                    }
                }
                if (notaSelecionpadre.getResaltar()) {
                    notaUi.setNota(notaSelecionpadre.getNota());
                } else {
                    notaUi.setNota(notaUi.getNotaEvaluacion());
                }
            } catch (Exception e) {
                e.printStackTrace();
                notaUi.setNota(notaUi.getNotaEvaluacion());
            }

            NotaUi notaUiAnterior = alumnosEvaluacionUiSelected.getNotaUi();
            if (notaUiAnterior != null) {
                notaUiAnterior.setNota(notaUiAnterior.getNota());
                if (notaUiAnterior != notaUi) {
                    notaUiAnterior.setResaltar(false);
                }
            }
            alumnosEvaluacionUiSelected.setNotaUi(notaUi);
            saveRegistroEvaluacion(new GrupoEvaluacionUi(), alumnosEvaluacionUiSelected);
        } else if (grupoEvaluacionUiSelected != null) {

            notaUi.setNota(notaUi.getNotaEvaluacion());
            NotaUi notaUiAnterior = grupoEvaluacionUiSelected.getNotaUi();
            if (notaUiAnterior != null) {
                notaUiAnterior.setNota(notaUiAnterior.getNotaEvaluacion());
                if (notaUiAnterior != notaUi) {
                    notaUiAnterior.setResaltar(false);
                }
            }
            grupoEvaluacionUiSelected.setNotaUi(notaUi);

            int posionNotaUi = grupoEvaluacionUiSelected.getNotaUis().indexOf(notaUi);
            if (posionNotaUi != -1) {

                for (AlumnosEvaluacionUi itemAlumnosUi : grupoEvaluacionUiSelected.getAlumnosEvaluacionUis()) {
                    if(!itemAlumnosUi.isVigente())continue;
                    NotaUi alumnoNotaUi = itemAlumnosUi.getNotaUis().get(posionNotaUi);
                    alumnoNotaUi.setResaltar(notaUi.getResaltar());
                    alumnoNotaUi.setNota(notaUi.getNotaEvaluacion());
                    NotaUi alumnoNotaUiAnterior = itemAlumnosUi.getNotaUi();
                    if (alumnoNotaUiAnterior != null) {
                        alumnoNotaUiAnterior.setNota(alumnoNotaUiAnterior.getNotaEvaluacion());
                        if (!alumnoNotaUiAnterior.equals(alumnoNotaUi)) {
                            alumnoNotaUiAnterior.setResaltar(false);
                        }
                    }

                    itemAlumnosUi.setNotaUi(alumnoNotaUi);
                }

            }
            if (view != null) view.changeTable();
            saveRegistroEvaluacion(grupoEvaluacionUiSelected, new AlumnosEvaluacionUi());

        }

    }

    @Override
    public void onSelectAlumnoGrupo(Object itemEvaluacionUi) {
        if (itemEvaluacionUi instanceof GrupoEvaluacionUi) {
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) itemEvaluacionUi;
            Log.d(TAG, "Grupo is togle " + grupoEvaluacionUi.isTogle());
            if (grupoEvaluacionUi.isTogle()) {
                grupoEvaluacionUi.setTogle(false);
            } else {
                grupoEvaluacionUi.setTogle(true);
            }

            List<AlumnosEvaluacionUi> columnHeaders = grupoEvaluacionUi.getAlumnosEvaluacionUis();
            if (columnHeaders == null) return;
            int posicion = itemsEvaluacion.indexOf(grupoEvaluacionUi);
            Log.d(TAG, "psocion: " + posicion);
            if (posicion == -1) return;
            try {
              ;
                List<List<NotaUi>> cellList = new ArrayList<>();
                for (AlumnosEvaluacionUi alumnosEvaluacionUi : columnHeaders) {
                    cellList.add(alumnosEvaluacionUi.getNotaUis());
                }

                if (grupoEvaluacionUi.isTogle()) {
                    if (view == null) return;
                    view.addRowRange(grupoEvaluacionUi, columnHeaders, cellList);
                } else {
                    if (view == null) return;
                    view.removeRowRange(grupoEvaluacionUi,columnHeaders, cellList);
                }

            } catch (Exception ignored) {
            }

        } else {
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) itemEvaluacionUi;
            if (view != null) view.showDilogInfoUsuario(alumnosEvaluacionUi);
        }
    }

    @Override
    public void onAddCrear() {
        showTeamList(idCargaCurso, cursoId, rubroEvaluacionUi.getId(), idCargaAcademica);

    }

    private void showTeamList(int idCargaCurso, int cursoId, String rubroEvaluacionId, int idCargaAcademica) {
        if (disabledEval) {

        } else {
            view.showFrameGroupsList();
            if (view != null)
                view.showTeamList(idCargaCurso, cursoId, rubroEvaluacionId, idCargaAcademica);
        }

    }

    @Override
    public void onChangeAlumnoList() {
        getAlumnoList();
    }

    @Override
    public void onSelectAlumnoEvaluacionTecladoNumerico(NotaUi nota) {

        AlumnosEvaluacionUi alumnosEvaluacionUiSelected = nota.getAlumnoEvaluacion();
        GrupoEvaluacionUi grupoEvaluacionUiSelected = nota.getGrupoEvaluacion();

        if (grupoEvaluacionUiSelected != null) {
            SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) nota;
            view.showDilogEvaluacionTecladoNumerico(sesionAprendizajeId, rubroEvaluacionUi, grupoEvaluacionUiSelected, selectorNumericoUi, gruposEvaluacion);
        } else if(alumnosEvaluacionUiSelected != null) {

            if(!alumnosEvaluacionUiSelected.isVigente())return;

            GrupoEvaluacionUi grupoEvaluacionUi = alumnosEvaluacionUiSelected.getParent();
            if (grupoEvaluacionUi == null) return;
            List<AlumnosEvaluacionUi> alumnosEvaluacionUis = grupoEvaluacionUi.getAlumnosEvaluacionUis();
            SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) nota;
            view.showDilogEvaluacionTecladoNumerico(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUiSelected, selectorNumericoUi, alumnosEvaluacionUis);
        }
    }

    @Override
    public void changeEvaluacion() {
        if (view != null) view.changeTable();
    }

    @Override
    public void onClickIndicador() {
        if (view != null) view.onShowInfoRubro(rubroEvaluacionProcesoId);
    }

    @Override
    public void onLongClickNota(NotaUi notaUi) {

        AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
        GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();

        if(alumnosEvaluacionUiSelected != null){

            if(!alumnosEvaluacionUiSelected.isVigente())return;

            if (!notaUi.getResaltar()) return;
            this.itemEvaluacionUiSelect = alumnosEvaluacionUiSelected;
            if (view != null)
                view.onShowPresionEvaluacion(notaUi.getNotaEvaluacion(), rubroEvaluacionProcesoId, notaUi.getId(), notaUi.getEstilo().getColor(), notaUi.isIntervalo());
        }else if(grupoEvaluacionUiSelected != null){
            if (!notaUi.getResaltar()) return;
            this.itemEvaluacionUiSelect = grupoEvaluacionUiSelected;
            if (view != null)
                view.onShowPresionEvaluacion(notaUi.getNotaEvaluacion(), rubroEvaluacionProcesoId, notaUi.getId(), notaUi.getEstilo().getColor(), notaUi.isIntervalo());
        }


    }

    @Override
    public void onSelectPrecicionEvaluacion(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        if (itemEvaluacionUiSelect == null) return;
        if (itemEvaluacionUiSelect instanceof AlumnosEvaluacionUi) {
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) itemEvaluacionUiSelect;
            NotaUi notaUi = alumnosEvaluacionUi.getNotaUi();
            if (notaUi == null) return;
            notaUi.setNota(notaActual);
            if (view != null) view.changeTable();
            saveRegistroEvaluacion(new GrupoEvaluacionUi(), alumnosEvaluacionUi);
        } else if (itemEvaluacionUiSelect instanceof GrupoEvaluacionUi) {
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) itemEvaluacionUiSelect;
            NotaUi notaUi = grupoEvaluacionUi.getNotaUi();
            if (notaUi == null) return;
            notaUi.setNota(notaActual);
            int posionNotaUi = grupoEvaluacionUi.getNotaUis().indexOf(notaUi);
            if (posionNotaUi != -1) {
                for (AlumnosEvaluacionUi itemAlumnosUi : grupoEvaluacionUi.getAlumnosEvaluacionUis()) {
                    NotaUi alumnoNotaUi = itemAlumnosUi.getNotaUis().get(posionNotaUi);
                    if(itemAlumnosUi.isVigente())alumnoNotaUi.setNota(notaActual);
                }

            }
            if (view != null) view.changeTable();
            saveRegistroEvaluacion(grupoEvaluacionUi, new AlumnosEvaluacionUi());
        }


    }

    @Override
    public void setCleanList() {
        for (GrupoEvaluacionUi grupoEvaluacionUi : gruposEvaluacion) {
            NotaUi notaUi = grupoEvaluacionUi.getNotaUi();
            if (notaUi != null) {
                notaUi.setResaltar(false);
                notaUi.setNota(notaUi.getNotaDefault());
            }

            for (AlumnosEvaluacionUi alumnosEvaluacionUi : grupoEvaluacionUi.getAlumnosEvaluacionUis()) {
                if(!alumnosEvaluacionUi.isVigente())continue;
                NotaUi notaUis = alumnosEvaluacionUi.getNotaUi();
                if (notaUis != null) {
                    notaUis.setResaltar(false);
                    notaUis.setNota(notaUis.getNotaDefault());
                    alumnosEvaluacionUi.setNotaUi(notaUi);
                }
            }

            if (view != null) view.changeTable();
            saveRegistroEvaluacion(grupoEvaluacionUi, new AlumnosEvaluacionUi());

        }
    }

    @Override
    public void onClickBtnPublicar(OptionPublicar optionPublicar) {
        if(!optionPublicar.isSelected()){
            optionPublicar.setSelected(true);
        }else {
            optionPublicar.setSelected(false);
        }

        publicarEvaluacion.execute(optionPublicar);
        if(view!=null)view.notifyChangeDataBase();
    }

    @Override
    public void onClickBtnComentario(OptionComentario optionComentario) {
        AlumnosEvaluacionUi alumnosEvaluacionUiSelected = optionComentario.getAlumnoEvaluacion();
        if(alumnosEvaluacionUiSelected != null){
            if(!alumnosEvaluacionUiSelected.isVigente())return;

            if(view!=null)view.showInfoComentario(optionComentario.getEvaluacionId());
        }

    }

    @Override
    public void onSelectAlumnoEvaluacionSelectorNota(NotaUi notaUi) {
        AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
        GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();

        SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) notaUi;

        if(grupoEvaluacionUiSelected != null){
           if(view!=null)view.showDilogEvaluacionSelectorNotas(sesionAprendizajeId, rubroEvaluacionUi, grupoEvaluacionUiSelected, selectorNumericoUi, gruposEvaluacion);
        }else if(alumnosEvaluacionUiSelected != null){

            if(!alumnosEvaluacionUiSelected.isVigente())return;

            GrupoEvaluacionUi grupoEvaluacionUi = alumnosEvaluacionUiSelected.getParent();
            if (grupoEvaluacionUi == null) return;
            List<AlumnosEvaluacionUi> alumnosEvaluacionUis = grupoEvaluacionUi.getAlumnosEvaluacionUis();
            if(view!=null)view.showDilogEvaluacionSelectorNotas(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUiSelected, selectorNumericoUi, alumnosEvaluacionUis);
        }

    }

    @Override
    public void onClickEye() {
        if(btnEye){
            changeEyeSimple();
            changeTableSimple();
            btnEye = false;
        }else {
            changerEyeFocus();
            changeTableAvanzado();
            btnEye = true;
        }
    }

    @Override
    public void onClickFooter() {
        if(btnFooter){
            btnFooter = false;
            changeSwiteOff();
            hideFooter();
        }else {
            btnFooter = true;
            changeSwiteOn();
            showFooter();
        }
    }

    private void changeSwiteOn(){
        if(view!=null)view.changeSwiteOn();
    }

    private void changeSwiteOff(){
        if(view!=null)view.changeSwiteOff();
    }

    private void changeEyeSimple(){
        if(view!=null)view.changeEyeSimple();
    }

    private void changerEyeFocus(){
        if(view!=null)view.changerEyeFocus();
    }

    private void showFooter(){
        if(view!=null)view.showFooter();
    }

    private void hideFooter(){
        if(view!=null)view.hideFooter();
    }

    private void changeTableAvanzado(){
        if(view!=null)view.changeTableAvanzado();
    }

    private void changeTableSimple(){
        if(view!=null)view.changeTableSimple();
    }


    private void formatAlumnoList(){
        itemsEvaluacion.clear();
        for (GrupoEvaluacionUi grupoEvaluacionUi:  gruposEvaluacion){
            itemsEvaluacion.add(grupoEvaluacionUi);
            if(grupoEvaluacionUi.isTogle()){
                itemsEvaluacion.addAll(grupoEvaluacionUi.getAlumnosEvaluacionUis());
            }else{
                itemsEvaluacion.removeAll(grupoEvaluacionUi.getAlumnosEvaluacionUis());
            }
        }

        if(view != null){
            view.changeNotaEvaluacion();
        }

    }

    private void showRubroList(List<RubroEvaluacionUi> rubros){
        if(view != null)
        {
            int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
            if(posicion == -1){
                posicion = 0;
            }

            if(rubros.size() > 0)
            {

                rubroEvaluacionUi = rubros.get(posicion);
                rubroEvaluacionUi.setStatus(true);
            }

            setRubroEvaluacionProceso(rubros);
            view.setRubro(rubros);
        }
    }

    private void showAlumnosList(List<Object> evaluados){
        if(view != null){
            view.hideProgess();
            IndicadorUi indicadorUi = rubroEvaluacionUi.getIndicadorUi();
            String indicador = indicadorUi.getAlias();
            if(indicador==null||indicador.trim().isEmpty()){
                indicador = indicadorUi.getTitle();
            }
            if(rubroEvaluacionUi.isStatus()){

                List<NotaUi> notaUis = new ArrayList<>();
                if(evaluados.size() > 0)
                {
                    if(evaluados.get(0) instanceof AlumnosEvaluacionUi){
                        AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi)evaluados.get(0);
                        notaUis.addAll(alumnosEvaluacionUi.getNotaUis());
                    }else if(evaluados.get(0) instanceof GrupoEvaluacionUi){
                        GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi)evaluados.get(0);
                        notaUis.addAll(grupoEvaluacionUi.getNotaUis());
                    }

                    for (NotaUi notaUi: notaUis){
                        notaUi.setIntervalos(setRangoNota(notaUi));
                    }
                    view.hideFrameGroupsList();
                    view.showEvaluacion(evaluados,notaUis, rubroEvaluacionUi , indicador,disabledEval);

                    List<NotaUi> notaUiList = new ArrayList<>();
                    for (NotaUi notaUi: notaUis){
                        if(!(notaUi instanceof OptionPublicar)&&
                                !(notaUi instanceof OptionEmpty)&&
                                !(notaUi instanceof OptionComentario)){
                            notaUiList.add(notaUi);
                        }
                    }

                    view.showListarRubros(notaUiList,rubroEvaluacionUi.getTipoNota());
                  ;
                }else{
                    switch (rubroEvaluacionUi.getTipo()){
                        case RUBRICA_DETALLE:

                            break;
                        case NORMAL:

                            break;
                    }
                    view.showEvaluacion(evaluados,notaUis, rubroEvaluacionUi ,indicador, disabledEval);
                    Log.d(TAG, "idCargaCurso "+  idCargaCurso+ " cursoId  " + " rubroEvaluacionUi "+ rubroEvaluacionUi.getId() );
                    showTeamList(idCargaCurso,cursoId,rubroEvaluacionUi.getId(), idCargaAcademica);
                }

            }

        }
    }


    private void setGetAlumnoList(List<GrupoEvaluacionUi> evaluados){

        Log.d(TAG,"evalacion size:"+evaluados.size());
        gruposEvaluacion.clear();
        gruposEvaluacion.addAll(evaluados);

        itemsEvaluacion.clear();
        itemsEvaluacion.addAll(evaluados);
        showAlumnosList(itemsEvaluacion);



    }

    private void toogleRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi){
        if(rubroEvaluacionUi != this.rubroEvaluacionUi){
            if(this.rubroEvaluacionUi != null){
                this.rubroEvaluacionUi.setStatus(false);
                updateRubroEvaluacionProceso(this.rubroEvaluacionUi);
            }
            if(rubroEvaluacionUi != null){
                rubroEvaluacionUi.setStatus(true);
               updateRubroEvaluacionProceso(rubroEvaluacionUi);
            }
            view.setRubro(rubroEvaluacionUis);
        }
    }

    //region matenimiento de Lista Rubro Evaluacion
    private void setRubroEvaluacionProceso(List<RubroEvaluacionUi> rubroEvaluacionUis){
        this.rubroEvaluacionUis.clear();
        this.rubroEvaluacionUis.addAll(rubroEvaluacionUis);
    }

    private void addRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi){
        rubroEvaluacionUis.add(rubroEvaluacionUi);
    }

    private int updateRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi){
        int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
        if(posicion != -1){
            rubroEvaluacionUis.set(posicion,rubroEvaluacionUi);
        }
        return posicion;
    }

    private void deleteRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi){
        int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
        if(posicion != -1){
            rubroEvaluacionUis.remove(posicion);
        }
    }
    //endregion manteniento de Lista Evalacion

    //region mantenimiento de Indicadores
    private void setIndicador(List<IndicadorUi> indicadorUis){
        if(indicadorUis != null){
            this.indicadorUis.clear();
            this.indicadorUis.addAll(indicadorUis);
        }
    }
    //endregion mantenimientos de Indicadores

    //region abstracion de datos
    private void getRubroList(){
        useCaseHandler.execute(
                getRubroList,
                new GetRubroList.RequestValues(sesionAprendizajeId),
                new UseCase.UseCaseCallback<GetRubroList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroList.ResponseValue response) {
                        showRubroList(response.getRubroEvaluacionProcesos());
                        getAlumnoList();
                    }
                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getGetRubroList");
                    }
                }
        );

    }

    private void getSingleRubroList(){
        useCaseHandler.execute(
                getRubroList,
                new GetRubroList.RequestValues(sesionAprendizajeId),
                new UseCase.UseCaseCallback<GetRubroList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroList.ResponseValue response) {
                        showRubroList(response.getRubroEvaluacionProcesos());
                     // mensajeAlumnoContenVacio();
                    }
                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getGetRubroList");
                    }
                }
        );

    }
   /* private void mensajeAlumnoContenVacio(){
        if (disabledEval)return;
        if(view != null){
            if(itemsEvaluacion.size() > 0){
                view.hideFrameGroupsList();
            }else{
                view.showTeamList(idCargaCurso,cursoId,rubroEvaluacionUi.getId(), idCargaAcademica);
            }
        }
    }*/

    private void getAlumnoList(){
        if(rubroEvaluacionUi == null)return;
        rubroEvaluacionUi.setCargaCursosId(idCargaCurso);
        sesionAprendizajeId = rubroEvaluacionUi.getSesionAprendizajeId();
        useCaseHandler.execute(
                getAlumnoList,
                new GetAlumnoGrupoList.RequestValues(sesionAprendizajeId,rubroEvaluacionUi, idEntidad, idGeoreferencia),
                new UseCase.UseCaseCallback<GetAlumnoGrupoList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetAlumnoGrupoList.ResponseValue response) {
                        setGetAlumnoList(response.getAlumnosEvaluacionUis());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getAlumnoList");
                    }
                }
        );
    }

    private void getIndicadorList(){
        useCaseHandler.execute(
                getIndicadorList,
                new GetIndicadorList.RequestValues(sesionAprendizajeId,rubroEvaluacionUi),
                new UseCase.UseCaseCallback<GetIndicadorList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetIndicadorList.ResponseValue response) {
                        //setIndicador(response.getAlumnosEvaluacionUis());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getIndicadorList");
                    }
                });
    }

    private void saveRegistroEvaluacion(GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi){
        useCaseHandler.execute(
                saveGrupoEvaluacion,
                new SaveGrupoEvaluacion.RequestValues(sesionAprendizajeId,rubroEvaluacionUi,grupoEvaluacionUi,alumnosEvaluacionUi),
                new UseCase.UseCaseCallback<SaveGrupoEvaluacion.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveGrupoEvaluacion.ResponseValue response) {
                        Log.d(TAG, "onSuccess " + response);
                        if(response.getSucces()){
                            if(view!=null)view.notifyChangeDataBase();
                        }else{

                        }
                    }

                    @Override
                    public void onError() {

                    }
                }

        );
    }

    private void getNotaAlumnoList(){
        Log.d(TAG,"gruposEvaluacion size:"+gruposEvaluacion.size());
        rubroEvaluacionUi.setCargaCursosId(idCargaCurso);
        useCaseHandler.execute(
                getNotaEvaluacionList,
                new GetNotaEvaluacionList.RequestValues(rubroEvaluacionUi,gruposEvaluacion),
                new UseCase.UseCaseCallback<GetNotaEvaluacionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetNotaEvaluacionList.ResponseValue response) {
                        showAlumnosList(itemsEvaluacion);
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getAlumnoList");
                    }
                }
        );
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onActivityCreated() {
        getRubro();
    }

    @Override
    public void onDestroyView() {
        this.view = null;
    }

    @Override
    public void onDetach() {

    }

    private void getRubro(){
        useCaseHandler.execute(getRubro,
                new GetRubro.RequestValues(rubroEvaluacionProcesoId),
                new UseCase.UseCaseCallback<GetRubro.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubro.ResponseValue response) {
                        rubroEvaluacionUi = response.getRubroEvaluacionUi();
                       if(rubroEvaluacionUi!=null){
                           switch (rubroEvaluacionUi.getTipo()){
                               case NORMAL:
                                   disabledEval = false;
                                   break;
                               case RUBRICA_DETALLE:
                                   disabledEval = true;
                                   if(view!=null)view.showBtnClean(false);
                                   break;
                           }
                       }
                        getAlumnoList();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    //endregion abstracion de datos

    private String setRangoNota(NotaUi valorNotaAsignado){
        StringBuilder rango =  new StringBuilder();

        if(valorNotaAsignado.isIncluidoLSuperior()){
            rango.append("[ ");
        }else {
            rango.append("< ");
        }
        rango.append(String.format("%.1f", valorNotaAsignado.getLimiteSuperior()));
        rango.append(" - ");
        rango.append(String.format("%.1f", valorNotaAsignado.getLimiteInferior()));
        if(valorNotaAsignado.isIncluidoLInferior()){
            rango.append(" ]");
        }else {
            rango.append(" >");
        }

        return  rango.toString();
    }

}
