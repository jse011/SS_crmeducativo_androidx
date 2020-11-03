package com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.holder;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.MessageAdapter;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.LinkUtils;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.ItemTouchHelperViewHolder;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageStikerHolder extends RecyclerView.ViewHolder implements LinkUtils.OnClickListener, ItemTouchHelperViewHolder, View.OnClickListener, View.OnLongClickListener {

    @BindView(R2.id.img_compartir)
    ImageView imgCompartir;
    @BindView(R2.id.background_view_stub)
    ConstraintLayout backgroundViewStub;
    @BindView(R2.id.img_sender)
    ImageView imgSender;
    @BindView(R2.id.content_sender)
    ConstraintLayout contentSender;
    @BindView(R2.id.horaSen)
    TextView horaSen;
    @BindView(R2.id.horaSenDelete)
    TextView horaSenDelete;
    @BindView(R2.id.contSenderEliminado)
    LinearLayout contSenderEliminado;
    @BindView(R2.id.img_receiver)
    ImageView imgReceiver;
    @BindView(R2.id.content_receiver)
    ConstraintLayout contentReceiver;
    @BindView(R2.id.horaRec)
    TextView horaRec;
    @BindView(R2.id.horaRecDelete)
    TextView horaRecDelete;
    @BindView(R2.id.contReceiverEliminado)
    LinearLayout contReceiverEliminado;
    @BindView(R2.id.foreground_view)
    public LinearLayout foregroundView;
    @BindView(R2.id.card_receiver)
    CardView cardReceiver;
    @BindView(R2.id.card_sender)
    CardView cardSender;
    private MessageUi2 messageUic;
    private MessageAdapter.Listener listener;
    private boolean seleccionado;

    public MessageStikerHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MessageUi2 messageUic, int personaId, MessageAdapter.Listener listener){
        this.listener = listener;
        this.messageUic = messageUic;
        this.seleccionado = false;
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(messageUic.getFecha()!=null?messageUic.getFecha():new Date());
        
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) foregroundView.getLayoutParams();

        int hora=calendar.get(Calendar.HOUR_OF_DAY);
        int minuto= calendar.get(Calendar.MINUTE);
        String tiempo = hora+":"+minuto+ ""+( (hora>=12) ? "PM" : "AM");
        if(messageUic.getEmisorId()== personaId)
        {

            layoutParams.setMarginStart((int) Utils.convertDpToPixel(32, itemView.getContext()));
            layoutParams.setMarginEnd((int) Utils.convertDpToPixel(0, itemView.getContext()));
            foregroundView.setLayoutParams(layoutParams);


            contentReceiver.setVisibility(View.GONE);
            contReceiverEliminado.setVisibility(View.GONE);
            
            contentSender.setVisibility(View.VISIBLE);
            contSenderEliminado.setVisibility(View.GONE);

            horaSen.setText(tiempo);

            starImage(cardSender, imgSender, messageUic.getImagenFcm());

            switch (messageUic.getEstado())
            {
                case CREADO:
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_historia, 0);
                    break;
                case GUARDADO:
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                    break;
                case ENVIADO:
                    Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.message_got_read_receipt_from_target);
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.setSaturation(0);
                    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                    drawable.mutate().setColorFilter(filter);
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
                    break;
                case VISTO:
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.message_got_read_receipt_from_target, 0);
                    break;
                default:
                    contentSender.setVisibility(View.GONE);
                    contSenderEliminado.setVisibility(View.VISIBLE);
                    horaSenDelete.setText(tiempo);
                    break;
            }
        }
        else
        {

            layoutParams.setMarginStart((int) Utils.convertDpToPixel(0, itemView.getContext()));
            layoutParams.setMarginEnd((int) Utils.convertDpToPixel(32, itemView.getContext()));
            foregroundView.setLayoutParams(layoutParams);

            contentSender.setVisibility(View.GONE);
            contSenderEliminado.setVisibility(View.GONE);
            contentReceiver.setVisibility(View.VISIBLE);
            contReceiverEliminado.setVisibility(View.GONE);

            horaRec.setText(tiempo);
            starImage(cardReceiver, imgReceiver, messageUic.getImagenFcm());

            if (messageUic.getEstado() == MessageUi2.ESTADO.ELIMINADO) {
                contentReceiver.setVisibility(View.GONE);
                contReceiverEliminado.setVisibility(View.VISIBLE);
                horaRecDelete.setText(tiempo);
            }

        }

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        if(messageUic.isSelected()){
            itemView.setBackgroundColor(Color.parseColor("#5E90CAF9"));
        }else {
            itemView.setBackgroundColor(0);
        }

    }

    String imagenURL = null;
    private void starImage(final CardView cardView , final ImageView imageView, String url){
        if(!TextUtils.isEmpty(imagenURL)&&!imagenURL.equals(url)){
            Glide.with(imageView).clear(imageView);
            imageView.setImageDrawable(null);
        }
        imagenURL = url;
        Glide.with(imageView)
                .asDrawable()
                .load(url)
                .apply(RequestOptions
                        .fitCenterTransform()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new FitCenter()))
                .skipMemoryCache(true)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        cardView.setCardBackgroundColor(Color.parseColor("#2F000000"));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        cardView.setCardBackgroundColor(Color.TRANSPARENT);
                        return false;
                    }
                })
                // .into((AppCompatImageView)view);
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                        if (resource instanceof Animatable) {
                            ((Animatable)resource).start();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    @Override
    public void onLinkClicked(View v, String link) {
        Log.d("onLinkClicked", "link: " + link);
        listener.onClick(messageUic);
    }

    @Override
    public void onClicked(View v) {
        Log.d("onClicked", "not Link: ");
        listener.onClick(messageUic);
    }


    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {
        imgCompartir.setVisibility(View.GONE);
        //backgroundViewStub.setBackgroundColor(0);
        if(seleccionado)listener.onSeleccionar(messageUic);
    }

    @Override
    public boolean canDrag() {
        return false;
    }

    @Override
    public boolean canSwipe() {
        return messageUic!=null && messageUic.getEstado()!= MessageUi2.ESTADO.ELIMINADO;
    }

    @Override
    public View backgroundView() {
        return foregroundView;
    }

    @Override
    public void setTranslationX(float dX, boolean isCurrentlyActive) {
        if(dX==0){
            imgCompartir.setVisibility(View.GONE);
        }else if(dX >60 && dX< 180){
            if(imgCompartir.getVisibility()!=View.VISIBLE)imgCompartir.setVisibility(View.VISIBLE);
            backgroundViewStub.setTranslationX(dX-100);
        }

        if(isCurrentlyActive){
            seleccionado = !(dX < 190);
        }

        Log.d("setTranslationX: ", "dx: " + dX);
    }

    @Override
    public void clearView() {
        backgroundViewStub.setTranslationX(0);
    }

    @Override
    public boolean onLongClick(View view) {
        listener.onLongClick(messageUic);
        return true;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(messageUic);
    }
}
