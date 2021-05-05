package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.iceteck.silicompressorr.SiliCompressor;

public class UpdateRepositorio  {

    private RepositorioRepository repository;
    private SiliCompressor siliCompressor;

    public UpdateRepositorio(RepositorioRepository repository, SiliCompressor siliCompressor) {
        this.repository = repository;
        this.siliCompressor = siliCompressor;
    }

    public void execute(Request request) {
        RepositorioDataSource.Callback<Boolean> booleanCallback = new RepositorioRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {

            }
        };
        String path = request.getPath();
        try {
            switch (request.getTipoFileUi()){
                case IMAGEN:
                    ///File fileImagen = new File(request.getPath());
                    //path = siliCompressor.compress(fileImagen.getPath(),fileImagen);

                    break;
                case VIDEO:
                    //File fileVideo = new File(request.getPath());
                    //path = siliCompressor.compressVideo(path,fileVideo.getPath());
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }



        switch (request.getRepositorioUi()){
            case ARCHIVO_COMPORTAMIENTO:
                repository.updateSucessDowloaComportamiento(request.getArchivoId(), path, booleanCallback);
                break;
            case ARCHIVO_ASISTENICA:
                repository.updateSucessDowloadAsistenica(request.getArchivoId(), path, booleanCallback);
                break;
            case ARCHIVO:
                repository.updateSucessDowload(request.getArchivoId(), path, booleanCallback);
                break;
            case ARCHIVO_RUBRO:
                repository.updateSucessDowloadRubro(request.getArchivoId(), path, booleanCallback);
                break;
        }

    }

    public static class Request{
        String archivoId;
        String path;
        RepositorioUi repositorioUi;
        RepositorioTipoFileU tipoFileUi;

        public Request(String archivoId, RepositorioTipoFileU tipoFileU, String path, RepositorioUi repositorioUi) {
            this.archivoId = archivoId;
            this.path = path;
            this.repositorioUi = repositorioUi;
            this.tipoFileUi = tipoFileUi;
        }

        public String getArchivoId() {
            return archivoId;
        }

        public String getPath() {
            return path;
        }

        public RepositorioUi getRepositorioUi() {
            return repositorioUi;
        }

        public RepositorioTipoFileU getTipoFileUi() {
            return tipoFileUi;
        }
    }
}
