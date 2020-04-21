package com.consultoraestrategia.ss_crmeducativo.createTeamList.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.local.CreateTeamLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.remote.CreateTeamRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.CreateTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.DeleteTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.CreateTeamAccionListWrapper;
import com.consultoraestrategia.ss_crmeducativo.createTeam.ui.CreateTeamActivity;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.CreateTeamListPresenter;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.CreateTeamListPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.CreateTeamListView;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.local.TeamListLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.remote.TeamListRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.CreateGroup;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.GenerateTeamList;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.GetAutoincrementedId;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.GetGroup;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.TeamAdapter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionGrupoEquipo;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateTeamListActivity extends AppCompatActivity implements CreateTeamListView, TeamAdapter.TeamListener {

    private static final String TAG = CreateTeamListActivity.class.getSimpleName();
    private static final int CREATE_TEAM_REQUEST = 202;
    private static final int UPDATE_TEAM_REQUEST = 212;
    @BindView(R.id.edtListName)
    EditText edtListName;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.txtCaption)
    TextView txtCaption;
    @BindView(R.id.textVacio)
    TextView textVacio;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.msg_actualizar)
    TextView msgActualizar;
    @BindView(R.id.group_estatico)
    ConstraintLayout groupEstatico;
    @BindView(R.id.txt_numero_equipos)
    EditText txtNumeroEquipos;
    @BindView(R.id.group_dinamico)
    ConstraintLayout groupDinamico;
    @BindView(R.id.contenedorconfig)
    ConstraintLayout contenedorconfig;
    MenuItem item;
    @BindView(R.id.textEmptyEstilosAp)
    TextView textEmptyEstilosAp;
    @BindView(R.id.textVacio2)
    TextView textVacio2;

    private TeamAdapter adapter;

    public static final String EXTRA_CARGA_CURSO_ID = "EXTRA_CARGA_CURSO_ID";
    public static final String EXTRA_CURSO_ID = "EXTRA_CURSO_ID";
    public static final String EXTRA_GRUPO_EQUIPO_ID = "EXTRA_GRUPO_EQUIPO_ID";
    public static final String EXTRA_TIPO_CREACION_GRUPO = "EXTRA_TIPO_CREACION_GRUPO";
    public static final String EXTRA_TIPO_ITEM = "EXTRA_TIPO_ITEM";
    public static final String EXTRA_TIPOGRUPO_ID= "EXTRA_TIPO_GRUPO_ID";
    public static final String EXTRA_ENTIDAD_ID= "EXTRA_ENTIDAD_ID";
    public static final String EXTRA_GEOREFERENCIA_ID= "EXTRA_GEOREFERENCIA_ID";
    public static final String EXTRA_PROGRAMA_EDUCATIVO_ID= "EXTRA_PROGRAMA_EDUCATIVO_ID";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;

    public static void startCreateTeamListActivity(Context context,Bundle bundle, String grupoEquipoId, String cursoId, String cargaCursoId, int tipoGrupo, Boolean tipoItem, int tipoCreacionGrupoId, int entidadId, int georeferenciaId, int programaEducativoId) {
        Intent intent = new Intent(context, CreateTeamListActivity.class);
        intent.putExtra(EXTRA_CARGA_CURSO_ID, cargaCursoId);
        intent.putExtra(EXTRA_CURSO_ID, cursoId);
        intent.putExtra(EXTRA_GRUPO_EQUIPO_ID, grupoEquipoId);
        intent.putExtra(EXTRA_TIPO_CREACION_GRUPO, tipoGrupo);
        intent.putExtra(EXTRA_TIPO_ITEM,tipoItem);
        intent.putExtra(EXTRA_TIPOGRUPO_ID, tipoCreacionGrupoId);
        intent.putExtra(EXTRA_ENTIDAD_ID, entidadId);
        intent.putExtra(EXTRA_GEOREFERENCIA_ID, georeferenciaId);
        intent.putExtra(EXTRA_PROGRAMA_EDUCATIVO_ID, programaEducativoId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teamlist);
        ButterKnife.bind(this);
        setupToolbar();
        setupTeamAdapter(new ArrayList<Team>(), this);
        setupPresenter();

        txtNumeroEquipos.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {
                presenter.onCantidadGruposChanged(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FAFAFA"));
        }
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        presenter.onResume();
    }


    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        //presenter.onBackPressed();
        onConfirmationDialog();
    }

    private void onConfirmationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensaje de confirmación")
                .setMessage("¿Seguro que quiere salir?")
                .setCancelable(false)
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        item = menu.findItem(R.id.action_create);
        presenter.onCreateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_create:
                createTeamList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    CreateTeamListPresenter presenter;

    private void setupPresenter() {
        presenter = (CreateTeamListPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            TeamListRepository repository = TeamListRepository.getInstance(
                    new TeamListLocalDataSource(
                            InjectorUtils.provideGrupoDeEquipoDao(),
                            InjectorUtils.provideEquipoDao(),
                            InjectorUtils.provideIntegranteDeEquipoDao(),
                            InjectorUtils.providePersonaDao(),
                            InjectorUtils.provideDimensionObservadaDao()
                    ),
                    new TeamListRemoteDataSource(getApplicationContext()
                    ));

            CreateTeamRepository repositoryTeam = CreateTeamRepository.getInstance(
                    new CreateTeamLocalDataSource(
                            InjectorUtils.provideGrupoDeEquipoDao(),
                            InjectorUtils.provideEquipoDao(),
                            InjectorUtils.provideIntegranteDeEquipoDao(),
                            InjectorUtils.providePersonaDao(),
                            InjectorUtils.provideDimensionObservadaDao()
                    ),
                    new CreateTeamRemoteDataSource());
            presenter = new CreateTeamListPresenterImpl(
                    new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                    new GetAutoincrementedId(repository),
                    new CreateGroup(repository),
                    new GetGroup(repository),
                    new DeleteTeam(repositoryTeam),
                    new CreateTeam(repositoryTeam),
                    getResources(),
                    new GetFechaCreacionGrupoEquipo(RepositoryInjector.getBEDatosEnvioGrupoRepositoryInjector()),
                    new GenerateTeamList(repository)
            );
            presenter.setExtras(getIntent().getExtras());
        }
        setPresenter(presenter);
    }

    @Override
    public void setPresenter(CreateTeamListPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void launchCreateTeamActivity(String cargaCursoId, String grupoEquipoId, int orden, Team team, Group group, int entidadId, int georeferenciaId) {
        Intent intent = CreateTeamActivity.launchCreateTeamActivity(this,getIntent().getExtras(), cargaCursoId, grupoEquipoId, orden, team, group, entidadId, georeferenciaId);
        startActivityForResult(intent, CREATE_TEAM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == CREATE_TEAM_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {

            try {
                CreateTeamAccionListWrapper wrapper = Parcels.unwrap(data.getExtras().getParcelable(CreateTeamActivity.EXTRA_TEAM));
                presenter.onSaveTeam(wrapper.getItem());
            } catch (Exception e) {
                Log.d(TAG, "onActivityResult Error");
                e.printStackTrace();
            }

        } else if (requestCode == UPDATE_TEAM_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            presenter.onSuccesUpdateGrupo();
        }
    }

    @Override
    public void showTeamListName(String name) {
        edtListName.setText(name);
    }

    @Override
    public void showTeams(List<Team> teamList) {
        Log.d(TAG, "showTeams" +teamList.size());
        showListTeam(teamList);
        adapter.setTeams(teamList);
    }

    private void showListTeam(List<Team> teamList){
        if (teamList.size()>0){
            recycler.setVisibility(View.VISIBLE);
            textVacio.setVisibility(View.GONE);
        }else{
            recycler.setVisibility(View.VISIBLE);
            textVacio.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void removeTeam(Team team) {
        Log.d(TAG, "removeTeam");
        adapter.removeTeam(team);
    }

    @Override
    public void updateTeam(Team team) {
        adapter.updateTeam(team);
        showList();
    }

    private void showList() {
        if (adapter.stado())textVacio.setVisibility(View.GONE);
        else textVacio.setVisibility(View.VISIBLE);

    }

    @Override


    public void showTeamRemoveMessage(String message, final Team team) {
        Snackbar.make(toolbar, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.global_message_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onRestoreTeam(team);
                    }
                })
                .show();
    }

    private void setupTeamAdapter(List<Team> teams, Context context) {
        recycler.setLayoutManager(new GridLayoutManager(context, 1));
        adapter = new TeamAdapter(teams, this);
        adapter.setRecyclerView(recycler);
        recycler.setAdapter(adapter);
    }

    @Override
    public int getTeamListSize() {
        return adapter != null ? adapter.getItemCount() : 0;
    }

    @Override
    public List<Team> getTeamList() {
        return adapter.getTeams();
    }

    @Override
    public void sync(int programaEducativoId) {
        CallService.jobServiceExportarTipos(this, TipoExportacion.GRUPO);
        SimpleSyncIntenService.start(this, programaEducativoId);
        CMRE.saveNotifyChangeDataBase(this);
        finish();
    }


    @Override
    public void createTeamList() {
        Log.d(TAG, "createTeamList");
        presenter.createTeamList(edtListName.getText().toString());
    }

    @Override
    public void showMessage(int message) {
        Snackbar.make(toolbar, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void addTeam(Team team) {
        Log.d(TAG, "addTeam");
        adapter.addTeam(team);
        textVacio.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        Log.d(TAG, "onCreateTeamButtonClicked");
        presenter.createTeam();
    }


    @Override
    public void onTeamSelected(Team team) {
        Log.d(TAG, "onTeamSelected: " + team);
        presenter.onTeamSelected(team);
    }

    @Override
    public void onTeamButtonDeleteClick(Team team) {

        presenter.onSwipeTeam(team);
    }

    @Override
    public void showError(int error) {
        Snackbar.make(toolbar, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMsgActualizacion() {
        msgActualizar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMsgActualizacion() {
        msgActualizar.setVisibility(View.GONE);
    }

    @Override
    public void showActividadImportacion(BEVariables beVariables) {
        Intent intent = ImportarActivity.launchImportarActivityIntent(this, TipoImportacion.GRUPO, beVariables);
        startActivityForResult(intent, UPDATE_TEAM_REQUEST);
    }

    @Override
    public void showFabCrearRubro() {
        groupEstatico.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFabCrearRubro() {
        groupEstatico.setVisibility(View.GONE);
    }

    @Override
    public void showImputCrearRubroDinamico() {
        groupDinamico.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImputCrearRubroDinamico() {
        groupDinamico.setVisibility(View.GONE);
    }

    @Override
    public void clearFocus() {
        try {
            txtNumeroEquipos.clearFocus();
            edtListName.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtNumeroEquipos.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edtListName.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showcontenedorconfig() {
        if(item!=null)item.setVisible(true);
        contenedorconfig.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidecontenedorconfig() {
        if(item!=null)item.setVisible(false);
        contenedorconfig.setVisibility(View.GONE);
    }

    @Override
    public void onBackPress() {
        super.onBackPressed();
    }

    @Override
    public void hideDinamicoAndEstatico() {

            groupDinamico.setVisibility(View.GONE);
            groupEstatico.setVisibility(View.GONE);
    }

    @Override
    public void showTextEmptyTeamsEstiloApred(List<Team> teams) {
        if(teams.isEmpty()) textEmptyEstilosAp.setVisibility(View.VISIBLE);
        else textEmptyEstilosAp.setVisibility(View.GONE);
        adapter.setTeams(teams);
    }

    @Override
    public void changeMenuItemCrear() {
        item.setTitle("Guardar");
    }

    @Override
    public void changeMenuItemEditar() {
        item.setTitle("Guardar");
    }

    @Override
    public void showTextEmptyTeamsDimamico(List<Team> teams) {
        if (teams.size()>0){
            recycler.setVisibility(View.VISIBLE);
            textVacio2.setVisibility(View.GONE);
        }else{
            recycler.setVisibility(View.VISIBLE);
            textVacio2.setVisibility(View.VISIBLE);
        }
        adapter.setTeams(teams);
    }

    @OnClick(R.id.msg_actualizar)
    public void onViewClicked() {
        presenter.onBtnActualizarEquipo();
    }


    @OnClick(R.id.fab_dinamico)
    public void onFabDinamicoClicked() {
        presenter.onCantidadGruposChanged(txtNumeroEquipos.getText());
    }

}
