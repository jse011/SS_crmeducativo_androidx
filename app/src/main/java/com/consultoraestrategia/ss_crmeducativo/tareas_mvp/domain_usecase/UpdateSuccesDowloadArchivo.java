package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpRepository;

public class UpdateSuccesDowloadArchivo extends UseCaseSincrono<UpdateSuccesDowloadArchivo.Request, Boolean> {
    TareasMvpRepository repository;

    public UpdateSuccesDowloadArchivo(TareasMvpRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        repository.updateSucessDowload(request.getArchivoId(), request.getPath(), new TareasMvpRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                callback.onResponse(success, item);
            }
        });
    }

    public static class Request{
        String archivoId;
        String path;

        public Request(String archivoId, String path) {
            this.archivoId = archivoId;
            this.path = path;
        }

        public String getArchivoId() {
            return archivoId;
        }

        public void setArchivoId(String archivoId) {
            this.archivoId = archivoId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
