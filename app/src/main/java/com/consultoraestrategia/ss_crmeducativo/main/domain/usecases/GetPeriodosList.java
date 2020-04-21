package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.List;

public class GetPeriodosList extends UseCase<GetPeriodosList.RequestValues, GetPeriodosList.ResponseValue> {

    private MainRepository mainRepository;

    public GetPeriodosList(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mainRepository.getPeriodoList(requestValues.getAnioAcademicoId(), requestValues.getProgramaEducativoId(), new MainDataSource.SucessCallback<List<PeriodoUi>>() {
            @Override
            public void onLoad(boolean success, List<PeriodoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int anioAcademicoId;
        private int programaEducativoId;

        public RequestValues(int anioAcademicoId, int programaEducativoId) {
            this.anioAcademicoId = anioAcademicoId;
            this.programaEducativoId = programaEducativoId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
        }

    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<PeriodoUi> periodoUiList;

        public ResponseValue(List<PeriodoUi> periodoUiList) {
            this.periodoUiList = periodoUiList;
        }

        public List<PeriodoUi> getPeriodoUiList() {
            return periodoUiList;
        }
    }
}
