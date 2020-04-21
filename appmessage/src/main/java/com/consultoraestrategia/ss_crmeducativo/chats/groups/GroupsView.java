package com.consultoraestrategia.ss_crmeducativo.chats.groups;

import java.util.List;

public interface GroupsView {
    void setList(List<Object> objects);

    void showProgress();
    void hideProgress();
}
