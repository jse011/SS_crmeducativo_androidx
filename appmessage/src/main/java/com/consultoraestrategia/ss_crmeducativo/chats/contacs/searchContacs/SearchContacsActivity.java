package com.consultoraestrategia.ss_crmeducativo.chats.contacs.searchContacs;

import android.content.Context;
import android.content.Intent;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.contacs.ContacsAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;
import com.consultoraestrategia.ss_crmeducativo.personalChat.view.PersonalChatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchContacsActivity extends AppCompatActivity implements ChatListener {


    private Toolbar toolbar;
    private ImageView back2;
    private ImageView back;
    private  ImageView ic_search;
    private AppBarLayout appBarLayout;
    private MaterialCardView contentSearch;
    private EditText textSearch;
    private ImageView ic_close;
    private ContacsAdapter contacsAdapter;
    private RecyclerView recy_contacts;
    private TextView title;

    public static String LIST="SearchContacsActivity.list";
    public static String TYPE="SearchContacsActivity.type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contacs);
        back2=(ImageView)findViewById(R.id.back2);
        back=(ImageView)findViewById(R.id.back);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        ic_search=(ImageView)findViewById(R.id.ic_search);
        appBarLayout=(AppBarLayout)findViewById(R.id.appBarLayout);
        contentSearch=(MaterialCardView)findViewById(R.id.contentSearch);
        textSearch=(EditText)findViewById(R.id.textSearch);
        ic_close=(ImageView)findViewById(R.id.ic_close);
        recy_contacts=(RecyclerView)findViewById(R.id.recy_contacts);
        title=(TextView)findViewById(R.id.title);
        setSupportActionBar(toolbar);
        setadapter();
        initListener();
        setlist();
        setTypeList();
    }




    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearch.setText("");
                appBarLayout.setVisibility(View.VISIBLE);
                contentSearch.setVisibility(View.GONE);
                contacsAdapter.listOld();
            }
        });
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setVisibility(View.GONE);
                contentSearch.setVisibility(View.VISIBLE);
            }
        });
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearch.setText("");
                contacsAdapter.listOld();
            }
        });
        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    ic_close.setVisibility(View.GONE);
                    contacsAdapter.listOld();
                }else {
                    ic_close.setVisibility(View.VISIBLE);
                    contacsAdapter.search(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setadapter() {
        contacsAdapter = new ContacsAdapter(new ArrayList<Object>(), this);
        recy_contacts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recy_contacts.setAdapter(contacsAdapter);
    }
    private void setlist() {

        List<Object>objects=(List<Object>)getIntent().getExtras().getSerializable(SearchContacsActivity.LIST) ;
        Log.d(getClass().getSimpleName(), "objects contacts size "+ objects.size());
        contacsAdapter.setList(objects);
    }
    private void setTypeList() {
        int type= getIntent().getExtras().getInt(SearchContacsActivity.TYPE) ;
        Log.d(getClass().getSimpleName(), "type "+ type);
        switch (type){
            case 1:
                title.setText("Estudiantes");
                break;
            case 2:
                title.setText("Directivos");
                break;
            default:
                title.setText("Docentes");
                break;
        }
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void clickChat(Object object) {
        if(object instanceof ContactUi){
            ContactUi contactUi=(ContactUi)object;
            SendDataChatBundle sendDataChatBundle=new SendDataChatBundle();
            sendDataChatBundle.setSenderId(contactUi.getOriginIdPerson());
            //sendDataChatBundle.setReceiverId(String.valueOf( contactUi.getIdPerson()));
            sendDataChatBundle.setTypeChat(SendDataChatBundle.TypeChat.PERSONAL);
            Intent intent= new Intent(getApplicationContext(), PersonalChatActivity.class);
            intent.putExtras(sendDataChatBundle.getBundle());
            startActivity(intent);
        }
    }
}
