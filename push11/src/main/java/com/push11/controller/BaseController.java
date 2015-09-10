package com.push11.controller;

import com.push11.client.Push11HttpClientDocument;
import com.push11.client.Push11HttpClientModel;
import com.push11.domain.AbstractDocument;
import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.Push11Exception;
import com.push11.model.base.BaseModel;
import com.push11.service.VersionService;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class BaseController<T> {

    @Autowired
    protected VersionService versionService;

    private final Class<T> clazz;

    public BaseController(final Class<T> clazz) {
        this.clazz = clazz;
    }

    protected CloseableHttpResponse executePost(String url, List<NameValuePair> nameValuePairs){
        Push11HttpClientDocument push11DocumentHttpClient = new Push11HttpClientDocument();
        return push11DocumentHttpClient.post(url, nameValuePairs);
    }

    protected CloseableHttpResponse executeGet(String url){
        Push11HttpClientDocument push11DocumentHttpClient = new Push11HttpClientDocument();
        return push11DocumentHttpClient.get(url);
    }

    protected T getDocument(String url){
        Push11HttpClientDocument push11DocumentHttpClient = new Push11HttpClientDocument(clazz);
        return (T) push11DocumentHttpClient.getJsonAsDocument(url);
    }

    protected T postDocument(String url, AbstractDocument document) throws Push11Exception {
        Push11HttpClientDocument push11DocumentHttpClient = new Push11HttpClientDocument(clazz);
        try {
            return (T) push11DocumentHttpClient.postJsonAsDocument(url, document);
        } catch (IOException e) {
            throw new Push11Exception("io", ErrorCode.IO_EXCEPTION);
        }
    }

    protected T getModel(String url){
        Push11HttpClientModel modelHttpClient = new Push11HttpClientModel(clazz);
        try {
            return (T) modelHttpClient.getJsonAsModel(url);
        } catch (IOException e) {
            throw new Push11Exception("io", ErrorCode.IO_EXCEPTION);
        }
    }

    protected T postModel(String url, BaseModel model){
        Push11HttpClientModel push11DocumentHttpClient = new Push11HttpClientModel(clazz);
        try {
            return (T) push11DocumentHttpClient.postJsonAsModel(url, model);
        } catch (IOException e) {
            throw new Push11Exception("io", ErrorCode.IO_EXCEPTION);
        }
    }

    protected Object postDocument(String url, Object document, Object returnObject){
        Push11HttpClientDocument push11DocumentHttpClient = new Push11HttpClientDocument();
        return push11DocumentHttpClient.postJSON(url, document, returnObject);
    }

    protected void findAndValidateVersion(String versionId) throws Push11Exception {
        versionService.findVersion(versionId);
    }


//    @Override
//    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        versionService.findVersion("");
//        return null;
//    }
}