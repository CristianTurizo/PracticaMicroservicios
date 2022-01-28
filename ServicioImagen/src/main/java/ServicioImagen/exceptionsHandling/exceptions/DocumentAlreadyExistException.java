package ServicioImagen.exceptionsHandling.exceptions;

public class DocumentAlreadyExistException extends RuntimeException{

    public DocumentAlreadyExistException(){
        super("El numero de documento ya se encuentra en la base de datos");
    }
}
