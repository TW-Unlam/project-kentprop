package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConsulta;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioConsultaTest {

    private static final Integer PUBLICACION_ID = 1;
    private RepositorioConsulta repositorioConsulta;
    private ServicioConsulta servicioConsulta;

    @Before
    public void init(){
        repositorioConsulta = mock(RepositorioConsulta.class);
        servicioConsulta = new ServicioConsultaDefault(repositorioConsulta);
    }

    @Test
    public void queCarguenLasConsultasHechasDeUnaPublicacion(){
        dadoQueExistenConsultasEnLaPublicacion(5);

        List<Consulta> consultas = entoncesMeTraeLasConsultasDeLaPublicacionConId(PUBLICACION_ID);
        entoncesObtengoLasConsultas(5, consultas);

    }

    @Test
    public void queAlNoHaberConsultasHechasMeTraigaUnaListaVacia(){
        dadoQueExistenConsultasEnLaPublicacion(0);

        List<Consulta> consultas = entoncesMeTraeLasConsultasDeLaPublicacionConId(PUBLICACION_ID);

        entoncesObtengoLasConsultas(0, consultas);

    }

    private List<Consulta> entoncesMeTraeLasConsultasDeLaPublicacionConId(Integer publicacionId) {
        List<Consulta> consultas = repositorioConsulta.buscarConsultasDePublicacion(publicacionId);
        return consultas;
    }

    private void entoncesObtengoLasConsultas(int cantidadConsultasEsperadas, List<Consulta> consultas) {
        assertThat(consultas).hasSize(cantidadConsultasEsperadas);
    }

    private void dadoQueExistenConsultasEnLaPublicacion(int cantidad) {
        List<Consulta> lista = givenExistenConsultas(cantidad);
        when(repositorioConsulta.buscarConsultasDePublicacion(PUBLICACION_ID)).thenReturn(lista);
    }

    private List<Consulta> givenExistenConsultas(int cantidad){
        List<Consulta> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Consulta());
        }
        return lista;
    }


}
