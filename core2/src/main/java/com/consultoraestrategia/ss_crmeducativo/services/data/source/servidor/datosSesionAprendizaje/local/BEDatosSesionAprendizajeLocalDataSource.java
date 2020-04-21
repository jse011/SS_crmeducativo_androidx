package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.local;

import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosSesionAprendizajeLocalDataSource extends ServiceLocalDataSource<BEDatosSesionAprendizaje> implements BEDatosSesionAprendizajeDataSource {
    @Override
    public void getDatosExportar(ObjectCallBack<BEDatosSesionAprendizaje> callBack) {
        BEDatosSesionAprendizaje beDatosSesionAprendizaje = new BEDatosSesionAprendizaje();
        beDatosSesionAprendizaje.setSesionAprendizaje(ConsultaUtils.getChangeItemsTable(SesionAprendizaje.class));
        try {
            if (!beDatosSesionAprendizaje.getSesionAprendizaje().isEmpty()){
                callBack.onResponse(true, beDatosSesionAprendizaje);
            }else {
                callBack.onResponse(false, null);
            }
        }catch (Exception e){
            callBack.onResponse(false, null);
        }

    }

    @Override
    public void changeEstado(final BEDatosSesionAprendizaje item, int syncFlag, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(SesionAprendizaje.class, item.getSesionAprendizaje(),syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, item.getCompetencias(),syncFlag,databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(Competencia.class, item.getCapacidades(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(DesempenioIcd.class, item.getDesempenioIcd(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(Icds.class, item.getIcd(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(CampoTematico.class, item.getCampoTematico(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, item.getRecursoDidacticoEvento(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, item.getTareaEvento(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, item.getTareaEventoRecurso(),syncFlag,databaseWrapper,  false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(ActividadAprendizaje.class, item.getActividadEvento(),syncFlag,databaseWrapper,  false);

            TransaccionUtils.fastStoreListSyncFlagUpdateRel(UnidadAprendizaje.class, item.getUnidadAprendizaje(),syncFlag,databaseWrapper,  false);

            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    @Override
    public void saveDatos(final BEDatosSesionAprendizaje item, final SuccessCallBack callBack) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            //TransaccionUtils.fastStoreListComprobacionSave(SesionAprendizaje.class, item.getSesionAprendizaje(), false);
            TransaccionUtils.fastStoreListComprobacionSave(Competencia.class, item.getCompetencias(), false);
            TransaccionUtils.fastStoreListComprobacionSave(Competencia.class, item.getCapacidades(), false);
            TransaccionUtils.fastStoreListComprobacionSave(DesempenioIcd.class, item.getDesempenioIcd(), false);
            TransaccionUtils.fastStoreListComprobacionSave(Icds.class, item.getIcd(), false);
            TransaccionUtils.fastStoreListComprobacionSave(CampoTematico.class, item.getCampoTematico(), false);
            //TransaccionUtils.fastStoreListComprobacionSave(RecursoDidacticoEventoC.class, item.getRecursoDidacticoEvento(), false);
            TransaccionUtils.fastStoreListComprobacionSave(TareasC.class, item.getTareaEvento(), false);
            //TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, item.getTareaEventoRecurso(),databaseWrapper, false);
            TransaccionUtils.fastStoreListComprobacionSave(ActividadAprendizaje.class, item.getActividadEvento(), false);

            //region sesion aprendizaje
            List<SesionAprendizaje> sesionAprendizajeList = item.getSesionAprendizaje();
            List<Integer> sesionAprendizajeIdLis = new ArrayList<>();
            if(sesionAprendizajeList!=null)for (SesionAprendizaje sesionAprendizaje: sesionAprendizajeList)sesionAprendizajeIdLis.add(sesionAprendizaje.getSesionAprendizajeId());
            List<SesionEventoCompetenciaDesempenioIcd> sesionEventoCompetenciaDesempenioIcds = ConsultaUtils.getChangeItemsTableChild(SesionEventoCompetenciaDesempenioIcd.class, SesionEventoCompetenciaDesempenioIcd_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
            List<Integer> sesionCompetenciaDesempenioIcdIdList = new ArrayList<>();
            for (SesionEventoCompetenciaDesempenioIcd sesionEventoCompetenciaDesempenioIcd: sesionEventoCompetenciaDesempenioIcds)sesionCompetenciaDesempenioIcdIdList.add(sesionEventoCompetenciaDesempenioIcd.getSesionCompetenciaDesempenioIcdId());
            TransaccionUtils.deleteTable(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
            TransaccionUtils.deleteTable(SesionEventoCompetenciaDesempenioIcd.class, T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.in(sesionAprendizajeIdLis));
            TransaccionUtils.deleteTable(SesionEventoDesempenioIcdCampotematico.class, SesionEventoDesempenioIcdCampotematico_Table.sesionCompetenciaDesempenioIcdId.in(sesionCompetenciaDesempenioIcdIdList));

            TransaccionUtils.fastStoreListInsert(SesionAprendizaje.class, sesionAprendizajeList, databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, item.getCompetenciaSesion(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SesionEventoCompetenciaDesempenioIcd.class, item.getCompetenciaSesionDesempenioIcd(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(SesionEventoDesempenioIcdCampotematico.class, item.getCompetenciaSesionDesempenioIcdCampoTematico(), databaseWrapper, true);
            //endregion sesion aprendizaje

            //region UnidadAprendizaje
            List<Integer> listUnidadAprendizajeId = new ArrayList<>();
            List<UnidadAprendizaje> listUnidadAprendizaje=  item.getUnidadAprendizaje();
            if(listUnidadAprendizaje!=null)for(UnidadAprendizaje unidadAprendizaje: listUnidadAprendizaje)listUnidadAprendizajeId.add(unidadAprendizaje.getUnidadAprendizajeId());
            List<Integer> unidadCompetenciaIdList = new ArrayList<>();
            List<CompetenciaUnidad> competenciaUnidadList = ConsultaUtils.getChangeItemsTableChild(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(listUnidadAprendizajeId));
            for(CompetenciaUnidad competenciaUnidad: competenciaUnidadList)unidadCompetenciaIdList.add(competenciaUnidad.getCompetenciaId());
            List<Integer> unidadCompetenciaDesempenioIcdIdList = new ArrayList<>();
            List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> rel_unidad_evento_competencia_desempenio_icds = ConsultaUtils.getChangeItemsTableChild(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.in(listUnidadAprendizajeId));
            for(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD evento_competencia_desempenio_icd : rel_unidad_evento_competencia_desempenio_icds)unidadCompetenciaDesempenioIcdIdList.add(evento_competencia_desempenio_icd.getUnidadCompetenciaDesempenioIcdId());

            TransaccionUtils.deleteTable(CompetenciaUnidad.class, CompetenciaUnidad_Table.unidadAprendizajeId.in(listUnidadAprendizajeId));
            TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.in(unidadCompetenciaIdList));
            TransaccionUtils.deleteTable(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.in(unidadCompetenciaDesempenioIcdIdList));

            TransaccionUtils.fastStoreListInsert(CompetenciaUnidad.class, item.getCompetenciaUnidad(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, item.getCompetenciaUnidadDesempenioIcd(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class,  item.getCompetenciaUnidadDesempenioIcdCampoTematico(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(UnidadAprendizaje.class, item.getUnidadAprendizaje(), databaseWrapper, true);
            //endregion

            //region RecursoDidacticoEventoC
            List<RecursoDidacticoEventoC> recursoDidacticoEventoCList = item.getRecursoDidacticoEvento();
            List<String> recursoDiactivoIdList = new ArrayList<>();
            if(recursoDidacticoEventoCList!=null)for(RecursoDidacticoEventoC recursoDidacticoEventoC: recursoDidacticoEventoCList)recursoDiactivoIdList.add(recursoDidacticoEventoC.getKey());
            TransaccionUtils.deleteTable(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursoDiactivoIdList));
            //TransaccionUtils.deleteTable(RecursoArchivo.class, RecursoArchivo_Table.recursoDidacticoId.in(recursoDiactivoIdList));
            //TransaccionUtils.deleteTable(RecursoReferenciaC.class, RecursoReferenciaC_Table.recursoDidacticoId.in(recursoDiactivoIdList));
            TransaccionUtils.deleteTable(TareasRecursosC.class, TareasRecursosC_Table.recursoDidacticoId.in(recursoDiactivoIdList));

            TransaccionUtils.fastStoreListInsert(RecursoDidacticoEventoC.class, recursoDidacticoEventoCList, databaseWrapper, true);
            //TransaccionUtils.fastStoreListInsert(RecursoArchivo.class, beDatosSilaboEventoEnvio.getRecursoArchivo(), databaseWrapper, true);
            //TransaccionUtils.fastStoreListInsert(RecursoReferenciaC.class, beDatosSilaboEventoEnvio.getRecursoReferencia(), databaseWrapper, true);
            TransaccionUtils.fastStoreListInsert(TareasRecursosC.class, item.getTareaEventoRecurso(), databaseWrapper, true);
            //endregion RecursoDidacticoEventoC

            //TransaccionUtils.fastStoreListSetData(CompetenciaUnidad.class, item.getCompetenciaUnidad(),databaseWrapper, false);
            //TransaccionUtils.fastStoreListSetData(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class, item.getCompetenciaUnidadDesempenioIcd(),databaseWrapper, false);
            //TransaccionUtils.fastStoreListSetData(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class, item.getCompetenciaUnidadDesempenioIcdCampoTematico(),databaseWrapper, false);

            //TransaccionUtils.fastStoreListSetData(T_GC_REL_COMPETENCIA_SESION_EVENTO.class, item.getCompetenciaSesion(),databaseWrapper, false);
            //TransaccionUtils.fastStoreListSetData(SesionEventoCompetenciaDesempenioIcd.class, item.getCompetenciaSesionDesempenioIcd(),databaseWrapper, false);
            //TransaccionUtils.fastStoreListSetData(SesionEventoDesempenioIcdCampotematico.class, item.getCompetenciaSesionDesempenioIcdCampoTematico(),databaseWrapper, false);

            //TransaccionUtils.fastStoreListComprobacionSave(UnidadAprendizaje.class, item.getUnidadAprendizaje(), false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }


    @Override
    public void comprobarCambiosSesionAprendizaje(int sesionAprendizajeId, DosObjectCallBack<Long, Long> dosObjectCallBack) {
        SesionAprendizaje sesionAprendizaje = SQLite.select()
                .from(SesionAprendizaje.class)
                .where(SesionAprendizaje_Table.sesionAprendizajeId.eq(sesionAprendizajeId))
                .querySingle();

        if(sesionAprendizaje != null){
            dosObjectCallBack.onResponse(true,0L, sesionAprendizaje.getFechaAccion());
        }else {
            dosObjectCallBack.onResponse(false,0L, 0L);
        }
    }

    @Override
    public void getDatosImportarListSesionPorUnidades(BEVariables importar, ObjectCallBack<BEDatosSesionAprendizaje> objectCallBack) {

    }
}
