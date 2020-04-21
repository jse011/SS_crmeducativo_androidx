package com.consultoraestrategia.ss_crmeducativo.login2.principal.password;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.login2.principal.Login2Presenter;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class PasswordFragment extends Fragment implements PasswordView, TextView.OnEditorActionListener {

    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.txt_nombre_usuario)
    TextView txtNombreUsuario;
    @BindView(R.id.edittext_password)
    TextInputEditText edittextPassword;
    @BindView(R.id.btn_siguiente_password)
    Button btnSiguientePassword;
    @BindView(R.id.imageView17)
    ImageView imageView17;
    @BindView(R.id.btn_close_password)
    ImageView btnClosePassword;
    @BindView(R.id.img_usuario_password)
    CircleImageView imgUsuarioPassword;
    @BindView(R.id.txt_usuario_password)
    TextView txtUsuarioPassword;
    @BindView(R.id.img_institucion)
    ImageView imgInstitucion;
    @BindView(R.id.btn_atras_lst_usu_pass)
    ImageView btnAtrasLstUsuPass;
    private Unbinder unbinder;
    private Login2Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edittextPassword.setOnEditorActionListener(this);
        //   initAdapter();
    }

    @Override
    public void onAttach(Login2Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPersona(PersonaUi personaUiSelected) {
        txtNombreUsuario.setText(personaUiSelected.getNombres());
        txtUsuarioPassword.setText(personaUiSelected.getUsuario());
        Glide.with(this)
                .load(personaUiSelected.getImagenUrl())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                .into(imgUsuarioPassword);

        Glide.with(this)
                .load(personaUiSelected.getInstitucionUrl())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imgInstitucion);
    }

    @Override
    public void onInvalitedPassword(String error) {
        edittextPassword.requestFocus();
        edittextPassword.setSelected(true);
        edittextPassword.setError(error);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            int i = textView.getId();
            if (i == R.id.edittext_password) {
                String password = edittextPassword.getText()==null?"":edittextPassword.getText().toString();
                presenter.onClickPasswordSiguiente(password);
            }
            handled = true;
        }
        return handled;
    }

    @OnClick({R.id.btn_siguiente_password, R.id.btn_close_password, R.id.btn_atras_lst_usu_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_siguiente_password:
                String password = edittextPassword.getText()==null?"":edittextPassword.getText().toString();
                presenter.onClickPasswordSiguiente(password);
                break;
            case R.id.btn_close_password:
                presenter.onClickPasswordAtras();
                break;
            case R.id.btn_atras_lst_usu_pass:
                presenter.onClickPasswordAtras();
                break;
        }
    }
}
