package com.consultoraestrategia.ss_crmeducativo.test.ui;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 5/01/2018.
 */

/**
 * AbstractRemoteDataSource are the entry points to the exportation layer.
 *
 * @param <T> the entity first level class to export
 * @param <P> the entity second level class to export
 * @param <Q> the entity first level to parse JSONResponse
 * @param <R> the entity second level to parse JSONResponse
 */
public abstract class AbstractSecondLevelRemoteDataSource<T extends LocalSLEntity<P>, P extends LocalEntity, Q extends EntitySLResponse, R extends EntityResponse> extends AbstractRemoteDataSource<T, Q> {

    private static final String TAG = AbstractSecondLevelRemoteDataSource.class.getSimpleName();

    protected abstract P getSecondLevelEntity(int id);

    protected abstract List<R> getResponseList(Q responseItem);

    protected abstract P setServerSLId(P secondLevelEntity, int id);

    @Override
    public List<T> getItemsUpdated(List<T> items, List<Q> responseList) {
        List<T> itemsUpdated = super.getItemsUpdated(items, responseList);
        for (Q responseItem :
                responseList) {
            List<R> secondLevelResponseList = getResponseList(responseItem); //La respuesta de Segundo Nivel, debe tener un método que se llame getResponseList(); Eso será en la clase que extienda de Q[EntityResponse]
            T firstLevelInstance = getLocalEntity(responseItem.getIdAndroid());
            int index = itemsUpdated.indexOf(firstLevelInstance);
            if (index != -1) {
                T firstLevelEntry = itemsUpdated.get(index);
                List<P> secondLevelList = firstLevelEntry.getSecondLevelList();// Si es un T de segundo nivel, debe tener un método que se llame getSecondLevelList...
                List<P> secondLevelListUpdated = getItemsSLToUpdate(secondLevelList, secondLevelResponseList);
                firstLevelEntry.setSecondLevelList(secondLevelListUpdated);
                itemsUpdated.set(index, firstLevelEntry);
            }
        }
        return itemsUpdated;
    }
    /*
        FastStoreModelTransaction
                .insertBuilder(FlowManager.getModelAdapter(entityClass))
                .addAll(itemsSaved)
                .build();*/

    private List<P> getItemsSLToUpdate(List<P> items, List<R> responseList) {

        Log.d(TAG, "getItemsSLToUpdate...");
        List<P> itemsUpdated = new ArrayList<>();
        for (R responseItem : responseList) {
            P itemInstance = getSecondLevelEntity(responseItem.getIdAndroid());
            int index = items.indexOf(itemInstance);
            if (index != -1) {
                P p = items.get(index);
                p = setServerSLId(p, responseItem.getIdServer());
                p.setSyncFlag(LocalEntity.FLAG_EXPORTED);
                itemsUpdated.add(p);
            }
        }
        return itemsUpdated;
    }

}
