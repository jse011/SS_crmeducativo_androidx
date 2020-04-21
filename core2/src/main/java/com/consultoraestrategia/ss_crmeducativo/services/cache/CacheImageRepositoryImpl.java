package com.consultoraestrategia.ss_crmeducativo.services.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.util.List;

public class CacheImageRepositoryImpl implements CacheImageRepository {
    private Context context;

    public CacheImageRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public void save(List<String> imageUrl, int size) {
        try {
            for (String s : imageUrl){
                if(TextUtils.isEmpty(s))continue;
                Log.d(getClass().getSimpleName(), "Url: " + imageUrl);
                FutureTarget<Bitmap> futureTarget =
                        Glide.with(context)
                                .asBitmap()
                                .apply(new RequestOptions()
                                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                                .load(s)
                                .submit(size, size);

                //Bitmap bitmap = futureTarget.get();

                // Do something with the Bitmap and then when you're done with it:
                // Glide.with(context).clear(futureTarget);
            }
        }catch (Exception e){e.printStackTrace();}

    }
}
