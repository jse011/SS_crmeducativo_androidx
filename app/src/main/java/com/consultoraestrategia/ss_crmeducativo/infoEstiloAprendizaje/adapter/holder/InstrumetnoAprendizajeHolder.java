package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.holder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.InstrumentoObservadoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstrumetnoAprendizajeHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.img_aprendizaje)
    ImageView imgAprendizaje;
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.txt_fecha)
    TextView txtFecha;
    @BindView(R.id.txt_tipoAp_one)
    TextView txtTipoApOne;
    @BindView(R.id.txt_tipoAp_two)
    TextView txtTipoApTwo;
    public InstrumetnoAprendizajeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(InstrumentoObservadoUi instrumentoObservadoUi){
        txtTitulo.setText(instrumentoObservadoUi.getNombreEvaluacion());
        txtFecha.setText(instrumentoObservadoUi.getFechaEvaluacion());

        if(instrumentoObservadoUi.getDimensionObservadaUiPrincipalOne()!=null)inciarDimension(instrumentoObservadoUi.getDimensionObservadaUiPrincipalOne(), txtTipoApTwo);
        if(instrumentoObservadoUi.getDimensionObservadaUiPrincipalTwo()!=null)inciarDimension(instrumentoObservadoUi.getDimensionObservadaUiPrincipalTwo(), txtTipoApOne);

        Glide.with(itemView.getContext())
                .load(instrumentoObservadoUi.getIcono())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                .into(imgAprendizaje);
    }

    private void inciarDimension(DimensionObservadaUi dimensionObservadaUi, TextView textView){
        DimensionUi dimensionUi = dimensionObservadaUi.getDimensionUi();
        if(dimensionUi==null)return;
        DimensionUi.EDimension eDimension =dimensionUi.geteDimension();
        int colorRBG;
        Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_radius_spinner);
        try {
            int colorFondo = Color.parseColor(dimensionUi.getColor());
            circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
            int colorTexto = R.color.white;
            colorRBG = ContextCompat.getColor(itemView.getContext(), colorTexto);
        }catch (Exception e){
            e.printStackTrace();
            colorRBG = Color.WHITE;
        }
        textView.setBackground(circle);
        textView.setTextColor(colorRBG);
        String descripcion = dimensionUi.getSigla() +":  "+ dimensionObservadaUi.getPorcentaje() + "%";
        textView.setText(descripcion);
    }

}
