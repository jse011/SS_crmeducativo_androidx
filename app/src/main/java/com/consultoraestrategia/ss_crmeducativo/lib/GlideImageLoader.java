package com.consultoraestrategia.ss_crmeducativo.lib;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.consultoraestrategia.ss_crmeducativo.R;


/**
 * Created by kelvi on 22/02/2017.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private Context context;
//    private CropCircleTransformation circleTransformation;

    public GlideImageLoader(Context context) {
        this.context = context;
        this.glideRequestManager = Glide.with(context);

    }

    @Override
    public void load(ImageView imageView, String URL) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_error_outline_black)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        glideRequestManager
                .load(URL)
                .apply(options)
                .into(imageView);


    }

    @Override
    public void load(final ImageView imageView, String URL, final CallBack callBack) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_error_outline_black)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        glideRequestManager
                .asBitmap()
                .load(URL)
                .apply(options)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(resource, transition);
                        if(callBack==null)return;
                        callBack.onSucces(resource);
                    }
                });



    }

    @Override
    public void loadWithCircular(final ImageView imageView, String url) {
        loadWithCircular(imageView, url,null,null);
    }

    @Override
    public void loadWithCircular(ImageView imageView, String URL, RequestOptions options) {
        loadWithCircular(imageView, URL,options,null);
    }


    @Override
    public void loadWithCircular(final ImageView imageView, String url, final CallBack callBack) {
        loadWithCircular(imageView, url,null,callBack);

    }

    @Override
    public void loadWithCircular(final ImageView imageView, String url, RequestOptions options, final CallBack callBack) {
        if(options==null)options = new RequestOptions()
                .error(R.drawable.ic_error_outline_black)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        glideRequestManager
                .asBitmap()
                .load(url)
                .apply(options)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                        if(callBack!=null)callBack.onSucces(resource);
                    }
                });
    }
}
