package com.carro.service.controladores;

import com.carro.service.entidades.Carro;
import com.carro.service.servicios.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros(){
        List<Carro> carros = carroService.getAll();
        if (carros.isEmpty())
            return  ResponseEntity.noContent().build();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id){
        Carro carro = carroService.getCarroById(id);
        if (carro == null)
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public  ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
        Carro newCarro = carroService.save(carro);
        return ResponseEntity.ok(newCarro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCarro(@PathVariable("id") int id){
        carroService.delete(id);
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosPorUsuarioId(@PathVariable("usuarioId") int id){
        List<Carro> carros = carroService.byUsuarioId(id);
        if (carros.isEmpty())
            return  ResponseEntity.noContent().build();
        return ResponseEntity.ok(carros);
    }


}
