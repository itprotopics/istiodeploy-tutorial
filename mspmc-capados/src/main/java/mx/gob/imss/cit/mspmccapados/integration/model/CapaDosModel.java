package mx.gob.imss.cit.mspmccapados.integration.model;

import lombok.Data;

@Data
public class CapaDosModel {
	
	private String nombreCapa;
	private String dato;
	private Integer count;
	private String hostname;
	
	private CapaTresModel capaTresModel;

}
