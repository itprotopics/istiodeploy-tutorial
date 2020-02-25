package mx.gob.imss.cit.mspmccapauno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.Tracer;
import mx.gob.imss.cit.mspmccapauno.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapauno.integration.enums.EnumHttpStatus;
import mx.gob.imss.cit.mspmccapauno.integration.model.CapaUnoModel;
import mx.gob.imss.cit.mspmccapauno.integration.model.ErrorResponse;
import mx.gob.imss.cit.mspmccapauno.services.CapaUnoService;

@RestController
public class CapaUnoController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CapaUnoService capaUnoService;
	
	private int count = 0;
	
    @RequestMapping("/health/ready")
    @ResponseStatus(HttpStatus.OK)
    public void ready() {}

    @RequestMapping("/health/live")
    @ResponseStatus(HttpStatus.OK)
    public void live() {}
    
    @Autowired
    private Tracer tracer;
	
    @RequestMapping("/recursoscapauno")
    public Object getRecursoCapaUno(@RequestHeader("User-Agent") String userAgent, 
    		@RequestHeader(value = "user-preference", required = false) String userPreference) {
    	
    	Object respuesta = null;
    	
        count++;
        logger.debug("mspmccapauno service ready to return");
        
        CapaUnoModel model;
        
        try {
        	
        	try {
	        	tracer.activeSpan().setBaggageItem("user-agent", userAgent);
	            if (userPreference != null && !userPreference.isEmpty()) {
	                tracer.activeSpan().setBaggageItem("user-preference", userPreference);
	            }
        	}
        	catch (Exception ex) {
        		throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Este es un mensaje de error de negocio",
    					ex.getMessage());
        	}
            
	        model = capaUnoService.capaUnoMethod();
	        model.setCount(count);
	
	        respuesta = new ResponseEntity<CapaUnoModel>(model, HttpStatus.OK);
	        
        }
        catch (BusinessException be) {
        	
        	ErrorResponse errorResponse = be.getErrorResponse();
        	
        	int numberHTTPDesired = Integer.parseInt(errorResponse.getCode());
  
            respuesta = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(numberHTTPDesired));
 
        }
        
        
        return respuesta;
    }
	
	
    
}
