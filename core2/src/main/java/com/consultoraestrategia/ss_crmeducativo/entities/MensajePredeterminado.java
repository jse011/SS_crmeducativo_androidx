package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by irvinmarin on 09/08/2018.
 */
@Table(database = AppDatabase.class)
public class MensajePredeterminado extends BaseEntity {

    @Column
    private String mensajePredeterminadoId;
    @Column
    private String asunto;
    @Column
    private String cabecera;
    @Column
    private String presentacion;
    @Column
    private String cuerpo;
    @Column
    private String despedida;
    @Column
    private int alcanceMensajeId;
    @Column
    private int objetivoMensajeId;
    @Column
    private int estadoId;

    public MensajePredeterminado() {
    }

    public static List<MensajePredeterminado> getMesnajeListByStateDeleted() {
        return SQLite.select()
                .from(MensajePredeterminado.class)
                .where(MensajePredeterminado_Table.estadoId.is(327))
                .queryList();
    }

    public String getMensajePredeterminadoId() {
        return mensajePredeterminadoId;
    }

    public void setMensajePredeterminadoId(String mensajePredeterminadoId) {
        this.mensajePredeterminadoId = mensajePredeterminadoId;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getDespedida() {
        return despedida;
    }

    public void setDespedida(String despedida) {
        this.despedida = despedida;
    }

    public int getAlcanceMensajeId() {
        return alcanceMensajeId;
    }

    public void setAlcanceMensajeId(int alcanceMensajeId) {
        this.alcanceMensajeId = alcanceMensajeId;
    }

    public int getObjetivoMensajeId() {
        return objetivoMensajeId;
    }

    public void setObjetivoMensajeId(int objetivoMensajeId) {
        this.objetivoMensajeId = objetivoMensajeId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public static List<MensajePredeterminado> getMesnajesListByObtiveIsntDeleted(int idTipoMensaje) {

        return SQLite.select()
                .from(MensajePredeterminado.class)
                .where(MensajePredeterminado_Table.objetivoMensajeId.is(idTipoMensaje))
                .and(MensajePredeterminado_Table.estadoId.isNot(327))
                .queryList();
    }

    public static MensajePredeterminado getMesnajeById(String keyMensajePred) {
        return SQLite.select()
                .from(MensajePredeterminado.class)
                .where(MensajePredeterminado_Table.key.is(keyMensajePred))
                .querySingle();
    }
}
