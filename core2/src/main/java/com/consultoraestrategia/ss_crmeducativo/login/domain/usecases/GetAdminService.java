package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;

public class GetAdminService extends  UseCase<GetAdminService.RequestValues, GetAdminService.ResponseValue>{

    private LoginRepository repository;

    public GetAdminService(LoginRepository repository) {
        this.repository = repository;
    }



    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerAdminService(requestValues.getOpcion(), requestValues.getUsuario(), requestValues.getPassword(), requestValues.getCorreo(), requestValues.getNumeroDocumento(), requestValues.getUrlServidor(), requestValues.isStates(),
                new LoginDataSource.Callback<AdminService>() {
                    @Override
                    public void onLoad(boolean success, AdminService item) {
                        if(success){
                            getUseCaseCallback().onSuccess(new ResponseValue(item));
                        }else {
                            getUseCaseCallback().onError();
                        }
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int opcion;
        private String usuario;
        private String password;
        private String correo;
        private String numeroDocumento;
        private String urlServidor;
        private boolean states;

        public RequestValues(int opcion, String usuario, String password, String correo, String numeroDocumento, String urlServidor, boolean states) {
            this.opcion = opcion;
            this.usuario = usuario;
            this.password = password;
            this.correo = correo;
            this.numeroDocumento = numeroDocumento;
            this.urlServidor = urlServidor;
            this.states = states;
        }

        public String getUrlServidor() {
            return urlServidor;
        }

        public int getOpcion() {
            return opcion;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getPassword() {
            return password;
        }

        public String getCorreo() {
            return correo;
        }

        public String getNumeroDocumento() {
            return numeroDocumento;
        }

        public boolean isStates() {
            return states;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private AdminService adminService;

        public ResponseValue(AdminService adminService) {
            this.adminService = adminService;
        }

        public AdminService getAdminService() {
            return adminService;
        }

    }
}
