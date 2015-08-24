package br.com.gsn.sysbusrascunho.util;

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

import br.com.gsn.sysbusrascunho.domain.ResponseRequest;

/**
 * Created by Geison on 23/08/2015.
 */
public final class SpringRestClient {

    public static <T> ResponseRequest post(final String url, final T param, Class<T> returnType) {
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(new MediaType("application", "json"));
            HttpEntity<T> requestEntity = new HttpEntity<>(param, requestHeaders);

            RestTemplate rest = new RestTemplate();

            rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            rest.getMessageConverters().add(new StringHttpMessageConverter());

            ResponseEntity<T> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, returnType);

            return new ResponseRequest(responseEntity.getBody(), responseEntity.getStatusCode().value());

        } catch (HttpStatusCodeException e) {
            return new ResponseRequest(e.getStatusCode().value());
        } catch (Exception e) {
            return new ResponseRequest(0);
        }
    }

    public static <T> ResponseRequest get(final String url, Class<T> returnType) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            T returnObject = restTemplate.getForObject(url, returnType);
            return new ResponseRequest(returnObject, HttpURLConnection.HTTP_OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseRequest(e.getStatusCode().value());
        } catch (Exception e) {
            return new ResponseRequest(0);
        }
    }
}
