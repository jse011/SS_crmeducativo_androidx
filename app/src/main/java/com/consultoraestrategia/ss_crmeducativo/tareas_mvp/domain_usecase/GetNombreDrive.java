package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase;

import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.DriveFileUi;

public class GetNombreDrive {
    TareasMvpRepository tareasMvpRepository;

    public GetNombreDrive(TareasMvpRepository tareasMvpRepository) {
        this.tareasMvpRepository = tareasMvpRepository;
    }

    public RetrofitCancel execute(RepositorioFileUi repositorioFileUi, Callback callback){
        
        return tareasMvpRepository.getUrlDriveArchivo(repositorioFileUi.getRecursoId(), new TareasMvpDataSource.Callback<DriveFileUi>() {

            @Override
            public void onLoad(boolean success, DriveFileUi driveFileUi) {
               if(success){
                   callback.onSuccess(driveFileUi);
               }else {
                   callback.onError();
               }
            }
        });
    }

    public interface Callback{
        void onSuccess(DriveFileUi driveFileUi);
        void onError();
    }
}
