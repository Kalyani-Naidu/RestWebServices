package com.example.rest.api.service;

import org.springframework.context.ApplicationEvent;

public class DataEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	public DataEvent(Object source) {
        super(source);
    }

}
