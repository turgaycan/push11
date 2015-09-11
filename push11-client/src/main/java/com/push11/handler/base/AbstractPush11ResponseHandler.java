package com.push11.handler.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class AbstractPush11ResponseHandler<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPush11ResponseHandler.class);

    public AbstractPush11ResponseHandler(final Class<T> clazz) {
        this.clazz = clazz;
    }

    private Class<T> clazz;

    public Class<T> getClazz() {
        return clazz;
    }

    protected abstract T jsonToObject(HttpEntity entity) throws IOException;

    protected Gson newGSonInstance() {
        return new GsonBuilder().create();
    }

    protected HttpEntity getHttpEntity(String objectAsString) {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        basicHttpEntity.setContent(new ByteArrayInputStream(objectAsString.getBytes()));
        return basicHttpEntity;
    }

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
