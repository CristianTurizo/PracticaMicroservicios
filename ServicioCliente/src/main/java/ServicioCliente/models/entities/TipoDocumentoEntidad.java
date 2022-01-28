package ServicioCliente.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tipo_documento")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoEntidad {

    @Id
    @Column(name = "id_tipo_documento")
    private int idTipoDocumento;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

}
