package libreria.libreria.controladores;

import java.util.List;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Autor;
import libreria.libreria.repositorios.AutorRepositorio;
import libreria.libreria.servicios.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class AutorController {
    
    @Autowired
    private AutorService autorService;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @GetMapping("/autores")
    public String autores(ModelMap modelo){
        List<Autor> autores = autorRepositorio.listaAutores();
        modelo.put("autores", autores);
        
        return "autores.html";
    }
    
    @PostMapping("/ingresarAutor")
    public String ingresarAutor(ModelMap modelo, @RequestParam String nombre) {
        try {
        autorService.ingresarAutor(nombre);         
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
        //    Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            return "autores.html";
        }
        modelo.put("exito", "Se ingresaron los datos correctamente");
        return autores(modelo);
    }
    
    @PostMapping("/bajaAutor")
        public String BajaAutor(ModelMap modelo, @RequestParam String idAutor) throws ErrorServicio{
        try {
            autorService.Baja(idAutor);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return autores(modelo);
        }
        modelo.put("exito", "Se dio de baja correctamente   ");
        return autores(modelo);
    }        
        
        @PostMapping("/altaAutor")
        public String AltaAutor(ModelMap modelo, @RequestParam String idAutor) throws ErrorServicio{
        try {
            autorService.Alta(idAutor);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return autores(modelo);
        }
        modelo.put("exito", "Se dio de alta correctamente");
        return autores(modelo);
    }
        
        @PostMapping("/actualizarAutor")
    public String actualizarAutor(ModelMap modelo, @RequestParam String nombre, @RequestParam String idAutor) throws ErrorServicio {
        try {
            autorService.modificarAutor(idAutor, nombre);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
        //    Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            return autores(modelo);
        }
        modelo.put("exito", "Se actualizaron los datos correctamente");
        return autores(modelo);
    }
    
}
