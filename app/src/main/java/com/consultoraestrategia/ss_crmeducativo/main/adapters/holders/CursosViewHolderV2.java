package com.consultoraestrategia.ss_crmeducativo.main.adapters.holders;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.CursosListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 09/10/2017.
 */

public class CursosViewHolderV2 extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.fondo)
    ImageView fondo;
    @BindView(R.id.txtNombreCurso)
    TextView txtNombreCurso;
    @BindView(R.id.txtPeriodo)
    TextView txtPeriodo;
    @BindView(R.id.txtHorario)
    TextView txtHorario;
    @BindView(R.id.txtSalon)
    TextView txtSalon;
    @BindView(R.id.txtAlumno)
    TextView txtAlumno;

    @BindView(R.id.txtNombreDocente)
    TextView txtNombreDocente;
    @BindView(R.id.imgAccionClase)
    View imgAccionClase;
    @BindView(R.id.imgDocente)
    ImageView imgDocente;
    @BindView(R.id.contItemView)
    ConstraintLayout contItemView;
    @BindView(R.id.cardviewCurso)
    CardView cardviewCurso;
    @BindView(R.id.progressCurso)
    ProgressBar progressCurso;
    @BindView(R.id.img_grupo)
    ImageView imgGrupo;
    @BindView(R.id.img_movil)
    ImageView imgMovil;
    @BindView(R.id.img_resultado)
    ImageView imgResultado;
    @BindView(R.id.img_eva)
    ImageView imgEva;
    @BindView(R.id.img_agenda)
    ImageView imgAgenda;
    public CursosViewHolderV2(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    private CursosUI cursosUiRecurso;
    private CursosListener listener;

    public void bind(final CursosUI cursosUI, final CursosListener listener, ImageLoader imageLoader) {
        this.cursosUiRecurso = cursosUI;
        this.listener = listener;
        txtNombreCurso.setText(cursosUI.getNombreCurso());
        String planPeriodo = cursosUI.getGradoSeccion() +" - "+ cursosUI.getNivelAcademico();
        txtPeriodo.setText(planPeriodo);
        txtHorario.setText(cursosUI.getDiaHora());
        txtSalon.setText(cursosUI.getNroSalon());
        txtNombreDocente.setText(cursosUI.getNombreDocente());
        txtAlumno.setText(String.valueOf(cursosUI.getCantidadPersonas()));
        if (!cursosUI.isNombreDocenteVisible()) {
            txtNombreDocente.setVisibility(View.GONE);
            imgDocente.setVisibility(View.GONE);
        } else {
            txtNombreDocente.setVisibility(View.VISIBLE);
            imgDocente.setVisibility(View.VISIBLE);
        }

        if (cursosUI.getIdCursDetalleHorario() != 0) {
            imgAccionClase.setVisibility(View.VISIBLE);
        } else {
            imgAccionClase.setVisibility(View.GONE);

        }
        //imgAccionClase.setVisibility(View.VISIBLE);

        fondo.setAlpha((float) 0.2);

        try {
            contItemView.setBackgroundColor(Color.parseColor(cursosUI.getBackgroundSolidColor()));
        }catch (Exception e){
            Log.d(CursosViewHolder.class.getSimpleName(),"parseColor: "+ cursosUI.getBackgroundSolidColor());
            e.printStackTrace();
        }

        imageLoader.load(fondo, cursosUI.getUrlBackgroundItem());

        if(cursosUI.isTutor()){
            imgGrupo.setVisibility(View.VISIBLE);
        }else {
            imgGrupo.setVisibility(View.GONE);
        }

        fondo.setOnClickListener(this);

        imgAccionClase.setOnClickListener(this);

        imgGrupo.setOnClickListener(this);
        imgMovil.setOnClickListener(this);
        imgResultado.setOnClickListener(this);
        imgEva.setOnClickListener(this);
        imgAgenda.setOnClickListener(this);
    }


    private void disabledClick() {
        progressCurso.setVisibility(View.VISIBLE);
        contItemView.setBackgroundColor(Color.parseColor("#5e93c7"));
        contItemView.setEnabled(false);
//        contItemView.setEnabled(false);
    }

    public void enabledCLick(CursosUI cursosUI, int position) {
////        getLayoutPosition();
//        progressCurso.setVisibility(View.GONE);
//        contItemView.setBackgroundColor(cursosUI.getBackgroundSolidColor());
//        contItemView.setEnabled(true);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fondo:
                listener.onCursoSelected(cursosUiRecurso);
                break;
            case R.id.imgAccionClase:
                listener.onHorarioCursoSelected(cursosUiRecurso,cursosUiRecurso.getCargaCurso());
                break;
            case R.id.img_grupo:
                listener.onClickTutoriaCursoSelected(cursosUiRecurso,cursosUiRecurso.getCargaCurso());
                break;
            case R.id.img_resultado:
                listener.onClickResultadoCursoSelected(cursosUiRecurso);
                break;
            case R.id.img_eva:
                listener.onClickEvaCursoSelected(cursosUiRecurso);
                break;
            case R.id.img_movil:
                listener.onClickMovilCursoSelected(cursosUiRecurso);
                break;
            case R.id.img_agenda:
                listener.onClickAgendaCursoSelected(cursosUiRecurso);
                break;
            //case R.id.img_reconocimiento:
            //listener.onClickReconocimientoCursoSelected(cursosUiRecurso,cursosUiRecurso.getCargaCurso());
            //  break;
        }
    }

    public ImageView getFondo() {
        return fondo;
    }

}
