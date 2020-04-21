package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageBaseRepository;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.local.SendMessageBaseLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.remote.SendMessageBaseRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.BaseSendMessageActivity;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.SendMessageTareaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.SendMessageTareaRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.local.SendMessageTareaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.remote.SendMessageTareaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.io.Serializable;
import java.util.List;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class SendMessageTareaActivity extends BaseSendMessageActivity {

    public static final String EXTRA_TITULO = "SendMessageTareaActivity.titulo";
    public static final String EXTRA_DESCRIPCION = "SendMessageTareaActivity.descripcion";
    public static final String EXTRA_FECHA_ENTREGA = "SendMessageTareaActivity.fecha.entrega";
    public static final String EXTRA_TAREA_UI = "SendMessageTareaActivity.tareaUI";

    public static void launchSendMessageTareaActivity(Context context, List<Persona> personas, int cargaCursoId, TareasUI tareasUI, String tituloTarea, String descripcion, String fechaEntrega) {
        Intent intent = new Intent(context, SendMessageTareaActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable(EXTRA_PERSON_LIST, (Serializable) personas);
        extras.putInt(EXTRA_ID_CARGA_CURSO, cargaCursoId);
        extras.putString(EXTRA_TITULO, tituloTarea);
        extras.putString(EXTRA_DESCRIPCION, descripcion);
        extras.putString(EXTRA_FECHA_ENTREGA, fechaEntrega);
        extras.putParcelable(EXTRA_TAREA_UI, Parcels.wrap(tareasUI));
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    @Override
    protected SendMessageBasePresenterImpl getPresenterImpl() {
        SendMessageBaseRepository repository = SendMessageBaseRepository.getInstance(
                new SendMessageBaseLocalDataSource(),
                new SendMessageBaseRemoteDataSource()
        );
        SendMessageTareaRepository tareaRepository = SendMessageTareaRepository.getInstance(
                new SendMessageTareaLocalDataSource(),
                new SendMessageTareaRemoteDataSource()
        );
        return new SendMessageTareaPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersonasRelacionasUseCase(repository),
                new GetIntencionListUseCase(repository),
                (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE),
                new GetDataImportantMessageUseCase(tareaRepository)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTxtTituloAccion("Mensaje Tareas");
        hideAdjuntText(ViewGroup.GONE);
        hideBtnMessajePred(ViewGroup.GONE);
        hideIntencionLayout(ViewGroup.GONE);
        setDataToImputs(
                getIntent().getStringExtra(EXTRA_TITULO),
                getIntent().getStringExtra(EXTRA_DESCRIPCION),
                getIntent().getStringExtra(EXTRA_FECHA_ENTREGA)
        );
        presenter.setTareUIActual(getIntent().getParcelableExtra(EXTRA_TAREA_UI));
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showSuccessSendMessageIndividual(String msj) {

    }
}
