package com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaEventos;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.AdjuntoEventoAdapterMore;
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.AdjuntoEventoEncuesta;
import com.consultoraestrategia.ss_crmeducativo.eventos.adapter.AdjuntoPreviewAdapter;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi;
import com.consultoraestrategia.ss_crmeducativo.util.IntentHelper;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.LinkUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DialogListaBannerEvento extends DialogFragment implements InformacionListaEventosView, LinkUtils.OnClickListener, AdjuntoPreviewAdapter.Listener, AdjuntoEventoAdapterMore.Listener, AdjuntoEventoEncuesta.Listener {

    @BindView(R.id.nombrePersonaEvento)
    TextView textNombre;
    @BindView(R.id.textView5)
    TextView textContenido;
    @BindView(R.id.nombre_directivo)
    TextView nombreDirectivo;
    @BindView(R.id.directivo)
    TextView directivo;

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
    @BindView(R.id.txt_megusta)
    TextView txtMegusta;
    @BindView(R.id.img_megusta)
    ImageView imgMegusta;
    @BindView(R.id.txt_tipo_evento)
    TextView txtTipoEvento;
    @BindView(R.id.contentEvento)
    RecyclerView contentEvento;
    @BindView(R.id.root)
    NestedScrollView root;
    @BindView(R.id.rc_recursos)
    RecyclerView rc_recursos;
    @BindView(R.id.content_recurso)
    View content_recurso;
    @BindView(R.id.rc_encuesta)
    RecyclerView rcEncuesta;

    private Unbinder bind;
    private EventosPresenter presenter;
    private AdjuntoPreviewAdapter adapter;


    public static DialogListaBannerEvento newInstance() {
        DialogListaBannerEvento fragment = new DialogListaBannerEvento();
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
        View v = inflater.inflate(R.layout.dialog_lista_banner_evento, container, false);
        bind = ButterKnife.bind(this, v);

        adapter = new AdjuntoPreviewAdapter(this, contentEvento);
        contentEvento.setLayoutManager(new LinearLayoutManager(getContext()));
        contentEvento.setAdapter(adapter);
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


    @OnClick({R.id.btn_me_gusta, R.id.btn_me_compartir})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_me_gusta:
                if(presenter!=null) presenter.onClikLikeInfoEvento();
                break;
            case R.id.btn_me_compartir:
                if(presenter!=null) presenter.onClikLikeInfoCompartir();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void showInformacionEvento(EventosUi eventosUi, EventoAdjuntoUi adjuntoUi, boolean mostrarRecursos) {
        textContenido.setText(eventosUi.getDescripcion());
        LinkUtils.autoLink(textContenido, this);
        txtTitulo.setText(eventosUi.getTitulo());
        List<EventoAdjuntoUi> adjuntoUiPreviewList = eventosUi.getAdjuntoUiPreviewList()!=null?eventosUi.getAdjuntoUiPreviewList():new ArrayList<>();
        adapter.setList(eventosUi, adjuntoUiPreviewList);
        //adapter.scrollToPosition(adjuntoUi);
        int selectedPosition = adjuntoUiPreviewList.indexOf(adjuntoUi);
        if(selectedPosition!=-1&&selectedPosition!=0){
            contentEvento.post(() -> {
                float y = contentEvento.getY() + contentEvento.getChildAt(selectedPosition).getY();
                root.smoothScrollTo(0, (int) y);
            });
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


        if (eventosUi.getTiposUi().getTipos()== TiposUi.AGENDA){
            String fechaPublicacion = eventosUi.getNombreCalendario() + " " + eventosUi.getNombreFechaPublicion();
            directivo.setText(fechaPublicacion);
        }else {
            //" - Publicado jue 1 jun 2021"
            String fechaPublicacion = eventosUi.getCargo() + " " + eventosUi.getNombreFechaPublicion();
            directivo.setText(fechaPublicacion );
        }
        String tipo = eventosUi.getTiposUi().getNombre();

        String nombreFecha = eventosUi.getTiposUi().getNombre();
       switch (eventosUi.getTiposUi().getTipos()){
           case EVENTOS:
               if(eventosUi.getFechaEvento() > 0)
                   nombreFecha = " " + Utils.getFechaDiaMesAnho(eventosUi.getFechaEvento());
               else nombreFecha = "";
               break;
           case NOTICIA:
               if(eventosUi.getFechaEvento() > 0)
                   nombreFecha = " " + Utils.tiempoFechaCreacion(eventosUi.getFechaEvento());
               else nombreFecha = "";
               break;
       }

        txtTipoEvento.setText(nombreFecha);

        if(mostrarRecursos){
            List<EventoAdjuntoUi> adjuntoUiList = eventosUi.getAdjuntoUiList()!=null?eventosUi.getAdjuntoUiList():new ArrayList<>();
            int count;
            switch (adjuntoUiList.size()){
                case 1:
                    count = 2;
                    break;
                case 2:
                    count = 2;
                    break;
                case 3:
                    count = 3;
                    break;
                default:
                    count = 4;
                    break;
            }

           AdjuntoEventoAdapterMore adjuntoEventoAdapter = new AdjuntoEventoAdapterMore(adjuntoUiList, eventosUi,this);
            rc_recursos.setAdapter(adjuntoEventoAdapter);
            rc_recursos.setLayoutManager(new GridLayoutManager(rc_recursos.getContext(), count));

            if(adjuntoUiList.isEmpty()){
                content_recurso.setVisibility(View.GONE);
            }else {
                content_recurso.setVisibility(View.VISIBLE);
            }
        }else {
            content_recurso.setVisibility(View.GONE);
        }

        AdjuntoEventoEncuesta adjuntoEventoEncuesta = new AdjuntoEventoEncuesta(eventosUi.getAdjuntoUiEncuestaList(), this);
        rcEncuesta.setAdapter(adjuntoEventoEncuesta);
        rcEncuesta.setLayoutManager(new LinearLayoutManager(rcEncuesta.getContext()));
        if(eventosUi.getAdjuntoUiEncuestaList().isEmpty()){
            rcEncuesta.setVisibility(View.GONE);
        }else {
            rcEncuesta.setVisibility(View.VISIBLE);
        }
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
    public void showCompartirEvento(EventosUi eventosUi) {
        Glide
                .with(getContext())
                .asBitmap()
                .load(eventosUi.getImagen())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        new IntentHelper().sendEmail(getContext(),null,eventosUi.getTitulo(), eventosUi.getDescripcion());
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
                        IntentHelper.sendEmailUri(getContext(), null, eventosUi.getTitulo(), eventosUi.getDescripcion(), Uri.parse(eventosUi.getImagen()));
                    }
                });
    }

    @Override
    public void hideInformacionEvento() {
        dismiss();
    }

    @Override
    public void setPresenter(EventosPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLinkClicked(View v, String link) {

    }

    @Override
    public void onClicked(View v) {

    }

    @Override
    public void onClickDialogAdjuntoEvento(EventosUi eventoUi, EventoAdjuntoUi adjuntoUi) {
        presenter.onClickDialogListBannerAdjuntoPreview(eventoUi, adjuntoUi);
    }



    @Override
    public void onClickAdjunto(EventosUi eventoUi, EventoAdjuntoUi adjuntoUi, boolean more) {
        presenter.onClickAdjunto(eventoUi, adjuntoUi, more);
    }


    @Override
    public void onLinkEncuesta(EventoAdjuntoUi adjuntoUi) {
        presenter.itemLinkEncuesta(adjuntoUi);
    }
}
