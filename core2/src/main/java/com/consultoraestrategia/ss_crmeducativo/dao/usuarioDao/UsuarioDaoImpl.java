package com.consultoraestrategia.ss_crmeducativo.dao.usuarioDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.raizlabs.android.dbflow.sql.language.property.Property;

public class UsuarioDaoImpl extends BaseIntegerDaoImpl<Usuario, Usuario_Table> implements UsuarioDao{

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Usuario_Table.usuarioId;
    }

    @Override
    protected Class<Usuario> getEntityClass() {
        return Usuario.class;
    }

    @Override
    protected Class<Usuario_Table> getTableclass() {
        return Usuario_Table.class;
    }
}
