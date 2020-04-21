package com.consultoraestrategia.ss_crmeducativo.createTeam.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createTeam.adapter.holder.PersonViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeam.listener.PersonListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final String TAG = PersonAdapter.class.getSimpleName();
    private List<Person> personList;
    private List<Person> mFilteredList;
    private Context context;
    private PersonListener listener;
    // slide-up animation
    Animation slideUp;
    Animation slideDown;
    List<Person>filtroOld;

    public PersonAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.mFilteredList = personList;
        this.context = context;
        this.filtroOld=new ArrayList<>();
        setupAnimations();
    }

    private void setupAnimations() {
        slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up);
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
    }

    public void setPersonListener(PersonListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Person person = mFilteredList.get(position);
        ((PersonViewHolder) holder).bind(person, context, listener, slideUp, slideDown);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public void filterEstiloAprendizaje( DimensionUi dimensionUiseleted){

        if(filtroOld.isEmpty())filtroOld.addAll(mFilteredList);
        this.mFilteredList.clear();
        for(Person person: filtroOld){
                DimensionObservadaUi dimensionObservadaUiMayor= null;

                for(DimensionObservadaUi dimensionObservadaUi: person.getDimensionObservadasUiList()){
                    if(dimensionObservadaUiMayor==null)
                        dimensionObservadaUiMayor=dimensionObservadaUi;

                    if(dimensionObservadaUiMayor.getArea()<dimensionObservadaUi.getArea())dimensionObservadaUiMayor=dimensionObservadaUi;
                }
                if(dimensionObservadaUiMayor!=null){

                    int idDimensionMayor= dimensionObservadaUiMayor.getDimensionUi().getId();
                  //  Log.d(TAG, "idDimensionMayor "+ idDimensionMayor);
                    if(idDimensionMayor==dimensionUiseleted.getId()){
                        mFilteredList.add(person);
                    }
                }

        }
            notifyDataSetChanged();
    }

    public void addPersonList(List<Person> personList) {
        for (Person person: personList){
            int position = this.personList.indexOf(person);
            if (position != -1) {
               // this.personList.set(position, person);
            }else {
                this.personList.add(person);
            }

            int positionFiltro = this.mFilteredList.indexOf(person);
            if (positionFiltro != -1) {
                // this.personList.set(position, person);
            }else {
                this.mFilteredList.add(person);
            }
        }

        notifyDataSetChanged();
    }


    public void updatePerson(Person person) {


        int positionFiltro = mFilteredList.indexOf(person);
        Log.d(TAG, "position Filtro: " + positionFiltro);
        if (positionFiltro != -1) {
            this.mFilteredList.set(positionFiltro, person);
            notifyItemChanged(positionFiltro);
        }

        int position = personList.indexOf(person);
        Log.d(TAG, "position: " + position);
        if (position != -1) {
            this.personList.set(position, person);
        }


    }

    public void deletePersona(Person person) {

        int positionFiltro = this.mFilteredList.indexOf(person);
        Log.d(TAG, "deletePersona positionFiltro: " + positionFiltro);
        if (positionFiltro != -1) {
            this.mFilteredList.remove(person);
            notifyItemRemoved(positionFiltro);
        }

        int position = this.personList.indexOf(person);
        Log.d(TAG, "deletePersona position: " + position);
        if (position != -1) {
            this.personList.remove(person);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                List<Person> filteredList = new ArrayList<>();
                if (charString.isEmpty()) {
                    filteredList = personList;
                } else {

                    for (Person person : personList) {
                        if (!person.isMember()) {
                            filteredList.add(person);
                        }
                    }

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<Person>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
