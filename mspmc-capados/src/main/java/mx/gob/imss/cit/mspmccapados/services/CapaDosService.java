package mx.gob.imss.cit.mspmccapados.services;

import mx.gob.imss.cit.mspmccapados.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaDosModel;

public interface CapaDosService {
	CapaDosModel capaDosMethod() throws BusinessException;
}
