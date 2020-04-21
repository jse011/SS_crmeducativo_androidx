package com.consultoraestrategia.ss_crmeducativo.actividades.data.source.local;


import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.actividades.data.source.ActividadesDataSource;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ActividadesUi;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.EEstado;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ESecuencia;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.RecursosUi;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.SubRecursosUi;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.ParametroDisenioCargacursoModel;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeHelper;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by kike on 08/02/2018.
 */

public class ActividadesLocalDataSource implements ActividadesDataSource {

    private static String TAG = ActividadesLocalDataSource.class.getSimpleName();

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {
        Archivo archivo = SQLite.select()
                .from(Archivo.class)
                .where(Archivo_Table.key.eq(archivoId))
                .querySingle();
        if (archivo != null) {
            archivo.setLocalpath(path);
            boolean success = archivo.save();
            booleanCallback.onLoad(success, success);
        } else {
            booleanCallback.onLoad(false, false);
        }
    }

    @Override
    public void getActividadesList(int cargaCurso, int sesionAprendizajeId, int backgroundColor, int personaId, CallbackActividades callbackActividades) {

        List<ActividadAprendizaje> padreActividadAprendizajeList = SQLite.select(Utils.f_allcolumnTable(ActividadAprendizaje_Table.ALL_COLUMN_PROPERTIES))
                .from(ActividadAprendizaje.class)
                .where(ActividadAprendizaje_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .and(ActividadAprendizaje_Table.parentId.is(0))
                .queryList();

        List<ActividadesUi> actividadListPadre = new ArrayList<>();

        List<RecursoDidacticoEventoC> mlst_recursoDidacticoEventoPrimero = SQLite.select()
                .from(RecursoDidacticoEventoC.class)
                .innerJoin(RecursoReferenciaC.class)
                .on(RecursoReferenciaC_Table.recursoDidacticoId.withTable().eq(RecursoDidacticoEventoC_Table.key.withTable()))
                .where(RecursoReferenciaC_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .queryList();

        List<RecursosUi> recursosUiListPrimero = new ArrayList<>();

        int countRecursoActividad = 0;
        for (ActividadAprendizaje actividad : padreActividadAprendizajeList) {
                List<RecursoDidacticoEventoC> mlst_recursoDidacticoEvento = SQLite.select()
                        .from(RecursoDidacticoEventoC.class)
                        .innerJoin(RecursoReferenciaC.class)
                        .on(RecursoReferenciaC_Table.recursoDidacticoId.withTable().eq(RecursoDidacticoEventoC_Table.key.withTable()))
                        .where(RecursoReferenciaC_Table.actividadAprendizajeId.withTable().is(actividad.getActividadAprendizajeId()))
                        .queryList();

                Tipos tipoActividad = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.withTable().is(actividad.getTipoActividadId()))
                        .querySingle();

                Tipos secuencia = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.withTable().is(actividad.getSecuenciaId()))
                        .querySingle();

                /*Sub Recursos*/
                List<ActividadAprendizaje> actividadAprendizajes = SQLite.select()
                        .from(ActividadAprendizaje.class)
                        .where(ActividadAprendizaje_Table.parentId.is(actividad.getActividadAprendizajeId()))
                        .queryList();
                List<SubRecursosUi> subRecursosUiList = new ArrayList<>();
                int i = 0;
                /*Cuano tiene Recursos Hijos - de la actividad*/
                for (ActividadAprendizaje aprendizaje : actividadAprendizajes) {
                    if (aprendizaje != null) {
                        i++;
                        List<RecursoDidacticoEventoC> mlst_recursoDidacticoEvento2 = SQLite.select()
                                .from(RecursoDidacticoEventoC.class)
                                .innerJoin(RecursoReferenciaC.class)
                                .on(RecursoReferenciaC_Table.recursoDidacticoId.withTable().eq(RecursoDidacticoEventoC_Table.key.withTable()))
                                .where(RecursoReferenciaC_Table.actividadAprendizajeId.withTable().is(aprendizaje.getActividadAprendizajeId()))
                                .queryList();

                        subRecursosUiList.add(new SubRecursosUi(i, aprendizaje.getDescripcionActividad(), getListSubRecurso(mlst_recursoDidacticoEvento2)));
                    }
                }

                ParametrosDisenio parametrosDisenio = ParametroDisenioCargacursoModel.SQLView()
                        .select(Utils.f_allcolumnTable(ParametrosDisenio_Table.ALL_COLUMN_PROPERTIES))
                        .getQuery(cargaCurso)
                        .querySingle();
                if (parametrosDisenio != null) {
                    try {
                        backgroundColor = Integer.parseInt(parametrosDisenio.getColor1().replaceFirst("#", ""), 16);
                    } catch (Exception e) {
                        backgroundColor = R.color.colorPrimary;
                    }
                } else {
                    backgroundColor = R.color.colorPrimary;
                }

                if (actividad != null && secuencia != null) {
                    ActividadesUi actividadesUi = new ActividadesUi(actividad.getActividad(),
                            countRecursoActividad,
                            actividad.getTiempo(),
                            actividad.getEstadoId(),
                            actividad.getTiempo(),
                            tipoActividad.getNombre(),
                            secuencia.getNombre(),
                            actividad.getDescripcionActividad(),
                            backgroundColor,
                            getRecursosListActividad(mlst_recursoDidacticoEvento),
                            subRecursosUiList);

                    actividadesUi.setId(actividad.getActividadAprendizajeId());

                    switch (secuencia.getTipoId()) {
                        case 356:
                            actividadesUi.seteSecuencia(ESecuencia.Inicio);
                            break;
                        case 357:
                            actividadesUi.seteSecuencia(ESecuencia.Desarrollo);
                            break;
                        case 358:
                            actividadesUi.seteSecuencia(ESecuencia.Cierre);
                            break;
                    }

                    switch (actividad.getEstadoId()) {
                        case ActividadAprendizaje.ESTADO_CREADO:
                            actividadesUi.seteEstado(EEstado.Creado);
                            break;
                        case ActividadAprendizaje.ESTADO_PENDIENTE:
                            actividadesUi.seteEstado(EEstado.Pendiente);
                            break;
                        case ActividadAprendizaje.ESTADO_HECHO:
                            actividadesUi.seteEstado(EEstado.Hecho);
                            break;
                    }

                    actividadListPadre.add(actividadesUi);
                }
        }
        for (RecursoDidacticoEventoC recursoDidacticoEvento : mlst_recursoDidacticoEventoPrimero) {
            if (recursoDidacticoEvento != null)
                recursosUiListPrimero.add(getRecursoEvento(recursoDidacticoEvento));
        }
        callbackActividades.onListeActividades(actividadListPadre, recursosUiListPrimero, 1);
    }

