package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.holders.PersonasDestinoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;

import java.util.List;

/**
 * Created by irvinmarin on 19/12/2017.
 */

public class PersonasDestinoAdapter extends RecyclerView.Adapter<PersonasDestinoViewHolder> {
    private List<PersonaRelacionesUI> personaRelacionesList;
    private ImageLoader imageLoader;
    private OnPersonaDestinoClickedListener destinoClickedListener;
    private RecyclerView rvPersonasDestino;
    private int tipoLayout;

    public PersonasDestinoAdapter(int tipoLayout, RecyclerView rvPersonasDestino, List<PersonaRelacionesUI> personaRelacionesList, ImageLoader imageLoader, OnPersonaDestinoClickedListener destinoClickedListener) {
        this.personaRelacionesList = personaRelacionesList;
        this.imageLoader = imageLoader;
        this.destinoClickedListener = destinoClickedListener;
        this.rvPersonasDestino = rvPersonasDestino;
        this.tipoLayout = tipoLayout;
    }

    public interface OnPersonaDestinoClickedListener {
        void onClickShowPersonaDestino(int tipoParentezco, PersonaRelacionesUI personaRelacionesUI);

        void onClickDeletePersonaDestino(int tipoParentezco, PersonaRelacionesUI personaRelacionesUI);

        void onSendMessajeInidivdualClicked(PersonaRelacionesUI personaRelaciones);
    }

    @Override
    public PersonasDestinoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona_destino, parent, false);
        return new PersonasDestinoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final PersonasDestinoViewHolder vh, final int position) {
        vh.bind(personaRelacionesList.get(position), imageLoader, destinoClickedListener,tipoLayout);
    }

    @Override
    public int getItemCount() {
//        return 3;
        return personaRelacionesList.size();
    }


    public void setPersonaRelacionesList(List<PersonaRelacionesUI> personaRelacionesList) {
        this.personaRelacionesList.clear();
        this.personaRelacionesList.addAll(personaRelacionesList);
        notifyDataSetChanged();
    }

    public void delete(PersonaRelacionesUI personasDestinoUI) {
        int position = personaRelacionesList.indexOf(personasDestinoUI);
        if (position != -1) {
            personaRelacionesList.remove(personasDestinoUI);
            notifyItemRemoved(position);
        }
    }

    public void add(PersonaRelacionesUI personasDestinoUI) {
        if (!this.personaRelacionesList.contains(personasDestinoUI)) {
            this.personaRelacionesList.add(personasDestinoUI);
            notifyItemInserted(getItemCount() - 1);
            scrollToLastItem();
        }
    }

    private void scrollToLastItem() {
        rvPersonasDestino.scrollToPosition(getItemCount() - 1);
    }
}
