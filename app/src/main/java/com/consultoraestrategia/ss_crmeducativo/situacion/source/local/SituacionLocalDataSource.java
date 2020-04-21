package com.consultoraestrategia.ss_crmeducativo.situacion.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.unidadAprendizajeDao.UnidadAprendizajeDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.SituacionDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.usecase.GetSituacionListUI;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class SituacionLocalDataSource implements SituacionDataSource {
    public static final String TAG = SituacionLocalDataSource.class.getSimpleName();
    private UnidadAprendizajeDao unidadAprendizajeDao;

    public SituacionLocalDataSource(UnidadAprendizajeDao unidadAprendizajeDao) {
        this.unidadAprendizajeDao = unidadAprendizajeDao;
    }

    @Override
    public List<SituacionUI> getSituacionUIList( GetSituacionListUI.RequestValues requestValues) {
        Log.d(TAG, "ENtidad id " + requestValues.getIdentidad());

        Log.d(TAG, "Unidad de Aprendixaje " + requestValues.getUnidadAprendizajeId());
        UnidadAprendizaje unidadAprendizaje = unidadAprendizajeDao.getUnidadAprendizajePorSesionId(requestValues.getUnidadAprendizajeId());

        List<SituacionUI> situacionUIList = new ArrayList<>();

        if (unidadAprendizaje != null) {

            boolean estado = true;

            int entidadId = requestValues.getIdentidad();

                while (estado){

                    long cantidad = SQLite.selectCountOf()
                            .from(ParametroConfiguracion.class)
                            .where(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                            .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                            .count();

                    if(cantidad==0){
                        Entidad entidad = SQLite.select()
                                .from(Entidad.class)
                                .where(Entidad_Table.entidadId.eq(entidadId))
                                .querySingle();

                        if(entidad==null||entidad.getParentId()==0){
                            estado = false;
                        }else {
                            entidadId = entidad.getParentId();
                            Log.d(TAG,"entidadId: " + entidadId);
                        }

                    }else {

                        estado = false;

                    }
                }

            Log.d(TAG,"entidadId: " + entidadId);

            ParametroConfiguracion contenidoPrincipal= SQLite.select()
                    .from(ParametroConfiguracion.class)
                    .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O1))
                    .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                    .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO))
                    .querySingle();

            SituacionUI base = new SituacionUI();
            base.setIdUnidadAprendizaje(unidadAprendizaje.getUnidadAprendizajeId());
            if(contenidoPrincipal!=null)base.setTipo(contenidoPrincipal.getParametro());
            base.setDescripcionTipo(unidadAprendizaje.getSituacionSignificativa());
            base.setTipoSubTitulo(SituacionUI.TIPO_DESAFIO);

            ParametroConfiguracion subcontenido= SQLite.select().from(ParametroConfiguracion.class)
                    .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O2))
                             .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                    .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO)).querySingle();

            if(subcontenido!=null) base.setSubtitulo(subcontenido.getParametro());
                        base.setDescripcionSubtitulo(unidadAprendizaje.getDesafio());

                        if (unidadAprendizaje.getDesafio().equals("")) {
                            base.setOcultarSecondItem(true);
                        } else {
                            base.setOcultarSecondItem(false);
                        }
                        situacionUIList.add(base);


            ParametroConfiguracion contenidoPrincipal2= SQLite.select().from(ParametroConfiguracion.class)
                    .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O3))
                    .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                    .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO)).querySingle();

                        SituacionUI complementaria = new SituacionUI();
                        complementaria.setIdUnidadAprendizaje(unidadAprendizaje.getUnidadAprendizajeId());
            if(contenidoPrincipal2!=null)complementaria.setTipo(contenidoPrincipal2.getParametro());
                        complementaria.setDescripcionTipo(unidadAprendizaje.getSituacionSignificativaComplementaria());

            ParametroConfiguracion subcontenido2= SQLite.select().from(ParametroConfiguracion.class)
                    .where(ParametroConfiguracion_Table.orden.withTable().eq(ParametroConfiguracion.O4))
                             .and(ParametroConfiguracion_Table.entidadId.withTable().eq(entidadId))
                    .and(ParametroConfiguracion_Table.concepto.withTable().eq(ParametroConfiguracion.CONCEPTO)).querySingle();

            if(subcontenido2!=null)complementaria.setSubtitulo(subcontenido2.getParametro());
                        base.setTipoSubTitulo(SituacionUI.TIPO_RETO);
                        complementaria.setDescripcionSubtitulo(unidadAprendizaje.getReto());

                        if (unidadAprendizaje.getReto().equals("")) {
                            base.setOcultarSecondItem(true);
                        } else {
                            base.setOcultarSecondItem(false);
                        }

                        situacionUIList.add(complementaria);


            }
        return situacionUIList;
    }
}
