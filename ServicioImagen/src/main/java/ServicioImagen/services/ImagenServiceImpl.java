package ServicioImagen.services;

import ServicioImagen.mappers.IMapper;
import ServicioImagen.models.DTO.ImagenDTO;
import ServicioImagen.repository.IImagenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImagenServiceImpl implements IImagenService {

    private final IImagenRepository imagenRepository;
    private final IMapper mapper;

    @Override
    public ImagenDTO obtenerPorId(String id) {
        return this.mapper.toImagenDTO(
                this.imagenRepository.findById(id).orElse(null));
    }

    @Override
    public ImagenDTO guardarImagen(ImagenDTO imagen) {
        return this.mapper.toImagenDTO(
                this.imagenRepository.save(
                        this.mapper.toImagenMongo(imagen)));
    }

    @Override
    public void eliminarImagen(String id) {
        try {
            this.imagenRepository.deleteById(id);
        } catch (Exception e) {
            log.warn("No hay imagen");
        }
    }
}
