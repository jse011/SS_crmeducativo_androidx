package com.consultoraestrategia.ss_crmeducativo.chats.contacs;

import java.util.List;

public interface ContacsView {
    void setListContacts(List<Object>listContacts);
    void  hideProgress();
    void showProgress();

    void searhContacts(String toString);

    void setOldListContacts();
}
