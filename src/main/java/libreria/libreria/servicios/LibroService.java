package libreria.libreria.servicios;

import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Autor;
import libreria.libreria.entidades.Editorial;
import libreria.libreria.entidades.Libro;
import libreria.libreria.repositorios.EditorialRepositorio;
import libreria.libreria.repositorios.AutorRepositorio;
import libreria.libreria.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    
    @Autowired
    private LibroRepositorio repositorio;
    
    @Autowired
    private AutorRepositorio repositorioautor;
    
    @Autowired
    private EditorialRepositorio repositorioeditorial;
    
    @Transactional
    public void registrarLibro (Long isbn, String titulo, String ano, int ejemplares, String autorId, String editorialId) throws ErrorServicio{
        
        validar(isbn, titulo, ano, ejemplares, autorId, editorialId);
        
        Autor autor = repositorioautor.findById(autorId).get();
        Editorial editorial = repositorioeditorial.findById(editorialId).get();
        
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAno(ano);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setAlta(true);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        repositorio.save(libro);
        
    }
    
    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, String ano, int ejemplares, int ejemplaresPrestados, int ejemplaresRestantes, String autorId, String editorialId) throws ErrorServicio, Exception{
        
        validar(isbn, titulo, ano, ejemplares, autorId, editorialId);
        
        Autor autor = repositorioautor.findById(autorId).get();
        Editorial editorial = repositorioeditorial.findById(editorialId).get();

        Optional<Libro> respuesta=repositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Libro libro = respuesta.get();

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAno(ano);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
        
            repositorio.save(libro);
            
        }
        else{
            
            throw new ErrorServicio("No se encontro el libro");
            
        }
        
    }
    
    @Transactional
    public void prestarLibro(String id) throws ErrorServicio{
        
        Optional<Libro> respuesta= repositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Libro libro = respuesta.get();
            
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
            
            repositorio.save(libro);
        }
        else{
            throw new ErrorServicio("No se encontro el libro");
        }
        
    }
    
    @Transactional
    public void devolverLibro(String id) throws ErrorServicio{
        
        Optional<Libro> respuesta= repositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Libro libro = respuesta.get();
            
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);
            
            repositorio.save(libro);
            
        }
        else{
            throw new ErrorServicio("No se encontro el libro");
        }
        
    }
    
    @Transactional
    public void Baja(String id) throws ErrorServicio{
        
        Optional<Libro> respuesta= repositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Libro libro = respuesta.get();
            
            libro.setAlta(false);
            
            repositorio.save(libro);
            
        }
        else{
            throw new ErrorServicio("No se encontro el libro");
        }  
    }
    
    @Transactional
    public void Alta(String id) throws ErrorServicio{
        
        Optional<Libro> respuesta= repositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Libro libro = respuesta.get();
            
            libro.setAlta(true);
            
            repositorio.save(libro);
            
        }
        else{
            throw new ErrorServicio("No se encontro el libro");
        }  
    }    
    
    public void validar(Long isbn, String titulo, String ano, int ejemplares, String autorId, String editorialId) throws ErrorServicio{
        
        if (isbn==0 || isbn==null) {
            throw new ErrorServicio("El isbn no puede ser nulo");
        }
        if (titulo == null || titulo.isEmpty()){
            throw new ErrorServicio("El titulo no puede ser nulo");
        }
        if (ejemplares==0) {
            throw new ErrorServicio("Ejemplares no puede ser nulo");
        }
        if (autorId == null || autorId.isEmpty()) {
            throw new ErrorServicio("El autor no puede ser nulo");
        }
        if (editorialId == null || editorialId.isEmpty()) {
            throw new ErrorServicio("La editorial no puede ser nula");
        }
        
    }

    
}
