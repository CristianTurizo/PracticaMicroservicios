package ServicioImagen.controller;

import ServicioImagen.models.DTO.ImagenDTO;
import ServicioImagen.services.IImagenService;
import ServicioImagen.services.ImagenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("imagenes")
@RequiredArgsConstructor
public class ImagenController {

    private final IImagenService imagenService;

    @GetMapping("/{id}")
    public ImagenDTO obtenerPorId(@PathVariable String id) {
        return this.imagenService.obtenerPorId(id);
    }

    @PostMapping
    public ImagenDTO guardarImagen(@RequestBody ImagenDTO imagen) {
        return this.imagenService.guardarImagen(imagen);
    }

    @PutMapping
    public ImagenDTO modificarImagen(@RequestBody ImagenDTO imagen){
        return this.imagenService.guardarImagen(imagen);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity eliminarImagen(@PathVariable String id) {
        this.imagenService.eliminarImagen(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
