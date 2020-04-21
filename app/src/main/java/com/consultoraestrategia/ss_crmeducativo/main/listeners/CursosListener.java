package com.consultoraestrategia.ss_crmeducativo.main.listeners;

import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.CursosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface CursosListener {
    void onCursoSelected(CursosViewHolder.CursosUiRecurso cursosUiRecurso);
    void onHorarioCursoSelected(CursosUI cursosUI, int cargaCurso);
    void onGradoSelected(GradoUi gradoUi, RecyclerView.ViewHolder holderSelected);
    void onSelectedViewHolder(RecyclerView.ViewHolder holder);
    void onClickTutoriaCursoSelected(CursosUI cursosUI, int cargaCurso);
    void onClickReconocimientoCursoSelected(CursosViewHolder.CursosUiRecurso cursosUiRecurso, int cargaCurso);
}
