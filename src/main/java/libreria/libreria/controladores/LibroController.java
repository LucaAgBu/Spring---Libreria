/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.libreria.controladores;

import java.util.List;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Autor;
import libreria.libreria.entidades.Editorial;
import libreria.libreria.entidades.Libro;
import libreria.libreria.repositorios.AutorRepositorio;
import libreria.libreria.repositorios.EditorialRepositorio;
import libreria.libreria.repositorios.LibroRepositorio;
import libreria.libreria.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Los ninios
 */

@Controller
@RequestMapping("/")
public class LibroController {
    
    @Autowired
    private PortalController PortalController;
        
    @Autowired
    private LibroService libroService;
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired 
    private EditorialRepositorio editorialRepositorio;
    
    @GetMapping("/libros")
    public String libro(ModelMap modelo){
        List<Libro> libros = libroRepositorio.ListaLibros();
        modelo.put("libros", libros);
        
        List<Autor> autores = autorRepositorio.listaAutoresDisponibles();
        modelo.put("autores", autores);
       
        List<Editorial> editoriales = editorialRepositorio.listaEditorialesDisponibles();
        modelo.put("editoriales", editoriales);
        
        return "libros.html";
    }
    
    @PostMapping("/ingresarLibro")
    public String ingresarLibro(ModelMap modelo, @RequestParam String titulo, @RequestParam String ano, @RequestParam int ejemplares, @RequestParam Long isbn, @RequestParam String idAutor, @RequestParam String idEditorial ) {
        try {
        libroService.registrarLibro(isbn, titulo, ano, ejemplares, idAutor, idEditorial);
        } catch (ErrorServicio ex) {
        modelo.put("error", ex.getMessage());
        //    Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
        return "libros.html";
        }
        modelo.put("exito", "Se ingresaron los datos correctamente");
        return libro(modelo);
    }
    
    @PostMapping("/actualizarLibro")
    public String actualizarLibro(ModelMap modelo, @RequestParam String titulo, @RequestParam String ano, @RequestParam int ejemplares, @RequestParam int ejemplaresPrestados, @RequestParam int ejemplaresRestantes, @RequestParam Long isbn, @RequestParam String idAutor, @RequestParam String idEditorial, @RequestParam String idLibro ) throws Exception {
        try {
        libroService.modificarLibro(idLibro, isbn, titulo, ano, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
        } catch (ErrorServicio ex) {
        modelo.put("error", ex.getMessage());
        //    Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
        return libro(modelo);
        }
        modelo.put("exito", "Se ingresaron los datos correctamente");
        return libro(modelo);
    }  

    
    @PostMapping("/prestar")
    public String Prestar(ModelMap modelo, @RequestParam String idLibro) throws ErrorServicio{
        try {
            libroService.prestarLibro(idLibro);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return "libros.html";
        }
        modelo.put("exito", "Se presto un libro");
        return PortalController.index(modelo);
    }
    
        @PostMapping("/devolver")
        public String Devolver(ModelMap modelo, @RequestParam String idLibro) throws ErrorServicio{
        try {
            libroService.devolverLibro(idLibro);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return "libros.html";
        }
        modelo.put("exito", "Se presto un libro");
        return PortalController.index(modelo);
    }
    
        @PostMapping("/bajaLibro")
        public String BajaLibro(ModelMap modelo, @RequestParam String idLibro) throws ErrorServicio{
        try {
            libroService.Baja(idLibro);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return "libros.html";
        }
        modelo.put("exito", "Se dio de baja correctamente   ");
        return libro(modelo);
    }        
        
        @PostMapping("/altaLibro")
        public String AltaLibro(ModelMap modelo, @RequestParam String idLibro) throws ErrorServicio{
        try {
            libroService.Alta(idLibro);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return "libros.html";
        }
        modelo.put("exito", "Se dio de alta correctamente");
        return libro(modelo);
    }
    
}
