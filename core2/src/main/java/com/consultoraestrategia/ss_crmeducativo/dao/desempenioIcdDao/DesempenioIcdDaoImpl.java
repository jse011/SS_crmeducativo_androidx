package com.consultoraestrategia.ss_crmeducativo.dao.desempenioIcdDao;


import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.raizlabs.android.dbflow.sql.language.property.Property;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class DesempenioIcdDaoImpl extends BaseIntegerDaoImpl<DesempenioIcd, DesempenioIcd_Table> implements DesempenioIcdDao {


    private static DesempenioIcdDaoImpl mInstance;

    private DesempenioIcdDaoImpl() {
    }

    public static DesempenioIcdDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new DesempenioIcdDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return DesempenioIcd_Table.desempenioIcdId;
    }

    @Override
    protected Class<DesempenioIcd> getEntityClass() {
        return DesempenioIcd.class;
    }

    @Override
    protected Class<DesempenioIcd_Table> getTableclass() {
        return DesempenioIcd_Table.class;
    }
}
