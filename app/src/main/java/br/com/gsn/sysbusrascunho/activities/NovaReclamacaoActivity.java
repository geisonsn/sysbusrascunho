package br.com.gsn.sysbusrascunho.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import br.com.gsn.sysbusrascunho.R;

/**
 * Created by geison on 07/05/15.
 */
public class NovaReclamacaoActivity extends Activity {

    private EditText dataOcorrencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_reclamacao);

        dataOcorrencia = (EditText) findViewById(R.id.dataOcorrencia);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showCalendar(View view) {

        Calendar calendar = Calendar.getInstance();

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        DatePickerDialog dialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dataOcorrencia.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }
        }, ano, mes, dia);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        dialog.show();
    }
}
