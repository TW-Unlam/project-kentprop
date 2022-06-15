package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class ServicioPreguntaDefault implements ServicioPregunta {
    private final RepositorioPregunta repositorioPregunta;

    @Autowired
    public ServicioPreguntaDefault(RepositorioPregunta repositorioPregunta) {
        this.repositorioPregunta = repositorioPregunta;
    }

    @Override
    public List<Pregunta> buscarConsultasDePublicacion(Integer publicacionId) {
        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(publicacionId);
        return preguntas;
    }

    @Override
    public Boolean hacerPregunta(Pregunta pregunta) {
        repositorioPregunta.guardarConsulta(pregunta);
        return true;
    }

    @Override
    public Publicacion buscarPublicacionPorId(Integer publicacionId) {
        Publicacion publicacion = repositorioPregunta.buscarPublicacionPorId(publicacionId);
        return publicacion;
    }
}
