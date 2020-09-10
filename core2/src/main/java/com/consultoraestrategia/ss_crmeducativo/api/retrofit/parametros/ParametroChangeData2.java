package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ParametroChangeData2 extends ApiRetrofit.Parameters {

    @SerializedName("vint_CargaCursoId")
    private int cargaCursoId;
    @SerializedName("vlst_CargaCursoId")
    private List<Integer> cargaCursoIdList;
    @SerializedName("vint_CalendarioPeriodoId")
    private int calendarioPeriodoId;
    @SerializedName("vint_ProgramaEducativoId")
    private int programaEducativoId;
    @SerializedName("vint_UsuarioId")
    private int usuarioId;
    @SerializedName("vint_EmpleadoId")
    private int empleadoId;
    @SerializedName("vint_EntidadId")
    private int entidadId;
    @SerializedName("vint_GeoreferenciaId")
    private int georeferenciaId;
    @SerializedName("vint_SilaboEventoId")
    private int silaboEventoId;
    @SerializedName("vlst_SilaboEventoId")
    private List<Integer> silaboEventoIdList;
    @SerializedName("vint_CursoId")
    private int cursoId;
    @SerializedName("vlst_CursoId")
    private List<Integer> cursoIdList;
    @SerializedName("vint_CargaAcademicaId")
    private int cargaAcademicaId;
    @SerializedName("vint_AnioAcademicoId")
    private int anioAcademicoId;

    public ParametroChangeData2() {
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public void setAnioAcademicoId(int anioAcademicoId) {
        this.anioAcademicoId = anioAcademicoId;
    }

    public int getAnioAcademicoId() {
        return anioAcademicoId;
    }

    public List<Integer> getSilaboEventoIdList() {
        return silaboEventoIdList;
    }

    public void setSilaboEventoIdList(List<Integer> silaboEventoIdList) {
        this.silaboEventoIdList = silaboEventoIdList;
    }

    public List<Integer> getCargaCursoIdList() {
        return cargaCursoIdList;
    }

    public void setCargaCursoIdList(List<Integer> cargaCursoIdList) {
        this.cargaCursoIdList = cargaCursoIdList;
    }

    public List<Integer> getCursoIdList() {
        return cursoIdList;
    }

    public void setCursoIdList(List<Integer> cursoIdList) {
        this.cursoIdList = cursoIdList;
    }

}
