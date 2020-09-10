package com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;

import java.util.ArrayList;
import java.util.List;

class AlumnoDiffCallback extends DiffUtil.Callback {
    List<AlumnoUi> oldPersons, newPersons;

    public AlumnoDiffCallback(List<AlumnoUi> oldPersons, List<AlumnoUi> newPersons) {
        this.oldPersons = oldPersons;
        this.newPersons = newPersons;
    }

    @Override
    public int getOldListSize() {
        return oldPersons.size();
    }

    @Override
    public int getNewListSize() {
        return newPersons.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).getId() == newPersons.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersons.get(oldItemPosition).equals(newPersons.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
