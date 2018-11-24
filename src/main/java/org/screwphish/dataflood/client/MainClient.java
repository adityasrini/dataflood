package org.screwphish.dataflood.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MainClient {

	final WebClient webClient;

	public MainClient() {
		this.webClient = WebClient.create("http://t.cn/ELPUoSv");
	}

}
