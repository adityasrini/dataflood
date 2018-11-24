package org.screwphish.dataflood.client;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DataFloodService {

	private final MainClient mainClient;
	private String redirectedUri;
	private String sessionId;

	@Inject
	public DataFloodService(MainClient mainClient) {
		this.mainClient = mainClient;
	}

	public void executeRedirects(){
		mainClient.webClient
				.get()
				.exchange()
				.doOnNext(clientResponse -> redirectedUri = clientResponse.headers().header("Location").get(0))
				.block();
		System.out.println(redirectedUri);

		mainClient.webClient
				.get()
				.uri(redirectedUri)
				.exchange()
				.doOnNext(clientResponse ->
						sessionId = clientResponse.headers().header("Set-Cookie").get(0).replace("; path=/","")
				)
				.block();

		System.out.println(sessionId);
		mainClient.webClient
				.get()
				.uri("https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/Login.php?sslchannel=true&sessionid={sessionId}", sessionId)
				.exchange()
				.doOnNext(clientResponse ->
						System.out.println(clientResponse.headers().asHttpHeaders())
				)
				.block();
	}
}
