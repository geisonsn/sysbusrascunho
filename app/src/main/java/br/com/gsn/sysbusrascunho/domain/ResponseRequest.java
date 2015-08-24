package br.com.gsn.sysbusrascunho.domain;

/**
 * Created by Geison on 23/08/2015.
 */
public class ResponseRequest {

    private Object objectReturn;
    private int statusCode;

    public ResponseRequest(Object object, int statusCode) {
        this.objectReturn = object;
        this.statusCode = statusCode;
    }

    public ResponseRequest(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getObjectReturn() {
        return this.objectReturn;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
