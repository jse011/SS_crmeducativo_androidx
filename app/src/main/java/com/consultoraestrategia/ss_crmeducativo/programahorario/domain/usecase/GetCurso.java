package com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioDatSource;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;

import java.util.List;

public class GetCurso extends UseCase<GetCurso.RequestValues, GetCurso.ResponseValue> {

    private ProgramaHorarioRepository repository;

    public GetCurso(ProgramaHorarioRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerCursosPorCargaCurso(requestValues.getCargaCursoId(),
                requestValues.getProgramaEducativoId(), requestValues.getAnioAcademicoId(), new ProgramaHorarioDatSource.Callback<List<CursoUi>>() {
                    @Override
                    public void load(boolean success, List<CursoUi> item) {
                        if(success){
                            getUseCaseCallback().onSuccess(new ResponseValue(item));
                        }else {
                            getUseCaseCallback().onError();
                        }
                    }
                });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int cargaCursoId;
        private int programaEducativoId;
        private int anioAcademicoId;

        public RequestValues(int cargaCursoId, int programaEducativoId, int anioAcademicoId) {
            this.cargaCursoId = cargaCursoId;
            this.programaEducativoId = programaEducativoId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<CursoUi> cursoUiList;

        public ResponseValue(List<CursoUi> cursoUiList) {
            this.cursoUiList = cursoUiList;
        }

        public List<CursoUi> getCursoUiList() {
            return cursoUiList;
        }
    }
}
