package br.com.gsn.sysbusrascunho.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.tasks.CadastroUsuarioTask;

/**
 * Created by p001234 on 05/05/15.
 */
public class NovoUsuarioAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_usuario);
    }

    public void cadastrarUsuario(View view) {
        //Implementar asynctask
        new CadastroUsuarioTask(this);
    }
}
