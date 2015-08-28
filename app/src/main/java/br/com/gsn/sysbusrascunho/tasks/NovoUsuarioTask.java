package br.com.gsn.sysbusrascunho.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.gsn.sysbusrascunho.domain.AbstractSpringRestResponse;
import br.com.gsn.sysbusrascunho.domain.SprintRestResponse;
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.util.SpringRestClient;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class NovoUsuarioTask extends AsyncTask<UsuarioDTO, Integer, SprintRestResponse> {

    private Context context;

    public NovoUsuarioTask(Context context) {
        this.context = context;
    }

    @Override
    protected SprintRestResponse doInBackground(UsuarioDTO... param) {
        return SpringRestClient.post(context, UrlServico.URL_NOVO_USUARIO, param[0], UsuarioDTO.class);
    }

    @Override
    protected void onPostExecute(final SprintRestResponse response) {
        /*if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_CREATED) {
            Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_CONFLICT) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_usuario_ja_cadastrado), Toast.LENGTH_SHORT).show();
        } else if (responseRequest.getStatusCode() == HttpURLConnection.HTTP_UNAVAILABLE
                || responseRequest.getStatusCode() == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
        }*/

        response.setOnHttpCreated(new AbstractSpringRestResponse.OnHttpCreated() {
            @Override
            public void doThis() {
                UsuarioDTO u = (UsuarioDTO) response.getObjectReturn();
                Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        /*response.setOnHttpConflict(new AbstractSpringRestResponse.OnHttpConflict() {
            @Override
            public void doThis() {
                Toast.makeText(context, context.getResources().getString(R.string.msg_usuario_ja_cadastrado), Toast.LENGTH_SHORT).show();
            }
        });*/
        response.executeCallbacks();
    }
}
