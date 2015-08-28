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
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.tasks.NovoUsuarioTask;
import br.com.gsn.sysbusrascunho.util.ConnectionUtil;
import br.com.gsn.sysbusrascunho.util.RegexValidatorUtil;

/**
 * Created by p001234 on 05/05/15.
 */
public class NovoUsuarioActivity extends Activity {


    private EditText nome, email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.novo_usuario);

        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void cadastrarUsuario(View view) {
        if (ConnectionUtil.isOnline(this)) {
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setNome(nome.getText().toString());
            usuario.setEmail(email.getText().toString());
            usuario.setUsername(email.getText().toString()); //TODO levar atribuição para o webservice
            usuario.setPassword(senha.getText().toString());

            if (isValidForm()) {
                new NovoUsuarioTask(this).execute(usuario);
            }
        } else {
            Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidForm() {
        List<View> invalidFields = new ArrayList<>();
        boolean isValid = true;
        if (TextUtils.isEmpty(nome.getText().toString())) {
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
        }

        if (!isValid) {
            invalidFields.get(0).requestFocus();
        }

        return isValid;
    }
}
