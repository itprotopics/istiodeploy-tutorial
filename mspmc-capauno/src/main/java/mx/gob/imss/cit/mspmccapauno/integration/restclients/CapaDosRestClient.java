package mx.gob.imss.cit.mspmccapauno.integration.restclients;

import mx.gob.imss.cit.mspmccapauno.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapauno.integration.model.CapaDosModel;

public interface CapaDosRestClient {

	CapaDosModel consultarCapaDos() throws BusinessException;
}
