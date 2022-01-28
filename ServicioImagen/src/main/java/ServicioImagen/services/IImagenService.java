package ServicioImagen.services;

import ServicioImagen.models.DTO.ImagenDTO;

public interface IImagenService {

    ImagenDTO obtenerPorId(String id);

    ImagenDTO guardarImagen(ImagenDTO imagen);

    void eliminarImagen(String id);
}
