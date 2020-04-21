package com.consultoraestrategia.ss_crmeducativo.dao.instrumentoObservada;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 16/08/2018.
 */

public class InstrumentoObservadaDaoImpl extends BaseDaoImpl<InstrumentoObservado,InstrumentoObservado_Table> implements InstrumentoObservadaDao {

    private static InstrumentoObservadaDaoImpl mInstance;

    private InstrumentoObservadaDaoImpl() {
    }

    public static InstrumentoObservadaDao getInstance() {
        if (mInstance == null) {
            mInstance = new InstrumentoObservadaDaoImpl();
        }
        return mInstance;
    }

    @Override
    public InstrumentoObservado get(String id) {
        return getWithQuery(getDefaultSqlOperator().eq(id));
    }


    private Property<String> getDefaultSqlOperator() {
        return new Property<>(getTableclass(), "dimensionObservadaId");
    }

    @Override
    protected Class<InstrumentoObservado> getEntityClass() {
        return InstrumentoObservado.class;
    }

    @Override
    protected Class<InstrumentoObservado_Table> getTableclass() {
        return InstrumentoObservado_Table.class;
    }

    @Override
    public List<InstrumentoObservado> getInstrumentoAlumno(int alumnoId, int entidadId, int georeferenciaId) {
        Where<InstrumentoObservado> instrumentoObservadoWhere = SQLite.select(Utils.f_allcolumnTable(InstrumentoObservado_Table.ALL_COLUMN_PROPERTIES))
                .from(InstrumentoObservado.class)
                .innerJoin(DimensionObservada.class)
                .on(DimensionObservada_Table.instrumentoObservadaId.withTable()
                        .eq(InstrumentoObservado_Table.instrumentoObservadoId.withTable()))
                .innerJoin(InstrumentoEvaluacion.class)
                .on(InstrumentoObservado_Table.instrumentoEvalId.withTable()
                        .eq(InstrumentoEvaluacion_Table.instrumentoevalid.withTable()))
                .where(InstrumentoObservado_Table.personaId.eq(alumnoId));

        InstrumentoEvaluacion instrumentoEvaluacion =  SQLite.select()
                .from(InstrumentoEvaluacion.class)
                .where(InstrumentoEvaluacion_Table.georeferenciaid.eq(georeferenciaId))
                .querySingle();


        if(instrumentoEvaluacion != null){
            instrumentoObservadoWhere.and(InstrumentoEvaluacion_Table.georeferenciaid.withTable().eq(georeferenciaId));
        }else {
            int entidadSearch = entidadId;
            boolean estado = true;

            while (estado){

                instrumentoEvaluacion = SQLite.select()
                        .from(InstrumentoEvaluacion.class)
                        .where(InstrumentoEvaluacion_Table.entidadid.eq(entidadSearch))
                        .querySingle();

                if(instrumentoEvaluacion==null){

                    Entidad entidad = SQLite.select()
                            .from(Entidad.class)
                            .where(Entidad_Table.entidadId.eq(entidadSearch))
                            .querySingle();

                    if(entidad==null||entidad.getParentId()==0){
                        estado = false;
                    }else {
                        entidadSearch = entidad.getParentId();
                        Log.d(getClass().getSimpleName(),"entidadSearch: " + entidadSearch);
                    }

                }else {
                    estado = false;
                }
            }

            if(instrumentoEvaluacion!=null){
                instrumentoObservadoWhere.and(InstrumentoEvaluacion_Table.entidadid.withTable().eq(entidadSearch));
            }

        }

        return instrumentoEvaluacion == null? new ArrayList<InstrumentoObservado>(): instrumentoObservadoWhere
                .orderBy(DimensionObservada_Table.valor.withTable().desc())
                .groupBy(Utils.f_allcolumnTable(InstrumentoObservado_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }
}
