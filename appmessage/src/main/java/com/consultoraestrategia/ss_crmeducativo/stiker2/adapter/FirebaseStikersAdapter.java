package com.consultoraestrategia.ss_crmeducativo.stiker2.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.aghajari.emojiview.sticker.Sticker;
import com.aghajari.emojiview.sticker.StickerCategory;
import com.aghajari.emojiview.sticker.StickerLoader;
import com.aghajari.emojiview.sticker.StickerProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.consultoraestrategia.ss_crmeducativo.stiker2.categoria.FirebaseStickers;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.CategoriaUi;
import com.consultoraestrategia.ss_crmeducativo.stiker2.entities.StikersUi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FirebaseStikersAdapter implements StickerProvider {
    StickerCategory[] stickerCategories = new StickerCategory[0];
    @NonNull
    @Override
    public StickerCategory[] getCategories() {

        return stickerCategories;
    }

    @NonNull
    @Override
    public StickerLoader getLoader() {
        return new StickerLoader() {
            @Override
            public void onLoadSticker(final View view, Sticker sticker) {

                if (sticker.isInstance(Integer.class)) {
                    Glide.with(view).load((int) sticker.getData()).apply(RequestOptions.fitCenterTransform()).into((AppCompatImageView)view);
                }else if(sticker.isInstance(String.class)) {

                   Glide.with(view)
                            .asDrawable()
                            .load((String)sticker.getData())
                            .apply(RequestOptions
                                    .fitCenterTransform()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                            .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new FitCenter()))
                            .skipMemoryCache(true)
                           // .into((AppCompatImageView)view);
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            ((AppCompatImageView)view).setImageDrawable(resource);
                            if (resource instanceof Animatable) {
                                ((Animatable)resource).start();
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });

                }
            }

            @Override
            public void onLoadStickerCategory(final View icon, StickerCategory stickerCategory, boolean selected) {
               if(stickerCategory.getCategoryData() instanceof CategoriaUi){
                   CategoriaUi categoriaUi = (CategoriaUi)stickerCategory.getCategoryData();
                   List<StikersUi> stikersUiList = categoriaUi.getStikersUiList();
                   if(!stikersUiList.isEmpty()){
                       String imagen = (String)stikersUiList.get(0).getData().toString();
                       Glide.with(icon)
                               .asDrawable()
                               .load(imagen)
                               .thumbnail(0.25f)
                               .apply(RequestOptions
                                       .fitCenterTransform())
                                       //.diskCacheStrategy(DiskCacheStrategy.ALL))
                               .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new FitCenter()))
                               .skipMemoryCache(true)
                               // .into((AppCompatImageView)view);
                               .into(new CustomTarget<Drawable>() {
                                   @Override
                                   public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                       ((AppCompatImageView)icon).setImageDrawable(resource);
                                       if (resource instanceof Animatable) {
                                           ((Animatable)resource).stop();
                                       }
                                   }

                                   @Override
                                   public void onLoadCleared(@Nullable Drawable placeholder) {

                                   }
                               });
                   }

               }else if(stickerCategory.getCategoryData() instanceof String) {
                   Glide.with(icon)
                           .load((String) stickerCategory.getCategoryData())
                           .apply(RequestOptions.fitCenterTransform())
                           .into((AppCompatImageView)icon);
               }else if(stickerCategory.getCategoryData() instanceof Integer) {
                   Glide.with(icon)
                           .load((Integer) stickerCategory.getCategoryData())
                           .apply(RequestOptions.fitCenterTransform())
                           .into((AppCompatImageView)icon);
               }


            }
        };
    }

    @Override
    public boolean isRecentEnabled() {
        return true;
    }


    public void setList(List<CategoriaUi> categoriaUis){
        List<StickerCategory> list = new ArrayList<>();
        for (CategoriaUi categoriaUi : categoriaUis){
            list.add(new FirebaseStickers(categoriaUi));
        }
        StickerCategory[] array = new StickerCategory[list.size()];
        stickerCategories = list.toArray(array);
    }
}
