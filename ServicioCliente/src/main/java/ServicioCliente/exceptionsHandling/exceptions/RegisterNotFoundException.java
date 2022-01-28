package ServicioCliente.exceptionsHandling.exceptions;

public class RegisterNotFoundException extends RuntimeException {

    public RegisterNotFoundException() {
        super("No se encontraron resgistros");
    }
}
