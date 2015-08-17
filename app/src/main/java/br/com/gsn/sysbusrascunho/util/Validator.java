package br.com.gsn.sysbusrascunho.util;

/**
 * Created by Geison on 15/08/2015.
 */
public abstract interface Validator<T> {

    public abstract boolean validate(T field);
}
