package com.consultoraestrategia.ss_crmeducativo.actividades.adapter.holder;

import androidx.core.content.ContextCompat;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.holder.DownloadHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class RecursosHolder extends DownloadHolder {


    public RecursosHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(RepositorioFileUi repositorioFileUi, DownloadItemListener repositorioItemListener) {
        super.bind(repositorioFileUi, repositorioItemListener);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void count(int count) {
        super.count(count);
    }

    @Override
    public RepositorioFileUi getRepositorioFileUi() {
        return super.getRepositorioFileUi();
    }


}
