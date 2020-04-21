package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.instrumentoObservada.InstrumentoObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionCaracteristica;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionCaracteristica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoInstrumento;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoInstrumento_Table;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.InfoEstiloAprendizajeSource;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.CaracteristicaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.InstrumentoObservadoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InfoEstiloAprendizajeLocalDataSource implements InfoEstiloAprendizajeSource {
    public static final String TAG = InfoEstiloAprendizajeLocalDataSource.class.getSimpleName();
    private DimensionObservadaDao dimensionObservadaDao;
    private InstrumentoObservadaDao instrumentoObservadaDao;
    private PersonaDao personaDao;

    public InfoEstiloAprendizajeLocalDataSource(DimensionObservadaDao dimensionObservadaDao, InstrumentoObservadaDao instrumentoObservadaDao, PersonaDao personaDao) {
        this.dimensionObservadaDao = dimensionObservadaDao;
        this.instrumentoObservadaDao = instrumentoObservadaDao;
        this.personaDao = personaDao;
    }

    @Override
    public void getListaDimensiones(int alumnoId, int entidadId, int georeferenciaId, Callback<AlumnoUi> callback) {
        try {
            AlumnoUi alumnoUi = new AlumnoUi();
            List<InstrumentoObservadoUi> instrumentoObservadoUiList = new ArrayList<>();
            List<InstrumentoObservado> instrumentoObservadoList = instrumentoObservadaDao.getInstrumentoAlumno(alumnoId, entidadId, georeferenciaId);
            for(InstrumentoObservado instrumentoObservado: instrumentoObservadoList){
                List<DimensionObservadaUi> dimensionObservadaUiList = new ArrayList<>();
                List<DimensionObservada> dimensionObservadaList = dimensionObservadaDao.getDimensionesAlumnoInstrumento(alumnoId, entidadId, georeferenciaId, instrumentoObservado.getInstrumentoObservadoId());
                int posicion = 0;
                for (DimensionObservada dimensionObservada : dimensionObservadaList) {


                    Dimension dimension = SQLite.select()
                            .from(Dimension.class)
                            .where(Dimension_Table.dimensionId.eq(dimensionObservada.getDimensionId()))
                            .querySingle();


                    DimensionObservadaUi dimensionObservadaUi = new DimensionObservadaUi();
                    dimensionObservadaUi.setPoscion(posicion);
                    dimensionObservadaUi.setKey(dimensionObservada.getDimensionObservadaId());
                    int valor = (int) (dimensionObservada.getValor()*100);
                    dimensionObservadaUi.setValor(valor);
                    if (dimension == null) continue;
                    DimensionUi dimensionUi = new DimensionUi();
                    dimensionUi.setId(dimension.getDimensionId());
                    dimensionUi.setNombre(dimension.getNombre());
                    dimensionUi.setDescripcion(dimension.getDescripcion());
                    dimensionUi.setInstrumentoEvalId(dimension.getInstrumentoEvalId());
                    dimensionUi.setSigla(dimension.getSigla());
                    dimensionUi.setEnfoque(dimension.getEnfoque());
                    dimensionUi.setConfiabilidad(dimension.getConfiabilidad());
                    dimensionUi.setIntervaloInicio(dimension.getIntervaloInicio());
                    dimensionUi.setIntervaloFin(dimension.getIntervaloFin());
                    dimensionUi.setIncluidoIInicio(dimension.getIncluidoIInicio());
                    dimensionUi.setIncluidoIFin(dimension.getIncluidoIFin());
                    dimensionUi.setColor(dimension.getColor());
                    dimensionUi.setIcono(dimension.getIcono());
                    dimensionUi.setMedida(dimension.getMedida());
                    dimensionUi.setOrden(dimension.getOrden());


                    List<CaracteristicaUi> caracteristicaUiList =  new ArrayList<>();
                    List<DimensionCaracteristica> dimensionCaracteristicaList = SQLite.select()
                            .from(DimensionCaracteristica.class)
                            .where(DimensionCaracteristica_Table.dimensionId.eq(dimension.getDimensionId()))
                            .queryList();
                    for (DimensionCaracteristica dimensionCaracteristica : dimensionCaracteristicaList){
                        CaracteristicaUi caracteristicaUi =  new CaracteristicaUi();
                        caracteristicaUi.setId(dimensionCaracteristica.getCaracteristicaId());
                        caracteristicaUi.setDescripcion(dimensionCaracteristica.getDescripcion());
                        caracteristicaUi.setNombre(dimensionCaracteristica.getNombre());
                        caracteristicaUiList.add(caracteristicaUi);
                    }
                    dimensionUi.setCaracteristicaUiList(caracteristicaUiList);
                    dimensionObservadaUi.setDimensionUi(dimensionUi);
                    dimensionObservadaUiList.add(dimensionObservadaUi);
                }


                InstrumentoObservadoUi instrumentoObservadoUi = new InstrumentoObservadoUi();
                instrumentoObservadoUi.setKey(instrumentoObservado.getInstrumentoObservadoId());
                instrumentoObservadoUi.setFechaEvaluacion(Utils.f_fecha_letras_dos(instrumentoObservado.getFechaEvaluacion()));
                instrumentoObservadoUi.setPersonaId(instrumentoObservado.getPersonaId());
                instrumentoObservadoUi.setIcono("https://www.protosabios.com/wp-content/uploads/2016/03/test-de-orientacion-profesional.png");
                InstrumentoEvaluacion  instrumentoEvaluacion = SQLite.select()
                        .from(InstrumentoEvaluacion.class)
                        .where(InstrumentoObservado_Table.instrumentoEvalId.eq(instrumentoObservado.getInstrumentoEvalId()))
                        .querySingle();
                if(instrumentoEvaluacion != null) instrumentoObservadoUi.setNombreEvaluacion(instrumentoEvaluacion.getNombre());

                List<DimensionObservadaUi> dimensionObservadaUiListClone = new ArrayList<>(dimensionObservadaUiList);


                if(dimensionObservadaUiListClone.size() >= 1)instrumentoObservadoUi.setDimensionObservadaUiPrincipalOne(dimensionObservadaUiListClone.get(0));
                if(dimensionObservadaUiListClone.size() >= 2)instrumentoObservadoUi.setDimensionObservadaUiPrincipalTwo(dimensionObservadaUiListClone.get(1));

                instrumentoObservadoUi.setDimensionObservadaUiList(dimensionObservadaUiList);
                List<DimensionObservadaUi> dimensionObservadaUiListNew = getNewdimensionObservadaUiListOrderValor(dimensionObservadaUiList);
                if(dimensionObservadaUiListNew.size() >= 1)instrumentoObservadoUi.setDimensionObservadaUiPrincipalOne(dimensionObservadaUiListNew.get(0));
                if(dimensionObservadaUiListNew.size() >= 2)instrumentoObservadoUi.setDimensionObservadaUiPrincipalTwo(dimensionObservadaUiListNew.get(1));

                instrumentoObservadoUi.setDimensionObservadaUiListOrdenada(dimensionObservadaUiListNew);


                instrumentoObservadoUiList.add(instrumentoObservadoUi);
            }


            Log.d(getClass().getSimpleName(), "Size: "+ instrumentoObservadoUiList.size());
            alumnoUi.setInstrumentoObservadoUiList(instrumentoObservadoUiList);

            Persona persona = personaDao.get(alumnoId);
            alumnoUi.setNombre(persona.getFirstName());
            alumnoUi.setApellido(persona.getApellidos());
            alumnoUi.setImagen(persona.getFoto());
            callback.load(true, alumnoUi);
        }catch (Exception e){
            e.printStackTrace();
            callback.load(false, null);
        }

    }

    private List<DimensionObservadaUi> getNewdimensionObservadaUiListOrderValor(List<DimensionObservadaUi> dimensionObservadaUiList) {

        List<DimensionObservadaUi> dimensionObservadaUiListNew = new ArrayList<>(dimensionObservadaUiList);
        try {


            int cantidadTotal = 0;
            DimensionObservadaUi dimensionObservadaUisUno = dimensionObservadaUiList.get(0);
            DimensionObservadaUi dimensionObservadaUisDos = dimensionObservadaUiList.get(1);
            DimensionObservadaUi dimensionObservadaUisTres = dimensionObservadaUiList.get(2);
            DimensionObservadaUi dimensionObservadaUisCuatro = dimensionObservadaUiList.get(3);
            int areaUno = (int)(dimensionObservadaUisUno.getValor() * dimensionObservadaUisDos.getValor() / 2);
            int areaDos = (int)(dimensionObservadaUisDos.getValor() * dimensionObservadaUisTres.getValor() / 2);
            int areaTres = (int)(dimensionObservadaUisTres.getValor() * dimensionObservadaUisCuatro.getValor() / 2);
            int areaCuatro = (int)(dimensionObservadaUisCuatro.getValor() * dimensionObservadaUisUno.getValor() / 2);
            cantidadTotal = areaUno + areaDos + areaTres + areaCuatro;

            dimensionObservadaUisUno.setArea(areaUno);
            float porsentajeOne = (float) areaUno / cantidadTotal;
            dimensionObservadaUisUno.setPorcentaje((int) (porsentajeOne*100));

            dimensionObservadaUisDos.setArea(areaDos);
            float porsentajeDos =(float) areaDos/cantidadTotal;
            porsentajeDos = porsentajeDos * 100;
            dimensionObservadaUisDos.setPorcentaje((int)porsentajeDos);
            dimensionObservadaUisTres.setArea(areaTres);
            float porsentajeTres = (float)areaTres/cantidadTotal;
            porsentajeTres = porsentajeTres * 100;
            dimensionObservadaUisTres.setPorcentaje((int)porsentajeTres);
            dimensionObservadaUisCuatro.setArea(areaCuatro);
            float porsentajeCuatro = (float)areaCuatro/cantidadTotal;
            porsentajeCuatro = porsentajeCuatro * 100;
            dimensionObservadaUisCuatro.setPorcentaje((int)porsentajeCuatro);


            Collections.sort(dimensionObservadaUiListNew, new Comparator<DimensionObservadaUi>() {
                @Override
                public int compare(DimensionObservadaUi p1, DimensionObservadaUi p2) {
                    return Integer.compare(p2.getArea(), p1.getArea());
                }
            });

            int posicion2=0;
            for (DimensionObservadaUi dimensionObservadaUi: dimensionObservadaUiListNew){
                posicion2++;
                dimensionObservadaUi.setPoscion(posicion2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return dimensionObservadaUiListNew;

    }
}
