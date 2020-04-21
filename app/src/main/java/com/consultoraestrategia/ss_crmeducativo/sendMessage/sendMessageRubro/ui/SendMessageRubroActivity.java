package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.telephony.TelephonyManager;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageBaseRepository;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.local.SendMessageBaseLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.remote.SendMessageBaseRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.BaseSendMessageActivity;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.SendMessageRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.SendMessageRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.local.SendMessageRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.remote.SendMessageRubroRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class SendMessageRubroActivity extends BaseSendMessageActivity {

    public static void launchSendMessageRubroActivity(Context context, List<Persona> personas, int cargaCursoId, String rubroId, int tipoLayoutMensaje, int programaEducativoId) {
        Intent intent = new Intent(context, SendMessageRubroActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable(EXTRA_PERSON_LIST, (Serializable) personas);
        extras.putInt(EXTRA_ID_CARGA_CURSO, cargaCursoId);
        extras.putString(EXTRA_ID_RUBRO, rubroId);
        extras.putInt(EXTRA_TIPO_LAYOUT_MENSAJE, tipoLayoutMensaje);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    protected SendMessageBasePresenterImpl getPresenterImpl() {


        SendMessageBaseRepository repository = SendMessageBaseRepository.getInstance(
                new SendMessageBaseLocalDataSource(),
                new SendMessageBaseRemoteDataSource()
        );
        SendMessageRubroRepository repositoryRubro = SendMessageRubroRepository.getInstance(
                new SendMessageRubroLocalDataSource(),
                new SendMessageRubroRemoteDataSource()
        );

        return new SendMessageRubroPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersonasRelacionasUseCase(repository),
                new GetIntencionListUseCase(repository),
                (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE),
                new GetDataImportantMessageUseCase(repositoryRubro)

        );
    }

    @Override
    public void showSuccessSendMessageIndividual(String msj) {
        Snackbar.make(getRootLayout(), msj, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    protected void setContentView() {
        super.setContentView();
    }

    @Override
    protected void onDestroy() {
        CallService.jobServiceExportarTipos(this, TipoExportacion.MENSAJE);
        SimpleSyncIntenService.start(this, 0);
        super.onDestroy();
    }
}
