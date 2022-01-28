package ServicioImagen.mappers;

import ServicioImagen.models.DTO.ImagenDTO;
import ServicioImagen.models.entities.ImagenMongo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);

    ImagenDTO toImagenDTO(ImagenMongo imagenMongo);

    ImagenMongo toImagenMongo(ImagenDTO imagenDTO);
}
