package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewPresenter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class Sentimiento1 extends Fragment implements SentimientoView {
    Unbinder unbinder;

    @BindView(R.id.txt_Titulo)
    TextView txtTitulo;
    @BindView(R.id.conten_preview)
    ConstraintLayout contenPreview;
    @BindView(R.id.conten_camera)
    ConstraintLayout contenCamera;
    @BindView(R.id.img_preview)
    PhotoView imgPreview;
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.nombrePersonaEvento)
    TextView nombrePersonaEvento;
    @BindView(R.id.backPrincipal)
    ImageView backPrincipal;
    @BindView(R.id.imgChangeCamera)
    ImageView imgChangeCamera;
    @BindView(R.id.imgCapture)
    ImageView imgCapture;
    @BindView(R.id.imgFlashOnOff)
    ImageView imgFlashOnOff;
    private String TAG = "Sentimiento1TAG";
    private PreviewPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_renocimiento_sentimiento1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onResumeViewPager() {
        //if (cameraView != null)cameraView.start();
        Log.d(TAG, "onResumeViewPager");
    }


    @Override
    public void onPauseViewPager() {
        // if (cameraView != null) cameraView.stop();
        Log.d(TAG, "onPauseViewPager");
    }

    @Override
    public void setContente(SentimientoUi sentimientoUi) {
        txtTitulo.setText(sentimientoUi.getNombre());
    }

    @Override
    public void setPresentar(PreviewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showPreview(SentimientoUi sentimientoUi, PersonaUi personaUi) {
        contenPreview.setVisibility(View.VISIBLE);

        Glide.with(imgPreview)
                .load(sentimientoUi.getFoto())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(imgPreview);

        Glide.with(circleImageView)
                .load(personaUi.getFoto())
                .apply(Utils.getGlideRequestOptions())
                .into(circleImageView);

        String nombre = personaUi.getNombre() + " " + personaUi.getApellidos();
        nombrePersonaEvento.setText(nombre);

    }

    @Override
    public void hideCamera() {
        contenCamera.setVisibility(View.GONE);
    }

    @Override
    public void hidePreview() {
        contenPreview.setVisibility(View.GONE);
        imgPreview.setImageDrawable(null);
    }

    @Override
    public void showCamera() {
        contenCamera.setVisibility(View.VISIBLE);
    }

    @Override
    public void flash(int flash) {
        switch (flash) {
            case 0:
                imgFlashOnOff.setImageResource(R.drawable.ic_flash_auto);
                break;
            case 1:
                imgFlashOnOff.setImageResource(R.drawable.ic_flash_on);
                break;
            default:
                imgFlashOnOff.setImageResource(R.drawable.ic_flash_off);
                break;
        }
    }

    @OnClick(R.id.backPrincipal)
    public void onBackPrincipalClicked() {
        // cameraView.start();
        presenter.onBackPrincipalClicked();
    }


    @OnClick(R.id.imgChangeCamera)
    public void onViewClicked() {
        presenter.toggleFacing();
    }

    @OnClick(R.id.imgFlashOnOff)
    public void onViewFlash() {
        presenter.onViewFlash();

    }

    @OnClick(R.id.imgCapture)
    public void onCaptureClicked() {
        presenter.capturePicture();
    }

    @OnClick(R.id.btn_siguiente)
    public void onBtnSiguiente() {
        presenter.btnSiguiente();
    }

    @OnClick(R.id.btn_atras)
    public void onViewClicked2() {
        presenter.btnAtras();
    }
}
