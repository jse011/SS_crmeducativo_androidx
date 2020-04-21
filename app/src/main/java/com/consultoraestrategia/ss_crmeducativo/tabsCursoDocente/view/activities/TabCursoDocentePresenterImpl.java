package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities;


/**
 * Created by kike on 29/03/2018.
 */

public class TabCursoDocentePresenterImpl {

}

/*public class TabCursoDocentePresenterImpl extends BasePresenterImpl<TabCursoDocenteView> implements TabCursoDocentePresenter {
    public static final String TAG = TabCursoDocentePresenterImpl.class.getSimpleName();
    ArrayList<CompetenciaCheck> tipoCompetenciaArrayList= new ArrayList<>();
    private TipoCreacionGrupoUi tipoCreacionGrupoUi = TipoCreacionGrupoUi.ESTATICO;
    private int GRUPO_DINAMICO=1, GRUPO_ESTATICO=2, GRUPO_UNICO_APRENDIZAJE=4, GRUPO_DIFERENTE_APRENDIZAJE=3;
    private GetPeriodoList getPeriodoList;
    private GetIsAprendizajeColegio getIsAprendizajeColegio;
    PeriodoUi periodoSelected;
    String idCalendarioPeriodo;
    private int idCargaCurso;
    private int idCurso;
    private int parametrodisenioid;
    private int idProgramaEducativo;
    private int idCargaAcademica;
    private String[] csoData;
    private String urlBackgroudResource;
    private String bgColor;
    String color = "";
    List<PeriodoUi>listaPeriodos = new ArrayList<>();
    private ParametroDisenioUi parametroDisenioUi;
    private int idGeoreferenciaId;
    private int idEntidad;
    private boolean filtro;
    private ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor;
    private int silaboId;
    private int grupoAcademicoId;
    private int perdiodoAcademicoId;
    private GetIsTutor getIsTutor;
    private boolean isTutor;
    private boolean isAprendizajeColegio;
    private int anioAcademicoId;
*//*
    public TabCursoDocentePresenterImpl(UseCaseHandler handler, Resources res, GetPeriodoList getPeriodoList , ChangeDataBaseDocenteMentor changeDataBaseDocenteMentor, GetIsTutor isTutor, GetIsAprendizajeColegio getIsAprendizajeColegio) {
        super(handler, res);
        this.getPeriodoList=getPeriodoList;
        this.changeDataBaseDocenteMentor = changeDataBaseDocenteMentor;
        this.getIsTutor = isTutor;
        this.getIsAprendizajeColegio = getIsAprendizajeColegio;
    }

    @Override
    public void onFabClicked(int pagePosition) {
        Log.d(TAG, "pagePosition: " + pagePosition);

        if (fragmentClassVisible == null) {
            fragmentClassVisible = RubricaBidFragment.class;
        }
        //cms

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

    private void onComportamientoFabClicked() {
        if (view != null)view.onComportamientoFabClicked();
    }

    @Override
    public void onFabClickedGrupos() {
        if(filtro)filtro=false;
        else {filtro=true;}
        if(view!=null) view.onFiltroGrupos(filtro);
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
        if (view != null) view.onGrupoFragmentFabClicked("Registro Lista de Equipos", TipoCreacionGrupoUi.values(), tipoCreacionGrupoUi, isAprendizajeColegio);
    }



    private Class<? extends Fragment> fragmentClassVisible;

    @Override
    public void onPageChanged(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == null) return;
        fragmentClassVisible = fragmentClass;

        if(periodoSelected!=null&&periodoSelected.isVigente()){
            if(view!=null)view.hideMsgCalendarioPeriodo();
        }else {
            if(view!=null)view.showMsgCalendarioPeriodo();
        }

        if (fragmentClass.equals(RubricaBidFragment.class)) {
            showFab(R.drawable.ic_add_white_24dp);
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            //comprobarCalendarioPeriodo(periodoSelected); cometado pq entorpece la animacion del viewpager
            return;
        }

        if (fragmentClass.equals(EvaluacionCompetenciasFragment.class)) {
            showFab(R.drawable.ic_filter_list_white_24dp);
            showPeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            if(view!=null)view.hideMsgCalendarioPeriodo();
            return;
        }


        if (fragmentClass.equals(RubroResultadoSilaboFragment.class)) {
            showFab(R.drawable.ic_filter_list_white_24dp);
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            //comprobarCalendarioPeriodo(periodoSelected);

            return;
        }


        if (fragmentClass.equals(FragmentUnidades.class)) {
            hideFab();
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            //comprobarCalendarioPeriodo(periodoSelected);
            return;
        }

        if (fragmentClass.equals(ListaGrupoFragment.class)) {
            showFab(R.drawable.ic_add_white_24dp);
            showFabGrupos();
            hideListPeriodos();
            if(view!=null)view.hideMsgCalendarioPeriodo();
            return;
        }


        if (fragmentClass.equals(FragmentTareas.class)) {
            hideFab();
            showBtnActualziar();
            hidePeriodoFinal();
            showPeriodosList();
            hideFabGrupos();
            //comprobarCalendarioPeriodo(periodoSelected);

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
            //comprobarCalendarioPeriodo(periodoSelected);
            if(view!=null)view.contraerToolbar();
            return;
        }
        if (fragmentClass.equals(ComportamientoFragment.class)) {
            showFab(R.drawable.ic_add_white_24dp);
            hideFabGrupos();
            hidePeriodoFinal();
            showPeriodosList();
            //comprobarCalendarioPeriodo(periodoSelected);
            return;
        }
    }



    @Override
    public void onBtnSuccesItemFilter(FiltradoUi filtradoUi, Bundle args) {
        args.putInt("FiltradoId", filtradoUi.getFiltradoId());
        if (view != null) {
            view.onEvaluacionFragmentFabClicked(args);
        }
    }

    @Override
    public void onBtnSuccesItemFilterRubro(ArrayList<CompetenciaCheck> tipoCompetencia) {
       this.tipoCompetenciaArrayList=tipoCompetencia;
        if (view != null) {
            view.onEvaluacionRubroSilaboFragmentFabClicked(tipoCompetencia);
        }
    }



    private void getPeriodList(final UseCase.UseCaseCallback<GetPeriodoList.ResponseValue> callback ) {

        int idCargaCurso = extras.getInt("idCargaCurso", 0);
        int idCurso = extras.getInt("cursoId", 0);
        final int parametrodisenioid = extras.getInt("parametrodisenioid", 0);
         Log.d(TAG, "idCargaCurso : " + idCargaCurso + "idCurso "+ idCurso);
        getPeriodoList.execute(
                new GetPeriodoList.RequestValues(idCargaCurso, idCurso, parametrodisenioid, anioAcademicoId),
                new UseCaseSincrono.Callback<GetPeriodoList.ResponseValue>() {
            @Override
            public void onResponse(boolean success, GetPeriodoList.ResponseValue response) {
                listaPeriodos.clear();
                int count = 0;
                PeriodoUi primaryPeriodo = null;
                for (PeriodoUi periodo : response.getPeriodoUiList()) {
                    if(count==0)primaryPeriodo = periodo;

                    // idCalendarioPeriodo = periodo.getIdCalendarioPeriodo();
                    if (periodo.isVigente()) {
                        periodoSelected=periodo;
                        idCalendarioPeriodo = periodo.getIdCalendarioPeriodo();
                        parametroDisenioUi=periodo.getParametroDisenioUi();
//
                        break;
                    }
                    count++;
                }

                if(periodoSelected==null&&primaryPeriodo!=null){
                    primaryPeriodo.setStatus(true);
                    periodoSelected=primaryPeriodo;
                    idCalendarioPeriodo = primaryPeriodo.getIdCalendarioPeriodo();
                    parametroDisenioUi=primaryPeriodo.getParametroDisenioUi();
                }

                if (view != null) {
                    if(parametroDisenioUi!=null)color = parametroDisenioUi.getColor3();
                    listaPeriodos.addAll(response.getPeriodoUiList());
                    view.showPeriodoList(response.getPeriodoUiList(),color);
                    if(parametroDisenioUi!=null)view.changeColorToolbar(parametroDisenioUi.getColor1());
                    if(parametroDisenioUi!=null)view.changeColorFloatButon(parametroDisenioUi.getColor2());

                    if(periodoSelected!=null){
                        if(periodoSelected.isVigente()){
                            view.showItemMenuUpdateAlumno();
                        }else {
                            view.hideItemMenuUpdateAlumno();
                        }
                    }

                }
                hideProgress();

                if(periodoSelected!=null)comprobarCalendarioPeriodo(periodoSelected);

                callback.onSuccess(response);
            }
        });

    }

    private void onResumenFramentManger(){
        Log.d(TAG,"onResumenFramentManger");
        if (fragmentClassVisible == null) {
            if(view!=null)view.onResumenFragmentRubrica(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo,periodoSelected.isVigente());
            return;
        }

        if (fragmentClassVisible.equals(RubricaBidFragment.class)) {
            if(view!=null)view.onResumenFragmentRubrica(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo,periodoSelected.isVigente());
            return;
        }

        if (fragmentClassVisible.equals(EvaluacionCompetenciasFragment.class)) {
            if(view!=null)view.onResumenFragmentResultado(idCalendarioPeriodo, periodoSelected.isVigente());
            return;
        }

        if (fragmentClassVisible.equals(ListaGrupoFragment.class)) {
            if(view!=null)view.onResumenFragmentGrupos();
            return;
        }

        if (fragmentClassVisible.equals(FragmentUnidades.class)) {
            if(view!=null) view.onResumenFragmentUnidades(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null) view.onResumenFragmentRubrica(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo,periodoSelected.isVigente());
            return;
        }

        if (fragmentClassVisible.equals(FragmentTareas.class)) {
            if(view!=null) view.onResumenFragmentTareas(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null)  view.onResumenFragmentRubrica(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo,periodoSelected.isVigente());
            return;
        }

        if (fragmentClassVisible.equals(RubroResultadoSilaboFragment.class)) {
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo,periodoSelected.isVigente());
            return;
        }


        if (fragmentClassVisible.equals(AsistenciaCursoFragment.class)) {
            if(view!=null)view.onResumenAsistenciaCursoFragment(idCalendarioPeriodo, periodoSelected.isVigente());
            return;
        }
        if (fragmentClassVisible.equals(ComportamientoFragment.class)) {
            if(view!=null)view.onResumenFragmentComportamiento(idCalendarioPeriodo, periodoSelected.isVigente());
            return;
        }
    }

    @Override
    public void actualizarRubroTipoAnclaFragmentRegistro(RubroProcesoUi rubroProcesoUi) {
        if (view != null) view.onActualizarEvaluacionFrament(rubroProcesoUi);
    }

    /*Este metodo llega desde RubricaFragment,Cuando se agrega*//*
    @Override
    public void onActualizarItemRubrica() {
        if (view != null) view.onActualizarRegistroFrament();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if(data == null)return;
            Bundle bundle = data.getExtras();
            int tipo = bundle.getInt(RubricasAbstractFragment.TIPO_RUBRICA);
            Log.d(TAG, "tipo :" + tipo);
            switch (tipo) {
                case RubricasAbstractFragment.AGREGAR_RUBRICAS_BIDIMENSIONAL:
                    // view.onActualizarRubricaFragment(data);
                    if (view != null) view.onActualizarRegistroFrament();
                    break;
                default:
                    Log.d(TAG, "default");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClickUpdate() {
        BEVariables beVariables = new BEVariables();
        if (fragmentClassVisible == null) {
            if(view!=null) view.showActualizarRubrica(beVariables);
            return;
        }

        if (fragmentClassVisible.equals(RubricaBidFragment.class)) {
            if(view!=null)view.showActualizarRubrica(beVariables);
            return;
        }


        if (fragmentClassVisible.equals(ListaGrupoFragment.class)) {
            if(view!=null)view.showActualizarGrupos(beVariables);
            return;
        }

        if (fragmentClassVisible.equals(RubroResultadoSilaboFragment.class)) {
            if(view!=null) view.showActualizarRubros(beVariables);
            return;
        }

    }

    @Override
    public void onChangeRubroUpdate() {
        if (view != null) view.onActualizarRegistroFrament();
    }

    @Override
    public void onClickRefrescar() {
        boolean isInternet = false;
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }
        if(view!=null)view.onCallSinckService(idProgramaEducativo);
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
        if(view==null)return;
        if(o instanceof TipoCreacionGrupoUi){
            TipoCreacionGrupoUi tipoCreacionGrupoUi = (TipoCreacionGrupoUi) o;
            this.tipoCreacionGrupoUi = tipoCreacionGrupoUi;
            switch (tipoCreacionGrupoUi){
                case DINAMICO:
                    tipoCreacionGrupoUi.setId(449);
                    tipoCreacionGrupoUi.setValor(GRUPO_DINAMICO);
                    break;
                case ESTATICO:
                    tipoCreacionGrupoUi.setId(446);
                    tipoCreacionGrupoUi.setValor(GRUPO_ESTATICO);
                    break;
                case APRENDIZAJE_DIFERENTE:
                    tipoCreacionGrupoUi.setId(0);
                    tipoCreacionGrupoUi.setValor(GRUPO_DIFERENTE_APRENDIZAJE);
                    break;
                case APRENDIZAJE_UNICO:
                    tipoCreacionGrupoUi.setId(0);
                    tipoCreacionGrupoUi.setValor(GRUPO_UNICO_APRENDIZAJE);
                    break;
            }
            view.showCrearGrupo(tipoCreacionGrupoUi);
        }
    }

    @Override
    public void onChangeFull(boolean success) {

        getPeriodoList.execute(
                new GetPeriodoList.RequestValues(idCargaCurso, idCurso, parametrodisenioid, anioAcademicoId),
                new UseCaseSincrono.Callback<GetPeriodoList.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, GetPeriodoList.ResponseValue response) {
                        for (PeriodoUi newPeriodoUi : response.getPeriodoUiList()){
                            for (PeriodoUi periodoUi : listaPeriodos){
                                if(newPeriodoUi.getIdPeriodo().equals(periodoUi.getIdPeriodo())){
                                    periodoUi.setVigente(newPeriodoUi.isVigente());
                                    periodoUi.setEditar(newPeriodoUi.isEditar());
                                    periodoUi.setEstado(newPeriodoUi.getEstado());
                                    periodoUi.setParametroDisenioUi(newPeriodoUi.getParametroDisenioUi());
                                }
                            }
                        }

                        if(c)comprobarCalendarioPeriodo();
                    }
                });
        if(fragmentClassVisible!=null){
            if (fragmentClassVisible.equals(RubroResultadoSilaboFragment.class))comprobarCPRubro(periodoUi.isEditar(),periodoUi.isVigente());
            else if (fragmentClassVisible.equals(FragmentTareas.class))comprobarCPTareas(periodoUi.isVigente());
            else if (fragmentClassVisible.equals(FragmentUnidades.class))comprobarCPSesiones(periodoUi.isEditar(),periodoUi.isVigente());
        }


    }


    @Override
    public void delteRubrica() {
        Log.d(TAG, "delteRubrica");
        if(view!=null) view.onActualizarRegistroFrament();
        if(view!=null) view.onResumenFragmentRegistro(idCalendarioPeriodo, periodoSelected.isVigente());
        if(view!=null) view.onResumenFragmentTareas(idCalendarioPeriodo, periodoSelected.isVigente());
    }
    @Override
    public void onDeleteRubro() {
        if(view!=null) view.onResumenFragmentTareas(idCalendarioPeriodo, periodoSelected.isVigente());
    }

    @Override
    public void onFinishSynck() {
        changeDataBaseDocenteMentor();
    }

    @Override
    public void onCreateOptionsMenu() {
        changeDataBaseDocenteMentor();
        habilitarContenidoTutor();
    }

    @Override
    public void onClickUpadteAlumnos() {
        if(view!=null)view.showProgressDialogUpdateAlumnos();
        int calendarioPerioId = TextUtils.isEmpty(idCalendarioPeriodo)?0:Integer.valueOf(idCalendarioPeriodo);
        if(view!=null)view.showSincContratoAlumnos(silaboId, grupoAcademicoId, perdiodoAcademicoId, calendarioPerioId, idProgramaEducativo);
    }

    @Override
    public void onClickUpadteFotoAlumnos() {
        if(view!=null)view.showCambiarFotoAlumnoActivity(idCargaCurso);
    }

    @Override
    public void PostDelaychangePeriodo() {
        if(periodoSelected!=null)_changePeriodo(periodoSelected);
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

    private void onchangeResultados(boolean success) {
        if(success)  if(view!=null)view.onResumenFragmentResultado(idCalendarioPeriodo, periodoSelected.isVigente());
    }

    private void onchangeComportamiento(boolean success) {
        if(success)  if(view!=null)view.onResumenFragmentComportamiento(idCalendarioPeriodo, periodoSelected.isVigente());
    }

    private void onChangeAsistenciaCurso(boolean success) {
        if(success) {
            if(view!=null) view.onResumenAsistenciaCursoFragment(idCalendarioPeriodo, periodoSelected.isVigente());
        }
    }

    private void onChangeGrupo(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentGrupos();
        }
    }

    private void onChangeTarea(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentTareas(idCalendarioPeriodo, periodoSelected.isVigente());
        }
    }

    private void onChangeCalendarioPeriodo(boolean success) {
        onChangeSesiones(success);
        onChangeRubro(success);
        onChangeTarea(success);


    }

    private void onChangeSesiones(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentUnidades(idCalendarioPeriodo, periodoSelected.isVigente());
        }
    }

    private void onChangeRubro(boolean success) {
        if(success) {
            if(view!=null)view.onResumenFragmentRubrica(idCalendarioPeriodo, periodoSelected.isVigente());
            if(view!=null)view.onResumenFragmentRegistro(idCalendarioPeriodo, periodoSelected.isVigente());
        }
    }


    private void showFab(@DrawableRes int drawable) {
        if (view != null) view.changeFabBg(drawable);
    }


    private void showFabGrupos(){
        if (view != null) view.showFabGrupos();
    }
    private void hideFabGrupos(){
        if (view != null) view.hideFabGrupos();
    }

    private void showBtnActualziar() {
        if (view != null) view.showBtnActualizar();
    }
    private void hideBtnActualziar() {
        if (view != null) view.hideBtnActualizar();
    }

    private void hideFab() {
        if (view != null) view.hideFab();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showProgress();
        setGetIsAprendizajeColegio();
        int length = csoData.length;
        if (length >= 1) {
            String title = csoData[0];
            String subtitle = csoData[1];
            showTitle(title);
            showSubtitle(subtitle);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(idCalendarioPeriodo != null){
            onResumenFramentManger();
        }else {
            getPeriodList(new UseCase.UseCaseCallback<GetPeriodoList.ResponseValue>() {
                @Override
                public void onSuccess(GetPeriodoList.ResponseValue response) {
                    showFragments();
                }

                @Override
                public void onError() {
                    showFinalMessage("Error en los calendarios Periodos");
                }
            });
        }
        setGetIsAprendizajeColegio();
    }

    private void habilitarContenidoTutor() {
        isTutor = getIsTutor.execute(idCargaAcademica);
        if(isTutor){
            if(view!=null)view.showItemMenuUpdateFotoAlumno();
        }else {
            if(view!=null)view.hideItemMenuUpdateFotoAlumno();
        }
    }


    private void setGetIsAprendizajeColegio(){
        this.isAprendizajeColegio = getIsAprendizajeColegio.execute(idEntidad, idGeoreferenciaId);
    }

    private void bindExtrasToUi() {
        if (extras == null) return;
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
    }
    private void showFragments() {
       Log.d(TAG, "showFragments 2" );
        if (periodoSelected!=null && view != null) view.showFragments(idCargaCurso,  idCurso,idProgramaEducativo,parametrodisenioid, periodoSelected, idCargaAcademica);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        bindExtrasToUi();
    }

    private void showTitle(String title) {
        if (view != null && !TextUtils.isEmpty(title)) view.showTitle(title);
    }

    private void showSubtitle(String subtitle) {
        if (view != null && !TextUtils.isEmpty(subtitle)) view.showSubtitle(subtitle);
    }

    private void showAppbarBackground(String bg, String bgColor) {
        if (view != null) view.showAppbarBackground(bg, bgColor);
    }


    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        Log.d(TAG, "onPeriodoCargaCursoSelected : " + periodoUi.getIdPeriodo());
        if (periodoSelected!=null && periodoSelected.equals(periodoUi)) {
            return;
        }
        changePeriodo(periodoUi);

        if(periodoSelected!=null){
            if(periodoSelected.isVigente()){
                view.showItemMenuUpdateAlumno();
            }else {
                view.hideItemMenuUpdateAlumno();
            }
        }
    }

    private void changePeriodo(PeriodoUi periodoUi) {
        if (view != null) {
            if(periodoSelected!=null)periodoSelected.setStatus(false); // LLegaba con true y cambiamos false
            periodoUi.setStatus(true);
            view.changePeriodo(periodoSelected, periodoUi);
            this.periodoSelected = periodoUi;
            Log.d(TAG, "isVIgente : " + periodoSelected.isVigente());
        }

    }


    private void _changePeriodo(PeriodoUi periodoUi) {
        if (view != null) {
            if(fragmentClassVisible!=EvaluacionCompetenciasFragment.class) comprobarCalendarioPeriodo(periodoSelected);
            idCalendarioPeriodo= periodoUi.getIdCalendarioPeriodo();
            view.onResumenFragmentRubrica(idCalendarioPeriodo, periodoSelected.isVigente());
            view.onResumenFragmentRegistro(idCalendarioPeriodo,  periodoSelected.isVigente());
            view.onResumenFragmentResultado(idCalendarioPeriodo, periodoSelected.isVigente());
            view.onResumenFragmentUnidades(idCalendarioPeriodo, periodoSelected.isVigente());
            view.onResumenFragmentTareas(idCalendarioPeriodo, periodoSelected.isVigente());
            view.onResumenAsistenciaCursoFragment(idCalendarioPeriodo, periodoSelected.isVigente());
            view.onResumenFragmentComportamiento(idCalendarioPeriodo, periodoSelected.isVigente());
        }

    }

    private void comprobarCalendarioPeriodo(PeriodoUi periodoUi){
        if(periodoUi==null)return;

        if(periodoUi.isVigente()){
            if(view!=null)view.hideMsgCalendarioPeriodo();
        }else {
            if(view!=null)view.showMsgCalendarioPeriodo();
        }

        if(periodoUi.isEditar()){
            if(fragmentClassVisible==null)showFab(R.drawable.ic_add_white_24dp);
        }else {
            if(fragmentClassVisible==null)hideFab();
        }
    }

    private void comprobarCPSesiones(boolean editar, boolean vigente) {
        view.comprobarCPSesiones(editar, vigente);
    }

    private void comprobarCPTareas(boolean vigente) {
        view.comprobrarCPTarea(vigente);
    }

    private void comprobarCPRubro(boolean editar, boolean anclar) {
        view.comprobarCPRubro(editar, anclar);
    }

    private void showPeriodoFinal() {
        PeriodoUi periodoUi = new PeriodoUi(String.valueOf(0), "Final", "", false,parametroDisenioUi );
        listaPeriodos.remove(periodoUi);
        listaPeriodos.add(periodoUi);
        if (view != null)  view.showPeriodoList(listaPeriodos, color);

    }
    private void hidePeriodoFinal() {
        PeriodoUi periodoUi = new PeriodoUi(String.valueOf(0), "Final", "", false, parametroDisenioUi);
        listaPeriodos.remove(periodoUi);
        if (view != null)view.showPeriodoList(listaPeriodos, color);
    }

    private void hideListPeriodos() {if (view != null)view.hidePeriodosLIst();}
    private void showPeriodosList() {if (view != null)view.showPeriodosLIst(); }

}*/
