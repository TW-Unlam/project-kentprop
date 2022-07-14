package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class RepositorioPublicacionesTest extends SpringTest {

    private final TipoPropiedad TIPO_EXISTENTE = TipoPropiedad.DEPARTAMENTO;
    private final Accion ACCION_EXISTENTE = Accion.ALQUILAR;
    private final String DESCRIPCION_EXISTENTE = "Ramos Mejia";
    private final TipoPropiedad TIPO_INEXISTENTE = TipoPropiedad.OFICINA;
    private final Accion ACCION_INEXISTENTE = Accion.EMPRENDER;
    private final String DESCRIPCION_INEXISTENTE = "Villa Luzuriaga";

    @Autowired
    private RepositorioPublicaciones repositorioPublicaciones;


    @Test @Transactional @Rollback
    public void busquedaPublicacionesConDevolucion(){

        dadoQueExisteUnaListaDePublicaciones();

        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE,
                TIPO_EXISTENTE,
                DESCRIPCION_EXISTENTE);

        entoncesMeDevuelveUnaListaDePublicacionesQueCoinciden(propiedades);
    }

    @Test @Transactional @Rollback
    public void busquedaPublicacionesSinDevolucion(){

        dadoQueNoExisteUnaListaDePublicaciones();
        
        List<Publicacion> publicaciones = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE,
                TIPO_EXISTENTE,
                DESCRIPCION_EXISTENTE);

        entoncesNoMeDevuelveNingunaPublicacion(publicaciones);

    }

    @Test @Transactional @Rollback
    public void realizarUnaBusquedaConDatosDeBusquedaInexistentes(){

        dadoQueExisteUnaListaDePublicaciones();

        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_INEXISTENTE,
                TIPO_INEXISTENTE,
                DESCRIPCION_INEXISTENTE);

        entoncesNoMeDevuelveNingunaPublicacion(propiedades);

    }

    @Test @Transactional @Rollback
    public void obtenerElObjetoPropiedadAlVerDetalleDeLaPublicacion(){
        dadoQueExisteUnaListaDePublicaciones();
        
        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE, TIPO_EXISTENTE, DESCRIPCION_EXISTENTE);
        Publicacion resultado = repositorioPublicaciones.buscarDetallePublicacion(propiedades.get(0).getId());

        entoncesMeDevuelveElDetalleDeLaPropiedadConId(propiedades.get(0).getId(), resultado.getId());
    }

    @Test @Transactional @Rollback
    public void obtenerElPropietarioDEUnaPropiedadEnDetalles(){
        dadoQueExisteUnaListaDePropiedadesConPropietarios();

        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE, TIPO_EXISTENTE, DESCRIPCION_EXISTENTE);
        Publicacion resultado = repositorioPublicaciones.buscarDetallePublicacion(propiedades.get(0).getId());
        Propiedad propietario = (Propiedad) repositorioPublicaciones.buscarPropiedad(resultado.getPropiedad().getId());

        entoncesMeDevuelveLosDatosDelUsuarioPropietarioDELaPropiedad( propietario.getPropietario());
        entoncesMeDevuelveLosDatosDelUsuarioAEnviar( propietario.getPropietario().getEmail(),"sullca@gmail");
    }

    @Test @Transactional @Rollback
    public void ObtenerLaPublicacionBuscadoPorElID() {
       Publicacion publicacionConImagenes=dadoQueExisteUnaListaDePublicacionesConImagenes();
       Publicacion resultado=repositorioPublicaciones.buscarPublicacionId(publicacionConImagenes.getId());
        entoncesMeDevuelveALPublicacion(resultado);
    }

    @Test @Transactional @Rollback
    public void alBuscarSiLAPublicacionYaEsFavoritoDevuelveNull() {
        Publicacion publicacionConImagenes=dadoQueExisteUnaListaDePublicacionesConImagenes();
        Favoritos resultado=repositorioPublicaciones.BuscarFavoritoExistente(publicacionConImagenes.getId(),1);
        entoncesMeDevuelvenFavoritoNulo(resultado);
    }

    @Test @Transactional @Rollback
    public void alBuscarSiLAPublicacionYaEsFavoritoDevuelveLARelacion() {
        Publicacion publicacionConImagenes=dadoQueExisteUnaListaDePublicacionesConImagenes();
       Usuario usuario= dadoqueExisteUnaPublicacionFavorita( publicacionConImagenes);
        Favoritos resultado=repositorioPublicaciones.BuscarFavoritoExistente(publicacionConImagenes.getId(),usuario.getId());
        entoncesMeDevuelvenFavorito(resultado,publicacionConImagenes.getId(),usuario.getId());
    }

    @Test @Transactional @Rollback
    public void alMarcarLaPublicacionComoFavoritoSeDebeGuardar() {
        Publicacion publicacionConImagenes=dadoQueExisteUnaListaDePublicacionesConImagenes();
        Usuario usuario= dadoqueExisteUnaPublicacionFavorita( new Publicacion());

        Favoritos resultado=repositorioPublicaciones.BuscarFavoritoExistente(publicacionConImagenes.getId(),usuario.getId());
        entoncesMeDevuelvenFavoritoNulo(resultado);
        Favoritos guardado=AlGuardarComoFavorioto(publicacionConImagenes,usuario);

        assertThat(guardado).isNotNull();
        entoncesexisteCorrectamente(publicacionConImagenes,usuario);
    }

    @Test @Transactional @Rollback
    public void alMarcarLaPublicacionComoFavoritoNuevamenteSeDebeEliminar() {
        Publicacion publicacionConImagenes=dadoQueExisteUnaListaDePublicacionesConImagenes();
        Usuario usuario= dadoqueExisteUnaPublicacionFavorita(  publicacionConImagenes);

        Favoritos resultado=repositorioPublicaciones.BuscarFavoritoExistente(publicacionConImagenes.getId(),usuario.getId());

        Actualizar_QuitarComoFavorito(resultado);

        entoncesexDejaDeExistir(publicacionConImagenes,usuario);
    }

    private void entoncesexDejaDeExistir(Publicacion publicacionConImagenes, Usuario usuario) {
        assertThat(repositorioPublicaciones.BuscarFavoritoExistente(publicacionConImagenes.getId(),usuario.getId())).isNull();

    }

    private void Actualizar_QuitarComoFavorito(Favoritos resultado) {

        repositorioPublicaciones.eliminarfavorito(resultado);

    }

    private void entoncesexisteCorrectamente(Publicacion publicacionConImagenes, Usuario usuario) {
        assertThat(repositorioPublicaciones.BuscarFavoritoExistente(publicacionConImagenes.getId(),usuario.getId())).isNotNull();
    }

    private Favoritos AlGuardarComoFavorioto(Publicacion publicacionConImagenes, Usuario usuario) {
        Favoritos fav =new Favoritos();
        fav.setPublicacion(publicacionConImagenes);
        fav.setUsuario(usuario);
        repositorioPublicaciones.indicarFavorito(fav);
        return fav;
    }

    private void entoncesMeDevuelvenFavorito(Favoritos resultado, Integer id, Integer usuarioId) {
    assertThat(resultado).isNotNull();
    assertThat(resultado.getPublicacion().getId()).isEqualTo(id);
    assertThat(resultado.getUsuario().getId()).isEqualTo(usuarioId);
    }

    private Usuario dadoqueExisteUnaPublicacionFavorita(Publicacion publicacion) {

        Usuario usuario=new Usuario();

        Favoritos fav=new Favoritos();

        fav.setUsuario(usuario);
        fav.setPublicacion(publicacion);

        session().save(publicacion);
        session().save(usuario);
        session().save(fav);

        return usuario;
    }


    private void entoncesMeDevuelvenFavoritoNulo(Favoritos resultado) {
        assertThat(resultado).isNull();
    }

    private void entoncesMeDevuelveALPublicacion(Publicacion resultado) {
        assertThat(resultado).isNotNull();
    }

    @Test @Transactional @Rollback
    public void obtenerPublicacionesDestacadas(){
        dadoQueExisteUnaListaDePublicaciones();
        
        List<Publicacion> destacadas = repositorioPublicaciones.buscarPublicacionesDestacadas();
        
        entoncesMeDevuelveLasPublicacionesDestacadas(destacadas, 2);
    }

    //--------------------------------
    @Test @Transactional @Rollback
    public void obtenerLasCoordenasDeLaPropiedad() {
        dadoQueExisteUnaListaDePublicaciones();

        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE, TIPO_EXISTENTE, DESCRIPCION_EXISTENTE);
        Publicacion resultado = repositorioPublicaciones.buscarDetallePublicacion(propiedades.get(0).getId());

        entoncesMeDevuelveLaLatitudDelAPropiedad((Double)resultado.getPropiedad().getUbicacion().getLatitud());
        entoncesMeDevuelveLaLongitudDelAPropiedad((Double)resultado.getPropiedad().getUbicacion().getLongitud());
    }

    @Test @Transactional @Rollback
    public void obtenerImagenesDeUnaPublicacion(){
        Publicacion publicacion = dadoQueExisteUnaListaDePublicacionesConImagenes();

        List<Imagen>imagenes = repositorioPublicaciones.buscarImagenesDeLaPublicacion(publicacion.getId());

        entoncesMeDevuelveLaListaDeImagenesCorrespondienteAlaPublicacion(imagenes, 4);
    }

    private void entoncesMeDevuelveLaListaDeImagenesCorrespondienteAlaPublicacion(List<Imagen> imagenes, int cantidadImagenes) {
        assertThat(imagenes).hasSize(cantidadImagenes);
    }


    private Publicacion dadoQueExisteUnaListaDePublicacionesConImagenes() {
        Publicacion publicacion = new Publicacion();
        Imagen imagen = new Imagen();
        Imagen imagen2 = new Imagen();
        Imagen imagen3 = new Imagen();
        Imagen imagen4 = new Imagen();

        imagen.setPublicacion(publicacion);
        imagen2.setPublicacion(publicacion);
        imagen3.setPublicacion(publicacion);
        imagen4.setPublicacion(publicacion);

        session().save(imagen);
        session().save(imagen2);
        session().save(imagen3);
        session().save(imagen4);
        session().save(publicacion);

        return publicacion;
    }

    private void entoncesMeDevuelveLaLatitudDelAPropiedad(Double latitudBuscada) {
        Double latitudEncontrada = -34.67006934350658;
        assertThat(latitudEncontrada).isEqualTo(latitudBuscada);
    }

    private void entoncesMeDevuelveLaLongitudDelAPropiedad(Double longitudBuscada) {
        Double longitudEncontrada = -58.563277268754504;
        assertThat(longitudEncontrada).isEqualTo(longitudBuscada);
    }


    private void entoncesMeDevuelveLasPublicacionesDestacadas(List<Publicacion> destacadas, int cantidad) {
        assertThat(destacadas).hasSize(cantidad);
    }

    private void entoncesMeDevuelveLosDatosDelUsuarioAEnviar(String email, String esperado) {
        assertThat(email).isEqualTo(esperado);
    }

    private void entoncesMeDevuelveLosDatosDelUsuarioPropietarioDELaPropiedad( Usuario obtenido) {
        assertThat(obtenido).isNotNull();
    }

    private void dadoQueExisteUnaListaDePropiedadesConPropietarios() {
        Publicacion publicacionUno = new Publicacion();
        Publicacion publicacionDos = new Publicacion();

        Propiedad propiedadUno = new Propiedad();
        Propiedad propiedadDos = new Propiedad();

        Ubicacion ubicacionUno = new Ubicacion();
        ubicacionUno.setProvincia("Buenos Aires");
        ubicacionUno.setLocalidad("Ramos Mejia");

        Ubicacion ubicacionDos = new Ubicacion();
        ubicacionDos.setProvincia("Buenos Aires");
        ubicacionDos.setLocalidad("Ramos Mejia");


        Usuario propietario1=new Usuario();
        propietario1.setEmail("sullca@gmail");

        Usuario propietario2=new Usuario();
        propietario2.setEmail("sullca2@gmail");

        propiedadUno.setUbicacion(ubicacionUno);
        propiedadUno.setTipoPropiedad(TipoPropiedad.DEPARTAMENTO);
        propiedadUno.setPropietario(propietario1);
        publicacionUno.setTipoAccion(ACCION_EXISTENTE);
        publicacionUno.setPropiedad(propiedadUno);

        propiedadDos.setUbicacion(ubicacionDos);
        propiedadDos.setTipoPropiedad(TipoPropiedad.CASA);
        propiedadDos.setPropietario(propietario2);
        publicacionDos.setTipoAccion(ACCION_EXISTENTE);
        publicacionDos.setPropiedad(propiedadDos);


        session().save(propietario1);
        session().save(propietario2);
        session().save(ubicacionUno);
        session().save(ubicacionDos);
        session().save(propiedadUno);
        session().save(propiedadDos);
        session().save(publicacionUno);
        session().save(publicacionDos);
    }


    private void entoncesMeDevuelveElDetalleDeLaPropiedadConId(Integer idPropiedad, Integer idDetalle) {
        assertThat(idPropiedad).isEqualTo(idDetalle);
    }

    private void entoncesNoMeDevuelveNingunaPublicacion(List<Publicacion> propiedades) {
        assertThat(propiedades).hasSize(0);
    }

    private void dadoQueNoExisteUnaListaDePublicaciones() {
    }

    private void entoncesMeDevuelveUnaListaDePublicacionesQueCoinciden(List<Publicacion> propiedades) {
        assertThat(propiedades).hasSize(1);
    }

    private void dadoQueExisteUnaListaDePublicaciones() {

        Publicacion publicacionUno = new Publicacion();
        Publicacion publicacionDos = new Publicacion();

        publicacionUno.setDestacada(true);
        publicacionDos.setDestacada(true);

        Propiedad propiedadUno = new Propiedad();
        Propiedad propiedadDos = new Propiedad();

        Ubicacion ubicacionDos = new Ubicacion();
        ubicacionDos.setProvincia("Buenos Aires");
        ubicacionDos.setLocalidad("Ramos Mejia");
        ubicacionDos.setLatitud(-34.67006934350658);
        ubicacionDos.setLongitud(-58.563277268754504);

        Ubicacion ubicacionUno = new Ubicacion();
        ubicacionUno.setProvincia("Buenos Aires");
        ubicacionUno.setLocalidad("Ramos Mejia");
        ubicacionUno.setLatitud(-34.67006934350658);
        ubicacionUno.setLongitud(-58.563277268754504);

        propiedadUno.setUbicacion(ubicacionDos);
        propiedadUno.setTipoPropiedad(TipoPropiedad.CASA);
        propiedadDos.setUbicacion(ubicacionUno);
        propiedadDos.setTipoPropiedad(TipoPropiedad.DEPARTAMENTO);

        publicacionUno.setTipoAccion(ACCION_EXISTENTE);
        publicacionDos.setTipoAccion(ACCION_EXISTENTE);
        publicacionUno.setPropiedad(propiedadUno);
        publicacionDos.setPropiedad(propiedadDos);

        session().save(ubicacionUno);
        session().save(ubicacionDos);
        session().save(propiedadUno);
        session().save(propiedadDos);
        session().save(publicacionUno);
        session().save(publicacionDos);

    }


}
