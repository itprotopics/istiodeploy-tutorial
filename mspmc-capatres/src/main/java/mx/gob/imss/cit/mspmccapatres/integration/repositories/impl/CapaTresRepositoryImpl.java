package mx.gob.imss.cit.mspmccapatres.integration.repositories.impl;

import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.mspmccapatres.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapatres.integration.repositories.CapaTresRepository;

@Repository("capaTresRepository")
public class CapaTresRepositoryImpl implements CapaTresRepository {

	@Override
	public CapaTresModel consultarDato() {
		// TODO Auto-generated method stub
		
		CapaTresModel model = new CapaTresModel();
		
		model.setDato("Este es un dato proveniente del MS de capa tres");
		model.setNombreCapa("Tres");
	
		
		return model;
	}

}
