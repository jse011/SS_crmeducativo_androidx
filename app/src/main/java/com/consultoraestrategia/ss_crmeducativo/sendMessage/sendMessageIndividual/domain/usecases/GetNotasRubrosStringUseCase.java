package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.SendMessageNormalRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetRubrosListCallback;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class GetNotasRubrosStringUseCase extends UseCase<GetNotasRubrosStringUseCase.RequestValues, GetNotasRubrosStringUseCase.ResponseValue> {
    private SendMessageNormalRepository repository;
    public GetNotasRubrosStringUseCase(SendMessageNormalRepository repository) {
        this.repository = repository;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getRubrosStringList(requestValues, new GetRubrosListCallback() {
            @Override
            public void onListLoaded(String nombreCurso, String nombreAlumno, String puntos, double desempenio, String logro, String urlProfile,String programa, int columnsSize, List<String> listRubrosString, String seccion, String periodoAcad) {
                getUseCaseCallback().onSuccess(new ResponseValue(nombreCurso, nombreAlumno, puntos, desempenio, logro, urlProfile, programa, columnsSize, listRubrosString, periodoAcad, seccion));
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
        private final String puntos;
        private final double desempenio;
        private final String logro;
        private final String urlProfile;
        private final String programa;
        private final int columnsSize;
        private final List<String> listRubrosString;
        private final String periodo;
        private final String seccion;

        public ResponseValue(String nombreCurso, String nombreAlumno, String puntos, double desempenio, String logro, String urlProfile, String programa, int columnsSize, List<String> listRubrosString, String periodo, String seccion) {
            this.nombreCurso = nombreCurso;
            this.nombreAlumno = nombreAlumno;
            this.puntos = puntos;
            this.desempenio = desempenio;
            this.logro = logro;
            this.urlProfile = urlProfile;
            this.programa = programa;
            this.columnsSize = columnsSize;
            this.listRubrosString = listRubrosString;
            this.periodo = periodo;
            this.seccion = seccion;
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

        @Override
        public String toString() {
            return "ResponseValue{" +
                    "nombreCurso='" + nombreCurso + '\'' +
                    ", nombreAlumno='" + nombreAlumno + '\'' +
                    ", puntos='" + puntos + '\'' +
                    ", logro='" + logro + '\'' +
                    ", columnsSize=" + columnsSize +
                    ", listRubrosString=" + listRubrosString +
                    '}';
        }
    }
}
