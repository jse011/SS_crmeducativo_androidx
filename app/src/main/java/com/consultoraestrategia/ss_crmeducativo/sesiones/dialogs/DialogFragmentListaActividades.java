package com.consultoraestrategia.ss_crmeducativo.sesiones.dialogs;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.AdapterActividades;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("ValidFragment")
public class DialogFragmentListaActividades extends DialogFragment{

    String TAG = "DialogFragmentActividades";
    View viewPadre;
    @BindView(R.id.rv_actividades)
    RecyclerView rvactividades;
    @BindView(R.id.txt_titulo_sesion)
    TextView txttitulo_sesion;


    public static DialogFragmentListaActividades newInstance() {
        DialogFragmentListaActividades fragment = new DialogFragmentListaActividades();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Obtener instancia de la action bar
        ActionBar actionBar = ((AppCompatActivity) getActivity())
                .getSupportActionBar();

        if (actionBar != null) {
            // Habilitar el Up Button
            actionBar.setDisplayHomeAsUpEnabled(true);
            // Cambiar icono del Up Button
            actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(getContext(), R.drawable.ic_close));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.dialog_fragment_lista_actividades, container, false);
        ButterKnife.bind(this, viewPadre);

        //this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        int mint_SesionAprendizajeId = getArguments().getInt("SesionAprendizajeId");
        int mint_backgroudColor = getArguments().getInt("backgroudColor");
        int mint_personaId = getArguments().getInt("personaId");


        SesionAprendizaje sesionAprendizaje = SQLite.select()
                                            .from(SesionAprendizaje.class)
                                            .where(SesionAprendizaje_Table.sesionAprendizajeId.withTable().is(mint_SesionAprendizajeId))
                                            .querySingle();


        List<ActividadAprendizaje> mlst_actividadAprendizajes = SQLite.select()
                                                                .from(ActividadAprendizaje.class)
                                                                .where(ActividadAprendizaje_Table.sesionAprendizajeId.withTable().is(mint_SesionAprendizajeId))
                                                                .queryList();


        List<RecursoDidacticoEventoC> mlst_recursoDidacticoEvento = SQLite.select()
                                                                    .from(RecursoDidacticoEventoC.class)
                                                                    .innerJoin(RecursoReferenciaC.class)
                                                                    .on(RecursoReferenciaC_Table.recursoDidacticoId.withTable().eq(RecursoDidacticoEventoC_Table.key.withTable()))
                                                                    .where(RecursoReferenciaC_Table.sesionAprendizajeId.withTable().is(mint_SesionAprendizajeId))
                                                                    .queryList();


        txttitulo_sesion.setText("Sesión "+sesionAprendizaje.getNroSesion() + " - " + sesionAprendizaje.getTitulo());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvactividades.setLayoutManager(linearLayoutManager);
        rvactividades.setHasFixedSize(true);

        List<Object> objects = new ArrayList<>();
        objects.addAll(mlst_actividadAprendizajes);
        if(mlst_recursoDidacticoEvento.size()>0){
            objects.add("Titulo Recursos");
            objects.addAll(mlst_recursoDidacticoEvento);
        }

        AdapterActividades listAdapter = new AdapterActividades(objects,mint_backgroudColor,mint_personaId);
        rvactividades.setAdapter(listAdapter);

        return viewPadre;
    }

    @Override
    public void onStart() {
        super.onStart();
        //this.getDialog().getWindow()
          //      .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

}



