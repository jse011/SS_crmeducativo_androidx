package com.consultoraestrategia.ss_crmeducativo.services.data.source.util;

import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.local.SEDatosCompletosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.remote.SEDatosCompletosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.local.BEDatosCargaAcademicaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.remote.BEDatosCargaAcademicaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.local.BEDatosEnvioAsistenciaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.remote.AsistenciaLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.local.BEDatosEnvioGrupoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.remote.BEDatosEnvioGrupoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.BEDatosEnvioHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.local.BEDatosEnvioHorarioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.remote.BEDatosEnvioHorarioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.local.BEDatosEnvioMensajeriaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.remote.BEDatosEnvioMensajeriaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.local.BEDatosEnvioTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.remote.BEDatosEnvioTipoNotaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.BEDatosEvaluacionResultadoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.local.BEDatosEvaluacionResultadoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.remote.BEDatosEvaluacionResultadoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local.BEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.remote.BEDatosRubroEvaluacionProcesoRemotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.local.BEDatosSesionAprendizajeLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.remote.BEDatosSesionAprendizajeRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.BEDatosSilaboEventoEnvioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.local.BEDatosSilaboEventoEnvioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.remote.BEDatosSilaboEventoEnvioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.BEDatosTareaRecursosRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.local.BEDatosTareaRecursosLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.remoto.BEDatosTareaRecursosRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.GEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.local.GEDatosEnvioAsistenciaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.remoto.GEDatosEnvioAsistenciaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.local.GEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.remoto.GEDatosRubroEvaluacionProcesoRemotelDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.BEObtenerDatosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.local.BEObtenerDatosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.remote.BEObtenerDatosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

/**
 * Created by SCIEV on 5/06/2018.
 */

public class RepositoryInjector {

