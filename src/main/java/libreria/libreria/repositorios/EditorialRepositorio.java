package libreria.libreria.repositorios;

import java.util.List;
import libreria.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
    @Query("SELECT f FROM Editorial f")
    public List<Editorial> listaEditoriales();
    
    @Query("SELECT f FROM Editorial f WHERE f.alta = true")
    public List<Editorial> listaEditorialesDisponibles();
    
    @Query("SELECT f FROM Editorial f WHERE f.nombre = :nombre")
    public Editorial buscarEditorial(@Param("nombre") String nombre);
    
}
