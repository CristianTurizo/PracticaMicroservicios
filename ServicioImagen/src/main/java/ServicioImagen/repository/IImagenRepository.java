package ServicioImagen.repository;

import ServicioImagen.models.entities.ImagenMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IImagenRepository extends MongoRepository<ImagenMongo, String> {
    Optional<ImagenMongo> findById(String id);

    // void deleteById(String id);

}
