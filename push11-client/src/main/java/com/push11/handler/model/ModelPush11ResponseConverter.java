package com.push11.handler.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.push11.handler.Push11ResponseHandler;
import com.push11.model.base.BaseModel;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class ModelPush11ResponseConverter extends Push11ResponseHandler<BaseModel> {

    @Override
    protected BaseModel jsonToObject(HttpEntity entity) throws IOException {
        Gson gson = new GsonBuilder().create();
        Charset charset = getCharset(entity);
        Reader reader = buildInputStream(entity, charset);
        return gson.fromJson(reader, BaseModel.class);
    }

    @Override
    protected HttpEntity objectToJSON(BaseModel baseModel) {
        Gson gson = new GsonBuilder().create();
        String objectAsString = gson.toJson(baseModel);
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        basicHttpEntity.setContent(new ByteArrayInputStream(objectAsString.getBytes()));
        return basicHttpEntity;
    }
}
