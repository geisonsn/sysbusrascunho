package br.com.gsn.sysbusrascunho.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gsn.sysbusrascunho.util.ConnectionUtil;
import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.tasks.LoginTask;
import br.com.gsn.sysbusrascunho.view.ClearFieldsError;


public class LoginActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);

        usuario.addTextChangedListener(new ClearFieldsError(usuario));
        senha.addTextChangedListener(new ClearFieldsError(senha));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void novoUsuario(View view) {
        startActivity(new Intent(this, NovoUsuarioAcitivity.class));
    }

    public void recuperarSenha(View view) {
    }

    public void realizarLogin(View view) {

        if (ConnectionUtil.isOnline(this)) {
           if (loginValido()) {
               String usuario = this.usuario.getText().toString();
               String senha = this.senha.getText().toString();
               usuario = "admin";
               senha = "admin";
               new LoginTask(this).execute(usuario, senha);
           }
        } else {
            Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean loginValido() {

        EditText campoComFoco = null;

        boolean isValid = true;

        if (usuario.getText().toString().length() == 0) {
            campoComFoco = usuario;
            usuario.setError("Usuário obrigatório");
            isValid = false;
        }
        if (senha.getText().toString().length() == 0) {
            if (campoComFoco == null) {
                campoComFoco = senha;
            }
            senha.setError("Senha obrigatória");
            isValid = false;
        }

        if (campoComFoco != null) {
            campoComFoco.requestFocus();
        }

        return isValid;
    }
}
