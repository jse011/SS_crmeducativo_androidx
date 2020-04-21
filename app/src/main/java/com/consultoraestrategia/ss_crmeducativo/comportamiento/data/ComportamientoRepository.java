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

public class ComportamientoRepository implements  ComportamientoDataSource {

    ComportamientoDataLocalSource comportamientoDataLocalSource;

    public ComportamientoRepository(ComportamientoDataLocalSource comportamientoDataLocalSource) {
        this.comportamientoDataLocalSource = comportamientoDataLocalSource;
    }

    @Override
    public void getListComportamiento(int programaEducativoId, int cargaAcademicaId, int cargaCursoId, int docenteId,int idcalendarioPeriodo,int georeferenciaId,int entidadId ,SucessCallback<List<AlumnoUi>> callback) {
        comportamientoDataLocalSource.getListComportamiento(programaEducativoId, cargaAcademicaId, cargaCursoId, docenteId,idcalendarioPeriodo,georeferenciaId, entidadId,callback);
    }

    @Override
    public void getListComportamientoAlumno(int programaEducativoId, int cargaAcademicaId, int cargaCursoId, int docenteId,  int idcalendarioPeriodo ,int idalumno, SucessCallback<AlumnoUi> callback) {
        comportamientoDataLocalSource.getListComportamientoAlumno(programaEducativoId, cargaAcademicaId, cargaCursoId, docenteId, idcalendarioPeriodo,idalumno, callback);
    }

    @Override
    public void getCurso(int idcargaCurso, int alumnoId, SucessCallback<CursoUi> callback) {
        comportamientoDataLocalSource.getCurso(idcargaCurso, alumnoId, callback);
    }

    @Override
    public void deleteComportamiento(String comportamientoId, CallbackSuccess callbackSuccess) {
        comportamientoDataLocalSource.deleteComportamiento(comportamientoId, callbackSuccess);
    }

    @Override
    public void getAlumnos(int cargaCursoId, SucessCallback<List<AlumnoUi>> callback) {
        comportamientoDataLocalSource.getAlumnos(cargaCursoId, callback);
    }

    @Override
    public boolean saveComportamiento(ComportamientoUi comportamientoUi, DestinoUi destinoUi, List<RepositorioFileUi> repositorioFileUiList) {
        return comportamientoDataLocalSource.saveComportamiento(comportamientoUi, destinoUi, repositorioFileUiList);
    }

    @Override
    public  List<TipoUi> getTipos(int georeferenciaId, int entidadId) {
     return comportamientoDataLocalSource.getTipos(georeferenciaId, entidadId);
    }

    @Override
    public ComportamientoUi getComportamiento(String idComportamiento) {
        return  comportamientoDataLocalSource.getComportamiento(idComportamiento);
    }

    @Override
    public ParametroDisenioUi getParametroDisenio(int idcargaCurso) {
        return comportamientoDataLocalSource.getParametroDisenio(idcargaCurso);
    }

    @Override
    public void getUsuariosDestino(int georeferenciaId, SucessCallback<List<UsuarioUi>> sucessCallback) {
        comportamientoDataLocalSource.getUsuariosDestino(georeferenciaId, sucessCallback);
    }

    @Override
    public DestinoUi getDestino(String idComportamiento, int georeferencia) {
        return comportamientoDataLocalSource.getDestino(idComportamiento, georeferencia);
    }

    @Override
    public List<Integer> validarUsuario(DestinoUi.Tipo tipo, int alumnoId, int cargaACademicaId, int georeferenciaId) {
        return comportamientoDataLocalSource.validarUsuario(tipo, alumnoId, cargaACademicaId, georeferenciaId);
    }

    @Override
    public void getComporamientosDestinosList(int docente, int alumnoId, SucessCallback<List<ComportamientoUi>> callback) {
        comportamientoDataLocalSource.getComporamientosDestinosList(docente,alumnoId,callback);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, CallbackSuccess callback) {
        comportamientoDataLocalSource.updateSucessDowload(archivoId, path, callback);
    }


}
