package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao.CompetenciaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao.IndicadorDao;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InfoRubroDataLocalSource implements InfoRubroDataSource {

    private String TAG= InfoRubroDataLocalSource.class.getSimpleName();
    public InfoRubroDataLocalSource() {
    }

    @Override
    public List<MensajeUi> getComentarios(String evaluacionId) {
        List<MensajeUi> mensajeUiList = new ArrayList<>();
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(evaluacionId))
                .querySingle();

        if(evaluacionProcesoC==null)return mensajeUiList;

        List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentarios = SQLite.select()
                .from(RubroEvaluacionProcesoComentario.class)
                .where(RubroEvaluacionProcesoComentario_Table.evaluacionProcesoId.eq(evaluacionProcesoC.getKey()))
                .and(RubroEvaluacionProcesoComentario_Table.delete.withTable().notEq(1))
                .queryList();

        for (RubroEvaluacionProcesoComentario rubroEvaluacionProcesoComentario : rubroEvaluacionProcesoComentarios){
            MensajeUi mensajeUi = new MensajeUi();
            mensajeUi.setId(rubroEvaluacionProcesoComentario.getKey());
            mensajeUi.setDescripcion(rubroEvaluacionProcesoComentario.getDescripcion());
            mensajeUi.setEvaluacionId(evaluacionProcesoC.getKey());
            mensajeUi.setTipoMensaje(MensajeUi.TipoMensaje.PREDETERMINADO);
            mensajeUi.setFechaCreacion(rubroEvaluacionProcesoComentario.getFechaCreacion());
            mensajeUiList.add(mensajeUi);
        }

        return mensajeUiList;
    }


    @Override
    public List<ArchivoComentarioUi> getArchivoComentarioList(String evaluacionId) {
        List<ArchivoComentarioUi> archivoComentarioUis = new ArrayList<>();

        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(evaluacionId))
                .querySingle();

        if(evaluacionProcesoC==null)return archivoComentarioUis;

        List<ArchivosRubroProceso> archivosRubroProcesoList = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .where(ArchivosRubroProceso_Table.evaluacionProcesoId.eq(evaluacionProcesoC.getKey()))
                .and(ArchivosRubroProceso_Table.delete.notEq(1))
                .queryList();

        for (ArchivosRubroProceso archivosRubroProceso: archivosRubroProcesoList){
            ArchivoComentarioUi archivoUi = new ArchivoComentarioUi();
            archivoUi.setEvaluacionId(archivosRubroProceso.getEvaluacionProcesoId());
            archivoUi.setArchivoId(archivosRubroProceso.getKey());
            String file = "";
            try {
                int p = Math.max(archivosRubroProceso.getUrl().lastIndexOf('/'), archivosRubroProceso.getUrl().lastIndexOf('\\'));
                file = archivosRubroProceso.getUrl().substring(p + 1);
            }catch (Exception e){
                e.printStackTrace();
            }

            archivoUi.setNombreArchivo(file);
            archivoUi.setNombreRecurso(file);
            switch (archivosRubroProceso.getTipoArchivoId()) {
                case ArchivosRubroProceso.TIPO_IMAGEN:
                    archivoUi.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                    break;
                case ArchivosRubroProceso.TIPO_VIDEO:
                    archivoUi.setTipoFileU(RepositorioTipoFileU.VIDEO);
                    break;
            }
            archivoUi.setUrl(archivosRubroProceso.getUrl());
            archivoUi.setPath(archivosRubroProceso.getLocalpath());
            if (TextUtils.isEmpty(archivosRubroProceso.getLocalpath())) {
                archivoUi.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
            } else {
                archivoUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
            }
            archivoUi.setSelect(true);
            archivoComentarioUis.add(archivoUi);
        }
        return archivoComentarioUis;
    }

    @Override
    public boolean saveComentario(MensajeUi mensajeUi) {
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(mensajeUi.getEvaluacionId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        RubroEvaluacionProcesoComentario rubroEvaluacionProcesoComentario = new RubroEvaluacionProcesoComentario();
        rubroEvaluacionProcesoComentario.setComentarioId("");
        rubroEvaluacionProcesoComentario.setDescripcion(mensajeUi.getDescripcion());
        rubroEvaluacionProcesoComentario.setEvaluacionProcesoComentarioId(mensajeUi.getId());
        rubroEvaluacionProcesoComentario.setKey(mensajeUi.getId());
        rubroEvaluacionProcesoComentario.setEvaluacionProcesoId(evaluacionProcesoC.getKey());
        rubroEvaluacionProcesoComentario.setSyncFlag(BaseEntity.FLAG_ADDED);

        //evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        //evaluacionProcesoC.save();
        return rubroEvaluacionProcesoComentario.save();
    }

    @Override
    public boolean deleteComentario(MensajeUi mensajeUi) {
        Log.d(TAG, mensajeUi.toString());

        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(mensajeUi.getEvaluacionId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;
        //evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        //evaluacionProcesoC.save();

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        RubroEvaluacionProcesoComentario rubroEvaluacionProcesoComentario = SQLite.select()
                .from(RubroEvaluacionProcesoComentario.class)
                .where(RubroEvaluacionProcesoComentario_Table.key.eq(mensajeUi.getId()))
                .querySingle();

        if(rubroEvaluacionProcesoComentario!=null){
            rubroEvaluacionProcesoComentario.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoComentario.setDelete(1);
            rubroEvaluacionProcesoComentario.save();
        }
        return true;
    }

    @Override
    public boolean saveComentarioArchivo(ArchivoComentarioUi archivoComentarioUi) {

        Log.d(TAG, "saveComentarioArchivo: " + archivoComentarioUi.getEvaluacionId());
        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(archivoComentarioUi.getEvaluacionId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;
        //evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        //evaluacionProcesoC.save();

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        ArchivosRubroProceso archivosRubroProceso = new ArchivosRubroProceso();
        archivosRubroProceso.setKey(archivoComentarioUi.getArchivoId());
        archivosRubroProceso.setLocalpath(archivoComentarioUi.getPath());
        archivosRubroProceso.setUrl(archivoComentarioUi.getUrl());
        archivosRubroProceso.setEvaluacionProcesoId(evaluacionProcesoC.getKey());
        switch (archivoComentarioUi.getTipoFileU()) {
            case IMAGEN:
                archivosRubroProceso.setTipoArchivoId(ArchivosRubroProceso.TIPO_IMAGEN);
                break;
            case VIDEO:
                archivosRubroProceso.setTipoArchivoId(ArchivosRubroProceso.TIPO_VIDEO);
                break;
            default:
                archivosRubroProceso.setTipoArchivoId(ArchivosRubroProceso.TIPO_IMAGEN);
                break;
        }

        archivosRubroProceso.setSyncFlag(BaseEntity.FLAG_UPDATED);
        evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        return archivosRubroProceso.save();
    }

    @Override
    public boolean deleteArchivoComentario(ArchivoComentarioUi archivoComentarioUi) {


        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(archivoComentarioUi.getEvaluacionId()))
                .querySingle();

        if(evaluacionProcesoC==null)return false;
        //evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
        //evaluacionProcesoC.save();

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC =  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(evaluacionProcesoC.getRubroEvalProcesoId()))
                .querySingle();

        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }

        ArchivosRubroProceso archivosRubroProceso = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .where(ArchivosRubroProceso_Table.key.eq(archivoComentarioUi.getArchivoId()))
                .querySingle();

        if(archivosRubroProceso!=null){
            archivosRubroProceso.setSyncFlag(BaseEntity.FLAG_UPDATED);
            archivosRubroProceso.setDelete(1);
            archivosRubroProceso.save();
        }

        return true;
    }
}
