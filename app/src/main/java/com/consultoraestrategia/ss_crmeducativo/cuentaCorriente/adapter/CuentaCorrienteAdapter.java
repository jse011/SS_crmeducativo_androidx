package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.adapter.holder.CuentaCorrienteViewHolder;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.listener.CuentaCorrienteListener;

import java.util.List;


/**
 * Created by @stevecampos on 18/09/2017.
 */

public class CuentaCorrienteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CuentaCorrienteAdapter.class.getSimpleName();



    private List<CuentaCoUI> cuentaCorrientes;
    private CuentaCorrienteListener listener;


    public CuentaCorrienteAdapter(List<CuentaCoUI> cuentaCorrientes) {
        this.cuentaCorrientes = cuentaCorrientes;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuenta_corriente, parent, false);
        return new CuentaCorrienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CuentaCoUI cuentaCorriente = cuentaCorrientes.get(position);
        ((CuentaCorrienteViewHolder) holder).bind(cuentaCorriente, listener, position);
    }

    @Override
    public int getItemCount() {
        return cuentaCorrientes.size();
    }

    public void setCuentaCorrientes(List<CuentaCoUI> cuentaCorrienteList) {
        this.cuentaCorrientes.clear();
        this.cuentaCorrientes.addAll(cuentaCorrienteList);
        notifyDataSetChanged();
    }


}
