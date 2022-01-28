package ServicioCliente.mappers;

import ServicioCliente.models.DTO.CiudadDTO;
import ServicioCliente.models.DTO.ClienteDTO;
import ServicioCliente.models.DTO.TipoDocumentoDTO;
import ServicioCliente.models.entities.CiudadEntidad;
import ServicioCliente.models.entities.ClienteEntidad;
import ServicioCliente.models.entities.TipoDocumentoEntidad;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);

    ArrayList<ClienteDTO> toClientesDto(ArrayList<ClienteEntidad> clientesEntidad);

    ClienteDTO toClienteDto(ClienteEntidad clienteEntidad);

    ClienteEntidad toClienteEntidad(ClienteDTO clienteDTO);

    CiudadDTO toCiudadDto(CiudadEntidad ciudadEntidad);

    CiudadEntidad toCiudadEntidad(CiudadDTO ciudadDTO);

    TipoDocumentoDTO toTipoDocumentoDto(TipoDocumentoEntidad tipoDocumentoEntidad);

    TipoDocumentoEntidad toTipoDocumentoEntidad(TipoDocumentoDTO tipoDocumentoDTO);
}
