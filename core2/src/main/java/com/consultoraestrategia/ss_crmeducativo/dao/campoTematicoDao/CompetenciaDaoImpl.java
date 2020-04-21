package com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class CompetenciaDaoImpl extends BaseIntegerDaoImpl<Competencia, Competencia_Table> implements CompetenciaDao {

    private static CompetenciaDao sInstance = null;
    private IndicadorDao indicadorDao;


    public static CompetenciaDao getInstance(IndicadorDao indicadorDao) {
        if (sInstance == null) {
            sInstance = new CompetenciaDaoImpl(indicadorDao);
        }
        return sInstance;
    }

    private CompetenciaDaoImpl(IndicadorDao indicadorDao) {
        this.indicadorDao = indicadorDao;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Competencia_Table.competenciaId;
    }

    @Override
    protected Class<Competencia> getEntityClass() {
        return Competencia.class;
    }

    @Override
    protected Class<Competencia_Table> getTableclass() {
        return Competencia_Table.class;
    }

    @Override
    public List<Competencia> getCompetencias(int silaboEventoId, int calendarioPeriodoId) {
        return SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Capacidad")
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                .innerJoin(CompetenciaUnidad.class)
                .on(CompetenciaUnidad_Table.competenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable((NameAlias.builder("Capacidad").build()))))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable()
                        .eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()
                        .eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(Tipos_Table.tipoId.withTable()
                        .eq(CalendarioPeriodo_Table.tipoId.withTable()))
                .where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(calendarioPeriodoId))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .limit(20)
                .queryList();
    }

    @Override
    public List<Competencia> getCompetenciasHastaCampoTematico(int silaboEventoId, int calendarioPeriodoId) {
        List<Competencia> competencias = getCompetencias(silaboEventoId, calendarioPeriodoId);
        for (Competencia competencia :
                competencias) {
            int competenciaId = competencia.getCompetenciaId();
            List<Competencia> capacidades = getCapacidades(silaboEventoId, calendarioPeriodoId, competenciaId);
            for (Competencia capacidad :
                    capacidades) {
                int capacidadId = capacidad.getCompetenciaId();
                List<Icds> indicadores = indicadorDao.getIndicadoresConCampoTematico(silaboEventoId, capacidadId);
                capacidad.setIndicadores(indicadores);
            }
            competencia.setCapacidades(capacidades);
        }
        return competencias;
    }

    @Override
    public List<Competencia> getCapacidades(int silaboEventoId, int calendarioPeriodoId, int competenciaId) {
        return SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Competecia_Padre")
                .on(Competencia_Table.superCompetenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build())))
                .innerJoin(CompetenciaUnidad.class)
                .on(CompetenciaUnidad_Table.competenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable()
                        .eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()
                        .eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(Tipos_Table.tipoId.withTable()
                        .eq(CalendarioPeriodo_Table.tipoId.withTable()))
                .where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(calendarioPeriodoId))
                .and(Competencia_Table.superCompetenciaId.withTable().is(competenciaId))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }

    @Override
    public List<Competencia> getCompetenciasPorSesionAprendizaje(int sesionAprendizajeId) {
        return SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Capacidad")
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build())
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable()))
                .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }

    @Override
    public List<Competencia> getCapacidadesPorCompetenciaYSesion(int competenciaParentId, int sesionAprendizajeId) {
        return SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Competecia_Padre")
                .on(Competencia_Table.superCompetenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build())))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable()))
                .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .and(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build()).is(competenciaParentId))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }

    @Override
    public Competencia obtenerCompenciaPorCapacidad(int capacidadId) {
        return SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Capacidad")
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                .where(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build()).is(capacidadId))
                .querySingle();
    }

    @Override
    public Competencia getCompetenciaRubro(String rubroEvalProcesoId) {
        return SQLite.select()
                .from(Competencia.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.competenciaId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.withTable().eq(rubroEvalProcesoId))
                .querySingle();
    }

    @Override
    public TreeMap<Integer, Competencia> getCompetenciaRubro(List<String> rubroEvalProcesoKeyList) {


        List<Competencia> competenciaList = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(Competencia.class).as("Capacidad")
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on((Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build())
                        .eq(RubroEvaluacionProcesoC_Table.competenciaId.withTable())))
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvalProcesoKeyList))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        List<Competencia> capacidadList = SQLite.select(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .from(Competencia.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on((Competencia_Table.competenciaId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.competenciaId.withTable())))
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvalProcesoKeyList))
                .groupBy(Utils.f_allcolumnTable(Competencia_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        TreeMap<Integer, Competencia> keyCapacidadCompetenciaList = new TreeMap<>();
        for (Competencia itemCapacidad : capacidadList){
            for(Competencia itemCompetencia: competenciaList){
                if(itemCapacidad.getSuperCompetenciaId() == itemCompetencia.getCompetenciaId()){
                    keyCapacidadCompetenciaList.put(itemCapacidad.getCompetenciaId(),itemCompetencia);
                    break;
                }
            }
        }
        return keyCapacidadCompetenciaList;

    }


}
