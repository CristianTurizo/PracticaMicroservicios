package ServicioCliente.services;

import ServicioCliente.exceptionsHandling.exceptions.AgeIncompatibilityExeption;
import ServicioCliente.exceptionsHandling.exceptions.DocumentAlreadyExistException;
import ServicioCliente.exceptionsHandling.exceptions.RegisterNotFoundException;
import ServicioCliente.feingclient.IImagenFeingClient;
import ServicioCliente.mappers.IMapper;
import ServicioCliente.models.DTO.ClienteDTO;
import ServicioCliente.models.entities.ClienteEntidad;
import ServicioCliente.repository.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final IMapper mapper;
    private final IClienteRepository clienteRepository;
    private final IImagenFeingClient imagenFeingClient;

    @Override
    public List<ClienteDTO> obtenerClientes() {
        var clienteDtos = this.mapper.toClientesDto(
                (ArrayList<ClienteEntidad>) this.clienteRepository.findAll());

        if (clienteDtos.isEmpty()) {
            throw new RegisterNotFoundException();
        } else {
            clienteDtos.forEach(cliente ->
                    cliente.setImagen(
                            this.imagenFeingClient.obtenerPorId(cliente.getIdImagen())));
            return clienteDtos;
        }
    }

    @Override
    public ClienteDTO obtenerPorId(Integer id) {
        var cliente = this.mapper.toClienteDto(
                this.clienteRepository.findById(id).orElseThrow(
                        RegisterNotFoundException::new));

        cliente.setImagen(this.imagenFeingClient.obtenerPorId(cliente.getIdImagen()));
        return cliente;
    }

    @Override
    public List<ClienteDTO> obtenerPorEdadMayorA(Integer edad) {
        var clientes = this.mapper.toClientesDto(
                this.clienteRepository.findByEdadGreaterThanEqual(edad));

        if (clientes.isEmpty()) {
            throw new RegisterNotFoundException();
        } else {
            clientes.forEach(cliente ->
                    cliente.setImagen(
                            this.imagenFeingClient.obtenerPorId(cliente.getIdImagen())));

            return clientes;
        }
    }

    @Override
    public ClienteDTO obtenerPorDocumentoYTipo(String documento, Integer tipo) {
        var cliente = this.mapper.toClienteDto(
                this.clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(documento, tipo)
                        .orElseThrow(RegisterNotFoundException::new));

        cliente.setImagen(this.imagenFeingClient.obtenerPorId(cliente.getIdImagen()));
        return cliente;
    }

    @Override
    public ClienteDTO guardarCliente(ClienteDTO cliente) {
        // Validaciones
        if (((cliente.getEdad() >= 18) && (cliente.getTipoDocumento().getIdTipoDocumento() == 2))
                || ((cliente.getEdad() < 18) && (cliente.getTipoDocumento().getIdTipoDocumento() == 1))) {
            throw new AgeIncompatibilityExeption();
        }
        ClienteDTO clienteConIgualDocumento = this.validarDocumento(
                cliente.getDocumento(), cliente.getTipoDocumento().getIdTipoDocumento());

        if ((clienteConIgualDocumento != null)) {
            if ((clienteConIgualDocumento.getIdClientes() != cliente.getIdClientes())) {
                throw new DocumentAlreadyExistException();
            }
        }

        // Guardar Imagen
        var imagen = this.imagenFeingClient.guardarImagen(cliente.getImagen());
        cliente.setIdImagen(imagen.getId());
        cliente.setImagen(imagen);

        //Guardar Cliente
        return this.mapper.toClienteDto(
                this.clienteRepository.save(
                        this.mapper.toClienteEntidad(cliente)));
    }

    @Override
    public ClienteDTO moficarCliente(ClienteDTO cliente) {
        // Validaciones
        if (((cliente.getEdad() >= 18) && (cliente.getTipoDocumento().getIdTipoDocumento() == 2))
                || ((cliente.getEdad() < 18) && (cliente.getTipoDocumento().getIdTipoDocumento() == 1))) {
            throw new AgeIncompatibilityExeption();
        }
        ClienteDTO clienteConIgualDocumento = this.validarDocumento(
                cliente.getDocumento(), cliente.getTipoDocumento().getIdTipoDocumento());

        if ((clienteConIgualDocumento != null)) {
            if ((clienteConIgualDocumento.getIdClientes() != cliente.getIdClientes())) {
                throw new DocumentAlreadyExistException();
            }
        }

        // Modificar Imagen Correcta
        var imagenAntigua = this.obtenerPorId(
                cliente.getIdClientes()).getImagen();
        cliente.setIdImagen(imagenAntigua.getId());
        cliente.getImagen().setId(imagenAntigua.getId());

        //Modificar objetos
        var imagenModificada = this.imagenFeingClient.modificarImagen(cliente.getImagen());
        cliente.setImagen(imagenModificada);

        return this.mapper.toClienteDto(
                this.clienteRepository.save(
                        this.mapper.toClienteEntidad(cliente)));

    }

    @Override
    public void eliminarCliente(Integer id) {
        var cliente = this.obtenerPorId(id);
        this.imagenFeingClient.eliminarImagen(cliente.getIdImagen());
        this.clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO validarDocumento(String documento, Integer tipo) {
        return this.mapper.toClienteDto(
                this.clienteRepository.findByDocumentoAndTipoDocumento_idTipoDocumento(documento, tipo)
                        .orElse(null));
    }

}
