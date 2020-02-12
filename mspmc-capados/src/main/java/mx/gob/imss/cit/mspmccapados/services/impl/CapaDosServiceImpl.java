package mx.gob.imss.cit.mspmccapados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.mspmccapados.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaDosModel;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapados.integration.restclients.CapaTresRestClient;
import mx.gob.imss.cit.mspmccapados.services.CapaDosService;

@Service("capaDosService")
public class CapaDosServiceImpl implements CapaDosService {

	@Autowired
	private CapaTresRestClient capaTresRestClient;
	
	@Override
	public CapaDosModel capaDosMethod() throws BusinessException {
		
		CapaDosModel capaDosModel = new CapaDosModel();
		
		CapaTresModel capaTresModel;
		
		capaDosModel.setDato("Este es un dato proveniente de la capa dos");
		capaDosModel.setNombreCapa("Dos");
		
		capaTresModel = capaTresRestClient.consultarCapaTres();
		
		capaDosModel.setCapaTresModel(capaTresModel);
		
		// TODO Auto-generated method stub
		return capaDosModel;
	}

}
