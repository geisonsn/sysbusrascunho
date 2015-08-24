package br.com.gsn.sysbusrascunho.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.LinhaDTO;
import br.com.gsn.sysbusrascunho.domain.ResponseRequest;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class AutoCompleteLinhaTask extends AsyncTask<String, Integer, ResponseRequest> {

    private Context context;

    public AutoCompleteLinhaTask(Context context) {
        this.context = context;
    }

    @Override
    protected ResponseRequest doInBackground(String... param) {
        String url = UrlServico.URL_LISTAGEM_LINHA_POR_NUMERO;
        url = url.replace("{numeroLinha}", param[0]);
        return SpringRestClient.get(url, LinhaDTO[].class);
    }

    @Override
    protected void onPostExecute(ResponseRequest responseRequest) {

        AutoCompleteTextView auto = (AutoCompleteTextView) ((Activity) context).findViewById(R.id.linha);

        if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_OK) {
            LinhaDTO[] l = (LinhaDTO[]) responseRequest.getObjectReturn();
            auto.setAdapter(new ArrayAdapter<LinhaDTO>(context, android.R.layout.simple_dropdown_item_1line, l));
            //Toast.makeText(context, "Consulta ok " + l[0].getNumeroLinha(), Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_UNAVAILABLE
                || responseRequest.getStatusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
        }
    }
}
