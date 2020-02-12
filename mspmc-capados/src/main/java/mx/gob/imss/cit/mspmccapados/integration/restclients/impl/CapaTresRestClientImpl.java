package mx.gob.imss.cit.mspmccapados.integration.restclients.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import mx.gob.imss.cit.mspmccapados.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapados.integration.restclients.CapaTresRestClient;

@Component("capaTresRestClient")
public class CapaTresRestClientImpl implements CapaTresRestClient {

	@Autowired
	private RestTemplate restTemplate;

//	@Value("${capatresservice.api.url:http://capatresservice:8080}")
//	@Value("${capatresservice.api.url:http://localhost:8180/recursoscapatres}")
	@Value("${capatresservice.api.url}")
	private String remoteURL;

	@Override
	public CapaTresModel consultarCapaTres() throws BusinessException {
		// TODO Auto-generated method stub
		
		CapaTresModel capaTresModel = null;
		
		try {
			 ResponseEntity<CapaTresModel> responseEntity = restTemplate.getForEntity(remoteURL, CapaTresModel.class);
			 capaTresModel = responseEntity.getBody();
	         
		}
		catch (HttpStatusCodeException ex) {
			
		}
		catch (RestClientException ex) {
			
		}
		
		return capaTresModel;
		
	}
}
