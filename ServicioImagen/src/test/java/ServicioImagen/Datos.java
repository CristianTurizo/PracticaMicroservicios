package ServicioImagen;

import ServicioImagen.models.DTO.ImagenDTO;
import ServicioImagen.models.entities.ImagenMongo;

public class Datos {
    public static final ImagenDTO IMAGEN_INICIAL = ImagenDTO.builder()
            .imagen("Imagen1")
            .build();

    public static final ImagenMongo IMAGEN_INICIAL_ENTIDAD = ImagenMongo.builder()
            .imagen("Imagen1")
            .build();

    public static final ImagenMongo IMAGEN_ENTIDAD = ImagenMongo.builder()
            .id("61df70530c3d0d7fbfe0e708")
            .imagen("Imagen1")
            .build();

    public static final ImagenDTO IMAGEN_DTO = ImagenDTO.builder()
            .id("61df70530c3d0d7fbfe0e708")
            .imagen("Imagen1")
            .build();

}
