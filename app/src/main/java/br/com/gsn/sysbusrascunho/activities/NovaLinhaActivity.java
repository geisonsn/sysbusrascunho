package br.com.gsn.sysbusrascunho.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
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
public class NovaLinhaActivity extends Activity {

    private EditText numeroLinha, numeroRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_linha);

        numeroLinha = (EditText) findViewById(R.id.numeroLinha);
        numeroRegistro = (EditText) findViewById(R.id.numeroRegistro);
    }

    public void salvarLinha(View view) {
        if (ConnectionUtil.isOnline(this)) {
            if (isValidForm()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                veiculo.setNumeroLinha(numeroLinha.getText().toString());
                veiculo.setNumeroRegistro(numeroRegistro.getText().toString());
                new NovaLinhaTask(this).execute(veiculo);
            }
        } else {
            Toast.makeText(this, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidForm() {
        List<View> invalidFields = new ArrayList<>();
        boolean isValid = true;
        if (TextUtils.isEmpty(numeroLinha.getText().toString())) {
            numeroLinha.setError(Html.fromHtml("<font color='red'>Preencha o número da linha</font>"));
            invalidFields.add(numeroLinha);
            isValid = false;
        }
        if (TextUtils.isEmpty(numeroRegistro.getText().toString())) {
            numeroRegistro.setError(Html.fromHtml("<font color='red'>Preencha o número de registro</font>"));
            invalidFields.add(numeroRegistro);
            isValid = false;
        }

        if (!isValid) {
            invalidFields.get(0).requestFocus();
        }

        return isValid;
    }
}
