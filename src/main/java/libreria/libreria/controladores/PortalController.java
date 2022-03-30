package libreria.libreria.controladores;

import java.util.List;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Libro;
import libreria.libreria.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class PortalController {
    
    @Autowired
    private LibroRepositorio libroRepositorio;


    @GetMapping("/")
    public String index(ModelMap modelo) throws ErrorServicio{
        List<Libro> libros = libroRepositorio.ListaLibrosDisponibles();
        modelo.put("libros", libros);
        
        return "index.html";
    }
  
}
