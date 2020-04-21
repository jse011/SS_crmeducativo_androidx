package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.items_tab;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.BandejaRecibidoAdapter;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 14/08/2017.
 */

public class ItemBandejaRecibidoFragment extends Fragment {
    private static int idAlumno;
    private static int idCargaCurso;
    View viewPadre;
    List<MensajeC> mensajeList;
    Context context;
    Persona alumno;
    @BindView(R.id.rvBandeja)
    RecyclerView rvBandeja;


    public static ItemBandejaRecibidoFragment newInstace(int idCargaCurso, int idAlumno) {
        ItemBandejaRecibidoFragment.idCargaCurso = idCargaCurso;
        ItemBandejaRecibidoFragment.idAlumno = idAlumno;

        return new ItemBandejaRecibidoFragment();
    }

    public ItemBandejaRecibidoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.layout_content_mensajes, container, false);
        ButterKnife.bind(this, viewPadre);

        populateMensajesRecibidos();
        setupRecyclerView();
        return viewPadre;
    }


    List<MensajeUsuarioC> mensajeUsuarioCListFinal = new ArrayList<>();

    private void populateMensajesRecibidos() {
        try{
            mensajeList = SQLite.select()
                    .from(MensajeC.class)
                    .where(MensajeC_Table.usuarioOrigenId.isNot(SessionUser.getCurrentUser().getUserId()))
                    .and(MensajeC_Table.cargaCursoId.isNot(idCargaCurso))
                    .queryList();
            for (MensajeC mensajeC : mensajeList) {
                List<MensajeUsuarioC> mensajeUsuarioCList = SQLite.select()
                        .from(MensajeUsuarioC.class)
                        .where(MensajeUsuarioC_Table.mensajeId.is(mensajeC.getKey()))
                        .queryList();
                if (mensajeUsuarioCList != null) mensajeUsuarioCListFinal.addAll(mensajeUsuarioCList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setupRecyclerView() {
        BandejaRecibidoAdapter bandejaAdapter = new BandejaRecibidoAdapter(mensajeUsuarioCListFinal);
        rvBandeja.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBandeja.setAdapter(bandejaAdapter);
    }
}
