package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.CreateTareaRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.CrearTareaCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CrearTareaUseCase extends UseCase<CrearTareaUseCase.RequestValues, CrearTareaUseCase.ResponseValue> {

    private CreateTareaRepository repository;

    public CrearTareaUseCase(CreateTareaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.crearTarea(requestValues, new CrearTareaCallback() {
            @Override
            public void onTareasCreated(String msjCorrecto, TareasUI tareasUI) {
                if(tareasUI!=null){
                    getUseCaseCallback().onSuccess(new ResponseValue(true, tareasUI));
                }else {
                    getUseCaseCallback().onError();
                }

            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final TareasUI tareasUI;
        private final int idOpcion;
        private final int idUnidadAprendizaje;
        private final int idSesionAprendizaje;
        private final int idSilaboEvento;
        private final int idUsuario;
        private final String titulo;
        private final String instruciones;
        private final long fechaEntrega;
        private final String horaEntrega;
        private final List<RepositorioFileUi> recursosUIList;
        int mIdCurso;
        private final int estadoTarea;

        public RequestValues(TareasUI tareasUI, int idOpcion, int idUnidadAprendizaje, int idSilaboEvento, int idUsuario, String titulo, String instruciones, long fechaEntrega, String horaEntrega, int mIdCurso, int idSesionAprendizaje, int estadoTarea, List<RepositorioFileUi> recursosUIList) {
            this.tareasUI = tareasUI;
            this.idOpcion = idOpcion;
            this.idUnidadAprendizaje = idUnidadAprendizaje;
            this.idSesionAprendizaje = idSesionAprendizaje;
            this.idSilaboEvento = idSilaboEvento;
            this.idUsuario = idUsuario;
            this.titulo = titulo;
            this.instruciones = instruciones;
            this.fechaEntrega = fechaEntrega;
            this.horaEntrega = horaEntrega;
            this.mIdCurso = mIdCurso;
            this.estadoTarea = estadoTarea;
            this.recursosUIList = recursosUIList;
        }

        public int getIdSesionAprendizaje() {
            return idSesionAprendizaje;
        }

        public int getEstadoTarea() {
            return estadoTarea;
        }

        public int getmIdCurso() {
            return mIdCurso;
        }

        public void setmIdCurso(int mIdCurso) {
            this.mIdCurso = mIdCurso;
        }

        public int getIdSilaboEvento() {
            return idSilaboEvento;
        }

        public TareasUI getTareasUI() {
            return tareasUI;
        }

        public int getIdOpcion() {
            return idOpcion;
        }

        public int getIdUnidadAprendizaje() {
            return idUnidadAprendizaje;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getInstruciones() {
            return instruciones;
        }

        public long getFechaEntrega() {
            return fechaEntrega;
        }

        public String getHoraEntrega() {
            return horaEntrega;
        }

        public List<RepositorioFileUi> getRecursosUIList() {
            return recursosUIList;
        }

        @Override
        public String toString() {
            return "RequestValues{" +
                    "tareasUI=" + tareasUI +
                    ", idOpcion=" + idOpcion +
                    ", idUnidadAprendizaje=" + idUnidadAprendizaje +
                    ", idSesionAprendizaje=" + idSesionAprendizaje +
                    ", idSilaboEvento=" + idSilaboEvento +
                    ", idUsuario=" + idUsuario +
                    ", titulo='" + titulo + '\'' +
                    ", instruciones='" + instruciones + '\'' +
                    ", fechaEntrega='" + fechaEntrega + '\'' +
                    ", horaEntrega='" + horaEntrega + '\'' +
                    ", mIdCurso=" + mIdCurso +
                    ", estadoTarea=" + estadoTarea +
                    '}';
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final boolean sucess;
        private final TareasUI tareasUI;

        public ResponseValue(boolean sucess, TareasUI tareasUI) {
            this.sucess = sucess;
            this.tareasUI = tareasUI;
        }

        public boolean isSucess() {
            return sucess;
        }

        public TareasUI getTareasUI() {
            return tareasUI;
        }
    }
}
