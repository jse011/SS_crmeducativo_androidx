package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase;

import androidx.core.view.ViewCompat;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaBannerUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GetFechaAsistenciaBimentreAgrupadas extends UseCase<GetFechaAsistenciaBimentreAgrupadas.RequestValues, GetFechaAsistenciaBimentreAgrupadas.ResponseValue> {

    private AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    public GetFechaAsistenciaBimentreAgrupadas(AsistenciaAlumnoRepository asistenciaAlumnoRepository) {
        this.asistenciaAlumnoRepository = asistenciaAlumnoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        final Calendar actual = Calendar.getInstance();


        this.asistenciaAlumnoRepository.getFechaAsistenciaPorBimestre(requestValues.getAnioAcademicoId(),requestValues.getCargaCursoId(), requestValues.getCalendarioPeriodoId(), requestValues.parametroDisenioId,requestValues.state,requestValues.getCargaAcademicaId(),
                new AsistenciaAlumnoDataSource.SucessCallback<List<FechaAsistenciaUi>>() {
            @Override
            public void onLoad(boolean success, List<FechaAsistenciaUi> item) {
                if(success){
                    int mes = -2;
                    int anio = -2;
                    Set<Object> objectSet = new LinkedHashSet<>();
                    for (FechaAsistenciaUi fechaAsistenciaUi: item){
                        final Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(fechaAsistenciaUi.getFechaAsistencia());
                        calendar.setMinimalDaysInFirstWeek(1);
                       if(mes != calendar.get(Calendar.MONTH) || anio != calendar.get(Calendar.YEAR))
                           objectSet.add(selectMes(calendar.get(Calendar.MONTH),calendar));

                        mes = calendar.get(Calendar.MONTH);
                        anio = calendar.get(Calendar.YEAR);
                        int nSemana = calendar.get(Calendar.WEEK_OF_YEAR);
                        if( mes==0 && nSemana==1)anio = calendar.get(Calendar.YEAR)-1;
                        String semanaString = Utils.f_intervaloSemana(nSemana, anio);
                        objectSet.add(semanaString);

                        fechaAsistenciaUi.setDiaSemana(Utils.f_diaSemana(calendar.get(Calendar.DAY_OF_WEEK)-1));
                        fechaAsistenciaUi.setDiaMes(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

                        int fechaCercana=0;
//                        if(calendar.get(Calendar.YEAR) == actual.get(Calendar.YEAR) &&
//                                calendar.get(Calendar.MONTH) == actual.get(Calendar.MONTH) &&
//                                calendar.get(Calendar.DATE) == actual.get(Calendar.DATE)){
//                            fechaCercana=
//                        }else {
//
//                        }


                        String asistencia = "Se registró la asistencia de " +fechaAsistenciaUi.getCantidadEvaluado() + " alumnos de "+ fechaAsistenciaUi.getCantidadTotal()+ " ";
                        fechaAsistenciaUi.setDescripcion(asistencia);

                        objectSet.add(fechaAsistenciaUi);
                    }
                    List<Object>objectList = new ArrayList<>(objectSet);
                    getUseCaseCallback().onSuccess(new ResponseValue(objectList));
                }else {
                    getUseCaseCallback().onError();
                    Log.d(getClass().getSimpleName(),"Error");
                }
            }
        });
    }
    
    public AsistenciaBannerUi selectMes(int mes, Calendar calendar){
        AsistenciaBannerUi asistenciaBannerUi;
        switch (mes){
            default:
                asistenciaBannerUi =  AsistenciaBannerUi.ENERO;
                break;
            case 1:
                asistenciaBannerUi =  AsistenciaBannerUi.FEBRERO;
                break;
            case 2:
                asistenciaBannerUi =  AsistenciaBannerUi.MARZO;
                break;
            case 3:
                asistenciaBannerUi =  AsistenciaBannerUi.ABRIL;
                break;
            case 4:
                asistenciaBannerUi =  AsistenciaBannerUi.MAYO;
                break;
            case 5:
                asistenciaBannerUi =  AsistenciaBannerUi.JUNIO;
                break;
            case 6:
                asistenciaBannerUi =  AsistenciaBannerUi.JULIO;
                break;
            case 7:
                asistenciaBannerUi =  AsistenciaBannerUi.AGOSTO;
                break;
            case 8:
                asistenciaBannerUi =  AsistenciaBannerUi.SETIEMBRE;
                break;
            case 9:
                asistenciaBannerUi =  AsistenciaBannerUi.OCTUBRE;
                break;
            case 10:
                asistenciaBannerUi =  AsistenciaBannerUi.NOVIEMBRE;
                break;
            case 11:
                asistenciaBannerUi = AsistenciaBannerUi.DICIEMBRE;
                break;
                
        }
        String titulo = asistenciaBannerUi.getMes() + " del " + calendar.get(Calendar.YEAR);
        asistenciaBannerUi.setTitulo(titulo);
        return asistenciaBannerUi;

    }



    public static class RequestValues implements UseCase.RequestValues{
      int anioAcademicoId;
      int cargaCursoId;
      int calendarioPeriodoId;
      int programaEducativoId;
      int cargaAcademicaId;
      int docenteId;
      int geografiaId;
      int parametroDisenioId;
      boolean state;

        public RequestValues(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int programaEducativoId, int docenteId, int geografiaId, int parametroDisenioId,boolean state, int cargaAcademicaId) {
            this.cargaCursoId = cargaCursoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.programaEducativoId = programaEducativoId;
            this.docenteId = docenteId;
            this.geografiaId = geografiaId;
            this.parametroDisenioId = parametroDisenioId;
            this.state = state;
            this.cargaAcademicaId =cargaAcademicaId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }

        public int getCargaAcademicaId() {
            return cargaAcademicaId;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
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

        public int getDocenteId() {
            return docenteId;
        }

        public void setDocenteId(int docenteId) {
            this.docenteId = docenteId;
        }

        public int getGeografiaId() {
            return geografiaId;
        }

        public void setGeografiaId(int geografiaId) {
            this.geografiaId = geografiaId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        List<Object> objectList;

        public ResponseValue(List<Object> objectList) {
            this.objectList = objectList;
        }

        public List<Object> getObjectList() {
            return objectList;
        }

        public void setObjectList(List<Object> objectList) {
            this.objectList = objectList;
        }
    }
}
