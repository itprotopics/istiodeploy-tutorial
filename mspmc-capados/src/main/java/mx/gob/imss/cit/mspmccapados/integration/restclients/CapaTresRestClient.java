package mx.gob.imss.cit.mspmccapados.integration.restclients;

import mx.gob.imss.cit.mspmccapados.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaTresModel;

public interface CapaTresRestClient {

	CapaTresModel consultarCapaTres() throws BusinessException;
}
