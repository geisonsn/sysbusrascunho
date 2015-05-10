package br.com.gsn.sysbusrascunho.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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

//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(urlServico, String.class);

        int len = 500;
        int responseCode = 0;
        URL url = null;
        InputStream is = null;
        try {

            url = new URL(urlServico);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            //http.setRequestMethod("GET");
            //http.setDoInput(true);

            //http.connect();

            responseCode = http.getResponseCode();
            is = http.getInputStream();

            String conteudo = readIt(is, len);

        } catch (Exception e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
            Toast.makeText(context, "Senha ou usuário incorretos", Toast.LENGTH_SHORT).show();
        } else if (responseCode == HttpURLConnection.HTTP_UNAVAILABLE) {
            Toast.makeText(context, "Servidor indisponível", Toast.LENGTH_SHORT).show();
        }
    }

    private String readIt(InputStream stream, int len) throws IOException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
