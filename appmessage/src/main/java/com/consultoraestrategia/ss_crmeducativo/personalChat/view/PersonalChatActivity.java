package com.consultoraestrategia.ss_crmeducativo.personalChat.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatsActivity;
import com.consultoraestrategia.ss_crmeducativo.personalChat.adapters.MessageAdapter;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataRemoteSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.presenter.PersonalChatPreImpl;
import com.consultoraestrategia.ss_crmeducativo.personalChat.presenter.PersonalChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.CreateGroupChat;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetChatReceiver;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetMessageBefore;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetMessageChat;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetPersonsOfGroups;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.SaveLastConexion;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.SaveSessionActive;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.SendMessage;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidateChatExistence;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidateChatExistenceGroup;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidatePersonExistence;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidatePersonGroupExistence;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalChatActivity extends BaseActivity<PersonalChatView, PersonalChatPresenter> implements PersonalChatView, SwipeRefreshLayout.OnRefreshListener{


//    @BindView(R.id.back)
     ImageView back;
//    @BindView(R.id.img_receiver)
    CircleImageView imgReceiver;
//    @BindView(R.id.receiver)
    TextView receiver;
//    @BindView(R.id.recy_msg)
    RecyclerView recyMsg;
//    @BindView(R.id.btnsend)
    FloatingActionButton btnsend;
//    @BindView(R.id.msg)
    SwipeRefreshLayout swipeContainer;
    EditText msg;
    TextView txtnotinternet;
    TextView again;
    TextView dateConexion;
    TextView  relations;
    ImageView conecct;
    TextView  txtmessage;
    TextView title_img;
    ImageView circle;
    private String TAG = PersonalChatActivity.class.getSimpleName();
    private MessageAdapter messageAdapter;


//    public static String ID_SENDER = "PersonalChatActivity.idsender";
//    public static String ID_RECEIVER = "PersonalChatActivity.idreceiver";


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected PersonalChatPresenter getPresenter() {
        PersonalChatRepository repository = new PersonalChatRepository(new PersonalChatDataRemoteSource(InjectorUtils.provideCursoDao(), InjectorUtils.provideParametrosDisenioDao()), new PersonalChatDataLocalSource(InjectorUtils.provideCursoDao()));
        presenter = new PersonalChatPreImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new ValidateChatExistence(repository)
                ,  new ValidatePersonExistence(repository), new GetMessageChat(repository), new GetChatReceiver(repository), new SendMessage(repository),
                new GetMessageBefore(repository), new SaveLastConexion(repository), new SaveSessionActive(repository), new ValidateChatExistenceGroup(repository),
                new ValidatePersonGroupExistence(repository), new CreateGroupChat(repository), new GetPersonsOfGroups(repository));
        return presenter;
    }

    @Override
    protected PersonalChatView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_personal_chat);
        back= (ImageView)getActivity().findViewById(R.id.back);
        imgReceiver= (CircleImageView)getActivity().findViewById(R.id.img_receiver);
        //btnsend= (FloatingActionButton)getActivity().findViewById(R.id.btnSend);
        receiver= (TextView) getActivity().findViewById(R.id.receiver);
        recyMsg=(RecyclerView)getActivity().findViewById(R.id.recy_msg);
        msg=(EditText) getActivity().findViewById(R.id.msg);
        swipeContainer=(SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_container);
        //txtnotinternet= (TextView) getActivity().findViewById(R.id.txtnotinternet);
        //again= (TextView) getActivity().findViewById(R.id.again);
        dateConexion=(TextView) getActivity().findViewById(R.id.date);
        relations=(TextView) getActivity().findViewById(R.id.relations);
        conecct=(ImageView)getActivity().findViewById(R.id.conecct);
        //txtmessage=(TextView)getActivity().findViewById(R.id.txtmessage);
        title_img=(TextView)getActivity().findViewById(R.id.title_img);
        circle=(ImageView)getActivity().findViewById(R.id.circle);
        initAdapter();
        initOnclicks();

    }

    private void initOnclicks() {
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createMessage(String.valueOf(msg.getText()));
                msg.setText("");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //presenter.onclikBack();
            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRefresh();
            }
        });
        swipeContainer.setOnRefreshListener(this);

    }
    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null)
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showMessageNotInternet() {
        //Toast.makeText(getActivity(), "No hay internet ", Toast.LENGTH_SHORT).show();
        txtnotinternet.setVisibility(View.VISIBLE);
        again.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessageNotInternet() {
        txtnotinternet.setVisibility(View.GONE);
        again.setVisibility(View.GONE);
        txtmessage.setVisibility(View.GONE);
        btnsend.setEnabled(true);
        msg.setEnabled(true);
    }

    @Override
    public void showNotInternet() {
        txtnotinternet.setVisibility(View.VISIBLE);
        txtmessage.setVisibility(View.VISIBLE);
        btnsend.setEnabled(false);
        msg.setEnabled(false);

    }

    @Override
    public void updateItemCreated(String messageId) {
        messageAdapter.updatePosition(messageId);
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
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view=  super.onCreateView(name, context, attrs);
        Log.d(TAG, "onCreateView");
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    public void initAdapter(){
        messageAdapter = new MessageAdapter(new ArrayList<Object>(), recyMsg);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyMsg.setLayoutManager(linearLayoutManager);
        recyMsg.setAdapter(messageAdapter);

    }

    @Override
    public void setListMessage(List<Object> messageUiList, int idSender) {

        messageAdapter.setList(messageUiList, idSender);
        recyMsg.scrollToPosition(messageUiList.size()-1);

    }

    @Override
    public void showHeader(ChatUi chatUi) {
        receiver.setText(chatUi.getNameReceiver());
        Log.d(TAG, "CONEXICON " +chatUi.getLastDateConexion() );
        title_img.setVisibility(View.GONE);
        circle.setVisibility(View.GONE);
        imgReceiver.setVisibility(View.VISIBLE);
        relations.setText(chatUi.getSubtitle());
        String url= chatUi.getUrl_image();
        Glide.with(getActivity().getApplicationContext())
                .load(url)
                .apply(Utils.getGlideRequestOptions())
                .into(imgReceiver);

        if(chatUi.isActive())
        {
            dateConexion.setVisibility(View.GONE);
            conecct.setVisibility(View.VISIBLE);
        }
        else {
            dateConexion.setText(chatUi.getLastDateConexion());
            if(chatUi.getLastDateConexion()==null) dateConexion.setVisibility(View.GONE);
            else   dateConexion.setVisibility(View.VISIBLE);
            conecct.setVisibility(View.GONE);
        }

    }
    @Override
    public void showHeaderGroup(ChatUi chatUi) {
       // receiver.setText(chatUi.getNameReceiver());
        title_img.setVisibility(View.VISIBLE);

        circle.setVisibility(View.VISIBLE);
        imgReceiver.setVisibility(View.GONE);
        try {
            switch (chatUi.getTypeGroup()){
                case ACADEMIC:
                    title_img.setText(chatUi.getUrl_image());
                    title_img.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.light_blank));

                    break;
                case COURSE:

                    String j="";
                    for(int i=0;chatUi.getUrl_image().length()>i; i++){
                        j=j+chatUi.getUrl_image().charAt(i);
                        if(i==2)break;
                    }
                    title_img.setText(j.toUpperCase());

                    title_img.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    ColorStateList csl2 = ColorStateList.valueOf(Color.parseColor(chatUi.getColor()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circle.setImageTintList(csl2);
                    }
                    break;
                default:
                    title_img.setVisibility(View.GONE);
                    circle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.group));
                    break;
            }
        }catch (Exception e){
            Log.d(getClass().getSimpleName(),"error "+ e.getMessage());
        }
        receiver.setText(chatUi.getNameReceiver());

    }

    @Override
    public void backToMainChat(int idSender) {
        Bundle bundle= new Bundle();
        bundle.putInt(ChatsActivity.ID_SENDER, idSender);

        Intent intent= new Intent(getApplicationContext(), ChatsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void closed() {
        finish();
    }

    @Override
    public void refreshList(List<Object> messageUis) {
        messageAdapter.addMessages(messageUis);
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    @Override
    public void onBackPressed() {
       //presenter.onclikBack();
        finish();
    }
}
