package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewPresenter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.adapter.SendAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class Send extends Fragment implements SendView, SendAdapter.Listener {
    @BindView(R.id.imagenEvento)
    PhotoView imagenEvento;
    @BindView(R.id.rec_preview)
    RecyclerView recPreview;
    @BindView(R.id.frameLayout2)
    FrameLayout frameLayout2;
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.conten_perfil)
    FrameLayout contenPerfil;
    @BindView(R.id.nombrePersonaEvento)
    TextView nombrePersonaEvento;
    @BindView(R.id.textView171)
    TextView textView171;

    @BindView(R.id.backPrincipal)
    ImageView backPrincipal;
    @BindView(R.id.constraintLayout5)
    ConstraintLayout constraintLayout5;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.text_progress)
    TextView textProgress;
    @BindView(R.id.pgr_progress)
    ProgressBar pgrProgress;
    @BindView(R.id.contenEnviar)
    ConstraintLayout contenEnviar;
    @BindView(R.id.btn_save)
    FloatingActionButton btnSave;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    private Unbinder unbinder;
    private SendAdapter sendAdapter;
    private PreviewPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_renocimiento_send, container, false);
        unbinder = ButterKnife.bind(this, view);
        recPreview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        sendAdapter = new SendAdapter(this);
        recPreview.setAdapter(sendAdapter);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(SentimientoUi sentimientoUi) {
        Glide.with(imagenEvento)
                .load(sentimientoUi.getFoto())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(imagenEvento);
    }

    @Override
    public void setImgaPreview(PersonaUi personaUi, ArrayList<SentimientoUi> sentimientoList) {
        if (!sentimientoList.isEmpty()) {
            Glide.with(imagenEvento)
                    .load(sentimientoList.get(0).getFoto())
                    .apply(new RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(imagenEvento);
        }

        Glide.with(circleImageView)
                .load(personaUi.getFoto())
                .apply(Utils.getGlideRequestOptions())
                .into(circleImageView);

        String nombre = personaUi.getNombre() + " " + personaUi.getApellidos();
        nombrePersonaEvento.setText(nombre);

        sendAdapter.setLista(sentimientoList);
    }

    @Override
    public void setPresentar(PreviewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void progress(String nombre, int progress) {
        String progreso = progress + "%";
        String descripcion = nombre + " Envíando ...";
        textView171.setText(descripcion);
        textProgress.setText(progreso);
        pgrProgress.setProgress(progress);
    }

    @Override
    public void onErrorSendStorege() {
        Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccesSendStorege() {
        Toast.makeText(getContext(), "Éxito!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideContenDownload() {
        contenEnviar.setVisibility(View.GONE);
    }

    @Override
    public void showContenDownload() {
        contenEnviar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBtnSend() {
        btnSave.show();
    }

    @Override
    public void hideBtnSend() {
        btnSave.hide();
    }

    @OnClick(R.id.btn_save)
    public void onClickBtnSave() {
        presenter.onClickBtnSave();
    }

    @OnClick(R.id.btn_cancelar)
    public void onViewClicked() {
        presenter.onClickCancelar();
    }

    @OnClick(R.id.backPrincipal)
    public void onViewClicked2() {
        presenter.btnAtras();
    }

}
