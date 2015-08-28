package br.com.gsn.sysbusrascunho.domain;

import android.content.Context;

/**
 * Created by Geison on 23/08/2015.
 */
public class SprintRestResponse extends AbstractSpringRestResponse {

//    private Object objectReturn;

    public SprintRestResponse(Context context, Object object, int statusCode) {
        super(context, object, statusCode);
//        this.objectReturn = object;
//        this.statusCode = statusCode;
    }

    public SprintRestResponse(Context context, int statusCode) {
        super(context, statusCode);
//        this.statusCode = statusCode;
    }

    /*public Object getObjectReturn() {
        return this.objectReturn;
    }

    public int getStatusCode() {
        return this.statusCode;
    }*/




}
