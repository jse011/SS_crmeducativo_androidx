package com.consultoraestrategia.ss_crmeducativo.createTeamList;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public interface CreateTeamListPresenter extends BasePresenter<CreateTeamListView> {
    void setExtras(Bundle extras);

    void createTeamList(String nameList);

    void createTeam();

    void onSwipeTeam(Team team);

    void onRestoreTeam(Team team);

    void onTeamSelected(Team team);

    void onSaveTeam(Team team);

    void onBtnActualizarEquipo();

    void onSuccesUpdateGrupo();

    void onCantidadGruposChanged(CharSequence s);

    void onCreateOptionsMenu();

}
