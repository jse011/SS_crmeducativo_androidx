package com.consultoraestrategia.ss_crmeducativo.grouplist.entities;

import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 15/09/2017.
 */
@Parcel
public class Group {
    public String id;
    public String cargaCursoId;
    public String title;
    public String subtitle;
    public String urlIcon;
    public int tipoId;
    public List<Team> teams;
    public boolean finished;
    public String color1;
    public CursoGrupoUi cursoGrupoUi;
    public boolean Checked;

    public Group() {
    }

    public Group(String id, String cargaCursoId, String title, String subtitle, String urlIcon, int tipoId, List<Team> teams, boolean finished) {
        this.id = id;
        this.cargaCursoId = cargaCursoId;
        this.title = title;
        this.subtitle = subtitle;
        this.urlIcon = urlIcon;
        this.tipoId = tipoId;
        this.teams = teams;
        this.finished = finished;
    }

    public Group(String id, String title, String subtitle, String urlIcon, List<Team> teams) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.urlIcon = urlIcon;
        this.teams = teams;
    }

    public Group(String id, String cargaCursoId, String title, String subtitle, String urlIcon, List<Team> teams, boolean finished) {
        this.id = id;
        this.cargaCursoId = cargaCursoId;
        this.title = title;
        this.subtitle = subtitle;
        this.urlIcon = urlIcon;
        this.teams = teams;
        this.finished = finished;
    }


    public Group(String cargaCursoId, boolean finished) {
        this.cargaCursoId = cargaCursoId;
        this.finished = finished;
    }

    public String getColor1() {
        return color1;
    }

    public String setColor1(String color1) {
        this.color1 = color1;
        return color1;
    }

    public String getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(String cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id != null ? id.equals(group.id) : group.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cursoGrupoUi != null ? cursoGrupoUi.hashCode() : 0);
        return result;
    }

    public static Group transform(GrupoEquipoC grupoDeEquipo) {
        if (grupoDeEquipo == null) return null;
        Group group = new Group(
                grupoDeEquipo.getKey(),
                String.valueOf(grupoDeEquipo.getCargaCursoId()),
                grupoDeEquipo.getNombre(),
                "",
                "",
                new ArrayList<Team>(),
                grupoDeEquipo.isFinished()
        );
        List<EquipoC> equipos = grupoDeEquipo.getEquipos();
        List<Team> teamList = Team.transform(equipos);
        for (Team team: teamList){
            team.setGroup(group);
        }
        group.setTeams(teamList);
        return group;
    }

    public static List<Group> transform(List<GrupoEquipoC> grupoDeEquipos) {
        List<Group> groupList = new ArrayList<>();
        for (GrupoEquipoC grupoEquipoC :
                grupoDeEquipos) {
            groupList.add(transform(grupoEquipoC));
        }
        return groupList;
    }

    public void setCursoGrupoUi(CursoGrupoUi cursoGrupoUi) {
        this.cursoGrupoUi = cursoGrupoUi;
    }

    public CursoGrupoUi getCursoGrupoUi() {
        return cursoGrupoUi;
    }
}
