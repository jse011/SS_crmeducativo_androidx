package com.consultoraestrategia.ss_crmeducativo.actividades.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.actividades.data.source.ActividadesRepository;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;

public class UpdateSuccesDowloadArchivo extends UseCaseSincrono<UpdateSuccesDowloadArchivo.Request, Boolean> {
    ActividadesRepository repository;

    public UpdateSuccesDowloadArchivo(ActividadesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        repository.updateSucessDowload(request.getArchivoId(), request.getPath(), new ActividadesRepository.Callback<Boolean>() {
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