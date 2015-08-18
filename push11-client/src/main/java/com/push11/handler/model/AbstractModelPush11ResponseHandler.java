package com.push11.handler.model;

import com.push11.model.base.BaseModel;
import com.push11.util.Urls;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractModelPush11ResponseHandler extends ModelPush11ResponseConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractModelPush11ResponseHandler.class);

    public ResponseHandler<BaseModel> buildResponseHandler() {
        return response -> {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (invalidStatus(statusLine)) {
                throw new HttpResponseException(
                        statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            if (entity == null) {
                LOGGER.error("Response contains no content");
                throw new ClientProtocolException("Response contains no content");
            }
            return jsonToObject(entity);
        };
    }


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
