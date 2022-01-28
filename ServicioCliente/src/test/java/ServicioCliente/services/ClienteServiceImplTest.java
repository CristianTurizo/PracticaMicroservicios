package ServicioCliente.services;

import ServicioCliente.exceptionsHandling.exceptions.AgeIncompatibilityExeption;
import ServicioCliente.exceptionsHandling.exceptions.DocumentAlreadyExistException;
import ServicioCliente.exceptionsHandling.exceptions.RegisterNotFoundException;
import ServicioCliente.feingclient.IImagenFeingClient;
import ServicioCliente.mappers.IMapper;
import ServicioCliente.models.DTO.ClienteDTO;
import ServicioCliente.models.DTO.ImagenDTO;
import ServicioCliente.models.entities.ClienteEntidad;
import ServicioCliente.repository.IClienteRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static ServicioCliente.Datos.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceImplTest {

    @MockBean
    IClienteRepository clienteRepository;

    @MockBean
    IMapper mapper;

    @MockBean
    IImagenFeingClient imagenFeingClient;

    @Autowired
    private IClienteService clienteService;

    @Nested
    class ObtenerClientes {
        @Test
        void ObtenerClientesReturn() {
            when(clienteRepository.findAll()).thenReturn(LISTA_CLIENTES_ENTIDAD);
            when(mapper.toClientesDto(LISTA_CLIENTES_ENTIDAD)).thenReturn(LISTA_CLIENTES_DTO);
            when(imagenFeingClient.obtenerPorId(anyString())).thenReturn(IMAGEN_DTO);

            var clientes = clienteService.obtenerClientes();

            verify(clienteRepository).findAll();
            verify(mapper).toClientesDto(any());
            verify(imagenFeingClient, times(2)).obtenerPorId(anyString());

            assertEquals(2, clientes.size());
        }

        @Test
        void obtenerClientesException() {
            var emptyListEntidad = new ArrayList<ClienteEntidad>();
            var emptyListDto = new ArrayList<ClienteDTO>();
            when(clienteRepository.findAll()).thenReturn(emptyListEntidad);
            when(mapper.toClientesDto(emptyListEntidad)).thenReturn(emptyListDto);

            assertThrows(RegisterNotFoundException.class, () ->
                    clienteService.obtenerClientes());

            verify(clienteRepository).findAll();
            verify(mapper).toClientesDto(any());
            verifyNoInteractions(imagenFeingClient);
        }
    }

    @Nested
    class ObtenerPorId {
        @Test
        void obtenerPorIdReturn() {
            Optional<ClienteEntidad> optionalEntidad = Optional.of(CLIENTE_ENTIDAD);
            when(clienteRepository.findById(1)).thenReturn(optionalEntidad);
            when(mapper.toClienteDto(optionalEntidad.get())).thenReturn(CLIENTE_DTO);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            var cliente = clienteService.obtenerPorId(1);

            verify(clienteRepository).findById(anyInt());
            verify(mapper).toClienteDto(any(ClienteEntidad.class));
            verify(imagenFeingClient).obtenerPorId(anyString());

            assertNotNull(cliente);

        }

        @Test
        void obtenerPorIdReturnException() {
            Optional<ClienteEntidad> optionalEntidad = Optional.of(CLIENTE_ENTIDAD);
            when(clienteRepository.findById(2)).thenReturn(Optional.empty());
            when(mapper.toClienteDto(optionalEntidad.get())).thenReturn(CLIENTE_DTO);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            assertThrows(RegisterNotFoundException.class, () ->
                    clienteService.obtenerPorId(1));


            verify(clienteRepository).findById(anyInt());
            verifyNoInteractions(mapper);
            verifyNoInteractions(imagenFeingClient);

        }

    }

    @Nested
    class ObtenerPorEdad {
        @Test
        void obtenerPorEdadReturn() {
            when(clienteRepository.findByEdadGreaterThanEqual(10)).thenReturn(LISTA_CLIENTES_ENTIDAD);
            when(mapper.toClientesDto(LISTA_CLIENTES_ENTIDAD)).thenReturn(LISTA_CLIENTES_DTO);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            var clientes = clienteService.obtenerPorEdadMayorA(10);

            verify(clienteRepository).findByEdadGreaterThanEqual(anyInt());
            verify(mapper).toClientesDto(any());
            verify(imagenFeingClient, times(clientes.size())).obtenerPorId(anyString());

            assertEquals(2, clientes.size());
            assertNotNull(clientes.get(1).getImagen());
            assertEquals("Imagen1", clientes.get(1).getImagen().getImagen());

        }

        @Test
        void obtenerPorEdadException() {
            var emptyListEntidad = new ArrayList<ClienteEntidad>();
            var emptyListDto = new ArrayList<ClienteDTO>();
            when(clienteRepository.findByEdadGreaterThanEqual(anyInt())).thenReturn(emptyListEntidad);
            when(mapper.toClientesDto(emptyListEntidad)).thenReturn(emptyListDto);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            assertThrows(RegisterNotFoundException.class, () ->
                    clienteService.obtenerPorEdadMayorA(50));

            verify(clienteRepository).findByEdadGreaterThanEqual(anyInt());
            verify(mapper).toClientesDto(any());
            verifyNoInteractions(imagenFeingClient);
        }
    }

    @Nested
    class ObtenerPorDocumentoYTipo {

        @Test
        void obtenerPorDocumentoYTipoReturn() {
            when(clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(
                    "1010122424", 1)).thenReturn(
                    Optional.of(CLIENTE_ENTIDAD));
            when(mapper.toClienteDto(any(ClienteEntidad.class))).thenReturn(CLIENTE_DTO);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            var cliente = clienteService.obtenerPorDocumentoYTipo("1010122424", 1);

            verify(clienteRepository).findByDocumentoAndTipoDocumento_idTipoDocumento(anyString(), anyInt());
            verify(mapper).toClienteDto(any(ClienteEntidad.class));
            verify(imagenFeingClient).obtenerPorId(anyString());

            assertNotNull(cliente);
        }

        @Test
        void obtenerPorDocumentoYTipoException() {
            when(clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(
                    "1010122424", 1)).thenReturn(
                    Optional.empty());

            assertThrows(RegisterNotFoundException.class, () ->
                    clienteService.obtenerPorDocumentoYTipo("1010122424", 1));

            verify(clienteRepository).findByDocumentoAndTipoDocumento_idTipoDocumento(anyString(), anyInt());
            verifyNoInteractions(mapper);
            verifyNoInteractions(imagenFeingClient);

        }
    }

    @Nested
    class Guardar {

        @Test
        void GuardarReturn() {
            when(imagenFeingClient.guardarImagen(IMAGEN_INICIAL)).thenReturn(IMAGEN_DTO);
            when(mapper.toClienteEntidad(CLIENTE_INICIAL_DTO)).thenReturn(CLIENTE_INICIAL_ENTIDAD);
            when(clienteRepository.save(CLIENTE_INICIAL_ENTIDAD)).thenReturn(CLIENTE_ENTIDAD);
            when(mapper.toClienteDto(CLIENTE_ENTIDAD)).thenReturn(CLIENTE_DTO);

            var clientegGuardado = clienteService.guardarCliente(CLIENTE_INICIAL_DTO);

            verify(imagenFeingClient).guardarImagen(any(ImagenDTO.class));
            verify(mapper).toClienteEntidad(any(ClienteDTO.class));
            verify(clienteRepository).save(any(ClienteEntidad.class));
            verify(mapper).toClienteDto(any(ClienteEntidad.class));

            assertNotNull(clientegGuardado);
            assertEquals(1, clientegGuardado.getIdClientes());
            assertEquals("61df70530c3d0d7fbfe0e708",clientegGuardado.getIdImagen());
            assertEquals(IMAGEN_DTO, clientegGuardado.getImagen());
        }

        @Test
        void guardarAgeException() {
            assertThrows(AgeIncompatibilityExeption.class, () ->
                    clienteService.guardarCliente(CLIENTE_IMCOMPATIBILIDAD_EDAD1));
            assertThrows(AgeIncompatibilityExeption.class, () ->
                    clienteService.guardarCliente(CLIENTE_IMCOMPATIBILIDAD_EDAD2));

            verifyNoInteractions(imagenFeingClient);
            verifyNoInteractions(mapper);
            verifyNoInteractions(clienteRepository);
        }

        @Test
        void guardarDocumentException() {
            when(clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(
                    anyString(), anyInt())).thenReturn(Optional.of(CLIENTE_ENTIDAD));
            when(mapper.toClienteDto(any(ClienteEntidad.class))).thenReturn(CLIENTE_DTO);

            assertThrows(DocumentAlreadyExistException.class, () ->
                    clienteService.guardarCliente(CLIENTE_IMCOMPATIBILIDAD_DOCUMENTO));

            verifyNoInteractions(imagenFeingClient);
            verify(mapper, never()).toClienteEntidad(any(ClienteDTO.class));
            verify(clienteRepository, never()).save(any(ClienteEntidad.class));

        }
    }

    @Nested
    class Modifcar {

        @Test
        void modficarReturn() {
            when(clienteRepository.findById(1)).thenReturn(Optional.of(CLIENTE_ENTIDAD));
            when(mapper.toClienteDto(CLIENTE_ENTIDAD)).thenReturn(CLIENTE_DTO);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            when(imagenFeingClient.modificarImagen(IMAGEN_DTO_MODIFICADA)).thenReturn(IMAGEN_DTO_MODIFICADA);
            when(mapper.toClienteEntidad(any(ClienteDTO.class))).thenReturn(CLIENTE_ENTIDAD_MODIFICADO);
            when(clienteRepository.save(CLIENTE_ENTIDAD_MODIFICADO)).thenReturn(CLIENTE_ENTIDAD_MODIFICADO);
            when(mapper.toClienteDto(CLIENTE_ENTIDAD_MODIFICADO)).thenReturn(CLIENTE_DTO_MODIFICADO);

            var clienteModificado = clienteService.moficarCliente(CLIENTE_INICIAL_MODIFICADO);

            verify(clienteRepository).findById(anyInt());
            verify(mapper, times(2)).toClienteDto(any(ClienteEntidad.class));
            verify(imagenFeingClient).obtenerPorId(anyString());
            verify(imagenFeingClient).modificarImagen(any(ImagenDTO.class));
            verify(mapper).toClienteEntidad(any(ClienteDTO.class));
            verify(clienteRepository).save(any(ClienteEntidad.class));

            assertEquals(1, clienteModificado.getIdClientes());
            assertEquals(15, clienteModificado.getEdad());
            assertEquals(IMAGEN_DTO_MODIFICADA, clienteModificado.getImagen());
            assertEquals(2, clienteModificado.getTipoDocumento().getIdTipoDocumento());

        }

        @Test
        void modificarAgeException() {
            assertThrows(AgeIncompatibilityExeption.class, () ->
                    clienteService.moficarCliente(CLIENTE_IMCOMPATIBILIDAD_EDAD1));
            assertThrows(AgeIncompatibilityExeption.class, () ->
                    clienteService.moficarCliente(CLIENTE_IMCOMPATIBILIDAD_EDAD2));

            verifyNoInteractions(imagenFeingClient);
            verifyNoInteractions(mapper);
            verifyNoInteractions(clienteRepository);
        }

        @Test
        void modificarDocumentException() {
            when(clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(
                    anyString(), anyInt())).thenReturn(Optional.of(CLIENTE_ENTIDAD));
            when(mapper.toClienteDto(any(ClienteEntidad.class))).thenReturn(CLIENTE_DTO);

            assertThrows(DocumentAlreadyExistException.class, () ->
                    clienteService.moficarCliente(CLIENTE_IMCOMPATIBILIDAD_DOCUMENTO));

            verifyNoInteractions(imagenFeingClient);
            verify(mapper, never()).toClienteEntidad(any(ClienteDTO.class));
            verify(clienteRepository, never()).save(any(ClienteEntidad.class));

        }
    }

    @Nested
    class EliminarCliente {
        @Test
        void elimianrClienteReturn() {
            Optional<ClienteEntidad> optionalEntidad = Optional.of(CLIENTE_ENTIDAD);
            when(clienteRepository.findById(1)).thenReturn(optionalEntidad);
            when(mapper.toClienteDto(optionalEntidad.get())).thenReturn(CLIENTE_DTO);
            when(imagenFeingClient.obtenerPorId("61df70530c3d0d7fbfe0e708")).thenReturn(IMAGEN_DTO);

            when(imagenFeingClient.eliminarImagen(anyString())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
            doNothing().when(clienteRepository).deleteById(anyInt());

            clienteService.eliminarCliente(1);

            verify(clienteRepository).findById(anyInt());
            verify(mapper).toClienteDto(any(ClienteEntidad.class));
            verify(imagenFeingClient).obtenerPorId(anyString());
            verify(imagenFeingClient).eliminarImagen(anyString());
            verify(clienteRepository).deleteById(anyInt());

        }
    }

    @Nested
    class ValidarDocumento {

        @Test
        void obtenerPorDocumentoYTipoReturn() {
            when(clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(
                    "1010122424", 1)).thenReturn(
                    Optional.of(CLIENTE_ENTIDAD));
            when(mapper.toClienteDto(any(ClienteEntidad.class))).thenReturn(CLIENTE_DTO);

            var cliente = clienteService.validarDocumento("1010122424", 1);

            verify(clienteRepository).findByDocumentoAndTipoDocumento_idTipoDocumento(anyString(), anyInt());
            verify(mapper).toClienteDto(any(ClienteEntidad.class));

            assertNotNull(cliente);
        }

        @Test
        void obtenerPorDocumentoYTipoException() {
            when(clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(
                    anyString(), anyInt())).thenReturn(
                    Optional.empty());

            var cliente = clienteService.validarDocumento("1010122424", 1);

            verify(clienteRepository).findByDocumentoAndTipoDocumento_idTipoDocumento(anyString(), anyInt());
            verify(mapper).toClienteDto(any());

            assertNull(cliente);

        }
    }

}