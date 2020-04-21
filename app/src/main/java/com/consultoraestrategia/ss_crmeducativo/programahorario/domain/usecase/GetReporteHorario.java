package com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.SparseArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioDatSource;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoHorarioDiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraDiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;

import java.util.ArrayList;
import java.util.List;

public class GetReporteHorario extends UseCase<GetReporteHorario.RequestValues, GetReporteHorario.ResponseValue> {

    private ProgramaHorarioRepository horarioRepository;
    private String TAG = GetReporteHorario.class.getSimpleName();


    public GetReporteHorario(ProgramaHorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        List<Integer> programaHorarioIdList = new ArrayList<>();
        for (ProgramaHorarioUi horarioProgramaUi: requestValues.getHorarioProgramaIdList())programaHorarioIdList.add(horarioProgramaUi.getId());
        Integer programaHorarioId = 0;
        if(getRequestValues().getProgramaHorarioUi()!=null)programaHorarioId = getRequestValues().getProgramaHorarioUi().getId();


        final ArrayList<DiaUi> diaUiList = new ArrayList<>();
            horarioRepository.listarDias( programaHorarioIdList,
                    new ProgramaHorarioDatSource.Callback<List<DiaUi>>() {
                        @Override
                        public void load(boolean success, List<DiaUi> item) {
                            if(success){
                                diaUiList.addAll(item);
                            }else {
                                getUseCaseCallback().onError();
                            }
                        }
                    });



            final List<HoraUi> horaUiList  = new ArrayList<>();
            horarioRepository.listarHoras(programaHorarioId, new ProgramaHorarioDatSource.Callback<List<HoraUi>>() {
                @Override
                public void load(boolean success, List<HoraUi> item) {
                    if(success){
                        horaUiList.addAll(item);
                    }else {
                        getUseCaseCallback().onError();
                    }
                }
            });


            List<CursoHorarioDiaUi> cursoHorarioDiaUiList = new ArrayList<>();
            for (CursoUi cursoUi: requestValues.getCursoUiList()){
                final CursoHorarioDiaUi cursoHorarioDiaUi = new CursoHorarioDiaUi();
                cursoHorarioDiaUi.setCursoUi(cursoUi);


                horarioRepository.listarHorasporCursos(cursoUi.getCargaCursoId(),
                        programaHorarioId,
                        new ProgramaHorarioDatSource.Callback<List<HoraDiaUi>>() {
                            @Override
                            public void load(boolean success, List<HoraDiaUi> item) {
                                if(success){
                                    Log.d(TAG, "Size HoraDiaUi: " + item.size());
                                    cursoHorarioDiaUi.setHoraDiaUiList(item);
                                }else {
                                    getUseCaseCallback().onError();
                                }
                            }
                        });


                cursoHorarioDiaUiList.add(cursoHorarioDiaUi);
            }




            Log.d(TAG, "Size: " + cursoHorarioDiaUiList.size());

            List<List<Object>> celdaHorarioUiListList = new ArrayList<>();

            for (HoraUi horaUi: horaUiList) {
                List<Object> celdaHorarioUiList = new ArrayList<>();
                for (DiaUi diaUi : diaUiList){

                    if(horaUi instanceof BanerUi){
                        celdaHorarioUiList.add(new BanerUi());
                        continue;
                    }

                    try {
                        CursoHorarioDiaUi celdaHorarioUi = new CursoHorarioDiaUi();
                        for (CursoHorarioDiaUi cursoHorarioDiaUi : cursoHorarioDiaUiList){

                            for (HoraDiaUi horaDiaUi: cursoHorarioDiaUi.getHoraDiaUiList()){

                                if(horaDiaUi.getDiaId() == diaUi.getId()){

                                    Log.d(TAG, horaDiaUi.getDiaId() +"=="+ diaUi.getId());
                                    int posicion = horaDiaUi.getHoraUiList().indexOf(horaUi);
                                    Log.d(TAG, "Size hora: "+ horaDiaUi.getHoraUiList().size());
                                    Log.d(TAG, "posicion hora: "+ posicion);
                                    if(posicion!=-1){
                                        celdaHorarioUi = cursoHorarioDiaUi;
                                        break;
                                    }
                                }
                            }
                        }
                        celdaHorarioUiList.add(celdaHorarioUi);
                    }catch (Exception e){
                        e.printStackTrace();
                        celdaHorarioUiList.add(new CursoHorarioDiaUi());
                    }

                }
                celdaHorarioUiListList.add(celdaHorarioUiList);

            }

        getUseCaseCallback().onSuccess(new ResponseValue(diaUiList, horaUiList, celdaHorarioUiListList));

    }

    public static class RequestValues implements UseCase.RequestValues{
        private List<ProgramaHorarioUi> horarioProgramaIdList;
        private ProgramaHorarioUi programaHorarioUi;
        private List<CursoUi> cursoUiList;

        public RequestValues(List<ProgramaHorarioUi> horarioProgramaIdList, ProgramaHorarioUi programaHorarioUi, List<CursoUi> cursoUiList) {
            this.horarioProgramaIdList = horarioProgramaIdList;
            this.programaHorarioUi = programaHorarioUi;
            this.cursoUiList = cursoUiList;
        }

        public List<ProgramaHorarioUi> getHorarioProgramaIdList() {
            return horarioProgramaIdList;
        }

        public ProgramaHorarioUi getProgramaHorarioUi() {
            return programaHorarioUi;
        }

        public List<CursoUi> getCursoUiList() {
            return cursoUiList;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<DiaUi> diaUiList;
        private List<HoraUi> horaUiList;
        private List<List<Object>> lists;

        public ResponseValue(List<DiaUi> diaUiList, List<HoraUi> horaUiList, List<List<Object>> lists) {
            this.diaUiList = diaUiList;
            this.horaUiList = horaUiList;
            this.lists = lists;
        }

        public List<DiaUi> getDiaUiList() {
            return diaUiList;
        }

        public List<HoraUi> getHoraUiList() {
            return horaUiList;
        }

        public List<List<Object>> getLists() {
            return lists;
        }
    }
}
