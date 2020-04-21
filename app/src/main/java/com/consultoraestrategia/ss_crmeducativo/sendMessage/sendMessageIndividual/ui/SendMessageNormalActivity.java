package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.SendMessageNormalPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.SendMessageNormalRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.local.SendMessageRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.remote.SendMessageRubroRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetDataImportantMessageUseCase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class SendMessageNormalActivity extends BaseSendMessageActivity {

    public static void launchSendMessageNormalActivity(Context context, List<Persona> personas, int cargaCursoId, String idRubro) {
        Intent intent = new Intent(context, SendMessageNormalActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable(EXTRA_PERSON_LIST, (Serializable) personas);
        extras.putInt(EXTRA_ID_CARGA_CURSO, cargaCursoId);
        extras.putString(EXTRA_ID_RUBRO, idRubro);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    @Override
    protected SendMessageBasePresenterImpl getPresenterImpl() {


        SendMessageBaseRepository repository = SendMessageBaseRepository.getInstance(
                new SendMessageBaseLocalDataSource(),
                new SendMessageBaseRemoteDataSource()
        );
        SendMessageNormalRepository repositoryNormal = SendMessageNormalRepository.getInstance(
                new SendMessageRubroLocalDataSource(),
                new SendMessageRubroRemoteDataSource()
        );

        return new SendMessageNormalPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersonasRelacionasUseCase(repository),
                new GetIntencionListUseCase(repository),
                (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE),
                new GetDataImportantMessageUseCase(repositoryNormal)

         );
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTxtTituloAccion("Mensaje Normal");
        hideAdjuntText(ViewGroup.GONE);

    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showSuccessSendMessageIndividual(String msj) {

    }
}
