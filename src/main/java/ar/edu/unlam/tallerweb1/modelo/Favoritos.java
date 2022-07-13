package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

    private boolean Estado;
}
