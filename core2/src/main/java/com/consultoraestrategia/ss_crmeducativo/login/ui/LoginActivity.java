package com.consultoraestrategia.ss_crmeducativo.login.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.transition.TransitionManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.login.LoginPresenter;
import com.consultoraestrategia.ss_crmeducativo.login.LoginView;
import com.consultoraestrategia.ss_crmeducativo.login.adapter.PersonaAdapter;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login.listener.PersonaListener;
import com.consultoraestrategia.ss_crmeducativo.login.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.login.preferent.LoginPreferentRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by irvinmarin on 20/10/2017.
 */

public abstract class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView, TextView.OnEditorActionListener, PersonaListener, View.OnClickListener {

    Toolbar toolbar;
    AppBarLayout appBarLayout;
    ImageView imageLogoProgressPage;
    ProgressBar progressbar;
    TextInputEditText edittextUsername;
    TextInputEditText edittextPasswordUser;
    TextInputLayout tilUsername;
    TextInputEditText edittextPassword;
    ConstraintLayout contendoPrincipal;
    ProgressBar pgrProgress;
    TextView textProgress;
    View viewPageProgress;
    Button btnSiguienteUser;
    TextView txtNombreUsuario;
    TextInputLayout textInputLayout5;
    Button btnSiguientePassword;
    Button btnSiguienteCorreo;
    ImageView btnClosePassword;
    CircleImageView imgUsuarioPassword;
    TextView txtUsuarioPassword;
    CardView groupPageUser;
    View contenloginPassword;
    View contenloginUser;
    View contenLoginListaUsuario;
    View contentLoginCorreo;
    View contentLoginDni;
    //View contentLoginPasswordUser;
    TextInputEditText editttextDni;
    Button btnSiguienteDni;
    RecyclerView rcContactos;
    TextView btnQuitarUsuario;
    ImageView btnAtrasLstUsu;
    TextView txtrecPassw;
    TextInputEditText edittextCorreo;
    ImageView btnAtrasCorreo;
    ImageView btnAtrasDni;
    ImageView btnBtnAtrasLstUsuPass;
    Button btn_reiniciar;
    Button btn_cancelar;

