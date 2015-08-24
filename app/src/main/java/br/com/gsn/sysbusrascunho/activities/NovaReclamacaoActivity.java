package br.com.gsn.sysbusrascunho.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.LinhaDTO;
import br.com.gsn.sysbusrascunho.tasks.AutoCompleteLinhaTask;
import br.com.gsn.sysbusrascunho.tasks.OrigemReclamacaoTask;
import br.com.gsn.sysbusrascunho.util.Dates;

/**
 * Created by geison on 07/05/15.
 */
public class NovaReclamacaoActivity extends Activity {

    private static EditText dataOcorrencia;
    private EditText placa;
    private Spinner objetoReclamado, origemReclamacao;
    private AutoCompleteTextView linha;
    private ArrayList<LinhaDTO> linhas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_reclamacao);

        dataOcorrencia = (EditText) findViewById(R.id.dataOcorrencia);
        placa = (EditText) findViewById(R.id.placa);
        objetoReclamado = (Spinner) findViewById(R.id.objetoReclamado);

        objetoReclamado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new OrigemReclamacaoTask(NovaReclamacaoActivity.this).execute(objetoReclamado.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        origemReclamacao = (Spinner) findViewById(R.id.origemReclamacao);

        //objetoReclamado.setAdapter(new Spin);
//        linha = (Spinner) findViewById(R.id.linha);
//        linhas.addAll(carregarLinhas());

        linha = (AutoCompleteTextView) findViewById(R.id.linha);
        linha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new AutoCompleteLinhaTask(NovaReclamacaoActivity.this).execute(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        linha.setAdapter(new LinhaArrayAdapter(this, linhas));
//        linha.setAdapter(new ArrayAdapter<LinhaDTO>(this, android.R.layout.simple_list_item_1, linhas));

        linha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LinhaDTO linhaSelecionada = linhas.get(position);
                Toast.makeText(parent.getContext(), "linha selecionada " + linhaSelecionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Nenhuma linha selecionada", Toast.LENGTH_SHORT).show();
            }
        });

        /*linha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //LinhaDTO linhaSelecionada = linhas.get(position);

            }
        });*/

        //Configuração para permitir que o dialogo do calendário seja aberto somente ao clicar na imagem
        dataOcorrencia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /**
                 * Implementação baseada na Thread:
                 * http://stackoverflow.com/questions/3554377/handling-click-events-on-a-drawable-within-an-edittext/19194441#19194441
                 */
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (dataOcorrencia.getRight() - dataOcorrencia.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        showCalendar();
                        return true;
                    }
                }
                return false;
            }
        });

    }

    private List<LinhaDTO> carregarLinhas() {
        ArrayList<LinhaDTO> linhas = new ArrayList<>();
        LinhaDTO linha = new LinhaDTO();
        linha.setIdLinha(1l);
        linha.setNumeroLinha("711");
        linhas.add(linha);
        linha = new LinhaDTO();
        linha.setIdLinha(12l);
        linha.setNumeroLinha("712");
        linhas.add(linha);
        linha = new LinhaDTO();
        linha.setIdLinha(13l);
        linha.setNumeroLinha("713");
        linhas.add(linha);
        return linhas;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showCalendar() {

        Calendar calendar = Calendar.getInstance();

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        DatePickerDialog dialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dataOcorrencia.setText(Dates.format(dayOfMonth, monthOfYear, year, "dd/MM/yyyy"));
            }
        }, ano, mes, dia);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        dialog.show();

        /*DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");*/
    }

    public boolean isValidForm() {
        List<View> invalidFields = new ArrayList<>();
        boolean isValid = true;
        /*if (TextUtils.isEmpty(nome.getText().toString())) {
            nome.setError(Html.fromHtml("<font color='red'>Preencha o campo nome</font>"));
            invalidFields.add(nome);
            isValid = false;
        }
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError(Html.fromHtml("<font color='red'>Preencha o campo email</font>"));
            invalidFields.add(email);
            isValid = false;
        } else {
            if (!RegexValidatorUtil.isValidEmail(email.getText().toString())) {
                email.setError(Html.fromHtml("<font color='red'>Informe um email válido</font>"));
                invalidFields.add(email);
                isValid = false;
            }
        }
        if (TextUtils.isEmpty(senha.getText().toString())) {
            senha.setError(Html.fromHtml("<font color='red'>Preencha o campo senha</font>"));
            invalidFields.add(senha);
            isValid = false;
        }*/

        if (!isValid) {
            invalidFields.get(0).requestFocus();
        }

        return isValid;
    }

    public void salvarReclamacao(View view) {
//         LinhaDTO l = (LinhaDTO) linha.
        //Toast.makeText(this, l.getNumeroLinha(), Toast.LENGTH_SHORT).show();

        //new AutoCompleteLinhaTask(this).execute("7");
    }

    /*public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of TimePickerDialog and return it
            DatePickerDialog d = new DatePickerDialog(getActivity(), this, ano, mes, dia);
            return d;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataOcorrencia.setText(Dates.format(dayOfMonth, monthOfYear, year, "dd/MM/yyyy"));
        }
    }*/
}
