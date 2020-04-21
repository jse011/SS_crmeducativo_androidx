package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class CameraReconocimientoLocalDataSource implements CameraReconocimientoDataSource {

    private PersonaDao personaDao;

    public CameraReconocimientoLocalDataSource(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    @Override
    public PersonaUi getDatosPersona(int usuarioId) {
        PersonaUi personaUi = new PersonaUi();
        UsuarioRolGeoreferencia usuarioRolGeoreferencia = SQLite.select()
                .from(UsuarioRolGeoreferencia.class)
                .where(UsuarioRolGeoreferencia_Table.rolId.eq(4))
                .and(UsuarioRolGeoreferencia_Table.usuarioId.eq(usuarioId))
                .querySingle();

        Persona persona = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .innerJoin(Usuario.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(Usuario_Table.personaId.withTable()))
                .where(Usuario_Table.usuarioId.withTable()
                        .eq(usuarioId))
                .querySingle();

        if(usuarioRolGeoreferencia!=null){
            personaUi.setGeoreferenciId(usuarioRolGeoreferencia.getGeoReferenciaId());
            personaUi.setEntidadId(usuarioRolGeoreferencia.getEntidadId());
        }

        if(persona!=null){
            personaUi.setUsuaroId(usuarioId);
            personaUi.setPersonaId(persona.getPersonaId());
            personaUi.setNombre(Utils.capitalize(persona.getFirstName()));
        }
        return personaUi;
    }

    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {
        List<PersonaContratoQuery> personaContratoQueryList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
        List<PersonaUi> personaList = new ArrayList<>();
        int count = 0;
        for (PersonaContratoQuery persona : personaContratoQueryList) {
            count++;
            PersonaUi personaUi = new PersonaUi();
            personaUi.setNumeracion(count);
            personaUi.setPersonaId(persona.getPersonaId());
            personaUi.setNombres(persona.getNombres());
            personaUi.setApellidos(Utils.capitalize(persona.getApellidoPaterno()) + " " + Utils.capitalize(persona.getApellidoMaterno()));
            personaUi.setFoto(persona.getFoto());
            personaList.add(personaUi);

        }
        return personaList;
    }

    @Override
    public PersonaUi getDatosAlumnos(int personaId) {
        PersonaUi personaUi = new PersonaUi();
        Persona persona = SQLite.select(Utils.f_allcolumnTable(Persona_Table.ALL_COLUMN_PROPERTIES))
                .from(Persona.class)
                .where(Persona_Table.personaId.withTable()
                        .eq(personaId))
                .querySingle();

        if(persona!=null){
            personaUi.setFoto(persona.getFoto());
            personaUi.setPersonaId(persona.getPersonaId());
            personaUi.setNombre(Utils.capitalize(persona.getFirstName()));
            personaUi.setApellidos(Utils.capitalize(persona.getApellidoPaterno())+" "+Utils.capitalize(persona.getApellidoMaterno()));
        }
        return personaUi;
    }
}
