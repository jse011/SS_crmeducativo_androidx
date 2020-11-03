package com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.PersonasDestinoUI;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.ComentariosDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.InteraccionTextual;
import com.consultoraestrategia.ss_crmeducativo.entities.InteraccionTextual_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ComentariosLocalDataSource implements ComentariosDataSource {
    private static String TAG = ComentariosLocalDataSource.class.getSimpleName();
    private PersonaDao personaDao;


    public ComentariosLocalDataSource(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    @Override
    public void getComentariosList(int sesionAprendizajeId, CallbackComentarios CallbackComentarios) {

        try {
            Set<Object> objectList = new LinkedHashSet<>();
            List<InteraccionTextual> comentarioList = SQLite.select()
                    .from(InteraccionTextual.class)
                    .where(InteraccionTextual_Table.entidad.eq(InteraccionTextual.ENTIDAD_SESSION))
                    .and(InteraccionTextual_Table.referenciaId.eq(sesionAprendizajeId))
                    .orderBy(InteraccionTextual_Table.fechaCreacion.asc())
                    .queryList();

            List<Persona> p = new ArrayList<>();

            if (comentarioList.isEmpty()) return;
            for (InteraccionTextual interaccionTextual : comentarioList) {

                Usuario u = SQLite.select().from(Usuario.class)
                        .where(Usuario_Table.usuarioId.eq(interaccionTextual.getUsuarioId()))
                        .querySingle();
                if (u != null) {

                    ComentarioUi comentarioUi = new ComentarioUi();
                    comentarioUi.setId(interaccionTextual.getKey());
                    comentarioUi.setContenido(interaccionTextual.getContenido());
                    comentarioUi.setFechaCreacion(interaccionTextual.getFechaCreacion());
                    comentarioUi.setUsuarioid(u.getUsuarioId());


                    TipoUi tipoUi = new TipoUi();
                    if (u.getPersonaId() == SessionUser.getCurrentUser().getPersonaId())
                        tipoUi.setTipo(TipoUi.Tipo.EMISOR);
                    else tipoUi.setTipo(TipoUi.Tipo.RECEPTOR);

                    comentarioUi.setTipoUi(tipoUi);


                    p = personaDao.getPersonas(interaccionTextual.getUsuarioId());


                    for (Persona per : p) {
                        comentarioUi.setUrl(per.getUrlPicture());
                        comentarioUi.setNombre(per.getNombreCompleto());
                    }

                    objectList.add(Utils.transformarFecha_a_FechaAsistenciaSinHora(comentarioUi.getFechaCreacion()));
                    objectList.add(comentarioUi);
                }

            }
            List<Object> comentarioUisit = new ArrayList<>(objectList);
            CallbackComentarios.onListeActividades(comentarioUisit, 1);
        } catch (Exception e) {
            e.printStackTrace();
            CallbackComentarios.onListeActividades(new ArrayList<Object>(), 1);
        }

    }

    @Override
    public void addComentario(int sesionAprendizajeId, String contenido, final CallbackComentarios callbackComentarios) {

            InteraccionTextual i = new InteraccionTextual();
            i.setContenido(contenido);
            i.setEntidad(InteraccionTextual.ENTIDAD_SESSION);
            i.setReferenciaId(sesionAprendizajeId);
            i.setUsuarioId(SessionUser.getCurrentUser().getUserId());
            i.setSyncFlag(BaseRelEntity.FLAG_ADDED);
            i.setFechaCreacion(BaseRelEntity.getTime());
            i.setFechaAccion(BaseRelEntity.getTime());
            i.setInteraccionTextualId(i.getKey());
            i.save();

            getComentariosList(sesionAprendizajeId, callbackComentarios);

    }

    public List<Long> getList(List<Long> fechalist) {
        Set<Long> hset = new HashSet<>();
        hset.addAll(fechalist);
        List<Long> list = new ArrayList<>(hset);
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}