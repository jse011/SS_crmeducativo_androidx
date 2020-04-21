package com.consultoraestrategia.ss_crmeducativo.createTeamList;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

import java.util.List;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public interface CreateTeamListView extends BaseView<CreateTeamListPresenter> {
    void launchCreateTeamActivity(String cargaCursoId, String grupoEquipoId, int orden, Team team, Group group, int entidadId, int georeferenciaId);

    void showTeamListName(String name);

    void showTeams(List<Team> teamList);

    void removeTeam(Team team);

    void updateTeam(Team team);

    void showTeamRemoveMessage(String message, Team team);

    int getTeamListSize();

    List<Team> getTeamList();

    void sync(int programaEducativoId);

    void createTeamList();

    void showMessage(int message);

    void addTeam(Team team);

    void showError(int error);

    void showMsgActualizacion();

    void hideMsgActualizacion();

    void showActividadImportacion(BEVariables beVariables);

    void showFabCrearRubro();

    void hideFabCrearRubro();

    void showImputCrearRubroDinamico();

    void hideImputCrearRubroDinamico();

    void clearFocus();

    void showcontenedorconfig();

    void hidecontenedorconfig();

    void onBackPress();
    void hideDinamicoAndEstatico();

    void showTextEmptyTeamsEstiloApred(List<Team> teams);

    void changeMenuItemCrear();

    void changeMenuItemEditar();

    void showTextEmptyTeamsDimamico(List<Team> teams);
}
