package br.com.gsn.sysbusrascunho.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.gsn.sysbusrascunho.R;

/**
 * Created by Geison on 24/08/2015.
 */
public class MenuActivity extends Activity implements View.OnClickListener {

    private Button telaInicial, telaLogin, cadastroUsuario, cadastroReclamacao, realizarCheckin, cadastroLinha, percentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        telaInicial = (Button) findViewById(R.id.telaInicial);
        telaInicial.setOnClickListener(this);
        telaLogin = (Button) findViewById(R.id.telaLogin);
        telaLogin.setOnClickListener(this);
        cadastroUsuario = (Button) findViewById(R.id.cadastroUsuario);
        cadastroUsuario.setOnClickListener(this);
        cadastroReclamacao = (Button) findViewById(R.id.cadastroReclamacao);
        cadastroReclamacao.setOnClickListener(this);
        realizarCheckin = (Button) findViewById(R.id.realizarCheckin);
        realizarCheckin.setOnClickListener(this);
        cadastroLinha = (Button) findViewById(R.id.cadastroLinha);
        cadastroLinha.setOnClickListener(this);
        percentLayout = (Button) findViewById(R.id.buttonPercentLayout);
        percentLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.telaInicial) {
            startActivity((new Intent(this, InicialActivity.class)));
        } else if (v.getId() == R.id.telaLogin) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (v.getId() == R.id.cadastroUsuario) {
            startActivity(new Intent(this, NovoUsuarioActivity.class));
        } else if (v.getId() == R.id.cadastroReclamacao) {
            startActivity(new Intent(this, NovaReclamacaoActivity.class));
        } else if (v.getId() == R.id.realizarCheckin) {
            startActivity(new Intent(this, CheckinActivity.class));
        } else if (v.getId() == R.id.cadastroLinha) {
            startActivity(new Intent(this, NovaLinhaActivity.class));
        } else if (v.getId() == R.id.buttonPercentLayout) {
            startActivity(new Intent(this, PercentLayoutActivity.class));
        }
    }
}
