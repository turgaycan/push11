package com.push11.handler.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.push11.handler.Push11ResponseHandler;
import com.push11.model.base.BaseModel;
import org.apache.http.HttpEntity;

import java.io.Reader;
import java.nio.charset.Charset;

public abstract class ModelPush11ResponseConverter extends Push11ResponseHandler {

    @Override
    protected BaseModel jsonToObject(HttpEntity entity) {
        Gson gson = new GsonBuilder().create();
        Charset charset = getCharset(entity);
        Reader reader = buildInputStream(entity, charset);
        return gson.fromJson(reader, BaseModel.class);
    }
}
