package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.holder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.ResultadoEstiloAprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultadoEstiloAprendizajeHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_cabecera)
    TextView txtCabecera;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.rc_caracteristica)
    RecyclerView rcCaracteristica;

    public ResultadoEstiloAprendizajeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(DimensionObservadaUi dimensionObservada){
        String nombre = dimensionObservada.getDimensionUi()==null ? "" : dimensionObservada.getDimensionUi().getNombre();

        String cabecera = dimensionObservada.getPoscion() + ". " + nombre + ": " + dimensionObservada.getPorcentaje() + "%";
        txtCabecera.setText(cabecera);
        if(dimensionObservada.getDimensionUi()!=null)txtDescripcion.setText(dimensionObservada.getDimensionUi().getDescripcion());
        inciarDimension(dimensionObservada, txtCabecera);
        List<Object> objectList = new ArrayList<>();
        if(dimensionObservada.getDimensionUi().getCaracteristicaUiList() != null)objectList.addAll(dimensionObservada.getDimensionUi().getCaracteristicaUiList());
        ResultadoEstiloAprendizajeAdapter resultadoEstiloAprendizajeAdapter = new ResultadoEstiloAprendizajeAdapter(objectList);
        rcCaracteristica.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rcCaracteristica.setAdapter(resultadoEstiloAprendizajeAdapter);
    }

    private void inciarDimension(DimensionObservadaUi dimensionObservadaUi, TextView textView){
        DimensionUi dimensionUi = dimensionObservadaUi.getDimensionUi();
        int colorRBG  = Color.WHITE;;
        Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_radius_spinner);
        try {
            if(dimensionUi!=null){
                int colorFondo = Color.parseColor(dimensionUi.getColor());
                circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
                int colorTexto = R.color.white;
                colorRBG = ContextCompat.getColor(itemView.getContext(), colorTexto);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        textView.setBackground(circle);
        textView.setTextColor(colorRBG);
    }


}
