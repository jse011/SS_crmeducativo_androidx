package com.consultoraestrategia.ss_crmeducativo.createTeam;

import android.content.Intent;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.User;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public interface CreateTeamPresenter extends BasePresenter<CreateTeamView> {

    void setExtras(Bundle extras);

    void onPersonSelected(Person person);

    void onPersonUnselected(Person person);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void createTeam(String name);

    void onClickFiltro();

    void onClickInfoPersona(Person person);
    void onItemSelectedEstiloAprendizaje(Object singleItem, int selectedPosition);

    void showDialogEstilosAprendizaje();
}
