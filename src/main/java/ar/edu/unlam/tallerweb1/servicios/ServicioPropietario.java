package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ServicioPropietario {
     List<Publicacion> obtenePublicacionesDelPropietario(Integer PropietarioId);

}
