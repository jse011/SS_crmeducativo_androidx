package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;

public class UpdateSuccesDowloadCasoArchivo extends UseCaseSincrono<UpdateSuccesDowloadCasoArchivo.Request, Boolean> {
    private ComportamientoRepository comportamientoRepository;

    public UpdateSuccesDowloadCasoArchivo(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    public void execute(Request request, final Callback<Boolean> callback) {
        comportamientoRepository.updateSucessDowload(request.getArchivoId(), request.getPath(), new ComportamientoDataSource.CallbackSuccess() {
            @Override
            public void onLoad(boolean success) {
                callback.onResponse(success, success);
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
