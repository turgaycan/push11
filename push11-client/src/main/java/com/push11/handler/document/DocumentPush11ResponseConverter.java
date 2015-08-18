package com.push11.handler.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.push11.domain.AbstractDocument;
import com.push11.handler.Push11ResponseHandler;
import org.apache.http.HttpEntity;

import java.io.Reader;
import java.nio.charset.Charset;

public abstract class DocumentPush11ResponseConverter extends Push11ResponseHandler {

    @Override
    protected AbstractDocument jsonToObject(HttpEntity entity) {
        Gson gson = new GsonBuilder().create();
        Charset charset = getCharset(entity);
        Reader reader = buildInputStream(entity, charset);
        return gson.fromJson(reader, AbstractDocument.class);
    }

}
