package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderRecursos extends RecyclerView.ViewHolder {

    @BindView(R.id.img_recurso)
    TextView imgrecurso;
    @BindView(R.id.txt_descripcion_rec)
    TextView txtdescripcion_rec;
    @BindView(R.id.txt_extencion_rec)
    TextView txtextencion_rec;


    public ViewHolderRecursos(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(final RecursoDidacticoEventoC recursoDidacticoEvento){
        final Context mcontex = itemView.getContext();
        String extencion = "Archivo ";
        Drawable imgdocumento;
        View.OnClickListener onClickListener;

        //region Traer la imagen  y las inteciones del documento
        switch (recursoDidacticoEvento.getTipoId()){
            case 379:
                extencion += "Video";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_vid_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + recursoDidacticoEvento.getDescripcion()));
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(recursoDidacticoEvento.getDescripcion()));
                        try {
                            mcontex.startActivity(appIntent);
                        } catch (ActivityNotFoundException ex) {
                            mcontex.startActivity(webIntent);
                        }
                    }
                };
                break;
            case 380:
                extencion += "Vinculo";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_link_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        Uri uri = Uri.parse(recursoDidacticoEvento.getDescripcion());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        mcontex.startActivity(intent);
                    }
                };
                break;
            case 397:
                extencion += "docx";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_doc_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
            case 398:
                extencion += "Imagen";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_img_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
            case 399:
                extencion += "Audio";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_aud_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
            case 400:
                extencion += "xlsx";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_xls_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
            case 401:
                extencion += "pptx";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_ppt_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
            case 402:
                extencion += "pdf";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_pdf_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
            //case 403:
            default:
                extencion += "Materiales";
                imgdocumento = ContextCompat.getDrawable(mcontex, R.drawable.ext_other_min);
                onClickListener  = new View.OnClickListener() {
                    public void onClick(View v)
                    {

                    }
                };
                break;
        }
        //endregion


        itemView.setOnClickListener(onClickListener);
        txtdescripcion_rec.setText(recursoDidacticoEvento.getTitulo());
        txtextencion_rec.setText(extencion);
        imgrecurso.setBackground(imgdocumento);
    }

}
