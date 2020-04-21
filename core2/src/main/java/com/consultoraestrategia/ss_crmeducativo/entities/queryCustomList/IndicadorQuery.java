package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

/**
 * Created by SCIEV on 11/04/2018.
 */
@QueryModel(database = AppDatabase.class, allFields = true)
public class IndicadorQuery {
    @Column
    public int icdId;
    @Column
    public int desempenioId;
    @Column
    public String titulo;
    @Column
    private String alias;
    @Column
    public String descripcion;
    @Column
    public String usuarioCreadorId;
    @Column
    public String usuarioAccionId;
    @Column
    public String estado;
    @Column
    public String peso;
    @Column
    public int desempenioIcdId;
    @Column
    private String desempenioDesc;
    @Column
    private int tipoId;
    @Column
    private String url;

    public int getIcdId() {
        return icdId;
    }

    public void setIcdId(int icdId) {
        this.icdId = icdId;
    }

    public int getDesempenioId() {
        return desempenioId;
    }

    public void setDesempenioId(int desempenioId) {
        this.desempenioId = desempenioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioCreadorId() {
        return usuarioCreadorId;
    }

    public void setUsuarioCreadorId(String usuarioCreadorId) {
        this.usuarioCreadorId = usuarioCreadorId;
    }

    public String getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(String usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesempenioDesc() {
        return desempenioDesc;
    }

    public void setDesempenioDesc(String desempenioDesc) {
        this.desempenioDesc = desempenioDesc;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
