package com.consultoraestrategia.ss_crmeducativo.lib;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

/**
 * Created by kelvi on 22/02/2017.
 */

public interface ImageLoader {
  void load(ImageView imageView, String URL);
  void loadWithCircular(ImageView imageView, String URL);
  void loadWithCircular(ImageView imageView, String URL,RequestOptions options);
  void loadWithCircular(ImageView imageView, String url, CallBack callBack);
  void loadWithCircular(ImageView imageView, String url, RequestOptions options, CallBack callBack);
  void load(ImageView imageView, String URL, ImageLoader.CallBack callBack);

  public interface CallBack{
    void onSucces(Bitmap bitmap);
  }
}
