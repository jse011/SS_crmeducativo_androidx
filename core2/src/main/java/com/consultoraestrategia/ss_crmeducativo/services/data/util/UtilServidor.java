package com.consultoraestrategia.ss_crmeducativo.services.data.util;

import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by SCIEV on 18/05/2018.
 */

public class UtilServidor {
    public static final String REMOTE_URL = "http://pruebas.consultoraestrategia.com/";
    private RestAPI restAPI;
    private ObjectMapper mapper;

    private UtilServidor(RestAPI restAPI, ObjectMapper mapper) {
        this.restAPI = restAPI;
        this.mapper = mapper;
    }

    public static UtilServidor getInstance() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
           return new UtilServidor(
                new RestAPI(),
                mapper);
    }

    public RestAPI getRestAPI() {
        restAPI.updateServerUrl();
        return restAPI;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public ApiRetrofit getApiRetrofit(){
        return ApiRetrofit.getInstance();
    }




}
