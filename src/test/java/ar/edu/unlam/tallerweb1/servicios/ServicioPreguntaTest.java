package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPregunta;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPreguntaTest {

    private static final Integer PUBLICACION_ID = 1;
    private RepositorioPregunta repositorioPregunta;
    private ServicioPregunta servicioPregunta;

    @Before
    public void init(){
        repositorioPregunta = mock(RepositorioPregunta.class);
        servicioPregunta = new ServicioPreguntaDefault(repositorioPregunta);
    }

    @Test
    public void queCarguenLasConsultasHechasDeUnaPublicacion(){
        dadoQueExistenConsultasEnLaPublicacion(5);

        List<Pregunta> preguntas = entoncesMeTraeLasConsultasDeLaPublicacionConId(PUBLICACION_ID);
        entoncesObtengoLasConsultas(5, preguntas);

    }

    @Test
    public void queAlNoHaberConsultasHechasMeTraigaUnaListaVacia(){
        dadoQueExistenConsultasEnLaPublicacion(0);

        List<Pregunta> preguntas = entoncesMeTraeLasConsultasDeLaPublicacionConId(PUBLICACION_ID);

        entoncesObtengoLasConsultas(0, preguntas);

    }

    private List<Pregunta> entoncesMeTraeLasConsultasDeLaPublicacionConId(Integer publicacionId) {
        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(publicacionId);
        return preguntas;
    }

    private void entoncesObtengoLasConsultas(int cantidadConsultasEsperadas, List<Pregunta> preguntas) {
        assertThat(preguntas).hasSize(cantidadConsultasEsperadas);
    }

    private void dadoQueExistenConsultasEnLaPublicacion(int cantidad) {
        List<Pregunta> lista = givenExistenConsultas(cantidad);
        when(repositorioPregunta.buscarConsultasDePublicacion(PUBLICACION_ID)).thenReturn(lista);
    }

    private List<Pregunta> givenExistenConsultas(int cantidad){
        List<Pregunta> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Pregunta());
        }
        return lista;
    }


}
