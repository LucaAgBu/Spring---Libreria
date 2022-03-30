package libreria.libreria.servicios;

import java.util.Optional;
import javax.transaction.Transactional;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Autor;
import libreria.libreria.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    
    @Autowired
    private AutorRepositorio repositorio;
    
    @Transactional
    public void ingresarAutor(String nombre) throws ErrorServicio{
        
        validar(nombre);
        
        Autor autor = new Autor();
        

        autor.setNombre(nombre);
        autor.setAlta(true);
        
        repositorio.save(autor);
        
    }
    
    @Transactional
    public void modificarAutor(String id, String nombre) throws ErrorServicio{
        
        validar(nombre);
        Optional<Autor> respuesta = repositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Autor autor = respuesta.get();
        
            autor.setNombre(nombre);
        
            repositorio.save(autor);        

        }else{
            throw new ErrorServicio("No se encontro ese autor");
        }
        
    }
    
    @Transactional
    public void Baja(String id) throws ErrorServicio{
        
        Optional<Autor> respuesta = repositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Autor autor = respuesta.get();
        
            autor.setAlta(false);
        
            repositorio.save(autor);        

        }else{
            throw new ErrorServicio("No se encontro ese autor");
        }
        
    }
    
    @Transactional
    public void Alta(String id) throws ErrorServicio{
        
        Optional<Autor> respuesta = repositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Autor autor = respuesta.get();
        
            autor.setAlta(true);
        
            repositorio.save(autor);        

        }else{
            throw new ErrorServicio("No se encontro ese autor");
        }
        
    }    
    
    public void validar(String nombre) throws ErrorServicio{
        
        if(nombre == null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        
    }
    
}
