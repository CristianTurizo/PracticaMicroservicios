package ServicioImagen.exceptionsHandling.exceptions;

public class AgeIncompatibilityExeption extends RuntimeException{

    public AgeIncompatibilityExeption(){
        super("Edad incompatible con el tipo de documento");
    }
}
