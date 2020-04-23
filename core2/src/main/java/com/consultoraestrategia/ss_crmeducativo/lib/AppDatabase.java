package com.consultoraestrategia.ss_crmeducativo.lib;


import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irvinmarin on 24/04/2017.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, insertConflict = ConflictAction.REPLACE)
public class AppDatabase {
    public static final String NAME = "CRMEfinal";
    public static final int VERSION = 12;//Pasar a la vercion 9

    @Migration(version = 5, database = AppDatabase.class)
    public static class MigrationRubroEvaluacionProceso extends AlterTableMigration<RubroEvaluacionProcesoC> {

        public MigrationRubroEvaluacionProceso(Class<RubroEvaluacionProcesoC> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "unidadAprendizajeId");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 6, database = AppDatabase.class)
    public static class MigrationRubroSesionAprendizaje extends AlterTableMigration<SesionAprendizaje> {

        public MigrationRubroSesionAprendizaje(Class<SesionAprendizaje> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "estadoEvaluacion");
            addColumn(SQLiteType.INTEGER, "evaluados");
            addColumn(SQLiteType.INTEGER, "docenteid");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 7, database = AppDatabase.class)
    public static class MigrationRubroEvaluacionProcesoTwo extends AlterTableMigration<RubroEvaluacionProcesoC> {

        public MigrationRubroEvaluacionProcesoTwo(Class<RubroEvaluacionProcesoC> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "estrategiaEvaluacionId");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 8, database = AppDatabase.class)
    public static class MigrationRubroEvaluacionResultado extends BaseMigration {

        @Override
        public void migrate(DatabaseWrapper database) {

            List<EvaluacionResultado> evaluacionResultadoList = SQLite.select()
                    .from(EvaluacionResultado.class)
                    .queryList(database);

            for(EvaluacionResultado evaluacionResultado : evaluacionResultadoList){
                EvaluacionResultadoC evaluacionResultadoC = new EvaluacionResultadoC();
                evaluacionResultadoC.setEvaluacionResultadoId(evaluacionResultado.getEvaluacionResultadoId());
                evaluacionResultadoC.setCalendarioPeriodoId(evaluacionResultado.getCalendarioPeriodoId());
                evaluacionResultadoC.setCompetenciaId(evaluacionResultado.getCompetenciaId());
                evaluacionResultadoC.setAlumnoId(evaluacionResultado.getAlumnoId());
                evaluacionResultadoC.setDocenteId(evaluacionResultado.getDocenteId());
                evaluacionResultadoC.setNota(evaluacionResultado.getNota());
                evaluacionResultadoC.setEscala(evaluacionResultado.getEscala());
                evaluacionResultadoC.setRubroEvalResultadoId(evaluacionResultado.getRubroEvalResultadoId());
                evaluacionResultadoC.setEstadoExportado(evaluacionResultado.getEstadoExportado());
                evaluacionResultadoC.setValorTipoNotaId(evaluacionResultado.getValorTipoNotaId());
                evaluacionResultadoC.setIcdId(evaluacionResultado.getIcdId());

                evaluacionResultadoC.setFechaAccion(evaluacionResultado.getFechaAccion());
                evaluacionResultadoC.setFechaCreacion(evaluacionResultado.getFechaCreacion());
                evaluacionResultadoC.setUsuarioAccionId(evaluacionResultado.getUsuarioAccionId());
                evaluacionResultadoC.setUsuarioCreacionId(evaluacionResultado.getUsuarioCreacionId());

                evaluacionResultadoC.save(database);
            }

        }
    }

    @Migration(version = 9, database = AppDatabase.class)
    public static class MigrationRubroEvaluacionResultadoTwo extends AlterTableMigration<EvaluacionResultadoC> {

        public MigrationRubroEvaluacionResultadoTwo(Class<EvaluacionResultadoC> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "cargaCursoId");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

}
