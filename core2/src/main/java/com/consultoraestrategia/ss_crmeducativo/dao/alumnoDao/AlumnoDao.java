package com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao;

import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

import java.util.List;

public interface AlumnoDao {
    List<Integer> getPadres(int alumnoId);
    int getApoderado(int alumnoId);
    Usuario getTutor(int cargaAcademicaId);
}
