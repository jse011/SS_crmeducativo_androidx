package com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.padre_mentor.clean.app.driveYoutubePreview.fragment.MultimediaPreview2Fragment;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.PreviewArchivoView;
import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TipoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi;
import com.consultoraestrategia.ss_crmeducativo.util.IntentHelper;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsStorage;
import com.consultoraestrategia.ss_crmeducativo.utils.youtube.YoutubeConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class InformacionEventoDialogFragment extends DialogFragment implements InformacionEventoDialogView, LifecycleImpl.LifecycleListener {
    @SuppressLint("InlinedApi")
    private static final int PORTRAIT_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

    @SuppressLint("InlinedApi")
    private static final int LANDSCAPE_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

    @BindView(R.id.nombrePersonaEvento)
    TextView textNombre;
    @BindView(R.id.textView5)
    TextView textContenido;
    @BindView(R.id.fechaPublicacion)
    TextView fechaPublicacion;
    @BindView(R.id.nombre_directivo)
    TextView nombreDirectivo;

    @BindView(R.id.circleImageView)
    ImageView imagenEntidad;
    @BindView(R.id.btn_me_gusta)
    ConstraintLayout btnMeGusta;
    @BindView(R.id.btn_me_compartir)
    ConstraintLayout btnMeCompartir;
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.cont_me_gusta)
    ConstraintLayout contMeGusta;
    @BindView(R.id.directivo)
    TextView directivo;
    @BindView(R.id.txt_megusta)
    TextView txtMegusta;
    @BindView(R.id.img_megusta)
    ImageView imgMegusta;
    private Unbinder bind;
    private EventosPresenter presenter;
    @BindView(R.id.constraintLayout22)
    View piePagina;
    @BindView(R.id.constraintLayout5)
    View cabecera;
    private YoutubeConfig youtubeConfig;
    private EventoAdjuntoUi adjuntoUi;

    public static InformacionEventoDialogFragment newInstance() {
        InformacionEventoDialogFragment fragment = new InformacionEventoDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_informacion_evento, container, false);
        bind = ButterKnife.bind(this, v);
        getChildFragmentManager().registerFragmentLifecycleCallbacks(new LifecycleImpl(0, this), true);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            cabecera.setVisibility(View.VISIBLE);
            piePagina.setVisibility(View.VISIBLE);
            youtubeConfig.getYouTubePlayerView().requestLayout();
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            cabecera.setVisibility(View.GONE);
            piePagina.setVisibility(View.GONE);
            youtubeConfig.getYouTubePlayerView().requestLayout();
        }
        if(youtubeConfig!=null)youtubeConfig.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @OnClick({R.id.backPrincipal, R.id.btn_me_gusta, R.id.btn_me_compartir})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backPrincipal:
                dismiss();
                break;
            case R.id.btn_me_gusta:
                presenter.onClikLikeInfoEvento();
                break;
            case R.id.btn_me_compartir:
                presenter.onClickBtnCompartirInfoEventos();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        youtubeConfig = null;
    }

    @Override
    public void setPresenter(EventosPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showInformacionEvento(EventosUi eventosUi, EventoAdjuntoUi adjuntoUi) {
        this.adjuntoUi = adjuntoUi;
        if(adjuntoUi.getTipoArchivo()== TipoAdjuntoUi.VIDEO){
            // Begin the transaction
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.content_youtube, new MultimediaPreview2Fragment());
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit();
        } else if(adjuntoUi.getTipoArchivo() == TipoAdjuntoUi.YOUTUBE){
            if (youtubeConfig == null) youtubeConfig = new YoutubeConfig(getContext());
            //youtubeConfig.setDisabledRotation(true);
            youtubeConfig.initialize(adjuntoUi.getYotubeId(), getChildFragmentManager(), R.id.content_youtube, new YoutubeConfig.PlaybackEventListener() {
                @Override
                public void onPlaying() {
                    //imgActionYoutube.setImageDrawable(ContextCompat.getDrawable(imgActionYoutube.getContext(), R.drawable.ic_pause_youtube));
                }

                @Override
                public void onPaused() {
                    //imgActionYoutube.setImageDrawable(ContextCompat.getDrawable(imgActionYoutube.getContext(), R.drawable.ic_play_youtube));
                }

                @Override
                public void onLandscape() {

                }

                @Override
                public void onPortrait() {

                }
            });
        } else {
            // Begin the transaction
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.content_evento, PreviewFragment.newInstance(adjuntoUi.getImagePreview()));
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit();
        }

        textContenido.setText(Html.fromHtml(eventosUi.getDescripcion()));

        txtTitulo.setText(eventosUi.getTitulo());

        textNombre.setText(eventosUi.getNombreEntidad());
        /*if (!TextUtils.isEmpty(eventosUi.getFotoEntidad())){
            imagenEntidad.setVisibility(View.VISIBLE);
                    Glide.with(imagenEntidad)
                            .load(eventosUi.getFotoEntidad())
                            .apply(Utils.getGlideRequestOptionsSimple())
                            .into(imagenEntidad);
        } else {
            imagenEntidad.setImageDrawable(null);
            imagenEntidad.setVisibility(View.GONE);
        }*/

        directivo.setText(eventosUi.getCargo());
        nombreDirectivo.setText(eventosUi.getPersona());


        changeLike(eventosUi);

        String tipoEvento = eventosUi.getTiposUi().getNombre();
        switch (eventosUi.getTiposUi().getTipos()){
            case EVENTOS:
                tipoEvento += " " + Utils.tiempoFechaCreacion(eventosUi.getFechaEvento());
                break;
            case NOTICIA:
                tipoEvento += " del " + Utils.getFechaDiaMesAnho(eventosUi.getFechaEvento());
                break;
            default:
                tipoEvento += " " + Utils.tiempoFechaCreacion(eventosUi.getFechaEvento());
                break;
        }

        fechaPublicacion.setText(tipoEvento);
    }

    @Override
    public void changeLike(EventosUi eventosUi) {
        String megusta = "me gusta";
        if(eventosUi.getLikeCount()!=0){
            megusta =  eventosUi.getLikeCount() + " me gusta";
        }else if(eventosUi.getLikeCount()>1000){
            megusta += "1k me gusta" ;
        }

        if(eventosUi.getLike()){
            imgMegusta.setImageDrawable(ContextCompat.getDrawable(imgMegusta.getContext(),R.drawable.ic_like ));
        }else {
            imgMegusta.setImageDrawable(ContextCompat.getDrawable(imgMegusta.getContext(),R.drawable.ic_like_disabled ));
        }
        txtMegusta.setText(megusta);

    }

    @Override
    public void showCompartirEvento(final EventosUi eventosUi) {
        if(TextUtils.isEmpty(eventosUi.getImagen())){
            IntentHelper.sendEmailUri(getContext(),null,eventosUi.getTitulo(), eventosUi.getDescripcion(), null);
        }else {
            IntentHelper.sendEmailUri(getContext(),null,eventosUi.getTitulo(), eventosUi.getDescripcion(), Uri.parse(eventosUi.getImagen()));
        }
    }

    @Override
    public void hideInformacionEvento() {
        dismiss();
    }

    @Override
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(PORTRAIT_ORIENTATION);
    }


    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if (f instanceof PreviewArchivoView.MultimediaPreviewView) {

        }
        getActivity().setRequestedOrientation(PORTRAIT_ORIENTATION);
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if (f instanceof PreviewArchivoView.MultimediaPreviewView) {
            if(adjuntoUi!=null){
                PreviewArchivoView.MultimediaPreviewView multimediaPreviewView = (PreviewArchivoView.MultimediaPreviewView)f;
                multimediaPreviewView.hideProgress();
                String extencion = UtilsStorage.getExtencion(adjuntoUi.getTitulo());
                if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("mp3") || extencion.toLowerCase().contains("ogg") || extencion.toLowerCase().contains("wav"))) {
                    multimediaPreviewView.uploadAudio(adjuntoUi.getDriveId());
                }else if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("mp4"))) {
                    multimediaPreviewView.uploadMp4(adjuntoUi.getDriveId());
                }else if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("flv"))) {
                    multimediaPreviewView.uploadFlv(adjuntoUi.getDriveId());
                }else if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("mkv"))) {
                    multimediaPreviewView.uploadMkv(adjuntoUi.getDriveId());
                }else {
                    multimediaPreviewView.uploadArchivo(adjuntoUi.getDriveId());
                }
                multimediaPreviewView.dimensionRatio(0, 0);
            }

        }
    }

    public static class PreviewFragment extends Fragment  {
        private Unbinder bind;
        @BindView(R.id.imagenEvento)
        ImageView imagenEvento;

        public static PreviewFragment newInstance(String imagenUrl) {
            Bundle args = new Bundle();
            args.putString("imagenUrl", imagenUrl);
            PreviewFragment fragment = new PreviewFragment();
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_preview_evento_image, container, false);
            bind = ButterKnife.bind(this, v);
            return v;
        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Glide.with(imagenEvento.getContext())
                    .load(getArguments().getString("imagenUrl"))
                    .apply(Utils.getGlideRequestOptionsSimple())
                    .into(imagenEvento);
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            bind.unbind();

        }

    }
}
