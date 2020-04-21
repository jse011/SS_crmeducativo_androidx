package com.consultoraestrategia.ss_crmeducativo.chats.groups;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.ChatGrupalActivity;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups.GroupAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;
import com.consultoraestrategia.ss_crmeducativo.personalChat.chooseTypePerson.DialogFragmentChoosePerson;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment implements GroupsView , ViewPagerItemListener<ChatPresenter>, ChatListener {

    private ChatPresenter presenter;
    private String TAG=GroupsFragment.class.getSimpleName();
    private RecyclerView recy_roups;
    private GroupAdapter groupAdapter;
    private ProgressBar progressBar;
    @Override
    public void onAttach(ChatPresenter presenter) {
        this.presenter=presenter;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        Log.e(TAG, "onCreateView");
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        recy_roups=(RecyclerView) getActivity().findViewById(R.id.recy_groups);
        progressBar=(ProgressBar) getActivity().findViewById(R.id.progress);
        initAdapter();


    }

    private void initAdapter() {
        groupAdapter = new GroupAdapter(new ArrayList<Object>(), this);
        recy_roups.setLayoutManager(new LinearLayoutManager(getContext()));
        recy_roups.setAdapter(groupAdapter);
    }


    @Override
    public void clickChat(Object object) {
        if(object instanceof GroupUi){
            GroupUi groupUi=(GroupUi)object;
            List<Long> docenteId = groupUi.getDocenteId();
            int cargaAcademicaId = groupUi.getCargaAcademicaId();
            int cargaCursoId = groupUi.getCargaCursoId();
            String grupoEquipoId = groupUi.getGrupoEquipoId();
            int personaId = groupUi.getIdSender();
            switch (groupUi.getType()){
                case ACADEMIC:
                    ChatGrupalActivity.start(getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_CLASSROON, ChatGrupalActivity.NIVEL_GENERAL);
                    break;
                case COURSE:
                    ChatGrupalActivity.start(getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_COURSE, ChatGrupalActivity.NIVEL_GENERAL);
                    break;
                default:
                    ChatGrupalActivity.start(getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_TEAM, ChatGrupalActivity.NIVEL_GENERAL);
                    break;
            }

            /*
            SendDataChatBundle sendDataChatBundle= new SendDataChatBundle();
            sendDataChatBundle.setSenderId(groupUi.getIdSender());
            sendDataChatBundle.setCargaAcademicaId(groupUi.getCargaAcademicaId());
            sendDataChatBundle.setCargaCursoId(groupUi.getCargaCursoId());
            sendDataChatBundle.setGrupoEquipoId(groupUi.getGrupoEquipoId());
            sendDataChatBundle.setDocenteId(groupUi.getDocenteId());
            sendDataChatBundle.setTypeChat(SendDataChatBundle.TypeChat.GROUP);;
            switch (groupUi.getType()){
                case ACADEMIC:
                    sendDataChatBundle.setTypeGroup(SendDataChatBundle.TypeGroup.ACADEMIC);
                    break;
                case COURSE:
                    sendDataChatBundle.setTypeGroup(SendDataChatBundle.TypeGroup.COURSE);
                    break;
                default:
                    sendDataChatBundle.setTypeGroup(SendDataChatBundle.TypeGroup.TEAM);
                    break;
            }
            DialogFragmentChoosePerson dialog =DialogFragmentChoosePerson.newInstance(sendDataChatBundle.getBundle());
            dialog.show(getFragmentManager(), TAG);*/

//            Intent intent= new Intent(getContext(), PersonalChatActivity.class);
//            intent.putExtras(sendDataChatBundle.getBundle());
//            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
      //  presenter.onResumGroupsList();
    }

    @Override
    public void setList(List<Object> objects) {
        groupAdapter.setList(objects);
    }



    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
