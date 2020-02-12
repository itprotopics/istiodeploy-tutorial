package mx.gob.imss.cit.mspmccapados.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import mx.gob.imss.cit.mspmccapados.exceptions.BusinessException;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaDosModel;
import mx.gob.imss.cit.mspmccapados.integration.model.CapaTresModel;
import mx.gob.imss.cit.mspmccapados.integration.model.ErrorResponse;
import mx.gob.imss.cit.mspmccapados.services.CapaDosService;

@RestController
public class CapaDosController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CapaDosService capaDosService;
	
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
    @RequestMapping("/recursoscapados")
    public Object getRecursoCapaDos() {
    	
    	Object respuesta = null;
    	
        count++;
        

        logger.debug("mspmccapados service ready to return");
        
        CapaDosModel model;
        
        try {
	        model = capaDosService.capaDosMethod();
	        model.setCount(count);
	
	        respuesta = new ResponseEntity<CapaDosModel>(model, HttpStatus.OK);
	        
        }
        catch (BusinessException be) {
        	
        	ErrorResponse errorResponse = be.getErrorResponse();
        	
        	int numberHTTPDesired = Integer.parseInt(errorResponse.getCode());
  
            respuesta = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(numberHTTPDesired));
 
        }
        
        return respuesta;
    }
	
	
}
