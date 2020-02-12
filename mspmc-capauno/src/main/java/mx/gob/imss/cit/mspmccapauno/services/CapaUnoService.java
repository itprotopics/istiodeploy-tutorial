package mx.gob.imss.cit.mspmccapauno.services;


import mx.gob.imss.cit.mspmccapauno.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapauno.integration.model.CapaUnoModel;

public interface CapaUnoService {
	CapaUnoModel capaUnoMethod() throws BusinessException;
}
