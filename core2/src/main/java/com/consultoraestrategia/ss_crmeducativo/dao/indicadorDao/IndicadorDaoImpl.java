package com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CampoTematicoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CampoTematicoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 19/04/2018.
 */

public class IndicadorDaoImpl extends BaseIntegerDaoImpl<Icds, Icds_Table> implements IndicadorDao {


    private static IndicadorDao sInstance = null;
    private CampoTematicoDao campoTematicoDao;

    public static IndicadorDao getInstance(CampoTematicoDao campoTematicoDao) {
        if (sInstance == null) {
            sInstance = new IndicadorDaoImpl(campoTematicoDao);
        }
        return sInstance;
    }

    private IndicadorDaoImpl(CampoTematicoDao campoTematicoDao) {
        this.campoTematicoDao = campoTematicoDao;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Icds_Table.icdId;
    }

    @Override
    protected Class<Icds> getEntityClass() {
        return Icds.class;
    }

    @Override
    protected Class<Icds_Table> getTableclass() {
        return Icds_Table.class;
    }

    @Override
    public List<Icds> getIndicadores(int silaboEventoId, int capacidadId) {
        return SQLite.select(Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES))
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable()
                        .eq(DesempenioIcd_Table.icdId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable()
                        .eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.desempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()
                        .eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()
                        .eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CompetenciaUnidad_Table.competenciaId.is(capacidadId))
                .groupBy(Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }

    @Override
    public List<Icds> getIndicadoresConCampoTematico(int silaboEventoId, int capacidadId) {
        List<Icds> indicadores = getIndicadores(silaboEventoId, capacidadId);
        for (Icds indicador :
                indicadores) {
            int indicadorId = indicador.getIcdId();
            List<CampoTematico> campoTematicos = new ArrayList<>();
            campoTematicos.addAll(campoTematicoDao.getCamposTematicos(silaboEventoId, indicadorId));
            Log.d(CampoTematicoDaoImpl.class.getSimpleName(),"indicadorId : "+campoTematicos.size());
            indicador.setCampoTematicoList(campoTematicos);
        }
        return indicadores;
    }

    @Override
    public List<Icds> getIndicadorePorCapacidadYSesionAprendizaje(int capacidadId, int sesionAprendizajeId) {
        return SQLite.select(Utils.f_allcolumnTable(Icds_Table.ALL_COLUMN_PROPERTIES))
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable()
                        .eq(DesempenioIcd_Table.icdId.withTable()))
                .innerJoin(SesionEventoCompetenciaDesempenioIcd.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable()
                        .eq(SesionEventoCompetenciaDesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionCompetenciaId.withTable()))
                .where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable().is(capacidadId))
                .and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .queryList();
    }

    @Override
    public Icds getIcdsporDesempenioIcd(int desempenioIcdId) {
        return SQLite.select()
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable()
                        .eq(DesempenioIcd_Table.icdId.withTable()))
                .where(DesempenioIcd_Table.desempenioIcdId.is(desempenioIcdId))
                .querySingle();
    }

    @Override
    public List<IndicadorQuery> getIcdsporRubroProceso(List<String> rubroEvalProcesoKeyList) {
        return SQLite.select(Utils.f_allcolumnTable(Icds_Table.icdId,
                Icds_Table.desempenioId,
                Icds_Table.descripcion,
                Icds_Table.titulo,
                Icds_Table.alias,
                Icds_Table.descripcion,
                Icds_Table.estado,
                Icds_Table.peso,
                DesempenioIcd_Table.desempenioIcdId,
                DesempenioIcd_Table.descripcion.as("desempenioDesc"),
                DesempenioIcd_Table.tipoId,
                DesempenioIcd_Table.url))
                .from(Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(DesempenioIcd_Table.icdId.withTable().eq(Icds_Table.icdId.withTable()))
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable().eq(RubroEvaluacionProcesoC_Table.desempenioIcdId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.withTable().in(rubroEvalProcesoKeyList))
                .queryCustomList(IndicadorQuery.class);
    }

}
