package ServicioCliente.services;

import ServicioCliente.models.DTO.ClienteDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> obtenerClientes();

    ClienteDTO obtenerPorId(Integer id);

    List<ClienteDTO> obtenerPorEdadMayorA(Integer edad);

    ClienteDTO obtenerPorDocumentoYTipo(String documento, Integer tipo);

    ClienteDTO guardarCliente(ClienteDTO cliente);

    ClienteDTO moficarCliente(ClienteDTO cliente);

    void eliminarCliente(Integer id);

    ClienteDTO validarDocumento(String documento, Integer tipo);

}
