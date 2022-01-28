package ServicioCliente.exceptionsHandling.exceptionHandler;

import ServicioCliente.exceptionsHandling.errorMessage.ErrorMessage;
import ServicioCliente.exceptionsHandling.exceptions.AgeIncompatibilityExeption;
import ServicioCliente.exceptionsHandling.exceptions.DocumentAlreadyExistException;
import ServicioCliente.exceptionsHandling.exceptions.RegisterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class MyExceptionHandler {

    // Manejo de exepcion para cuando no se encuentra un registro
    @ExceptionHandler(RegisterNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> registerNotFoundException(HttpServletRequest request, RegisterNotFoundException e) {
        var errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    // Manejo de exepcion para cuando se repite el tipo y el documento
    @ExceptionHandler(DocumentAlreadyExistException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorMessage> documentAlreadyExistException(HttpServletRequest request, DocumentAlreadyExistException e) {
        var errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.FORBIDDEN.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    // Manejo de exepcion para cuando no son compatibles la edad con el documento
    @ExceptionHandler(AgeIncompatibilityExeption.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorMessage> ageIncompatibilityExeption(HttpServletRequest request, AgeIncompatibilityExeption e) {
        var errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.FORBIDDEN.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    // Manejo de excepcion para cuando no se cumple con la validacion de los campos del Json
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> argumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        var result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        var message = new StringBuilder();
        fieldErrors.forEach(field ->
                message.append(field.getField()).append(": ").append(field.getDefaultMessage()).append(". "));

        var errorMessage = new ErrorMessage(message.toString(), HttpStatus.BAD_REQUEST.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepcion para cuando se envía un Json no valido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> notReadableException(HttpServletRequest request) {
        var message = "Verifique los campos de Json";
        var errorMessage = new ErrorMessage(message, HttpStatus.BAD_REQUEST.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepcion para cuando el tipo del valor del paramatro no es el correspondiente
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> invalidTypeOfSearchException(HttpServletRequest request) {
        var message = "Verifique el tipo del valor de los parametros de busqueda";
        var errorMessage = new ErrorMessage(message, HttpStatus.NOT_ACCEPTABLE.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
    }

    // Manejo de excepcion para cuando el nombre del de la url parametro no es correcto o no se envía parametro
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorMessage> notParamFoundException(HttpServletRequest request) {
        var message = "El parametro en el path de acceso no existe";
        var errorMessage = new ErrorMessage(message, HttpStatus.BAD_REQUEST.value(), request.getMethod(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
