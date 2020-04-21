package com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

/**
 * Created by @stevecampos on 19/04/2018.
 */

public class CampoTematicoDaoImpl extends BaseIntegerDaoImpl<CampoTematico, CampoTematico_Table> implements CampoTematicoDao {

    private static CampoTematicoDao sInstance = null;

    private CampoTematicoDaoImpl() {
    }

    public static CampoTematicoDao getInstance() {
        if (sInstance == null) {
            sInstance = new CampoTematicoDaoImpl();
        }
        return sInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return CampoTematico_Table.campoTematicoId;
    }

    @Override
    protected Class<CampoTematico> getEntityClass() {
        return CampoTematico.class;
    }

    @Override
    protected Class<CampoTematico_Table> getTableclass() {
        return CampoTematico_Table.class;
    }

    @Override
    public List<CampoTematico> getCamposTematicos(int silaboEventoId, int indicadorId) {
        return SQLite.select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                .from(CampoTematico.class)
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.campoTematicoIcd.withTable()
                        .eq(CampoTematico_Table.campoTematicoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.withTable()
                        .eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()
                        .eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()
                        .eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(DesempenioIcd.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.desempenioIcdId.withTable()
                        .eq(DesempenioIcd_Table.desempenioIcdId.withTable()))
                .where(DesempenioIcd_Table.icdId.withTable().is(indicadorId))
                .and(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .queryList();
    }

    @Override
    public List<CampoTematico> getCamposTematicosPorIndicadorYSesionAprendizaje(int indicadorId, int sesionAprendizajeId) {
        return SQLite.select(Utils.f_allcolumnTable(CampoTematico_Table.ALL_COLUMN_PROPERTIES))
                .from(CampoTematico.class)
                .innerJoin(SesionEventoDesempenioIcdCampotematico.class)
                .on(CampoTematico_Table.campoTematicoId.withTable()
                        .eq(SesionEventoDesempenioIcdCampotematico_Table.campoTematicoId.withTable()))
                .innerJoin(SesionEventoCompetenciaDesempenioIcd.class)
                .on(SesionEventoDesempenioIcdCampotematico_Table.sesionCompetenciaDesempenioIcdId.withTable()
                        .eq(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaDesempenioIcdId.withTable()))
                .innerJoin(DesempenioIcd.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.desempenioIcdId.withTable()
                        .is(DesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(Icds.class)
                .on(DesempenioIcd_Table.icdId.withTable()
                        .eq(Icds_Table.icdId.withTable()))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionCompetenciaId.withTable()))
                .where(Icds_Table.icdId.withTable().is(indicadorId))
                .and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .queryList();
    }

    @Override
    public List<CampoTematico> getCampotematicoRubro(String rubroEvalProcesoId) {
        return   SQLite.select()
                .from(CampoTematico.class)
                .innerJoin(RubroEvaluacionProcesoCampotematicoC.class)
                .on(CampoTematico_Table.campoTematicoId.withTable()
                        .eq(RubroEvaluacionProcesoCampotematicoC_Table.campoTematicoId.withTable()))
                .where(RubroEvaluacionProcesoCampotematicoC_Table.rubroEvalProcesoId.withTable().eq(rubroEvalProcesoId))
                .queryList();
    }
}
