package br.com.gsn.sysbusrascunho.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.VeiculoDTO;
import br.com.gsn.sysbusrascunho.tasks.NovaLinhaTask;
import br.com.gsn.sysbusrascunho.util.ConnectionUtil;

/**
 * Created by Geison on 26/08/2015.
 */
public class CheckinActivity extends Activity {

    private AutoCompleteTextView linha;
    private EditText latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin);

        latitude = (EditText) findViewById(R.id.numeroLinha);
        longitude = (EditText) findViewById(R.id.numeroRegistro);

        linha = (AutoCompleteTextView) findViewById(R.id.linha);
        linha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void checkin(View view) {
        if (ConnectionUtil.isOnline(this)) {
            if (isValidForm()) {
                VeiculoDTO veiculo = new VeiculoDTO();
//                veiculo.setNumeroRegistro();
                new NovaLinhaTask(this).execute(veiculo);
            }
        } else {
            Toast.makeText(this, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidForm() {
        List<View> invalidFields = new ArrayList<>();
        boolean isValid = true;
        if (TextUtils.isEmpty(linha.getText().toString())) {
            latitude.setError(Html.fromHtml("<font color='red'>Selecione uma linha</font>"));
            invalidFields.add(linha);
            isValid = false;
        }

        if (!isValid) {
            invalidFields.get(0).requestFocus();
        }

        return isValid;
    }
}
