package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class ServicioPreguntaDefault implements ServicioPregunta {
    private final RepositorioPregunta repositorioPregunta;
    private ServicioLogin servicioUsuario;

    @Autowired
    public ServicioPreguntaDefault(RepositorioPregunta repositorioPregunta, ServicioLogin servicioUsuario) {
        this.repositorioPregunta = repositorioPregunta;
        this.servicioUsuario = servicioUsuario;
    }

    @Override
    public List<Pregunta> buscarConsultasDePublicacion(Integer publicacionId) {
        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(publicacionId);
        return preguntas;
    }

    @Override

    public void hacerPregunta(String pregunta, Integer publicacionId, Integer usuarioId) {
    Publicacion publicacion = buscarPublicacionPorId(publicacionId);
    Usuario usuario = servicioUsuario.obterneUsuario((Integer) usuarioId);

    repositorioPregunta.guardarConsulta(new Pregunta(pregunta, publicacion, usuario));
    }

    @Override
    public Publicacion buscarPublicacionPorId(Integer publicacionId) {
        Publicacion publicacion = repositorioPregunta.buscarPublicacionPorId(publicacionId);
        return publicacion;
    }

    @Override
    public Integer responderPregunta(Integer preguntaId, String descripcion) {
        Pregunta preguntaAresponder=buscarLaPregunta(preguntaId);
        preguntaAresponder.setRespuesta(descripcion);
        repositorioPregunta.guardarRespuesta(preguntaAresponder);
        return preguntaAresponder.getPublicacion().getId();
    }

    @Override
    public Pregunta buscarLaPregunta(Integer id) {
        return repositorioPregunta.ObtenerPregunta(id);
    }
}
