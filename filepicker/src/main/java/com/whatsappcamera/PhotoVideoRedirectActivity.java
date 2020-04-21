package com.whatsappcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import droidninja.filepicker.R;

/**
 * Created by sotsys016-2 on 13/8/16 in com.cnc3camera.
 */
public class PhotoVideoRedirectActivity extends AppCompatActivity {

    private ImageView imgShow;
    private FloatingActionButton floatButtonCheck;
    private FloatingActionButton floatButtonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photovideo_redirect);

        init();

    }

    VideoView videoView;

    private void init() {

        imgShow = (ImageView) findViewById(R.id.imgShow);
        videoView = (VideoView) findViewById(R.id.vidShow);
        floatButtonCheck = (FloatingActionButton)findViewById(R.id.floatButton_check);

        floatButtonClose = (FloatingActionButton)findViewById(R.id.floatButton_close);
        floatButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getStringExtra("WHO").equalsIgnoreCase("Image")) {

            imgShow.setVisibility(View.VISIBLE);

            Glide.with(PhotoVideoRedirectActivity.this)
                    .load(getIntent().getStringExtra("PATH"))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_photo_cont))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            return false;
                        }
                    })
                    .into(imgShow);

            floatButtonCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bmap = BitmapFactory.decodeFile(getIntent().getStringExtra("PATH"), options);
                    //guardar imagen
                    Save savefile = new Save();
                    String path = savefile.SaveImage(imgShow.getContext(), bmap);
                   if(!TextUtils.isEmpty(path)){
                       Intent data = new Intent();
                       //---set the data to pass back---
                       data.putExtra(WhatsappCameraActivity.CAPTURED_PHOTO_PATH_KEY,path);
                       setResult(RESULT_OK, data);
                       //---close the activity---
                   }
                    finish();
                }
            });

        } else {

            videoView.setVisibility(View.VISIBLE);
            try {
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("PATH")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            videoView.requestFocus();
            //videoView.setZOrderOnTop(true);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    videoView.start();
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //videoView.start();
                }
            });

            floatButtonCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //guardar imagen
                    SaveVideo savefile = new SaveVideo();
                    String path = savefile.SaveVideo(videoView.getContext(), getIntent().getStringExtra("PATH"));
                    if(!TextUtils.isEmpty(path)){
                        Intent data = new Intent();
                        //---set the data to pass back---
                        data.putExtra(WhatsappCameraActivity.CAPTURED_PHOTO_PATH_KEY,path);
                        setResult(RESULT_OK, data);
                        //---close the activity---
                    }
                    finish();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
        super.onBackPressed();
    }
}
