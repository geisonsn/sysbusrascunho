package br.com.gsn.sysbusrascunho.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.gsn.sysbusrascunho.domain.AbstractSpringRestResponse;
import br.com.gsn.sysbusrascunho.domain.SprintRestResponse;
import br.com.gsn.sysbusrascunho.domain.VeiculoDTO;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class NovaLinhaTask extends AsyncTask<VeiculoDTO, Integer, SprintRestResponse> {

    private Context context;

    public NovaLinhaTask(Context context) {
        this.context = context;
    }

    @Override
    protected SprintRestResponse doInBackground(VeiculoDTO... param) {
        return SpringRestClient.post(context, UrlServico.URL_NOVA_LINHA, param[0], VeiculoDTO.class);
    }

    @Override
    protected void onPostExecute(final SprintRestResponse response) {

        response.setOnHttpCreated(new AbstractSpringRestResponse.OnHttpCreated() {
            @Override
            public void doThis() {
                VeiculoDTO retorno = (VeiculoDTO) response.getObjectReturn();
                Toast.makeText(context, "Linha cadastrada com sucesso", Toast.LENGTH_SHORT).show();
//                EditText numeroLinha = (EditText) ((Activity) context).findViewById(R.id.numeroLinha);
//                numeroLinha.requestFocus();
//                numeroLinha.setText("");
//                ((EditText) ((Activity) context).findViewById(R.id.numeroRegistro)).setText("");
            }
        });
        response.executeCallbacks();
    }
}
