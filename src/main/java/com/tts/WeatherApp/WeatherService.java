package com.tts.WeatherApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
	@Autowired
	private RequestRepository requestRepository;
	
	@Value("${api_key}")
	private String apiKey;
	
	public Response getForecast(String zipCode) {
		requestRepository.save(new Request(zipCode));
		String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&appid="+apiKey;
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(url,  Response.class);
		}
		catch (HttpClientErrorException ex) {
		    Response response = new Response();
		    response.setName("error");
		    return response;
		}
	}
	
	//returns 10 most recent zipcode searches
	public List<Request> getRequests() {
		List<Request> rlist = new ArrayList<>(requestRepository.findAll());
		Collections.reverse(rlist);
		rlist = rlist.subList(0, 10);
		System.out.println(rlist);
		return rlist;
	}
	
}
