package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.holders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioCanalComunicacion;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioCanalComunicacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 04/07/2018.
 */

public class DestinatariosViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = DestinatariosViewHolder.class.getSimpleName();

    @BindView(R.id.cbRelacion)
    CheckBox cbRelacion;
    @BindView(R.id.txtNombreRelacion)
    TextView txtNombreRelacion;
    @BindView(R.id.cbMessenger)
    CheckBox cbMessenger;
    @BindView(R.id.cbAcademico)
    CheckBox cbAcademico;
    @BindView(R.id.cbWhatsApp)
    CheckBox cbWhatsApp;
    @BindView(R.id.cbgmail)
    CheckBox cbgmail;
    @BindView(R.id.cbSMS)
    CheckBox cbSMS;

    Persona padre;
    Persona parents;
    Persona alumno;
    List<UsuarioCanalComunicacion> canalComunicacionsSeleccionados = new ArrayList<>();

    public DestinatariosViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }

    public void bind(final Relaciones relaciones, final int position) {
        Usuario usuario = null;
        cbAcademico.setEnabled(false);
        cbMessenger.setEnabled(false);
        cbWhatsApp.setEnabled(false);
        cbgmail.setEnabled(false);

        txtNombreRelacion.setTextSize(10);

        try{
            parents = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.is(relaciones.getPersonaVinculadaId()))
                    .querySingle();

            if (parents != null) {
                usuario = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.personaId.is(parents.getPersonaId()))
                        .querySingle();

                String fulllnameRelacion = parents.getApellidoPaterno() + " " + parents.getApellidoMaterno() + " \n" + parents.getNombres();
                if (!parents.getCelular().equals("")) {
                    cbRelacion.setEnabled(true);
                    cbRelacion.setChecked(true);


                } else {
                    cbRelacion.setEnabled(false);
                }
                if (relaciones.getTipoId() == 181) {
                    txtNombreRelacion.setText(fulllnameRelacion);
                    cbRelacion.setText("Padre");
                } else {
                    if (relaciones.getTipoId() == 182) {
                        cbRelacion.setText("Madre");
                        txtNombreRelacion.setText(fulllnameRelacion);
                    }
                }
            } else {
                if (relaciones.getTipoId() == 181) {
                    txtNombreRelacion.setText("No Registrado");
                    cbRelacion.setText("Padre");
                } else {
                    if (relaciones.getTipoId() == 182) {
                        cbRelacion.setText("Madre");
                        txtNombreRelacion.setText("No Registrada");
                    }
                }
            }

            List<UsuarioCanalComunicacion> usuarioCanalComunicacionList = null;
//        List<UsuarioCo> canalComunicacions;
            if (usuario != null) {
                usuarioCanalComunicacionList = SQLite.select()
                        .from(UsuarioCanalComunicacion.class)
                        .where(UsuarioCanalComunicacion_Table.usuarioId.is(usuario.getUsuarioId()))
                        .queryList();
                if (usuarioCanalComunicacionList.size() != 0) {
                    for (UsuarioCanalComunicacion usuarioCanalComunicacion : usuarioCanalComunicacionList) {

                        switch (usuarioCanalComunicacion.getCanalComId()) {
                            case 1:
                                cbAcademico.setEnabled(true);
                                cbAcademico.setChecked(true);
                                cbAcademico.setVisibility(View.VISIBLE);
                                if (cbAcademico.isChecked()) {
                                    canalComunicacionsSeleccionados.add(usuarioCanalComunicacion);

                                }
                                break;
                            case 2:
                                cbMessenger.setEnabled(true);
                                cbMessenger.setChecked(true);
                                cbMessenger.setVisibility(View.VISIBLE);
                                if (cbMessenger.isChecked()) {
                                    canalComunicacionsSeleccionados.add(usuarioCanalComunicacion);

                                }
                                break;
                            case 3:
                                cbWhatsApp.setEnabled(true);
                                cbWhatsApp.setVisibility(View.VISIBLE);
                                if (cbWhatsApp.isChecked()) {
                                    canalComunicacionsSeleccionados.add(usuarioCanalComunicacion);

                                }
                                break;
                            case 4:
                                cbSMS.setEnabled(true);
                                cbSMS.setVisibility(View.VISIBLE);
                                if (cbSMS.isChecked()) {
                                    canalComunicacionsSeleccionados.add(usuarioCanalComunicacion);
                                }

                                break;
                            case 5:
                                cbgmail.setEnabled(true);
                                cbgmail.setVisibility(View.VISIBLE);
                                if (cbgmail.isChecked()) {
                                    canalComunicacionsSeleccionados.add(usuarioCanalComunicacion);
                                }
                                break;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}