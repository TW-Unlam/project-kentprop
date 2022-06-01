package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Accion;
import ar.edu.unlam.tallerweb1.modelo.TipoPropiedad;

public class DatosBusqueda {
  private TipoPropiedad tipoPropiedad;
  private Accion tipoAccion;
  private String descripcion;

  public DatosBusqueda(TipoPropiedad tipoPropiedad, Accion tipoAccion, String descripcion) {
    this.tipoPropiedad = tipoPropiedad;
    this.tipoAccion = tipoAccion;
    this.descripcion = descripcion;
  }

  public DatosBusqueda() {
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

  public String getUbicacion() {
    return descripcion;
  }

  public void setUbicacion(String descripcion) {
    this.descripcion = descripcion;
  }
}
