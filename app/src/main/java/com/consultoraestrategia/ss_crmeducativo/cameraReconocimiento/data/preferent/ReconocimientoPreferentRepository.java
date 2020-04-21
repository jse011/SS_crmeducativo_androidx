package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.preferent;


import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

import java.util.List;

public interface ReconocimientoPreferentRepository {
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    List<SentimientoUi> getFotos(int personaId);

    void guardarFotos(List<SentimientoUi> fotos, int personaId, Callback<Boolean> usuarioCallback);

    void eliminarFotos(int personaId, Callback<Boolean> usuarioCallback);

}
