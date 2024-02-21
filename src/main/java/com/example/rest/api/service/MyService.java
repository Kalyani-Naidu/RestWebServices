package com.example.rest.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.rest.api.model.Project;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class MyService {
	
	private static final Logger log = LoggerFactory.getLogger(MyService.class);
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	private WebClient client;
	
	public MyService(WebClient client) {
		this.client = client;
	}
	
	@PostConstruct
	public void getAllById(){
		log.info("Retrieving all employees...");
		
		Mono<Project> project = client
				        		.get()
				        		.uri("/getData/{empId}",5)
				                .retrieve()
				                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
				                        Mono.error(new RuntimeException("Client error: " + clientResponse.statusCode())))
				                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
				                        Mono.error(new RuntimeException("Server error: " + clientResponse.statusCode())))
				                .bodyToMono(Project.class)
				                .doOnError(error -> log.error("Error retrieving employees: {}", error.getMessage()));
					
		 project.subscribe(projects -> {
		        log.info("Received projects: {}", projects);
		        eventPublisher.publishEvent(new DataEvent(this));
		        int empId = projects.getEmpId();
		        int projectCode = projects.getProjectCode();
		        String projectName = projects.getProjectName();
		        log.info("empId: "+empId);
		        log.info("projectCode: "+projectCode);
		        log.info("projectName: "+projectName);
		    }, error -> {
		        log.error("Error retrieving projects: {}", error.getMessage());
		    });
	}

}
