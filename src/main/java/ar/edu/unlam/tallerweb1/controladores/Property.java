package ar.edu.unlam.tallerweb1.controladores;

public class Property {
  private final Integer id;
  private final String ubicacion;

  public Property(Integer id, String ubicacion) {
    this.id = id;
    this.ubicacion = ubicacion;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public Integer getId() {
    return id;
  }

}
