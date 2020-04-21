package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;

import java.util.List;

public class GetGradosList extends UseCase<GetGradosList.RequestValues, GetGradosList.ResponseValue> {
    MainRepository mainRepository;

    public GetGradosList(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mainRepository.getGradosList(requestValues.getProgramaId(), requestValues.getUsuarioId(), requestValues.getAnioAcademicoId(), new MainDataSource.SucessCallback<List<GradoUi>>() {
            @Override
            public void onLoad(boolean success, List<GradoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }
    public static class RequestValues implements UseCase.RequestValues{
        private int programaId;
        private int usuarioId;
        private int anioAcademicoId;

        public RequestValues(int programaId, int usuarioId, int anioAcademicoId) {
            this.programaId = programaId;
            this.usuarioId = usuarioId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getProgramaId() {
            return programaId;
        }

        public int getUsuarioId() {
            return usuarioId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<GradoUi >gradoUiList;

        public ResponseValue(List<GradoUi> gradoUiList) {
            this.gradoUiList = gradoUiList;
        }

        public List<GradoUi> getGradoUiList() {
            return gradoUiList;
        }
    }
}
