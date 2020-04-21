package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;

public class UpdateSuccesDowloadArchivo extends UseCaseSincrono<UpdateSuccesDowloadArchivo.Request, Boolean> {

    UnidadRepository repository;

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        repository.updateSucessDowload(request.getArchivoId(), request.getPath(), new UnidadDataSource.Callback<Boolean>() {
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