    protected ApiRetrofit apiRetrofit;
    private LoginPreferentRepository loginPreferentRepository;
    private PersonaAdapter usuarioAdapter;
    private ImageView imgInstitucion;
    private View groupBienvenidad;
    private ImageView imageBienvenida;
    private TextView textBienvenida;
    private TextView nombreUser;
    private ImageView imgBienvenidaInstitucion;
    private CircleImageView imgBienvenidaUserer;
    private Dialog dialogTimeOutConnection;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login_mvp);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        imageLogoProgressPage = findViewById(R.id.image_logo_progress_page);
        progressbar = findViewById(R.id.progressbar);
        edittextUsername = findViewById(R.id.edittext_username);
        tilUsername = findViewById(R.id.til_username);
        edittextPassword = findViewById(R.id.edittext_password);
        contendoPrincipal = findViewById(R.id.contendoPrincipal);
        pgrProgress= findViewById(R.id.pgr_progresshorizontal);
        textProgress = findViewById(R.id.text_progress);
        viewPageProgress = findViewById(R.id.group_page_progress);
        btnSiguienteUser = findViewById(R.id.btn_siguiente_user);
        btnSiguienteUser.setOnClickListener(this);
        txtNombreUsuario  = findViewById(R.id.txt_nombre_usuario);
        textInputLayout5  = findViewById(R.id.textInputLayout5);
        btnSiguientePassword  = findViewById(R.id.btn_siguiente_password);
        btnSiguientePassword.setOnClickListener(this);
        btnClosePassword = findViewById(R.id.btn_close_password);
        btnClosePassword.setOnClickListener(this);
        imgUsuarioPassword = findViewById(R.id.img_usuario_password);
        txtUsuarioPassword  = findViewById(R.id.txt_usuario_password);
        groupPageUser = findViewById(R.id.group_page_user);
        contenloginPassword  = findViewById(R.id.conten_login_mvp_password);
        contenloginUser = findViewById(R.id.conten_login_mvp_user);
        contentLoginCorreo = findViewById(R.id.conten_login_mvp_correo);
        contenLoginListaUsuario = findViewById(R.id.conten_login_lista_usuario);
        rcContactos = findViewById(R.id.rc_contactos);
        btnQuitarUsuario = findViewById(R.id.btn_quitar_usuario);
        btnQuitarUsuario.setOnClickListener(this);
        btnAtrasLstUsu = findViewById(R.id.btn_atras_lst_usu);
        btnAtrasLstUsu.setOnClickListener(this);
        txtrecPassw = findViewById(R.id.txtrecPassw);
        txtrecPassw.setOnClickListener(this);
        edittextCorreo = findViewById(R.id.edittext_correo);
        btnSiguienteCorreo = findViewById(R.id.btn_siguiente_correo);
        btnSiguienteCorreo.setOnClickListener(this);
        btnSiguienteDni = findViewById(R.id.btn_siguiente_dni);
        btnSiguienteDni.setOnClickListener(this);
        editttextDni = findViewById(R.id.edittext_dni);
        contentLoginDni = findViewById(R.id.conten_login_mvp_dni);
        btnAtrasDni = findViewById(R.id.btn_atras_dni);
        btnAtrasDni.setOnClickListener(this);
        btnAtrasCorreo = findViewById(R.id.btn_atras_correo);
        btnAtrasCorreo.setOnClickListener(this);
        edittextPasswordUser = findViewById(R.id.edittext_password_user);
        //contentLoginPasswordUser = findViewById(R.id.conten_login_mvp_password_user);
        btnBtnAtrasLstUsuPass = findViewById(R.id.btn_atras_lst_usu_pass);
        btnBtnAtrasLstUsuPass.setOnClickListener(this);
        imgInstitucion = findViewById(R.id.img_institucion);
        groupBienvenidad = findViewById(R.id.group_bienvenidad);
        imageBienvenida = findViewById(R.id.image_bienvenida);
        textBienvenida = findViewById(R.id.text_bienvenida);
        nombreUser = findViewById(R.id.nombre_user);
        imgBienvenidaInstitucion = findViewById(R.id.img_bienvenida_institucion);
        imgBienvenidaUserer = findViewById(R.id.img_bienvenida_userer);
        //viewPagerPageProgress = findViewById(R.id.view_pager_page_progress);
        btn_reiniciar = findViewById(R.id.btn_reiniciar);
        //pageIndicatorView = findViewById(R.id.pageIndicatorView);
        btn_reiniciar.setOnClickListener(this);
        btn_cancelar = findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(this);
        setSupportActionBar(toolbar);
        //setupLogoAnim();
        setupEditorListener();
        setupPreferenciaRepository();
        setupAdapter();

    }

    private void setupAdapter() {
        rcContactos.setLayoutManager(new LinearLayoutManager(this));
        this.usuarioAdapter = new PersonaAdapter(new ArrayList<PersonaUi>(), this);
        rcContactos.setAdapter(this.usuarioAdapter);
    }

    private void setupPreferenciaRepository() {
        this.loginPreferentRepository = new LoginPreferentRepositoryImpl(this);
    }

    private void setupEditorListener() {
        edittextPassword.setOnEditorActionListener(this);
        edittextUsername.setOnEditorActionListener(this);
        edittextPasswordUser.setOnEditorActionListener(this);
        edittextCorreo.setOnEditorActionListener(this);
        editttextDni.setOnEditorActionListener(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected LoginView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected String getTag() {
        return LoginActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }


    @Override
    protected ViewGroup getRootLayout() {
        return contendoPrincipal;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressbar;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_servers) {
            presenter.onServerMenuClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void disableInputs() {
        btnSiguientePassword.setEnabled(false);
        edittextUsername.setEnabled(false);
        edittextPassword.setEnabled(false);
        edittextCorreo.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void enableInputs() {
        edittextUsername.setEnabled(true);
        edittextPassword.setEnabled(true);
        btnSiguientePassword.setEnabled(true);
        edittextCorreo.setEnabled(true);
    }


    @Override
    public void clearImputs() {
        edittextUsername.setText("");
        edittextPassword.setText("");
        edittextCorreo.setText("");
    }

    @Override
    public void clearFocusPassword() {
        edittextPasswordUser.setText("");
        edittextCorreo.setText("");
        editttextDni.setText("");
        edittextPassword.setText("");
    }

    @Override
    public void clearFocusCorreo() {
        edittextCorreo.setText("");
        editttextDni.setText("");
    }

    @Override
    public void clearFocusDni() {
        editttextDni.setText("");
    }



    @Override
    public void showServerListDialog() {
    }

    @Override
    public void showEdtDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_edt, null);
        final EditText editText = dialogView.findViewById(R.id.edt_content);
        builder
                .setView(dialogView)
                .setTitle(R.string.login_dialog_add_server)
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (presenter != null) {
                            presenter.onCustomUrlOk(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showProgressPage() {

        viewPageProgress.setVisibility(View.VISIBLE);
        viewPageProgress.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up));
    }

    @Override
    public void showLoginPage() {
        groupPageUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoginCorreo() {
        contentLoginCorreo.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoginDNI() {
        contentLoginDni.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginDNI() {
        contentLoginDni.setVisibility(View.GONE);
    }

    /*@Override
    public void showLoginPasswordUser() {
        contentLoginPasswordUser.setVisibility(View.VISIBLE);
    }*/

    /*@Override
    public void hideLoginPasswordUser() {
        contentLoginPasswordUser.setVisibility(View.GONE);
    }*/

    @Override
    public void hideLoginCorreo() {
        contentLoginCorreo.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressPage() {
        viewPageProgress.setVisibility(View.GONE);
    }

    @Override
    public void hideLoginPage() {
        groupPageUser.setVisibility(View.GONE);
    }

    @Override
    public void showListUsuario(List<PersonaUi> personaUiList, boolean elimiarUsuario) {
        usuarioAdapter.setUsuarios(personaUiList, elimiarUsuario);
    }

    @Override
    public void removeUsuario(PersonaUi personaUi) {
        usuarioAdapter.removerUsuario(personaUi);
    }

    @Override
    public void updateProgress(int progress) {
        pgrProgress.setProgress(progress);
    }

    @Override
    public void updateProgressText(String text) {
        TransitionManager.beginDelayedTransition(contendoPrincipal);
        textProgress.setText(text);
    }

    @Override
    public void setupLogoAnim() {
        imageLogoProgressPage.setVisibility(View.VISIBLE);
        Glide.with(this)
                    .asGif()
                    .load(Uri.parse("file:///android_asset/DownloadData2-min.gif"))
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_error_outline_black))
                    .into(imageLogoProgressPage);
        //viewPagerPageProgress.setVisibility(View.GONE);
    }

    @Override
    public void ShowLoginPagePassword() {
        contenloginPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginPagePassword() {
        contenloginPassword.setVisibility(View.GONE);
    }

    @Override
    public void showLoginPageUser() {
        contenloginUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginPageUser() {
        contenloginUser.setVisibility(View.GONE);
    }

    @Override
    public void showLoginPageListUser() {
        contenLoginListaUsuario.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginPageListUser() {
        contenLoginListaUsuario.setVisibility(View.GONE);
    }

    @Override
    public void setUrlImgenPassword(String url) {
       try {
           Glide.with(this)
                   .load(url)
                   .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                   .into(imgUsuarioPassword);
       }
       catch (Exception e){e.getStackTrace();}

    }


    @Override
    public void setNombrePersona(String nombre) {
        txtNombreUsuario.setText(nombre);
    }
    @Override
    public void setUserName(String usuario) {
        txtUsuarioPassword.setText(usuario);
    }


    @Override
    public void onErroUsuario(String error) {
        edittextUsername.requestFocus();
        edittextUsername.setSelected(true);
        edittextUsername.setError(error);
    }

    @Override
    public void onErrorPassword(String error) {
        edittextPassword.requestFocus();
        edittextPassword.setSelected(true);
        edittextPassword.setError(error);
    }

    @Override
    public void onErrorCorreo(String error) {
        edittextCorreo.requestFocus();
        edittextCorreo.setSelected(true);
        edittextCorreo.setError(error);
    }

    @Override
    public void onErrorDni(String error) {
        editttextDni.requestFocus();
        editttextDni.setSelected(true);
        editttextDni.setError(error);
    }

    @Override
    public void onInvalitedCorreo(String error) {
        edittextCorreo.requestFocus();
        edittextCorreo.setSelected(true);
        edittextCorreo.setError(error);
    }

    @Override
    public void onInvalitedPassword(String error) {
        edittextPassword.requestFocus();
        edittextPassword.setSelected(true);
        edittextPassword.setError(error);
    }

    @Override
    public void onCredencialesIncorrectos(String error) {
        edittextUsername.requestFocus();
        edittextUsername.setSelected(true);
        edittextUsername.setError(error);
    }

    @Override
    public void onInvalitedDni(String error) {
        editttextDni.requestFocus();
        editttextDni.setSelected(true);
        editttextDni.setError(error);
    }

    @Override
    public void onCancelarTodosLlamadasHTTPS() {
        apiRetrofit.cancelAll();
    }

    @Override
    public void onSetTextoBtnQuitarUsuario(String nombre) {
        btnQuitarUsuario.setText(nombre);
    }

    @Override
    public void showAtnAtrasLstUsu() {
        btnAtrasLstUsu.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidebtnAtrasLstUsu() {
        btnAtrasLstUsu.setVisibility(View.GONE);
    }

    @Override
    public void validadoUser(String user) {
        txtUsuarioPassword.setText(user);
    }

    @Override
    public void clearFocus() {
        try {
            edittextPassword.clearFocus();
            edittextUsername.clearFocus();
            edittextPassword.setText("");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextPassword.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edittextUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        apiRetrofit = null;
        this.loginPreferentRepository = null;
        dialogTimeOutConnection =null;
    }


    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            int i = textView.getId();
            if (i == R.id.edittext_password) {
                onBtnSiguientePassword();
            } else if (i == R.id.edittext_password_user) {
                onBtnSiguienteUser();
            } else if (i == R.id.edittext_correo){
                onBtnSiguienteCorreo();
            }else if (i == R.id.edittext_dni){
                onBtnSiguienteDni();
            }/*else if (i == R.id.edittext_password_user){
                onBtnSiguientePasswordUsuario();
            }*/
            handled = true;
        }
        return handled;

    }



    @Override
    public void getTodosUsuarios(Callback<List<PersonaUi>> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.getTodosUsuarios(usuarioCallback);
    }

    @Override
    public void guardarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.guardarUsuario(usuarioUi, usuarioCallback);
    }

    @Override
    public void eliminarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.eliminarUsuario(usuarioUi, usuarioCallback);
    }

    @Override
    public void elimarTodosUsuario(Callback<Boolean> usuarioCallback) {
        if (loginPreferentRepository != null)
            loginPreferentRepository.elimarTodosUsuario(usuarioCallback);
    }

    @Override
    public void onClickPersona(PersonaUi personaUi) {
        presenter.onClickUsuario(personaUi);
    }

    @Override
    public void hideKeyBoard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClickedBtnQuitarUsuario() {
        presenter.onClickedBtnQuitarUsuario();
    }

    public void onClickedBtnAtrasListUsua() {
        presenter.onClickedBtnAtrasListUsua();
    }

    public void onclickedrecPasswd(){
        presenter.onConfirmacionPassword();
    }

    public void onClickedBtnClosePassword() {
        presenter.onClickedBtnClosePassword();
    }

    public void onBtnSiguienteUser() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextUsername.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguienteUser(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString());
    }

    public void onBtnSiguienteDni(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editttextDni.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguienteDni(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString(), edittextCorreo.getText().toString(), editttextDni.getText().toString());
    }

    public void onBtnAtrasCorreo(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextCorreo.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onClickedBtnAtrasCorreo();
    }

    public void onBtnAtrasDni(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editttextDni.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onClickedBtnAtrasDni();
    }

    /*public void onBtnSiguientePasswordUsuario(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextPasswordUser.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguientePasswordUsuario(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString());
    }*/

    public void onBtnSiguientePassword(){
        presenter.onBtnSiguientePassword(edittextPassword.getText().toString());
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextPassword.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBtnSiguienteCorreo(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittextCorreo.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onBtnSiguienteCorreo(edittextUsername.getText().toString(), edittextPasswordUser.getText().toString(), edittextCorreo.getText().toString());
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_quitar_usuario)onClickedBtnQuitarUsuario();
        if(v.getId()==R.id.btn_atras_lst_usu)onClickedBtnAtrasListUsua();
        if(v.getId()==R.id.txtrecPassw)onclickedrecPasswd();
        if(v.getId()==R.id.btn_close_password)onClickedBtnClosePassword();
        //if(v.getId()==R.id.btn_siguiente_password_user)onBtnSiguientePasswordUsuario();
        if(v.getId()==R.id.btn_siguiente_password)onBtnSiguientePassword();
        if(v.getId()==R.id.btn_siguiente_user)onBtnSiguienteUser();
        if (v.getId()==R.id.btn_siguiente_correo)onBtnSiguienteCorreo();
        if (v.getId()==R.id.btn_siguiente_dni)onBtnSiguienteDni();
        if (v.getId()==R.id.btn_atras_correo)onBtnAtrasCorreo();
        if (v.getId()==R.id.btn_atras_dni)onBtnAtrasDni();
        if (v.getId()==R.id.btn_atras_lst_usu_pass)onClickedBtnAtrasListUsua();
        if(v.getId()==R.id.btn_reiniciar)onClickBtnReiniciarPageProgress();
        if(v.getId()==R.id.btn_cancelar)onClickBtnCancelar();


    }

    private void onClickBtnCancelar() {
        presenter.onClickBtnCancelar();
    }

    private void onClickBtnReiniciarPageProgress() {
        presenter.onClickBtnReiniciarPageProgress();
    }


    @Override
    public void onErrorPasswordUsuario(String string) {
        edittextPasswordUser.setError(string);
        edittextPasswordUser.requestFocus();
        edittextPasswordUser.setSelected(true);
    }

    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if ( view instanceof ViewGroup ) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    @Override
    public void disabledOnClick() {
        enableDisableView(groupPageUser, false);
    }

    @Override
    public void enableOnClick() {
        enableDisableView(groupPageUser, true);
    }

    @Override
    public void setUrlImgenInstitucion(String imagenUrl) {
        Glide.with(this)
                .load(imagenUrl)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imgInstitucion);
    }

    @Override
    public void showLoginBienvenida(String nombres, String imagenUrl, String imagenInsitucionUrl) {
        Calendar calendar = Calendar.getInstance();
        Log.d(getTag(), "hora: " + calendar.get(Calendar.HOUR_OF_DAY) );
        if(calendar.get(Calendar.HOUR_OF_DAY) >= 0 &&  calendar.get(Calendar.HOUR_OF_DAY) <= 12){
            textBienvenida.setText("Morning");
            Glide.with(imageBienvenida.getContext())
                    .load(R.drawable.good_morning_img)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imageBienvenida);
        }else if(calendar.get(Calendar.HOUR_OF_DAY) > 12 &&  calendar.get(Calendar.HOUR_OF_DAY) < 18){
            textBienvenida.setText("Afternoon");
            Glide.with(imageBienvenida.getContext())
                    .load(R.drawable.good_morning_img)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imageBienvenida);
        }else{
            textBienvenida.setText("Night");
            Glide.with(imageBienvenida.getContext())
                    .load(R.drawable.good_night_img)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imageBienvenida);
        }

        nombreUser.setText(nombres);

        Glide.with(this)
                .load(imagenInsitucionUrl)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imgBienvenidaInstitucion);

        Glide.with(this)
                .load(imagenUrl)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imgBienvenidaUserer);

        groupBienvenidad.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoginBienvenida() {
        groupBienvenidad.setVisibility(View.GONE);
    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectivityManager!=null)activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showMessageNotConnection() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_not_connection);
        dialog.show();

    }

    @Override
    public void showTimeOutConnection() {
        if(dialogTimeOutConnection==null||!dialogTimeOutConnection.isShowing()){
            dialogTimeOutConnection = new Dialog(this);
            dialogTimeOutConnection.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogTimeOutConnection.setContentView(R.layout.dialog_unknow_connection);
            Button buttonSalir = dialogTimeOutConnection.findViewById(R.id.btn_salir);
            buttonSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogTimeOutConnection.dismiss();
                    presenter.onClickTimeOutConnectionSalir();
                }
            });
            Button buttonContinuar = dialogTimeOutConnection.findViewById(R.id.btn_continuar);
            buttonContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogTimeOutConnection.dismiss();
                    //presenter.onClickTimeOutConnectionContinuar();
                }
            });

            dialogTimeOutConnection.show();
        }

    }

    @Override
    public void showViewPagerProgressPager(List<LoginProgressPagerUi> loginProgressPagerUiList) {

    }

    @Override
    public void hideViewPagerProgressPager() {
        //viewPagerPageProgress.setVisibility(View.GONE);
    }

    @Override
    public void changePagerProgressPager(final int posicion) {
       /* Log.d(getTag(), "changePagerProgressPager: " + posicion);
        viewPagerPageProgress.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewPagerPageProgress!=null)viewPagerPageProgress.setCurrentItem(posicion);
            }
        },2000);*/
    }

    @Override
    public void showBtnReintentarProgress() {
        btn_reiniciar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnReintentarProgress() {
        btn_reiniciar.setVisibility(View.GONE);
    }
}
