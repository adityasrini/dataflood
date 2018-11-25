package org.screwphish.dataflood.controller;

import org.screwphish.dataflood.service.EndpointInteractionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@RestController
@RequestMapping("/")
public class RootController {

	private final EndpointInteractionService endpointInteractionService;

	@Inject
	public RootController(EndpointInteractionService endpointInteractionService) {
		this.endpointInteractionService = endpointInteractionService;
	}


	@GetMapping("/")
	public Mono<String> begin() {
		return endpointInteractionService.initialGetRequests();
	}
}