    @Override
    public void updateActividad(ActividadesUi actividadesUi, Callback<ActividadesUi> callback) {

            ActividadAprendizaje actividadAprendizaje = SQLite.select()
                    .from(ActividadAprendizaje.class)
                    .where(ActividadAprendizaje_Table.actividadAprendizajeId.eq(actividadesUi.getId()))
                    .querySingle();

            if (actividadAprendizaje != null) {
                switch (actividadesUi.geteEstado()) {
                    case Hecho:
                        actividadAprendizaje.setEstadoId(ActividadAprendizaje.ESTADO_HECHO);
                        break;
                    case Creado:
                        actividadAprendizaje.setEstadoId(ActividadAprendizaje.ESTADO_CREADO);
                        break;
                    case Pendiente:
                        actividadAprendizaje.setEstadoId(ActividadAprendizaje.ESTADO_PENDIENTE);
                        break;
                }
                actividadAprendizaje.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                actividadAprendizaje.save();
            }
            callback.onLoad(true, actividadesUi);
    }

    private RecursosUi getRecursoEvento(RecursoDidacticoEventoC recursoDidacticoEvento) {
        RecursosUi recursosUI = new RecursosUi();
        recursosUI.setRecursoId(recursoDidacticoEvento.getKey());
        recursosUI.setNombreRecurso(recursoDidacticoEvento.getTitulo());
        recursosUI.setDescripcion(recursoDidacticoEvento.getDescripcion());
        recursosUI.setFechaCreacionRecuros(recursoDidacticoEvento.getFechaCreacion());
        boolean isYoutube = false;
        switch (recursoDidacticoEvento.getTipoId()) {
            case RecursoDidacticoEventoC.TIPO_AUDIO:
                recursosUI.setTipoFileU(RepositorioTipoFileU.AUDIO);
                break;
            case RecursoDidacticoEventoC.TIPO_DIAPOSITIVA:
                recursosUI.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                break;
            case RecursoDidacticoEventoC.TIPO_DOCUMENTO:
                recursosUI.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                break;
            case RecursoDidacticoEventoC.TIPO_HOJA_CALCULO:
                recursosUI.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                break;
            case RecursoDidacticoEventoC.TIPO_IMAGEN:
                recursosUI.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                break;
            case RecursoDidacticoEventoC.TIPO_PDF:
                recursosUI.setTipoFileU(RepositorioTipoFileU.PDF);
                break;
            case RecursoDidacticoEventoC.TIPO_VIDEO:
                isYoutube = !TextUtils.isEmpty(YouTubeHelper.extractVideoIdFromUrl(recursoDidacticoEvento.getUrl()));
                if (!isYoutube) {
                    isYoutube = !TextUtils.isEmpty(YouTubeHelper.extractVideoIdFromUrl(recursosUI.getDescripcion()));
                    if (isYoutube) {
                        recursosUI.setUrl(recursosUI.getDescripcion());
                    }
                }
                if (isYoutube) {
                    recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                    recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                    recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                    recursosUI.setTipoFileU(RepositorioTipoFileU.YOUTUBE);
                } else {
                    recursosUI.setTipoFileU(RepositorioTipoFileU.VIDEO);
                }
                break;
            case RecursoDidacticoEventoC.TIPO_VINCULO:
                recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                recursosUI.setTipoFileU(RepositorioTipoFileU.VINCULO);
                break;

        }

        Log.d(TAG, "archivo:( " + recursoDidacticoEvento.getUrl());
        if (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_AUDIO ||
                recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_DIAPOSITIVA ||
                recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_DOCUMENTO ||
                recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_HOJA_CALCULO ||
                recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_IMAGEN ||
                recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_PDF ||
                (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_VIDEO
                        && !isYoutube)
        ) {

            Archivo archivo = SQLite.select(Utils.f_allcolumnTable(Archivo_Table.ALL_COLUMN_PROPERTIES))
                    .from(Archivo.class)
                    .innerJoin(RecursoArchivo.class)
                    .on(Archivo_Table.key.withTable().eq(RecursoArchivo_Table.archivoId.withTable()))
                    .where(RecursoArchivo_Table.recursoDidacticoId.withTable().eq(recursoDidacticoEvento.getKey()))
                    .querySingle();

            Log.d(TAG, "archivo:(");
            if (archivo != null) {
                Log.d(TAG, "great");
                recursosUI.setArchivoId(archivo.getKey());
                recursosUI.setNombreArchivo(archivo.getNombre());
                recursosUI.setPath(archivo.getLocalpath());
                recursosUI.setUrl(archivo.getPath());
                recursosUI.setFechaCreacionRecuros(archivo.getFechaCreacion());
                if (TextUtils.isEmpty(archivo.getLocalpath())) {
                    recursosUI.setEstadoFileU(RepositorioEstadoFileU.SIN_DESCARGAR);
                } else {
                    recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                }
                recursosUI.setUrl(archivo.getPath());
                recursosUI.setPath(archivo.getLocalpath());
                recursosUI.setFechaAccionArchivo(archivo.getFechaAccion());
            } else if (recursoDidacticoEvento.getTipoId() == RecursoDidacticoEventoC.TIPO_VIDEO) {
                recursosUI.setNombreArchivo(recursoDidacticoEvento.getUrl());
                recursosUI.setUrl(recursoDidacticoEvento.getUrl());
                recursosUI.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                recursosUI.setTipoFileU(RepositorioTipoFileU.VINCULO);
                recursosUI.setFechaAccionArchivo(recursoDidacticoEvento.getFechaCreacion());
            }

        } else {
            String url = recursoDidacticoEvento.getUrl();
            if (TextUtils.isEmpty(url)) url = recursoDidacticoEvento.getDescripcion();
            recursosUI.setUrl(url);
            recursosUI.setFechaAccionArchivo(recursoDidacticoEvento.getFechaCreacion());
        }
        return recursosUI;
    }


    private List<RecursosUi> getListSubRecurso(List<RecursoDidacticoEventoC> mlst_recursoDidacticoEvento2) {
        List<RecursosUi> recursosSubRecursoList = new ArrayList<>();
            for (RecursoDidacticoEventoC recursoDidacticoEvento : mlst_recursoDidacticoEvento2)
                recursosSubRecursoList.add(getRecursoEvento(recursoDidacticoEvento));
            return recursosSubRecursoList;
    }

    /*Cuando son Actividad de Recursos- Sin Hijos*/
    private List<RecursosUi> getRecursosListActividad(List<RecursoDidacticoEventoC> mlst_recursoDidacticoEvento) {
        List<RecursosUi> recursosUiListActividad = new ArrayList<>();
            for (RecursoDidacticoEventoC recursoDidacticoEvento : mlst_recursoDidacticoEvento) {
                recursosUiListActividad.add(getRecursoEvento(recursoDidacticoEvento));
            }
            return recursosUiListActividad;
    }


}
