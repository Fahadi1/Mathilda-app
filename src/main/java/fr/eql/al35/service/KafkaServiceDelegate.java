package fr.eql.al35.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import fr.eql.al35.entity.Command;
import fr.eql.al35.iservice.KafkaIService;

@Service
public class KafkaServiceDelegate implements KafkaIService {
	
	private RestTemplate restTemplate;
	private String baseUrl = "http://localhost:8180//wsrest/publisherKafka/producer/";

	public KafkaServiceDelegate() {
		restTemplate = initRestTemplate();
	}
	
	public RestTemplate initRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		}
		interceptors.add(new RestTemplateHeaderModifierInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Override
	public String sendNewCommandToKafka(Command command) {
		restTemplate.postForObject(baseUrl + "/command", command, Command.class);
		return "Service Kafka opérationnel";
	}

}
