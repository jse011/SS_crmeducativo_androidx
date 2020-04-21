package com.consultoraestrategia.ss_crmeducativo.util;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by irvinmarin on 17/08/2017.
 */

@SuppressLint("ValidFragment")
public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "SelectDateFragment";
    Calendar calendar;
    int diaSemana;
    OnDateSelectClickListener clickListener;

    public interface OnDateSelectClickListener {
        void onClickFechaSelect(String fecha, String dateSelect);
        void onClickFechaSelect(long timeInMillis);
    }

    @SuppressLint("ValidFragment")
    public SelectDateFragment(OnDateSelectClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        int mesReal = mm + 1;
//          int mesCorrect = mm + 1;
        String fecha = dd + "/" + mm + "/" + yy;
        String fechaVisible = dd + "/" + mesReal + "/" + yy;
        clickListener.onClickFechaSelect(fecha,fechaVisible);

        Calendar calendar = new GregorianCalendar(yy, mm, dd);
        clickListener.onClickFechaSelect(calendar.getTimeInMillis());

//
    }
}
