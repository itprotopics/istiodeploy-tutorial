package mx.gob.imss.cit.mspmccapatres.services;

import mx.gob.imss.cit.mspmccapatres.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapatres.integration.model.CapaTresModel;

public interface CapaTresService {

	CapaTresModel consultarDato() throws  BusinessException;
	
}
