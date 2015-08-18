package com.push11.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Pushh11ClientUtil {

    protected CloseableHttpClient instanceOfClient() {
        return HttpClients.createDefault();
    }

    protected HttpGet getHttpGet(String endpointUrl) {
        return new HttpGet(getUri(endpointUrl));
    }

    protected HttpPost getHttpPost(String urlToRead) {
        return new HttpPost(getUri(urlToRead));
    }

    protected String getUri(String endpointUrl) {
        return Urls.PUSH_DATA_BASE_URL + endpointUrl;
    }
}
