package com.push11.client;

import com.push11.handler.model.AbstractModelPush11ResponseHandler;
import com.push11.model.base.BaseModel;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Push11ModelHttpClient<T extends BaseModel> extends AbstractModelPush11ResponseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11ModelHttpClient.class);

    private Class<T> clazz;

    public Push11ModelHttpClient(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Push11ModelHttpClient() {
    }


    public BaseModel getJsonAsModel(String endpointUrl) {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpGet httpget = getHttpGet(endpointUrl);
        ResponseHandler<BaseModel> handler = buildResponseHandler();
        BaseModel responseEntity = null;
        try {
            return httpClient.execute(httpget, handler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
        }
        return responseEntity;
    }

    public BaseModel postJsonAsModel(String endpointUrl) {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpPost httpPost = getHttpPost(endpointUrl);
        ResponseHandler<BaseModel> responseHandler = buildResponseHandler();
        BaseModel responseEntity = null;
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
        }

        return responseEntity;
    }

}
