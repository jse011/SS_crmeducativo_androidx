package com.consultoraestrategia.ss_crmeducativo.dao.tareasDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;

import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface TareasDao extends BaseDao<TareasC> {
    List<TareasC> getTareasPorSesionAprendizaje(int sesionAprendizajeId);

    List<TareasC> getTareasPorUnidadAprendizaje(int sesionAprendizajeId);

    TareasC getTareaPorUnidadTituloUsuario(String titulo, String instruciones, int idUnidadAprendizaje, int idUsuario);

    TareasC getTareaId(String id);
}
