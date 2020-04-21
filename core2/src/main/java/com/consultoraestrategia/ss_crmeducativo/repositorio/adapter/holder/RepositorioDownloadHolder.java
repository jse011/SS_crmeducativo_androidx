package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;

import droidninja.filepicker.utils.AndroidLifecycleUtils;

public class RepositorioDownloadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView txtFile;
    private final ImageView imgExtencion;
    private final ImageView imgFondo;
    private final ProgressBar progressBar2;
    private final ImageView imgFondoprogres;
    private final ImageView imgClose;
    private final CircularProgressBar progressSucces;
    private final ImageView imgDownload;
    private final ImageView imgCheck;
    private final View contenFooter;
    private final ImageView btnMore;
    private final TextView txtFecha;
    private final TextView txtNombre;
    private RepositorioFileUi repositorioFileUi;
    private RepositorioItemListener listener;

    public RepositorioDownloadHolder(View itemView) {
        super(itemView);
        txtFile = (TextView)itemView.findViewById(R.id.txt_file);
        imgExtencion = (ImageView)itemView.findViewById(R.id.img_extencion);
        imgFondo = (ImageView)itemView.findViewById(R.id.img_fondo);
        progressBar2 = (ProgressBar)itemView.findViewById(R.id.progressBar2);
        imgFondoprogres = (ImageView)itemView.findViewById(R.id.img_fondo_progres);
        imgClose = (ImageView)itemView.findViewById(R.id.img_close);
        progressSucces = (CircularProgressBar)itemView.findViewById(R.id.progress_succes);
        imgDownload = (ImageView)itemView.findViewById(R.id.img_download);
        imgCheck = (ImageView)itemView.findViewById(R.id.imgCheck);
        contenFooter = (View)itemView.findViewById(R.id.view_footer);
        btnMore = (ImageView)itemView.findViewById(R.id.btn_more);
        txtFecha = (TextView)itemView.findViewById(R.id.txt_fecha);
        txtNombre = (TextView)itemView.findViewById(R.id.txt_nombre);

        imgDownload.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        contenFooter.setOnClickListener(this);
        imgFondo.setOnClickListener(this);
    }

    public void bind(RepositorioFileUi repositorioFileUi, RepositorioItemListener repositorioItemListener) {
        this.repositorioFileUi = repositorioFileUi;
        this.listener = repositorioItemListener;
        txtFile.setText(repositorioFileUi.getNombreArchivo());
        txtFecha.setText(Utils.f_fecha_letras_2(repositorioFileUi.getFechaAccionArchivo()));
        txtNombre.setText(repositorioFileUi.getNombreRecurso());
        setupSelect(repositorioFileUi.isSelect());
        setupEstado(repositorioFileUi.getEstadoFileU());
        setupIcono(repositorioFileUi.getTipoFileU());
        showImagen(repositorioFileUi.getTipoFileU());
    }

    private void setupSelect(boolean select) {
        if(select){
            imgCheck.setVisibility(View.VISIBLE);
        }else {
            imgCheck.setVisibility(View.GONE);
        }

    }

    private void setupEstado(RepositorioEstadoFileU estadoFileU) {
        hideDowload();
        hiProgress();
        hiProgressSuccess();
        switch (estadoFileU){
            case SIN_DESCARGAR:
                showDowload();
                break;
            case CONECTANDO_SERVER:
                showProgress();
                break;
            case ENPROCESO_DESCARGA:
                showProgressSuccess();
                break;
            case ERROR_DESCARGA:
                showDowload();
                break;
            case DESCARGA_COMPLETA:
                showImagen(repositorioFileUi.getTipoFileU());
                break;
        }
    }

    private void setupIcono(RepositorioTipoFileU tipoFileU) {
        switch (tipoFileU){
            case PDF:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_pdf));
                break;
            case AUDIO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_aud));
                break;
            case VIDEO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_vid));
                break;
            case YOUTUBE:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.youtube));
                break;
            case IMAGEN:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_img));
                break;
            case VINCULO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_link));
                break;
            case DOCUMENTO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_doc));
                break;
            case DIAPOSITIVA:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_ppt));
                break;
            case HOJA_CALCULO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_xls));
                break;
            case MATERIALES:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_other));
                break;
        }

    }

    private void showImagen(RepositorioTipoFileU tipoFileU) {

        switch (tipoFileU){
            case IMAGEN:
                String path = "";
                RequestOptions glideRequestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                if(repositorioFileUi.getEstadoFileU() == RepositorioEstadoFileU.DESCARGA_COMPLETA){
                    path = repositorioFileUi.getPath();
                }else {
                    path = repositorioFileUi.getUrl();
                    glideRequestOptions.override(50, 50);
                }

                Glide.with(itemView.getContext())
                        .load(path)
                        .apply(
                                glideRequestOptions.centerCrop()
                        ).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    //on load failed
                                    imgExtencion.setVisibility(View.VISIBLE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    //on load success
                                    imgExtencion.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                        .into(imgFondo);

                break;
            case VIDEO:
                if(repositorioFileUi.getEstadoFileU() == RepositorioEstadoFileU.DESCARGA_COMPLETA){

                    String pathw = repositorioFileUi.getPath();
                    if(TextUtils.isEmpty(pathw)){
                        imgExtencion.setVisibility(View.VISIBLE);
                    }else {
                        if(AndroidLifecycleUtils.canLoadImage(itemView.getContext())) {
                            Glide.with(itemView.getContext())
                                    .load(new File(pathw))
                                    .apply(RequestOptions
                                            .centerCropTransform()
                                            //.override(50, 80)
                                            .placeholder(droidninja.filepicker.R.drawable.image_placeholder))
                                    .thumbnail(0.5f)
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            imgExtencion.setVisibility(View.VISIBLE);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            imgExtencion.setVisibility(View.GONE);
                                            return false;
                                        }
                                    })
                                    .into(imgFondo);
                        }else {
                            imgExtencion.setVisibility(View.VISIBLE);
                        }
                    }

                }
                break;
                default:
                    imgFondo.setImageBitmap(null);
                    //imgFondo.destroyDrawingCache();
                    imgExtencion.setVisibility(View.VISIBLE);
                    break;
        }


    }

    private void showProgress(){
        progressBar2.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.VISIBLE);
        imgFondoprogres.setVisibility(View.VISIBLE);
    }

    private void hiProgress(){
        progressBar2.setVisibility(View.GONE);
        imgClose.setVisibility(View.GONE);
        imgFondoprogres.setVisibility(View.GONE);
    }

    private void showProgressSuccess(){
        progressSucces.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.VISIBLE);
        imgFondoprogres.setVisibility(View.VISIBLE);
    }

    private void hiProgressSuccess(){
        progressSucces.setVisibility(View.GONE);
        imgClose.setVisibility(View.GONE);
        imgFondoprogres.setVisibility(View.GONE);
    }

    private void showDowload(){
        imgFondoprogres.setVisibility(View.VISIBLE);
        imgDownload.setVisibility(View.VISIBLE);
    }

    private void hideDowload(){
        imgFondoprogres.setVisibility(View.GONE);
        imgDownload.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.view_footer) {
            onClickCheck();
        }else if(i == R.id.img_close){
            onClickClose();
        }else if(i == R.id.img_download){
            onClickDownload();
        }else if(i == R.id.img_fondo){
            onClickArchivo();
        }
    }

    private void onClickArchivo() {
        listener.onClickArchivo(repositorioFileUi);
    }

    private void onClickDownload() {
        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.CONECTANDO_SERVER);
        listener.onClickDownload(repositorioFileUi);
        setupEstado(this.repositorioFileUi.getEstadoFileU());
    }

    private void onClickClose() {
        hiProgress();
        showDowload();
        listener.onClickClose(repositorioFileUi);
    }

    private void onClickCheck() {
        if(repositorioFileUi.isSelect()){
            repositorioFileUi.setSelect(false);
        }else {
            repositorioFileUi.setSelect(true);
        }

        setupSelect(repositorioFileUi.isSelect());
        listener.onClickCheck(repositorioFileUi);

    }

    public void count(int count) {
        if (progressSucces.getVisibility() == View.GONE) {
            progressSucces.setVisibility(View.VISIBLE);
        }
        if (progressBar2.getVisibility() == View.VISIBLE) {
            progressBar2.setVisibility(View.GONE);
        }
        if (imgFondoprogres.getVisibility() == View.GONE) {
            imgFondoprogres.setVisibility(View.VISIBLE);
        }
        if (imgClose.getVisibility() == View.GONE) {
            imgClose.setVisibility(View.VISIBLE);
        }
        if (imgDownload.getVisibility() == View.VISIBLE) {
            imgDownload.setVisibility(View.GONE);
        }
        progressSucces.setProgress(count);

    }

    public RepositorioFileUi getRepositorioFileUi() {
        return repositorioFileUi;
    }
}