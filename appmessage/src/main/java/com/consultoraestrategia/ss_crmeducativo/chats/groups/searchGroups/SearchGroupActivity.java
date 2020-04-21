package com.consultoraestrategia.ss_crmeducativo.chats.groups.searchGroups;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.CategoryUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.groups.searchGroups.adapters.FilterGroupAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

public class SearchGroupActivity extends AppCompatActivity  implements ChatListener {


    EditText textSearch;
    ImageView back;
    ImageView ic_close;
    RecyclerView recy;
    LinearLayout filter;
    TextView nameFilter;
    ImageView closeFilter;
    boolean filterSelected;

    public static String LIST_GROUPS= "SearchGroupActivity.listGroups";
    public static String LIST_FILTERS= "SearchGroupActivity.listFilters";
    FilterGroupAdapter filterGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group);
        textSearch= (EditText)findViewById(R.id.textSearch);
        back=(ImageView)findViewById(R.id.back);
        ic_close=(ImageView)findViewById(R.id.ic_close);
        recy=(RecyclerView)findViewById(R.id.recy);
        filter=(LinearLayout)findViewById(R.id.filter);
        nameFilter=(TextView)findViewById(R.id.nameFilter);
        closeFilter=(ImageView)findViewById(R.id.closeFilter);
        setadapter();
        setlist();
        filterSelected=false;

    }

    private void setlist() {
        final List<Object>objectListFilters=(List<Object>)getIntent().getExtras().getSerializable(LIST_FILTERS);
        final List<Object>objectListGroups=(List<Object>)getIntent().getExtras().getSerializable(LIST_GROUPS);

        Log.d(getClass().getSimpleName(), "setlist filters"+ objectListFilters.size());
        Log.d(getClass().getSimpleName(), "setlist groups"+ objectListGroups.size());

        filterGroupAdapter.setListForFilter(objectListGroups);
        filterGroupAdapter.setList(objectListFilters);

        closeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterSelected=false;
                filter.setVisibility(View.GONE);
                filterGroupAdapter.setList(objectListFilters);
                filterGroupAdapter.setListForFilter(objectListGroups);

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
                    if(filterSelected) filterGroupAdapter.setlistOld();
                    else    filterGroupAdapter.setList(objectListFilters);
                }else {
                    ic_close.setVisibility(View.VISIBLE);
                    filterGroupAdapter.search(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearch.setText("");
                if(filterSelected) filterGroupAdapter.setlistOld();
                else    filterGroupAdapter.setList(objectListFilters);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setadapter() {
        filterGroupAdapter = new FilterGroupAdapter(new ArrayList<Object>(), this);
        recy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recy.setAdapter(filterGroupAdapter);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void clickChat(Object object) {

        if(object instanceof CategoryUi){
            filterSelected=true;
            CategoryUi categoryUi=(CategoryUi)object;
            filterGroupAdapter.filterXCatefory(categoryUi);

            filter.setVisibility(View.VISIBLE);
            nameFilter.setText(categoryUi.getName());
        }
        else if(object instanceof GroupUi){

        }
    }
}
