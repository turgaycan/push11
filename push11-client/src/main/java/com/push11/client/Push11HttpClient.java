package com.push11.client;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.push11.domain.document.AbstractDocument;
import com.push11.handler.AbstractPush11ResponseBuilder;
import com.push11.domain.model.base.BaseModel;
import com.push11.domain.util.Urls;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Push11HttpClient<T> extends AbstractPush11ResponseBuilder<T> {

    public Push11HttpClient(Class clazz) {
        super(clazz);
    }

    public T getJSON(String endpointUrl) throws IOException {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpGet httpget = getHttpGet(endpointUrl);
        ResponseHandler<T> handler = buildResponseHandler();
        try {
            return httpClient.execute(httpget, handler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
            throw new IOException("IOException occurs when executing.. ");
        }
    }

    public T postDocumentJSON(String endpointUrl, AbstractDocument document) throws IOException {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpPost httpPost = getHttpPost(endpointUrl);
        httpPost.setEntity(documentToJSON(document));
        ResponseHandler<T> responseHandler = buildResponseHandler();
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
            throw new IOException("IOException occurs when executing.. ");
        }
    }

    public T postModelJSON(String endpointUrl, BaseModel model) throws IOException {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpPost httpPost = getHttpPost(endpointUrl);
        httpPost.setEntity(modelToJSON(model));
        ResponseHandler<T> responseHandler = buildResponseHandler();
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
            throw new IOException("IOException occurs when executing.. ");
        }

    }

    public Object postJSON(String urlToRead, Object o, Object returnObject) {
        CloseableHttpClient httpClient = instanceOfClient();
        try {
            HttpPost postRequest = getHttpPost(urlToRead);

            List<NameValuePair> nameValuePairs = new ArrayList<>();

            JsonElement elm = new GsonBuilder().create().toJsonTree(o);
            JsonObject jsonObject = elm.getAsJsonObject();
            nameValuePairs.addAll(jsonObject.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue().getAsString())).collect(Collectors.toList()));
            try {
                postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("UnsupportedEncodingException occurs when set entity {}", e);
            }
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            try {
                returnObject = httpClient.execute(postRequest, responseHandler);
            } catch (IOException e) {
                LOGGER.error("IOException occurs when executing post {}", e);
            }
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("IOException occurs when closing httpClient {}", e);
            }
        }
        return returnObject;
    }

    public CloseableHttpResponse get(String url) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            CookieStore cookieStore = new BasicCookieStore();
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setCookieStore(cookieStore);
            httpClient = instanceOfClient();
            HttpGet httpGet = getHttpGet(url);
            try {
                response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
            } catch (ClientProtocolException e) {
                LOGGER.error("ClientProtocolException occurs {}", e);
            } catch (IOException e) {
                LOGGER.error("IOException occurs when consumes {}", e);
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("IOException when response closes {} ", e);
                }
            }
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("IOException when httpClient closes {} ", e);
            }
        }

        return response;
    }

    public CloseableHttpResponse post(String url, List<NameValuePair> nameValuePairs) {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpPost httpPost = getHttpPost(url);
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            try {
                response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
            } catch (ClientProtocolException e) {
                LOGGER.error("ClientProtocolException occurs {}", e);
            } catch (IOException e) {
                LOGGER.error("IOException occurs {}", e);
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("IOException when response closes {} ", e);
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException occurs {}", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("IOException when httpClient closes {} ", e);
            }
        }
        return response;
    }

    private CloseableHttpClient instanceOfClient() {
        return HttpClients.createDefault();
    }

    private HttpGet getHttpGet(String endpointUrl) {
        HttpGet httpGet = new HttpGet(getUri(endpointUrl));
        httpGet.setHeader("content-type", "application/json");
        return httpGet;
    }

    private HttpPost getHttpPost(String urlToRead) {
        HttpPost httpPost = new HttpPost(getUri(urlToRead));
        httpPost.setHeader("content-type", "application/json");
        return httpPost;

    }

    private String getUri(String endpointUrl) {
        return Urls.PUSH_DATA_BASE_URL + endpointUrl;
    }

    /**
     * @param protocol
     * @param host
     * @param path
     * @param nameValuePairs
     * @return
     */
    public HttpGet buildCustomHttpGet(String protocol, String host, String path, List<NameValuePair> nameValuePairs) {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme(protocol)
                    .setHost(host)
                    .setPath(path)
                    .addParameters(nameValuePairs)
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URISyntaxException occurs {}", e);
        }
        return new HttpGet(uri);
    }

    /**
     * @param protocol
     * @param host
     * @param path
     * @param nameValuePairs
     * @return
     */
    public HttpPost buildCustomHttpPost(String protocol, String host, String path, List<NameValuePair> nameValuePairs) {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme(protocol)
                    .setHost(host)
                    .setPath(path)
                    .addParameters(nameValuePairs)
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URISyntaxException occurs {}", e);
        }
        return new HttpPost(uri);
    }

}
