package br.com.gsn.sysbusrascunho.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.OrigemReclamacaoDTO;
import br.com.gsn.sysbusrascunho.domain.ResponseRequest;
import br.com.gsn.sysbusrascunho.domain.enums.ObjetoReclamadoEnum;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class OrigemReclamacaoTask extends AsyncTask<String, Integer, ResponseRequest> {

    private Context context;

    public OrigemReclamacaoTask(Context context) {
        this.context = context;
    }

    @Override
    protected ResponseRequest doInBackground(String... param) {

        ObjetoReclamadoEnum objetoReclamado = ObjetoReclamadoEnum.getFromDescricao(param[0]);
        if (objetoReclamado.equals(ObjetoReclamadoEnum.OUTROS)) {
            OrigemReclamacaoDTO o = new OrigemReclamacaoDTO();
            o.setDescricaoTipoReclamacao("Outros");
            return new ResponseRequest(new OrigemReclamacaoDTO[] {o}, HttpURLConnection.HTTP_OK);
        }

        String url = UrlServico.URL_LISTAGEM_ORIGEM_RECLAMACAO;
        url = url.replace("{objetoReclamado}", ObjetoReclamadoEnum.getFromDescricao(param[0]).name());
        return SpringRestClient.get(url, OrigemReclamacaoDTO[].class);
    }

    @Override
    protected void onPostExecute(ResponseRequest responseRequest) {

        Spinner origemReclamacao = (Spinner) ((Activity) context).findViewById(R.id.origemReclamacao);

        if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_OK) {
            OrigemReclamacaoDTO[] l = (OrigemReclamacaoDTO[]) responseRequest.getObjectReturn();
            origemReclamacao.setAdapter(new ArrayAdapter<OrigemReclamacaoDTO>(context, android.R.layout.simple_dropdown_item_1line, l));
            //Toast.makeText(context, "Consulta ok " + l[0].getNumeroLinha(), Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_UNAVAILABLE
                || responseRequest.getStatusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
        }
    }
}
