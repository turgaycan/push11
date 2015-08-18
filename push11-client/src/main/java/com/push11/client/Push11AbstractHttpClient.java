package com.push11.client;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.push11.domain.AbstractDocument;
import com.push11.handler.document.AbstractDocumentPush11ResponseHandler;
import com.push11.util.Urls;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Push11AbstractHttpClient<T> extends AbstractDocumentPush11ResponseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11AbstractHttpClient.class);

    private Class<T> clazz;

    public Push11AbstractHttpClient(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Push11AbstractHttpClient() {
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


    public AbstractDocument getJsonAsDocument(String endpointUrl) {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpGet httpget = getHttpGet(endpointUrl);
        ResponseHandler<AbstractDocument> handler = buildResponseHandler();
        AbstractDocument responseEntity = null;
        try {
            return httpClient.execute(httpget, handler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
        }
        return responseEntity;
    }

    public AbstractDocument postJsonAsDocument(String endpointUrl) {
        CloseableHttpClient httpClient = instanceOfClient();
        HttpPost httpPost = getHttpPost(endpointUrl);
        ResponseHandler<AbstractDocument> responseHandler = buildResponseHandler();
        AbstractDocument responseEntity = null;
        try {
            return httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            LOGGER.error("IOException occurs when executing.. {}", e);
        }

        return responseEntity;
    }

    public Object postJSON(String urlToRead, Object o, Object returnObject) {
        CloseableHttpClient httpClient = instanceOfClient();
        try {
            HttpPost postRequest = getHttpPost(urlToRead);
            postRequest.setHeader("content-type", "application/x-www-form-urlencoded");

            List<NameValuePair> nameValuePairs = new ArrayList<>();

            JsonElement elm = new GsonBuilder().create().toJsonTree(o);
            JsonObject jsonObject = elm.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().getAsString()));
            }
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

    private CloseableHttpClient instanceOfClient() {
        return HttpClients.createDefault();
    }

    private HttpGet getHttpGet(String endpointUrl) {
        return new HttpGet(getUri(endpointUrl));
    }

    private HttpPost getHttpPost(String urlToRead) {
        return new HttpPost(getUri(urlToRead));
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
