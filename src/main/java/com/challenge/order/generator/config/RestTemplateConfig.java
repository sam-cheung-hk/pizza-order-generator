package com.challenge.order.generator.config;

import com.challenge.order.generator.config.property.TrustStoreProperties;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private TrustStoreProperties trustStoreProperties;

    @Bean
    public RestTemplate getRestTemplate() throws Exception {
        // load trust store
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStoreProperties.getResource().getURL(), trustStoreProperties.getPassword().toCharArray())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

        // Build HTTP client
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }

}
