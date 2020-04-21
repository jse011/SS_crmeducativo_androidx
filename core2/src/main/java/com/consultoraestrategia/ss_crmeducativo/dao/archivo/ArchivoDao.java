package com.consultoraestrategia.ss_crmeducativo.dao.archivo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;

import java.util.List;

public interface ArchivoDao extends BaseIntegerDao {
    List<Archivo> obtenerArchivoTarea(int tareaId);

    List<Archivo> all();
}
