package com.consultoraestrategia.ss_crmeducativo.dao.archivo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;

import java.util.List;

public class ArchivoDaoImpl extends BaseDaoImpl<Archivo, Archivo_Table>  {


    @Override
    protected Class<Archivo> getEntityClass() {
        return null;
    }

    @Override
    protected Class<Archivo_Table> getTableclass() {
        return null;
    }
}
