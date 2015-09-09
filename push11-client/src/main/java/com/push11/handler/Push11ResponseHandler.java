package com.push11.handler;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class Push11ResponseHandler<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Push11ResponseHandler.class);

    protected abstract T jsonToObject(HttpEntity entity) throws IOException;

    protected abstract HttpEntity objectToJSON(T t);

    protected Charset getCharset(HttpEntity entity) {
        ContentType contentType = ContentType.getOrDefault(entity);
        return contentType.getCharset();
    }

    protected Reader buildInputStream(HttpEntity entity, Charset charset) throws IOException {
        try {
            return new InputStreamReader(entity.getContent(), charset);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when streaming.. {} ", e);
            throw new IOException("IOException occurs when streaming");
        }
    }

    protected boolean invalidStatus(StatusLine statusLine) {
        return statusLine.getStatusCode() >= 300;
    }

}
