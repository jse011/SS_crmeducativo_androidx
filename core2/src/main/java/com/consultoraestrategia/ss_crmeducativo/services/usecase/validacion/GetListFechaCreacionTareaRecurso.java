package com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.BEDatosTareaRecursosRepository;

import java.util.List;

/**
 * Created by SCIEV on 28/05/2018.
 */

public class GetListFechaCreacionTareaRecurso extends UseCase<GetListFechaCreacionTareaRecurso.RequestValues, GetListFechaCreacionTareaRecurso.ResponseValue> {

    private BEDatosTareaRecursosRepository repository;

    public GetListFechaCreacionTareaRecurso(BEDatosTareaRecursosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.comprobarListaCambiosTareaRecursos(requestValues.getTareaRecusoIdList(), new ServiceDataSource.DosObjectCallBack<List<Long>, List<Long>>() {
            @Override
            public void onResponse(boolean success, List<Long> f_ServidorList, List<Long> f_LocalList) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(f_ServidorList, f_LocalList));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        List<String> tareaRecusoIdList;

        public RequestValues(List<String> tareaRecusoIdList) {
            this.tareaRecusoIdList = tareaRecusoIdList;
        }

        public List<String> getTareaRecusoIdList() {
            return tareaRecusoIdList;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        List<Long> f_ServidorList;
        List<Long> f_LocalList;

        public ResponseValue(List<Long> f_ServidorList, List<Long> f_LocalList) {
            this.f_ServidorList = f_ServidorList;
            this.f_LocalList = f_LocalList;
        }

        public List<Long> getF_ServidorList() {
            return f_ServidorList;
        }

        public List<Long> getF_LocalList() {
            return f_LocalList;
        }
    }

}
