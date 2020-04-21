package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.AsistenciaSesionAlumnoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request.GenerarAsistenciaPorCargaAcademicaRequest;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request.GenerarAsistenciaPorCursoRequest;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivoAsistencia;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivoAsistencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.JustificacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.JustificacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AsistenciaAlumnoLocalDataSource implements AsistenciaAlumnoDataSource {

    AsistenciaSesionAlumnoDao asistenciaSesionAlumnoDao;
    TipoNotaDao tipoNotaDao;
    ValorTipoNotaDao valorTipoNotaDao;
    ParametrosDisenioDao parametrosDisenioDao;
    private String TAG = AsistenciaAlumnoLocalDataSource.class.getSimpleName();



    public AsistenciaAlumnoLocalDataSource(AsistenciaSesionAlumnoDao asistenciaSesionAlumnoDao, TipoNotaDao tipoNotaDao, ValorTipoNotaDao valorTipoNotaDao, ParametrosDisenioDao parametrosDisenioDao) {
        this.asistenciaSesionAlumnoDao = asistenciaSesionAlumnoDao;
        this.tipoNotaDao = tipoNotaDao;
        this.valorTipoNotaDao = valorTipoNotaDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
    }

    @Override
    public void getAsistenciaAlumnos(AsistenciaUi asistenciaUi, SucessCallback<List<AsistenciaUi>> callback) {
        Log.d(TAG, "getIdCargaCurso " + asistenciaUi.getIdCargaCurso());
       Log.d(TAG, "calendarioPeriodoId " + asistenciaUi.getIdCalendarioPeriodo());
       Log.d(TAG, "getIdCargaAcademica " + asistenciaUi.getIdCargaAcademica());
       Log.d(TAG, "fecha " + Utils.f_fecha_letras(asistenciaUi.getFecha()));

        List<AsistenciaUi> asistenciaUiList = new ArrayList<>();
        asistenciaUiList.clear();

        long fecha =  asistenciaUi.getFecha();
        List<AsistenciaSesionAlumnoC> newAsistenciaSesionAlumnoC=new ArrayList<>();


        List<AsistenciaSesionAlumnoC> asistenciaSesionAlumnoC= asistenciaSesionAlumnoDao.getAsistenciaPorCalendarioPeriodo(asistenciaUi.getIdCargaCurso(),
                asistenciaUi.getIdCalendarioPeriodo(),
                asistenciaUi.getIdCargaAcademica());

        for (AsistenciaSesionAlumnoC itemAsistenciaSesionAlumno: asistenciaSesionAlumnoC){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(itemAsistenciaSesionAlumno.getFechaAsistencia());

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(fecha);
            if(calendar.get(Calendar.YEAR)==c.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==c.get(Calendar.MONTH)&&calendar.get(Calendar.DATE)== c.get(Calendar.DATE)){
                newAsistenciaSesionAlumnoC.add(itemAsistenciaSesionAlumno);
            }
        }



       Log.d(TAG, "newAsistenciaSesionAlumnoC size "+ newAsistenciaSesionAlumnoC.size());
        //if (newAsistenciaSesionAlumnoC==null)return;
        for(AsistenciaSesionAlumnoC  asistenciaSes: newAsistenciaSesionAlumnoC){

            AsistenciaUi asistencia=  new AsistenciaUi();
            //justificacion
            JustificacionC justificacionC= SQLite.select(Utils.f_allcolumnTable(JustificacionC_Table.ALL_COLUMN_PROPERTIES))
                    .from(JustificacionC.class)
                    .where(JustificacionC_Table.asistenciaSesionId.withTable().eq(asistenciaSes.key))
                    .querySingle();
            //Log.d(TAG ,"justificacionC "+ justificacionC);
            if(justificacionC!=null){
                JustificacionUi justificacionUi =new JustificacionUi();
                justificacionUi.setId(justificacionC.key);
                justificacionUi.setRazon(justificacionC.getDescripcion());

                //tipos list
                List<Tipos> tiposList= SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES))
                        .from(Tipos.class).where(Tipos_Table.objeto.withTable().eq(TipoUi.objeto.T_AS_MAE_JUSTIFICACION.name())).queryList();
                List<TipoUi> tipoUis= new ArrayList<>();
                for(Tipos tipos: tiposList){
                    TipoUi tipoUi= new TipoUi();
                    tipoUi.setId(tipos.getTipoId());
                    tipoUi.setConcepto(tipos.getConcepto());
                    tipoUi.setNombre(tipos.getNombre());
                    tipoUis.add(tipoUi);
                }
                justificacionUi.setTipoUiList(tipoUis);
                //tiposeleted
                for (TipoUi tipo:tipoUis)if(tipo.getId()==justificacionC.getTipoJustificacionId())justificacionUi.setTipo(tipo);
                asistencia.setJustificacionUi(justificacionUi);

                List<ArchivoAsistencia> recursoArchivoList = SQLite.select()
                        .from(ArchivoAsistencia.class)
                        .where(ArchivoAsistencia_Table.justificacionId.eq(justificacionC.getKey()))
                        .queryList();
               // Log.d(TAG, "recursoArchivoList: " + recursoArchivoList.size());
                List<AsistenicaArchivoUi> asistenicaArchivoUiList = new ArrayList<>();
                for (ArchivoAsistencia archivoAsistencia : recursoArchivoList){
                    AsistenicaArchivoUi asistenicaArchivoUi = new AsistenicaArchivoUi();
                    asistenicaArchivoUi.setKey(archivoAsistencia.getKey());
                    asistenicaArchivoUi.setUrl(archivoAsistencia.getPath());
                    asistenicaArchivoUi.setPath(archivoAsistencia.getLocalpath());
                    asistenicaArchivoUi.setNombre(archivoAsistencia.getNombre());
                    asistenicaArchivoUi.setFechaAccionArchivo(archivoAsistencia.getFechaAccion());
                    switch (archivoAsistencia.getTipoId()){
                        case ArchivoAsistencia.TIPO_AUDIO:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.AUDIO);
                            break;
                        case ArchivoAsistencia.TIPO_DIAPOSITIVA:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.PRESENTACION);
                            break;
                        case ArchivoAsistencia.TIPO_DOCUMENTO:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.DOCUMENTO);
                            break;
                        case ArchivoAsistencia.TIPO_HOJA_CALCULO:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.HOJA_CALCULO);
                            break;
                        case ArchivoAsistencia.TIPO_IMAGEN:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.IMAGEN);
                            break;
                        case ArchivoAsistencia.TIPO_PDF:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.PDF);
                            break;
                        case ArchivoAsistencia.TIPO_VIDEO:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VIDEO);
                            break;
                        case ArchivoAsistencia.TIPO_VINCULO:
                            asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VINCULO);
                            break;
                    }
                    asistenicaArchivoUiList.add(asistenicaArchivoUi);
                }
                asistencia.setAsistenicaArchivoUiList(asistenicaArchivoUiList);


            }

            ParametrosDisenio parametrosDisenio = null;
            if(asistenciaSes.getCargaCursoId()!=0){
               parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(asistenciaUi.getIdCargaCurso());
                if (parametrosDisenio==null)return;
                asistenciaUi.setColor(parametrosDisenio.getColor1());
            }
            asistencia.setFecha(asistenciaSes.getFechaAsistencia());
            asistencia.setId(asistenciaSes.getKey());
            asistencia.setIdGeoreferencia(asistenciaSes.getGeoreferenciaId());
            asistencia.setIdDocente(asistenciaSes.getDocenteId());
            asistencia.setIdCargaCurso(asistenciaSes.getCargaCursoId());
            asistencia.setIdCalendarioPeriodo(asistenciaSes.getCalendarioPeriodoId());


            Persona persona = SQLite.select().from(Persona.class)
                    .where(Persona_Table.personaId.eq(asistenciaSes.getAlumnoId())).querySingle();
            AlumnosUi alumnosUi= new AlumnosUi();
            if(persona!=null){
                alumnosUi.setId(persona.getPersonaId());
                alumnosUi.setNombre(Utils.capitalize(persona.getFirstName()));
                alumnosUi.setLastName((Utils.capitalize(persona.getApellidos())));
                alumnosUi.setUrlProfile(persona.getUrlPicture());
            }else {
                alumnosUi.setId(asistenciaSes.getAlumnoId());
                alumnosUi.setNombre("Alumno");
                alumnosUi.setLastName("Desconocido");
            }
            asistencia.setAlumnosUi(alumnosUi);

            TipoNotaC tipoN= tipoNotaDao.getValorAsistencia(asistenciaUi.getIdProgramaEducativo());

            if(tipoN!=null){

                TipoNotaUi tipoNotaUi= new TipoNotaUi();
                tipoNotaUi.setId(tipoN.key);
                tipoNotaUi.setLongitudPaso(tipoN.getLongitudPaso());
                tipoNotaUi.setNombre(tipoN.getNombre());
                tipoNotaUi.setTipoId(tipoN.getTipoId());
                tipoNotaUi.setValorDefecto(tipoN.getValorDefecto());
                tipoNotaUi.setValorMinimo(tipoN.getValorMinino());
                tipoNotaUi.setValorMaximo(tipoN.getValorMaximo());


               List<ValorTipoNotaC> listaValores= valorTipoNotaDao.getListPorTipoNotaId(tipoN.getTipoNotaId());
                List<ValorTipoNotaUi>lista= new ArrayList<>();
                for(ValorTipoNotaC valortipo:listaValores){
                    //Log.d(TAG, "valortipo " +valortipo.getAlias());
                    ValorTipoNotaUi valorTipoNotaUi= new ValorTipoNotaUi();
                    if(parametrosDisenio!=null)valorTipoNotaUi.setColorCurso(parametrosDisenio.getColor1());
                    switch (valortipo.getEstadoId()){
                        case ValorTipoNotaC.PUNTUAL :
                            valorTipoNotaUi.setTipo(ValorTipoNotaUi.Tipo.PUNTUAL);
                            valorTipoNotaUi.setEnabled(true);
                            break;
                        case ValorTipoNotaC.TARDE :
                            valorTipoNotaUi.setTipo(ValorTipoNotaUi.Tipo.TARDE);
                            valorTipoNotaUi.setEnabled(true);
                            break;
                        case ValorTipoNotaC.AUSENTE :
                            valorTipoNotaUi.setTipo(ValorTipoNotaUi.Tipo.AUSENTE);
                            valorTipoNotaUi.setEnabled(true);
                            break;
                        case ValorTipoNotaC.TARDE_JDT :
                            valorTipoNotaUi.setTipo(ValorTipoNotaUi.Tipo.TARDE_JTD);
                           valorTipoNotaUi.setEnabled(false);
                            break;
                        case ValorTipoNotaC.AUSENTE_JDT :
                            valorTipoNotaUi.setTipo(ValorTipoNotaUi.Tipo.AUSENTE_JTD);
                            valorTipoNotaUi.setEnabled(false);
                            break;
                    }

                    valorTipoNotaUi.setEstado(String.valueOf(valortipo.getEstadoId()));
                    valorTipoNotaUi.setTitulo(valortipo.getTitulo());
                    valorTipoNotaUi.setSelected(valortipo.isSeleccionado());
                    valorTipoNotaUi.setIcono(valortipo.getIcono());
                    valorTipoNotaUi.setAlias(valortipo.getAlias());
                    valorTipoNotaUi.setId(valortipo.key);


                    Log.d(TAG ,"AsitenciaAlumnoLocalDatSource");
                    if(asistenciaSes.getValorTipoNotaId().equals(valortipo.key)){
                        valorTipoNotaUi.setEnabled(true);
                        valorTipoNotaUi.setSelected(true);
                        asistencia.setValorTipoNotaUi(valorTipoNotaUi);
                    }
                    valorTipoNotaUi.setAsistenciaUi(asistencia);
                    lista.add(valorTipoNotaUi);
                }
                  //arreglar
                Collections.sort(lista, new Comparator<ValorTipoNotaUi>() {
                    public int compare(ValorTipoNotaUi obj1, ValorTipoNotaUi obj2) {
                        return obj1.getEstado().compareTo(obj2.getEstado());
                    }
                });
                tipoNotaUi.setValorTipoNotaList(lista);
                asistencia.setTipoNotaUi(tipoNotaUi);
            }

            asistenciaUiList.add(asistencia);


        }

        Collections.sort(asistenciaUiList, new Comparator<AsistenciaUi>() {
            public int compare(AsistenciaUi obj1, AsistenciaUi obj2) {
                return obj1.getAlumnosUi().getLastName().compareTo(obj2.getAlumnosUi().getLastName());
            }

        });
        callback.onLoad(true, asistenciaUiList);

    }

    @Override
    public void saveAsitenciaAlumnos(List<AsistenciaUi> lista,  Callback callback) {
        Log.d(TAG, "lIST "+lista.size() );

        try {
            for(AsistenciaUi asistenciaUi :lista){

                if(!asistenciaUi.isModificado())continue;

                AsistenciaSesionAlumnoC asistenciaSesionAlumnoC= SQLite.select(Utils.f_allcolumnTable(AsistenciaSesionAlumnoC_Table.ALL_COLUMN_PROPERTIES))
                        .from(AsistenciaSesionAlumnoC.class)
                        .where(AsistenciaSesionAlumnoC_Table.key.withTable()
                                .eq(asistenciaUi.getId()))
                        .querySingle();
               if(asistenciaSesionAlumnoC==null) throw new Error("no se hallo la asistencia");
               asistenciaSesionAlumnoC.setValorTipoNotaId(asistenciaUi.getValorTipoNotaUi()==null? "" : asistenciaUi.getValorTipoNotaUi().getId());
               if(asistenciaUi.getHora()!=null) asistenciaSesionAlumnoC.setHora(asistenciaUi.getHora());

                //justificacion
                if(asistenciaUi.getJustificacionUi()!=null){

                    JustificacionC justificacionC= SQLite.select(Utils.f_allcolumnTable(JustificacionC_Table.ALL_COLUMN_PROPERTIES))
                            .from(JustificacionC.class).where(JustificacionC_Table.asistenciaSesionId.withTable().eq(asistenciaSesionAlumnoC.key)).querySingle();
                    if(justificacionC!=null){

                        if(asistenciaUi.isSelected()) {
                            justificacionC.setDescripcion(asistenciaUi.getJustificacionUi().getRazon());
                            justificacionC.setTipoJustificacionId(asistenciaUi.getJustificacionUi().getTipo().getId());
                            justificacionC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                            justificacionC.update();

                        }
                            else{
                                List<ArchivoAsistencia>archivoAsistenciaList= SQLite.select().from(ArchivoAsistencia.class)
                                        .where(ArchivoAsistencia_Table.justificacionId.withTable().eq(justificacionC.key))
                                        .queryList();

                                    for(ArchivoAsistencia a: archivoAsistenciaList){
                                        a.setSyncFlag(BaseEntity.FLAG_DELETED);
                                        a.delete();
                                    }
                               // Log.d(TAG, "delete justificacion");
                                justificacionC.setSyncFlag(BaseEntity.FLAG_DELETED);
                                justificacionC.delete();
                            }

                    }else{
                        justificacionC = new JustificacionC();
                        justificacionC.setAsistenciaSesionId(asistenciaUi.getId());
                        justificacionC.setDescripcion(asistenciaUi.getJustificacionUi().getRazon());
                        justificacionC.setTipoJustificacionId(asistenciaUi.getJustificacionUi().getTipo().getId());
                        justificacionC.setSyncFlag(BaseEntity.FLAG_ADDED);
                        justificacionC.save();
                    }


                    SQLite.delete()
                            .from(ArchivoAsistencia.class)
                            .where(ArchivoAsistencia_Table.justificacionId.eq(justificacionC.getKey()))
                            .execute();

                    List<AsistenicaArchivoUi> asistenicaArchivoUis = asistenciaUi.getAsistenicaArchivoUiList();
                    if(asistenicaArchivoUis!=null){
                     //   Log.d(TAG,"asistenicaArchivoUis: " + asistenicaArchivoUis.size());
                        for (AsistenicaArchivoUi asistenicaArchivoUi : asistenicaArchivoUis){
                            ArchivoAsistencia archivoAsistencia = new ArchivoAsistencia();
                            archivoAsistencia.setNombre(asistenicaArchivoUi.getNombre());
                            switch (asistenicaArchivoUi.getTipo()){
                                case PDF:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_PDF);
                                    break;
                                case AUDIO:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_AUDIO);
                                    break;
                                case VIDEO:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_VIDEO);
                                    break;
                                case VINCULO:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_VINCULO);
                                    break;
                                case IMAGEN:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_IMAGEN);
                                    break;
                                case DOCUMENTO:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_DOCUMENTO);
                                    break;
                                case PRESENTACION:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_DIAPOSITIVA);
                                    break;
                                case HOJA_CALCULO:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_HOJA_CALCULO);
                                    break;
                                case MATERIALES:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_VINCULO);
                                    break;
                                case DEFAUL:
                                    archivoAsistencia.setTipoId(ArchivoAsistencia.TIPO_VINCULO);
                                    break;
                            }
                            archivoAsistencia.setPath(asistenicaArchivoUi.getUrl());
                            archivoAsistencia.setLocalpath(asistenicaArchivoUi.getPath());
                            archivoAsistencia.setJustificacionId(justificacionC.getKey());
                            archivoAsistencia.setSyncFlag(BaseEntity.FLAG_ADDED);
                            archivoAsistencia.setKey(asistenicaArchivoUi.getKey());
                            archivoAsistencia.setArchivoAsistenciaId(asistenicaArchivoUi.getKey());
                            boolean estadoArchivo = archivoAsistencia.save();
                           // Log.d(TAG,"estadoArchivo: " + estadoArchivo);
                            if(!estadoArchivo) throw new Error("no se guardo archivo");
                        }
                    }

                }
                asistenciaSesionAlumnoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                asistenciaSesionAlumnoC.save();

            }
            callback.onLoad(true);
        }catch (Exception ex){
            ex.printStackTrace();
            Log.d(TAG ,"Error al guardar lista de asistencias "+ ex.getMessage());
        }

    }

    @Override
    public void getTiposJustificacion( SucessCallback<List<TipoUi>> callback) {

        List<Tipos> tiposList= SQLite.select(Utils.f_allcolumnTable(Tipos_Table.ALL_COLUMN_PROPERTIES))
                .from(Tipos.class).where(Tipos_Table.objeto.withTable().eq(TipoUi.objeto.T_AS_MAE_JUSTIFICACION.name())).queryList();

        List<TipoUi> tipoUis= new ArrayList<>();
        for(Tipos tipos: tiposList){
            TipoUi tipoUi= new TipoUi();
            tipoUi.setId(tipos.getTipoId());
            tipoUi.setConcepto(tipos.getConcepto());
            tipoUi.setNombre(tipos.getNombre());
            tipoUis.add(tipoUi);
        }

       callback.onLoad(true, tipoUis);

    }

    @Override
    public void generarAsistencia(AsistenciaUi asistenciaUi, CallbackAsistencia callback) {
//        Log.d(TAG, "getAsistenciaAlumnos local"+  asistenciaUi.getIdCargaAcademica());
//        Log.d(TAG, "getAsistenciaAlumnos local pro"+  asistenciaUi.getIdProgramaEducativo());

        try{
            long fecha=Utils.transformarFecha_a_FechaAsistenciaSinHora(asistenciaUi.getFecha());
            boolean response;
            long count = SQLite.selectCountOf()
                    .from(AsistenciaSesionAlumnoC.class)
                    .where(AsistenciaSesionAlumnoC_Table.georeferenciaId.withTable().eq(asistenciaUi.getIdGeoreferencia()))
                    .and(AsistenciaSesionAlumnoC_Table.calendarioPeriodoId.withTable().eq(asistenciaUi.getIdCalendarioPeriodo()))
                    .and(AsistenciaSesionAlumnoC_Table.cargaCursoId.withTable().eq(asistenciaUi.getIdCargaCurso()))
                    .and(AsistenciaSesionAlumnoC_Table.docenteId.withTable().eq(asistenciaUi.getIdDocente()))
                    .and(AsistenciaSesionAlumnoC_Table.fechaAsistencia.withTable().eq(fecha))
                    .count();
            if(count == 0){
                if(asistenciaUi.getIdCargaCurso()==0){
                    GenerarAsistenciaPorCargaAcademicaRequest request= new GenerarAsistenciaPorCargaAcademicaRequest();
                    request.setCalendarioPeridoId(asistenciaUi.getIdCalendarioPeriodo());
                    request.setCargaAcademicaId(asistenciaUi.getIdCargaAcademica());
                    request.setEmpleadoId(asistenciaUi.getIdDocente());
                    request.setFechaAsistenciaSinHora(fecha);
                    request.setHora(gethora());
                    request.setGeorefenciaId(asistenciaUi.getIdGeoreferencia());
                    request.setProgramaEducativoId(asistenciaUi.getIdProgramaEducativo());
                    request.setEstadoAsistenciaValorTipoNota(ValorTipoNotaC.PUNTUAL);
                    response= asistenciaSesionAlumnoDao.generarAsistenciaPorCargaAcademica(request);
                }
                else {
                    GenerarAsistenciaPorCursoRequest request= new GenerarAsistenciaPorCursoRequest();
                    request.setCalendarioPeridoId(asistenciaUi.getIdCalendarioPeriodo());
                    request.setCargaCursoId(asistenciaUi.getIdCargaCurso());
                    request.setEmpleadoId(asistenciaUi.getIdDocente());
                    request.setFechaAsistenciaSinHora(fecha);
                    request.setHora(gethora());
                    request.setGeorefenciaId(asistenciaUi.getIdGeoreferencia());
                    request.setProgramaEducativoId(asistenciaUi.getIdProgramaEducativo());
                    request.setEstadoAsistenciaValorTipoNota(ValorTipoNotaC.PUNTUAL);
                    response= asistenciaSesionAlumnoDao.generarAsistenciaPorCurso(request);
                    ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(asistenciaUi.getIdCargaCurso());
                    if (parametrosDisenio==null)return;
                    asistenciaUi.setColor(parametrosDisenio.getColor1());
                }

                //Log.d(TAG, "onSuccessAsistencia"+response);

                callback.onAsistencia(true, asistenciaUi);
        }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "Error al generar Asistencia"+ e.getMessage());
            callback.onAsistencia(false, null);
        }


    }

    private String gethora() {
        Calendar c = Calendar.getInstance();
       // c.setTimeInMillis(1570292433000L);
        int hora= c.get(Calendar.HOUR_OF_DAY);
        int minuto= c.get(Calendar.MINUTE);
        Log.d(TAG, "hora string 1 "+  hora +":"+minuto+ " hora  "+ c.get(Calendar.HOUR));
        return  hora +":"+minuto;
    }

    public List<Integer>cursosCargaList(int cargaAcademicaId){
       List<Integer>listInteger= new ArrayList<>();
       List<CargaCursos >cargaCursosList= SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
               .from(CargaCursos.class)
               .where(CargaCursos_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId)).queryList();
       for(CargaCursos cargaCursos: cargaCursosList)listInteger.add(cargaCursos.getCargaCursoId());
       return listInteger;
   }

    @Override
    public void getArchivoAsistenciaList(String asisteniciaId, String justificacionId, SucessCallback<List<AsistenicaArchivoUi>> callback) {
        List<ArchivoAsistencia> archivoAsistencias = SQLite.select()
                .from(ArchivoAsistencia.class)
                .where(ArchivoAsistencia_Table.justificacionId.eq(justificacionId))
                .queryList();
        List<AsistenicaArchivoUi> asistenicaArchivoUiList = new ArrayList<>();
        for (ArchivoAsistencia archivoAsistencia : archivoAsistencias){
            AsistenicaArchivoUi asistenicaArchivoUi = new AsistenicaArchivoUi();
            asistenicaArchivoUi.setKey(archivoAsistencia.getKey());
            asistenicaArchivoUi.setNombre(archivoAsistencia.getNombre());
            asistenicaArchivoUi.setUrl(archivoAsistencia.getPath());
            asistenicaArchivoUi.setPath(archivoAsistencia.getLocalpath());
            asistenicaArchivoUi.setFechaAccionArchivo(archivoAsistencia.getFechaAccion());
            switch (archivoAsistencia.getTipoId()){
                case ArchivoAsistencia.TIPO_PDF:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.PDF);
                    break;
                case ArchivoAsistencia.TIPO_AUDIO:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.AUDIO);
                    break;
                case ArchivoAsistencia.TIPO_VIDEO:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VIDEO);
                    break;
                case ArchivoAsistencia.TIPO_VINCULO:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.VINCULO);
                    break;
                case ArchivoAsistencia.TIPO_IMAGEN:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.IMAGEN);
                    break;
                case ArchivoAsistencia.TIPO_DOCUMENTO:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.DOCUMENTO);
                    break;
                case ArchivoAsistencia.TIPO_DIAPOSITIVA:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.PRESENTACION);
                    break;
                case ArchivoAsistencia.TIPO_HOJA_CALCULO:
                    asistenicaArchivoUi.setTipo(AsistenicaArchivoUi.Tipo.HOJA_CALCULO);
                    break;
            }
            asistenicaArchivoUiList.add(asistenicaArchivoUi);

        }
        callback.onLoad(true,asistenicaArchivoUiList );
    }

    @Override
    public void getFechaAsistenciaPorBimestre(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int parametroDisenioId, boolean state, int cargaAcademicaId, SucessCallback<List<FechaAsistenciaUi>> callback) {
        Log.d(TAG, "set time "+ new Date().getTime());
        Log.d(TAG, "cargaCursoId "+ cargaCursoId);
       Log.d(TAG, "calendarioPeriodoId "+ calendarioPeriodoId);
        Log.d(TAG, "state "+ state);
        Log.d(TAG, "cargaAcademicaId "+ cargaAcademicaId);
        Log.d(TAG, "anioAcademicoId "+ anioAcademicoId);

        List<Integer>listInteger= new ArrayList<>();
        if(cargaCursoId==0)listInteger=cursosCargaList(cargaAcademicaId);
        else listInteger.add(cargaCursoId);

        List<Long> fechaAsistencia= asistenciaSesionAlumnoDao.getFechaAsistenciaPorCalendarioPeriodoPorFechaActual(listInteger, calendarioPeriodoId, new Date(), anioAcademicoId);
        List<AsistenciaSesionAlumnoC> asistenciaSesionAlumnoCList= asistenciaSesionAlumnoDao.getAsistenciaPorCalendarioPeriodo(cargaCursoId, calendarioPeriodoId, cargaAcademicaId);

       Log.d(TAG, "asistenciaSesionAlumnoCList Size :" + asistenciaSesionAlumnoCList.size());
       Log.d(TAG, "fechaAsistencia Size :" + fechaAsistencia.size());
        ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cargaCursoId);
        ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
        if(parametrosDisenio!=null){
            parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
            parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
            parametroDisenioUi.setColor3(parametrosDisenio.getColor3());
        }

        List<FechaAsistenciaUi> fechaAsistenciaUis = new ArrayList<>();
        for (Long fecha: fechaAsistencia){
            FechaAsistenciaUi fechaAsistenciaUi = new FechaAsistenciaUi();
            fechaAsistenciaUi.setFechaAsistencia(fecha);
            fechaAsistenciaUi.setParametroDisenioUi(parametroDisenioUi);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(fecha);

            int cantidadNoEvaluado = 0;
            int cantidadPuntual = 0;
            int cantidadTarde = 0;
            int cantidadEvaluado = 0;
            int cantidadAusente = 0;
            int cantidadTotal = 0;
            fechaAsistenciaUi.setCantidadNoEvaluados(0);
            for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: asistenciaSesionAlumnoCList){

               Calendar calendar = Calendar.getInstance();
               calendar.setTimeInMillis(asistenciaSesionAlumnoC.getFechaAsistencia());

               Calendar c = Calendar.getInstance();
               c.setTimeInMillis(fecha);

                if(calendar.get(Calendar.YEAR)==c.get(Calendar.YEAR)&&calendar.get(Calendar.MONTH)==c.get(Calendar.MONTH)&&calendar.get(Calendar.DATE)== c.get(Calendar.DATE)){

                    if(TextUtils.isEmpty(asistenciaSesionAlumnoC.getValorTipoNotaId())){
                        cantidadNoEvaluado++;
                    }else{
                        cantidadEvaluado++;
                        ValorTipoNotaC valorTipoNotaC = valorTipoNotaDao.getValorTipoNotaPorId(asistenciaSesionAlumnoC.getValorTipoNotaId());
                        if (valorTipoNotaC==null) return;
                        switch (valorTipoNotaC.getEstadoId()){

                            case ValorTipoNotaC.PUNTUAL:
                                cantidadPuntual++;
                                break;
                            case ValorTipoNotaC.TARDE:
                                cantidadTarde++;
                                break;
                            case ValorTipoNotaC.AUSENTE:
                                cantidadAusente++;
                                break;
                            case ValorTipoNotaC.TARDE_JDT:
                                cantidadTarde++;
                                break;
                            case ValorTipoNotaC.AUSENTE_JDT:
                                cantidadAusente++;
                                break;
                        }
                    }
                    cantidadTotal++;
                    fechaAsistenciaUi.setTipo(FechaAsistenciaUi.Tipo.GENERADO);
                }

            }
            fechaAsistenciaUi.setCantidadPuntual(cantidadPuntual);
            fechaAsistenciaUi.setCantidadTarde(cantidadTarde);
            fechaAsistenciaUi.setCantidadAusente(cantidadAusente);
            fechaAsistenciaUi.setCantidadTotal(cantidadTotal);
            fechaAsistenciaUi.setCantidadNoEvaluados(cantidadNoEvaluado);
            fechaAsistenciaUi.setCantidadEvaluado(cantidadEvaluado);
            fechaAsistenciaUis.add(fechaAsistenciaUi);
        }

          callback.onLoad(true,fechaAsistenciaUis);
    }




}
