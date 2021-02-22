package com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo;


import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public class CalendarioPeriodoDaoImpl extends BaseIntegerDaoImpl<CalendarioPeriodo, CalendarioPeriodo_Table> implements CalendarioPeriodoDao{
    private static final String TAG = CalendarioPeriodoDaoImpl.class.getSimpleName();

    private static CalendarioPeriodoDaoImpl mInstance;

    private CalendarioPeriodoDaoImpl() {
    }

    public static CalendarioPeriodoDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new CalendarioPeriodoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return CalendarioPeriodo_Table.calendarioPeriodoId;
    }

    @Override
    protected Class<CalendarioPeriodo> getEntityClass() {
        return CalendarioPeriodo.class;
    }

    @Override
    protected Class<CalendarioPeriodo_Table> getTableclass() {
        return CalendarioPeriodo_Table.class;
    }

    @Override
    public List<CalendarioPeriodo> getRubrosEvalProceso(List<String> rubroEvalProcesoKeyList) {
        return   SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                .from(CalendarioPeriodo.class)
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(CalendarioPeriodo_Table.calendarioPeriodoId.withTable()
                        .eq(RubroEvaluacionProcesoC_Table.calendarioPeriodoId.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvalProcesoKeyList))
                .groupBy(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                .queryList();
    }

    @Override
    public boolean isVigenteCalendarioPorFechaActual(int calendarioPeriodoId, int cargarCursoId, boolean isRubroResultado) {
        boolean status = false;

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();


        CalendarioPeriodo calendarioPeriodo = SQLite.select()
                .from(CalendarioPeriodo.class)
                .where(CalendarioPeriodo_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                .querySingle();
        fechaInicio.setTimeInMillis(calendarioPeriodo.getFechaInicio());
        fechaFin.setTimeInMillis(calendarioPeriodo.getFechaFin());

        fechaInicio.set(0, fechaInicio.get(Calendar.MONTH), fechaInicio.get(Calendar.DAY_OF_MONTH));
        fechaFin.set(0, fechaFin.get(Calendar.MONTH), fechaFin.get(Calendar.DAY_OF_MONTH));
        fechaActual.set(0, fechaActual.get(Calendar.MONTH), fechaActual.get(Calendar.DAY_OF_MONTH));

        if (calendarioPeriodo!=null){
            if (fechaInicio.getTimeInMillis()<fechaActual.getTimeInMillis() && fechaFin.getTimeInMillis()>fechaActual.getTimeInMillis()){
                status=true;
            }
        }
        return status;
    }

    @Override
    public boolean isVigenteCalendarioCursoPeriodo(int calendarioPeridoId, int cargaCursoId, boolean isRubroResultado, DatabaseWrapper databaseWrapper) {
        boolean status = false;
        boolean statusCargaCuros = false;
        try {

            Log.d(TAG, "calendarioPeridoId :"+ calendarioPeridoId);
            Calendar fechaActual = Calendar.getInstance();
            fechaActual.set(Calendar.MILLISECOND, 0);
            fechaActual.set(Calendar.SECOND, 0);
            fechaActual.set(Calendar.MINUTE, 0);
            fechaActual.set(Calendar.HOUR_OF_DAY, 0);

            Calendar fechaActualHoraMinuto = Calendar.getInstance();
            fechaActualHoraMinuto.set(Calendar.MILLISECOND, 0);
            fechaActualHoraMinuto.set(Calendar.SECOND, 0);

            Where<CalendarioPeriodo> calendarioPeriodoWhere = SQLite.select()
                    .from(CalendarioPeriodo.class)
                    .where(CalendarioPeriodo_Table.calendarioPeriodoId.eq(calendarioPeridoId));

            CalendarioPeriodo calendarioPeriodo = databaseWrapper!=null ? calendarioPeriodoWhere.querySingle(databaseWrapper) :calendarioPeriodoWhere.querySingle();

            Where<CalendarioPeriodoDetalle> where = SQLite.select()
                    .from(CalendarioPeriodoDetalle.class)
                    .where(CalendarioPeriodoDetalle_Table.calendarioPeriodoId.eq(calendarioPeridoId));

            if(isRubroResultado){
                where.and(CalendarioPeriodoDetalle_Table.tipoId.eq(493));
            }else {
                where.and(CalendarioPeriodoDetalle_Table.tipoId.eq(494));
            }

            CalendarioPeriodoDetalle calendarioPeriodoDetalle =  databaseWrapper!=null ? where.querySingle(databaseWrapper) : where.querySingle();
            if (calendarioPeriodo == null||calendarioPeriodoDetalle==null){
                Log.d(TAG, "No tiene calendario detalle");
                return false;
            }
            Log.d(TAG, "calendarioPeriodo.getEstadoId() "+ calendarioPeriodo.getEstadoId());
            if(calendarioPeriodo.getEstadoId()== CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE ||
                    calendarioPeriodo.getEstadoId()== CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO ) {

                Calendar fechaDetalleInicio = Calendar.getInstance();
                fechaDetalleInicio.setTimeInMillis(calendarioPeriodoDetalle.getFechaInicio());
                fechaDetalleInicio.set(Calendar.MILLISECOND, 0);
                fechaDetalleInicio.set(Calendar.SECOND, 0);
                fechaDetalleInicio.set(Calendar.MINUTE, 0);
                fechaDetalleInicio.set(Calendar.HOUR_OF_DAY, 0);

                Calendar fechaDetalleFin = Calendar.getInstance();
                fechaDetalleFin.setTimeInMillis(calendarioPeriodoDetalle.getFechaFin());
                fechaDetalleFin.set(Calendar.MILLISECOND, 0);
                fechaDetalleFin.set(Calendar.SECOND, 0);
                fechaDetalleFin.set(Calendar.MINUTE, 0);
                fechaDetalleFin.set(Calendar.HOUR_OF_DAY, 0);

                int resutDetalleInicio = fechaActual.compareTo(fechaDetalleInicio);
                int resutDetalleFin = fechaActual.compareTo(fechaDetalleFin);

                Log.d(TAG, "resutDetalleInicio ? "+ Utils.getFechaDiaMes(fechaActual.getTimeInMillis()) +" : " +  Utils.getFechaDiaMes(fechaDetalleInicio.getTimeInMillis()));
                Log.d(TAG, "resutDetalleFin ? "+ Utils.getFechaDiaMes(fechaActual.getTimeInMillis())  +" : " + Utils.getFechaDiaMes(fechaDetalleFin.getTimeInMillis()));
                Log.d(TAG, "resutDetalleInicio ? "+ fechaActual.getTimeInMillis() +" : " +  fechaDetalleInicio.getTimeInMillis());
                Log.d(TAG, "resutDetalleFin ? "+ fechaActual.getTimeInMillis()  +" : " + fechaDetalleFin.getTimeInMillis());
                Log.d(TAG, "resutDetalleInicio = " + resutDetalleInicio);
                Log.d(TAG, "resutDetalleFin = " + resutDetalleFin);

                Log.d(TAG, "cargaCursoId" + cargaCursoId + " " + " calendarioPeriodoId: " + calendarioPeriodoDetalle.getCalendarioPeriodoDetId());

                Where<CargaCursoCalendarioPeriodo> cargaCursoCalendarioPeriodoWhere = SQLite.select()
                        .from(CargaCursoCalendarioPeriodo.class)
                        .where(CargaCursoCalendarioPeriodo_Table.cargaCursoId.eq(cargaCursoId))
                        .and(CargaCursoCalendarioPeriodo_Table.calendarioPeriodoDetId.eq(calendarioPeriodoDetalle.getCalendarioPeriodoDetId()));


                CargaCursoCalendarioPeriodo cargaCursoCalendarioPeriodo = databaseWrapper !=null ? cargaCursoCalendarioPeriodoWhere.querySingle(databaseWrapper) : cargaCursoCalendarioPeriodoWhere.querySingle();

                if((resutDetalleInicio == 0 || resutDetalleInicio > 0) &&
                        (resutDetalleFin == 0 || resutDetalleFin < 0)
                ){
                    status = true;

                }else {
                    if(cargaCursoCalendarioPeriodo!=null){

                        if(cargaCursoCalendarioPeriodo.getEstadoId()==CargaCursoCalendarioPeriodo.CERRADO){
                            status = false;
                        }else {
                            Log.d(TAG, "resutDetalleInicio");

                            /*if(cargaCursoCalendarioPeriodo.getEstadoId()==CargaCursoCalendarioPeriodo.PENDIENTE){*/
                            Calendar fechaCargaCursoInicio = Calendar.getInstance();
                            fechaCargaCursoInicio.setTimeInMillis(cargaCursoCalendarioPeriodo.getFechaInicio());
                            List<Integer> listHoraInicio = Utils.changeHourMinuto(cargaCursoCalendarioPeriodo.getHoraInicio());
                            fechaCargaCursoInicio.set(Calendar.HOUR_OF_DAY, listHoraInicio.get(0));
                            fechaCargaCursoInicio.set(Calendar.MINUTE, listHoraInicio.get(1));

                            fechaCargaCursoInicio.set(Calendar.SECOND, 0);
                            fechaCargaCursoInicio.set(Calendar.MILLISECOND, 0);



                            Calendar fechaCargaCursoFin = Calendar.getInstance();
                            fechaCargaCursoFin.setTimeInMillis(cargaCursoCalendarioPeriodo.getFechaFin());
                            List<Integer> listHoraFinal = Utils.changeHourMinuto(cargaCursoCalendarioPeriodo.getHoraFin());
                            fechaCargaCursoFin.set(Calendar.HOUR_OF_DAY, listHoraFinal.get(0));
                            fechaCargaCursoFin.set(Calendar.MINUTE, listHoraFinal.get(1));

                            fechaCargaCursoFin.set(Calendar.MILLISECOND, 0);
                            fechaCargaCursoFin.set(Calendar.SECOND, 0);


                            int resutCargaCursoInicio = fechaActualHoraMinuto.compareTo(fechaCargaCursoInicio);
                            int resutCargaCursoFin = fechaActualHoraMinuto.compareTo(fechaCargaCursoFin);

                            Log.d(TAG, "resutDetalleInicio ? "+ Utils.getFechaDiaMes(fechaActual.getTimeInMillis()) +" : " +  Utils.getFechaDiaMes(fechaCargaCursoInicio.getTimeInMillis()));
                            Log.d(TAG, "resutDetalleFin ? "+ Utils.getFechaDiaMes(fechaActual.getTimeInMillis())  +" : " + Utils.getFechaDiaMes(fechaCargaCursoFin.getTimeInMillis()));
                            Log.d(TAG, "resutDetalleInicio ? "+ fechaActual.getTimeInMillis() +" : " +  fechaCargaCursoInicio.getTimeInMillis());
                            Log.d(TAG, "resutDetalleFin ? "+ fechaActual.getTimeInMillis()  +" : " + fechaCargaCursoFin.getTimeInMillis());
                            if((resutCargaCursoInicio == 0 || resutCargaCursoInicio > 0) &&
                                    (resutCargaCursoFin == 0 || resutCargaCursoFin < 0)
                            ) {
                                status = true;
                            }
                        }

                    }
                }



            }
        } catch (Exception e){
            e.printStackTrace();
            status = false;
        }finally {
        }
        return status;
    }
}
