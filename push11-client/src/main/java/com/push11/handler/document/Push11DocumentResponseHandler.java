package com.push11.handler.document;

import com.push11.domain.AbstractDocument;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Push11DocumentResponseHandler extends Push11DocumentResponseConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11DocumentResponseHandler.class);

    public ResponseHandler<AbstractDocument> buildResponseHandler() {
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
