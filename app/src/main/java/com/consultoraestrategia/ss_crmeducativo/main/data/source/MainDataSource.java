package com.consultoraestrategia.ss_crmeducativo.main.data.source;

import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioExternoUi;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetAccesosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetCursosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetHijosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetUsuarioCallback;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosInicioSesion;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface MainDataSource {

    boolean succesData();

    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }


    void getAccesosUIList(int idUsuario, int hijo, GetAccesosListCallback callback);

    void getHijosUIList(int idUsuario, int idHijo, GetHijosListCallback callback);

    void getCursosUIList(int idUsuario, int idPrograma, int idAnioAcademico, GetCursosListCallback callback);

    void getUsuarioUI(GetUsuarioCallback callback);

    void getPeriodoList(int anioAcademicoId, int programaEducativoId,SucessCallback<List<PeriodoUi>> sucessCallback );
    void getGradosList(int idPrograma, int idUsuario, int idAnioAcademico,SucessCallback<List<GradoUi>> sucessCallback);
    boolean saveAlarma(int hora, int minute);
    AlarmaUi getHoraAlarma();

    List<AnioAcademicoUi> getListAnioAcademico(int usuarioId);

    List<ProgramaEduactivosUI> getListProgramaEducativo(int anioCademico, int usuarioId);

    RetrofitCancel getDatosInicioSesion (int empleadoId, int anioId, MainDataSource.SucessCallback<Boolean> callback);

    RetrofitCancel updateListAnioAcademico(int usuarioId, SucessCallback<Boolean> callback);
}
