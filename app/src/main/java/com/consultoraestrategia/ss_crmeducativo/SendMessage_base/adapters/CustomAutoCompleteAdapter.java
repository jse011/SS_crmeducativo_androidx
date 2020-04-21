package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class CustomAutoCompleteAdapter extends ArrayAdapter<PersonaRelacionesUI> {

    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.txtNroTelefono)
    TextView txtNroTelefono;
    @BindView(R.id.txtNombreDestino)
    TextView txtNombreDestino;
    private LayoutInflater layoutInflater;
    List<PersonaRelacionesUI> personaRelacionesUIList;

    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return ((PersonaRelacionesUI) resultValue).getPersona().getNombreCompleto();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null) {
                ArrayList<PersonaRelacionesUI> suggestions = new ArrayList<PersonaRelacionesUI>();
                for (PersonaRelacionesUI customer : personaRelacionesUIList) {
                    // Note: change the "contains" to "startsWith" if you only want starting matches
                    if (customer.getPersona().getNombreCompleto().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(customer);
                    }
                }

                results.values = suggestions;
                results.count = suggestions.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            if (results != null && results.count > 0) {
                // we have filtered results
                addAll((ArrayList<PersonaRelacionesUI>) results.values);
            } else {
                // no filter, add entire original list back in
                addAll(personaRelacionesUIList);
            }
            notifyDataSetChanged();
        }
    };
    ImageLoader imageLoader;

    public CustomAutoCompleteAdapter(Context context, int textViewResourceId, List<PersonaRelacionesUI> customers) {
        super(context, textViewResourceId, customers);
        // copy all the customers into a master list
        personaRelacionesUIList = new ArrayList<PersonaRelacionesUI>(customers.size());
        personaRelacionesUIList.addAll(customers);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new GlideImageLoader(context);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_persona_destino, null);
        }

        ButterKnife.bind(this, view);

        PersonaRelacionesUI customer = getItem(position);
        txtNroTelefono.setText(customer.getPersona().getCelular() + "-" + customer.getParentezco());
        txtNombreDestino.setText(customer.getPersona().getNombreCompleto());
        imageLoader.loadWithCircular(imageView6, customer.getPersona().getFoto());
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Nullable
    @Override
    public PersonaRelacionesUI getItem(int position) {
        return super.getItem(position);
    }
}