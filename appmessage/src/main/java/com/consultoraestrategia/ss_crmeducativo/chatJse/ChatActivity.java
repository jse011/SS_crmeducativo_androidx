package com.consultoraestrategia.ss_crmeducativo.chatJse;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aghajari.emojiview.listener.OnStickerActions;
import com.aghajari.emojiview.listener.SimplePopupAdapter;
import com.aghajari.emojiview.sticker.Sticker;
import com.aghajari.emojiview.view.AXEmojiEditText;
import com.aghajari.emojiview.view.AXEmojiPopup;
import com.aghajari.emojiview.view.AXEmojiTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.MessageAdapter;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.local.ChatLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.remote.ChatRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.ChangeMessageEstadoEliminado;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.ClipboardMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetListLastMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetListMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetTokenSala;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.SaveImagenMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.SaveMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.SendNotificacion;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.previewCamera.CameraPreviewCamera;
import com.consultoraestrategia.ss_crmeducativo.stiker2.StikersComponet;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.OnStartDragListener;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.SimpleItemTouchHelperCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.codetail.animation.ViewAnimationUtils;

public class ChatActivity extends BaseActivity<ChatView, ChatPresenter> implements ChatView, SwipeRefreshLayout.OnRefreshListener, LastChangePostionListener.CallBack, MessageAdapter.Listener, OnStartDragListener {
    private static final int RequestCodeImgen = 14521;
    @BindView(R2.id.receiver)
    TextView receiver;
    @BindView(R2.id.relations)
    TextView relations;
    @BindView(R2.id.img_receiver)
    CircleImageView imgReceiver;
    @BindView(R2.id.msg)
    AXEmojiEditText msg;
    @BindView(R2.id.recy_msg)
    RecyclerView recyMsg;
    @BindView(R2.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.btn_bajar)
    FloatingActionButton btnBajar;
    @BindView(R2.id.root)
    CoordinatorLayout root;
    @BindView(R2.id.btn_emoticon)
    FloatingActionButton btnEmoticon;
    @BindView(R2.id.txt_count_message)
    TextView txtCountMessage;
    public static String salaId = null;
    @BindView(R2.id.img_sender)
    ImageView imgSender;
    @BindView(R2.id.txt_reciver)
    TextView txtReciver;
    @BindView(R2.id.textreceiver)
    AXEmojiTextView textreceiver;
    @BindView(R2.id.conten_anclar)
    ConstraintLayout contenAnclar;
    @BindView(R2.id.titulo_search)
    TextView tituloSearch;
    @BindView(R2.id.btn_info)
    FloatingActionButton btnInfo;
    @BindView(R2.id.btn_copy)
    FloatingActionButton btnCopy;
    @BindView(R2.id.btn_delete)
    FloatingActionButton btnDelete;
    @BindView(R2.id.toolbar_search)
    Toolbar toolbarSearch;
    @BindView(R2.id.appBar_search)
    AppBarLayout appBarSearch;
    @BindView(R2.id.layout_appbar_search)
    LinearLayout layoutAppbarSearch;
    @BindView(R2.id.appbarLayout)
    AppBarLayout appbarLayout;
    @BindView(R2.id.barra_anclar)
    View barraAnclar;

    private float positionFromRight = 2;
    private MessageAdapter adapter;
    private LastChangePostionListener firsthChangePostionListener;
    private ItemTouchHelper mItemTouchHelper;
    private AXEmojiPopup emojiPopup;

