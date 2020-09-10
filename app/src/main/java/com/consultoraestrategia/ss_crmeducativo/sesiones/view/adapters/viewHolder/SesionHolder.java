package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.AdapterSesiones_v2;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.AdapterUnidades;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.EstadoSesiones;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SesionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.cant_Recursos)
    TextView cantRecursos;
    @BindView(R.id.fondo)
    TextView fondo;
    @BindView(R.id.txt_tiempo)
    TextView txttiempo;
    @BindView(R.id.txt_tiempo_medida)
    TextView txttiempo_medida;
    @BindView(R.id.lin_titulo_sesion)
    View lin_titulosesion;
    @BindView(R.id.txt_titulo_sesion)
    TextView txt_titulosesion;
    @BindView(R.id.txt_num_sesion)
    TextView txt_numsesion;
    @BindView(R.id.txt_fecha_sesion)
    TextView txt_fechasesion;
    @BindView(R.id.txt_tag)
    TextView txtTag;
    @BindView(R.id.cant_AlumnosEvaluados)
    TextView cantidadAlumnosEvaluados;
    @BindView(R.id.cardv_sesiones)
    CardView cardvSesiones;
    @BindView(R.id.contItemView)
    ConstraintLayout contItemView;
    @BindView(R.id.imageRubrica)
    ImageView imageRubrica;


    private SesionAprendizajeUi sesionAprendizaje;
    private int programaEducativoId;
    EstadoSesiones estadoSesiones = EstadoSesiones.CREADO;
    Calendar calendar;
    private AdapterUnidades.UnidadesListener listener;

    // Cada uno de los elementos de mi vista
    public SesionHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(AdapterUnidades.UnidadesListener listener, int vint_backgroudColor, SesionAprendizajeUi sesionAprendizaje, Calendar calendar, int programaEducativoId){
        this.programaEducativoId = programaEducativoId;
        this.listener = listener;
        this.calendar = calendar;
        txtTag.setOnClickListener(this);
        itemView.setOnClickListener(this);
        this.sesionAprendizaje = sesionAprendizaje;
        //region Cambiar el Color
        LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(itemView.getContext(), R.drawable.background_border_bottom_cian);
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.gradientDrawble);
        gradientDrawable.setColor(vint_backgroudColor);

        lin_titulosesion.setBackground(layerDrawable);

        txttiempo.setTextColor(vint_backgroudColor);
        txttiempo_medida.setTextColor(vint_backgroudColor);
        //endregion
        cantidadAlumnosEvaluados.setText(sesionAprendizaje.getCantidadEvaluadosSesion());
        if(TextUtils.isEmpty(sesionAprendizaje.getCantidadEvaluadosSesion())){
            imageRubrica.setVisibility(View.GONE);
        }else {
            imageRubrica.setVisibility(View.VISIBLE);
        }

        txt_numsesion.setText(sesionAprendizaje.getNroSesion()+"");
        txt_titulosesion.setText(sesionAprendizaje.getTitulo());
        txttiempo.setText(sesionAprendizaje.getHoras()+"");
        txttiempo_medida.setText("min.");

        //region Ocultar si es menor de cero
        if(sesionAprendizaje.getCantidad_recursos() <= 0){
            cantRecursos.setVisibility(View.GONE);
        }else{
            cantRecursos.setVisibility(View.VISIBLE);
        }
        //endregion

        switch (sesionAprendizaje.getEstadoEjecucionId()){
            case 315:
                estadoSesiones = EstadoSesiones.CREADO;
                break;
            case 316:
                estadoSesiones = EstadoSesiones.PROGRAMADO;
                break;
            case 317:
                estadoSesiones = EstadoSesiones.HECHO;
                break;
            case 318:
                estadoSesiones = EstadoSesiones.PENDIENTE;
                break;
        }

        Calendar fechaEjecucion = Calendar.getInstance();
        fechaEjecucion.setTimeInMillis(sesionAprendizaje.getFechaEjecucion());
        txt_fechasesion.setText(f_fecha_letras(fechaEjecucion));
        cantRecursos.setText(sesionAprendizaje.getCantidad_recursos()+"");





        int compareCalendar = compareTo( fechaEjecucion, calendar);

        Log.d(AdapterSesiones_v2.class.getSimpleName(), "sesionAprendizaje " + fechaEjecucion.getTimeInMillis() + " > "+ calendar.getTimeInMillis());
        Log.d(AdapterSesiones_v2.class.getSimpleName(), "estadoSesiones " + estadoSesiones.toString());

        if(compareCalendar <  0 &&estadoSesiones == EstadoSesiones.PROGRAMADO){
            onPendiente();
            listener.onChangeSesionListener();
        }

        if(compareCalendar >=  0 &&estadoSesiones == EstadoSesiones.PENDIENTE){
            onProgramado();
            listener.onChangeSesionListener();
        }
        cambiarTagColor();
    }

    private int compareTo(Calendar calendar1, Calendar calendar2) {
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar1.get(Calendar.DAY_OF_MONTH);

        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth2 = calendar2.get(Calendar.DAY_OF_MONTH);

        if(year == year2 && month == month2 && dayOfMonth == dayOfMonth2){
            return 0;
        }else if(year > year2 ){
            return 1;
        }else if(year == year2 && month > month2){
            return 1;
        }else if(year == year2 && month == month2 && dayOfMonth > dayOfMonth2){
            return 1;
        } else {
            return -1;
        }

    }

    public String f_fecha_letras(Calendar timesTamp) {
        String mstr_fecha = "";
        String[] vobj_days = {"Dom", "Lun", "Mart", "Mié", "Jue", "Vie", "Sáb"};
        String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};
        int year = timesTamp.get(Calendar.YEAR);
        int month = timesTamp.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = timesTamp.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = timesTamp.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1] + " " + dayOfMonth + " de " + vobj_Meses[month];
        return mstr_fecha;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_tag:
                if(estadoSesiones == EstadoSesiones.PROGRAMADO){
                    onHecho();
                    cambiarTagColor();
                    CallService.jobServiceExportarTipos(itemView.getContext(), TipoExportacion.SESIONES);
                    SimpleSyncIntenService.start(itemView.getContext(), programaEducativoId);
                    SynckService.start(itemView.getContext(),programaEducativoId);
                }else if(estadoSesiones == EstadoSesiones.HECHO){

                    if(sesionAprendizaje.getFechaEjecucion() < calendar.getTimeInMillis()){
                        Toast.makeText(itemView.getContext(), R.string.unidad_aprend_change_error_pendiente, Toast.LENGTH_LONG).show();
                        return;
                    }
                    showMessage(R.string.unidad_aprend_change_ejecucion_titulo,
                            R.string.unidad_aprend_change_ejecucion_hecho,
                            new AdapterSesiones_v2.showMessageCollback() {
                                @Override
                                public void onClickAceptar() {
                                    onProgramado();
                                    cambiarTagColor();
                                    CallService.jobServiceExportarTipos(itemView.getContext(), TipoExportacion.SESIONES);
                                    SimpleSyncIntenService.start(itemView.getContext(), programaEducativoId);
                                    SynckService.start(itemView.getContext(),programaEducativoId);
                                }

                                @Override
                                public void onClickCancelar() {

                                }
                            });
                }else if(estadoSesiones == EstadoSesiones.PENDIENTE){
                    showMessage(R.string.unidad_aprend_change_ejecucion_titulo,
                            R.string.unidad_aprend_change_ejecucion_pendiente,
                            new AdapterSesiones_v2.showMessageCollback() {
                                @Override
                                public void onClickAceptar() {
                                    onHecho();
                                    cambiarTagColor();
                                    //CallService.jobServiceExportarTipos(itemView.getContext(), TipoExportacion.SESIONES);
                                    SimpleSyncIntenService.start(itemView.getContext(), programaEducativoId);
                                    SynckService.start(itemView.getContext(),programaEducativoId);
                                }

                                @Override
                                public void onClickCancelar() {

                                }
                            });
                }
                break;
            default:
                listener.onClickSesionListener(view,sesionAprendizaje);
                break;
        }
    }

    private void onPendiente(){
        try {
            SesionAprendizajeUi sesionAprendizajeUi= sesionAprendizaje;
            //listener.getSesionAprendizaje(sesionAprendizaje.getSesionAprendizajeId());
            estadoSesiones = EstadoSesiones.PENDIENTE;
            sesionAprendizajeUi.setEstadoEjecucionId(318);
            sesionAprendizajeUi.setSyncFlag(SesionAprendizajeUi.FLAG_UPDATED);
            listener.SaveSesionAprendizaje(sesionAprendizajeUi);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void onHecho(){
        try {
            SesionAprendizajeUi sesionAprendizajeUi= sesionAprendizaje;
            //listener.getSesionAprendizaje(sesionAprendizaje.getSesionAprendizajeId());
            estadoSesiones = EstadoSesiones.PENDIENTE;
            estadoSesiones = EstadoSesiones.HECHO;
            sesionAprendizajeUi.setEstadoEjecucionId(317);
            sesionAprendizajeUi.setSyncFlag(SesionAprendizaje.FLAG_UPDATED);
            listener.SaveSesionAprendizaje(sesionAprendizajeUi);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onProgramado(){

        try {
            SesionAprendizajeUi sesionAprendizajeUi=sesionAprendizaje;
            //listener.getSesionAprendizaje(sesionAprendizaje.getSesionAprendizajeId());
            estadoSesiones = EstadoSesiones.PROGRAMADO;
            sesionAprendizajeUi.setEstadoEjecucionId(316);
            sesionAprendizajeUi.setSyncFlag(SesionAprendizaje.FLAG_UPDATED);
            listener.SaveSesionAprendizaje(sesionAprendizajeUi);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cambiarTagColor(){
        String nombreEstadoSession = "";
        Drawable drawable = null;
        Drawable drawables = null;
        @ColorInt
        int colorEstado = 0;
        switch (estadoSesiones){
            case HECHO:
                nombreEstadoSession = "Hecho";//Verde
                colorEstado = ContextCompat.getColor(itemView.getContext(),R.color.md_green_600);
                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.corner_bg_red);
                drawables = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_session_verde);
                //fondo.setBackgroundColor(R.drawable.border_session_verde);
                break;
            case CREADO:
                nombreEstadoSession = "Creado";//Azul
                colorEstado = ContextCompat.getColor(itemView.getContext(),R.color.md_blue_600);
                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.corner_bg_blue);
                drawables = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_session_azul);
                break;
            case PENDIENTE:
                nombreEstadoSession = "Pendiente";//Rojo
                colorEstado = ContextCompat.getColor(itemView.getContext(),R.color.md_red_500);
                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.corner_bg_red_600);
                drawables = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_session_rojo);
                break;
            case PROGRAMADO:
                nombreEstadoSession = "Programado";//Magenta
                colorEstado = ContextCompat.getColor(itemView.getContext(),R.color.md_orange_600);
                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.corner_bg_orange);
                drawables = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_session_magenta);
                break;
        }
        //corner_bg_orange
        lin_titulosesion.setBackgroundColor(colorEstado);
        txtTag.setText(nombreEstadoSession);
        txtTag.setTextColor(colorEstado);
        txtTag.setBackground(drawable);
        fondo.setBackground(drawables);
        txttiempo_medida.setTextColor(colorEstado);
        txttiempo.setTextColor(colorEstado);
        Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_circle_unidades);
        circle.mutate().setColorFilter(colorEstado, PorterDuff.Mode.SRC_ATOP);
        cantRecursos.setBackground(circle);

    }

    public void showMessage(int titulo , int message, final AdapterSesiones_v2.showMessageCollback collback) {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(itemView.getContext());
        builder.setCancelable(false);
        builder.setTitle(titulo);
        builder.setMessage(message);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                collback.onClickAceptar();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                collback.onClickCancelar();
                dialogInterface.cancel();
            }
        });
        //Create AdapterExample
        builder.create().show();
    }

}