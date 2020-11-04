package io.github.changsy.gateway.outbound.httpclient4;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class HttpClient {

    private static final Logger logger = Logger.getLogger(HttpClient.class.getCanonicalName());

    public static boolean enable_log = true;

    public static String post(HttpPost post) {

        return execute(post);
    }

    static HttpPost createHttpRequest(String url, String requestBody) {
        try {
            if(enable_log)
            {
                logger.info(url);
                logger.info(requestBody);
            }
            URIBuilder uriBuilder = new URIBuilder(url);

            HttpPost post = new HttpPost(uriBuilder.build());
            post.addHeader("Content-Type", "application/json;charset=utf-8");
            post.addHeader("Accept", "application/json");
            Integer.parseInt(post.getFirstHeader("Content-Length").getValue());
            if (requestBody != null)
                post.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

            return post;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static String execute(HttpPost post) {
        try {

            org.apache.http.client.HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            String responseStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if(enable_log)
                logger.info(responseStr);
            return responseStr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
