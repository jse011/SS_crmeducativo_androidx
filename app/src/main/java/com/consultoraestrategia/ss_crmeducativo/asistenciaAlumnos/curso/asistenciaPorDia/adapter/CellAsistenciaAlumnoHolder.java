package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CellAsistenciaAlumnoHolder extends AbstractViewHolder {

    @BindView(R.id.img_valor_asistencia)
    CircleImageView img_valor_asistencia;
    ValorTipoNotaUi valorTipoNotaUi;

    public CellAsistenciaAlumnoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(ValorTipoNotaUi valorTipoNota){
        this.valorTipoNotaUi=valorTipoNota;

        if(valorTipoNotaUi.isSelected()){
            seleccionar();
            AsistenciaUi asistenciaUi= valorTipoNotaUi.getAsistenciaUi();
            asistenciaUi.setViewHolder(this);
        }
        else deseleccionar();

        String url=valorTipoNotaUi.getIcono();
        if (!TextUtils.isEmpty(url)) {
            try{
                Glide.with(itemView.getContext())
                        .load(url)
                        .apply(Utils.getGlideRequestOptions())
                        .into(img_valor_asistencia);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public void seleccionar(){
        Log.d(getClass().getSimpleName() , "true");
        valorTipoNotaUi.setSelected(true);
        img_valor_asistencia.setAlpha(1.0f);
    }
    public void deseleccionar(){
        Log.d(getClass().getSimpleName() , "false");
        valorTipoNotaUi.setSelected(false);
        img_valor_asistencia.setAlpha(0.1f);
    }




}
