package br.com.gsn.sysbusrascunho.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.gsn.sysbusrascunho.UrlServico;

/**
 * Created by p001234 on 05/05/15.
 */
public class LoginTask extends AsyncTask<String, Integer, String> {

    private Context context;

    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

//      String urlServico = "http://sysbusweb-gsanton.rhcloud.com/services/usuario/:usuario/:senha";
        String urlServico = UrlServico.URL_LOGIN;

        String usuario = params[0];
        String senha = params[1];

        urlServico = urlServico.replace(":usuario", usuario);
        urlServico = urlServico.replace(":senha", senha);

        URL url = null;
        InputStream in = null;
        try {

            url = new URL(urlServico);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            int responseCode = http.getResponseCode();

            if (responseCode == 200) {
                Toast.makeText(context, "Usuário logado", Toast.LENGTH_SHORT).show();
                publishProgress(responseCode);
            } else if (responseCode == 400) {
                Toast.makeText(context, "Senha ou usuário incorretos", Toast.LENGTH_SHORT).show();
                publishProgress(responseCode);
            }

//            Object content = http.getContent();
//            in = new BufferedInputStream(http.getInputStream());

        } catch (Exception e) {
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (values[0] == 200) {
            Toast.makeText(context, "Usuário logado", Toast.LENGTH_SHORT).show();
        }
        super.onProgressUpdate(values);
    }
}
