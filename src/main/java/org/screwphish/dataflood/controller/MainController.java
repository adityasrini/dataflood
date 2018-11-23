package org.screwphish.dataflood.controller;

import org.screwphish.dataflood.client.MainClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {

    @Inject
    private MainClient mainClient;

    @GetMapping("/getbody")
    public Mono<String> hello(){
        return mainClient.getWebClient().get().uri("P4S4N6B4D4NC0K").exchange().flatMap( response -> {
            System.out.println(response.headers().asHttpHeaders());
            return response.bodyToMono(String.class);
        });
    }

    @GetMapping("/Login.php")
    public Mono<String> redirect(@RequestParam Map<String, String> allRequestParams){
        var output =  WebClient.create("https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/Login.php?sslchannel=true&sessionid=" + allRequestParams.get("sessionid")).get().retrieve().bodyToMono(String.class).block(Duration.ofSeconds(10L));
        System.out.println(output);
        return null;
    }
}
