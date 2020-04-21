package com.consultoraestrategia.ss_crmeducativo.dao.alumnoDao;

import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

import java.util.List;

public interface AlumnoDao {
    List<Usuario> getPadres(int alumnoId);
    Usuario getApoderado(int alumnoId);
    Usuario getTutor(int cargaAcademicaId);
}
