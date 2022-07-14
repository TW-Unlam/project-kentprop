package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;

import java.util.List;


public interface ServicioPregunta {
     List<Pregunta> buscarConsultasDePublicacion(Integer publicacionId);

     void hacerPregunta(String pregunta, Integer publicacionId, Integer usuarioId);

     Publicacion buscarPublicacionPorId(Integer publicacionId);

     Integer responderPregunta(Integer preguntaId, String descripcion);

     Pregunta buscarLaPregunta(Integer id);

     List<Pregunta> buscarPreguntasPorIdDeUsuario(Integer usuarioId);
}
