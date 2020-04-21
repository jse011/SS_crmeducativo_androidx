package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class TecnicaEvaluacion extends BaseModel {

    @PrimaryKey
    @Column
    private int tecnicaEvaluacionId;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private String procedimiento;
    @Column
    private boolean estado;
    @Column
    private int usuarioCreacionId;
    @Column
    private int usuarioCreadorId;
    @Column
    private long fechaCreacion;
    @Column
    private int usuarioAccionId;
    @Column
    private long fechaAccion;
    @Column
    private long fechaEnvio;
    @Column
    private long fechaEntrega;

    @Column
    private long fechaRecibido;
    @Column
    private long fechaVisto;
    @Column
    private long fechaRespuesta;

    @Column
    private String time;


    public int getTecnicaEvaluacionId() {
        return tecnicaEvaluacionId;
    }

    public void setTecnicaEvaluacionId(int tecnicaEvaluacionId) {
        this.tecnicaEvaluacionId = tecnicaEvaluacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public int getUsuarioCreadorId() {
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(int usuarioCreadorId) {
        this.usuarioCreadorId = usuarioCreadorId;
    }

    public long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public long getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(long fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public long getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(long fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public long getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(long fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public long getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(long fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public long getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(long fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    public long getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(long fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
