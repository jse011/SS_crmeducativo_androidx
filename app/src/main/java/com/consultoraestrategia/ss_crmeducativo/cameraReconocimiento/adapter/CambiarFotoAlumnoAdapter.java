package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listener.RepositorioItemUpdateListener;

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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_camera_reconocimineto_foto_alumno, viewGroup, false));
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
        if (position != -1) {
            this.mFilteredList.set(position, value);
            notifyItemChanged(position);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    mFilteredList = personaUiList;
                } else {

                    List<PersonaUi> filteredList = new ArrayList<>();

                    for (PersonaUi personaUi : personaUiList) {
                        String nombres = TextUtils.isEmpty(personaUi.getNombres()) ? "" : personaUi.getNombres();
                        String apellidos = TextUtils.isEmpty(personaUi.getApellidos()) ? "" : personaUi.getApellidos();
                        if (nombres.toLowerCase().contains(charString) ||
                                apellidos.toLowerCase().contains(charString)) {
                            filteredList.add(personaUi);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                mFilteredList = (List<PersonaUi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imgProfile)
        CircleImageView imgProfile;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtSubtittle)
        TextView txtSubtittle;
        @BindView(R.id.txt_numeracion)
        TextView txtNumeracion;
        @BindView(R.id.root)
        LinearLayout root;
        @BindView(R.id.rc_pasos)
        RecyclerView rcPasos;
        @BindView(R.id.footer)
        FrameLayout footer;

        private RepositorioItemUpdateListener listener;
        private PersonaUi personaUi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            footer.setOnClickListener(this);
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


            txtNumeracion.setText(String.valueOf(personaUi.getNumeracion()));
            txtTitle.setText(personaUi.getNombres());
            txtSubtittle.setText(personaUi.getApellidos());
            rcPasos.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            PasosCambiarFotoAdapter pasosCambiarFotoAdapter = new PasosCambiarFotoAdapter();
            rcPasos.setAdapter(pasosCambiarFotoAdapter);
            pasosCambiarFotoAdapter.setLista(personaUi.getFotos());

        }

        @Override
        public void onClick(View v) {
            listener.subirFoto(personaUi);
        }


    }

}
