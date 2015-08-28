package br.com.gsn.sysbusrascunho.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.AbstractSpringRestResponse;
import br.com.gsn.sysbusrascunho.domain.OrigemReclamacaoDTO;
import br.com.gsn.sysbusrascunho.domain.SprintRestResponse;
import br.com.gsn.sysbusrascunho.domain.enums.ObjetoReclamadoEnum;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class OrigemReclamacaoTask extends AsyncTask<String, Integer, SprintRestResponse> {

    private Context context;

    public OrigemReclamacaoTask(Context context) {
        this.context = context;
    }

    @Override
    protected SprintRestResponse doInBackground(String... param) {

        ObjetoReclamadoEnum objetoReclamado = ObjetoReclamadoEnum.getFromDescricao(param[0]);
        if (objetoReclamado.equals(ObjetoReclamadoEnum.OUTROS)) {
            OrigemReclamacaoDTO o = new OrigemReclamacaoDTO();
            o.setDescricaoTipoReclamacao("Outros");
            return new SprintRestResponse(context, new OrigemReclamacaoDTO[] {o}, HttpURLConnection.HTTP_OK);
        }

        String url = UrlServico.URL_LISTAGEM_ORIGEM_RECLAMACAO;
        url = url.replace("{objetoReclamado}", ObjetoReclamadoEnum.getFromDescricao(param[0]).name());
        return SpringRestClient.getForObject(context, url, OrigemReclamacaoDTO[].class);
    }

    @Override
    protected void onPostExecute(final SprintRestResponse response) {

        /*Spinner origemReclamacao = (Spinner) ((Activity) context).findViewById(R.id.origemReclamacao);

        if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_OK) {
            OrigemReclamacaoDTO[] l = (OrigemReclamacaoDTO[]) responseRequest.getObjectReturn();
            origemReclamacao.setAdapter(new ArrayAdapter<OrigemReclamacaoDTO>(context, android.R.layout.simple_list_item_1, l));
            //Toast.makeText(context, "Consulta ok " + l[0].getNumeroLinha(), Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_UNAVAILABLE
                || responseRequest.getStatusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
        }*/

        response.setOnHttpOk(new AbstractSpringRestResponse.OnHttpOk() {
            @Override
            public void doThis() {
                Spinner origemReclamacao = (Spinner) ((Activity) context).findViewById(R.id.origemReclamacao);
                OrigemReclamacaoDTO[] l = (OrigemReclamacaoDTO[]) response.getObjectReturn();
                origemReclamacao.setAdapter(new ArrayAdapter<OrigemReclamacaoDTO>(context, android.R.layout.simple_spinner_item, l));
            }
        });
        response.executeCallbacks();
    }
}
