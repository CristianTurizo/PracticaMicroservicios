package ServicioCliente.feingclient;

import ServicioCliente.models.DTO.ImagenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public class ImagenFeingFallback implements IImagenFeingClient {

    @Override
    public ImagenDTO obtenerPorId(String id) {
        return ImagenDTO.builder()
                .id("String")
                .imagen("String")
                .build();
    }

    @Override
    public ImagenDTO guardarImagen(ImagenDTO imagen) {
        return ImagenDTO.builder()
                .id("String")
                .imagen("String")
                .build();
    }

    @Override
    public ImagenDTO modificarImagen(ImagenDTO imagen) {
        return ImagenDTO.builder()
                .id("String")
                .imagen("String")
                .build();
    }

    @Override
    public ResponseEntity eliminarImagen(String id) {
        return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
