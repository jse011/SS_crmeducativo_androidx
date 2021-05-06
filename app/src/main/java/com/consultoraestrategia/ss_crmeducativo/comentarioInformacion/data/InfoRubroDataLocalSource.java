package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

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
    public List<ArchivoUi> getArchivoComentarioList(String evaluacionId) {
        List<ArchivoUi> archivoComentarioUis = new ArrayList<>();

        List<ArchivosRubroProceso> archivosRubroProcesoList = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .where(ArchivosRubroProceso_Table.evaluacionProcesoId.eq(evaluacionId))
                .and(ArchivosRubroProceso_Table.delete.notEq(1))
                .queryList();

        for (ArchivosRubroProceso archivosRubroProceso: archivosRubroProcesoList){
           ArchivoUi archivoUi = new ArchivoUi();
            archivoUi.setEvaluacionProcesoId(evaluacionId);
            archivoUi.setId(archivosRubroProceso.getKey());
            String file = "";
            try {
                int p = Math.max(archivosRubroProceso.getUrl().lastIndexOf('/'), archivosRubroProceso.getUrl().lastIndexOf('\\'));
                file = archivosRubroProceso.getUrl().substring(p + 1);
            }catch (Exception e){
                e.printStackTrace();
            }

            archivoUi.setNombre(file);
            archivoUi.setUrl(archivosRubroProceso.getUrl());
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
    public boolean deleteArchivoComentario(ArchivoUi archivoComentarioUi) {


        EvaluacionProcesoC evaluacionProcesoC = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.key.eq(archivoComentarioUi.getEvaluacionProcesoId()))
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
                .where(ArchivosRubroProceso_Table.key.eq(archivoComentarioUi.getId()))
                .querySingle();

        if(archivosRubroProceso!=null){
            archivosRubroProceso.setSyncFlag(BaseEntity.FLAG_UPDATED);
            archivosRubroProceso.setDelete(1);
            archivosRubroProceso.save();
        }

        return true;
    }
}
