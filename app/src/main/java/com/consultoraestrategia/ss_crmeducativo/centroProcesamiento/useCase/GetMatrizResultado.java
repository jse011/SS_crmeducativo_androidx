package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data.CentroProcesamientoRepositorio;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CabeceraUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CellTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.ColumnTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.MatrizResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RowTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GetMatrizResultado {

    private CentroProcesamientoRepositorio repositorio;

    public GetMatrizResultado(CentroProcesamientoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public RetrofitCancel execute(int silaboEveId, int cargaCursoId, int calendarioPerId, int rubroformal, String tituloCurso, Callback callback){
        return repositorio.getMatrizResultado(silaboEveId, cargaCursoId, calendarioPerId, rubroformal, new CentroProcesamientoRepositorio.Callback<MatrizResultadoUi>() {
            @Override
            public void onLoad(boolean success, MatrizResultadoUi matriz) {
                if(success){
                    //#region Ordenar
                    Collections.sort(matriz.getCapacidadList(), (o1, o2) -> {
                        int value1 = Integer.compare(o1.getOrden2(), o2.getOrden2());
                        if (value1 == 0) {
                            int value2 = Integer.compare(o1.getOrden(),o2.getOrden());
                            if (value2 == 0) {
                                return Integer.compare(o1.getRubroResultadoId(), o2.getRubroResultadoId());
                            } else {
                                return value2;
                            }
                        }
                        return value1;
                    });
                    Collections.sort(matriz.getCompetenciaList(), (o1, o2) -> Integer.compare(o1.getCompetenciaId(), o2.getCompetenciaId()));
                    Collections.sort(matriz.getEvaluacionList(), (o1, o2) -> {
                        int value1 = Integer.compare(o1.getOrden2(), o2.getOrden2());
                        if (value1 == 0) {
                            int value2 = Integer.compare(o1.getOrden(),o2.getOrden());
                            if (value2 == 0) {
                                return Integer.compare(o1.getRubroEvalResultadoId(), o2.getRubroEvalResultadoId());
                            } else {
                                return value2;
                            }
                        }
                        return value1;
                    });
                    //#endregion

                    //#region Poner cell temporales para los alumnos que no tienen evalauciones resultados generados
                    for (RowTableRegEvalUi alumnoUi : matriz.getAlumnoEvalList()){
                        List<CellTableRegEvalUi> cellTableRegEvalUis = alumnoUi.getEvaluacionUiList();
                        for (ColumnTableRegEvalUi capacidadUi: matriz.getCapacidadList()){
                            CellTableRegEvalUi evaluacionUi = new CellTableRegEvalUi();
                            evaluacionUi.setNotaNoGenerada(true);
                            evaluacionUi.setAlumnoId(alumnoUi.getPersonaId());
                            evaluacionUi.setRubroEvalResultadoId(capacidadUi.getRubroResultadoId());
                            evaluacionUi.setCompetenciaId(capacidadUi.getRubroResultadoId());
                            evaluacionUi.setParentId(capacidadUi.getParendId());
                            evaluacionUi.setTipo(CellTableRegEvalUi.NOTA);
                            cellTableRegEvalUis.add(evaluacionUi);
                        }
                    }
                    //#endregion

                    //#region Cabecera
                    List<CabeceraUi> cabeceraUiList = new ArrayList<>();
                    cabeceraUiList.add(new CabeceraUi(CabeceraUi.TITULO_ALUMNO, 1));
                    if(matriz.getCapacidadList()!=null && !matriz.getCapacidadList().isEmpty()){
                        CabeceraUi cabeceratituloCompetencia = new CabeceraUi(CabeceraUi.TITULO, 1);
                        int cantidadCapacidades = 0;
                        for (ColumnTableRegEvalUi capacidadUi : matriz.getCapacidadList()){
                            if(capacidadUi.getCompetenciaId()!=0)cantidadCapacidades++;
                        }
                        cabeceratituloCompetencia.setRowSpan(cantidadCapacidades);

                        cabeceraUiList.add(cabeceratituloCompetencia);
                        if(rubroformal==1){
                            cabeceraUiList.add(new CabeceraUi(CabeceraUi.COMPETENCIA_FINAL_TITULO, 1));
                        }

                        cabeceraUiList.add(new CabeceraUi(CabeceraUi.ESPACIO_CALENDARIO, 1));
                    }
                    matriz.setCabeceraList(cabeceraUiList);
                    //#endregion

                    //#region Competencia
                    List<CabeceraUi> competenciaUiList = new ArrayList<>();
                    CabeceraUi cabeceraUi = new CabeceraUi(CabeceraUi.ALUMNO, 1);
                    cabeceraUi.setTitulo(tituloCurso);
                    competenciaUiList.add(cabeceraUi);
                    if(matriz.getCompetenciaList()!=null&&!matriz.getCompetenciaList().isEmpty()){
                        for (CabeceraUi competenciaUi: matriz.getCompetenciaList()){
                            competenciaUi.setTipo(CabeceraUi.COMPETENCIA);
                            int cantGrupoCapacidades = 0;
                            for (ColumnTableRegEvalUi capacidadUi : matriz.getCapacidadList()){
                                if(capacidadUi.getParendId()==competenciaUi.getCompetenciaId()|| //Contar capacidad o Promedios de la competencia
                                        (capacidadUi.getParendId()==0 && capacidadUi.getCompetenciaId()==competenciaUi.getCompetenciaId())
                                ){cantGrupoCapacidades++;}
                            }
                            competenciaUi.setRowSpan(cantGrupoCapacidades>0?cantGrupoCapacidades:1);
                            competenciaUiList.add(competenciaUi);
                        }
                        if(rubroformal==1){
                            competenciaUiList.add(new CabeceraUi(CabeceraUi.COMPETENCIA_FINAL, 1));
                        }

                        competenciaUiList.add(new CabeceraUi(CabeceraUi.ESPACIO_CALENDARIO, 1));
                    }
                    matriz.setCompetenciaList(competenciaUiList);
                    //#endregion

                    //#region capacidad
                    List<ColumnTableRegEvalUi> capacidadListUis = new ArrayList<>();
                    capacidadListUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.ALUMNO));
                    if(matriz.getCapacidadList()!=null && !matriz.getCapacidadList().isEmpty()){
                        for (ColumnTableRegEvalUi capacidadUi : matriz.getCapacidadList()){
                            if(capacidadUi.getCompetenciaId()>0){
                                capacidadUi.setTipo(ColumnTableRegEvalUi.NOTA);
                            }else {
                                capacidadUi.setTipo(ColumnTableRegEvalUi.FINAL);
                            }
                            capacidadListUis.add(capacidadUi);
                        }
                        capacidadListUis.add(new ColumnTableRegEvalUi(ColumnTableRegEvalUi.ESPACIO_CALENDARIO));
                    }
                    matriz.setCapacidadList(capacidadListUis);
                    //endregion

                    //region evaluacion
                    //Agrupar evaluacion por alumnos
                    for (CellTableRegEvalUi evaluacionUi : matriz.getEvaluacionList()){
                        RowTableRegEvalUi alumnoUi = null;
                        for (RowTableRegEvalUi item : matriz.getAlumnoEvalList()){
                            if(item.getPersonaId()==evaluacionUi.getAlumnoId()){
                                alumnoUi = item;
                                break;
                            }
                        }

                        if(alumnoUi!=null){
                            // Cambiar las evalauciones resultados generados a sus respectivas evaluaciones
                            List<CellTableRegEvalUi> capacidadUiList = new ArrayList<>(alumnoUi.getEvaluacionUiList());
                            int position = 0;
                            for (CellTableRegEvalUi capacidadUi : alumnoUi.getEvaluacionUiList()){
                                evaluacionUi.setAlumnoVigencia(alumnoUi.getVigencia());
                                if(capacidadUi.getRubroEvalResultadoId()==evaluacionUi.getRubroEvalResultadoId()){
                                    evaluacionUi.setTipo(CellTableRegEvalUi.NOTA);
                                    evaluacionUi.setCompetenciaId(capacidadUi.getCompetenciaId());
                                    evaluacionUi.setParentId(capacidadUi.getParentId());
                                    evaluacionUi.setBimestrNoVigente(matriz.getHabilitado()==0);
                                    evaluacionUi.setBimestrCerrado(matriz.getEstadoCargaCurCalPerId()==305);
                                    capacidadUiList.set(position, evaluacionUi);
                                }
                                position++;
                            }
                            alumnoUi.setEvaluacionUiList(capacidadUiList);

                        }
                    }

                    List<List<CellTableRegEvalUi>> evaluacionListList = new ArrayList<>();
                    for (RowTableRegEvalUi alumnoUi: matriz.getAlumnoEvalList()){
                        List<CellTableRegEvalUi> evaluacionList = alumnoUi.getEvaluacionUiList();
                        CellTableRegEvalUi alumnoEvaluacionUi = new CellTableRegEvalUi(CellTableRegEvalUi.ALUMNO);
                        alumnoEvaluacionUi.setAlumnoUi(alumnoUi);
                        evaluacionList.add(0, alumnoEvaluacionUi);
                        if(evaluacionList.size()>1){
                            evaluacionList.add(new CellTableRegEvalUi(CellTableRegEvalUi.ESPACIO_CALENDARIO));
                        }
                        evaluacionListList.add(evaluacionList);
                    }
                    matriz.setEvaluacionListList(evaluacionListList);
                    //#endregion

                    callback.onSuccess(matriz);

                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSuccess(MatrizResultadoUi matrizResultadoUi);
        void onError();
    }

}
