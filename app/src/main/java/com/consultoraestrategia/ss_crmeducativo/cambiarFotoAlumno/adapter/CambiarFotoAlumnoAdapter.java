package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.adapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.listener.RepositorioItemUpdateListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CambiarFotoAlumnoAdapter extends RecyclerView.Adapter<CambiarFotoAlumnoAdapter.ViewHolder> implements Filterable {

    private List<PersonaUi> personaUiList = new ArrayList<>();
    private List<PersonaUi> mFilteredList = personaUiList;

    private RepositorioItemUpdateListener listener;

    public CambiarFotoAlumnoAdapter(RepositorioItemUpdateListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cambiar_foto_alumno, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(mFilteredList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public void setList(List<PersonaUi> personaUiList) {
        this.personaUiList.clear();
        this.personaUiList.addAll(personaUiList);
        Log.d(getClass().getSimpleName(), "personaUiList " + personaUiList.size());
        notifyDataSetChanged();
    }

    public void update(PersonaUi value) {
        int position = mFilteredList.indexOf(value);
        if(position!=-1){
            this.mFilteredList.set(position, value);
            notifyItemChanged(position);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = personaUiList;
                } else {

                    List<PersonaUi> filteredList = new ArrayList<>();

                    for (PersonaUi personaUi : personaUiList) {
                        String nombres = TextUtils.isEmpty(personaUi.getNombres())?"":personaUi.getNombres();
                        String apellidos = TextUtils.isEmpty(personaUi.getApellidos())?"":personaUi.getApellidos();
                        if (nombres.toLowerCase().contains(charString)||
                                apellidos.toLowerCase().contains(charString)) {
                            filteredList.add(personaUi);
                        }
                    }

                    mFilteredList = filteredList;
                }

                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, Filter.FilterResults
                    filterResults) {
                mFilteredList = (List<PersonaUi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.progress_succes2)
        ProgressBar progressSucces2;
        @BindView(R.id.imgProfile)
        CircleImageView imgProfile;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtSubtittle)
        TextView txtSubtittle;
        @BindView(R.id.txt_numeracion)
        TextView txtNumeracion;
        @BindView(R.id.img_person_preview)
        ImageView imgPersonPreview;
        @BindView(R.id.btn_add_evidencias)
        Button btnAddEvidencias;
        @BindView(R.id.root)
        ConstraintLayout root;
        private RepositorioItemUpdateListener listener;
        private PersonaUi personaUi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnAddEvidencias.setOnClickListener(this);
            imgPersonPreview.setOnClickListener(this);
        }

        public void bind(PersonaUi personaUi, RepositorioItemUpdateListener listener) {
            this.personaUi = personaUi;
            this.listener = listener;

            RequestOptions options = new RequestOptions()
                    .error(R.drawable.ic_error_outline_black)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);


            Glide.with(itemView.getContext())
                    .load(personaUi.getFoto())
                    .apply(options)
                    .into(imgProfile);

            if(!TextUtils.isEmpty(personaUi.getPath())){
                Glide.with(itemView.getContext())
                        .load(new File(personaUi.getPath()))
                        .apply(options)
                        .into(imgPersonPreview);
            } else {
                Glide.with(itemView.getContext())
                        .load(R.drawable.ic_camera)
                        .apply(options)
                        .into(imgPersonPreview);
            }

            txtNumeracion.setText(String.valueOf(personaUi.getNumeracion()));
            txtTitle.setText(personaUi.getNombres());
            txtSubtittle.setText(personaUi.getApellidos());

            if(personaUi.isWorking()){
                progressSucces2.setVisibility(View.VISIBLE);
            }else {
                progressSucces2.setVisibility(View.GONE);
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_person_preview:
                    listener.btnAddFoto(personaUi);
                    break;
                case R.id.btn_add_evidencias:
                    listener.subirFoto(personaUi);
                    break;
            }
        }





    }

}
