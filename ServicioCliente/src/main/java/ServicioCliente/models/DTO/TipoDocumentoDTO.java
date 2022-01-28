package ServicioCliente.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Data
@Builder
public class TipoDocumentoDTO {

    @Positive(message = "El valor debe ser mayor que 0")
    @Max(value = 2, message = "El valor debe ser menor que 3")
    @JsonProperty("idTipoDocumento")
    private int idTipoDocumento;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

}
