package com.usuario.service.controlador;



import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarusuarios(){
        List<Usuario> usuarios = usuarioService.getALl();
        if (usuarios.isEmpty())
            return  ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public  ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(newUsuario);
    }
////////////////////////////RestTemplate
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null)
            return ResponseEntity.notFound().build();
        List<Carro> carros = usuarioService.getCarros(id);
        return ResponseEntity.ok(carros);

    }
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null)
            return ResponseEntity.notFound().build();
        List<Moto> motos = usuarioService.getMotos(id);
        return ResponseEntity.ok(motos);

    }


    ///FEIGNCLIENT
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId,@RequestBody Carro carro){
        Carro nuevocarro = usuarioService.saveCarro(usuarioId,carro);
        return ResponseEntity.ok(nuevocarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId,@RequestBody Moto moto){
        Moto nuevaMoto = usuarioService.saveMoto(usuarioId,moto);
        return ResponseEntity.ok(nuevaMoto);
    }
    //Un solo carro por Id
    @GetMapping("/carroPorId/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id){
        Carro obtenercarro = usuarioService.obtenerCarro(id);
        return ResponseEntity.ok(obtenercarro);
    }


    @GetMapping("/moto/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosFe(@PathVariable("usuarioId") int usuarioId){
        List<Moto> listamotos = usuarioService.getMotosFe(usuarioId);
        return ResponseEntity.ok(listamotos);
    }
    @GetMapping("/carro/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosFe(@PathVariable("usuarioId") int usuarioId){
        List<Carro> listacarros = usuarioService.getCarrosFe(usuarioId);
        return ResponseEntity.ok(listacarros);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") int id){
        usuarioService.delete(id);
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }



























}
