package org.screwphish.dataflood.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MainClient {

	public final WebClient webClient;

	public MainClient(@Value("${name}") String defaultUrl) {
		this.webClient = WebClient.create(defaultUrl);
	}

}
