package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.adapter.holder;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.listener.CuentaCorrienteListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class CuentaCorrienteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtDescripcionPago)
    TextView txtDescripcionPago;
    @BindView(R.id.txtFechaPago)
    TextView txtFechaPago;
    @BindView(R.id.txtTitle)
    TextView txtMontoPago;
    @BindView(R.id.txtMontoCuotas)
    TextView txtMontoCuotas;
    @BindView(R.id.contentCard)
    ConstraintLayout contentCard;


    public CuentaCorrienteViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(CuentaCoUI cuentaCorriente, CuentaCorrienteListener listener, int position) {

        double debito = cuentaCorriente.getDebito();
        double credito = cuentaCorriente.getCredito();
        String fecha = cuentaCorriente.getFecha();
        txtDescripcionPago.setText(cuentaCorriente.getGlosa());


        if (debito == 0) {
            txtMontoCuotas.setText("--");

        } else {
            txtMontoCuotas.setText(cuentaCorriente.getDebito() + "");
        }

        if (credito == 0) {
            txtMontoPago.setText("--");
        } else {
            txtMontoPago.setText(cuentaCorriente.getCredito() + "");
        }


        if ((position % 2) == 0) {
            try{
                contentCard.setBackgroundColor(Color.parseColor("#dadada"));
            }catch (Exception e){
                e.printStackTrace();
            }

        } else {
            try{
                contentCard.setBackgroundColor(Color.parseColor("#ffffff"));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        Date date = new Date(fecha);
        String fechaLarge = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(date);
        txtFechaPago.setText(fechaLarge);

    }


}
