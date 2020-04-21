package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

/**
 * Created by SCIEV on 2/10/2017.
 */

public class IntervalosAdapter  {
    /*private List<IntervaloUi> intervalos;
    private RecyclerView recyclerView;

    public IntervalosAdapter(List<IntervaloUi> intervalos) {
        this.intervalos = intervalos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_persona, viewGroup, false);
        return  new IntervalosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewGroup, int position) {
        IntervaloUi intervalo = intervalos.get(position);
        IntervalosViewHolder personaViewHolder = (IntervalosViewHolder)viewGroup;
        personaViewHolder.bind(intervalo);
    }

    @Override
    public int getItemCount() {
        return intervalos.size();
    }

    public void addPersona(IntervaloUi intervalo){
        this.intervalos.add(intervalo);
        notifyItemInserted(getItemCount()-1);
        recyclerView.scrollToPosition(getItemCount()-1);
    }

    public void updatepersona(IntervaloUi intervalo){
        int position =  this.intervalos.indexOf(intervalo);
        if(position != -1){
            this.intervalos.set(position,intervalo);
            notifyItemChanged(position);
        }
    }

    public void deletePersona(IntervaloUi intervalo){
        int position =  this.intervalos.indexOf(intervalo);
        if(position != -1){
            this.intervalos.remove(intervalo);
            notifyItemRemoved(position);
        }

    }

    public void setintervalos(List<IntervaloUi> intervalo){
        this.intervalos.clear();
        this.intervalos.addAll(intervalo);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }*/
}
