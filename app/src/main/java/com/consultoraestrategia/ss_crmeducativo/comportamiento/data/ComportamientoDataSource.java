package com.consultoraestrategia.ss_crmeducativo.comportamiento.data;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public interface ComportamientoDataSource {

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }
    interface Callback<T> {
        void onLoad(boolean success);
    }
    interface CallbackSuccess {
        void onLoad(boolean success);
    }

    void getListComportamiento(int programaEducativoId, int cargaAcademicaId,int cargaCursoId,int docenteId, int idcalendarioPeriodo,int georeferenciaId,int entidadId, SucessCallback<List<AlumnoUi>> callback );
    void getListComportamientoAlumno(int programaEducativoId, int cargaAcademicaId,int cargaCursoId,int docenteId,  int idcalendarioPeriodo ,int idalumno, SucessCallback<AlumnoUi> callback );
    void getCurso(int idcargaCurso, int alumnoId, SucessCallback<CursoUi> callback);
    void deleteComportamiento(String comportamientoId, CallbackSuccess callbackSuccess);
    void getAlumnos(int cargaCursoId,SucessCallback<List<AlumnoUi>> callback );
    boolean saveComportamiento(ComportamientoUi comportamientoUi, DestinoUi destinoUi, List<RepositorioFileUi> repositorioFileUiList);
    List<TipoUi> getTipos(int georeferenciaId, int entidadId);
    ComportamientoUi getComportamiento(String idComportamiento);
    ParametroDisenioUi getParametroDisenio(int idcargaCurso);
    void getUsuariosDestino(int georeferenciaId ,SucessCallback<List<UsuarioUi>> sucessCallback);
    DestinoUi getDestino(String idComportamiento, int georeferencia);
    void getComporamientosDestinosList(int docente,int alumnoId,  SucessCallback<List<ComportamientoUi>> callback );
    void updateSucessDowload(String archivoId, String path, CallbackSuccess callback);

}
