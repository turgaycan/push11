package com.push11.handler;

import com.push11.handler.converter.AbstractPush11ResponseConverter;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;

public abstract class AbstractPush11ResponseBuilder<T> extends AbstractPush11ResponseConverter<T> {

    public AbstractPush11ResponseBuilder(Class clazz) {
        super(clazz);
    }

    public ResponseHandler<T> buildResponseHandler() {
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

}
