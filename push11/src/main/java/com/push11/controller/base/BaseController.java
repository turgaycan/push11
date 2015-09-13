package com.push11.controller.base;

import com.push11.client.Push11HttpClient;
import com.push11.domain.AbstractDocument;
import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.Push11Exception;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.List;

public abstract class BaseController<T> {

    private final Class<T> clazz;

    public Class<T> getClazz() {
        return clazz;
    }

    public BaseController(final Class<T> clazz) {
        this.clazz = clazz;
    }

    protected CloseableHttpResponse executePost(String url, List<NameValuePair> nameValuePairs){
        Push11HttpClient push11DocumentHttpClient = new Push11HttpClient(clazz);
        return push11DocumentHttpClient.post(url, nameValuePairs);
    }

    protected CloseableHttpResponse executeGet(String url){
        Push11HttpClient push11DocumentHttpClient = new Push11HttpClient(clazz);
        return push11DocumentHttpClient.get(url);
    }

    protected T getJSON(String url){
        Push11HttpClient push11DocumentHttpClient = new Push11HttpClient(clazz);
        try {
            return (T) push11DocumentHttpClient.getJSON(url);
        } catch (IOException e) {
            throw new Push11Exception("io", ErrorCode.IO_EXCEPTION);
        }
    }

    protected T postJSON(String url, AbstractDocument document) throws Push11Exception {
        Push11HttpClient push11DocumentHttpClient = new Push11HttpClient(clazz);
        try {
            return (T) push11DocumentHttpClient.postDocumentJSON(url, document);
        } catch (IOException e) {
            throw new Push11Exception("io", ErrorCode.IO_EXCEPTION);
        }
    }

    protected Object postJSON(String url, AbstractDocument abstractDocument, String returnObject){
        Push11HttpClient push11DocumentHttpClient = new Push11HttpClient(clazz);
        return push11DocumentHttpClient.postJSON(url, abstractDocument, returnObject);
    }

}