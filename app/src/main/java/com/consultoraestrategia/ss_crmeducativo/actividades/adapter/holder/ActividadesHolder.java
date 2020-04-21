package com.consultoraestrategia.ss_crmeducativo.actividades.adapter.holder;

import android.graphics.Color;

import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.actividades.adapter.RecursosAdapter;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ActividadesUi;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.EEstado;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 08/02/2018.
 */

public class ActividadesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static String TAG = ActividadesHolder.class.getSimpleName();

    @BindView(R.id.textView59)
    TextView textView59;
    @BindView(R.id.txt_num_actividad)
    TextView txtnum_actividad;
    @BindView(R.id.txt_validacion)
    TextView txtValidacion;
    @BindView(R.id.Checked_validacion)
    ImageView CheckedValidacion;
    @BindView(R.id.txt_actividad_det)
    TextView txtactividad;
    @BindView(R.id.txt_tiempo_act_det)
    TextView txttiempo_act;
    @BindView(R.id.txt_tipo_act_det)
    TextView txtTipoActDet;
    @BindView(R.id.txt_secuencia_act_det)
    TextView txtSecuenciaActDet;
    @BindView(R.id.txt_descripcion_act_det)
    TextView txtDescripcionActDet;
    @BindView(R.id.rv_act_recursos)
    RecyclerView rvActRecursos;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.txt_tiempo)
    TextView txtTiempo;
    @BindView(R.id.img_secuencia)
    ImageView imgSecuencia;
    @BindView(R.id.txt_secuencia)
    TextView txtSecuencia;
    @BindView(R.id.img_check)
    ImageView imgCheck;
    private final static String[] estado = {"Hecho", "Pendiente"};
    private final static int[] colorId = {R.color.md_blue_500, R.color.md_red_500};
    private ActividadesUi actividadesUi;
    private ActividadListener actividadListener;

    public ActividadesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        imgCheck.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }


    public void bind(final ActividadesUi actividadesUi, int position, ActividadListener actividadListener, DownloadItemListener downloadItemListener) {
        this.actividadesUi = actividadesUi;
        this.actividadListener = actividadListener;
        txtactividad.setText(actividadesUi.getNombreActividad());
        txtnum_actividad.setText(String.valueOf(position + 1));
        txttiempo_act.setText(String.valueOf(actividadesUi.getTiempoActividadDetalle() + "min"));
        txtTiempo.setText(String.valueOf(actividadesUi.getTiempoActividadDetalle()));
        txtTipoActDet.setText(String.valueOf("- " + actividadesUi.getNombreaActividadDetalle()));
        txtSecuenciaActDet.setText(String.valueOf("- " + actividadesUi.getSecuenciaNombre()));
        txtDescripcionActDet.setText(actividadesUi.getDescripcionActividad());
        txtSecuencia.setText(actividadesUi.getSecuenciaNombre());
        switch (actividadesUi.geteSecuencia()) {
            case Cierre:
                imgSecuencia.setBackground(ContextCompat.getDrawable(imgSecuencia.getContext(), R.drawable.ic_cierre));
                break;
            case Inicio:
                imgSecuencia.setBackground(ContextCompat.getDrawable(imgSecuencia.getContext(), R.drawable.ic_power_button));
                break;
            case Desarrollo:
                imgSecuencia.setBackground(ContextCompat.getDrawable(imgSecuencia.getContext(), R.drawable.ic_desarrollo));
                break;
        }

        if (textView59.isSelected()) {
            txtDescripcionActDet.setVisibility(View.GONE);
            rvActRecursos.setVisibility(View.GONE);
            textView59.setSelected(true);
        } else {
            rvActRecursos.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(actividadesUi.getDescripcionActividad())){
                txtDescripcionActDet.setVisibility(View.VISIBLE);
            }else {
                txtDescripcionActDet.setVisibility(View.GONE);
            }
            textView59.setSelected(false);
        }

        //region Estado Actividad
        if (actividadesUi.geteEstado() == EEstado.Hecho)
            estadoEcho();
        else estadoPendiente();

        //endregion

        rvActRecursos.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rvActRecursos.setHasFixedSize(false);
        rvActRecursos.setNestedScrollingEnabled(false);
        ArrayList<Object> objects = new ArrayList<>();
        objects.addAll(actividadesUi.getRecursosUiList());
        objects.addAll(actividadesUi.getSubRecursosUiList());
        RecursosAdapter recursosAdapter = new RecursosAdapter(objects, position, downloadItemListener);
        rvActRecursos.setAdapter(recursosAdapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_check:
                clicImgeCheck();
                break;
           default:
                ocultar();
                break;
        }
    }

    private void clicImgeCheck() {
        if (CheckedValidacion.isSelected()) {
            actividadesUi.seteEstado(EEstado.Pendiente);
            CheckedValidacion.setSelected(false);
            estadoPendiente();

        } else {
            actividadesUi.seteEstado(EEstado.Hecho);
            CheckedValidacion.setSelected(true);
            estadoEcho();
        }
        if (actividadListener != null) actividadListener.onClickActividad(actividadesUi);
    }

    private void ocultar() {
        if (!textView59.isSelected()) {
            rvActRecursos.setVisibility(View.GONE);
            txtDescripcionActDet.setVisibility(View.GONE);
            textView59.setSelected(true);
        } else {
            rvActRecursos.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(actividadesUi.getDescripcionActividad())){
                txtDescripcionActDet.setVisibility(View.VISIBLE);
            }else {
                txtDescripcionActDet.setVisibility(View.GONE);
            }
            textView59.setSelected(false);
        }
    }

    private void estadoEcho() {
        try {
            txtValidacion.setText(estado[0]);
            txtValidacion.setTextColor(ContextCompat.getColor(txtValidacion.getContext(), colorId[0]));
            imgCheck.setBackground(ContextCompat.getDrawable(imgCheck.getContext(), R.drawable.ic_check));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void estadoPendiente() {
        try {
            txtValidacion.setText(estado[1]);
            txtValidacion.setTextColor(ContextCompat.getColor(txtValidacion.getContext(), colorId[1]));
            imgCheck.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public RecyclerView getRvActRecursos() {
        return rvActRecursos;
    }
}
