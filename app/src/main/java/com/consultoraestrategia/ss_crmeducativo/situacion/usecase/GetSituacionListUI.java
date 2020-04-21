package com.consultoraestrategia.ss_crmeducativo.situacion.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.SituacionDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.SituacionRepository;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class GetSituacionListUI {
    private static final String TAG = GetSituacionListUI.class.getSimpleName();
    private SituacionRepository repository;

    public GetSituacionListUI(SituacionRepository repository) {
        this.repository = repository;
    }

    public ResponseValue execute(RequestValues request){
        return new ResponseValue(repository.getSituacionUIList(request));
    }


    public static  class RequestValues{
        private  int unidadAprendizajeId;
        private  int cargaCursoId;
        private int identidad;

        public RequestValues(int unidadAprendizajeId, int cargaCursoId, int identidad) {
            this.unidadAprendizajeId = unidadAprendizajeId;
            this.cargaCursoId = cargaCursoId;
            this.identidad = identidad;
        }

        public int getIdentidad() {
            return identidad;
        }

        public int getUnidadAprendizajeId() {
            return unidadAprendizajeId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static class ResponseValue {
        private  List<SituacionUI> list;

        public ResponseValue(List<SituacionUI> list) {
            this.list = list;
        }

        public List<SituacionUI> getList() {
            return list;
        }
    }
}
