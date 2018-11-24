package org.screwphish.dataflood.controller;

import org.screwphish.dataflood.client.DataFloodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/")
public class MainController {

	private final DataFloodService dataFloodService;
	private String redirectedUri;
	private String sessionId;

	@Inject
	public MainController(DataFloodService dataFloodService) {
		this.dataFloodService = dataFloodService;
	}


	@GetMapping("/getbody")
	public String hello() {
		dataFloodService.executeRedirects();
		return "executing...";
	}
}
