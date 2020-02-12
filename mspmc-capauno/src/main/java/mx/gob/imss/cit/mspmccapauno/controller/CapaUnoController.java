package mx.gob.imss.cit.mspmccapauno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.imss.cit.mspmccapauno.exceptions.BusinessException;
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
    
//	@Bean
//	public io.opentracing.Tracer tracer() {
//		return Configuration.fromEnv().getTracer();
//	}
//	
    @RequestMapping("/recursoscapauno")
    public Object getRecursoCapaUno() {
    	
    	Object respuesta = null;
    	
        count++;
        

        logger.debug("mspmccapauno service ready to return");
        
        CapaUnoModel model;
        
        try {
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
