package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public class SaveComportamiento  {

    ComportamientoRepository comportamientoRepository;

    public SaveComportamiento(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }
    public Response execute(Request request){
        return new Response(comportamientoRepository.saveComportamiento(request.getComportamientoUi(), request.getDestinoUi(), request.getRepositorioFileUiList()));
    }


    public static class Request {
        private ComportamientoUi comportamientoUi;
        private DestinoUi destinoUi;
        private List<RepositorioFileUi> repositorioFileUiList;

        public Request(ComportamientoUi comportamientoUi, DestinoUi destinoUi, List<RepositorioFileUi> repositorioFileUiList) {
            this.comportamientoUi = comportamientoUi;
            this.destinoUi = destinoUi;
            this.repositorioFileUiList = repositorioFileUiList;
        }

        public ComportamientoUi getComportamientoUi() {
            return comportamientoUi;
        }

        public DestinoUi getDestinoUi() {
            return destinoUi;
        }

        public List<RepositorioFileUi> getRepositorioFileUiList() {
            return repositorioFileUiList;
        }
    }
    public class Response{
        private boolean success;

        public Response(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
