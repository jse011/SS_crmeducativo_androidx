package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.adapters.viewholders;

import android.annotation.SuppressLint;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.adapters.RecursosTareaAdapter;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.adapters.TareasAdapter;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.listeners.TareasUIListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public class TareasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = TareasViewHolder.class.getSimpleName();

//    @BindView(R.id.txtNroTarea)
//    TextView txtNroTarea;
    @BindView(R.id.txtEstadoTarea)
    TextView txtEstadoTarea;
    @BindView(R.id.txtFechaEntrege)
    TextView txtFechaEntrega;
    @BindView(R.id.txtTitulo)
    TextView txtTituloTarea;
    @BindView(R.id.txtContenidoTarea)
    TextView txtContenidoTarea;
    @BindView(R.id.btnAbrir)
    Button btnAbrir;
    @BindView(R.id.txtCreador)
    TextView txtNombreCreador;
    @BindView(R.id.txtFechaCreacion)
    TextView txtFechaCreacion;
    @BindView(R.id.rvRecurso)
    RecyclerView rvRecurso;
    @BindView(R.id.spinne)
    ImageView spinne;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.cantNoEvaluados)
    TextView cantidadNoEvaluados;
    @BindView(R.id.cantEvaluados)
    TextView cantidadEvaluados;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.cantidad)
    ConstraintLayout constraintLayoutCantidad;
    @BindView(R.id.txtNroTarea)
    ImageView txtTarea;
    @BindView(R.id.constraintLayoutContenido)
    ConstraintLayout constraintLayoutContenido;
    @BindView(R.id.contentVacio)
    ConstraintLayout contentVacio;


    private TareasUIListener listener;
    private TareasUI tareasUI;
    private ParametroDisenioUi parametroDisenioUi;
    private boolean status = true;

    public TareasViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        txtEstadoTarea.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);
        constraintLayoutContenido.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void bind(final TareasUI tareasUI, final TareasUIListener listener, ImageLoader imageLoader, final int position, final TareasAdapter.SpinnerListener spinnerListener, final HeaderTareasAprendizajeUI headerTareasAprendizajeUI, ParametroDisenioUi parametroDisenioUi) {
        this.listener = listener;
        this.tareasUI = tareasUI;
        this.parametroDisenioUi =parametroDisenioUi;
        if (tareasUI != null) {
//            if (tareasUI.isDocente()) {
//                spinne.setVisibility(View.VISIBLE);
//                txtEstadoTarea.setVisibility(View.VISIBLE);
//            } else {
//                spinne.setVisibility(View.GONE);
//                txtEstadoTarea.setVisibility(View.GONE);
//            }

            if(tareasUI.getRecursosUIList().size()>0){
                contentVacio.setVisibility(View.GONE);
                rvRecurso.setVisibility(View.VISIBLE);
            }else{
                contentVacio.setVisibility(View.VISIBLE);
                rvRecurso.setVisibility(View.GONE);
            }


            rvRecurso.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            RecursosTareaAdapter recursosTareaAdapter = new RecursosTareaAdapter(new ArrayList<RecursosUI>(), tareasUI, 0, null);
            rvRecurso.setAdapter(recursosTareaAdapter);


            if (tareasUI.getRecursosUIList() != null)recursosTareaAdapter.setRecursos(tareasUI.getRecursosUIList());
            txtTituloTarea.setText(tareasUI.getTituloTarea());

            if (!(tareasUI.getFechaLimite() == 0D)) {
                String hora = Utils.changeTime12Hour(tareasUI.getHoraEntrega());
                String fecha_entrega = Utils.f_fecha_letras(tareasUI.getFechaLimite());
                if(!TextUtils.isEmpty(hora)){
                    fecha_entrega = fecha_entrega + " " + hora;
                }
                txtFechaEntrega.setText(fecha_entrega);
            } else {
                txtFechaEntrega.setText(R.string.ItemTareas_Tarea_FechaLimite);
            }

            String estado = tareasUI.getEstado().getNombre();
            txtEstadoTarea.setText(estado.toUpperCase());

            if (tareasUI.getEstado() == TareasUI.Estado.Publicado) {
                txtEstadoTarea.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_light_blue_600));
            }else {
                txtEstadoTarea.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_600));
            }

            txtNombreCreador.setText(tareasUI.getPersonaPuclicacion());
            txtFechaCreacion.setText(Utils.f_fecha_letras(tareasUI.getFechaCreacionTarea()));

            if (!TextUtils.isEmpty(tareasUI.getDescripcion()))txtContenidoTarea.setText(tareasUI.getDescripcion());
            else txtContenidoTarea.setText("No existe descripcion de Tarea");

            if(tareasUI.getRubroEvalProcesoUi()==null){
                constraintLayoutCantidad.setVisibility(View.GONE);
                constraintLayoutContenido.setOnClickListener(null);
                txtTarea.setImageResource(R.drawable.report);
                //txtTarea.setBackgroundColor((Color.parseColor(parametroDisenioUi.getColor1())));
                txtTarea.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.teal));

            }else {
                constraintLayoutCantidad.setVisibility(View.VISIBLE);
                String string = tareasUI.getRubroEvalProcesoUi().getCantidadEvaluados();
                String[] parts = string.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                int cantidadEvaluadoss = Integer.parseInt(part1);
                int cantidadNoEval = Integer.parseInt(part2);
                cantidadEvaluados.setText(String.valueOf(cantidadEvaluadoss));
                cantidadNoEvaluados.setText(String.valueOf(cantidadNoEval));
                txtTarea.setImageResource(R.drawable.ic_icon_matrix_white);

                //txtTarea.setBackgroundColor((Color.parseColor(parametroDisenioUi.getColor1())));
                txtTarea.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.teal));

            }


            spinne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(spinne.getContext(), view);
                    DroppyMenuPopup.Builder builder = droppyBuilder
                            //.fromMenu(R.menu.droppy)
                            .addMenuItem(new DroppyMenuItem("Editar"))
                            .addMenuItem(new DroppyMenuItem("Eliminar"))
                            .addMenuItem(new DroppyMenuItem("Notificar por Mensaje"))
                            .triggerOnAnchorClick(false)
                            .setOnClick(new DroppyClickCallbackInterface() {
                                @Override
                                public void call(View v, int positionMenu) {
                                    Log.d("positionMenu:", String.valueOf(positionMenu));
                                    switch (positionMenu) {
                                        case 0:
                                            listener.onOpTareaEditClicked(tareasUI, headerTareasAprendizajeUI);
                                            spinnerListener.onUpdateTareaListener(tareasUI);

                                            break;
                                        case 1:
                                            listener.onOpTareaDelteClicked(tareasUI);
                                            spinnerListener.onEliminarListener(tareasUI);
                                            break;
                                        case 2:
                                            listener.onOpNotificarTareasClicked(tareasUI, headerTareasAprendizajeUI);
                                            break;
                                        case 3:
                                            listener.onOpCrearRubricaClicked(tareasUI, headerTareasAprendizajeUI);
                                            break;
                                        case 4:
                                            listener.onOpCrearRubroClicked(tareasUI, headerTareasAprendizajeUI);
                                            break;
                                    }
                                }
                            })
                            .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                                @Override
                                public void call() {
                                    Log.d(TAG, "onDismissCallback");
                                }
                            })
                            .setPopupAnimation(new DroppyFadeInAnimation())
                            .setXOffset(5)
                            .setYOffset(5);

                    if(tareasUI.getRubroEvalProcesoUi()==null&& tareasUI.isCalendarioEditar()){
                        builder .addMenuItem(new DroppyMenuItem("Crear Rúbrica"))
                                .addMenuItem(new DroppyMenuItem("Crear Rubro"));
                    }
                    DroppyMenuPopup droppyMenu = builder.build();
                    droppyMenu.show();
                }
            });
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtEstadoTarea:
                Log.d(TAG,"estado: " + tareasUI.getEstado());
                if(tareasUI.isCalendarioVigente()){
                    String estado = "";
                    Log.d(TAG,"estado: " + tareasUI.getEstado());
                    switch (tareasUI.getEstado()){
                        case Creado:
                            estado = TareasUI.Estado.Publicado.getNombre().toUpperCase();
                            txtEstadoTarea.setText(estado.toUpperCase());
                            txtEstadoTarea.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_600));
                            break;
                        case Publicado:
                            estado =  TareasUI.Estado.Creado.getNombre().toUpperCase();
                            txtEstadoTarea.setText(estado.toUpperCase());
                            txtEstadoTarea.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_light_blue_600));
                            break;
                        default:
                            txtEstadoTarea.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_600));
                            break;
                    }
                    listener.onClikEstado(tareasUI);
                }

                break;
            case R.id.constraintLayout:
                //verMas();
                break;
            case R.id.constraintLayoutContenido:
                listener.onClikRubroTarea(tareasUI);
                break;
        }
    }

    private void verMas() {
        if(status){
            status = false;
            constraintLayoutContenido.setVisibility(View.GONE);
        }else {
            status = true;
            constraintLayoutContenido.setVisibility(View.VISIBLE);
        }
    }

}
