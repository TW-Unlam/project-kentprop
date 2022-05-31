package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "TABLA_DETALLE_PROPIEDAD")
public class Detalle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;






    public Detalle(Integer id, Integer cantidadAmbientes) {
        this.id = id;

    }

    public Detalle() {

    }


    public Detalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
