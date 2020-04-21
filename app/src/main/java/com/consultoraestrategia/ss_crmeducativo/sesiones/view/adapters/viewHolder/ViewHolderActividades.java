package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.AdapterRecursos;
import com.consultoraestrategia.ss_crmeducativo.util.CircularTextView;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderActividades extends RecyclerView.ViewHolder {
    @BindView(R.id.lin_actividad_Det)
    ConstraintLayout lin_actividad_Det;
    @BindView(R.id.lin_cabeceraAct)
    LinearLayout lin_cabeceraAct;
    @BindView(R.id.lin_cabeceraActDet)
    LinearLayout lin_cabeceraActDet;
    @BindView(R.id.lin_cabeceraActDesc)
    LinearLayout lin_cabeceraActDesc;
    @BindView(R.id.txt_actividad)
    TextView txtactividad;
    @BindView(R.id.txt_num_actividad)
    CircularTextView txtnum_actividad;

    @BindView(R.id.txt_tiempo_act)
    TextView txttiempo_act;
    @BindView(R.id.txt_tiem_medida_act)
    TextView txttiem_medida_act;

    /*@BindView(R.id.txt_tipo_act)
    TextView txttipo_act;
    @BindView(R.id.txt_secuencia_act)
    TextView txtsecuencia_act;*/
    @BindView(R.id.txt_actividad_det)
    TextView txtactividad_det;
    @BindView(R.id.txt_tiempo_act_det)
    TextView txttiempo_act_det;
    @BindView(R.id.txt_tipo_act_det)
    TextView txttipo_act_det;
    @BindView(R.id.txt_secuencia_act_det)
    TextView txtsecuencia_act_det;
    @BindView(R.id.txt_descripcion_act_det)
    TextView txtdescripcion_act_det;
    @BindView(R.id.txt_cant_recursos)
    TextView txtcant_recursos;
    @BindView(R.id.txt_ver_detalle)
    TextView txtver_detalle;
    @BindView(R.id.txt_ocultar_det)
    TextView txtocultar_det;
    @BindView(R.id.rv_act_recursos)
    RecyclerView rvact_recursos;
    @BindView(R.id.Checked_validacion)
    CheckedTextView Checked_validacion;
    @BindView(R.id.txt_validacion)
    TextView txtvalidacion;


    // Cada uno de los elementos de mi vista
    public ViewHolderActividades(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
    
    public void bind(final ActividadAprendizaje actividad, final int vint_backgroudColor, int position, int personaId){
        Context mcontex = itemView.getContext();

        Drawable circle = ContextCompat.getDrawable(mcontex, R.drawable.ic_circle_unidades);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontex);
        final Drawable draw_tump_up = ContextCompat.getDrawable(mcontex, R.drawable.ic_thumb_up);
        final Drawable draw_tump_dow = ContextCompat.getDrawable(mcontex, R.drawable.ic_thumb_down);

        Tipos tipoActividad  = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.tipoId.withTable().is(actividad.getTipoActividadId()))
                .querySingle();

        Tipos secuencia  = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.tipoId.withTable().is(actividad.getSecuenciaId()))
                .querySingle();

       final List<RecursoDidacticoEventoC> mlst_recursoDidacticoEvento = SQLite.select()
                .from(RecursoDidacticoEventoC.class)
                .innerJoin(RecursoReferenciaC.class)
                .on(RecursoReferenciaC_Table.recursoDidacticoId.withTable().eq(RecursoDidacticoEventoC_Table.key.withTable()))
                .where(RecursoReferenciaC_Table.actividadAprendizajeId.withTable().is(actividad.getActividadAprendizajeId()))
                .queryList();

        int Cantidad_recursos =  mlst_recursoDidacticoEvento.size();


        if(personaId > 0){
            Checked_validacion.setOnClickListener(null);
        }else{
            Checked_validacion.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //region Cambiar el estadoActividad
                    int mint_Estado = 0;
                    if (Checked_validacion.isSelected())
                    {
                        mint_Estado = 228;
                        txtvalidacion.setText(String.valueOf("Pendiente"));
                        int mint_backgroudColor = Color.parseColor("#34bf73");
                        txtvalidacion.setTextColor(mint_backgroudColor);
                        Checked_validacion.setSelected(false);
                        Checked_validacion.setCheckMarkDrawable(draw_tump_dow);
                    }
                    else
                    {
                        mint_Estado = 229;
                        txtvalidacion.setText(String.valueOf("Hecho"));
                        txtvalidacion.setTextColor(vint_backgroudColor);
                        Checked_validacion.setSelected(true);
                        draw_tump_up.mutate().setColorFilter(vint_backgroudColor, PorterDuff.Mode.SRC_ATOP);
                        Checked_validacion.setCheckMarkDrawable(draw_tump_up);
                    }

                    actividad.setEstadoId(mint_Estado);
                    actividad.save();
                    //endregion
                }
            });
        }

        //region cambiar de color
        circle.mutate().setColorFilter(vint_backgroudColor, PorterDuff.Mode.SRC_ATOP);
        txtcant_recursos.setBackground(circle);
        String hexColor = "#" + Integer.toHexString(vint_backgroudColor).substring(2);
        //txtnum_actividad.setStrokeWidth(1);
        //txtnum_actividad.setStrokeColor(hexColor);
        //txtnum_actividad.setSolidColor("#ffffff");
        txtnum_actividad.setTextColor(vint_backgroudColor);
        txtver_detalle.setTextColor(vint_backgroudColor);
        txtocultar_det.setTextColor(vint_backgroudColor);
        txttiem_medida_act.setTextColor(vint_backgroudColor);
        txttiempo_act.setTextColor(vint_backgroudColor);
        //endregion

        txtactividad.setText(actividad.getActividad());
        txtnum_actividad.setText((position + 1)+"");
        txttiempo_act.setText(actividad.getTiempo()+"");
        txttiem_medida_act.setText("min");
        //txttipo_act.setText(tipoActividad.getNombre());
        //txtsecuencia_act.setText(secuencia.getNombre());
        txtcant_recursos.setText(Cantidad_recursos+"");

        //region texto de la opcion Ver mas
        txtactividad_det.setText(actividad.getActividad());
        txttiempo_act_det.setText(actividad.getTiempo()+" min.");
        txttipo_act_det.setText(tipoActividad.getNombre());
        txtsecuencia_act_det.setText(secuencia.getNombre());
        txtdescripcion_act_det.setText(actividad.getDescripcionActividad());
        //endregion

        //region Estado Actividad
        if (actividad.getEstadoId() == 229)
        {
            txtvalidacion.setText(String.valueOf("Hecho"));
            txtvalidacion.setTextColor(vint_backgroudColor);
            Checked_validacion.setSelected(true);
            draw_tump_up.mutate().setColorFilter(vint_backgroudColor, PorterDuff.Mode.SRC_ATOP);
            Checked_validacion.setCheckMarkDrawable(draw_tump_up);
        }
        else
        {
            txtvalidacion.setText(String.valueOf("Pendiente"));
            int mint_backgroudColor = Color.parseColor("#34bf73");
            txtvalidacion.setTextColor(mint_backgroudColor);
            Checked_validacion.setSelected(false);
            Checked_validacion.setCheckMarkDrawable(draw_tump_dow);
        }
        //endregion

        rvact_recursos.setLayoutManager(linearLayoutManager);
        AdapterRecursos listAdapter = new AdapterRecursos(mlst_recursoDidacticoEvento);
        rvact_recursos.setAdapter(listAdapter);


        
    }



}
