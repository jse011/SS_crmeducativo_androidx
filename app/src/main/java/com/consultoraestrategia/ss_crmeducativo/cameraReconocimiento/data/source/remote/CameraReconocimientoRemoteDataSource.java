package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class CameraReconocimientoRemoteDataSource implements CameraReconocimientoDataSource {
    @Override
    public PersonaUi getDatosPersona(int usuarioId) {
        return null;
    }

    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {
        return null;
    }

    @Override
    public PersonaUi getDatosAlumnos(int personaId) {
        return null;
    }
}
