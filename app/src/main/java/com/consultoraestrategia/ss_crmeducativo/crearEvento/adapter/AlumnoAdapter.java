package com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.ViewHolder> implements Filterable {

    private List<AlumnoUi> alumnoUiList = new ArrayList<>(), filterList =new ArrayList<>();
    private Listener listener;
    private AlumnoFilter filter;

    public AlumnoAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_alumno_crear_evento, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(alumnoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return alumnoUiList.size();
    }

    public void setList(List<AlumnoUi> alumnoUiList, boolean filter) {

        this.alumnoUiList.clear();
        this.alumnoUiList.addAll(alumnoUiList);
        notifyDataSetChanged();

        if(!filter){
            filterList.clear();
            filterList.addAll(alumnoUiList);
        }

    }

    public List<AlumnoUi> getAlumnoUiList() {
        return alumnoUiList;
    }

    public void update(AlumnoUi alumnoUi) {
        int position = alumnoUiList.indexOf(alumnoUi);
        if(position!=-1){
            alumnoUiList.set(position, alumnoUi);
            notifyItemChanged(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
        @BindView(R.id.img_foto)
        ImageView imgFoto;
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.check_padres)
        CheckBox checkPadres;
        @BindView(R.id.check_alumno)
        CheckBox checkAlumno;
        @BindView(R.id.check_select)
        CheckBox checkSelect;
        private AlumnoUi alumnoUi;
        private Listener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(AlumnoUi alumnoUi, Listener listener) {
            this.listener = listener;
            this.alumnoUi = alumnoUi;
            Glide.with(imgFoto)
                    .load(alumnoUi.getFoto())
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgFoto);

            String nombre = alumnoUi.getNombres() + " " + alumnoUi.getApellidos();
            txtNombre.setText(nombre);

            int states[][] = {{android.R.attr.state_checked}, {}};
            int colors[] = {Color.parseColor("#0A2D5C"), Color.parseColor("#0A2D5C")};
            CompoundButtonCompat.setButtonTintList(checkSelect, new ColorStateList(states, colors));
            CompoundButtonCompat.setButtonTintList(checkPadres, new ColorStateList(states, colors));
            CompoundButtonCompat.setButtonTintList(checkAlumno, new ColorStateList(states, colors));

            checkPadres.setOnCheckedChangeListener(null);
            checkAlumno.setOnCheckedChangeListener(null);

            checkSelect.setChecked(alumnoUi.isEnviarAlumno()||alumnoUi.isEnviarPadre());
            checkAlumno.setChecked(alumnoUi.isEnviarAlumno());
            checkPadres.setChecked(alumnoUi.isEnviarPadre());

            checkPadres.setOnCheckedChangeListener(this);
            checkAlumno.setOnCheckedChangeListener(this);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.getId()==R.id.check_alumno){
                listener.onChangeAlumno(alumnoUi);
            }else if(buttonView.getId()==R.id.check_padres){
                listener.onChangePadres(alumnoUi);
            }
        }

        @Override
        public void onClick(View v) {
            listener.onClickItem(alumnoUi);
        }
    }

    public interface Listener {
        void onClickItem(AlumnoUi alumnoUi);

        void onChangePadres(AlumnoUi alumnoUi);

        void onChangeAlumno(AlumnoUi alumnoUi);
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new AlumnoFilter(this, filterList);
        }

        return filter;
    }
}
