package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioPublicacionTest {

    private RepositorioPublicaciones repositorio;
    private RepositorioUsuario repositorioUsuario;
    private ServicioPublicaciones servicioPublicaciones;
    private static final Integer ID_PUBLICACION = 1;
    private static final Integer ID_USUARIO = 2;
    private final Accion ACCION = Accion.COMPRAR;
    private final TipoPropiedad TIPO = TipoPropiedad.CASA;
    private final String descripcion = "";

    @Before
    public void init(){
        repositorio = mock(RepositorioPublicaciones.class);
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioPublicaciones = new ServicioPublicacionesDefault(repositorio, repositorioUsuario);
    }

    @Test
    public void queAlBuscarUnaPublicacionDeberaTraermeLaListaDePropiedades(){
        dadoQueExisteUnaListaDePublicaciones(3);

        List<Publicacion> busqueda = cuandoBuscoUnaPublicacion();

        entoncesSeObtieneLasPublicaciones(busqueda,3);

    }
    @Test
    public void queAlBuscarUnaPublicacionDeberaTraermeUnaListaVacia() {
        dadoQueNoExisteUnaListaDePublicaciones();

        List<Publicacion> busqueda = cuandoBuscoUnaPublicacion();

        entoncesSeObtieneLasPublicaciones(busqueda,0);

    }
    @Test
    public void solicitarDetalleDePropiedadMedianteNumeroId(){
        
        dadoQueExisteUnaPublicacion();
        
        Publicacion resultado = cuandoSeleccionoVerDetalle();

        entoncesSeObtieneElDetalleDeLaPublicacion(resultado);
        
    }

    @Test
    public void alIndicarQueDejeDeSerPublicacionFavoritaDelUsuarioSeElimine() {
        dadoqueLaPublicacionYaEsFavorito();
        cuandoSelecionaFavorito();
        entocesLaPublicacionDejaDeSerFavorita();
    }

    @Test
    public void alindicarComoPublicacionFavoritoQuelocree() {
        dadoQueExisteUnaPublicacionYUsuario();
        cuandoQuieroIndicarComoFavorito();
        entoncesDeberiaCrearlaRelacion();
    }

    @Test
    public void alIngresarComoUsuarioDeberiaTraerMiFavoritoExistente() {
        dadoQueTengoUnaPublicacionFavorita(ID_PUBLICACION, ID_USUARIO);
        boolean estadoFavorito = cuandoQuieroVerificarSiLaPublicacionEsFavorita(ID_PUBLICACION, ID_USUARIO);
        entoncesDeberiaDevolvermeTrue(estadoFavorito);
    }

    private void entoncesDeberiaDevolvermeTrue(boolean estadoFavorito) {
        assertThat(estadoFavorito).isTrue();
    }

    private boolean cuandoQuieroVerificarSiLaPublicacionEsFavorita(Integer idPublicacion, Integer idUsuario) {
        return servicioPublicaciones.obtenerEstadoFavorito(idPublicacion, idUsuario);
    }

    @Test
    public void QueAlbuscarFavoritosMeTraigaUnaLista() {
        dadoqueLaPublicacionYaEsFavorito();

        List<Publicacion> busqueda = cuandoBuscoUnaPublicacionFavorita();

        entoncesSeObtieneLasPublicaciones(busqueda,1);
    }

    private List<Publicacion> cuandoBuscoUnaPublicacionFavorita() {
        return servicioPublicaciones.buscarPublicacionFavoritas(1);
    }

    private void entoncesDeberiaCrearlaRelacion() {
        verify(repositorio,times(1)).indicarFavorito(anyObject());
    }

    private void cuandoQuieroIndicarComoFavorito() {
        servicioPublicaciones.indicarPublicacionFavorita(ID_PUBLICACION,1);
    }

    private void dadoQueExisteUnaPublicacionYUsuario() {
       Publicacion publicacion=new Publicacion();
        when(repositorio.buscarPublicacionId(1)).thenReturn(publicacion);
       Usuario usuario=new Usuario();
        when(repositorioUsuario.obterneUsuario(1)).thenReturn( usuario);
    }

    private void entocesLaPublicacionDejaDeSerFavorita() {
        verify(repositorio,times(1)).eliminarfavorito(anyObject());
    }

    private void cuandoSelecionaFavorito() {
        servicioPublicaciones.indicarPublicacionFavorita(1,1);
    }

    private void dadoqueLaPublicacionYaEsFavorito() {
    Publicacion publicacionFav=new Publicacion();
    publicacionFav.setId(1);
    Usuario usuario= new Usuario();
    usuario.setId(1);
     List<Favoritos> lisfav= new LinkedList<>();
            Publicacion tmpP=new Publicacion();
            Favoritos tmpF=new Favoritos();
            tmpF.setPublicacion(tmpP);
            tmpF.setUsuario(usuario);
            lisfav.add(tmpF);

    when(repositorio.BuscarFavoritosDelUsuario(1)).thenReturn(lisfav);
    Favoritos fav=new Favoritos();
    fav.setPublicacion(publicacionFav);
    fav.setUsuario(usuario);
    fav.setEstado(true);
    when(repositorio.buscarFavoritoExistente(publicacionFav.getId(), usuario.getId())).thenReturn(fav);
    }

    @Test
    public void obtenerPublicacionesDestacadas(){
        dadoQueExisteUnaListaDePublicacionesDestacadas(3);

        List<Publicacion> busqueda = cargoLasPublicacionesDestacadas();
        
        entoncesObtengoLaCantidadDePublicacionesDeseadas(busqueda, 3);
    }

    @Test
    public void obtenerImagenesDePublicaciones(){
        Publicacion publicacion = dadoQueExisteUnaListaDeImagenesEnUnaPublicacion(3);

        List<Imagen> busqueda = cuandoBuscoUnaImagen(publicacion);

        entoncesObtengoLaCantidadDeImagenesDeseadas(busqueda,3);
    }

    private void entoncesObtengoLaCantidadDeImagenesDeseadas(List<Imagen> busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);
    }

    private List<Imagen> cuandoBuscoUnaImagen(Publicacion publicacion) {
        return repositorio.buscarImagenesDeLaPublicacion(publicacion.getId());
    }

    private Publicacion dadoQueExisteUnaListaDeImagenesEnUnaPublicacion(int cantidad) {
        List<Imagen> imagenes = givenExistenImagenes(cantidad);
        Publicacion publicacion = new Publicacion(1);

        when(repositorio.buscarImagenesDeLaPublicacion(publicacion.getId())).thenReturn(imagenes);

        return publicacion;
    }

    private List<Imagen> givenExistenImagenes(int cantidad) {
        List<Imagen> imagenes = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            imagenes.add(new Imagen());
        }
        return imagenes;
    }

    private void entoncesObtengoLaCantidadDePublicacionesDeseadas(List<Publicacion>busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);
    }

    private List<Publicacion> cargoLasPublicacionesDestacadas() {
        return servicioPublicaciones.obtenerPublicacionesDestacadas();
    }

    private void dadoQueExisteUnaListaDePublicacionesDestacadas(int cantidad) {
        List<Publicacion> destacadas = givenExistenPropiedades(cantidad);
        when(repositorio.buscarPublicacionesDestacadas()).thenReturn(destacadas);
    }

    private void dadoQueNoExisteUnaListaDePublicaciones() {
        List<Publicacion> lista = new LinkedList<>();
        when(repositorio.buscarPublicaciones(ACCION, TIPO, descripcion)).thenReturn(lista);
    }

    private List<Publicacion> givenExistenPropiedades(int cantidad){
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Publicacion());
        }
        return lista;
    }

    private void entoncesSeObtieneElDetalleDeLaPublicacion(Publicacion resultado) {
        assertThat(resultado.getId()).isEqualTo(ID_PUBLICACION);
    }

    private Publicacion cuandoSeleccionoVerDetalle() {
        return servicioPublicaciones.verDetallePublicacion(ID_PUBLICACION);

    }

    private void dadoQueExisteUnaPublicacion() {
        Publicacion detalle = new Publicacion();
        detalle.setId(1);
        when(repositorio.buscarDetallePublicacion(ID_PUBLICACION)).thenReturn(detalle);
    }

    private void entoncesSeObtieneLasPublicaciones(List<Publicacion> busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);

    }

    private List<Publicacion> cuandoBuscoUnaPublicacion() {
        return servicioPublicaciones.buscarPublicacion(ACCION, TIPO, descripcion);
    }

    private void dadoQueExisteUnaListaDePublicaciones(int cantidad) {
        List<Publicacion> lista = givenExistenPropiedades(cantidad);

        when(repositorio.buscarPublicaciones(ACCION, TIPO, descripcion)).thenReturn(lista);

    }

    private void dadoQueTengoUnaPublicacionFavorita(Integer publicacionId, Integer usuarioId) {
        Favoritos favorito = new Favoritos();

        favorito.setEstado(true);
        when(repositorio.buscarFavoritoExistente(publicacionId, usuarioId)).thenReturn(favorito);
    }
}
