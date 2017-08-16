package com.cloud.client.eureka.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EurekaClientPrimaryController {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/compose")
	public String composeWord() {
		return getWord("cloud-client-eureka-one") + " "
				+ getWord("cloud-client-eureka-two") + " "
				+ getWord("cloud-client-eureka-three") + " "
				+ getWord("cloud-client-eureka-four") + " "
				+ getWord("cloud-client-eureka-five") + ".";
	}
	
	public String getWord(String service) {
		List<ServiceInstance> wordList = discoveryClient.getInstances(service);
		
		if (wordList != null && wordList.size() > 0) {
			
			URI uri = wordList.get(0).getUri();
			if (uri != null) {
				return (new RestTemplate()).getForObject(uri, String.class);
			}
			
		}
		
		return null;
	}
	
}
