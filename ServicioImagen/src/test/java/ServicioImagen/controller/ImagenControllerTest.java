package ServicioImagen.controller;

import ServicioImagen.models.DTO.ImagenDTO;
import ServicioImagen.services.IImagenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static ServicioImagen.Datos.IMAGEN_DTO;
import static ServicioImagen.Datos.IMAGEN_INICIAL;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImagenController.class)
class ImagenControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IImagenService imagenService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void obtenerPorIdTest() throws Exception {
        when(imagenService.obtenerPorId("61df70530c3d0d7fbfe0e708"))
                .thenReturn(IMAGEN_DTO);

        mvc.perform(MockMvcRequestBuilders.get("/61df70530c3d0d7fbfe0e708")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imagen").value("Imagen1"));

        verify(imagenService).obtenerPorId("61df70530c3d0d7fbfe0e708");
    }

    @Test
    void guardarTest() throws Exception {

        when(imagenService.guardarImagen(IMAGEN_INICIAL)).thenReturn(IMAGEN_DTO);

        mvc.perform(MockMvcRequestBuilders.post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(IMAGEN_INICIAL)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idImagen").value("61df70530c3d0d7fbfe0e708"));

        verify(imagenService).guardarImagen(any(ImagenDTO.class));
    }

    @Test
    void modificarTest() throws Exception {
        when(imagenService.guardarImagen(IMAGEN_INICIAL)).thenReturn(IMAGEN_DTO);

        mvc.perform(MockMvcRequestBuilders.put("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(IMAGEN_INICIAL)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idImagen").value("61df70530c3d0d7fbfe0e708"))
                .andExpect(jsonPath("$.imagen").value("Imagen1"));

        verify(imagenService).guardarImagen(any(ImagenDTO.class));
    }

    @Test
    void eliminarTest() throws Exception {
        String id = "61df70530c3d0d7fbfe0e708";

        doNothing().when(imagenService).eliminarImagen(id);

        mvc.perform(MockMvcRequestBuilders.delete("/61df70530c3d0d7fbfe0e708"))
                .andExpect(status().isNoContent());

        verify(imagenService).eliminarImagen(id);
    }

}