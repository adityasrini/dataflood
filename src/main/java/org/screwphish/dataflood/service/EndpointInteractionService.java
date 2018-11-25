package org.screwphish.dataflood.service;

import org.screwphish.dataflood.client.MainClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Service
public class EndpointInteractionService {

	private final MainClient mainClient;
	private String responseCookie;
	private String sessionId;

	@Inject
	public EndpointInteractionService(MainClient mainClient) {
		this.mainClient = mainClient;
	}

	public Mono<String> initialGetRequests() {

		mainClient
				.webClient
				.get()
				.exchange()
				.doOnNext(clientResponse ->
						responseCookie = clientResponse.headers().header("Set-Cookie").get(0).replace("; path=/", "")
				)
				.flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
				.doOnNext(s ->
						sessionId = s
								.replace("\"; top.location = page; </script></html>", "")
								.replace("<html><script language=\"javascript\">var page = \"Login.php?sslchannel=true&sessionid=", ""))
				.block();

		return mainClient
				.webClient
				.get()
				.uri("https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/assets/signin.php")
				.header("Referer", "https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/Login.php?sslchannel=true&sessionid=" + sessionId)
				.header("Cookie", responseCookie)
				.exchange()
				.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
	}
}
