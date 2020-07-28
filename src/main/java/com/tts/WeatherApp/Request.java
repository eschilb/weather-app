package com.tts.WeatherApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String zipCode;
	
	public Request() {
		
	}
	
	public Request(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", zipCode=" + zipCode + "]";
	}
	
}
