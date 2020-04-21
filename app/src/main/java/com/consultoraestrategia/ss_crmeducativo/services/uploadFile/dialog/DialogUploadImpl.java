package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;

public class DialogUploadImpl extends Dialog implements View.OnClickListener , DialogUpload {
    private Callback callback;

    public DialogUploadImpl(Context context) {
        super(context);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_uploadfile);
        setTitle("My Custom AdapterExample");
        Button hello = (Button) findViewById(R.id.hello);
        Button close = (Button) findViewById(R.id.close);
        hello.setEnabled(false);
        close.setEnabled(true);
        hello.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.hello){
            Toast.makeText(getContext(), "Hello, I'm Custom Alert AdapterExample", Toast.LENGTH_LONG).show();
        }else if(v.getId()==R.id.close){
            if(callback!=null)callback.onErrorDialogUpload();
            onHideDialogUpload();
        }
    }

    @Override
    public void onListenerDialogUpload(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onShowDialogUpload() {
        show();
    }

    @Override
    public void onHideDialogUpload() {
        cancel();
    }


}
