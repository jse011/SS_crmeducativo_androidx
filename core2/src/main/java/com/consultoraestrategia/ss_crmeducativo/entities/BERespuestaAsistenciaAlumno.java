package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 02/08/2017.
 */

public class BERespuestaAsistenciaAlumno {
    public int IdServer;

    public long IdAndroid;

    private MensajeUsuarioR MensajeUsuario;


    public BERespuestaAsistenciaAlumno() {
    }

    public BERespuestaAsistenciaAlumno(int idServer, long idAndroid) {
        IdServer = idServer;
        IdAndroid = idAndroid;
    }

    public MensajeUsuarioR getMensajeUsuario() {
        return MensajeUsuario;
    }

    public void setMensajeUsuario(MensajeUsuarioR mensajeUsuario) {
        MensajeUsuario = mensajeUsuario;
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
