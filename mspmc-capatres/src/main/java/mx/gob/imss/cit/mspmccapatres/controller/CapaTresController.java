package mx.gob.imss.cit.mspmccapatres.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.imss.cit.mspmccapatres.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapatres.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapatres.integration.model.ErrorResponse;
import mx.gob.imss.cit.mspmccapatres.services.CapaTresService;

@RestController
public class CapaTresController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     * Counter to help us see the lifecycle
     */
    private int count = 0;
    
    private static final String HOSTNAME = parseContainerIdFromHostname(
            System.getenv().getOrDefault("HOSTNAME", "unknown"));

    static String parseContainerIdFromHostname(String hostname) {
        return hostname.replaceAll("recommendation-v\\d+-", "");
    }
    
    @Autowired
    private CapaTresService capaTresService;
    
    
    @RequestMapping("/health/ready")
    @ResponseStatus(HttpStatus.OK)
    public void ready() {}

    @RequestMapping("/health/live")
    @ResponseStatus(HttpStatus.OK)
    public void live() {}

    @RequestMapping("/recursoscapatres")
    public Object getRecursoCapaTres() {
    	
    	Object respuesta = null;
    	
        count++;
        logger.debug(String.format("getRecursoCapaTres request from %s: %d", HOSTNAME, count));

        logger.debug("mspmccapatres service ready to return");
        
        CapaTresModel model;
        
        try {
	        model = capaTresService.consultarDato();
	        
	        model.setCount(count);
	        respuesta = new ResponseEntity<CapaTresModel>(model, HttpStatus.OK);
	        
        }
        catch (BusinessException be) {
        	
        	ErrorResponse errorResponse = be.getErrorResponse();
        	
        	int numberHTTPDesired = Integer.parseInt(errorResponse.getCode());
  
            respuesta = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(numberHTTPDesired));
 
        }
        
        return respuesta;
    }
	
}
