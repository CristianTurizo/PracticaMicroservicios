package ServicioCliente.models.entities;

import ServicioCliente.models.DTO.ImagenDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Entity
@Table(name = "clientes1")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clientes")
    private int idClientes;

    @Column(name = "nombres")
    private String nombre;

    @Column(name = "apellidos")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "tipo_documento", referencedColumnName = "id_tipo_documento")
    private TipoDocumentoEntidad tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @Column(name = "edad")
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "ciudad_nacimiento", referencedColumnName = "id_ciudad")
    private CiudadEntidad ciudadNacimiento;

    @Column(name = "imagen_cliente")
    private String idImagen;

    @Transient
    private ImagenDTO imagen;

}
