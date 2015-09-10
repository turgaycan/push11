package com.push11.controller;

import com.push11.client.Push11AbstractHttpClient;
import com.push11.client.Push11ModelHttpClient;
import com.push11.domain.AbstractDocument;
import com.push11.exception.custom.ErrorCode;
import com.push11.exception.custom.Push11Exception;
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
        Push11AbstractHttpClient push11DocumentHttpClient = new Push11AbstractHttpClient();
        return push11DocumentHttpClient.post(url, nameValuePairs);
    }

    protected CloseableHttpResponse executeGet(String url){
        Push11AbstractHttpClient push11DocumentHttpClient = new Push11AbstractHttpClient();
        return push11DocumentHttpClient.get(url);
    }

    protected T getDocument(String url){
        Push11AbstractHttpClient push11DocumentHttpClient = new Push11AbstractHttpClient(clazz);
        return (T) push11DocumentHttpClient.getJsonAsDocument(url);
    }

    protected T postDocument(String url, AbstractDocument instance) throws Push11Exception {
        Push11AbstractHttpClient push11DocumentHttpClient = new Push11AbstractHttpClient(clazz);
        try {
            return (T) push11DocumentHttpClient.postJsonAsDocument(url, instance);
        } catch (IOException e) {
            throw new Push11Exception("io", ErrorCode.IO_EXCEPTION);
        }
    }

    protected T getModel(String url){
        Push11ModelHttpClient modelHttpClient = new Push11ModelHttpClient(clazz);
        return (T) modelHttpClient.getJsonAsModel(url);
    }

    protected T postModel(String url){
        Push11ModelHttpClient push11DocumentHttpClient = new Push11ModelHttpClient(clazz);
        return (T) push11DocumentHttpClient.postJsonAsModel(url);
    }

    protected Object postDocument(String url, Object document, Object returnObject){
        Push11AbstractHttpClient push11DocumentHttpClient = new Push11AbstractHttpClient();
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