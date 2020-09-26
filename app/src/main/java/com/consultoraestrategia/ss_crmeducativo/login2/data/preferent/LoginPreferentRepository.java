package com.consultoraestrategia.ss_crmeducativo.login2.data.preferent;

import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;

import java.util.List;

public interface LoginPreferentRepository {
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    List<PersonaUi> getTodosUsuarios();

    void guardarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback);

    void eliminarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback);

    void elimarTodosUsuario(Callback<Boolean> usuarioCallback);

    void guardarWebConfig(List<WebConfig> webConfigs);

    void elimarWebConfig();

    WebConfig getWebConfig(String key);

    List<ServiceEnvioFbUi> getListaCambios();

    void eliminarCambios();

   void saveCambiosFirebase(List<ServiceEnvioFbUi> serviceEnvioUi);

    long getFechaCambiosFirebase();

    long getFechaCambiosResultados();

    void clearCambiosFirebase();

    void saveFechaCambiosFirebase(long fecha);

    void saveFechaCambiosResultados(long fecha);

    void saveCambiosFirebaseResultados(List<ServiceEnvioFbUi> serviceEnvioUi);

    List<ServiceEnvioFbUi> getListaCambiosResultados();
}
