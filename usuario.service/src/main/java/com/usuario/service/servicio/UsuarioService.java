package com.usuario.service.servicio;



import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeignClient carroFeignClient;
    @Autowired
    private MotoFeignClient motoFeignClient;

    public List<Usuario> getALl(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

    /////////////////
    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros =restTemplate.getForObject("http://localhost:8002/carro/usuario/"+usuarioId,List.class);
        return carros;
    }
    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos =restTemplate.getForObject("http://localhost:8003/moto/usuario/"+usuarioId,List.class);
        return motos;
    }
///FEIGNCLIENT
    public Carro saveCarro(int usuarioId,Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return  nuevoCarro;
    }
    public Moto saveMoto(int usuarioId,Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return  nuevaMoto;
    }
    //Un carro especifico
    public Carro obtenerCarro(int id){
        Carro nuevoCarro = carroFeignClient.getCarro(id);
        return  nuevoCarro;
    }

    public List<Carro> getCarrosFe(int usuarioId){
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        return carros;
    }
    public List<Moto> getMotosFe(int usuarioId){
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        return motos;
    }




    public void delete(int id){
        usuarioRepository.deleteById(id);
    }

}
