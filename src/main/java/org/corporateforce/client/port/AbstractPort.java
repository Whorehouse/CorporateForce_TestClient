package org.corporateforce.client.port;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public abstract class AbstractPort {
	
	public static final String SERVER_URI = "http://localhost:8080/CorporateForce_Server/";
	
	public static LinkedHashMap get(String service, int id) {
		RestTemplate restTemplate = new RestTemplate();
		LinkedHashMap entity = restTemplate.getForObject(SERVER_URI +service + "/get/"+id, LinkedHashMap.class);
		return entity;
	}
	
	public static List<LinkedHashMap> list(String service) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> list = restTemplate.getForObject(SERVER_URI +service + "/list", List.class);
		return list;
	}
}
