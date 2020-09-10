package com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter;

import android.text.TextUtils;
import android.widget.Filter;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;

import java.util.ArrayList;
import java.util.List;

class AlumnoFilter extends Filter {
    private AlumnoAdapter adapter;
    private List<AlumnoUi> filterList;

    public AlumnoFilter(AlumnoAdapter adapter, List<AlumnoUi> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            List<AlumnoUi> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                AlumnoUi alumnoUi = filterList.get(i);
                StringBuilder nombre = new StringBuilder((!TextUtils.isEmpty(alumnoUi.getNombres()) ? alumnoUi.getNombres() : ""));
                nombre.append(" ");
                nombre.append((!TextUtils.isEmpty(alumnoUi.getApellidos()) ? alumnoUi.getApellidos() : ""));
                //CHECK
                if(nombre.toString().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }


    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setList((ArrayList<AlumnoUi>) results.values, true);

    }
}
