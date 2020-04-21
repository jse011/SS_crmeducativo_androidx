package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.CanalDestinoEstadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Intencion;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeIntencionItemC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.CreateMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetIntencionListCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetPersonasRelacionadasCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.CreateMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class SendMessageBaseLocalDataSource implements SendMessageDataSource {

    private static final String TAG = SendMessageBaseLocalDataSource.class.getSimpleName();
    private final SessionUser currentUser;

    public SendMessageBaseLocalDataSource() {
        currentUser = SessionUser.getCurrentUser();
    }

    @Override
    public void getPersonasRelacionadasList(GetPersonasRelacionasUseCase.RequestValues requestValues,
                                            GetPersonasRelacionadasCallback callback) {
        generatePersonasDestinoOfList(requestValues.getPersonaList(), callback);
    }

    private List<PersonaRelacionesUI> getDatosPadres(final Persona alumno) {
        final List<PersonaRelacionesUI> personaRelacionesList = new ArrayList<>();

        List<Relaciones> relacionesList = SQLite.select()
                .from(Relaciones.class)
                .where(Relaciones_Table.personaPrincipalId.is(alumno.getPersonaId()))
                .queryList();

        for (final Relaciones relaciones : relacionesList) {
            final Persona padrePersona = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.is(relaciones.getPersonaVinculadaId()))
                    .querySingle();
            Usuario PadreUsuario = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.personaId.is(padrePersona.getPersonaId()))
                    .querySingle();
            if (relaciones.getTipoId() == 181 || relaciones.getTipoId() == 182 || relaciones.isActivo()) {
                //if (PadreUsuario != null) {
                    final Tipos tiposRelacion = SQLite.select()
                            .from(Tipos.class)
                            .where(Tipos_Table.tipoId.is(relaciones.getTipoId()))
                            .querySingle();
                    String apoderado = "";
                    if (relaciones.isActivo()) apoderado = "/ Apoderado";
                    final String finalApoderado = apoderado;
                    personaRelacionesList.add(new PersonaRelacionesUI(
                            padrePersona,
                            tiposRelacion.getNombre() + " " + finalApoderado + " de " + alumno.getFirstName(),
                            relaciones.getTipoId(),
                            relaciones.isActivo(),
                            null,
                            alumno)
                    );
                //}
            }
        }
        return personaRelacionesList;
    }

    private Persona personaUsuarioOrigen;

    public void crearMensaje(CreateMessageUseCase.RequestValues requestValues, CreateMessageCallback callback) {
        if (currentUser != null) {
            Usuario usuarioOrigen = SQLite.select()
                    .from(Usuario.class)
                    .where(Usuario_Table.usuario.is(currentUser.getUsername()))
                    .querySingle();
            personaUsuarioOrigen = Persona.getPersona(currentUser.getPersonaId());
            UsuarioRolGeoreferencia personaGeoreferenciaOrigen = SQLite.select()
                    .from(UsuarioRolGeoreferencia.class)
                    .where(UsuarioRolGeoreferencia_Table.usuarioId.is(usuarioOrigen.getUsuarioId()))
                    .querySingle();
            String nombrePeriodo = "";
            for (PersonaRelacionesUI personasDestinoUI : requestValues.getPersonaDestinoMessage()) {
                Usuario usuarioDestino = SQLite.select()
                        .from(Usuario.class)
                        .where(Usuario_Table.personaId.is(personasDestinoUI.getPersona().getPersonaId()))
                        .querySingle();
                if (usuarioDestino != null) {
                    String destinoNumero = personasDestinoUI.getPersona().getCelular();
                    if (!destinoNumero.equals("")) {

                        String fechaHoy = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                        createMensajeCompletToSend(requestValues, personasDestinoUI.getPersonaPrincipal().getPersonaId(), nombrePeriodo, usuarioDestino, callback, destinoNumero, fechaHoy);
                    } else {
                        Log.d(TAG, "Error Apoderado no tiene celular Registrado");
                    }
                } else {
                    Log.d(TAG, "No tiene Usuario en el sistema");
                    callback.onError("No tiene Usuario en el Sistema");
                }
            }

        }
    }

    @Override
    public void getIntencionList(GetIntencionListUseCase.RequestValues
                                         requestValues, GetIntencionListCallback callback) {
        List<IntencionUI> intencionUIList = new ArrayList<>();
        List<Intencion> intencionList = SQLite.select()
                .from(Intencion.class)
                .queryList();
        if (!intencionList.isEmpty()) {
            for (Intencion intencion : intencionList) {
                intencionUIList.add(new IntencionUI(intencion.getIntencionId(), intencion.getNombre()));
            }
            callback.onListLoaded(intencionUIList);
        } else {
            callback.onError("Error ");
            Log.d(TAG, "getMesnajesPredternidadosUIList: No hay Lista Intenciones");
        }

    }

    @Override
    public void createMessage(CreateMessageUseCase.RequestValues
                                      requestValues, CreateMessageCallback createMessageCallback) {
        crearMensaje(requestValues, createMessageCallback);
    }

    private void generatePersonasDestinoOfList(List<Persona> personaList, GetPersonasRelacionadasCallback callback) {
        final List<PersonaRelacionesUI> personasRelacionesListPrimary = new ArrayList<>();
        final List<PersonaRelacionesUI> personaRelacionesList = new ArrayList<>();
        for (final Persona personaAlumno : personaList) {
            int idPersonaAlumno = 0;
            if (personaAlumno != null) {
                idPersonaAlumno = personaAlumno.getPersonaId();
//                Usuario usuarioDestino = SQLite.select()
//                        .from(Usuario.class)
//                        .where(Usuario_Table.personaId.is(idPersonaAlumno))
//                        .querySingle();
//                if (usuarioDestino != null) {
                PersonaRelacionesUI personaRelacionesPrimary = new PersonaRelacionesUI();
                personaRelacionesPrimary.setPersona(personaAlumno);
                List<PersonaRelacionesUI> personaRelacionesUIListPadres = getDatosPadres(personaAlumno);
                if (!personaRelacionesUIListPadres.isEmpty()) {
                    personaRelacionesPrimary.setPersonasRelacionadas(personaRelacionesUIListPadres);
                    personaRelacionesList.addAll(personaRelacionesUIListPadres);
                } else {
                    personaRelacionesPrimary.setPersonasRelacionadas(new ArrayList<PersonaRelacionesUI>());
                }
                personaRelacionesPrimary.setParentezco("Alumno");
                personaRelacionesPrimary.setApoderado(false);
                personaRelacionesPrimary.setPersonaPrincipal(personaAlumno);
                personasRelacionesListPrimary.add(personaRelacionesPrimary);
//                }
            }
        }
        if (personasRelacionesListPrimary.isEmpty()) {
            callback.onError("Docente sin Mensajeria disponible");
        } else {
            callback.onPersonasRelacionadaListLoaded(personasRelacionesListPrimary);
        }
    }

    private void createMensajeCompletToSend(CreateMessageUseCase.RequestValues requestValues, int personaIdAlumno, String nombrePeriodo, Usuario usuarioDestino, CreateMessageCallback callback, String destinoNumero, String fechaHoy) {
        {
            if (createMensajeObject(requestValues, fechaHoy, personaIdAlumno)) {
                final MensajeC mensajeSaved = SQLite.select()
                        .from(MensajeC.class)
                        .where(MensajeC_Table.cargaCursoId.is(requestValues.getIdCargaCurso()))
                        .and(MensajeC_Table.alumnoId.is(personaIdAlumno))
                        .and(MensajeC_Table.contenido.is(requestValues.getContenido()))
                        .and(MensajeC_Table.asunto.is(requestValues.getAsunto()))
                        .querySingle();

                if (createMensajeUsuarioObject(usuarioDestino, mensajeSaved)) {
                    MensajeUsuarioC mensajeUsuarioSaved = SQLite.select()
                            .from(MensajeUsuarioC.class)
                            .where(MensajeUsuarioC_Table.mensajeId.is(mensajeSaved.getKey()))
                            .and(MensajeUsuarioC_Table.usuarioDestinoId.is(usuarioDestino.getUsuarioId()))
                            .querySingle();

                    CanalDestinoEstadoC canalDestinoEstadoCreated = new CanalDestinoEstadoC();
                    canalDestinoEstadoCreated.setCanalComId(1);
                    canalDestinoEstadoCreated.setMensajeUsuarioId(mensajeUsuarioSaved.getKey());
                    canalDestinoEstadoCreated.setEstadoId(0);
                    canalDestinoEstadoCreated.save();

                    MensajeIntencionItemC intencionItemCreated = new MensajeIntencionItemC();
                    intencionItemCreated.setIntencionItemId(mensajeSaved.getIntencionId());
                    intencionItemCreated.setMensajeId(mensajeSaved.getKey());
                    intencionItemCreated.setEstadoExportado(0);
                    intencionItemCreated.save();

                    if (personaUsuarioOrigen.getCelular().equals("")) {
                        callback.onError("No tiene Usuario en el Sistema");
                    } else {
                        if (requestValues.getContenido() != null) {
                            callback.onCreateMessageLoaded(true);
                        }
                    }
                }
            }

        }

    }

    private boolean createMensajeObject(CreateMessageUseCase.RequestValues requestValues, String fechaHoy, int personaIdAlumno) {

        long dateTime = new Date().getTime();
        final MensajeC mensajeCreated = new MensajeC();
        mensajeCreated.setUsuarioOrigenId(currentUser.getUserId());
        mensajeCreated.setCategoriaId(443);
        mensajeCreated.setCanalComId(1);
        mensajeCreated.setCargaCursoId(requestValues.getIdCargaCurso());
        mensajeCreated.setAlumnoId(personaIdAlumno);
        mensajeCreated.setEntidadId(1);
        mensajeCreated.setGeoreferenciaId(1);
        mensajeCreated.setOrganigramaId(58);
        mensajeCreated.setIntencionId(1);
        mensajeCreated.setAsunto(requestValues.getAsunto());
        mensajeCreated.setContenido(requestValues.getContenido());
        mensajeCreated.setAsistenciaAlumnoId(0);
        mensajeCreated.setEvaluacionResultadoId(0);
        mensajeCreated.setDestacado(true);
        mensajeCreated.setImportante(true);
        mensajeCreated.setFechaCreacion(dateTime);
        mensajeCreated.setFechaEnvio(fechaHoy);
        mensajeCreated.setEstadoExportado(0);
        return mensajeCreated.save();
    }

    private boolean createMensajeUsuarioObject(Usuario usuarioDestino, MensajeC mensajeSaved) {
        final MensajeUsuarioC mensajeUsuarioCreated = new MensajeUsuarioC();
        mensajeUsuarioCreated.setUsuarioDestinoId(usuarioDestino.getUsuarioId());
        mensajeUsuarioCreated.setMensajeId(mensajeSaved.getKey());
        mensajeUsuarioCreated.setListaUsuarioId(1);
        mensajeUsuarioCreated.setEstadoMensajeId(0);
        mensajeUsuarioCreated.setEstadoRespuestaId(0);
        mensajeUsuarioCreated.setFechaRecibido("");
        mensajeUsuarioCreated.setFechaRespuesta("");
        mensajeUsuarioCreated.setFechaVisto("");
        mensajeUsuarioCreated.setImportante(true);
        mensajeUsuarioCreated.setDestacado(true);
        mensajeUsuarioCreated.setSyncFlag(MensajeUsuarioC.FLAG_ADDED);
        return mensajeUsuarioCreated.save();
    }
}


