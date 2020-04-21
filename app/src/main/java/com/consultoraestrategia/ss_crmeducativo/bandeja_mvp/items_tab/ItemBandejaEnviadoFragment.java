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
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.BandejaEnviadosAdapter;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC_Table;
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

public class ItemBandejaEnviadoFragment extends Fragment {
    private static int idCargaCurso;
    private static int idAlumno;
    View viewPadre;
    List<MensajeC> mensajeList;
    Context context;
    Persona alumno;
    @BindView(R.id.rvBandeja)
    RecyclerView rvBandeja;


    public static ItemBandejaEnviadoFragment newInstace(int idCargaCurso, int idAlumno) {
        ItemBandejaEnviadoFragment.idCargaCurso = idCargaCurso;
        ItemBandejaEnviadoFragment.idAlumno = idAlumno;

        return new ItemBandejaEnviadoFragment();
    }


    public ItemBandejaEnviadoFragment() {
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.layout_content_mensajes, container, false);
        ButterKnife.bind(this, viewPadre);
        return viewPadre;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateMensajesEnviados();
        setupRecyclerView();
    }


    private void populateMensajesEnviados() {
        try{
            mensajeList = SQLite.select()
                    .from(MensajeC.class)
                    .where(MensajeC_Table.usuarioOrigenId.is(SessionUser.getCurrentUser().getUserId()))
                    .and(MensajeC_Table.cargaCursoId.is(idCargaCurso))
                    .queryList();

            for (MensajeC mensaje : mensajeList) {
                List<MensajeUsuarioC> mensajeUsuarios = SQLite.select()
                        .from(MensajeUsuarioC.class)
                        .where(MensajeUsuarioC_Table.mensajeId.is(mensaje.getKey()))
                        .queryList();
                if (mensajeUsuarios != null) {
                    mensajeUsuarioList.addAll(mensajeUsuarios);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    List<MensajeUsuarioC> mensajeUsuarioList = new ArrayList<>();

    private void setupRecyclerView() {

        if (mensajeList.size() > 0) {
            BandejaEnviadosAdapter bandejaAdapter = new BandejaEnviadosAdapter(mensajeList);
            rvBandeja.setLayoutManager(new LinearLayoutManager(getContext()));
            rvBandeja.setAdapter(bandejaAdapter);
        }

    }
}
