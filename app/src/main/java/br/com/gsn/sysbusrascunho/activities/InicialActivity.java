package br.com.gsn.sysbusrascunho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.tasks.LoginTask;


public class InicialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
    }

    public void realizarLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void realizarCadastro(View view) {
        startActivity((new Intent(this, NovoUsuarioActivity.class)));
    }
}
