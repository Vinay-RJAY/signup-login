package com.example.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CommonApiResponse {

	private String response;
	
	private String result;
}
