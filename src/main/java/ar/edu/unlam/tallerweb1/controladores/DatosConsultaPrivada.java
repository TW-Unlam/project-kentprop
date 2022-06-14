package ar.edu.unlam.tallerweb1.controladores;

public class DatosConsultaPrivada {
    private String email;
    private String nombre;
    private Integer Telefono;
    private String mensaje;

    public DatosConsultaPrivada(String email, String nombre, Integer telefono, String mensaje) {
        this.email = email;
        this.nombre = nombre;
        this.Telefono = telefono;
        this.mensaje = mensaje;
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
        return Telefono;
    }

    public void setTelefono(Integer telefono) {
        Telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
