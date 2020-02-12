package mx.gob.imss.cit.mspmccapauno.integration.model;

import lombok.Data;

@Data
public class CapaDosModel {
	
	private String nombreCapa;
	private String dato;
	private Integer count;

	
	private CapaTresModel capaTresModel;

}
