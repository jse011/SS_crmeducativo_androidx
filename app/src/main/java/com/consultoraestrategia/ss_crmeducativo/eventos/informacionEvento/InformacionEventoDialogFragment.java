package com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi;
import com.consultoraestrategia.ss_crmeducativo.util.IntentHelper;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class InformacionEventoDialogFragment extends DialogFragment implements InformacionEventoDialogView {

    @BindView(R.id.nombrePersonaEvento)
    TextView textNombre;
    @BindView(R.id.textView5)
    TextView textContenido;
    @BindView(R.id.fechaPublicacion)
    TextView fechaPublicacion;
    @BindView(R.id.nombre_directivo)
    TextView nombreDirectivo;
    @BindView(R.id.imagenEvento)
    ImageView imagenEvento;
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
                presenter.onClickBtnMegustaInfoEventos();
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
    }

    @Override
    public void setPresenter(EventosPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showInformacionEvento(EventosUi eventosUi) {
        textContenido.setText(Html.fromHtml(eventosUi.getDescripcion()));

        txtTitulo.setText(eventosUi.getTitulo());
        String tipo = eventosUi.getTiposUi().getNombre() + " para:";

        if (eventosUi.getTiposUi().getTipos()== TiposUi.NOTICIA ||
                eventosUi.getTiposUi().getTipos() == TiposUi.EVENTOS){

            if(!TextUtils.isEmpty(eventosUi.getImagen())){
                imagenEvento.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                            .load(eventosUi.getImagen())
                            .apply(Utils.getGlideRequestOptionsSimple())
                            .into(imagenEvento);


            }else {
                imagenEvento.setImageDrawable(null);
                imagenEvento.setVisibility(View.GONE);
            }
        }else{
            imagenEvento.setVisibility(View.GONE);
            imagenEvento.setImageDrawable(null);

        }

        textNombre.setText(eventosUi.getNombreEntidad());
        if (!TextUtils.isEmpty(eventosUi.getFotoEntidad())){
            imagenEntidad.setVisibility(View.VISIBLE);
                    Glide.with(imagenEntidad)
                            .load(eventosUi.getFotoEntidad())
                            .apply(Utils.getGlideRequestOptionsSimple())
                            .into(imagenEntidad);
        } else {
            imagenEntidad.setImageDrawable(null);
            imagenEntidad.setVisibility(View.GONE);
        }

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
        Glide
                .with(getContext())
                .asBitmap()
                .load(eventosUi.getImagen())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        IntentHelper.sendEmailUri(getContext(),null,eventosUi.getTitulo(), eventosUi.getDescripcion(), null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), resource, "Title", null);
                        IntentHelper.sendEmailUri(getContext(),null,eventosUi.getTitulo(), eventosUi.getDescripcion(), Uri.parse(path));
                    }
                });
    }

    @Override
    public void hideInformacionEvento() {
        dismiss();
    }
}
