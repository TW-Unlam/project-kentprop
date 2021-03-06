package ar.edu.unlam.tallerweb1.controladores;

public class DatosConsulta {
    private String email;
    private String nombre;
    private Integer telefono;
    private String mensaje;
    private Integer propiedadId;

    public DatosConsulta(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) {
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mensaje = mensaje;
        this.propiedadId = propiedadId;
    }

    public Integer getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(Integer propiedadId) {
        this.propiedadId = propiedadId;
    }

    public DatosConsulta() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
