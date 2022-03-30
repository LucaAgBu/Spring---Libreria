package libreria.libreria.repositorios;

import java.util.List;
import libreria.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
    @Query("SELECT f FROM Autor f")
    public List<Autor> listaAutores();
    
    @Query("SELECT f FROM Autor f WHERE f.alta = true")
    public List<Autor> listaAutoresDisponibles();
    
    @Query("SELECT f FROM Autor f WHERE f.nombre = :nombre")
    public Autor buscarAutor(@Param("nombre") String nombre);

}
