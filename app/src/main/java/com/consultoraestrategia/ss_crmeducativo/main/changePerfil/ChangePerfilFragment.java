package com.consultoraestrategia.ss_crmeducativo.main.changePerfil;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio.SeleccionAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChangePerfilFragment extends DialogFragment implements ChangePerfilView {

    @BindView(R.id.imagenPerson)
    CircleImageView imagenPerson;
    @BindView(R.id.img_item_cargo)
    ImageView imgItemCargo;
    @BindView(R.id.txt_description_nombre)
    TextView txtDescriptionNombre;
    @BindView(R.id.txt_nombre)
    TextView txtNombre;
    @BindView(R.id.img_item_telefono)
    ImageView imgItemTelefono;
    @BindView(R.id.txt_description_telefono)
    TextView txtDescriptionTelefono;
    @BindView(R.id.celularPerson)
    EditText celularPerson;
    @BindView(R.id.img_item_direccion)
    ImageView imgItemDireccion;
    @BindView(R.id.txt_description_direccion)
    TextView txtDescriptionDireccion;
    @BindView(R.id.direccionPerson)
    EditText direccionPerson;
    @BindView(R.id.conten_direccion)
    LinearLayout contenDireccion;
    @BindView(R.id.img_item_mail)
    ImageView imgItemMail;
    @BindView(R.id.txt_description_email)
    TextView txtDescriptionEmail;
    @BindView(R.id.correoPerson)
    EditText correoPerson;
    Unbinder unbinder1;
    @BindView(R.id.imagenReconosimiento)
    CircleImageView imagenReconosimiento;
    @BindView(R.id.progress_succes2)
    ProgressBar progressSucces2;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.progressBar7)
    ProgressBar progressBar7;
    private Unbinder unbinder;
    private MainPresenter presenter;

    public ChangePerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    /**
     * Crea un diálogo de alerta sencillo
     *
     * @return Nuevo diálogo
     */
    public AlertDialog createSimpleDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_main_change_perfil, null);

        unbinder = ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

    public static SeleccionAnioAcademico newInstance(String title) {
        SeleccionAnioAcademico frag = new SeleccionAnioAcademico();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public void onResume() {
        /*if( getDialog()!=null&& getDialog().getWindow()!=null){
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }*/
        super.onResume();
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPersona(PersonaUi personaUi) {
        txtNombre.setText(Utils.capitalize(personaUi.getNombres() + " " + personaUi.getApellidos()));
        celularPerson.setText(personaUi.getCelular());
        //direccionPerson.setText(personaUi.getDireccion());
        correoPerson.setText(personaUi.getCorreo());
        txtDescriptionNombre.setText("Docente");
        if (!TextUtils.isEmpty(personaUi.getFoto())) {
            Glide.with(getContext())
                    .load(personaUi.getFoto())
                    .apply(Utils.getGlideRequestOptionsSimple())
                    .into(imagenPerson);

        /*    Glide.with(getContext())
                    .load(personaUi.getFoto())
                    .apply(Utils.getGlideRequestOptionsSimple())
                    .into(imagenReconosimiento);*/

        }
    }

    @Override
    public void close() {
        dismiss();
    }

    @Override
    public void showFaceDectecion(int georeferenciaId, final PersonaUi personaUi) {

        final String fileName = personaUi.getPersonaId() + "_1.jpg";
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Docentes").child("icrmedu_docente").child(String.valueOf(georeferenciaId)).child(fileName);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri uri = task.getResult();
                    if (uri != null) {
                        Glide.with(imagenReconosimiento)
                                .load(uri.toString())
                                .apply(Utils.getGlideRequestOptionsSimple())
                                .into(imagenReconosimiento);
                    }

                }
            }
        });


        Log.d("showFaceDectecion", "georeferenciaId: " + georeferenciaId + " PersonaId: " + personaUi.getPersonaId());
    }

    @Override
    public void updatePersona(PersonaUi personaUi) {
        if (personaUi.getWorking()) {
            progressSucces2.setVisibility(View.VISIBLE);
        } else {
            progressSucces2.setVisibility(View.GONE);
        }
    }

    @Override
    public void disabledButtons() {
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    @Override
    public void showProgress() {
        progressBar7.setVisibility(View.VISIBLE);
    }

    @Override
    public void enabledButtons() {
        btnAceptar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    @Override
    public void hideProgress() {
        progressBar7.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @OnClick(R.id.btn_cancelar)
    public void onViewClicked() {
        dismiss();
    }

    @OnClick(R.id.imagenReconosimiento)
    public void onClickedReconocimiento() {
        presenter.onClickReconocimiento();
    }

    @OnClick(R.id.btn_aceptar)
    public void onAceptarClicked() {
        presenter.onClickGuardarPerfil(celularPerson.getText().toString(), correoPerson.getText().toString());
    }

    @OnClick(R.id.nav_bar_content_profile)
    public void onBtnChangeImageClicked() {
        presenter.changeFile();
    }
}

