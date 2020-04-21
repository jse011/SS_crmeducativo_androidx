package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 27/07/2017.
 */
@Table(database = AppDatabase.class)
public class IndicarLogro extends BaseModel {
    @PrimaryKey
    private int indicadorLogroId;
    @Column
    private String titulo;
    @Column
    private String descripcion;
    @Column
    private boolean estado;
    @Column
    private int competenciaId;
    @Column
    private int planCursoId;
    @Column
    private int usuarioCreador;
    @Column
    private String fechaCreacion;
    @Column
    private int usuarioAccion;
    @Column
    private String fechaAccion;
    @Column
    private int silaboEventoId;

    public IndicarLogro() {
    }

    public IndicarLogro(int indicadorLogroId, String titulo, String descripcion, boolean estado, int competenciaId, int planCursoId, int usuarioCreador, String fechaCreacion, int usuarioAccion, String fechaAccion, int silaboEventoId) {
        this.indicadorLogroId = indicadorLogroId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.competenciaId = competenciaId;
        this.planCursoId = planCursoId;
        this.usuarioCreador = usuarioCreador;
        this.fechaCreacion = fechaCreacion;
        this.usuarioAccion = usuarioAccion;
        this.fechaAccion = fechaAccion;
        this.silaboEventoId = silaboEventoId;
    }

    public int getIndicadorLogroId() {
        return indicadorLogroId;
    }

    public void setIndicadorLogroId(int indicadorLogroId) {
        this.indicadorLogroId = indicadorLogroId;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getPlanCursoId() {
        return planCursoId;
    }

    public void setPlanCursoId(int planCursoId) {
        this.planCursoId = planCursoId;
    }

    public int getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(int usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(int usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }
}
