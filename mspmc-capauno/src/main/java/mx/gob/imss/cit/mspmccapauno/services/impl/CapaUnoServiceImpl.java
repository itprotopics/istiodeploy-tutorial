package mx.gob.imss.cit.mspmccapauno.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.mspmccapauno.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapauno.integration.model.CapaDosModel;
import mx.gob.imss.cit.mspmccapauno.integration.model.CapaUnoModel;
import mx.gob.imss.cit.mspmccapauno.integration.restclients.CapaDosRestClient;
import mx.gob.imss.cit.mspmccapauno.services.CapaUnoService;

@Service("capaUnoService")
public class CapaUnoServiceImpl implements CapaUnoService {

	@Autowired
	private CapaDosRestClient capaDosRestClient;
	
	@Override
	public CapaUnoModel capaUnoMethod() throws BusinessException {
		
		CapaUnoModel capaUnoModel = new CapaUnoModel();
		
		CapaDosModel capaDosModel;
		
		capaUnoModel.setDato("Este es un dato proveniente de la capa uno");
		capaUnoModel.setNombreCapa("Uno");
		
		capaDosModel = capaDosRestClient.consultarCapaDos();
		
		capaUnoModel.setCapaDosModel(capaDosModel);
		
		// TODO Auto-generated method stub
		return capaUnoModel;
	}

}
