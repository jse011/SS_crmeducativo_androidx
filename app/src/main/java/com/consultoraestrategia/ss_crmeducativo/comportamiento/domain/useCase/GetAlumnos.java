package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.ListenerTabCreateComport;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;

import java.util.List;

public class GetAlumnos extends UseCase<GetAlumnos.RequestValues, GetAlumnos.ResponseValue> {

   ComportamientoRepository comportamientoRepository;

    public GetAlumnos(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
   comportamientoRepository.getAlumnos(requestValues.getCargacursoId(), new ComportamientoDataSource.SucessCallback<List<AlumnoUi>>() {
       @Override
       public void onLoad(boolean success, List<AlumnoUi> item) {
           getUseCaseCallback().onSuccess(new ResponseValue(item));
       }
   });
    }
    public static class RequestValues implements UseCase.RequestValues{
        private int cargacursoId;

        public RequestValues(int cargacursoId) {
            this.cargacursoId = cargacursoId;
        }

        public int getCargacursoId() {
            return cargacursoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        List<AlumnoUi>alumnoUiList;

        public ResponseValue(List<AlumnoUi> alumnoUiList) {
            this.alumnoUiList = alumnoUiList;
        }

        public List<AlumnoUi> getAlumnoUiList() {
            return alumnoUiList;
        }
    }
}
