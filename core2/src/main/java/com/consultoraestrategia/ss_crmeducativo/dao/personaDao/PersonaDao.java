package com.consultoraestrategia.ss_crmeducativo.dao.personaDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;

/**
 * Created by @stevecampos on 26/04/2018.
 */

public interface PersonaDao extends BaseIntegerDao<Persona> {
    List<Persona> getPersonas(int usuarioId);
    List<PersonaContratoQuery> getAlumnosDeCargaCurso(int cargaCursoId);
    List<PersonaContratoQuery> getAlumnosDeCargaCurso(int cargaCursoId, DatabaseWrapper databaseWrapper);
    List<PersonaContratoQuery> searchAlumnosDeCargaCurso(boolean orderByNombre, boolean orderByApellido, String persona, int cargaCursoId, DatabaseWrapper databaseWrapper);
    List<PersonaContratoQuery> getAlumnosDeRubro(boolean orderByNombre, boolean orderByApellido, String persona, String rubroEvaluacionId, int cargaCurso);
    List<Persona> getHijos(int personaPadre);
    List<PersonaContratoQuery> getAlumnosDeRubroEquipo(List<String> rubroEquipoKeyList, int cargaCursoId);

    PersonaContratoQuery getPersonContrato(int personaId, int cargaCursosId);
}
