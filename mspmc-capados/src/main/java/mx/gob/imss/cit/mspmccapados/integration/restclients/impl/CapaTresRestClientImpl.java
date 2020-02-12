package mx.gob.imss.cit.mspmccapados.integration.restclients.impl;

import org.springframework.stereotype.Component;

import mx.gob.imss.cit.mspmccapados.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapados.integration.restclients.CapaTresRestClient;

@Component("capaTresRestClient")
public class CapaTresRestClientImpl implements CapaTresRestClient {

	@Override
	public CapaTresModel consultarCapaTres() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
