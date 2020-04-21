package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.CreateRubBidBodyHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.CreateRubBidFirstHeaderHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder.CreateRubBidHeaderHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.Nivel;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.NivelPorRubro;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.RubroEvaluacionUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class CreateRubBidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_FIRSTHEADER = 1;
    public static final int TYPE_HEADER = 2;
    public static final int TYPE_FIRSTBODY = 3;
    public static final int TYPE_BODY = 4;

    private List<Object> items = new ArrayList<>();

    public CreateRubBidAdapter(List<Object> items) {
        this.items.addAll(items);
    }

    @Override
    public int getItemViewType(int position) {
        Object object = items.get(position);
        if (object instanceof NivelPorRubro) {
            return TYPE_BODY;
        }
        if (object instanceof RubroEvaluacionUi){
            return TYPE_FIRSTBODY;
        }

        if (object instanceof Nivel) {
            return TYPE_HEADER;
        }
        return TYPE_FIRSTHEADER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TYPE_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_rubbid_tablebody, parent, false);
                holder = new CreateRubBidBodyHolder(view);
                break;
                case TYPE_FIRSTBODY:
                View v3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_rubbid_tablebody, parent, false);
                holder = new CreateRubBidBodyHolder(v3);
                break;
            case TYPE_HEADER:
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_rubbid_tableheader, parent, false);
                holder = new CreateRubBidHeaderHolder(v1);
                break;
            default:
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_rubbid_tablefirstheader, parent, false);
                holder = new CreateRubBidFirstHeaderHolder(v2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object object = items.get(position);
        int itemViewType = holder.getItemViewType();
        switch (itemViewType) {
            case TYPE_BODY:
                ((CreateRubBidBodyHolder) holder).bind((NivelPorRubro) object);
                break;
            case TYPE_HEADER:
                ((CreateRubBidHeaderHolder) holder).bind((Nivel) object);
                break;
            case TYPE_FIRSTBODY:
                ((CreateRubBidBodyHolder) holder).bind((RubroEvaluacionUi) object);
                break;
            default:
                ((CreateRubBidFirstHeaderHolder) holder).bind((String) object);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
