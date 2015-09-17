package com.push11.handler.converter;


import com.google.gson.Gson;
import com.push11.domain.document.AbstractDocument;
import com.push11.handler.base.AbstractPush11ResponseHandler;
import com.push11.domain.model.base.BaseModel;
import org.apache.http.HttpEntity;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class AbstractPush11ResponseConverter<T> extends AbstractPush11ResponseHandler<T> {

    public AbstractPush11ResponseConverter(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected T jsonToObject(HttpEntity entity) throws IOException {
        Gson gson = newGSonInstance();
        Charset charset = getCharset(entity);
        Reader reader = buildInputStream(entity, charset);
        return gson.fromJson(reader, getClazz());
    }

    protected HttpEntity documentToJSON(AbstractDocument document) {
        Gson gson = newGSonInstance();
        String objectAsString = gson.toJson(document);
        return getHttpEntity(objectAsString);
    }

    protected HttpEntity modelToJSON(BaseModel model) {
        Gson gson = newGSonInstance();
        String objectAsString = gson.toJson(model);
        return getHttpEntity(objectAsString);
    }
}
