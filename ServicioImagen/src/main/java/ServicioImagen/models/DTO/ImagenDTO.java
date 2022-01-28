package ServicioImagen.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ImagenDTO {

    @JsonProperty("idImagen")
    private String id;

    @JsonProperty("imagen")
    private String imagen;

}
