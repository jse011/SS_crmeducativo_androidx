package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;

import java.util.List;

public class GetDatosPreFastData {

    private LoginDataRepository loginDataRepository;

    public GetDatosPreFastData(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    public Response execute(Request request){
        String nombreColegio = loginDataRepository.getNombreColegio(request.getUsuarioId());
        String anioActual = loginDataRepository.getNombreAnioActual(request.getAnioAcademicoId());
        List<ProgramaEducativoUi> cursosUiList = loginDataRepository.getCursosAnioPrograma(request.getUsuarioId(),request.getAnioAcademicoId(), request.getCalendarioPeriodoId(), request.getProgramaEducativoId());
        return new Response(nombreColegio, anioActual, cursosUiList);
    }

    public static class Request{
        private int usuarioId;
        private int anioAcademicoId;
        private int calendarioPeriodoId;
        private int programaEducativoId;
        public Request(int usuarioId, int anioAcademicoId, int calendarioPeriodoId, int programaEducativoId) {
            this.usuarioId = usuarioId;
            this.anioAcademicoId = anioAcademicoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.programaEducativoId = programaEducativoId;
        }

        public int getUsuarioId() {
            return usuarioId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
        }
    }

    public static class Response {
        String nombreColegio;
        String anioActual;
        List<ProgramaEducativoUi> cursosUiList;

        public Response(String nombreColegio, String anioActual, List<ProgramaEducativoUi> cursosUiList) {
            this.nombreColegio = nombreColegio;
            this.anioActual = anioActual;
            this.cursosUiList = cursosUiList;
        }

        public String getNombreColegio() {
            return nombreColegio;
        }

        public String getAnioActual() {
            return anioActual;
        }

        public List<ProgramaEducativoUi> getCursosUiList() {
            return cursosUiList;
        }
    }


}
