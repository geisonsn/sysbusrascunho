package br.com.gsn.sysbusrascunho.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.AbstractSpringRestResponse;
import br.com.gsn.sysbusrascunho.domain.SprintRestResponse;
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by p001234 on 05/05/15.
 */
public class LoginTask extends AsyncTask<String, Integer, SprintRestResponse> {

    ProgressDialog progressDialog;
    private Context context;

    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("Carregando...");
//        progressDialog.setMessage("Realizando login");
//        progressDialog.show();
    }

    @Override
    protected SprintRestResponse doInBackground(String... params) {

//        String urlServico = "http://192.168.2.1:80/sysbusweb/services/usuario/{usuario}/{senha}";
        String urlServico = UrlServico.URL_LOGIN;

        String paramUsuario = params[0];
        String paramSenha = params[1];

        urlServico = urlServico.replace("{usuario}", paramUsuario);
        urlServico = urlServico.replace("{senha}", paramSenha);

        return SpringRestClient.getForObject(context, urlServico, UsuarioDTO.class);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(final SprintRestResponse response) {
//        progressDialog.dismiss();

        response.setOnHttpOk(new AbstractSpringRestResponse.OnHttpOk() {
            @Override
            public void doThis() {
                UsuarioDTO usuario = (UsuarioDTO) response.getObjectReturn();
                Toast.makeText(context, "Usuário " + usuario.getEmail() + " logado", Toast.LENGTH_SHORT).show();
            }
        });
        response.setOnHttpNotFound(new AbstractSpringRestResponse.OnHttpNotFound() {
            @Override
            public void doThis() {
                Toast.makeText(context, context.getResources().getString(R.string.msg_senha_usuario_incorreto), Toast.LENGTH_SHORT).show();
            }
        });

        response.executeCallbacks();
    }
}
