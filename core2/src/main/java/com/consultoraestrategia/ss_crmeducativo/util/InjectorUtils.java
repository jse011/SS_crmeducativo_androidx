package com.consultoraestrategia.ss_crmeducativo.util;

import com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso.TareaRubroEvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso.TareaRubroEvaluacionProcesoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao.AlumnoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao.AlumnoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.AsistenciaSesionAlumnoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.AsistenciaSesionAlumnoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CampoTematicoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CampoTematicoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.comportamientoDao.ComportamientoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.comportamientoDao.ComportamientoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao.EscalaEvaluacionDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.recursoDidacticoEventoDao.RecursoDidacticoEventoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.recursoDidacticoEventoDao.RecursoDidacticoEventoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SesionUserDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.dao.silaboEventoDao.SilaboEventoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.silaboEventoDao.SilaboEventoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasRecursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tareasDao.TareasRecursoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TipoEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TipoEvaluacionDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDatoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.unidadAprendizajeDao.UnidadAprendizajeDao;
import com.consultoraestrategia.ss_crmeducativo.dao.unidadAprendizajeDao.UnidadAprendizajeDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.cargaCursoDao.CargaCursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.cargaCursoDao.CargaCursoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.desempenioIcdDao.DesempenioIcdDao;
import com.consultoraestrategia.ss_crmeducativo.dao.desempenioIcdDao.DesempenioIcdDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.dia.DiaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dia.DiaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.equipo.EquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.equipo.EquipoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.equipoEvaluacionProceso.EquipoEvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.equipoEvaluacionProceso.EquipoEvaluacionProcesoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso.EvaluacionProcesoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo.GrupoDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo.GrupoDeEquipoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.horarioPrograma.HorarioProgramaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.horarioPrograma.HorarioProgramaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.instrumentoObservada.InstrumentoObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.instrumentoObservada.InstrumentoObservadaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo.IntegranteDeEquipoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo.IntegranteDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEquipo.RubroEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEquipo.RubroEquipoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDaoImpl;

import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.RubroEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.RubroEvaluacionDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado.RubroEvaluacionResultadoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado.RubroEvaluacionResultadoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroIntegrante.RubroIntegranteDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroIntegrante.RubroIntegranteDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDaoImpl;

/**
 * Created by @stevecampos on 19/04/2018.
 */

public class InjectorUtils {

    public static CampoTematicoDao provideCampoTematicoDao() {
        return CampoTematicoDaoImpl.getInstance();
    }

    public static IndicadorDao provideIndicadorDao() {
        return IndicadorDaoImpl.getInstance(provideCampoTematicoDao());
    }

    public static CompetenciaDao provideCompetenciaDao() {
        return CompetenciaDaoImpl.getInstance(provideIndicadorDao());
    }

    public static EscalaEvaluacionDao provideEscalaEvaluacionDao() {
        return EscalaEvaluacionDaoImpl.getInstance();
    }

    public static SilaboEventoDao provideSilaboEventoDao() {
        return SilaboEventoDaoImpl.getInstance();
    }

    public static TipoEvaluacionDao provideTipoEvaluacionDao() {
        return TipoEvaluacionDaoImpl.getInstance();
    }

    public static ValorTipoNotaDao provideValorTipoNotaDao() {
        return ValorTipoNotaDaoImpl.getInstance();
    }

    public static TipoNotaDao provideTipoNotaDao() {
        return TipoNotaDatoImpl.getInstance(provideValorTipoNotaDao(),
                provideEscalaEvaluacionDao());
    }

    public static TiposDao provideTiposDao() {
        return TiposDaoImpl.getInstance();
    }

    public static AsistenciaSesionAlumnoDao provideAsistenciaSesionAlumnoDao() {
        return AsistenciaSesionAlumnoDaoImpl.getInstance(providePersonaDao(),
                provideDiaDao(),
                provideCalendarioPeriodo(),
                provideValorTipoNotaDao(),
                provideTipoNotaDao());
    }

    public static RubroEvaluacionDao provideRubroEvaluacionDao() {
        return RubroEvaluacionDaoImpl.getInstance();
    }

