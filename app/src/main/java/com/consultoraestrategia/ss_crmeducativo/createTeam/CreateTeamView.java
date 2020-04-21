package com.consultoraestrategia.ss_crmeducativo.createTeam;

import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public interface CreateTeamView extends BaseView<CreateTeamPresenter> {

    void startSelectImageActivity();

    void addPersonList(List<Person> persons);

    void showPicture(Uri uri);

    void showName(String name);

    int getMemberCount();

    void showMemberRecycler();

    void hideMemberRecycler();

    void addMember(Person person);

    void removeMember(Person person);

    void updatePerson(Person person);

    void deletePerson(Person person);

    void shoMessage(int message);

    void showError(int error);

    void createTeam();

    void finishActivity();

    void devolverResultado(Team team);

    void siFiltraUsuarioDeseleccionados();

    void noFiltraUsuarioDeseleccionados();

    void cambiarColorFiltorDeseleccionado(int color);

    void showinfoUsuario(Person person);

    void hideMsjListEmpty();

    void showMsjListEmpty(String text);
    void showListSingleChooserEstilosAprendizaje(String dialogTitle, final List<Object> items, int positionSelected);
    void filter( DimensionUi dimensionUiseleted);
}
