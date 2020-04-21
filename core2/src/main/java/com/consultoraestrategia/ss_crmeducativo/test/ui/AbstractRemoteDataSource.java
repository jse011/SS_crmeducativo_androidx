package com.consultoraestrategia.ss_crmeducativo.test.ui;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
  Created by @stevecampos on 4/01/2018.
 */

/**
 * AbstractRemoteDataSource are the entry points to the exportation layer.
 *
 * @param <T> the entity class to export
 * @param <Q> the entity to parse de JSONResponse
 */
public abstract class AbstractRemoteDataSource<T extends LocalEntity, Q extends EntityResponse> {
    private static final String TAG = AbstractRemoteDataSource.class.getSimpleName();

    protected abstract T getLocalEntity(int id);

    protected abstract T setServerId(T instance, int id);

    protected abstract JSONObject consumeApi(List<T> pendingItems) throws Exception;


    public void export(final List<T> pendingItems, final Class<Q> responseClass, final Callback<Q> callback) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "export");
                List<Q> responseList = new ArrayList<>();
                try {
                    JSONObject jsonObject = consumeApi(pendingItems);
                    Log.d(TAG, "jsonObject: " + jsonObject.toString());
                    ObjectMapper mapper = new ObjectMapper();
                    responseList = mapper.readValue(Utils.getJsonObResultArray(jsonObject), TypeFactory.defaultInstance().constructCollectionType(List.class, responseClass));
                } catch (Exception e) {
                    Log.d(TAG, "Exception: " + e);
                } finally {
                    responseList.removeAll(Collections.singleton(null));
                    callback.onItemsExported(responseList);
                }
            }
        });
        thread.start();
    }

    public interface Callback<Q> {
        void onItemsExported(List<Q> responseList);
    }

    public List<T> getItemsUpdated(List<T> items, List<Q> responseList) {
        Log.d(TAG, "getItemsUpdated...");
        List<T> itemsSaved = new ArrayList<>();
        for (Q responseItem : responseList) {
            if (responseItem != null) {
                T itemInstance = getLocalEntity(responseItem.getIdAndroid());
                int index = items.indexOf(itemInstance);
                if (index != -1) {
                    T t = items.get(index);
                    t = setServerId(t, responseItem.getIdServer());
                    t.setSyncFlag(LocalEntity.FLAG_EXPORTED);
                    itemsSaved.add(t);
                }
            }
        }
        return itemsSaved;
    }
}
