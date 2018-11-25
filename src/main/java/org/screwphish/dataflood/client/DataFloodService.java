package org.screwphish.dataflood.client;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DataFloodService {

	private final MainClient mainClient;
	private String redirectedUri;
	private String responseCookie;
	private String sessionId;

	@Inject
	public DataFloodService(MainClient mainClient) {
		this.mainClient = mainClient;
	}

	public void executeRedirects() {

		mainClient
				.webClient
				.get()
				.exchange()
				.doOnNext(clientResponse -> redirectedUri = clientResponse.headers().header("Location").get(0))
				.block();

		var result =
				mainClient
						.webClient
						.get()
						.uri(redirectedUri)
						.retrieve()
						.bodyToMono(String.class)
						.block();

		assert result != null;

		sessionId =
				result
						.replace("\"; top.location = page; </script></html>", "")
						.replace("<html><script language=\"javascript\">var page = \"Login.php?sslchannel=true&sessionid=", "");


		mainClient
				.webClient
				.get()
				.uri(redirectedUri)
				.exchange()
				.doOnNext(clientResponse ->
						responseCookie = clientResponse.headers().header("Set-Cookie").get(0).replace("; path=/", "")
				)
				.block();

		mainClient
				.webClient
				.get()
				.uri("https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/Login.php?sslchannel=true&sessionid={sessionId}", sessionId)
				.header("Referer", redirectedUri)
				.header("Cookie", responseCookie)
				.retrieve()
				.bodyToMono(String.class);

		mainClient
				.webClient
				.get()
				.uri("https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/assets/signin.php")
				.header("Referer", "https://mail-requestcancelation3dsupp.orderspayauthorizedappsid.com/Login.php?sslchannel=true&sessionid=" + sessionId)
				.header("Cookie", responseCookie)
				.retrieve()
				.bodyToMono(String.class)
				.block();

	}
}
