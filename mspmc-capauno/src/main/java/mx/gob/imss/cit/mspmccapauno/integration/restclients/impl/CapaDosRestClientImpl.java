package mx.gob.imss.cit.mspmccapauno.integration.restclients.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import mx.gob.imss.cit.mspmccapauno.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapauno.integration.model.CapaDosModel;
import mx.gob.imss.cit.mspmccapauno.integration.restclients.CapaDosRestClient;

@Component("capaDosRestClient")
public class CapaDosRestClientImpl implements CapaDosRestClient {

	@Autowired
	private RestTemplate restTemplate;

//	@Value("${capadosservice.api.url:http://capadosservice:8080}")
//	@Value("${capadosservice.api.url:http://localhost:8180/recursoscapados}")
	@Value("${capadosservice.api.url}")
	private String remoteURL;

	@Override
	public CapaDosModel consultarCapaDos() throws BusinessException {
		// TODO Auto-generated method stub
		
		CapaDosModel capaDosModel = null;
		
		try {
			 ResponseEntity<CapaDosModel> responseEntity = restTemplate.getForEntity(remoteURL, CapaDosModel.class);
			 capaDosModel = responseEntity.getBody();
	         
		}
		catch (HttpStatusCodeException ex) {
			
		}
		catch (RestClientException ex) {
			
		}
		
		return capaDosModel;
		
	}
}
