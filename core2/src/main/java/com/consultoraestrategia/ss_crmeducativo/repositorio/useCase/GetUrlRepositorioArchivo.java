package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;

import java.util.List;

public class GetUrlRepositorioArchivo extends UseCaseSincrono<GetUrlRepositorioArchivo.Request, String> {

    private RepositorioRepository repositorioRepository;

    public GetUrlRepositorioArchivo(RepositorioRepository repositorioRepository) {
        this.repositorioRepository = repositorioRepository;
    }

    @Override
    public void execute(GetUrlRepositorioArchivo.Request request, final Callback<String> callback) {
        repositorioRepository.getUrlRepositorioArchivo(new RepositorioRepository.Callback<String>() {
            @Override
            public void onLoad(boolean success, String item) {
                if (success){
                    callback.onResponse(true, item);
                }else {
                    callback.onResponse(false, null);
                }
            }
        });
    }

    public static class Request{

    }
}
