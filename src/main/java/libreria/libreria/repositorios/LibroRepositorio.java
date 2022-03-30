/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.libreria.repositorios;

import java.util.List;
import libreria.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Los ninios
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
    
    @Query("SELECT f FROM Libro f WHERE f.autor.id = :id")
    public List<Libro> ListaLibrosporAutor(@Param("id") String id);
    
    @Query("SELECT f FROM Libro f WHERE f.editorial.id = :id")
    public List<Libro> ListaLibrosporEditorial(@Param("id") String id);
    
    @Query("SELECT f FROM Libro f ")
    public List<Libro> ListaLibros();
    
    @Query("SELECT f FROM Libro f WHERE f.alta = true")
    public List<Libro> ListaLibrosDisponibles();

    @Query("SELECT f FROM Libro f WHERE f.titulo = :titulo")
    public Libro LibroporTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT f FROM Libro f WHERE f.isbn = :isbn")
    public Libro LibroporIsbn(@Param("isbn") String isbn);
    
}
