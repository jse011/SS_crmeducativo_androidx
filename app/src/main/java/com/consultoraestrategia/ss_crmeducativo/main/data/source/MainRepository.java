package com.consultoraestrategia.ss_crmeducativo.main.data.source;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetAccesosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetCursosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetHijosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetUsuarioCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.local.LocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.remote.RemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class MainRepository implements MainDataSource {
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    public MainRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }
//
//    public static MainRepository getInstace(CameraReconocimientoLocalDataSource localDataSource, CameraReconocimientoRemoteDataSource remoteDataSource) {
//
//        if (INSTANCE == null) {
//            INSTANCE = new MainRepository(localDataSource, remoteDataSource);
//        }
//        return INSTANCE;
//    }
//
//    private static MainRepository INSTANCE = null;


    @Override
    public boolean succesData() {
        return localDataSource.succesData();
    }

    @Override
    public void getAccesosUIList(int idUsuario, int idHijo, GetAccesosListCallback callback) {
        localDataSource.getAccesosUIList(idUsuario, idHijo, callback);
        remoteDataSource.getAccesosUIList(idUsuario, idHijo, callback);
    }

    @Override
    public void getHijosUIList(int idUsuario, int idHijo, GetHijosListCallback callback) {
        localDataSource.getHijosUIList(idUsuario, idHijo, callback);
        remoteDataSource.getHijosUIList(idUsuario, idHijo, callback);
    }

    @Override
    public void getCursosUIList(int idUsuario, int idPrograma, int idAnioAcademico, GetCursosListCallback callback) {
        localDataSource.getCursosUIList(idUsuario, idPrograma, idAnioAcademico, callback);
//        remoteDataSource.getCursosUIList(idUsuario, idPrograma, callback);
    }


    @Override
    public void getUsuarioUI(GetUsuarioCallback callback) {
        localDataSource.getUsuarioUI(callback);
        remoteDataSource.getUsuarioUI(callback);
    }

    @Override
    public void getPeriodoList(int anioAcademicoId, int programaEducativoId, SucessCallback<List<PeriodoUi>> sucessCallback) {
        localDataSource.getPeriodoList(anioAcademicoId,programaEducativoId, sucessCallback);
        remoteDataSource.getPeriodoList(anioAcademicoId,programaEducativoId, sucessCallback);
    }

    @Override
    public void getGradosList(int idPrograma, int idUsuario, int idAnioAcademico, SucessCallback<List<GradoUi>> sucessCallback) {
        localDataSource.getGradosList(idPrograma, idUsuario, idAnioAcademico, sucessCallback);
        remoteDataSource.getGradosList(idPrograma, idUsuario, idAnioAcademico, sucessCallback);
    }

    @Override
    public boolean saveAlarma(int hora, int minute) {
        return localDataSource.saveAlarma(hora, minute);
    }

    @Override
    public AlarmaUi getHoraAlarma() {
        return localDataSource.getHoraAlarma();
    }

    @Override
    public List<AnioAcademicoUi> getListAnioAcademico(int usuarioId) {
        return localDataSource.getListAnioAcademico(usuarioId);
    }

    @Override
    public List<ProgramaEduactivosUI> getListProgramaEducativo(int anioCademico, int usuarioId) {
        return localDataSource.getListProgramaEducativo(anioCademico,usuarioId);
    }

    @Override
    public RetrofitCancel getDatosInicioSesion(int empleadoId, int anioId, SucessCallback<Boolean> callback) {
        return remoteDataSource.getDatosInicioSesion(empleadoId, anioId, callback);
    }

    @Override
    public RetrofitCancel updateListAnioAcademico(int usuarioId, SucessCallback<Boolean> callback) {
        return remoteDataSource.updateListAnioAcademico(usuarioId, callback);
    }


}
