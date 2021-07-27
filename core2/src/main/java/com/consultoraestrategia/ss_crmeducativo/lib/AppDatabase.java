package com.consultoraestrategia.ss_crmeducativo.lib;


import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
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
import com.raizlabs.android.dbflow.annotation.Column;
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
    //public static final int VERSION = 14;//Playstore 13
   //public static final int VERSION = 15;//Playstore 14 se creo uns tabla webconfig
    //public static final int VERSION = 16;//Playstore 15 se aumento resultadoTipoNotaId
   //public static final int VERSION = 17;//Playstore 16 se aumento parametros a las tablas Eventos, Calendario y se creo la tabla EventoAdjunto
   public static final int VERSION = 18;//Se actualizo la tabla Caso


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

    @Migration(version = 16, database = AppDatabase.class)
    public static class MigrationRubroEvaluacionProceso extends AlterTableMigration<RubroEvaluacionProcesoC> {

        public MigrationRubroEvaluacionProceso(Class<RubroEvaluacionProcesoC> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.TEXT, "resultadoTipoNotaId");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 17, database = AppDatabase.class)
    public static class MigrationCalendario extends AlterTableMigration<Calendario> {

        public MigrationCalendario(Class<Calendario> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.TEXT, "nFoto");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 17, database = AppDatabase.class)
    public static class AlterEvento extends AlterTableMigration<Evento> {

        public AlterEvento(Class<Evento> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "fechaPublicacion_");
            addColumn(SQLiteType.INTEGER, "fechaPublicacion");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }

    @Migration(version = 18, database = AppDatabase.class)
    public static class CasoAlter extends AlterTableMigration<Caso> {

        public CasoAlter(Class<Caso> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "apoderado");
            addColumn(SQLiteType.INTEGER, "tutor");
            addColumn(SQLiteType.INTEGER, "padre");
            addColumn(SQLiteType.INTEGER, "organigramaId");


        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();
        }

    }
}
