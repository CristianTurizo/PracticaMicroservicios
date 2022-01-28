package ServicioImagen.exceptionsHandling.errorMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorMessage {

    @JsonProperty("message")
    private final String message;

    @JsonProperty("statusCode")
    private final int statusCode;

    @JsonProperty("method")
    private final String httpMethod;

    @JsonProperty("uri")
    private final String uriRequested;

    public ErrorMessage(String message, int statusCode, String httpMethod, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.httpMethod = httpMethod;
    }

}
