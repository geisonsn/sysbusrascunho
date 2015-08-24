package br.com.gsn.sysbusrascunho.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.ResponseRequest;
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class NovoUsuarioTask extends AsyncTask<UsuarioDTO, Integer, ResponseRequest> {

    private Context context;

    public NovoUsuarioTask(Context context) {
        this.context = context;
    }

    @Override
    protected ResponseRequest doInBackground(UsuarioDTO... param) {
        return SpringRestClient.post(UrlServico.URL_NOVO_USUARIO, param[0], UsuarioDTO.class);
    }

    @Override
    protected void onPostExecute(ResponseRequest responseRequest) {
        if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_CREATED) {
            Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_CONFLICT) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_usuario_ja_cadastrado), Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_UNAVAILABLE
                || responseRequest.getStatusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
        }
    }
}
