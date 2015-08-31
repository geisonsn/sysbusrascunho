package br.com.gsn.sysbusrascunho.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Arrays;

import br.com.gsn.sysbusrascunho.domain.AbstractSpringRestResponse;
import br.com.gsn.sysbusrascunho.domain.LinhaDTO;
import br.com.gsn.sysbusrascunho.domain.SprintRestResponse;
import br.com.gsn.sysbusrascunho.persistence.LinhaDao;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class LinhaTask extends AsyncTask<Void, Integer, SprintRestResponse> {

    private LinhaDao linhaDao;
    private Context context;

    public LinhaTask(Context context) {
        this.context = context;
        linhaDao = new LinhaDao(context);
    }

    @Override
    protected SprintRestResponse doInBackground(Void... param) {
        String url = UrlServico.URL_LISTAGEM_LINHA;

        return SpringRestClient.getForObject(context, url, LinhaDTO[].class);
    }

    @Override
    protected void onPostExecute(final SprintRestResponse response) {
        response.setOnHttpOk(new AbstractSpringRestResponse.OnHttpOk() {
            @Override
            public void doThis() {
                LinhaDTO[] linhas = (LinhaDTO[]) response.getObjectReturn();
                Integer ultimaLinha = linhaDao.getIdUltimaLinha();

                if (ultimaLinha == null || ultimaLinha == 0) {
                    for (LinhaDTO linha : linhas) {
                        if (linha.getIdLinha().intValue() > ultimaLinha.intValue()) {
                            linhaDao.insert(Arrays.asList(linhas));
                        }
                    }
                }
            }
        });
        response.executeCallbacks();
    }
}
