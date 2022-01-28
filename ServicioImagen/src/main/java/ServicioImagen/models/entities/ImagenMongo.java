package ServicioImagen.models.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@Builder
@Document(value = "Imagen")
public class ImagenMongo {

    @Id
    private String id;

    private String imagen;

}
