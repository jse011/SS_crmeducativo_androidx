package com.consultoraestrategia.ss_crmeducativo.utils.firebase;

import com.google.firebase.firestore.ListenerRegistration;

public class ListenerFirebaseImpl implements ListenerFirebase {

    private ListenerRegistration listenerRegistration;

    public ListenerFirebaseImpl(ListenerRegistration listenerRegistration) {
        this.listenerRegistration = listenerRegistration;
    }

    @Override
    public void onStop() {
        listenerRegistration.remove();
    }
}
