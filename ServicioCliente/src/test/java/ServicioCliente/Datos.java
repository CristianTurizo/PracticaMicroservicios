package ServicioCliente;

import ServicioCliente.models.DTO.CiudadDTO;
import ServicioCliente.models.DTO.ClienteDTO;
import ServicioCliente.models.DTO.ImagenDTO;
import ServicioCliente.models.DTO.TipoDocumentoDTO;
import ServicioCliente.models.entities.CiudadEntidad;
import ServicioCliente.models.entities.ClienteEntidad;
import ServicioCliente.models.entities.TipoDocumentoEntidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datos {
    public static final ClienteDTO CLIENTE_INICIAL_DTO = ClienteDTO.builder()
            .nombre("Cristian")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(1)
                    .build())
            .documento("1010122424")
            .edad(21)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen1")
                    .build())
            .build();

    public static final ClienteEntidad CLIENTE_INICIAL_ENTIDAD = ClienteEntidad.builder()
            .nombre("Cristian")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoEntidad.builder()
                    .idTipoDocumento(1)
                    .build())
            .documento("1010122424")
            .edad(21)
            .ciudadNacimiento(CiudadEntidad.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen1")
                    .build())
            .build();

    public static final ClienteEntidad CLIENTE_ENTIDAD = ClienteEntidad.builder()
            .idClientes(1)
            .nombre("Cristian")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoEntidad.builder()
                    .idTipoDocumento(1)
                    .tipoDocumento("CC")
                    .build())
            .documento("1010122424")
            .edad(21)
            .ciudadNacimiento(CiudadEntidad.builder()
                    .idCiudad(1)
                    .ciudad("Sincelejo")
                    .build())
            .idImagen("61df70530c3d0d7fbfe0e708")
            .build();


    public static final ClienteDTO CLIENTE_DTO = ClienteDTO.builder()
            .idClientes(1)
            .nombre("Cristian")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(1)
                    .tipoDocumento("CC")
                    .build())
            .documento("1010122424")
            .edad(21)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .ciudad("Sincelejo")
                    .build())
            .idImagen("61df70530c3d0d7fbfe0e708")
            .imagen(ImagenDTO.builder()
                    .id("61df70530c3d0d7fbfe0e708")
                    .imagen("Imagen1")
                    .build())
            .build();

    public static final ClienteEntidad CLIENTE_ENTIDAD2 = ClienteEntidad.builder()
            .idClientes(2)
            .nombre("Carolina")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoEntidad.builder()
                    .idTipoDocumento(2)
                    .tipoDocumento("TI")
                    .build())
            .documento("1122334455")
            .edad(13)
            .ciudadNacimiento(CiudadEntidad.builder()
                    .idCiudad(1)
                    .ciudad("Sincelejo")
                    .build())
            .idImagen("61df70530c3d0d7fbfe0e708")
            .build();

    public static final ClienteDTO CLIENTE_DTO2 = ClienteDTO.builder()
            .idClientes(2)
            .nombre("Carolina")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(2)
                    .tipoDocumento("TI")
                    .build())
            .documento("1122334455")
            .edad(13)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .ciudad("Sincelejo")
                    .build())
            .idImagen("61df70530c3d0d7fbfe0e708")
            .build();

    public static final ImagenDTO IMAGEN_INICIAL = ImagenDTO.builder()
            .imagen("Imagen1")
            .build();

    public static final ImagenDTO IMAGEN_DTO = ImagenDTO.builder()
            .id("61df70530c3d0d7fbfe0e708")
            .imagen("Imagen1")
            .build();

    public static final ArrayList<ClienteEntidad> LISTA_CLIENTES_ENTIDAD = new ArrayList<>(
            List.of(CLIENTE_ENTIDAD, CLIENTE_ENTIDAD2));

    public static final ArrayList<ClienteDTO> LISTA_CLIENTES_DTO = new ArrayList<>(
            List.of(CLIENTE_DTO, CLIENTE_DTO2));

    public static final ClienteDTO CLIENTE_IMCOMPATIBILIDAD_EDAD1 = ClienteDTO.builder()
            .nombre("Cristian")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(2)
                    .build())
            .documento("1010122424")
            .edad(21)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen1")
                    .build())
            .build();

    public static final ClienteDTO CLIENTE_IMCOMPATIBILIDAD_EDAD2 = ClienteDTO.builder()
            .nombre("Cristian")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(1)
                    .build())
            .documento("1010122424")
            .edad(13)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen1")
                    .build())
            .build();

    public static final ClienteDTO CLIENTE_IMCOMPATIBILIDAD_DOCUMENTO = ClienteDTO.builder()
            .nombre("Carolina")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(2)
                    .build())
            .documento("1010122424")
            .edad(13)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen1")
                    .build())
            .build();

    public static final ClienteDTO CLIENTE_INICIAL_MODIFICADO = ClienteDTO.builder()
            .idClientes(1)
            .nombre("Angelica")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(2)
                    .build())
            .documento("1010122424")
            .edad(15)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen2")
                    .build())
            .build();

    public static final ClienteEntidad CLIENTE_ENTIDAD_INICIAL_MODIFICADO = ClienteEntidad.builder()
            .idClientes(1)
            .nombre("Angelica")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoEntidad.builder()
                    .idTipoDocumento(2)
                    .build())
            .documento("1010122424")
            .edad(15)
            .ciudadNacimiento(CiudadEntidad.builder()
                    .idCiudad(1)
                    .build())
            .imagen(ImagenDTO.builder()
                    .imagen("Imagen2")
                    .build())
            .build();

    public static final ClienteEntidad CLIENTE_ENTIDAD_MODIFICADO = ClienteEntidad.builder()
            .idClientes(1)
            .nombre("Angelica")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoEntidad.builder()
                    .idTipoDocumento(2)
                    .tipoDocumento("TI")
                    .build())
            .documento("1010122424")
            .edad(15)
            .ciudadNacimiento(CiudadEntidad.builder()
                    .idCiudad(1)
                    .ciudad("Sincelejo")
                    .build())
            .idImagen("61df70530c3d0d7fbfe0e708")
            .imagen(ImagenDTO.builder()
                    .id("61df70530c3d0d7fbfe0e708")
                    .imagen("Imagen2")
                    .build())
            .build();

    public static final ClienteDTO CLIENTE_DTO_MODIFICADO = ClienteDTO.builder()
            .idClientes(1)
            .nombre("Angelica")
            .apellido("Turizo")
            .tipoDocumento(TipoDocumentoDTO.builder()
                    .idTipoDocumento(2)
                    .tipoDocumento("TI")
                    .build())
            .documento("1010122424")
            .edad(15)
            .ciudadNacimiento(CiudadDTO.builder()
                    .idCiudad(1)
                    .ciudad("Sincelejo")
                    .build())
            .idImagen("61df70530c3d0d7fbfe0e708")
            .imagen(ImagenDTO.builder()
                    .id("61df70530c3d0d7fbfe0e708")
                    .imagen("Imagen2")
                    .build())
            .build();

    public static final ImagenDTO IMAGEN_INICIAL_MODIFICADA = ImagenDTO.builder()
            .imagen("Imagen2")
            .build();

    public static final ImagenDTO IMAGEN_DTO_MODIFICADA = ImagenDTO.builder()
            .id("61df70530c3d0d7fbfe0e708")
            .imagen("Imagen2")
            .build();
}
