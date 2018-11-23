package org.screwphish.dataflood.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class MainClient {

    private final WebClient webClient;

    public MainClient() {
        this.webClient = WebClient.builder().uriBuilderFactory(new DefaultUriBuilderFactory("https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com")).build();
//        this.webClient = WebClient.create("http://t.cn/ELPUoSv?id=41652025");
    }

    public String createWebClient(){
        return null;
    }


    public WebClient getWebClient() {
        return webClient;
    }
}
