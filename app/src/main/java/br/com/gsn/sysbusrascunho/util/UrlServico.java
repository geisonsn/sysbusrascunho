package br.com.gsn.sysbusrascunho.util;

/**
 * Created by p001234 on 05/05/15.
 */
public final class UrlServico {

    private UrlServico() {}

    public static final String URL_LOGIN = "http://sysbusweb-gsanton.rhcloud.com/services/usuario/{usuario}/{senha}";
    public static final String URL_NOVO_USUARIO = "http://sysbusweb-gsanton.rhcloud.com/services/usuario";
    public static final String URL_LISTAGEM_LINHA = "http://sysbusweb-gsanton.rhcloud.com/services/linha";
    public static final String URL_LISTAGEM_ORIGEM_RECLAMACAO = "http://sysbusweb-gsanton.rhcloud.com/services/origemreclamacao/{objetoreclamado}";

}
