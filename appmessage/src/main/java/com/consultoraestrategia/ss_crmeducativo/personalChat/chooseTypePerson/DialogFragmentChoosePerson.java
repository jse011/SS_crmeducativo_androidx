package com.consultoraestrategia.ss_crmeducativo.personalChat.chooseTypePerson;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.ChatGrupalActivity;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.List;

public class DialogFragmentChoosePerson extends DialogFragment {
    String TAG= DialogFragmentChoosePerson.class.getSimpleName();

    RadioGroup radioGroup;
    TextView btnOk;

    SendDataChatBundle.TypePerson typePerson;


    public static DialogFragmentChoosePerson newInstance(Bundle bundle) {
        DialogFragmentChoosePerson fragment = new DialogFragmentChoosePerson();
//        fragment.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.typePerson=SendDataChatBundle.TypePerson.TEACHERS;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup=(RadioGroup)view.findViewById(R.id.group);
        btnOk=(TextView)view.findViewById(R.id.btnOk);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.btnTeacher){
                    typePerson=SendDataChatBundle.TypePerson.TEACHERS;
                    //Log.d(TAG, " click "+ PERSONS_TEACHER);
                }else if (checkedId == R.id.btnStudent){
                    typePerson=SendDataChatBundle.TypePerson.STUDENTS;
                }else{
                    typePerson=SendDataChatBundle.TypePerson.PARENTS;
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " typePerson "+ typePerson);
                SendDataChatBundle sendDataChatBundle=SendDataChatBundle.clone( getArguments());

                List<Long> docenteId = sendDataChatBundle.getDocenteId();
                int cargaAcademicaId = sendDataChatBundle.getCargaAcademicaId();
                int cargaCursoId = sendDataChatBundle.getCargaCursoId();
                String grupoEquipoId = sendDataChatBundle.getGrupoEquipoId();
                int personaId = sendDataChatBundle.getSenderId();

                if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.ACADEMIC
                        && typePerson== SendDataChatBundle.TypePerson.TEACHERS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_CLASSROON, ChatGrupalActivity.NIVEL_GENERAL);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.ACADEMIC
                        && typePerson== SendDataChatBundle.TypePerson.PARENTS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_CLASSROON, ChatGrupalActivity.NIVEL_PADRES);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.ACADEMIC
                        && typePerson== SendDataChatBundle.TypePerson.STUDENTS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_CLASSROON, ChatGrupalActivity.NIVEL_ALUMNO);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.COURSE
                        && typePerson== SendDataChatBundle.TypePerson.TEACHERS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_COURSE, ChatGrupalActivity.NIVEL_GENERAL);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.COURSE
                        && typePerson== SendDataChatBundle.TypePerson.PARENTS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_COURSE, ChatGrupalActivity.NIVEL_PADRES);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.COURSE
                        && typePerson== SendDataChatBundle.TypePerson.STUDENTS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_COURSE, ChatGrupalActivity.NIVEL_ALUMNO);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.TEAM
                        && typePerson== SendDataChatBundle.TypePerson.TEACHERS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_TEAM, ChatGrupalActivity.NIVEL_GENERAL);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.TEAM
                        && typePerson== SendDataChatBundle.TypePerson.PARENTS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_TEAM, ChatGrupalActivity.NIVEL_PADRES);
                }else if(sendDataChatBundle.getTypeGroup() ==  SendDataChatBundle.TypeGroup.TEAM
                        && typePerson== SendDataChatBundle.TypePerson.STUDENTS){
                    ChatGrupalActivity.start(btnOk.getContext(), personaId, cargaCursoId, cargaAcademicaId, grupoEquipoId, docenteId, ChatGrupalActivity.TIPO_TEAM, ChatGrupalActivity.NIVEL_ALUMNO);
                }


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_person, container, false);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
