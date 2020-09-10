package com.consultoraestrategia.ss_crmeducativo.lib;


import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso_Table;
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
   // public static final int VERSION = 13;//Playstore 12
    public static final int VERSION = 14;//Playstore 13

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

    @Migration(version = 12, database = AppDatabase.class)
    public static class MigrationTareaRubroEvaluacionProceso extends AlterTableMigration<RubroEvaluacionProcesoC> {

        public MigrationTareaRubroEvaluacionProceso(Class<RubroEvaluacionProcesoC> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.TEXT, "tareaId");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
            List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = SQLite.select()
                    .from(TareaRubroEvaluacionProceso.class)
                    .queryList();

            for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso : tareaRubroEvaluacionProcesoList){
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(tareaRubroEvaluacionProceso.getRubroEvalProcesoId()))
                        .querySingle();

                if(rubroEvaluacionProcesoC!=null){
                    rubroEvaluacionProcesoC.setTareaId(TextUtils.isEmpty(rubroEvaluacionProcesoC.getTareaId())?tareaRubroEvaluacionProceso.getTareaId():rubroEvaluacionProcesoC.getTareaId());
                }
            }
        }

    }

    @Migration(version = 14, database = AppDatabase.class)
    public static class MigrationEvento extends AlterTableMigration<Evento> {

        public MigrationEvento(Class<Evento> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "envioPersonalizado");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 14, database = AppDatabase.class)
    public static class MigrationCalendario extends AlterTableMigration<Calendario> {

        public MigrationCalendario(Class<Calendario> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "cargaAcademicaId");
            addColumn(SQLiteType.INTEGER, "cargaCursoId");
            addColumn(SQLiteType.INTEGER, "estadoPublicación");
            addColumn(SQLiteType.INTEGER, "rolId");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

}