    public static BEDatosRubroEvaluacionProcesoRepository getBEDatosRubroEvaluacionProcesoRepositoryInjector(){
        return BEDatosRubroEvaluacionProcesoRepository.getInstance(
                new BEDatosRubroEvaluacionProcesoLocalDataSource(InjectorUtils.provideEvaluacionProcesoDao(), InjectorUtils.provideRubroProcesoDao()),
                new BEDatosRubroEvaluacionProcesoRemotaDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosEnvioTipoNotaRepository getBEDatosEnvioTipoNotaRepositoryInjector(){
        return BEDatosEnvioTipoNotaRepository.getInstance(
                new BEDatosEnvioTipoNotaLocalDataSource(),
                new BEDatosEnvioTipoNotaRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosEnvioGrupoRepository getBEDatosEnvioGrupoRepositoryInjector(){
        return BEDatosEnvioGrupoRepository.getInstance(
                new BEDatosEnvioGrupoLocalDataSource(),
                new BEDatosEnvioGrupoRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static GEDatosRubroEvaluacionProcesoRepository getGEDatosRubroEvaluacionProcesoRepositoryInjectorConTareas(){
        return GEDatosRubroEvaluacionProcesoRepository.getInstance(
                new GEDatosRubroEvaluacionProcesoLocalDataSource(
                        new BEDatosRubroEvaluacionProcesoLocalDataSource(InjectorUtils.provideEvaluacionProcesoDao(), InjectorUtils.provideRubroProcesoDao()),
                        new BEDatosEnvioTipoNotaLocalDataSource(),
                        new BEDatosEnvioGrupoLocalDataSource(),
                        getBEDatosTareaRecursosRepositorySinRubros()),
                new GEDatosRubroEvaluacionProcesoRemotelDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    private static GEDatosRubroEvaluacionProcesoRepository getGEDatosRubroEvaluacionProcesoRepositoryInjectorSinTareas(){
        return GEDatosRubroEvaluacionProcesoRepository.getInstance(
                new GEDatosRubroEvaluacionProcesoLocalDataSource(
                        new BEDatosRubroEvaluacionProcesoLocalDataSource(InjectorUtils.provideEvaluacionProcesoDao(), InjectorUtils.provideRubroProcesoDao()),
                        new BEDatosEnvioTipoNotaLocalDataSource(),
                        new BEDatosEnvioGrupoLocalDataSource(),
                        null),
                new GEDatosRubroEvaluacionProcesoRemotelDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosEnvioAsistenciaRepository getBEDatosEnvioAsistenciaRepositoryInjector(){
        return  BEDatosEnvioAsistenciaRepository.getInstance(
                new BEDatosEnvioAsistenciaLocalDataSource(),
                new AsistenciaLoginRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static GEDatosEnvioAsistenciaRepository getGEDatosEnvioAsistenciaRepositoryInjector(){
        return  GEDatosEnvioAsistenciaRepository.getInstance(
                new GEDatosEnvioAsistenciaLocalDataSource(new BEDatosEnvioAsistenciaLocalDataSource(),
                        new BEDatosEnvioTipoNotaLocalDataSource()),
                new GEDatosEnvioAsistenciaRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosTareaRecursosRepository getBEDatosTareaRecursosRepositoryConRubros(){
        return  BEDatosTareaRecursosRepository.getInstance(
                new BEDatosTareaRecursosLocalDataSource(
                        getGEDatosRubroEvaluacionProcesoRepositoryInjectorSinTareas()),
                new BEDatosTareaRecursosRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    private static BEDatosTareaRecursosRepository getBEDatosTareaRecursosRepositorySinRubros(){
        return  BEDatosTareaRecursosRepository.getInstance(
                new BEDatosTareaRecursosLocalDataSource(null),
                new BEDatosTareaRecursosRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosSesionAprendizajeRepository getBEDatosSesionAprendizajeRepository(){
        return BEDatosSesionAprendizajeRepository.getInstance(new BEDatosSesionAprendizajeLocalDataSource(),
                new BEDatosSesionAprendizajeRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosCargaAcademicaRepository getBEDatosCargaAcademicaRepository(){
        return BEDatosCargaAcademicaRepository.getInstance(
                new BEDatosCargaAcademicaLocalDataSource(),
                new BEDatosCargaAcademicaRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static BEDatosEnvioAsistenciaRepository getBEDatosEnvioAsistenciaRepository(){
        return BEDatosEnvioAsistenciaRepository.getInstance(
                new BEDatosEnvioAsistenciaLocalDataSource(),
                new AsistenciaLoginRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosEnvioGrupoRepository getBEbeDatosEnvioGrupoRepository(){
        return BEDatosEnvioGrupoRepository.getInstance(
                new BEDatosEnvioGrupoLocalDataSource(),
                new BEDatosEnvioGrupoRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosEnvioHorarioRepository getBEDatosEnvioHorarioRepository(){
        return BEDatosEnvioHorarioRepository.getInstance(
                new BEDatosEnvioHorarioLocalDataSource(),
                new BEDatosEnvioHorarioRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosEnvioMensajeriaRepository getBEDatosEnvioMensajeriaRepository(){
        return BEDatosEnvioMensajeriaRepository.getInstance(
                new BEDatosEnvioMensajeriaLocalDataSource(),
                new BEDatosEnvioMensajeriaRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosEnvioTipoNotaRepository getBEDatosEnvioTipoNotaRepository(){
        return BEDatosEnvioTipoNotaRepository.getInstance(
                new BEDatosEnvioTipoNotaLocalDataSource(),
                new BEDatosEnvioTipoNotaRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosEvaluacionResultadoRepository getBEDatosEvaluacionResultadoRepository(){
        return BEDatosEvaluacionResultadoRepository.getInstance(
                new BEDatosEvaluacionResultadoLocalDataSource(),
                new BEDatosEvaluacionResultadoRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosRubroEvaluacionProcesoRepository getBEDatosRubroEvaluacionProcesoRepository(){
        return BEDatosRubroEvaluacionProcesoRepository.getInstance(
                new BEDatosRubroEvaluacionProcesoLocalDataSource(InjectorUtils.provideEvaluacionProcesoDao(), InjectorUtils.provideRubroProcesoDao()),
                new BEDatosRubroEvaluacionProcesoRemotaDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEDatosSilaboEventoEnvioRepository getBEDatosSilaboEventoEnvioRepository(){
        return BEDatosSilaboEventoEnvioRepository.getInstance(
                new BEDatosSilaboEventoEnvioLocalDataSource(),
                new BEDatosSilaboEventoEnvioRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static BEObtenerDatosLoginRepository getBEObtenerDatosLoginRepository(){
        return BEObtenerDatosLoginRepository.getInstance(
                new BEObtenerDatosLoginLocalDataSource(),
                new BEObtenerDatosLoginRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance()
        );
    }

    public static SEDatosCompletosLoginRepository getSEDatosCompletosLoginRepository(){
        return SEDatosCompletosLoginRepository.getInstance(
                new SEDatosCompletosLoginLocalDataSource(),
                new SEDatosCompletosLoginRemoteDataSource()
        );
    }

    public static ServiceRemoteDataRepository getServiceRemoteDataRepository(){
        return new ServiceRemoteDataRepositoryImpl(UtilServidor.getInstance());
    }

    public static ServiceLocalDataRepository getServiceLocalDataRepository(){
        return new ServiceLocalDataRepositoryImpl(InjectorUtils.provideSessionUserDao());
    }
}
