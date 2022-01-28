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
@Table(name = "ciudades")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiudadEntidad {

    @Id
    @Column(name = "id_ciudad")
    private int idCiudad;

    @Column(name = "ciudad")
    private String ciudad;

}
