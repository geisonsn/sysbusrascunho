package br.com.gsn.sysbusrascunho.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.util.ConnectionUtil;
import br.com.gsn.sysbusrascunho.util.EdiTextEmptyValidator;
import br.com.gsn.sysbusrascunho.util.RegexValidatorUtil;
import br.com.gsn.sysbusrascunho.util.Validator;
import br.com.gsn.sysbusrascunho.view.FieldsValidation;

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
            usuario.setUsername(email.getText().toString());
            usuario.setPassword(senha.getText().toString());

            EdiTextEmptyValidator blankValidator = new EdiTextEmptyValidator();
            List<Validator> validators = new ArrayList<>();
            validators.add(blankValidator);

            /*new FieldsValidation()
                .add(nome, validators)
                .add(email, validators)
                .add(senha, validators).validate();*/
//            nome.setError("<font color='red'>Vazio</font>");
            new FieldsValidation().add(nome, validators).validate();

            if (RegexValidatorUtil.isValidEmail(email.getText().toString())) {
//                new NovoUsuarioTask(this).execute(usuario);
            } else {
//                email.setError(Html.fromHtml("<font color='red'>Email inválido</font>"));
//                email.requestFocus();
            }
        } else {
            Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void validateForm() {
        if (TextUtils.isEmpty(nome.getText().toString())) {
            nome.setError(Html.fromHtml("<font color='red'>Preencha o campo nome</font>"));
        }
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError(Html.fromHtml("<font color='red'>Preencha o campo email</font>"));
        }
        if (TextUtils.isEmpty(senha.getText().toString())) {
            senha.setError(Html.fromHtml("<font color='red'>Preencha o campo senha</font>"));
        }
    }
}
