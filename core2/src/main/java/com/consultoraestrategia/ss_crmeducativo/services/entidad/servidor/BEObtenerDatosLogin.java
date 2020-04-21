package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.ColorCondicionalC;
import com.consultoraestrategia.ss_crmeducativo.entities.CuentaCorriente;
import com.consultoraestrategia.ss_crmeducativo.entities.CursoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Estados;
import com.consultoraestrategia.ss_crmeducativo.entities.IndicarLogro;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursosAlumno;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Ubicaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEObtenerDatosLogin  extends BEDatosServidor {
    //public List<BETipos> obtenerTipos { get; set; }
    private List<AnioAcademico> anioAcademicos;
    private List<CuentaCorriente> cuentaCorriente;
    private List<PlanCursosAlumno> planCursosAlumno;
    private List<Estados> estados;
    private List<Tipos> tipos;
    private List<Persona> personas;
    private List<Relaciones> relaciones;
    //public List<BECompetencia> competencias_sesion_user { get; set; }
    private List<CursoCompetencia> cursoCompetencias;//1

    private List<ColorCondicionalC> colorCondicional;

    private List<IndicarLogro> indicarLogro;//2
    private List<Usuario> usuariosrelacionados;
    //public List<BE_ConfAlumno_All> obtenerAlumnosMatriculadosRubroEvalResult { get; set; }
    //public List<BE_ConfAlumno_All> obtener_alumnosmatriculadosasistenciaseccion { get; set; }
    private List<ParametrosDisenio> obtener_parametros_disenio;
    private List<Rutas> rutas;
    private List<ParametroConfiguracion> parametroConfiguracion;
    private List<Ubicaciones> ubicaciones;

    public BEObtenerDatosLogin() {
    }



    public List<AnioAcademico> getAnioAcademicos() {
        return anioAcademicos;
    }

    public List<CuentaCorriente> getCuentaCorriente() {
        return cuentaCorriente;
    }

    public List<PlanCursosAlumno> getPlanCursosAlumno() {
        return planCursosAlumno;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public List<Relaciones> getRelaciones() {
        return relaciones;
    }

    public List<CursoCompetencia> getCursoCompetencias() {
        return cursoCompetencias;
    }

    public List<ColorCondicionalC> getColorCondicional() {
        return colorCondicional;
    }

    public List<IndicarLogro> getIndicarLogro() {
        return indicarLogro;
    }

    public List<Usuario> getUsuariosrelacionados() {
        return usuariosrelacionados;
    }

    public List<ParametrosDisenio> getObtener_parametros_disenio() {
        return obtener_parametros_disenio;
    }

    public List<Rutas> getRutas() {
        return rutas;
    }

    public List<ParametroConfiguracion> getParametroConfiguracions() {
        return parametroConfiguracion;
    }

    public List<Ubicaciones> getUbicaciones() {
        return ubicaciones;
    }

    @Override
    public String toString() {
        return "BEObtenerDatosLogin{" +
                "anioAcademicos=" + anioAcademicos +
                ", cuentaCorriente=" + cuentaCorriente +
                ", planCursosAlumno=" + planCursosAlumno +
                ", estados=" + estados +
                ", tipos=" + tipos +
                ", personas=" + personas +
                ", relaciones=" + relaciones +
                ", cursoCompetencias=" + cursoCompetencias +
                ", colorCondicional=" + colorCondicional +
                ", indicarLogro=" + indicarLogro +
                ", usuariosrelacionados=" + usuariosrelacionados +
                ", obtener_parametros_disenio=" + obtener_parametros_disenio +
                ", rutas=" + rutas +
                ", parametroConfiguracion=" + parametroConfiguracion +
                '}';
    }
}
