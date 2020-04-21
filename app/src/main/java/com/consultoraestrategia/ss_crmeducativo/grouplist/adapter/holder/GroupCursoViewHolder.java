package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder;


import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
;
import android.widget.TextView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.CursoGrupoUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupCursoViewHolder extends RecyclerView.ViewHolder  {
   private static final String TAG = GroupTeamViewHolder.class.getSimpleName();
   @BindView(R.id.txtCurso)
   TextView txtCurso;
   @BindView(R.id.view3)
   View view;
   @BindView(R.id.constraintLayout)
   ConstraintLayout constraintLayout;


   public  GroupCursoViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
   }

   public void bind(CursoGrupoUi cursoGrupo){
      if(cursoGrupo!=null&&cursoGrupo.getNombreCurso()!=null)txtCurso.setText(cursoGrupo.getNombreCurso().toUpperCase());
      try {
         txtCurso.setTextColor(Color.parseColor(cursoGrupo.getColor1()));
         view.setBackgroundColor(Color.parseColor(cursoGrupo.getColor1()));
      }catch (Exception e){
              e.printStackTrace();
      }
   }
}
