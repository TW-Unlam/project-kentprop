package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DatosReserva {
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaInicioReserva;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaFinReserva;
    private Integer idPublicacion;

    public DatosReserva(Date fechaInicioReserva, Date fechaFinReserva, Integer idPublicacion) {
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
        this.idPublicacion = idPublicacion;
    }

    public DatosReserva(){

    }

    public Date getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(Date fechaInicioReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public Date getFechaFinReserva() {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(Date fechaFinReserva) {
        this.fechaFinReserva = fechaFinReserva;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}
