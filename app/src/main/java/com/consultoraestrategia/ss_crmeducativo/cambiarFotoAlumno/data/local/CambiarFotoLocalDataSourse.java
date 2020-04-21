package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CamabiarFotoDataSourse;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class CambiarFotoLocalDataSourse implements CamabiarFotoDataSourse {
    private PersonaDao personaDao;
    private static final String TAG = CambiarFotoLocalDataSourse.class.getSimpleName();
    public CambiarFotoLocalDataSourse(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {

        List<PersonaContratoQuery> personaContratoQueryList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
        List<PersonaUi> personaList = new ArrayList<>();
        int count = 0;
        for (PersonaContratoQuery persona: personaContratoQueryList){
            count++;
            PersonaUi personaUi = new PersonaUi();
            personaUi.setNumeracion(count);
            personaUi.setPersonaId(persona.getPersonaId());
            personaUi.setNombres(persona.getNombres());
            personaUi.setApellidos(Utils.capitalize(persona.getApellidoPaterno()) + " "+ Utils.capitalize(persona.getApellidoMaterno()));
            personaUi.setFoto(persona.getFoto());
            personaUi.setPath(persona.getPath());
            personaList.add(personaUi);
        }
        Log.d(TAG, "cargaCursoId " + cargaCursoId);
        Log.d(TAG, "CambiarFotoLocalDataSourse " + personaList.size());
        return personaList;
    }

    @Override
    public void savePathPersona(PersonaUi personaUi) {
        Persona persona = personaDao.get(personaUi.getPersonaId());
        if(persona!=null){
            persona.setPath(personaUi.getPath());
            persona.setFoto(personaUi.getFoto());
            persona.save();
        }
    }

    @Override
    public void uploadFileCasoRubro(PersonaUi personaUi, Callback<String> stringCallback) {

    }
}
