package br.com.gsn.sysbusrascunho.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;
import br.com.gsn.sysbusrascunho.domain.UsuarioDTO;
import br.com.gsn.sysbusrascunho.util.UrlServico;

/**
 * Created by geison on 07/05/15.
 */
public class NovoUsuarioTask extends AsyncTask<UsuarioDTO, Integer, Integer> {

    private Context context;

    public NovoUsuarioTask(Context context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(UsuarioDTO... param) {

        try {

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(new MediaType("application", "json"));
            HttpEntity<UsuarioDTO> requestEntity = new HttpEntity<UsuarioDTO>(param[0], requestHeaders);

            RestTemplate rest = new RestTemplate();

            rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            rest.getMessageConverters().add(new StringHttpMessageConverter());

            ResponseEntity<UsuarioDTO> responseEntity = rest.exchange(UrlServico.URL_NOVO_USUARIO, HttpMethod.POST, requestEntity, UsuarioDTO.class);
            UsuarioDTO result = responseEntity.getBody();

            return responseEntity.getStatusCode().value();

        } catch (HttpStatusCodeException e) {
            return e.getStatusCode().value();
        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    protected void onPostExecute(Integer responseCode) {
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        } else if (responseCode == HttpURLConnection.HTTP_CONFLICT) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_usuario_ja_cadastrado), Toast.LENGTH_SHORT).show();
        } else if (responseCode == HttpURLConnection.HTTP_UNAVAILABLE || responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
        }
    }
}
