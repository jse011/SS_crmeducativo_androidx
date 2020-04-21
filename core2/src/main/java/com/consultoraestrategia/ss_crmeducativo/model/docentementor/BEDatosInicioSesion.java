package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion;

import java.util.List;

public class BEDatosInicioSesion {
    private List<AnioAcademico> anioAcademicos;

    private List<Empleado> empleados;

    private List<ParametroConfiguracion> parametroConfiguracion;

    private int anioAcademicoId;

    public List<AnioAcademico> getAnioAcademicos() {
        return anioAcademicos;
    }

    public void setAnioAcademicos(List<AnioAcademico> anioAcademicos) {
        this.anioAcademicos = anioAcademicos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public int getAnioAcademicoId() {
        return anioAcademicoId;
    }

    public void setAnioAcademicoId(int anioAcademicoId) {
        this.anioAcademicoId = anioAcademicoId;
    }

    public List<ParametroConfiguracion> getParametroConfiguracion() {
        return parametroConfiguracion;
    }

    public void setParametroConfiguracion(List<ParametroConfiguracion> parametroConfiguracion) {
        this.parametroConfiguracion = parametroConfiguracion;
    }
}
