package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.FirstColumn;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.HeaderTable;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.ListCells;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.SendMessageRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.callbacks.GetRubrosListCallback;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class GetNotasRubrosStringUseCase extends UseCase<GetNotasRubrosStringUseCase.RequestValues, GetNotasRubrosStringUseCase.ResponseValue> {
    private SendMessageRubroRepository repository;

    public GetNotasRubrosStringUseCase(SendMessageRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getRubrosStringList(requestValues, new GetRubrosListCallback() {
            @Override
            public void onListLoaded(String nombreCurso, String nombreAlumno, String apellidos, String puntos,
                                     double desempenio, String logro, String urlProfile,
                                     String programa, int columnsSize,
                                     List<String> listRubrosString, String seccion, String periodoAcad,
                                     List<FirstColumn> nombresRubros,
                                     List<HeaderTable> headerTableList,
                                     List<ListCells> listCellsParent) {
                getUseCaseCallback().onSuccess(new ResponseValue(nombreCurso, nombreAlumno, apellidos, puntos,
                        desempenio, logro, urlProfile, programa, columnsSize,
                        listRubrosString, periodoAcad, seccion,
                        nombresRubros,
                        headerTableList,
                        listCellsParent
                ));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        final int idAlumno;
        final int idCargaCurso;
        final String rubroId;

        public RequestValues(int idAlumno, int idCargaCurso, String rubroId) {
            this.idAlumno = idAlumno;
            this.idCargaCurso = idCargaCurso;
            this.rubroId = rubroId;
        }

        public int getIdAlumno() {
            return idAlumno;
        }

        public int getIdCargaCurso() {
            return idCargaCurso;
        }

        public String getRubroId() {
            return rubroId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
//        String nombreCurso, String nombreAlumno, String puntos, String logro, int columnsSize,

        private final String nombreCurso;
        private final String nombreAlumno;
        private final String apellidos;
        private final String puntos;
        private final double desempenio;
        private final String logro;
        private final String urlProfile;
        private final String programa;
        private final int columnsSize;
        private final List<String> listRubrosString;
        private final String periodo;
        private final String seccion;
        private final List<FirstColumn> nombresRubros;
        private final List<HeaderTable> headerTableList;
        private final List<ListCells> listCellsParent;


        public ResponseValue(String nombreCurso, String nombreAlumno, String apellidos, String puntos, double desempenio, String logro, String urlProfile, String programa, int columnsSize, List<String> listRubrosString, String periodo, String seccion, List<FirstColumn> nombresRubros, List<HeaderTable> headerTableList, List<ListCells> listCellsParent) {
            this.nombreCurso = nombreCurso;
            this.nombreAlumno = nombreAlumno;
            this.apellidos = apellidos;
            this.puntos = puntos;
            this.desempenio = desempenio;
            this.logro = logro;
            this.urlProfile = urlProfile;
            this.programa = programa;
            this.columnsSize = columnsSize;
            this.listRubrosString = listRubrosString;
            this.periodo = periodo;
            this.seccion = seccion;
            this.nombresRubros = nombresRubros;
            this.headerTableList = headerTableList;
            this.listCellsParent = listCellsParent;
        }

        public String getApellidos() {
            return apellidos;
        }

        public List<FirstColumn> getNombresRubros() {
            return nombresRubros;
        }

        public List<HeaderTable> getHeaderTableList() {
            return headerTableList;
        }

        public List<ListCells> getListCellsParent() {
            return listCellsParent;
        }

        public String getPrograma() {
            return programa;
        }

        public String getUrlProfile() {
            return urlProfile;
        }

        public String getPeriodo() {
            return periodo;
        }

        public String getSeccion() {
            return seccion;
        }

        public double getDesempenio() {
            return desempenio;
        }

        public String getNombreCurso() {
            return nombreCurso;
        }

        public String getNombreAlumno() {
            return nombreAlumno;
        }

        public String getPuntos() {
            return puntos;
        }

        public String getLogro() {
            return logro;
        }

        public int getColumnsSize() {
            return columnsSize;
        }

        public List<String> getListRubrosString() {
            return listRubrosString;
        }


    }
}
