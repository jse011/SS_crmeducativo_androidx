package com.consultoraestrategia.ss_crmeducativo.entities;

import java.util.List;

/**
 * Created by irvinmarin on 02/08/2017.
 */

public class MensajeUsuarioR {
    public int IdServer;

    public long IdAndroid;

    private List<CanalDestEstadoR> CanalDestEstado;

    public MensajeUsuarioR() {
    }

    public MensajeUsuarioR(int idServer, long idAndroid) {
        IdServer = idServer;
        IdAndroid = idAndroid;
    }

    public List<CanalDestEstadoR> getCanalDestEstado() {
        return CanalDestEstado;
    }

    public void setCanalDestEstado(List<CanalDestEstadoR> canalDestEstado) {
        CanalDestEstado = canalDestEstado;
    }

    public int getIdServer() {
        return IdServer;
    }

    public void setIdServer(int idServer) {
        IdServer = idServer;
    }

    public long getIdAndroid() {
        return IdAndroid;
    }

    public void setIdAndroid(long idAndroid) {
        IdAndroid = idAndroid;
    }
}
