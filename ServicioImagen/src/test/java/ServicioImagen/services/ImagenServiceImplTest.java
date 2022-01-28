package ServicioImagen.services;

import ServicioImagen.mappers.IMapper;
import ServicioImagen.models.DTO.ImagenDTO;
import ServicioImagen.models.entities.ImagenMongo;
import ServicioImagen.repository.IImagenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ServicioImagen.Datos.*;

class ImagenServiceImplTest {

    @MockBean
    private IImagenRepository imagenRepository;

    @MockBean
    private IMapper mapper;

    IImagenService imagenService;

    @BeforeEach
    void setUp() {
        this.imagenRepository = mock(IImagenRepository.class);
        this.mapper = mock(IMapper.class);
        imagenService = new ImagenServiceImpl(this.imagenRepository, this.mapper);
    }

    @Test
    void obtenerPorIdReturnImagen() {
        when(this.imagenRepository.findById("61df70530c3d0d7fbfe0e708"))
                .thenReturn(Optional.ofNullable(IMAGEN_ENTIDAD));

        when(this.mapper.toImagenDTO(IMAGEN_ENTIDAD)).thenReturn(IMAGEN_DTO);

        ImagenDTO imagenObtenida = imagenService.obtenerPorId("61df70530c3d0d7fbfe0e708");

        assertEquals("Imagen1", imagenObtenida.getImagen());

        verify(this.mapper).toImagenDTO(any(ImagenMongo.class));
        verify(this.imagenRepository).findById(anyString());
    }

    @Test
    void obtenerPorIdReturnNull() {
        when(this.imagenRepository.findById(anyString())).thenReturn(Optional.empty());

        ImagenDTO imagenObtenida = this.imagenService.obtenerPorId("a");

        assertNull(imagenObtenida);
        verify(this.imagenRepository).findById(anyString());
    }

    @Test
    void guardarReturnImagen() {
        when(this.mapper.toImagenMongo(IMAGEN_INICIAL)).thenReturn(IMAGEN_INICIAL_ENTIDAD);
        when(this.imagenRepository.save(IMAGEN_INICIAL_ENTIDAD)).thenReturn(IMAGEN_ENTIDAD);
        when(this.mapper.toImagenDTO(IMAGEN_ENTIDAD)).thenReturn(IMAGEN_DTO);

        ImagenDTO imagenGuardada = this.imagenService.guardarImagen(IMAGEN_INICIAL);

        assertEquals("61df70530c3d0d7fbfe0e708", imagenGuardada.getId());
        verify(this.mapper).toImagenMongo(any(ImagenDTO.class));
        verify(this.imagenRepository).save(any(ImagenMongo.class));
        verify(this.mapper).toImagenDTO(any(ImagenMongo.class));
    }

    @Test
    void eliminarTest() {
        doNothing().when(this.imagenRepository).deleteById("61df70530c3d0d7fbfe0e708");

        imagenService.eliminarImagen("61df70530c3d0d7fbfe0e708");

        verify(this.imagenRepository).deleteById(anyString());
    }

}