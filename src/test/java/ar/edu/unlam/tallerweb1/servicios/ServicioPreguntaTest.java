package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPregunta;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioPreguntaTest {

    private static final Integer PUBLICACION_ID = 1;
    private RepositorioPregunta repositorioPregunta;
    private RepositorioUsuario repositorioUsuario;
    private ServicioLogin servicioUsuario;
    private ServicioPregunta servicioPregunta;

    @Before
    public void init(){
        repositorioPregunta = mock(RepositorioPregunta.class);
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioUsuario = new ServicioLoginDefault(repositorioUsuario);
        servicioPregunta = new ServicioPreguntaDefault(repositorioPregunta, servicioUsuario);
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
    @Test
    public void queAlResponderLaPreguntaTraigaLosDatosParaPoderActualizarla(){
        dadoQueExisteLaConsulteEnLaPublicacion();
        Pregunta preguntas = entoncesMeTraeLasConsultaConId(1);
        entoncesObtengoLaConsulta( preguntas);
    }
    @Test
    public void alActualizarLaPregunta(){
        dadoQueExisteLaConsulteEnLaPublicacion();
        Pregunta preguntas = entoncesMeTraeLasConsultaConId(1);
        int iddeLaPublicacion=cuandoGuardaLaPregunta(preguntas.getId(),"3 meses");
        entoncesSeACtualizaLasDatos(preguntas);

    }

    private Integer cuandoGuardaLaPregunta(Integer id, String descripcion) {
        return servicioPregunta.responderPregunta(id,descripcion);
    }

    private void dadoQueExisteLaConsulteEnLaPublicacion() {
        Pregunta pregunta = new Pregunta();
        Publicacion publicacion= new Publicacion();
        publicacion.setId(1);
        pregunta.setId(1);
        pregunta.setPublicacion(publicacion);
        when(repositorioPregunta.ObtenerPregunta(1)).thenReturn(pregunta);

    }

    /*private void cuandoGuardaLaPregunta(Pregunta preguntas) {
        servicioPregunta.responderPregunta(preguntas);
    }*/

    private void entoncesSeACtualizaLasDatos(Pregunta preguntas) {
        verify(repositorioPregunta,times(1)).guardarRespuesta(preguntas);
    }

    private void entoncesObtengoLaConsulta( Pregunta preguntas) {
        assertThat(preguntas).isNotNull();
    }

    private Pregunta entoncesMeTraeLasConsultaConId(Integer IdPregunta) {
        return servicioPregunta.buscarLaPregunta(1);
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
