package ServicioCliente.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ImagenDTO {
    @JsonProperty("idImagen")
    private String id;

    @NotBlank(message = "Campo Vacio")
    @JsonProperty("imagen")
    private String imagen;
}
