package com.push11.handler.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.push11.domain.AbstractDocument;
import com.push11.handler.Push11ResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class DocumentPush11ResponseConverter extends Push11ResponseHandler<AbstractDocument> {

    @Override
    protected AbstractDocument jsonToObject(HttpEntity entity) throws IOException {
        Gson gson = new GsonBuilder().create();
        Charset charset = getCharset(entity);
        Reader reader = buildInputStream(entity, charset);
        return gson.fromJson(reader, AbstractDocument.class);
    }

    @Override
    protected HttpEntity objectToJSON(AbstractDocument abstractDocument) {
        Gson gson = new GsonBuilder().create();
        String objectAsString = gson.toJson(abstractDocument);
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        basicHttpEntity.setContent(new ByteArrayInputStream(objectAsString.getBytes()));
        return basicHttpEntity;
    }


}
