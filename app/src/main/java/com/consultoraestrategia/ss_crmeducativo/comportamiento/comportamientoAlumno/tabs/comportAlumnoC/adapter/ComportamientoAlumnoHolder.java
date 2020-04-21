package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui.ListenerComportAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComportamientoAlumnoHolder extends AbstractViewHolder implements View.OnClickListener {


    @BindView(R.id.img_tipo)
    ImageView img_tipo;
    @BindView(R.id.fecha)
    TextView fecha;
    @BindView(R.id.txtdescriocion)
    TextView descripcion;
    @BindView(R.id.txt_cantidad_tipo)
    TextView txtCantidadTipo;
    @BindView(R.id.btnopciones)
    ImageView btnopciones;
    ListenerComportAlumnoC listener;
    ComportamientoUi comportamientoUi;

    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.txtvacioArchivos)
    TextView txtvacioArchivos;
    @BindView(R.id.recyclerArchivos)
    RecyclerView recyclerArchivos;
    @BindView(R.id.txt_comportamiento_descripcion)
    TextView txtComportamientoDescripcion;
    @BindView(R.id.txt_comportamiento_titulo)
    TextView txtComportamientoTitulo;
    private DownloadAdapter recursosCasosAdapter;
    private DownloadItemListener downloadItemListener;
    private List<ArchivoUi> recursosUIList;


    public ComportamientoAlumnoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        btnopciones.setOnClickListener(this);
    }

    public void bind(final ComportamientoUi comportamientoUi, final ListenerComportAlumnoC listener,DownloadItemListener downloadItemListener ){
        this.comportamientoUi=comportamientoUi;
        this.downloadItemListener = downloadItemListener;
        this.listener=listener;
        int ruta=R.drawable.policeman;
        if (comportamientoUi.getTipoConducta()!=null){

            switch (comportamientoUi.getTipoConducta().getId()){
                case 541:
                    ruta=R.drawable.medal;
                    break;
                case 542:
                    ruta= R.drawable.policeman;
                    break;
                default:
                    ruta= R.drawable.ic_graduacion;
                    break;
            }

            String time = Utils.f_fecha_letras(comportamientoUi.getFecha()) + " " + Utils.getFechaHora(comportamientoUi.getFecha());
            fecha.setText(time);
            descripcion.setText(comportamientoUi.getDescripcion());
            if(comportamientoUi.getTipoConducta().getNombre()!=null)nombre.setText(comportamientoUi.getTipoConducta().getNombre().toUpperCase());
            if(comportamientoUi.getTipoComportamientoUi()!=null){
                txtCantidadTipo.setText(String.valueOf(comportamientoUi.getTipoComportamientoUi().getCantidad()));
                txtComportamientoTitulo.setText(comportamientoUi.getTipoComportamientoUi().getTitulo());
                txtComportamientoDescripcion.setText(comportamientoUi.getTipoComportamientoUi().getDescripcion());
            }
        }else {
            fecha.setText("");
            descripcion.setText("");
            txtCantidadTipo.setText("");
            txtComportamientoTitulo.setText("");
            txtComportamientoDescripcion.setText("");
        }
        img_tipo.setBackgroundResource(ruta);


        recyclerArchivos.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        recursosCasosAdapter = new DownloadAdapter(downloadItemListener, recyclerArchivos);
        recyclerArchivos.setAdapter(recursosCasosAdapter);
        recyclerArchivos.setHasFixedSize(false);
        recyclerArchivos.setNestedScrollingEnabled(false);
        this.recursosUIList = comportamientoUi.getArchivoUiList();
        if (recursosUIList != null)recursosCasosAdapter.setList(new ArrayList<RepositorioFileUi>(comportamientoUi.getArchivoUiList()));
        recyclerArchivos.setHasFixedSize(true);

        if(comportamientoUi.getArchivoUiList()==null||
                comportamientoUi.getArchivoUiList().isEmpty()){
            recyclerArchivos.setVisibility(View.GONE);
            txtvacioArchivos.setVisibility(View.VISIBLE);
        }else {
            recyclerArchivos.setVisibility(View.VISIBLE);
            txtvacioArchivos.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnopciones:
                listener.onclickOpciones(comportamientoUi, v, btnopciones.getContext());
                break;
        }

    }

    public DownloadAdapter getRecursosCasosAdapter() {
        return recursosCasosAdapter;
    }

    public List<ArchivoUi> getRecursosUIList() {
        return recursosUIList;
    }
}
