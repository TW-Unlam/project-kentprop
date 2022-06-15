package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;

import java.util.List;

public interface RepositorioPregunta {

    List<Pregunta> buscarConsultasDePublicacion(Integer idPublicacion);

    List<Pregunta> buscarConsultasDeUsuario(long idUsuario);

    void guardarConsulta(Pregunta pregunta);

    Publicacion buscarPublicacionPorId(Integer publicacionId);
}
