package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetCursosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetCursosUIList extends UseCase<GetCursosUIList.RequestValues, GetCursosUIList.ResponseValue> {

    private MainRepository repository;

    public GetCursosUIList(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getCursosUIList(requestValues.getIdUsuario(), requestValues.getIdPrograma(), requestValues.getAnioAcademicoSelceted(),
                new GetCursosListCallback() {
                    @Override
                    public void onCursosListLoaded(List<CursosUI> cursosUIList, List<CargaCursos> cargaCursosList) {
                        getUseCaseCallback().onSuccess(new ResponseValue(cursosUIList, cargaCursosList));
                    }

                    @Override
                    public void onError(String error) {

                    }
                });


    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int idUsuario;
        private final int idPrograma;
        private final int anioAcademicoSelceted;

        public RequestValues(int idUsuario, int idPrograma, int anioAcademicoSelceted) {
            this.idUsuario = idUsuario;
            this.idPrograma = idPrograma;
            this.anioAcademicoSelceted = anioAcademicoSelceted;
        }

        public int getIdPrograma() {
            return idPrograma;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public int getAnioAcademicoSelceted() {
            return anioAcademicoSelceted;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<CursosUI> cursosUIList;
        private final List<CargaCursos> cargaCursosList;

        public ResponseValue(List<CursosUI> cursosUIList, List<CargaCursos> cargaCursosList) {
            this.cursosUIList = cursosUIList;
            this.cargaCursosList = cargaCursosList;
        }

        public List<CursosUI> getCursosUIList() {
            return cursosUIList;
        }

        public List<CargaCursos> getCargaCursosList() {
            return cargaCursosList;
        }
    }
}
