package com.consultoraestrategia.ss_crmeducativo.createTeam.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.createTeam.CreateTeamPresenter;
import com.consultoraestrategia.ss_crmeducativo.createTeam.CreateTeamPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createTeam.CreateTeamView;
import com.consultoraestrategia.ss_crmeducativo.createTeam.adapter.MemberAdapter;
import com.consultoraestrategia.ss_crmeducativo.createTeam.adapter.PersonAdapter;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.local.CreateTeamLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.remote.CreateTeamRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.CreateTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.GetDimension;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.GetPersonList;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.GetTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.CreateTeamAccionListWrapper;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeam.listener.PersonListener;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.infoUsuario.InfoUsuarioFragment;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.consultoraestrategia.ss_crmeducativo.createTeamList.ui.CreateTeamListActivity.EXTRA_CARGA_CURSO_ID;

public class CreateTeamActivity extends AppCompatActivity implements CreateTeamView, PersonListener, BaseFragmentListener {

    private static final String TAG = CreateTeamActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.imgCamera)
    CircleImageView imgCamera;
    @BindView(R.id.edtTeamName)
    EditText edtTeamName;
    @BindView(R.id.recyclerMembers)
    RecyclerView recyclerMembers;
    @BindView(R.id.txtCaption)
    TextView txtCaption;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerUsers)
    RecyclerView recyclerPerson;
    public static final String EXTRA_TEAM = "team";
    public static final String EXTRA_GRUPO_ID = "EXTRA_GRUPO_ID";
    public static final String EXTRA_ORDEN = "EXTRA_ORDEN";
    public static final String EXTRA_EQUIPO_ID = "EXTRA_EQUIPO_ID";
    public static final String EXTRA_ENTIDAD_ID = "EXTRA_ENTIDAD_ID";
    public static final String EXTRA_GEOREFENCIA_ID = "EXTRA_GEOREFERENCIA_ID";
    @BindView(R.id.filtro)
    FloatingActionButton filtro;
    @BindView(R.id.txt_empty_list)
    TextView txtEmptyList;
    private InfoUsuarioFragment infoUsuarioFragment;

    public static Intent launchCreateTeamActivity(Context context, Bundle bundle, String cargaCursoId, String grupoId, int orden, Team team, Group group, int entidadId, int georeferenciaId) {
        Intent intent = new Intent(context, CreateTeamActivity.class);
        intent.putExtra(EXTRA_CARGA_CURSO_ID, cargaCursoId);
        intent.putExtra(EXTRA_GRUPO_ID, grupoId);
        intent.putExtra(EXTRA_ORDEN, orden);
        intent.putExtra(EXTRA_TEAM, Parcels.wrap(new CreateTeamAccionListWrapper(team, group)));
        intent.putExtra(EXTRA_ENTIDAD_ID, entidadId);
        intent.putExtra(EXTRA_GEOREFENCIA_ID, georeferenciaId);
        intent.putExtras(bundle);
        return intent;
    }

    private CreateTeamPresenter presenter;
    private PersonAdapter personAdapter;
    private MemberAdapter memberAdapter;

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        ButterKnife.bind(this);
        setupToolbar();
        setupPersonAdapter();
        setupMemberAdapter();
        setupPresenter();
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
        super.onStart();
        presenter.onStart();
    }

    private void clearFocus() {
        try {
            edtTeamName.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtTeamName.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearFocus();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        MenuItem item = menu.findItem(R.id.action_create);
        if (item != null) item.setTitle(R.string.activity_create_team_create);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            createTeam();
        }
        if (id == android.R.id.home) {
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupPresenter() {
        presenter = (CreateTeamPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            CreateTeamRepository repository = CreateTeamRepository.getInstance(
                    new CreateTeamLocalDataSource(
                            InjectorUtils.provideGrupoDeEquipoDao(),
                            InjectorUtils.provideEquipoDao(),
                            InjectorUtils.provideIntegranteDeEquipoDao(),
                            InjectorUtils.providePersonaDao(),
                            InjectorUtils.provideDimensionObservadaDao()
                    ),
                    new CreateTeamRemoteDataSource()
            );
            presenter = new CreateTeamPresenterImpl(
                    new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                    new GetTeam(repository),
                    new GetPersonList(repository),
                    new CreateTeam(repository),
                    getCacheDir(),
                    getContentResolver(), new GetDimension(repository));
            presenter.setExtras(getIntent().getExtras());
        }
        setPresenter(presenter);
    }


    private void setupPersonAdapter() {
        recyclerPerson.setLayoutManager(new LinearLayoutManager(this));
        personAdapter = new PersonAdapter(new ArrayList<Person>(), this);
        personAdapter.setPersonListener(this);
        recyclerPerson.setAdapter(personAdapter);
        ((SimpleItemAnimator) recyclerPerson.getItemAnimator()).setSupportsChangeAnimations(false);
    }


    private void setupMemberAdapter() {
        recyclerMembers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        memberAdapter = new MemberAdapter(new ArrayList<Person>(), this);
        memberAdapter.setListener(this);
        memberAdapter.setRecyclerView(recyclerMembers);
        recyclerMembers.setAdapter(memberAdapter);
    }

    @Override
    public void setPresenter(CreateTeamPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }


    @OnClick(R.id.imgCamera)
    @Override
    public void startSelectImageActivity() {
        Log.d(TAG, "startSelectImageActivity");
        //startCropImageActivity(null);
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1, 1).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void addPersonList(List<Person> persons) {
        Log.d(TAG, "setCuentaCorrientes");
        personAdapter.addPersonList(persons);
    }
    @Override
    public void filter( DimensionUi dimensionUiseleted) {
        personAdapter.filterEstiloAprendizaje(dimensionUiseleted);
    }

    @Override
    public void showPicture(Uri uri) {
        Glide.with(this)
                .load(uri)
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_menu_camera))
                .into(imgCamera);
    }

    @Override
    public void showName(String name) {
        if (!TextUtils.isEmpty(name)) {
            edtTeamName.setText(name);
        }
    }

    @Override
    public int getMemberCount() {
        return memberAdapter.getItemCount();
    }

    @Override
    public void showMemberRecycler() {
        recyclerMembers.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMemberRecycler() {
        recyclerMembers.setVisibility(View.GONE);
    }

    @Override
    public void addMember(Person person) {
        Log.d(TAG, "addMember");
        memberAdapter.addMember(person);
    }

    @Override
    public void removeMember(Person person) {
        Log.d(TAG, "removeMember");
        memberAdapter.removeMember(person);
    }

    @Override
    public void updatePerson(Person person) {
        Log.d(TAG, "updatePerson");
        personAdapter.updatePerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        personAdapter.deletePersona(person);
    }

    @Override
    public void shoMessage(int message) {
        Snackbar.make(toolbar, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showError(int error) {
        Snackbar.make(toolbar, error, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick({R.id.fab})
    public void onFabFilterClicked() {
        presenter.showDialogEstilosAprendizaje();
    }

    @Override
    public void createTeam() {
        presenter.createTeam(edtTeamName.getText().toString());
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }

    @Override
    public void onPersonSeleteced(Person person) {
        Log.d(TAG, "onCuentaCoSelected");
        presenter.onPersonSelected(person);
    }

    @Override
    public void onPersonUnselected(Person person) {
        Log.d(TAG, "onCuentaCoUnselected");
        presenter.onPersonUnselected(person);
    }

    @Override
    public void onClickInfoPersona(Person person) {
        presenter.onClickInfoPersona(person);
    }

    private AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void showListSingleChooserEstilosAprendizaje(String dialogTitle, final List<Object> items, int positionSelected) {
        if (items.isEmpty()) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int size = items.size();
        final CharSequence[] singleItems = new CharSequence[size];

        for (int i = 0; i < size; i++) {
            singleItems[i] = items.get(i).toString();
        }

        if (positionSelected >= items.size()) {
            positionSelected = -1;
        }

        builder.setTitle(dialogTitle)

                .setSingleChoiceItems(singleItems, positionSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        if (selectedPosition != -1) {
                            Object objectSelected = items.get(selectedPosition);
                            if (presenter != null){
                                presenter.onItemSelectedEstiloAprendizaje(objectSelected, selectedPosition);
                            }
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    @Override
    public void devolverResultado(Team team) {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        try {
            args.putParcelable(EXTRA_TEAM, Parcels.wrap(new CreateTeamAccionListWrapper(team)));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "onActivityResult Error");
        }

        intent.putExtras(args);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.filtro)
    public void onViewClicked() {
        presenter.onClickFiltro();
    }

    @Override
    public void siFiltraUsuarioDeseleccionados() {
        personAdapter.getFilter().filter("Si Filtrar");
    }

    @Override
    public void noFiltraUsuarioDeseleccionados() {
        personAdapter.getFilter().filter("");
    }

    @Override
    public void cambiarColorFiltorDeseleccionado(@ColorRes int color) {
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{ContextCompat.getColor(getBaseContext(), color)});
        filtro.setBackgroundTintList(csl);
    }

    @Override
    public void showinfoUsuario(Person person) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = person.getBitmap();
        byte[] byteArray = null;
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byteArray = stream.toByteArray();
            int tamanio = byteArray.length / 1024;
            Log.d(TAG, "tamanio: " + tamanio);
            if (tamanio > 70) byteArray = null;
        }
        FragmentManager manager = getSupportFragmentManager();
        infoUsuarioFragment = InfoUsuarioFragment.newInstance(byteArray, person.getUrlPicture(), person.getFirstName(), Integer.parseInt(person.getId()), getIntent().getStringExtra(EXTRA_CARGA_CURSO_ID), new CRMBundle(getIntent().getExtras()));
        infoUsuarioFragment.show(manager, InfoUsuarioFragment.class.getSimpleName());
    }

    @Override
    public void hideMsjListEmpty() {
        txtEmptyList.setVisibility(View.GONE);
    }

    @Override
    public void showMsjListEmpty(String text) {
        txtEmptyList.setText(text);
        txtEmptyList.setVisibility(View.VISIBLE);
    }


}
