package ServicioCliente.controller;

import ServicioCliente.models.DTO.ClienteDTO;
import ServicioCliente.services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping()
    public List<ClienteDTO> obtenerClientes() {
        return this.clienteService.obtenerClientes();
    }

    @GetMapping(path = "/{id}")
    public ClienteDTO obtenerPorId(@PathVariable int id) {
        return this.clienteService.obtenerPorId(id);
    }

    @GetMapping(path = "/findByEdadMayorA")
    public List<ClienteDTO> obtenerPorEdadMayorA(@RequestParam("edad") Integer edad) {
        return this.clienteService.obtenerPorEdadMayorA(edad);
    }

    @GetMapping(path = "/findByDocumentoYTipo")
    public ClienteDTO obtenerPorDocumentoYTipo(@RequestParam("documento") String documento, @RequestParam("tipo") Integer tipo) {
        return this.clienteService.obtenerPorDocumentoYTipo(documento, tipo);
    }

    @PostMapping()
    public ResponseEntity<ClienteDTO> guardar(@Valid @RequestBody ClienteDTO cliente) {
        var clienteNuevo = this.clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ClienteDTO> modificar(@Valid @RequestBody ClienteDTO cliente) {
        var clienteModificado = this.clienteService.moficarCliente(cliente);
        return new ResponseEntity<>(clienteModificado, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity eliminarCliente(@PathVariable("id") int id) {
        this.clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
