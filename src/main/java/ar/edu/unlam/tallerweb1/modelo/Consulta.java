package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pregunta;

    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacionConsultada_id")
    private Publicacion publicacion;

    public Consulta(Integer id, String pregunta, String respuesta, Usuario usuario, Publicacion publicacion) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public Consulta() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
