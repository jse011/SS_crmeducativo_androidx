package com.consultoraestrategia.ss_crmeducativo.login2.entities;

public class ServiceEnvioUi {
    private int uploadProgress;
    private int uploadRubroProgress;//Solo cuando se sincroniza los rubros primero
    private int dowloadProgress;
    private boolean activo;
    private boolean encoloa;
    private int success;
    private int programaEducativoId;
    private boolean syncrono;

    public void setUploadProgress(int uploadProgress) {
        this.uploadProgress = uploadProgress;
    }

    public int getUploadProgress() {
        return uploadProgress;
    }

    public void setDowloadProgress(int dowloadProgress) {
        this.dowloadProgress = dowloadProgress;
    }

    public int getDowloadProgress() {
        return dowloadProgress;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isEncoloa() {
        return encoloa;
    }

    public void setEncoloa(boolean encoloa) {
        this.encoloa = encoloa;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getSuccess() {
        return success;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setSyncrono(boolean syncrono) {
        this.syncrono = syncrono;
    }

    public boolean isSyncrono() {
        return syncrono;
    }

    public enum Tipo{TipoNota, Rubrica, Resultado, Rubro, Formula, Unidades, Grupos, Tareas, Casos, Asistencias, Estudiantes, CerrarCurso, SessionAlumno, TareaAlumno, ResultadosAcademico}

    private String nombre;
    private Tipo tipo = Tipo.Rubrica;
    private String descripcion;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getUploadRubroProgress() {
        return uploadRubroProgress;
    }

    public void setUploadRubroProgress(int uploadRubroProgress) {
        this.uploadRubroProgress = uploadRubroProgress;
    }
}
