package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.CampoTematicoAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class ItemCampotematicoViewHolder extends RecyclerView.ViewHolder {



    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.espacio)
    View espacio;
    @BindView(R.id.text_titulo)
    TextView textTitulo;
    @BindView(R.id.campoHijo)
    RecyclerView campoHijo;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    private boolean verMas=true;

    public ItemCampotematicoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(CampotematicoUi campotematicoUi) {
        if (campotematicoUi.getTipo()==CampotematicoUi.Tipo.PARENT){
            selectorHijo(campotematicoUi.getCampotematicoUiListHijo());
            imageView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(campotematicoUi.getDescripcion()))
            textTitulo.setText(campotematicoUi.getDescripcion());
            else textTitulo.setText("Sin Asignacion de Nombre");
            campoHijo.setVisibility(View.VISIBLE);
            espacio.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
            textTitulo.setVisibility(View.GONE);
            campoHijo.setVisibility(View.GONE);
            espacio.setVisibility(View.GONE);
        }

    }

    private void selectorHijo(List<CampotematicoUi> campotematico) {
        CampoTematicoAdapter campoTematicoAdapter = new CampoTematicoAdapter(campotematico);
        campoHijo.setAdapter(campoTematicoAdapter);
        campoHijo.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        campoHijo.setHasFixedSize(true);
        campoHijo.setVisibility(View.VISIBLE);
        campoHijo.setEnabled(true);
    }

    @OnClick({R.id.imageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                onVerMas();
                break;
        }
    }

    private void onVerMas() {
        if(verMas){
            verMas = false;
            campoHijo.setVisibility(View.GONE);
            Drawable mDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.moree);
            imageView.setBackground(mDrawable);
        }else {
            verMas = true;
            campoHijo.setVisibility(View.VISIBLE);
            Drawable mDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.less);
            imageView.setBackground(mDrawable);
        }
    }


}
