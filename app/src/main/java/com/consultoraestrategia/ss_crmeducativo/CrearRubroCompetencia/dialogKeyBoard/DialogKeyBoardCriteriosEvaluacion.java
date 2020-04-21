package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialogKeyBoard;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;

public class DialogKeyBoardCriteriosEvaluacion extends Dialogkeyboard {

    public static DialogKeyBoardCriteriosEvaluacion newInstance(String descripcion) {
        Bundle bundle = new Bundle();
        bundle.putString(DESCRIPCION, descripcion);
        DialogKeyBoardCriteriosEvaluacion dialogkeyboard = new DialogKeyBoardCriteriosEvaluacion();
        dialogkeyboard.setArguments(bundle);
        return dialogkeyboard;
    }
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        txtTitulo.setText("Criterio evaluación");
    }
}
