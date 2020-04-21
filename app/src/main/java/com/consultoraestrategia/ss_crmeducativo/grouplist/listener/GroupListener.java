package com.consultoraestrategia.ss_crmeducativo.grouplist.listener;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 25/09/2017.
 */

public interface GroupListener {
    void onGroupSelected(Group group);
    void onOptionSelect(View view, Group group);
    void onCheckSelected(Group group);
    void onClickSaveGrupoSesion();


}
