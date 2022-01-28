package ServicioCliente.feingclient;

import ServicioCliente.models.DTO.ImagenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "imagen", fallbackFactory = FallbackFactory.class)
public interface IImagenFeingClient {

    @GetMapping("/{id}")
    ImagenDTO obtenerPorId(@PathVariable String id);

    @PostMapping
    ImagenDTO guardarImagen(@RequestBody ImagenDTO imagen);

    @PutMapping
    ImagenDTO modificarImagen(@RequestBody ImagenDTO imagen);

    @DeleteMapping(path = "/{id}")
    ResponseEntity eliminarImagen(@PathVariable String id);

}
