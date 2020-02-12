package mx.gob.imss.cit.mspmccapatres.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.mspmccapatres.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapatres.integration.enums.EnumHttpStatus;
import mx.gob.imss.cit.mspmccapatres.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapatres.integration.repositories.CapaTresRepository;
import mx.gob.imss.cit.mspmccapatres.services.CapaTresService;

@Service("capaTresService")
public class CapaTresServiceImpl implements CapaTresService {

	@Autowired
	private CapaTresRepository capaTresRepository;

	@Override
	public CapaTresModel consultarDato()  throws  BusinessException {
		// TODO Auto-generated method stub
		
		try {
			
			return capaTresRepository.consultarDato();
			
		}
		catch (Exception e) {
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Este es un mensaje de error de negocio",
					e.getMessage());
		}
	}
	
	
}
