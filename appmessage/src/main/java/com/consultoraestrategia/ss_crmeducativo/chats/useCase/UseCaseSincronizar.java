package com.consultoraestrategia.ss_crmeducativo.chats.useCase;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosContacto;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class UseCaseSincronizar {

    private ChatRepository repository;

    public UseCaseSincronizar(ChatRepository repository) {
        this.repository = repository;
    }

    public RetrofitCancel execute(final Callback callback) {
        return repository.sincronizarInformacion(new ChatDataSource.SuccessCallback() {
            @Override
            public void onLoad(boolean success) {
                callback.onLoad(success);
            }
        });
    }

    public interface Callback{
        void onLoad(boolean success);
    }


}
