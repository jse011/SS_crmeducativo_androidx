package com.consultoraestrategia.ss_crmeducativo.createTeam.listener;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;

/**
 * Created by @stevecampos on 19/09/2017.
 */

public interface PersonListener {
    void onPersonSeleteced(Person person);
    void onPersonUnselected(Person person);

    void onClickInfoPersona(Person person);
}
