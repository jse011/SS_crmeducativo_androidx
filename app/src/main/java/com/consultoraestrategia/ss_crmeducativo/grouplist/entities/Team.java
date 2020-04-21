package com.consultoraestrategia.ss_crmeducativo.grouplist.entities;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 15/09/2017.
 */
@Parcel
public class Team {
    public String id;
    public String groupId;//FK
    public String name;
    public String etiqueta;
    public int orden;
    public String urlImage;
    public List<Person> personList;
    public Group group;
    public int colorFondo;
    public int colorTexto;
    public Team() {
    }

    public Team(String groupId, String name, int orden, String urlImage, List<Person> personList) {
        this.groupId = groupId;
        this.name = name;
        this.orden = orden;
        this.urlImage = urlImage;
        this.personList = personList;
    }

    public Team(String id, String groupId, String name, int orden, String urlImage, List<Person> personList) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.orden = orden;
        this.urlImage = urlImage;
        this.personList = personList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


    public String getMembersName() {
        String membersName = "";
        if (personList == null || personList.isEmpty()) {
            return null;
        }

        int size = personList.size();
        for (int i = 0; i < size; i++) {
            Person person = personList.get(i);
            if (person != null) {
                membersName += person.getName();
                if (i < size - 1) {
                    membersName += ", ";
                }
            }
        }
        return membersName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return id != null ? id.equals(team.id) : team.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "groupId: " + groupId + "\n" +
                "name: " + name + "\n" +
                "urlImage: " + urlImage + "\n" +
                "orden: " + orden + "\n";
    }

    public static Team transform(EquipoC equipo) {
        if (equipo == null) return null;
        List<EquipoIntegranteC> integrantes = equipo.getIntegrantes();
        List<Person> personList = Person.transform(integrantes);
        return new Team(
                equipo.getKey(),
                equipo.getGrupoEquipoId(),
                equipo.getNombre(),
                equipo.getOrden(),
                equipo.getUrlImagen(),
                personList
        );
    }

    public static List<Team> transform(List<EquipoC> equipos) {
        List<Team> teamList = new ArrayList<>();
        for (EquipoC equipo :
                equipos) {
            Team team = transform(equipo);
            teamList.add(team);
        }
        return teamList;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(int colorFondo) {
        this.colorFondo = colorFondo;
    }

    public int getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(int colorTexto) {
        this.colorTexto = colorTexto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