    public static IntegranteDeEquipoDao provideIntegranteDeEquipoDao() {
        return IntegranteDeEquipoDaoImpl.getInstance(
                providePersonaDao()
        );
    }

    public static EquipoDao provideEquipoDao() {
        return EquipoDaoImpl.getInstance(provideIntegranteDeEquipoDao());
    }

    public static GrupoDeEquipoDao provideGrupoDeEquipoDao() {
        return GrupoDeEquipoDaoImpl.getInstance(provideEquipoDao());
    }

    public static PersonaDao providePersonaDao() {
        return PersonaDaoImpl.getInstance();
    }

    public static RubroProcesoDao provideRubroProcesoDao() {
        return RubroProcesoDaoImpl.getInstance(provideRubroEvalRNPFormulaDao());
    }

    public static RubroEvalRNPFormulaDao provideRubroEvalRNPFormulaDao() {
        return RubroEvalRNPFormulaDaoImpl.getInstance();
    }

    public static EvaluacionProcesoDao provideEvaluacionProcesoDao() {
        return EvaluacionProcesoDaoImpl.getInstance(
                providePersonaDao(),
                provideRubroEvalRNPFormulaDao(),
                provideEscalaEvaluacionDao(),
                provideTipoNotaDao(),
                provideTiposDao());
    }


    public static TareasDao provideTareasDao() {
        return TareasDaoImpl.getInstance();
    }

    public static TareasRecursoDao provideTareasRecursoDao() {
        return TareasRecursoDaoImpl.getInstance();
    }

    public static RecursoDidacticoEventoDao provideRecursoDidacticoEventoDao() {
        return RecursoDidacticoEventoDaoImpl.getInstance();
    }

    public static UnidadAprendizajeDao provideUnidadAprendizajeDao() {
        return UnidadAprendizajeDaoImpl.getInstance();
    }

    public static RubroEvaluacionResultadoDao provideRubroEvaluacionResultadoDao() {
        return RubroEvaluacionResultadoDaoImpl.getInstance();
    }

    public static DimensionObservadaDao provideDimensionObservadaDao(){
        return DimensionObservadaDaoImpl.getInstance();
    }

    public static DesempenioIcdDao provideDesempenioDao() {
        return DesempenioIcdDaoImpl.getInstance();
    }

    public static CalendarioPeriodoDao provideCalendarioPeriodo() {
        return CalendarioPeriodoDaoImpl.getInstance();
    }

    public static EquipoEvaluacionProcesoDao provideEquipoEvaluacionProcesoDao() {
        return EquipoEvaluacionProcesoDaoImpl.getInstance();
    }

    public static RubroEquipoDao provideRubroEquipo() {
        return RubroEquipoDaoImpl.getInstance();
    }

    public static RubroIntegranteDao provideRubroIntegrateDao() {
        return RubroIntegranteDaoImpl.getInstance();
    }

    public static InstrumentoObservadaDao provideInstrumentoObservadaDao() {
        return InstrumentoObservadaDaoImpl.getInstance();
    }

    public static HorarioProgramaDao provideHorarioProgramaDao() {
        return HorarioProgramaDaoImpl.getInstance();
    }

    public static DiaDao provideDiaDao() {
        return DiaDaoImpl.getInstance();
    }

    public static CursoDao provideCursoDao() {
        return CursoDaoImpl.getInstance();
    }

    public static CargaCursoDao provideCargaCursoDao() {
        return CargaCursoDaoImpl.getInstance();
    }

    public static ParametrosDisenioDao provideParametrosDisenioDao() {
        return ParametrosDisenioDaoImpl.getInstance();
    }

    public static SessionUserDao provideSessionUserDao() {
        return SesionUserDaoImpl.getInstance();
    }

    public static ComportamientoDao provideComportamientoDao() {
        return ComportamientoDaoImpl.getInstance();
    }
    public static AlumnoDao provideAlumnoDao() {
        return AlumnoDaoImpl.getInstance();
    }

    public static TareaRubroEvaluacionProcesoDao provideTareaRubroEvaluacionProcesoDao() {
        return TareaRubroEvaluacionProcesoDaoImpl.getInstance();
    }
}
