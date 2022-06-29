package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;

import java.util.List;


public interface ServicioPregunta {
     List<Pregunta> buscarConsultasDePublicacion(Integer publicacionId);

     void hacerPregunta(Pregunta pregunta);

     Publicacion buscarPublicacionPorId(Integer publicacionId);
}
