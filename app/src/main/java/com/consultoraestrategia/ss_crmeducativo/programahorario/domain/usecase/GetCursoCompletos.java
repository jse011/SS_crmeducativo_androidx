package com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioDatSource;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;

import java.util.List;

public class GetCursoCompletos extends UseCase<GetCursoCompletos.RequestValues, GetCursoCompletos.ResponseValue> {


    private ProgramaHorarioRepository horarioRepository;

    public GetCursoCompletos(ProgramaHorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        horarioRepository.listarCursosCargaCursoProgramaEducativo(requestValues.getProgramaEducativo(),
                requestValues.getEmpleadoId(), requestValues.getAnioAcademicoId(),
                new ProgramaHorarioDatSource.Callback<List<CursoUi>>() {
                    @Override
                    public void load(boolean success, List<CursoUi> item) {
                            if(success){
                                getUseCaseCallback().onSuccess(new ResponseValue(item));
                            }   else {
                                getUseCaseCallback().onError();
                            }
                    }
                });
    }

    public static class RequestValues implements UseCase.RequestValues{
        int programaEducativo;
        int empleadoId;
        int anioAcademicoId;

        public RequestValues(int programaEducativo, int empleadoId,  int anioAcademicoId) {
            this.programaEducativo = programaEducativo;
            this.empleadoId = empleadoId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getProgramaEducativo() {
            return programaEducativo;
        }

        public void setProgramaEducativo(int programaEducativo) {
            this.programaEducativo = programaEducativo;
        }

        public int getEmpleadoId() {
            return empleadoId;
        }

        public void setEmpleadoId(int empleadoId) {
            this.empleadoId = empleadoId;
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

