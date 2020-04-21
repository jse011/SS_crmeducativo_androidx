package com.consultoraestrategia.ss_crmeducativo.dao.comportamientoDao;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ComportamientoDaoImpl implements ComportamientoDao {

    private static ComportamientoDao sInstance = null;

    private ComportamientoDaoImpl() {
    }

    public static ComportamientoDao getInstance() {
        if (sInstance == null) {
            sInstance = new ComportamientoDaoImpl();
        }
        return sInstance;
    }


}
