package com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoImpl implements AlumnoDao {

    private static AlumnoDao mInstance;

    public static AlumnoDao getInstance() {
        if (mInstance == null) {
            mInstance = new AlumnoDaoImpl();
        }
        return mInstance;
    }

    @Override
    public List<Usuario> getPadres(int alumnoId) {//1380

        List<Usuario>usuarioList= new ArrayList<>();
        List<Relaciones> relacionesList=getRelaciones(alumnoId);

        for (Relaciones relaciones : relacionesList) {
            if (relaciones.getTipoId() == 181 || relaciones.getTipoId() == 182  ) {
                Usuario PadresUsuario = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.personaId.withTable().eq(relaciones.getPersonaVinculadaId()))
                        .querySingle();
                if(PadresUsuario!=null)usuarioList.add(PadresUsuario);
            }


         }
        return usuarioList;
    }

    public List<Relaciones>getRelaciones(int alumnoid){
        return SQLite.select()
                .from(Relaciones.class)
                .where(Relaciones_Table.personaPrincipalId.withTable().eq(alumnoid))
                .queryList();
    }

    @Override
    public Usuario getApoderado(int alumnoId) {
        Log.d(getClass().getSimpleName(), "ALUMNO ID"+alumnoId);
        Usuario usuario= new Usuario();
        Contrato contrato= SQLite.select().from(Contrato.class)
                .where(Contrato_Table.personaId.withTable().eq(alumnoId)).querySingle();
        if (contrato != null) {
            usuario = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.personaId.withTable().eq(contrato.getApoderadoId()))
                    .querySingle();
            if(usuario==null)usuario= new Usuario();
        }
        return usuario;
    }

    @Override
    public Usuario getTutor(int cargaAcademicaId) {
        Usuario usuario= new Usuario();
        CargaAcademica cargaAcademica= SQLite.select().from(CargaAcademica.class)
                .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId)).querySingle();
        if(cargaAcademica!=null){
            Empleado empleado= SQLite.select().from(Empleado.class)
                    .where(Empleado_Table.empleadoId.withTable().eq(cargaAcademica.getIdEmpleadoTutor())).querySingle();
            Log.d(getClass().getSimpleName(),"getcargaacademica, "+ cargaAcademica.getCargaAcademicaId()+ " tutor ");

            if(empleado!=null){
                usuario= SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.personaId.withTable().eq(empleado.getPersonaId()))
                    .querySingle();
            if(usuario==null)usuario= new Usuario();}
        }
        return usuario;
    }
}
