package br.com.gsn.sysbusrascunho.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

//      String urlServico = "http://sysbusweb-gsanton.rhcloud.com/services/usuario/:usuario/:senha";
        String urlServico = UrlServico.URL_LOGIN;

        String usuario = params[0];
        String senha = params[1];

        urlServico = urlServico.replace(":usuario", usuario);
        urlServico = urlServico.replace(":senha", senha);

        int responseCode = 0;
        URL url = null;
        InputStream in = null;
        try {

            url = new URL(urlServico);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod();

            responseCode = http.getResponseCode();
//            Object content = http.getContent();
//            in = new BufferedInputStream(http.getInputStream());

        } catch (Exception e) {
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
        if (responseCode == 200) {
            Toast.makeText(context, "Usuário logado", Toast.LENGTH_SHORT).show();
        } else if (responseCode == 404) {
            Toast.makeText(context, "Senha ou usuário incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
