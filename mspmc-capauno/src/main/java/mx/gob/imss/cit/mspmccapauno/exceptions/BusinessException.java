package mx.gob.imss.cit.mspmccapauno.exceptions;

import mx.gob.imss.cit.mspmccapauno.integration.enums.EnumHttpStatus;
import mx.gob.imss.cit.mspmccapauno.integration.model.ErrorResponse;

public class BusinessException extends Exception {

	  /**
	   *
	   */
	  private static final long serialVersionUID = -872483897865904849L;

	  private ErrorResponse errorResponse;

	  public BusinessException(EnumHttpStatus status, String businessMessage, String reasonPhrase) {

	    super(reasonPhrase);

	    errorResponse = new ErrorResponse(status, businessMessage, reasonPhrase);
	  }

	  public BusinessException(ErrorResponse errorResponse) {
	    this.errorResponse = errorResponse;
	  }

	  public ErrorResponse getErrorResponse() {
	    return errorResponse;
	  }

	  public void setErrorResponse(ErrorResponse errorResponse) {
	    this.errorResponse = errorResponse;
	  }

	}