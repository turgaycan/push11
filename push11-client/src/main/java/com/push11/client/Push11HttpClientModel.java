package com.push11.client;

import com.push11.handler.model.Push11ModelResponseHandler;
import com.push11.model.base.BaseModel;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Push11HttpClientModel<T extends BaseModel> extends Push11ModelResponseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11HttpClientModel.class);

    private Class<T> clazz;

    public Push11HttpClientModel(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Push11HttpClientModel() {
    }

    public BaseModel getJsonAsModel(String endpointUrl) throws IOException {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpGet httpget = getHttpGet(endpointUrl);
        ResponseHandler<BaseModel> handler = buildResponseHandler();
        try {
            return httpClient.execute(httpget, handler);
        } catch (IOException e) {
            LOGGER.info("IOException occurs when executing.. {}", e);
            throw new IOException("IOException occurs when executing.. ");
        }
    }

    public BaseModel postJsonAsModel(String endpointUrl, BaseModel model) throws IOException {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpPost httpPost = getHttpPost(endpointUrl);
        httpPost.setHeader("content-type", "application/json");
        httpPost.setEntity(objectToJSON(model));
        ResponseHandler<BaseModel> responseHandler = buildResponseHandler();
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            LOGGER.info("IOException occurs when executing.. {}", e);
            throw new IOException("IOException occurs when executing.. ");
        }
    }

}
