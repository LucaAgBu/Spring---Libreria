package libreria.libreria.servicios;

import java.util.Optional;
import javax.transaction.Transactional;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Editorial;
import libreria.libreria.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {
    
    @Autowired
    private EditorialRepositorio repositorio;
    
    @Transactional
    public void ingresarEditorial(String nombre) throws ErrorServicio{
        
        validar(nombre);
        
        Editorial editorial = new Editorial();
        

        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        //System.out.println(editorial);
        repositorio.save(editorial);
        
    }
    
    @Transactional
    public void modificarEditorial(String id, String nombre) throws ErrorServicio{
        
        validar(nombre);
        
        Optional<Editorial> respuesta = repositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            
            repositorio.save(editorial);
            
        }else{
            throw new ErrorServicio("No se encontro esa editorial");
        }
        
    }
    
    @Transactional
    public void Baja(String id) throws ErrorServicio{
        
        Optional<Editorial> respuesta = repositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Editorial editorial = respuesta.get();
            
            editorial.setAlta(false);
            
            repositorio.save(editorial);
            
        }else{
            throw new ErrorServicio("No se encontro esa editorial");
        }        
    }
    
    @Transactional
    public void Alta(String id) throws ErrorServicio{
        
        Optional<Editorial> respuesta = repositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Editorial editorial = respuesta.get();
            
            editorial.setAlta(true);
            
            repositorio.save(editorial);
            
        }else{
            throw new ErrorServicio("No se encontro esa editorial");
        }        
    }
    
    public void validar(String nombre) throws ErrorServicio{
        
        if(nombre == null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        
    }    
}
