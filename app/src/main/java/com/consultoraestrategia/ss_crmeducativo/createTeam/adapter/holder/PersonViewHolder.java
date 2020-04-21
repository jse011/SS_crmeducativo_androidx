package com.consultoraestrategia.ss_crmeducativo.createTeam.adapter.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionObservadaUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeam.listener.PersonListener;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ImageLoader.CallBack {
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txtItemName)
    TextView txtName;
    @BindView(R.id.imgPicture)
    CircleImageView imgPicture;
    @BindView(R.id.imgCheck)
    ImageView imgCheck;
    @BindView(R.id.txt_subdivicion)
    TextView txtSubdivicion;
    @BindView(R.id.txtTipoAprendizaje)
    TextView txtTipoAprendizaje;
    @BindView(R.id.txt_tipoAp_two)
    TextView txtTipoApTwo;
    @BindView(R.id.txt_empty)
    TextView txtEmpty;

    private PersonListener listener;
    private Person person;
    private
    ImageLoader imageLoader;

    public PersonViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        imgPicture.setOnClickListener(this);
        txtSubdivicion.setOnClickListener(this);
        imageLoader = new GlideImageLoader(view.getContext());
    }

    public void bind(Person person, Context context, PersonListener listener, Animation slideUp, Animation slideDown) {
        if(person==null)return;
        this.listener = listener;
        this.person = person;
        txtName.setText(person.getFullName());
        //addMemberClickListener(person, listener);
        //txtTipoAprendizaje.setText(person.getTipoAprendizaje());
        setPersonMember(person, slideUp, slideDown);
        imageLoader.load(imgPicture, person.getUrlPicture(), this);
        if(person.getDimensionObservadasUiList().size()>=1){
            inciarDimension(person.getDimensionObservadasUiList().get(0),txtTipoAprendizaje);
        } else {
            txtTipoApTwo.setBackground(null);
            txtTipoApTwo.setTextColor(Color.WHITE);
            txtTipoApTwo.setText("");
        }

        if(person.getDimensionObservadasUiList().size()>=2){
            inciarDimension(person.getDimensionObservadasUiList().get(1),txtTipoApTwo);
        } else{
            txtTipoAprendizaje.setBackground(null);
            txtTipoAprendizaje.setTextColor(Color.WHITE);
            txtTipoAprendizaje.setText("");
        }

        if(person.getDimensionObservadasUiList().size()<1){
            txtEmpty.setVisibility(View.VISIBLE);
        }else {
            txtEmpty.setVisibility(View.GONE);
        }
    }

    private void inciarDimension(DimensionObservadaUi dimensionObservadaUi, TextView textView){
        DimensionUi dimensionUi = dimensionObservadaUi.getDimensionUi();
        if(dimensionUi==null)return;

        int colorFondo;
        int colorRBG= ContextCompat.getColor(itemView.getContext(), R.color.white);
        Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_radius_spinner);
        try {
            colorFondo = Color.parseColor(dimensionUi.getColor());
            circle.mutate().setColorFilter(colorFondo, PorterDuff.Mode.SRC_ATOP);
        }catch (Exception e){
            e.printStackTrace();
        }
        textView.setBackground(circle);
        textView.setTextColor(colorRBG);
        String descripcion = dimensionUi.getSigla() +":  "+ dimensionObservadaUi.getPorcentaje() + "%";
        textView.setText(descripcion);
    }

    private void setPersonMember(Person person, Animation slideUp, Animation slideDown) {
        if (person.isMember()) {
            showCheck(slideUp);
        } else {
            hideCheck(slideDown);
        }
    }

    private void hideCheck(Animation slideDown) {
        if (imgCheck.getVisibility() == View.VISIBLE) {
            imgCheck.setVisibility(View.GONE);
            imgCheck.startAnimation(slideDown);
        }
    }

    private void showCheck(Animation slideUp) {
        if (imgCheck.getVisibility() == View.GONE) {
            imgCheck.setVisibility(View.VISIBLE);
            imgCheck.startAnimation(slideUp);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgPicture:
                if(person!=null)listener.onClickInfoPersona(person);
                break;
            case R.id.txt_subdivicion:
                if (imgCheck.getVisibility() == View.GONE) {
                    if(person!=null)listener.onPersonSeleteced(person);
                } else if (imgCheck.getVisibility() == View.VISIBLE) {
                    if(person!=null)listener.onPersonUnselected(person);
                }
                break;
        }
    }

    @Override
    public void onSucces(Bitmap bitmap) {
        person.setBitmap(bitmap);
    }
}
