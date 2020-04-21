package com.consultoraestrategia.ss_crmeducativo.services.data.util;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jse on 20/05/2018.
 */

public class ConsultaUtils {
    private static final String TAG = ConsultaUtils.class.getSimpleName();

    public static  <P extends BaseModel> List<P> getChangeItemsTable(final Class<P> clazz) {
        List<P> pList = new ArrayList<>();
        try {
           Property<Integer> syncFlag = new Property<Integer>(clazz, "syncFlag");
            pList = SQLite.select()
                   .from(clazz)
                   .where(syncFlag.is(BaseEntity.FLAG_ADDED))
                   .or(syncFlag.is(BaseEntity.FLAG_UPDATED))
                   .queryList();
       }catch (Exception e){
            e.printStackTrace();
        }
       return pList;
    }

    public static  <P extends BaseModel> long countChangeItemsTable(final Class<P> clazz) {
       long count = 0;
        try {
            Property<Integer> syncFlag = new Property<Integer>(clazz, "syncFlag");
            count = SQLite.selectCountOf()
                    .from(clazz)
                    .where(syncFlag.is(BaseEntity.FLAG_ADDED))
                    .or(syncFlag.is(BaseEntity.FLAG_UPDATED))
                    .count();
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public static  <P extends BaseModel> List<P> getChangeItemsTableChild(final Class<P> clazz,SQLOperator sqlOperator) {
        return SQLite.select()
                .from(clazz)
                .where(sqlOperator)
                .queryList();
    }

    public static  <P extends BaseModel> P getChangeItemTableChild(final Class<P> clazz,SQLOperator sqlOperator) {
        return SQLite.select()
                .from(clazz)
                .where(sqlOperator)
                .querySingle();
    }


}
