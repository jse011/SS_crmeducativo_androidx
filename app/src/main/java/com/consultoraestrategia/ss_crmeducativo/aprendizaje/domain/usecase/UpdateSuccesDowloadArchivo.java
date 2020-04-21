package com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;

public class UpdateSuccesDowloadArchivo extends UseCaseSincrono<UpdateSuccesDowloadArchivo.Request, Boolean> {
    AprendizajeRepository repository;

    public UpdateSuccesDowloadArchivo(AprendizajeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        repository.updateSucessDowload(request.getArchivoId(), request.getPath(), new AprendizajeRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                callback.onResponse(success, item);
            }
        });
    }

    public static class Request {
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