package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUploadEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class RepositorioUpdateHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imgFondo;
    private ImageView imgFondoProgres;
    private View viewFooter;
    private ImageView imgExtencion;
    private ImageView imgRemover;
    private TextView txtFile;
    private ProgressBar progressBar2;
    private CircularProgressBar progressSucces;
    private ImageView imgClose;
    private ImageView imgUpdate;
    private UpdateRepositorioFileUi updateRepositorioFileUi;
    private RepositorioItemUpdateListener listener;


    public RepositorioUpdateHolder(View itemView) {
        super(itemView);
        imgFondo =  itemView.findViewById(R.id.img_fondo);
        imgFondoProgres =  itemView.findViewById(R.id.img_fondo_progres);
        viewFooter =  itemView.findViewById(R.id.view_footer);
        imgExtencion =  itemView.findViewById(R.id.img_extencion);
        imgRemover =  itemView.findViewById(R.id.img_remover);
        txtFile =  itemView.findViewById(R.id.txt_file);
        progressBar2 =  itemView.findViewById(R.id.progressBar2);
        progressSucces =  itemView.findViewById(R.id.progress_succes);
        imgClose =  itemView.findViewById(R.id.img_close);
        imgUpdate =  itemView.findViewById(R.id.img_update);
        imgUpdate.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        imgRemover.setOnClickListener(this);
        //imgFondo.setOnClickListener(this);
    }


    public void bind(UpdateRepositorioFileUi updateRepositorioFileUi, RepositorioItemUpdateListener repositorioItemUpdateListener) {
        this.updateRepositorioFileUi = updateRepositorioFileUi;
        this.listener = repositorioItemUpdateListener;
        txtFile.setText(updateRepositorioFileUi.getNombreArchivo());
        setupEstado(updateRepositorioFileUi.getUploadEstadoFileU());
        setupIcono(updateRepositorioFileUi.getTipoFileU());
        showImagen(updateRepositorioFileUi.getTipoFileU());
        progressSucces.setProgress(updateRepositorioFileUi.getCount());
    }


    private void setupEstado(RepositorioUploadEstadoFileU estadoFileU) {
        hideError();
        hideUpload();
        hiProgress();
        hiProgressSuccess();
        switch (estadoFileU){
            case SIN_SUBIR:
                showUpload();
                break;
            case CONECTANDO_SERVER:
                showProgress();
                break;
            case ENPROCESO_SUBIDA:
                showProgressSuccess();
                break;
            case ERROR_SUBIDA:
                showError();
                break;
            case SUDIDA_COMPLETA:

                break;
        }
    }

    private void setupIcono(RepositorioTipoFileU tipoFileU) {
        switch (tipoFileU){
            case PDF:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.icon_file_pdf));
                break;
            case AUDIO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_aud_min));
                break;
            case VIDEO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_vid_min));
                break;
            case IMAGEN:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_img_min));
                break;
            case VINCULO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_link_min));
                break;
            case DOCUMENTO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.icon_file_doc));
                break;
            case DIAPOSITIVA:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.icon_file_ppt));
                break;
            case HOJA_CALCULO:
                imgExtencion.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.icon_file_xls));
                break;
        }

    }

    private void showImagen(RepositorioTipoFileU tipoFileU) {

        switch (tipoFileU){
            case IMAGEN:
                String url="";
                if(updateRepositorioFileUi!=null)url=updateRepositorioFileUi.getPath();
                    Glide.with(itemView.getContext())
                            .load(url)
                            .apply(
                                    Utils.getGlideRequestOptions(R.drawable.repositorio_file_background)
                                            .override(50, 50)
                            )
                            .into(imgFondo);

                break;
            default:
                imgFondo.setImageBitmap(null);
                imgFondo.destroyDrawingCache();
                break;
        }


    }

    private void showProgress(){
        progressBar2.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.VISIBLE);
        imgFondoProgres.setVisibility(View.VISIBLE);
    }

    private void hiProgress(){
        progressBar2.setVisibility(View.GONE);
        imgClose.setVisibility(View.GONE);
        imgFondoProgres.setVisibility(View.GONE);
    }

    private void showProgressSuccess(){
        progressSucces.setVisibility(View.VISIBLE);
        imgClose.setVisibility(View.VISIBLE);
        imgFondoProgres.setVisibility(View.VISIBLE);
    }

    private void hiProgressSuccess(){
        progressSucces.setVisibility(View.GONE);
        imgClose.setVisibility(View.GONE);
        imgFondoProgres.setVisibility(View.GONE);
    }


    private void showError(){
        imgFondoProgres.setVisibility(View.VISIBLE);
        imgUpdate.setVisibility(View.VISIBLE);
        imgUpdate.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_cached_repositorio));
    }

    private void hideError(){
        imgFondoProgres.setVisibility(View.GONE);
        imgUpdate.setVisibility(View.GONE);
    }

    private void showUpload(){
        imgFondoProgres.setVisibility(View.VISIBLE);
        imgUpdate.setVisibility(View.VISIBLE);
        imgUpdate.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_upload_library));
    }

    private void hideUpload(){
        imgFondoProgres.setVisibility(View.GONE);
        imgUpdate.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
       if(i == R.id.img_close){
            onClickClose();
        }else if(i == R.id.img_update){
            onClickUpload();
        }else if(i == R.id.img_remover){
            onClickRemover();
        }else if(i==R.id.img_fondo){
           onClickArchivo();
       }
    }

    private void onClickArchivo() {
        listener.onClickArchivo(updateRepositorioFileUi);
    }

    private void onClickUpload() {
        updateRepositorioFileUi.setUploadEstadoFileU(RepositorioUploadEstadoFileU.CONECTANDO_SERVER);
        listener.onClickUpload(updateRepositorioFileUi);
        setupEstado(updateRepositorioFileUi.getUploadEstadoFileU());
    }

    private void onClickRemover() {
        listener.onClickRemover(updateRepositorioFileUi);
    }

    private void onClickClose() {
        hiProgress();
        showUpload();
        listener.onClickClose(updateRepositorioFileUi);
    }


    public void count(int count) {
        Log.d("RepositorioRemoteData", "count: " + count);
        progressSucces.setProgress(count);
    }

}