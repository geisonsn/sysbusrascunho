package br.com.gsn.sysbusrascunho.domain;

import android.content.Context;
import android.widget.Toast;

import java.net.HttpURLConnection;

import br.com.gsn.sysbusrascunho.R;

/**
 * Created by Geison on 26/08/2015.
 */
public abstract class AbstractSpringRestResponse {

    private Context context;
    private int statusCode;
    private Object objectReturn;

    private OnHttpOk onHttpOk;
    private OnHttpNotFound onHttpNotFound;
    private OnHttpCreated onHttpCreated;
    private OnHttpConflict onHttpConflict;

    protected AbstractSpringRestResponse(Context context, Object objectReturn, int statusCode) {
        this.context = context;
        this.objectReturn = objectReturn;
        this.statusCode = statusCode;
    }

    protected AbstractSpringRestResponse(Context context, int statusCode) {
        this.context = context;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getObjectReturn() {
        return objectReturn;
    }

    protected void onHttpOk() {
        if (onHttpOk == null) {
            Toast.makeText(context, "Requisição realizada com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            onHttpOk.doThis();
        }
    }

    protected void onHttpCreated() {
        if (onHttpCreated == null) {
            Toast.makeText(context, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            onHttpCreated.doThis();
        }
    }

    protected void onHttpConflict() {
        if (onHttpConflict == null) {
            Toast.makeText(context, context.getResources().getString(R.string.msg_registro_ja_cadastrado), Toast.LENGTH_SHORT).show();
        } else {
            onHttpConflict.doThis();
        }
    }

    protected void onHttpUnavailable() {
        Toast.makeText(context, context.getResources().getString(R.string.msg_servidor_indisponivel) , Toast.LENGTH_SHORT).show();
    }

    protected void onHttpNotFound() {
        if (onHttpNotFound == null) {
            Toast.makeText(context, "Não encontrado" , Toast.LENGTH_SHORT).show();
        } else {
            onHttpNotFound.doThis();
        }
    }

    protected void onOther() {
        Toast.makeText(context, "Ops! Ocorreu um erro não previsto." , Toast.LENGTH_SHORT).show();
    }

    public void setOnHttpOk(OnHttpOk onHttpOk) {
        this.onHttpOk = onHttpOk;
    }

    public void setOnHttpNotFound(OnHttpNotFound onHttpNotFound) {
        this.onHttpNotFound = onHttpNotFound;
    }

    public void setOnHttpCreated(OnHttpCreated onHttpCreated) {
        this.onHttpCreated = onHttpCreated;
    }
    public void setOnHttpConflict(OnHttpConflict onHttpConflict) {
        this.onHttpConflict = onHttpConflict;
    }

    public void executeCallbacks() {
        if (this.statusCode == HttpURLConnection.HTTP_OK)  {
            onHttpOk();
        } else if (this.statusCode == HttpURLConnection.HTTP_CREATED) {
            onHttpCreated();
        } else if (this.statusCode == HttpURLConnection.HTTP_CONFLICT) {
            onHttpConflict();
        } else if (this.statusCode == HttpURLConnection.HTTP_UNAVAILABLE) {
            onHttpUnavailable();
        }  else if (this.statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
            onHttpNotFound();
        } else {
            onOther();
        }
    }

    public interface HttpStatusCodeCallback {
        void doThis();
    }

    public interface OnHttpOk extends HttpStatusCodeCallback {
    }

    public interface OnHttpNotFound extends HttpStatusCodeCallback {
    }

    public interface OnHttpCreated extends HttpStatusCodeCallback {
    }

    public interface OnHttpConflict extends HttpStatusCodeCallback {
    }
}
