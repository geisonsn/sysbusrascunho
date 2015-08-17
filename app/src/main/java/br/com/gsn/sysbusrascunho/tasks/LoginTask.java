package br.com.gsn.sysbusrascunho.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by p001234 on 05/05/15.
 */
public class LoginTask extends AsyncTask<String, Integer, Integer> {

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
    protected Integer doInBackground(String... params) {

//        String urlServico = "http://192.168.2.1:80/sysbusweb/services/usuario/:usuario/:senha";
        String urlServico = UrlServico.URL_LOGIN;

        String paramUsuario = params[0];
        String paramSenha = params[1];

        urlServico = urlServico.replace(":usuario", paramUsuario);
        urlServico = urlServico.replace(":senha", paramSenha);

        int responseCode = 0;

        UsuarioDTO usuario = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            usuario = restTemplate.getForObject(urlServico, UsuarioDTO.class);
            responseCode = 200;
        } catch (HttpStatusCodeException e) {
            responseCode = e.getStatusCode().value();
        } catch (Exception e) {
            System.out.println(e);
        }

        return responseCode;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer responseCode) {
//        progressDialog.dismiss();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Toast.makeText(context, "Usuário logado", Toast.LENGTH_SHORT).show();
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_senha_usuario_incorreto), Toast.LENGTH_SHORT).show();
        } else if (responseCode == HttpURLConnection.HTTP_UNAVAILABLE) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Um erro misterioso ocorreu." , Toast.LENGTH_SHORT).show();
        }
    }
}
