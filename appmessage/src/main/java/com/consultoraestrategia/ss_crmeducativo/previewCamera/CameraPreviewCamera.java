package com.consultoraestrategia.ss_crmeducativo.previewCamera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fxn.pix.Options;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.ChatGrupalActivity;
import com.consultoraestrategia.ss_crmeducativo.previewCamera.adapter.PreviewAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.fxn.pix.Pix;
import com.github.chrisbanes.photoview.PhotoView;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CameraPreviewCamera extends AppCompatActivity implements PreviewAdapter.Listener {
    public static final String IMAGE_RESULTS = "image_results";
    public static final String TEXTO_RESULTS = "texto_results";
    private static final int RequestCodeImgen = 45234;
    @BindView(R2.id.rec_preview)
    RecyclerView recPreview;
    @BindView(R2.id.imagenEvento)
    PhotoView imagenEvento;
    @BindView(R2.id.msg)
    EmojiEditText msg;
    @BindView(R2.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R2.id.nombrePersonaEvento)
    TextView nombrePersonaEvento;
    @BindView(R2.id.name_perfil)
    TextView namePerfil;
    @BindView(R2.id.root)
    ConstraintLayout root;
    @BindView(R2.id.btn_emoticon)
    FloatingActionButton btnEmoticon;
    private PreviewAdapter previewAdapter;
    private boolean estado = false;
    private EmojiPopup emojiPopup;
    private boolean emoticon;

    public static Intent star(Context context, String image, String alias, String perfil, String color, int drawable) {
        Intent intent = new Intent(context, CameraPreviewCamera.class);
        intent.putExtra("image", image);
        intent.putExtra("alias", alias);
        intent.putExtra("perfil", perfil);
        intent.putExtra("color", color);
        intent.putExtra("drawable", drawable);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_camera);
        ButterKnife.bind(this);
        emojiPopup = EmojiPopup.Builder.fromRootView(root).build(msg);
        setupEditex();
        String imagen = getIntent().getStringExtra("image");
        String alias = getIntent().getStringExtra("alias");
        String perfil = getIntent().getStringExtra("perfil");
        int drawable = getIntent().getIntExtra("drawable", 0);
        String color = getIntent().getStringExtra("color");

        nombrePersonaEvento.setText(perfil);

        if (TextUtils.isEmpty(imagen)) {

            if (drawable == 1) {
                circleImageView.setImageDrawable(ContextCompat.getDrawable(this, drawable));
            } else {
                namePerfil.setVisibility(View.VISIBLE);
                namePerfil.setText(alias);
                if (TextUtils.isEmpty(color)) {
                    Drawable drawableRecurso = ContextCompat.getDrawable(this, R.drawable.border_message);
                    circleImageView.setBackground(drawableRecurso.mutate());
                    namePerfil.setText(alias);
                    namePerfil.setTextColor(Color.BLACK);
                } else {
                    Drawable drawableRecurso = ContextCompat.getDrawable(this, R.drawable.border_message);
                    try {
                        drawableRecurso.mutate().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);
                        circleImageView.setBackground(drawableRecurso);
                        namePerfil.setTextColor(Color.WHITE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


        } else {
            Glide.with(circleImageView)
                    .load(imagen)
                    .apply(Utils.getGlideRequestOptions())
                    .into(circleImageView);
        }

        recPreview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        previewAdapter = new PreviewAdapter(this);
        recPreview.setAdapter(previewAdapter);
                                         //Custom Path For media Storage

        root.postDelayed(new Runnable() {
            @Override
            public void run() {
                Options options = Options.init()
                        .setRequestCode(RequestCodeImgen)                                           //Request code for activity results
                        .setCount(8)                                                   //Number of images to restict selection count
                        .setFrontfacing(false)                                         //Front Facing camera on start
                        //.setPreSelectedUrls(returnValue)                               //Pre selected Image Urls
                        .setSpanCount(4)                                               //Span count for gallery min 1 & max 5
                        .setExcludeVideos(true)                                       //Option to exclude videos
                        .setVideoDurationLimitinSeconds(30)                            //Duration for video recording
                        .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
                        .setPath(getResources().getString(R.string.app_name)+"/images");

                Pix.start(CameraPreviewCamera.this, options);
            }
        }, 50);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEditex() {
        msg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(emoticon){
                        emoticon=false;
                        emojiPopup.dismiss();
                        changeBtnIconEmoticon();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ChatGrupalActivity.RESULT_OK && requestCode == RequestCodeImgen) {
            root.setVisibility(View.VISIBLE);
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            previewAdapter.setLista(returnValue);
            if (returnValue.size() < 2) {
                recPreview.setVisibility(View.GONE);
            }
            if (!returnValue.isEmpty()) {
                Glide.with(imagenEvento)
                        .load(returnValue.get(0))
                        .apply(Utils.getGlideRequestOptionsSimple())
                        .into(imagenEvento);
            }


        }else {
            finish();
        }

    }

    @Override
    public void onClick(String url) {
        Glide.with(imagenEvento)
                .load(url)
                .apply(Utils.getGlideRequestOptionsSimple())
                .into(imagenEvento);
    }

    @OnClick(R2.id.backPrincipal)
    public void onBackPrincipalClicked() {
        finish();
    }

    @OnClick(R2.id.floatingActionButton4)
    public void onViewClicked() {
        try {
            Intent resultIntent = new Intent();
            resultIntent.putStringArrayListExtra(IMAGE_RESULTS, previewAdapter != null ? new ArrayList<>(previewAdapter.getLista()) : new ArrayList<String>());
            resultIntent.putExtra(TEXTO_RESULTS, msg.getText().toString());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (previewAdapter.getLista().isEmpty() && estado) finish();
        estado = true;
        emoticon = false;
        changeBtnIconEmoticon();
    }



    @OnClick(R2.id.btn_emoticon)
    public void onBtnEmoticonClicked() {
        emoticon = !emoticon;
        if (emoticon) {
            emojiPopup.toggle();
            changeBtnIconTeclado();
        } else {
            emojiPopup.dismiss();
            changeBtnIconEmoticon();
        }
    }

    public void changeBtnIconTeclado() {
        btnEmoticon.setImageResource(R.drawable.ic_keyboard);
    }

    public void changeBtnIconEmoticon() {
        btnEmoticon.setImageResource(R.drawable.input_emoji);
    }
}
