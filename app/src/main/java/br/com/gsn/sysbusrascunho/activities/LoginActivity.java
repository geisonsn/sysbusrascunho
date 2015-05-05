package br.com.gsn.sysbusrascunho.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.tasks.LoginTask;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
        String usuario = ((EditText) findViewById(R.id.usuario)).getText().toString();
        String senha = ((EditText) findViewById(R.id.senha)).getText().toString();
        //usuario = "admin";
        //senha = "admin";
        new LoginTask(this).execute(usuario, senha);
    }
}
