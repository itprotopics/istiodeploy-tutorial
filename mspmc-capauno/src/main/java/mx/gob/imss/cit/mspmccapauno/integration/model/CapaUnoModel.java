package mx.gob.imss.cit.mspmccapauno.integration.model;

import lombok.Data;

@Data
public class CapaUnoModel {

	private String nombreCapa;
	private String dato;
	private Integer count;

	
	private CapaDosModel capaDosModel;

	
}
