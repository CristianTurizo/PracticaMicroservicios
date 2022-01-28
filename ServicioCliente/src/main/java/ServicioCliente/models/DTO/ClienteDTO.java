package ServicioCliente.models.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
public class ClienteDTO {

    private int idClientes;

    @NotBlank(message = "Campo Vacio")
    private String nombre;

    @NotBlank(message = "Campo Vacio")
    private String apellido;

    @Valid
    @NotNull
    private TipoDocumentoDTO tipoDocumento;

    @NotBlank(message = "Campo Vacio")
    @Size(min = 8, max = 10, message = "El documento debe tener entre 8 y 10 digitos")
    private String documento;

    @NotNull
    @Positive(message = "La edad debe ser mayor que 0")
    private Integer edad;

    @NotNull
    private CiudadDTO ciudadNacimiento;

    private String idImagen;

    @Valid
    @NotNull
    private ImagenDTO imagen;

}
