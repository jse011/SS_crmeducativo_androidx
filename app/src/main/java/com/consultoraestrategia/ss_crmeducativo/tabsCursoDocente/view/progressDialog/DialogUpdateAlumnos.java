package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.progressDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.services.util.ImportarCountDownTimer;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DialogUpdateAlumnos extends DialogFragment implements ImportarCountDownTimer.ImportarCountDownTimerCallback, View.OnKeyListener {

    @BindView(R.id.imagen)
    ImageView imagen;
    Unbinder unbinder;
    @BindView(R.id.pgr_progresshorizontal)
    ProgressBar pgrProgresshorizontal;
    @BindView(R.id.text_progress)
    TextView textProgress;
    private ImportarCountDownTimer importarCountDownTimer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_alumnos, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupImageProgress();
        setupProgress();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListenerKey();
    }

    private void setupListenerKey() {
        if(getView()!=null){
            getView().setOnKeyListener(this);
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
        }
    }

    private void setupProgress() {
        final int maxSecondsInMillis = 50000;
        final int countInterval = 5000;
        importarCountDownTimer = new ImportarCountDownTimer(maxSecondsInMillis, countInterval, this);
        importarCountDownTimer.start();
    }

    private void setupImageProgress() {
        Glide.with(this)
                .asGif()
                .load(Uri.parse("file:///android_asset/importar_data.gif"))
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_error_outline_black))
                .into(imagen);
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (importarCountDownTimer != null) importarCountDownTimer.onDestroy();
    }


    @Override
    public void onImportarCountDownTimerCount(int counter) {
        String[] progressMessages = getResources().getStringArray(R.array.activity_login_progress_messages_array);
        String[] progressMessagesSlowConnection = getResources().getStringArray(R.array.activity_login_progress_slow_connection_messages_array);
        int slowConnectionSize = progressMessagesSlowConnection.length;
        int prgsMssgsSize = progressMessages.length;
        if (counter % 2 == 0) {//CADA 2 SEGUNDOS, ACTUALIZAR LA VISTA.
            int position = counter / 2;
            String message = "";
            if (position < prgsMssgsSize) {
                message = progressMessages[position];
            } else {
                int randomNum  = (int) ((Math.random() * slowConnectionSize));
                // int randomNum = ThreadLocalRandom.current().nextInt(0, slowConnectionSize);
                message = progressMessagesSlowConnection[randomNum];
            }
            textProgress.setText(message);
        }
    }

    @Override
    public void onImportarCountDownTimerFinish() {
        showFinalMessage(getResources().getString(R.string.unknown_error));
    }

    @Override
    public void onImportarCountDownProgress(int progress) {
        pgrProgresshorizontal.setProgress(progress);
    }

    public void showFinalMessage(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        TextView text = new TextView(getActivity());
        text.setPadding(20, 20, 20, 0);
        text.setText(message);
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle(R.string.dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        DialogUpdateAlumnos.this.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onKey( View v, int keyCode, KeyEvent event )
    {
        if( keyCode == KeyEvent.KEYCODE_BACK )
        {
            return true;
        }
        return false;
    }

}
