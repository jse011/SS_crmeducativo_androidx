package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PrecisionUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrecisionAdapter extends RecyclerView.Adapter<PrecisionAdapter.PresicionHolder> {

    private List<PrecisionUi> presicionUiList;

    public PrecisionAdapter(List<PrecisionUi> presicionUiList) {
        this.presicionUiList = presicionUiList;
    }

    @NonNull
    @Override
    public PresicionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crear_rubrica_nota_presicion, parent, false);
        return new PresicionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresicionHolder holder, int position) {
        holder.bind(presicionUiList.get(position));
    }

    @Override
    public int getItemCount() {
        return presicionUiList==null?0:presicionUiList.size();
    }

    public void setList(List<PrecisionUi> cursoUiList) {
        this.presicionUiList.clear();
        this.presicionUiList.addAll(cursoUiList);
        notifyDataSetChanged();
    }

    public class PresicionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nav_bar_letra_profile)
        TextView navBarLetraProfile;

        PresicionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(PrecisionUi presicionUi) {
            try {
                Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_circle_precision_rubrica);
                circle.mutate().setColorFilter(Color.parseColor(presicionUi.getContornoColor()), PorterDuff.Mode.SRC_ATOP);
                /*ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                circle.setColorFilter(filter);*/
                navBarLetraProfile.setBackground(circle);


            }catch (Exception e){e.getStackTrace();}

            navBarLetraProfile.setText(presicionUi.getContenido());

        }
    }

}
