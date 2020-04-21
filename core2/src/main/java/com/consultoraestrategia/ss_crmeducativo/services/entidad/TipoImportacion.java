package com.consultoraestrategia.ss_crmeducativo.services.entidad;

/**
 * Created by SCIEV on 13/06/2018.
 */

public enum TipoImportacion {
    RUBROEVALUACION("Actualizando los Rubros de Evaluación",
            "Éxito al actualizar los Rubros de Evaluación",
            "Error al actualizar los Rubros de Evaluación"),
    ASISTENCIA("Actualizando las Asistencias",
            "Éxito al actualizar las Asistencias",
            "Error al actualizar las Asistencias"),
    TAREA("Actualizando las Tareas",
            "Éxito al actualizar las Tareas",
            "Error al actualizar las Tareas"),
    SESIONES("Actualizando las Sesiones Aprendizaje",
            "Éxito al actualizar las Sesiones Aprendizaje",
            "Error al actualizar las Sesiones Aprendizaje"),
    STRATEGY_LOGIN("","",""),
    DEFAULT("","",""),
    GRUPO("Actualizando las Listas de Grupos",
            "Éxito al actualizar las Listas de Grupos",
            "Error al actualizar las Listas de Grupos"),
    UNIDAD("Actualizando las Unidades de Aprendizaje",
            "Éxito al actualizar las Unidades de Aprendizaje",
            "Error al actualizar las Unidades de Aprendizaje"),
    GRUPOS("Actualizando las Listas de Grupos",
            "Éxito al actualizar las Listas de Grupos",
            "Error al actualizar las Listas de Grupos"),
    TAREA_UNIDAD("Actualizando las Tareas por Unidad Aprendizaje",
            "Éxito al actualizar las Tareas por Unidad Aprendizaje",
            "Error al actualizar las Tareas por Unidad Aprendizaje"),
    TIPONOTA("Actualizando los Niveles de Logro",
            "Éxito al actualizar los Niveles de Logro",
            "Error al actualizar los Niveles de Logro"),
    CALENDARIO_PERIODO("Actualizando los calendarios periodos",
            "Éxito al actualizar los calendarios periodos",
            "Error al actualizar los calendarios periodos"),
    CONTACTOS("Actualizando los Datos Personales",
            "Éxito al actualizar los Datos Personales",
            "Error al actualizar los Datos Personales"),
    RUBROEVALUACION_CALENDARIO("Actualizando los Rubros de Evaluación por Calendario",
            "Éxito al actualizar los Rubros de Evaluación por Calendario",
            "Error al actualizar los Rubros de Evaluación por Calendario"),
    MENSAJE("Actualizando los Mensajes del Docente",
            "Éxito al actualizar los Mensajes del Docente",
            "Error al actualizar los Mensajes del Docente"),
    ALUMNOVIGENCIA("Actualizando la vigencia de los Alumnos",
            "Éxito al actualizar la vigencia de los Alumnos",
            "Error al actualizar la vigencia de los Alumnos"),
    COMENTARIO("Actualizando los Comentarios del Docente",
            "Éxito al actualizar los Comentarios del Docente",
            "Error al actualizar los Comentarios del Docente");

    String msgExecute;
    String msgSuccess;
    String msgError;

    TipoImportacion(String msgExecute, String msgSuccess, String msgError) {
        this.msgExecute = msgExecute;
        this.msgSuccess = msgSuccess;
        this.msgError = msgError;
    }

    public String getMsgExecute() {
        return msgExecute;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public String getMsgError() {
        return msgError;
    }
}

