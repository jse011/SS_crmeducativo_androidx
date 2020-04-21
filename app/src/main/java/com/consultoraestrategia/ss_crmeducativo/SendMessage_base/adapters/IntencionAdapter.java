package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;

import java.util.List;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class IntencionAdapter extends ArrayAdapter<IntencionUI> {


    public IntencionAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<IntencionUI> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intencion, parent, false);
        }
        IntencionUI item = getItem(position);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtNombreIntencion);
        txtTitle.setText(item.getNombre());

        return convertView;
    }
}
