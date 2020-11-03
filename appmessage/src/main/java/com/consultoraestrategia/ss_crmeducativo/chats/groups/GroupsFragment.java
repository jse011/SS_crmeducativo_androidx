package com.consultoraestrategia.ss_crmeducativo.chats.groups;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewPagerItemListener;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.ChatGrupalActivity;
import com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups.GroupAdapter;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupsFragment extends Fragment implements GroupsView, ViewPagerItemListener<ChatPresenter>, ChatListener {

    @BindView(R2.id.recy_groups)
    RecyclerView recyGroups;
    @BindView(R2.id.progress)
    ProgressBar progress;
    private ChatPresenter presenter;
    private String TAG = GroupsFragment.class.getSimpleName();
    private GroupAdapter groupAdapter;
    private Unbinder unbinder;


    @Override
    public void onAttach(ChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        Log.e(TAG, "onCreateView");
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        initAdapter();

    }

    private void initAdapter() {
        groupAdapter = new GroupAdapter(new ArrayList<Object>(), this);
        recyGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        recyGroups.setAdapter(groupAdapter);
    }


    @Override
    public void clickChat(Object object) {
        if (object instanceof GroupUi) {
            GroupUi groupUi = (GroupUi) object;
            List<Long> docenteId = groupUi.getDocenteId();
            int cargaAcademicaId = groupUi.getCargaAcademicaId();
            int cargaCursoId = groupUi.getCargaCursoId();
            String grupoEquipoId = groupUi.getGrupoEquipoId();
            int personaId = groupUi.getIdSender();
            int nivel;
            switch (groupUi.getGrupo()){
                case Alumno:
                    nivel = ChatGrupalActivity.NIVEL_ALUMNO;
                    break;
                case Padre:
                    nivel = ChatGrupalActivity.NIVEL_PADRES;
                    break;
                case Todos:
                    nivel = ChatGrupalActivity.NIVEL_GENERAL;
                    break;
                default:
                    nivel = ChatGrupalActivity.NIVEL_GENERAL;
                    break;
            }

            int tipo = 0;
            switch (groupUi.getType()) {
                case ACADEMIC:
                    tipo = ChatGrupalActivity.TIPO_CLASSROON;
                    break;
                case COURSE:
                    tipo = ChatGrupalActivity.TIPO_COURSE;
                    break;
                case TEAM:
                    tipo = ChatGrupalActivity.TIPO_TEAM;
                    nivel = ChatGrupalActivity.NIVEL_ALUMNO;
                    break;

            }

            ChatGrupalActivity.start(getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, tipo, nivel);

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
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
