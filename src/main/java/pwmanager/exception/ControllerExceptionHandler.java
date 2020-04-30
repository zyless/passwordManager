package pwmanager.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler{

	@Autowired
	ResponseMsg message;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ResponseMsg handleNumberTypeException(NumberFormatException exception) {
	 message.setMessage("Bitte tragen Sie eine Zahl ein");
	 return message;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseMsg handleNullTypeException(HttpMessageConversionException exception) {
	 message.setMessage("Objekt wurde nicht gefunden");
	 return message;
	
	}


}