    public static void start(Context context, int personaId, int personaExternaId, String nombreChat, String fotoChat) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("personaId", personaId);
        intent.putExtra("personaExternaId", personaExternaId);
        intent.putExtra("nombreChat", nombreChat);
        intent.putExtra("fotoChat", fotoChat);
        context.startActivity(intent);
    }

    @Override
    protected String getTag() {
        return "ChatActivityTAG";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ChatPresenter getPresenter() {
        ChatRepository repository = new ChatRepository(new ChatLocalDataSource(), new ChatRemoteDataSource());
        return new ChatPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersona(repository),
                new SaveMessage(repository),
                new GetListMessage(repository),
                new GetListLastMessage(repository),
                new GetTokenSala(repository),
                new SendNotificacion(repository),
                new ClipboardMessage(this),
                new ChangeMessageEstadoEliminado(repository),
                new SaveImagenMessage(repository));
    }

    @Override
    protected ChatView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_personal_chat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        swipeContainer.setOnRefreshListener(this);
        setAdapter();
        if (firsthChangePostionListener == null) {
            firsthChangePostionListener = new LastChangePostionListener(this);
        }


        StikersComponet stikersComponet = new StikersComponet(this);
        stikersComponet.setEditText(msg);
        stikersComponet.initStikersFirebase(new OnStickerActions() {
            @Override
            public void onClick(View view, Sticker sticker, boolean fromRecent) {
                if(sticker.getData() instanceof String){
                    presenter.onSelectedSticker((String)sticker.getData());
                }

            }

            @Override
            public boolean onLongClick(View view, Sticker sticker, boolean fromRecent) {
                return false;
            }
        });
        // create emoji popup
        emojiPopup = new AXEmojiPopup(stikersComponet);
        emojiPopup.setPopupListener(new SimplePopupAdapter() {
            @Override
            public void onShow() {
               changeBtnIconTeclado();
            }

            @Override
            public void onDismiss() {
                changeBtnIconEmoticon();
            }
        });

        setupEditex();
        initSearchBar();

    }



    @SuppressLint("ClickableViewAccessibility")
    private void setupEditex() {
        msg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP )  presenter.onClickMsgListener();
                return false;
            }
        });
        btnEmoticon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP )  presenter.onBtnEmoticonClicked();
                return false;
            }
        });
    }

    private void setAdapter() {
        adapter = new MessageAdapter(new ArrayList<Object>(), recyMsg, this, this);
        recyMsg.setLayoutManager(new LinearLayoutManager(this));
        recyMsg.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyMsg);
        //recyMsg.setItemAnimator(new SlideInDownAnimator());
        //recyMsg.setItemAnimator(new SlideInRightAnimator());
        //recyMsg.setItemAnimator(new SlideInLeftAnimator());
        //recyMsg.setItemAnimator(new SlideInUpAnimator());
        recyMsg.setItemAnimator(new DefaultItemAnimator());
        //((SimpleItemAnimator) recyMsg.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void initSearchBar() {
        if (toolbarSearch != null) {
            toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_preview_white);
            layoutAppbarSearch.setVisibility(View.GONE);

            toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onClickNavigationHome();
                }
            });
        }
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void setConstantSalaId(String salaId) {
        ChatActivity.salaId = salaId;
    }

    @Override
    public void setCabecera(String nombre, String descripcion, String url) {
        receiver.setText(nombre);
        relations.setText(descripcion);

        Glide.with(imgReceiver)
                .load(url)
                .apply(Utils.getGlideRequestOptions())
                .into(imgReceiver);
    }

    @Override
    public void clearSend() {
        msg.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setListMessage(List<Object> response, int personaId, boolean notify) {
        Log.d(getTag(), "setListMessage");
        for (Object o : response) {
            if (o instanceof MessageUi2) {
                MessageUi2 messageUi2 = (MessageUi2) o;
                Log.d(getTag(), messageUi2.toString());
            }
        }
        adapter.setList(response, personaId, notify);
    }

    @Override
    public void refreshList(List<Object> messageUis) {
        adapter.addListMessages(messageUis);
    }

    @Override
    public void addMessage(List<Object> messageUiList) {
        Log.d(getTag(), "messageUiList");
        for (Object o : messageUiList) {
            if (o instanceof MessageUi2) {
                MessageUi2 messageUi2 = (MessageUi2) o;
                Log.d(getTag(), messageUi2.toString());
            }
        }
        adapter.addMessages(messageUiList);
    }

    @Override
    public void scrollToPositionBotton(boolean delay) {
        if(delay){
            recyMsg.postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.scrollToPositionBotton();
                }
            }, 100);
        }else {
            adapter.scrollToPositionBotton();
        }


    }

    @Override
    public void scrollToPosition(final int position) {
        adapter.scrollToPosition(position);

    }

    @Override
    public void showFloatingButton() {
        btnBajar.show();
    }

    @Override
    public void hideFloatingButton() {
        btnBajar.hide();
    }

    @Override
    public void showEmoticon() {
        //firsthChangePostionListener.preventEvent();
        emojiPopup.toggle();
        //emojiLayout.hideAndOpenKeyboard();
        //emojiPopup.dismiss(); // Dismisses the Popup.
        // emojiPopup.isShowing(); // Returns true when Popup is showing.
    }


    public void changeBtnIconTeclado() {
        btnEmoticon.setImageResource(R.drawable.ic_keyboard);
    }

    public void changeBtnIconEmoticon() {
        btnEmoticon.setImageResource(R.drawable.input_emoji);
    }

    @Override
    public void showTeclado() {
        emojiPopup.dismiss();
       // emojiLayout.dismiss();
        //emojiLayout.dismiss();
    }

    @Override
    public void showConutMessage(int countMessage) {
        txtCountMessage.setText(String.valueOf(countMessage));
        txtCountMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideConutMessage() {
        txtCountMessage.setVisibility(View.GONE);
    }

    @Override
    public void setConutMessage(int countMessage) {
        txtCountMessage.setText(String.valueOf(countMessage));
    }

    @Override
    public boolean isShowEmoji() {
        boolean emojiShow = false;
        try {
            emojiShow = emojiPopup.isShowing();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emojiShow;
    }

    @Override
    public void showAnclarMessage(MessageUi2 messageUi2) {

    }

    @Override
    public void showDeleteMessage() {
        btnDelete.show();
    }

    @Override
    public void hideDeleteMessage() {
        btnDelete.hide();
    }

    @Override
    public void changeList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setCountSelection(int countMessage) {
        tituloSearch.setText(String.valueOf(countMessage));
    }

    @Override
    public void changeToolbarSelection() {
        changeColorStatussbar(ContextCompat.getColor(this, R.color.colorPrimary));
        if (layoutAppbarSearch.getVisibility() != View.VISIBLE) showSearchBar(positionFromRight);
    }

    @Override
    public void changeToolbarNormal() {
        changeColorStatussbar(Color.parseColor("#112747"));
        if (layoutAppbarSearch.getVisibility() == View.VISIBLE) hideSearchBar(positionFromRight);
    }

    @Override
    public void showAlertDelete(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("")
                .setMessage(mensaje)
                .setCancelable(false)
                .setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onClickAcceptDelete();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void hideAnclarMessage() {
        contenAnclar.setVisibility(View.GONE);
    }

    @Override
    public void showOnPickImage(PersonaUi personaUiExterna) {
        Intent intent = CameraPreviewCamera.star(this, personaUiExterna.getFoto(), "", personaUiExterna.getNombre(), null, 0);
        startActivityForResult(intent, RequestCodeImgen);
    }

    @Override
    public void showAnclarMessage(int emisorId, int personaId, String nombre, String imagenFcm, String mensaje) {
        contenAnclar.setVisibility(View.VISIBLE);
        if(emisorId==personaId){
            nombre = "Tú";
            txtReciver.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            barraAnclar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }else {
            txtReciver.setTextColor(Color.parseColor("#1A7264"));
            barraAnclar.setBackgroundColor(Color.parseColor("#1A7264"));
        }

        txtReciver.setText(nombre);
        textreceiver.setText(TextUtils.isEmpty(imagenFcm) ? mensaje : "\uD83D\uDCF7 Foto");
        if (!TextUtils.isEmpty(imagenFcm)) {

            imgSender.setVisibility(View.VISIBLE);
            Glide.with(imgSender)
                    .load(imagenFcm)
                    .apply(Utils.getGlideRequestOptions()
                            .centerCrop())
                    .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new FitCenter()))
                    .skipMemoryCache(true)
                    //.into(imgSender);
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            imgSender.setImageDrawable(resource);
                            if (resource instanceof Animatable) {
                                ((Animatable)resource).stop();
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        } else {
            imgSender.setVisibility(View.GONE);
            imgSender.setImageDrawable(null);
        }

    }

    @Override
    public void onInitListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyMsg.removeOnScrollListener(firsthChangePostionListener);
                recyMsg.removeOnLayoutChangeListener(firsthChangePostionListener);

                recyMsg.addOnScrollListener(firsthChangePostionListener);
                recyMsg.addOnLayoutChangeListener(firsthChangePostionListener);
            }
        },1000);

    }

    @Override
    public void updateList(MessageUi2 messageUi2) {
        adapter.update(messageUi2);
    }

    @Override
    public void updateListPosition(int posicion, MessageUi2 messageUi1) {
        adapter.update(posicion, messageUi1);
    }

    @Override
    public void addList(Object add) {
        adapter.add(add);
    }

    @Override
    public void showInfoMessage() {
        btnInfo.show();
    }

    @Override
    public void hideInfoMessage() {
        btnInfo.hide();
    }


    public void changeColorStatussbar(@ColorInt int  color){
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    @OnClick(R2.id.btnSend)
    public void onBtnSendClicked() {
        presenter.onClickSend(msg.getText().toString());
    }

    @Override
    public void hideProgress() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        swipeContainer.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void changeFirsthPostion(int firstVisibleItem) {
        Log.d(getTag(), "firstVisibleItem: " + firstVisibleItem);
        presenter.changeFirsthPostion(firstVisibleItem);
    }

    @Override
    public void onKeyboardOpens(int lastVisibleItem) {

        presenter.onKeyboardOpens(lastVisibleItem);
    }

    @Override
    public void onKeyboardClose() {
        boolean emojiShow = false;
        try {
            emojiShow = emojiPopup.isShowing();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //updateBottom(emojiLayout.isShowing() ? emojiLayout.getPopupHeight() : 0);
        presenter.onKeyboardClose();
    }

    @OnClick(R2.id.btn_bajar)
    public void onBtnBajarClicked() {
        presenter.onBtnBajarClicked();
    }

    @OnClick(R2.id.btn_emoticon)
    public void onBtnEmoticonClicked() {
       // presenter.onBtnEmoticonClicked();
    }

    @Override
    public void onSeleccionar(MessageUi2 messageUi2) {
        presenter.onSeleccionarMessage(messageUi2);
    }

    @Override
    public void onLongClick(MessageUi2 messageUi2) {
        presenter.onLongClick(messageUi2);
    }

    @Override
    public void onClick(MessageUi2 messageUi2) {
        presenter.onClickMessage(messageUi2);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    /**
     * to hide the searchAppBarLayout and show the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void hideSearchBar(float positionFromRight) {

        try {
            // start x-index for circular animation
            int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48) * (0.5f + positionFromRight));
            // start  y-index for circular animation
            int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

            // calculate max radius
            int dx = Math.max(cx, toolbar.getWidth() - cx);
            int dy = Math.max(cy, toolbar.getHeight() - cy);
            float finalRadius = (float) Math.hypot(dx, dy);

            // Circular animation declaration begin
            Animator animator;
            animator = ViewAnimationUtils
                    .createCircularReveal(layoutAppbarSearch, cx, cy, finalRadius, 0);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(200);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutAppbarSearch.setVisibility(View.GONE);
                    //editTextSearch.setText("");
                    //UtilsAppMessenger.hideKeyBoard(editTextSearch);

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            animator.start();
        } catch (Exception e) {
            e.printStackTrace();
            layoutAppbarSearch.setVisibility(View.GONE);
            //editTextSearch.setText("");
            //UtilsAppMessenger.hideKeyBoard(editTextSearch);
        }

        // Circular animation declaration end

        appbarLayout.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appbarLayout, "translationY", 0),
                ObjectAnimator.ofFloat(appbarLayout, "alpha", 1),
                ObjectAnimator.ofFloat(appbarLayout, "translationY", 0)
        );
        set.setDuration(100).start();

    }

    /**
     * to show the searchAppBarLayout and hide the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void showSearchBar(float positionFromRight) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                // ObjectAnimator.ofFloat(appbarLayout, "translationY", -tabLayout.getHefight()),
                // ObjectAnimator.ofFloat(appbarLayout, "translationY", -tabLayout.getHeight()),
                ObjectAnimator.ofFloat(appbarLayout, "alpha", 0)
        );
        set.setDuration(100).addListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                appbarLayout.setVisibility(View.GONE);
                //editTextSearch.requestFocus();
                //UtilsAppMessenger.showKeyBoard(editTextSearch);
            }

            @Override
            public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

            }
        });
        set.start();


        // start x-index for circular animation
        int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48) * (0.5f + positionFromRight));
        // start y-index for circular animation
        int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, toolbar.getWidth() - cx);
        int dy = Math.max(cy, toolbar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        final Animator animator;
        animator = ViewAnimationUtils
                .createCircularReveal(layoutAppbarSearch, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        layoutAppbarSearch.setVisibility(View.VISIBLE);
        animator.start();
        // Circular animation declaration end
    }

    @Override
    public void onBackPressed() {
        // if the searchToolBar is visible, hide it
        // otherwise, do parent onBackPressed method
        if (layoutAppbarSearch.getVisibility() == View.VISIBLE)
            presenter.onClickNavigationHome();
        else
            super.onBackPressed();

    }

    @OnClick({R2.id.btn_info, R2.id.btn_copy, R2.id.btn_delete})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_info) {
            presenter.onClickInfo();
        } else if (id == R.id.btn_copy) {
            presenter.onClickCopy();
        } else if (id == R.id.btn_delete) {
            presenter.onClickDelete();
        }
    }

    @OnClick(R2.id.btn_close_anclar)
    public void onClickedBtnCloseAnclar() {
        presenter.onClickedBtnCloseAnclar();
    }

    @OnClick(R2.id.btn_imagen)
    public void onClickedBtnImagen() {
        presenter.onClickedBtnImagen();
    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(getTag(), "onActivityResult: ");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ChatActivity.RESULT_OK && requestCode == RequestCodeImgen) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(CameraPreviewCamera.IMAGE_RESULTS);
            String descripcion = data.getStringExtra(CameraPreviewCamera.TEXTO_RESULTS);
            presenter.onSeleccionList(returnValue, descripcion);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
