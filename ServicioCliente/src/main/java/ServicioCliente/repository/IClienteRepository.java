package ServicioCliente.repository;

import ServicioCliente.models.entities.ClienteEntidad;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface IClienteRepository extends CrudRepository<ClienteEntidad, Integer> {
    ArrayList<ClienteEntidad> findByEdadGreaterThanEqual(Integer edad);

    Optional<ClienteEntidad> findByDocumentoAndTipoDocumento_idTipoDocumento(String documento, Integer tipoDocumento);
}

