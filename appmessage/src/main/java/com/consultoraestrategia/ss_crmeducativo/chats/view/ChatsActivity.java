package com.consultoraestrategia.ss_crmeducativo.chats.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.aghajari.emojiview.AXEmojiManager;
import com.aghajari.emojiview.emoji.iosprovider.AXIOSEmojiProvider;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.UseCaseSincronizar;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.ChatGrupalActivity;
import com.consultoraestrategia.ss_crmeducativo.chatJse.ChatActivity;
import com.consultoraestrategia.ss_crmeducativo.chats.contacs.ContactsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.contacs.searchContacs.SearchContacsActivity;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataRemoteSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.groups.GroupsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.groups.searchGroups.SearchGroupActivity;
import com.consultoraestrategia.ss_crmeducativo.chats.listchats.ChatsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetAllChats;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetChats;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetChatsGroups;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetContacts;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetDatosChatAndUpdatePersonaGrupo;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetGroups;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetListFilterGroups;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetSenderInformation;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetUsuario;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.utils.UtilsAppMessenger;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.ios.IosEmojiProvider;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatsActivity extends BaseActivity<Chatview, ChatPresenter> implements Chatview, LifecycleImpl.LifecycleListener {

    public static String ID_SENDER = "ChatsActivity.idsender";


    String TAG = ChatsActivity.class.getSimpleName();
    /* @BindView(R2.id.url_image)
     CircleImageView urlImage;*/
    /*@BindView(R2.id.text_app)
    TextView textApp;*/
   /* @BindView(R2.id.content)
    ConstraintLayout content;*/
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.pager)
    ViewPager pager;
    @BindView(R2.id.executive)
    FloatingActionButton executive;
    @BindView(R2.id.student)
    FloatingActionButton student;
    @BindView(R2.id.teacher)
    FloatingActionButton teacher;
    @BindView(R2.id.filter)
    FloatingActionMenu filter;
    @BindView(R2.id.editText_search)
    EditText editTextSearch;
    @BindView(R2.id.toolbar_search)
    Toolbar toolbarSearch;
    @BindView(R2.id.appBar_search)
    AppBarLayout appBarSearch;
    @BindView(R2.id.layout_appbar_search)
    LinearLayout layoutAppbarSearch;
    @BindView(R2.id.appBar)
    AppBarLayout appBar;
    private ViewpagerAdapter fragmentAdapter;
    private float positionFromRight = 2;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ChatPresenter getPresenter() {
        ChatRepository chatRepository = new ChatRepository(new ChatDataRemoteSource( UtilServidor.getInstance(),InjectorUtils.provideParametrosDisenioDao()), new ChatDataLocalSource(InjectorUtils.provideCursoDao(), InjectorUtils.provideParametrosDisenioDao()));
        presenter = new ChatPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetSenderInformation(chatRepository)
                , new GetChats(chatRepository), new GetContacts(chatRepository), new GetGroups(chatRepository), new GetListFilterGroups(chatRepository)
                , new GetChatsGroups(chatRepository),
                new GetUsuario(chatRepository),
                new GetAllChats(chatRepository),
                new GetDatosChatAndUpdatePersonaGrupo(chatRepository),
                new UseCaseSincronizar(chatRepository));
        return presenter;
    }

    @Override
    protected Chatview getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_chats);
        ButterKnife.bind(this);
        initSearchBar();
        initViewPager();
        setSupportActionBar(toolbar);
        onclick();
        initTextListener();
        SetupToken();
    }

    private void SetupToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        InstanceIdResult instanceIdResult = task.getResult();
                        // Get new Instance ID token
                        String token = instanceIdResult!=null ? instanceIdResult.getToken():null;

                        Log.d(TAG,"token: " + token);

                    }
                });
    }

    /**
     * Initialize searchBar.
     */
    private void initSearchBar() {
        if (toolbarSearch != null) {
            toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_plomo_24dp);
            layoutAppbarSearch.setVisibility(View.GONE);
            toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSearchBar(positionFromRight);
                }
            });
        }
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
            animator = io.codetail.animation.ViewAnimationUtils
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
                    editTextSearch.setText("");
                    UtilsAppMessenger.hideKeyBoard(editTextSearch);

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            animator.start();
        }catch (Exception e){
            e.printStackTrace();
            layoutAppbarSearch.setVisibility(View.GONE);
            editTextSearch.setText("");
            UtilsAppMessenger.hideKeyBoard(editTextSearch);
        }

        // Circular animation declaration end

        appBar.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appBar, "translationY", 0),
                ObjectAnimator.ofFloat(appBar, "alpha", 1),
                ObjectAnimator.ofFloat(pager, "translationY", 0)
        );
        set.setDuration(100).start();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(final View myView, int posFromRight, boolean containsOverflow, final boolean isShow) {

        int width = myView.getWidth();

        if (posFromRight > 0)
            width -= (posFromRight * getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2);
        if (containsOverflow)
            width -= getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, (float) width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float) width, 0);

        anim.setDuration((long) 220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if (isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();


    }

    private void initTextListener() {

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence query, int i, int i1, int i2) {
                if (!query.toString().isEmpty()) {
                    presenter.search(query.toString());
                } else {
                    presenter.setListOld();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.showListFilter(ContactUi.TypeContact.TEACHER);

            }
        });
        executive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.showListFilter(ContactUi.TypeContact.EXECUTIVE);

            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.showListFilter(ContactUi.TypeContact.STUDENT);

            }
        });



    }


    private void initViewPager() {
        fragmentAdapter = new ViewpagerAdapter(getSupportFragmentManager(), 1, this);
        fragmentAdapter.addFragment(new ChatsFragment(), "Chats");
        fragmentAdapter.addFragment(new ContactsFragment(), "Contactos");
        fragmentAdapter.addFragment(new GroupsFragment(), "Grupos");
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(fragmentAdapter);
        //pager.setPagingEnabled(true);
        tabLayout.setupWithViewPager(pager);
        setCustomFont(tabLayout);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                hideSearchBar(positionFromRight);
                if (presenter != null)
                    presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    public void setCustomFont(TabLayout tabLayout) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    //  ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/SF-Pro-Display-Regular.otf"));
                    ((TextView) tabViewChild).setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    ((TextView) tabViewChild).setAllCaps(false);
                }
            }
        }
    }

    private void onclick() {
        /*back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentSearch.setVisibility(View.GONE);
                appBarLayout.setVisibility(View.VISIBLE);
                tab.setVisibility(View.VISIBLE);
                textSearch.setText("");
                presenter.setListOld();
            }
        });

        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.valideFragmentSearch();

            }
        });
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearch.setText("");
            }
        });

        textSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickTextSearch();
            }
        });*/


    }


    @Override
    public void showSearchDefault() {
        //appBarLayout.setVisibility(View.GONE);
        //tab.setVisibility(View.GONE);
    }

    @Override
    public void setListFilterGroups(List<Object> filters, List<Object> objectListGroups) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SearchGroupActivity.LIST_FILTERS, (Serializable) filters);
        bundle.putSerializable(SearchGroupActivity.LIST_GROUPS, (Serializable) objectListGroups);
        Intent intent = new Intent(getActivity(), SearchGroupActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void hideSearch() {
        //appBarLayout.setVisibility(View.VISIBLE);
        //tab.setVisibility(View.VISIBLE);
    }

    @Override
    public void showChatPersonal(int personaId, int personaExternaId, String name, String imageRec) {
        ChatActivity.start(this, personaId, personaExternaId, name, imageRec);
    }

    @Override
    public void showChatGrupal(int personaId, ChatUi chatUi) {
        List<Long> docenteId = chatUi.getDocenteId();
        int cargaAcademicaId = chatUi.getCargaAcademicaId();
        int cargaCursoId = chatUi.getCargaCursoId();
        String grupoEquipoId = chatUi.getGrupoEquipoId();
        int tipo_sala = 0,  nivel_sala=0;
        if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.ACADEMIC
                && chatUi.getTypePerson()== ChatUi.TypePerson.TEACHERS){
            tipo_sala = ChatGrupalActivity.TIPO_CLASSROON;
            nivel_sala = ChatGrupalActivity.NIVEL_GENERAL;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.ACADEMIC
                && chatUi.getTypePerson()== ChatUi.TypePerson.PARENTS){
            tipo_sala = ChatGrupalActivity.TIPO_CLASSROON;
            nivel_sala = ChatGrupalActivity.NIVEL_PADRES;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.ACADEMIC
                && chatUi.getTypePerson()== ChatUi.TypePerson.STUDENTS){
            tipo_sala = ChatGrupalActivity.TIPO_CLASSROON;
            nivel_sala = ChatGrupalActivity.NIVEL_ALUMNO;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.COURSE
                && chatUi.getTypePerson()== ChatUi.TypePerson.TEACHERS){
            tipo_sala = ChatGrupalActivity.TIPO_COURSE;
            nivel_sala = ChatGrupalActivity.NIVEL_GENERAL;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.COURSE
                && chatUi.getTypePerson()== ChatUi.TypePerson.PARENTS){
            tipo_sala = ChatGrupalActivity.TIPO_COURSE;
            nivel_sala = ChatGrupalActivity.NIVEL_PADRES;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.COURSE
                && chatUi.getTypePerson()== ChatUi.TypePerson.STUDENTS){
            tipo_sala = ChatGrupalActivity.TIPO_COURSE;
            nivel_sala = ChatGrupalActivity.NIVEL_ALUMNO;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.TEAM
                && chatUi.getTypePerson()== ChatUi.TypePerson.TEACHERS){
            tipo_sala = ChatGrupalActivity.TIPO_TEAM;
            nivel_sala = ChatGrupalActivity.NIVEL_GENERAL;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.TEAM
                && chatUi.getTypePerson()== ChatUi.TypePerson.PARENTS){
            tipo_sala = ChatGrupalActivity.TIPO_TEAM;
            nivel_sala = ChatGrupalActivity.NIVEL_PADRES;
        }else if(chatUi.getTypeGroup() ==  ChatUi.TypeGroup.TEAM
                && chatUi.getTypePerson()== ChatUi.TypePerson.STUDENTS){
            tipo_sala = ChatGrupalActivity.TIPO_TEAM;
            nivel_sala = ChatGrupalActivity.NIVEL_ALUMNO;
        }

        ChatGrupalActivity.start(this,personaId,cargaCursoId,cargaAcademicaId,grupoEquipoId, docenteId, tipo_sala, nivel_sala);

    }

    @Override
    public void subscribeToTopic(int personaId) {
        FirebaseMessaging.getInstance().subscribeToTopic("appmessage_"+personaId);
    }


    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmojiManager.install(new IosEmojiProvider());
        AXEmojiManager.install(this,new AXIOSEmojiProvider(this));
    }

    @Override
    public void showHeader(String urlimage) {

     /*   Glide.with(getActivity().getApplicationContext())
                .load(urlimage)
                .apply(Utils.getGlideRequestOptions())
                .into(urlImage);*/
    }

    @Override
    public void hideButtonContact() {
        filter.setVisibility(View.GONE);
    }

    @Override
    public void showButtonContact() {
        filter.setVisibility(View.VISIBLE);
    }


    @Override
    public void sendListContacts(List<Object> objects, int typeInt) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SearchContacsActivity.LIST, (Serializable) objects);
        bundle.putInt(SearchContacsActivity.TYPE, typeInt);
        Intent intent = new Intent(getActivity(), SearchContacsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onChildsFragmentViewCreated() {
        Log.d(TAG, "onChildsFragmentViewCreated");
        presenter.onChildsFragmentViewCreated();
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {
        Log.d(TAG, "onFragmentViewCreated: " + f.getClass().getSimpleName());
    }

    @Override
    public void onFragmentResumed(Fragment f) {
        Log.d(TAG, "onFragmentResumed: " + f.getClass().getSimpleName());
        if (f instanceof ChatsFragment) {
            presenter.onResumenChatsList();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if (f instanceof ChatsFragment) {
            presenter.onChatsFragmentViewDestroyed();
        }
        if (f instanceof ContactsFragment) {
            presenter.onContactsFragmentViewDestroyed();
        }
        if (f instanceof GroupsFragment) {
            presenter.onGroupsFragmentViewDestroyed();
        }
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {

        if (f instanceof ChatsFragment) {
            presenter.attachView((ChatsFragment) f);
            presenter.activityChatsFragment();
        }
        if (f instanceof ContactsFragment) {
            presenter.attachView((ContactsFragment) f);
            presenter.activityCreatedContactsFragment();
        }
        if (f instanceof GroupsFragment) {
            presenter.attachView((GroupsFragment) f);
            presenter.activityGroupsFragment();
        }
        if (f instanceof ViewPagerItemListener) {
            ViewPagerItemListener<ChatPresenter> viewPagerItemFragment = (ViewPagerItemListener<ChatPresenter>) f;
            viewPagerItemFragment.onAttach(presenter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            showSearchBar(positionFromRight);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // if the searchToolBar is visible, hide it
        // otherwise, do parent onBackPressed method
        if (layoutAppbarSearch.getVisibility() == View.VISIBLE)
            hideSearchBar(positionFromRight);
        else
            super.onBackPressed();

    }

    /**
     * to show the searchAppBarLayout and hide the mainAppBar with animation.
     *
     * @param positionFromRight
     */
    private void showSearchBar(float positionFromRight) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appBar, "translationY", -tabLayout.getHeight()),
                ObjectAnimator.ofFloat(pager, "translationY", -tabLayout.getHeight()),
                ObjectAnimator.ofFloat(appBar, "alpha", 0)
        );
        set.setDuration(100).addListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                appBar.setVisibility(View.GONE);
                editTextSearch.requestFocus();
                UtilsAppMessenger.showKeyBoard(editTextSearch);
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
        int cx = toolbar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48)* (0.5f + positionFromRight));
        // start y-index for circular animation
        int cy = (toolbar.getTop() + toolbar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, toolbar.getWidth() - cx);
        int dy = Math.max(cy, toolbar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        final Animator animator;
        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(layoutAppbarSearch, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        layoutAppbarSearch.setVisibility(View.VISIBLE);
        animator.start();
        // Circular animation declaration end
    }

}
