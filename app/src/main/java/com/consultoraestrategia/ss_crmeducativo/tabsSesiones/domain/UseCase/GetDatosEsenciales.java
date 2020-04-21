package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.TabSesionesDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.TabsSesionesRepository;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.DatosEnsencialesUI;

/**
 * Created by SCIEV on 25/01/2018.
 */

public class GetDatosEsenciales{

    TabsSesionesRepository repository;

    public GetDatosEsenciales(TabsSesionesRepository repository) {
        this.repository = repository;
    }


    public DatosEnsencialesUI executeUseCase(RequestValues requestValues) {
        return repository.getDatosEsenciales(requestValues.getSesionAprendizajeId(),requestValues.getCursoId());
    }

    public final static class RequestValues implements UseCase.RequestValues {
        private int sesionAprendizajeId;
        private int cursoId;

        public RequestValues(int sesionAprendizajeId, int cursoId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.cursoId = cursoId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public int getCursoId() {
            return cursoId;
        }
    }


}
