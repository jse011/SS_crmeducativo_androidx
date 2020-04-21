package com.consultoraestrategia.ss_crmeducativo.chats.adapters.contacs;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class ContacsAdapter extends  RecyclerView.Adapter  {

    List<Object>objects;
    List<Object>objectsOld;
    final int CONTACT=0, SUBCONTACT=1, TYPE=2;
    ChatListener chatListener;

    public ContacsAdapter(List<Object> objects, ChatListener chatListener) {
        this.objects = objects;
        this.chatListener=chatListener;
        this.objectsOld= null;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case SUBCONTACT:
                return new SubContactHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subcontact, viewGroup, false));
            case TYPE:
                return new TypeHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_type_contact, viewGroup, false));
            default:
                return new ContacsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Object object=objects.get(i);
        if(viewHolder instanceof SubContactHolder){
            SubContactHolder subContactHolder=(SubContactHolder)viewHolder;
            subContactHolder.bind((ContactUi) object, chatListener);

        }
        else if(viewHolder instanceof ContacsHolder){
            ContacsHolder  contacsHolder=(ContacsHolder)viewHolder;
            contacsHolder.bind((ContactUi) object, chatListener);
        }
        else {
            TypeHolder typeHolder=(TypeHolder)viewHolder;
            typeHolder.bind((String)object);
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object=objects.get(position);
        if(object instanceof ContactUi){
            ContactUi contactUi= (ContactUi)objects.get(position);
            if(contactUi.getType().equals(ContactUi.Type.SUBCONTACT))return SUBCONTACT;
            else return CONTACT;
        }
        else return TYPE;

    }
    public void setList(List<Object>objects){
        if(objectsOld==null)objectsOld=objects;
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

    public void listOld() {
        if(objectsOld!=null)setList(objectsOld);
    }

    public void search(String toString) {
        HashSet<Object>objects= new LinkedHashSet<>();
        for(Object object : objectsOld){
            if(object instanceof ContactUi){
                ContactUi contactUi=(ContactUi)object;
                String nombres= contactUi.getName().toUpperCase();
                int position= nombres.indexOf(toString.toUpperCase());
                if(position!=-1){
                    if(contactUi.getType().equals(ContactUi.Type.CONTACT)){
                        objects.add(object);
                        objects.addAll(contactUi.getContactUiList());
                    }
                    else {
                        objects.add(contactUi.getContactUiPrincipal());
                        objects.add(object);
                    }

                }
            }
        }
        setList(new ArrayList<Object>(objects));

    }
}
