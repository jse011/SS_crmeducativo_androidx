package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardRecursosDidacticosUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class RecursoDidacticoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recRecursos)
    RecyclerView recRecursos;
    @BindView(R.id.txtvacio)
    TextView vacio;

    private DownloadAdapter adapter;

    public RecursoDidacticoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CardRecursosDidacticosUi cardRecursosDidacticosUi, DownloadItemListener downloadItemListener) {
        if (cardRecursosDidacticosUi.getRecursosDidacticoUis().isEmpty()) {
            vacio.setVisibility(View.VISIBLE);
        } else {
            vacio.setVisibility(View.GONE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recRecursos.getContext());
        adapter = new DownloadAdapter(downloadItemListener, recRecursos);
        recRecursos.setAdapter(adapter);
        recRecursos.setLayoutManager(linearLayoutManager);
        adapter.setList(new ArrayList<RepositorioFileUi>(cardRecursosDidacticosUi.getRecursosDidacticoUis()));
        recRecursos.setNestedScrollingEnabled(false);
    }

    public DownloadAdapter getAdapter() {
        return adapter;
    }
}
