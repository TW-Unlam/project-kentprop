package ar.edu.unlam.tallerweb1.modelo;

public class Propiedad {

    private Ubicacion ubicacion;
    private Integer id;
    private TipoPropiedad tipoPropiedad;
    private Accion tipoAccion;
    private Estatus estatus;
    private Detalle detalle;

    public Propiedad(Ubicacion ubicacion, Integer id, TipoPropiedad tipoPropiedad, Accion tipoAccion, Estatus estatus, Detalle detalle) {
        this.ubicacion = ubicacion;
        this.id = id;
        this.tipoPropiedad = tipoPropiedad;
        this.tipoAccion = tipoAccion;
        this.estatus = estatus;
        this.detalle = detalle;
    }

    public Propiedad(){

    }

    public Propiedad(Integer id , Detalle detalle){
        this.id = id;
        this.detalle =detalle;

    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Accion getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(Accion tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
}
