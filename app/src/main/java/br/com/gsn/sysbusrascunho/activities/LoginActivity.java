package br.com.gsn.sysbusrascunho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.tasks.LoginTask;
import br.com.gsn.sysbusrascunho.util.ConnectionUtil;
import br.com.gsn.sysbusrascunho.view.ClearFieldsError;
import br.com.gsn.sysbusrascunho.view.FieldValidation;


public class LoginActivity extends Activity {

    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.senha);
        senha = (EditText) findViewById(R.id.senha);

//        usuario.setOnEditorActionListener(new FieldValidation(usuario, "Usuário obrigatório"));
//        senha.setOnEditorActionListener(new FieldValidation(senha, "Senha obrigatória"));

    }

    public void novoUsuario(View view) {
        startActivity(new Intent(this, NovoUsuarioActivity.class));
    }

    public void recuperarSenha(View view) {
    }

    public void realizarLogin(View view) {

        if (ConnectionUtil.isOnline(this)) {
           if (loginValido()) {
               String usuario = this.usuario.getText().toString();
               String senha = this.senha.getText().toString();
               new LoginTask(this).execute(usuario, senha);
           }
        } else {
            Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean loginValido() {

        EditText campoComFoco = null;

        boolean isValid = true;

        if (TextUtils.isEmpty(usuario.getText())) {
            campoComFoco = usuario;
            usuario.setError(Html.fromHtml("<font color='red'>Usuário obrigatório</font>"));
            isValid = false;
        }

        if (TextUtils.isEmpty(senha.getText())) {
            if (campoComFoco == null) {
                campoComFoco = senha;
            }
            senha.setError(Html.fromHtml("<font color='red'>Senha obrigatória</font>"));
            isValid = false;
        }

        if (campoComFoco != null) {
            campoComFoco.requestFocus();
        }

        return isValid;
    }
}
